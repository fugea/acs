package com.suma.acs.messageDo;


import javax.servlet.http.HttpSession;

import com.suma.acs.utils.Context;
import org.springframework.stereotype.Component;

import com.suma.acs.entity.CmdScheduleDomain;
import com.swg.acs.Message;
import com.swg.acs.message.Download;
import com.swg.acs.message.Inform;

@Component("Download")
public class DownloadDo extends MessageBaseDo {

	private HttpSession session;
	private CmdScheduleDomain csd;
	@Override
	public Message process(Message request) {
		Inform lastInform = (Inform) session.getAttribute(Context.LASTINFORM);
		Download responseMessage = new Download();
		if (csd != null) {
//			AcsServerDao acsServerDao = (AcsServerDao) SpringApplicationContext.getBean("acsServerDao", AcsServerDao.class);
//			List<DeviceNeedDoAction> list = acsServerDao.getDeviceNeedDoActionList01(String.valueOf(dna.getDnid()), 1);
//          DeviceNeedDoActionParameter temp = list.get(i);
			String nameValues = csd.getParameters();
			if((nameValues!=null) && (!"".equals(nameValues))){
				String[] nameValue = nameValues.split("#dnda#");
				for (int j = 0; j < nameValue.length; j++) {
					String[] nv = nameValue[j].split("@dndap@");
					String name = nv[0];
					String value = nv[1].trim();
					if (value.equalsIgnoreCase("") || value == null)
						continue;
					if (name.equalsIgnoreCase("Download.commandKey")) {
						responseMessage.setCommandKey(value);
					}
					if (name.equalsIgnoreCase("Download.fileType")) {
						responseMessage.setFileType(value);
					}
					if (name.equalsIgnoreCase("Download.url")) {
						responseMessage.setUrl(value);
					}
					if (name.equalsIgnoreCase("Download.userName")) {
						responseMessage.setUserName(value);
					}
					if (name.equalsIgnoreCase("Download.password")) {
						responseMessage.setPassword(value);
					}
					if (name.equalsIgnoreCase("Download.fileSize")) {
						responseMessage.setFileSize(Long.parseLong(value));
					}
					if (name.equalsIgnoreCase("Download.targetFileName")) {
						responseMessage.setTargetFileName(value);
					}
					if (name.equalsIgnoreCase("Download.successURL")) {
						responseMessage.setSuccessUrl(value);
					}
					if (name.equalsIgnoreCase("Download.failureURL")) {
						responseMessage.setFailureUrl(value);
					}	
					if(name.equalsIgnoreCase("Download.upgradeType")){
						responseMessage.setUpdateType(value);
					}
					if(name.equalsIgnoreCase("Download.sversion")){
						responseMessage.setMajorSWupdate(value);
					}
					if(name.equalsIgnoreCase("Download.asversion")){
						responseMessage.setMinorSWupdate(value);
					}
					if(name.equalsIgnoreCase("Download.checkTime")){
						responseMessage.setCheckTime(value);
					}
					if(name.equalsIgnoreCase("Download.hCode")){
						responseMessage.setHcode(value);
					}
			    }
			}
		}
		responseMessage.setUri(lastInform.getUri());
		session.setAttribute(Context.LASTACTION, responseMessage.getName());
		return responseMessage;
	}

	public void setSession(HttpSession session) {
        this.session = session;
    }

    public void setOrder(Message order) {
    }

    public Message getResult() {
        return null; // To change body of implemented methods use File | Settings | File Templates.
    }

}
