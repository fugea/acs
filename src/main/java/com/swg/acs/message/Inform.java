/**
 * 
 */
package com.swg.acs.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;
import com.swg.acs.message.struct.DeviceIdStruct;
import com.swg.acs.message.struct.EventStruct;
import com.swg.acs.message.struct.ParameterValueStruct;

/**
 * @author satriaprayoga
 *
 */
public class Inform extends CwmpMessage {

	private static final long serialVersionUID = -5714051071391056821L;

	private List<EventStruct> eventStructs;

	private List<ParameterValueStruct> parameterValueStructs;

	private Object parameterValueStructsObject = new Object();

	private DeviceIdStruct deviceIdStruct;

	private int maxEnvelopes = 1;

	private String currentTime;

	private int retryCount;

	private String root;
	
	private String cwmpId;
	
	public String getCwmpId(){
		return cwmpId;
	}
	
	

	public Inform() {
		super("Inform");
		eventStructs = new ArrayList<EventStruct>();
		parameterValueStructs = new ArrayList<ParameterValueStruct>();
	}

	/* (non-Javadoc)
	 * @see com.swg.acs.Message#configureBody(com.swg.acs.MessageBody, com.swg.acs.message.cwmp.ArgumentFactory)
	 */
	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {

	}

	public static Hashtable<String,Long> informTimeMap = new Hashtable<String,Long>();
	/* (non-Javadoc)
	 * @see com.swg.acs.Message#configureParse(com.swg.acs.MessageBody)
	 */
	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
		String sn = configureDeviceId(messageBody);
		configureEvent(messageBody);
		maxEnvelopes = Integer.parseInt(messageBody.getChild("MaxEnvelopes")
				.getValue());
		currentTime = messageBody.getChild("CurrentTime").getValue();
		retryCount = Integer.parseInt(messageBody.getChild("RetryCount")
				.getValue());
		configureParameterValueStruct(messageBody);
		long curTime = strTimeConvertLongTime(currentTime);
		informTimeMap.put(sn, System.currentTimeMillis());
//		if(informTimeMap.containsKey(sn)){
//			InformTime it = informTimeMap.get(sn);
//			long mapCurrTime = it.getCurTime();
//			it.setLastTime(mapCurrTime);
//			it.setCurTime(curTime);
//		}else{
//			InformTime it = new InformTime();
//			it.setSn(sn);
//			it.setLastTime(curTime);
//			it.setCurTime(curTime);
//			informTimeMap.put(sn, it);
//		}
	}

	private String configureDeviceId(MessageArgument argument) {
		MessageArgument deviceArg = argument.getChild("DeviceId");
		String manufacturer = deviceArg.getChild("Manufacturer").getValue();
		String oui = deviceArg.getChild("OUI").getValue();
		String productClass = deviceArg.getChild("ProductClass").getValue();
		String serialNumber = deviceArg.getChild("SerialNumber").getValue();
		deviceIdStruct = new DeviceIdStruct(manufacturer, oui, productClass,
				serialNumber);
		return serialNumber;
	}

	private void configureEvent(MessageArgument argument) {
		MessageArgument eventArg = argument.getChild("Event");
		Iterator<MessageArgument> iterator = eventArg.childIterator();
		while (iterator.hasNext()) {
			MessageArgument event = iterator.next();
			String eventCode = event.getChild("EventCode").getValue();
			String commandKey = event.getChild("CommandKey").getValue();
			EventStruct eventStruct = new EventStruct(eventCode, commandKey);
			eventStructs.add(eventStruct);
		}
	}

	private void configureParameterValueStruct(MessageArgument arg) {
		MessageArgument argument = arg.getChild("ParameterList");
		if(argument!=null){
			Iterator<MessageArgument> iterator = argument.childIterator();
			while (iterator.hasNext()) {
				MessageArgument paramStruct = iterator.next();
				MessageArgument name = paramStruct.getChild("Name");
				MessageArgument value = paramStruct.getChild("Value");

				ParameterValueStruct struct = new ParameterValueStruct(name.getValue(),value.getValue());
				parameterValueStructs.add(struct);
				if (root == null && !name.getValue().startsWith(".")) {
					if (name.getValue().startsWith("Device."))
						root = "Device";
					else if (name.getValue().startsWith("InternetGatewayDevice."))
						root = "InternetGatewayDevice";
				}
			}
		}
	}

	public List<EventStruct> getEventStructs() {
		return eventStructs;
	}

	public List<ParameterValueStruct> getParameterValueStructs() {
		return parameterValueStructs;
	}
	
	public void clearParameterValueStructs(){
		parameterValueStructs.clear();
	}

	public DeviceIdStruct getDeviceIdStruct() {
		return deviceIdStruct;
	}

	public int getMaxEnvelopes() {
		return maxEnvelopes;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getSoftwareVersion() {
		String v = paramsGet(root + ".DeviceInfo.SoftwareVersion");
		if (v != null) {
			v = v.replace('-', '.');
			v = v.replace(',', ' ');
		}
		return v;
	}

	public String getHardwareVersion() {
		return paramsGet(root + ".DeviceInfo.HardwareVersion");
	}

	public String getConfigVersion() {
		return paramsGet(root + ".DeviceInfo.VendorConfigFile.1.Version");
	}

	public String getURL() {
		String url = paramsGet(root + ".ManagementServer.ConnectionRequestURL");
		if (url != null) {
			return url;
		}
		url = paramsGet(root + ".ManagementServer.UDPConnectionRequestAddress");
		if (url != null) {
			url = (url.indexOf(':') == -1) ? "udp://" + url + ":80" : "udp://"
					+ url;
		}
		return url;
	}

	public String getConreqUser() {
		return paramsGet(root + ".ManagementServer.ConnectionRequestUsername");
	}

	public String getConreqPass() {
		return paramsGet(root + ".ManagementServer.ConnectionRequestPassword");
	}

	public String getProvisiongCode() {
		return paramsGet(root + ".DeviceInfo.ProvisioningCode");
	}

	public void setProvisiongCode(String code) {
		synchronized (parameterValueStructsObject) {
			for (ParameterValueStruct parameterValueStruct : parameterValueStructs) {
				if (parameterValueStruct.getName().equalsIgnoreCase(
						root + ".DeviceInfo.ProvisioningCode")) {
					parameterValueStruct.setValue(code);
				}
			}
		}
	}

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private long strTimeConvertLongTime(String time){
		if(time==null || time.equals(""))
			return 0;
		String newTime = time.replaceAll("[A-Z]+"," ").trim();
		Date newData = null;
		try{
			newData = simpleDateFormat.parse(newTime);
			return newData.getTime();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		
	}
	private String paramsGet(String s) {
		synchronized (parameterValueStructsObject) {
			for (ParameterValueStruct parameterValueStruct : parameterValueStructs) {
				if (parameterValueStruct.getName().equalsIgnoreCase(s)) {
					return parameterValueStruct.getValue();
				}
			}
			return null;
		}
	}
	public static void main(String []args) throws Exception{
     String data="2015-06-16T16:18:00Z"; 
     data = data.replaceAll("[A-Z]+"," ").trim(); 
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
     Date newData = sdf.parse(data); 
     long time = (System.currentTimeMillis() - newData.getTime())/1000;
	}
}
