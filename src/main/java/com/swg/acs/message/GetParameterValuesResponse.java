/**
 * 
 */
package com.swg.acs.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;
import com.swg.acs.message.struct.ParameterValueStruct;

/**
 * @author satriaprayoga
 * 
 */
public class GetParameterValuesResponse extends CwmpMessage {
	private static final long serialVersionUID = 6214746844106518712L;

	private List<ParameterValueStruct> parameterValueStructs;
	
	
	//Device.RootDataModelVersion
	private static final String  deviceRootDataModelVersion = "Device.DeviceInfo.SerialNumber";//Device.RootDataModelVersion
	private boolean deviceRootDataModelVersionNameflag = false;

	private String cwmpId;
	public String getCwmpId(){
		return cwmpId;
	}
	/**
	 * @return the deviceRootDataModelVersion
	 */
	public boolean getDeviceRootDataModelVersion() {
		return deviceRootDataModelVersionNameflag;
	}

	public GetParameterValuesResponse() {
		super("GetParameterValuesResponse");
		parameterValueStructs = new ArrayList<ParameterValueStruct>();
	}

	@Override
	protected void configureBody(MessageBody bodyPart,ArgumentFactory argumentFactory) {

	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
		System.out.println("GetParameterValuesResponse start =" + System.currentTimeMillis());
		MessageArgument argument = messageBody.getChild("ParameterList");
		Iterator<MessageArgument> iterator = argument.childIterator();
		StringBuilder sb = new StringBuilder();
		String serialNumber = null;
		while (iterator.hasNext()) {
			MessageArgument paramStruct = iterator.next();
			MessageArgument name = paramStruct.getChild("Name");
			MessageArgument valueMA = paramStruct.getChild("Value");
			String value = valueMA.getValue();
			String nameValue = name.getValue();
//			if(nameValue.contains("Device.DeviceInfo.SerialNumber")){
//				serialNumber = value;
//			}
			if(nameValue.equalsIgnoreCase(deviceRootDataModelVersion)){
				deviceRootDataModelVersionNameflag=true;
			}
			if(checkValidity(valueMA,value)){
				ParameterValueStruct cpr = new ParameterValueStruct(nameValue, value == null ? "" : value);
				parameterValueStructs.add(cpr);
			}
//			sb.append(nameValue + " = " + value + "\n");
		}
		System.out.println("GetParameterValuesResponse end =" + System.currentTimeMillis());
//		if(deviceRootDataModelVersionNameflag&& serialNumber !=null){
//		  String str = sb.toString();
//		  
//		  String filepath = "log_xml_tr069/soapxml/" + serialNumber + "/parameter/" ;//L:/workspace/nms-console/
//		  //+ System.currentTimeMillis() + "parameter.txt"
//		  File dir=new File(filepath);
//		  if(!dir.exists()){
//		   dir.mkdir();
//		  }
//		  FileWriter fw = null;
//		  try{
//			  fw = new FileWriter(filepath + "parameter" +  System.currentTimeMillis() + ".txt"); 
//			  fw.write(str);
//			  fw.flush();
//		  }catch(IOException ex){
//			  ex.printStackTrace();
//		  }finally{
//			  if(fw!=null){
//				  try{
//					  fw.close();
//				  }catch(IOException e){}
//			  }
//		  }
//		  //File file = new File(filepath + "parameter" +  System.currentTimeMillis() + ".txt");
//		  
//		  
//		}
	}

	private boolean checkValidity(MessageArgument param,String value) {
		String paramType = param.getAttribute(param.getAttributeName());
		if(value == null || value.equals("")){
			return false;
		}
		switch (paramType) {
		case "xsd:int":
		case "xsd:unsignedInt":
			try {
				Integer.parseInt(value);
				return true;
			} catch (Exception e) {
				return false;
			}
		case "xsd:string":
			if(value == null || value.equals("")){
				return false;
			}else{
				return true;
			}
		case "xsd:boolean":
			if(value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")){
				return true;
			}
			break;
		case "xsd:dateTime":
			if(value.contains("-") && value.contains("T")){
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

	public List<ParameterValueStruct> getParameterValueStructs() {
		return parameterValueStructs;
	}

}
