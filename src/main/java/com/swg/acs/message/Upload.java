/**
 * 
 */
package com.swg.acs.message;

import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;

/**
 * @author satriaprayoga
 *
 */
public class Upload extends CwmpMessage {
	private static final long serialVersionUID = 8610882003673215156L;
	
	public static final String FT_CONFIG = "1 Vendor Configuration File";
    public static final String FT_LOG = "2 Vendor Log File";

	private String commandKey;
	private String fileType;
	private String url;
	private String username;
	private String password;
	private int delaySeconds;
	private String cwmpId;
	public String getCwmpId(){
		return cwmpId;
	}

	public Upload() {
		super("Upload");
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("CommandKey")).setValue(commandKey);
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("FileType")).setValue(fileType);
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("URL")).setValue(url);
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("Username")).setValue(username);
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("Password")).setValue(password);
		bodyPart.addMessageArgument(argumentFactory.createMessageArgument("DelaySeconds")).setValue(String.valueOf(delaySeconds));
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
	}

	public String getCommandKey() {
		return commandKey;
	}

	public void setCommandKey(String commandKey) {
		this.commandKey = commandKey;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDelaySeconds() {
		return delaySeconds;
	}

	public void setDelaySeconds(int delaySeconds) {
		this.delaySeconds = delaySeconds;
	}
	
	

}
