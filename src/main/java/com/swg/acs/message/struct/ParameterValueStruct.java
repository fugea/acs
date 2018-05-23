/**
 * 
 */
package com.swg.acs.message.struct;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author satriaprayoga
 * 
 */
public class ParameterValueStruct implements Serializable {
	private static final long serialVersionUID = 2539202839927604798L;

	private Long deviceId;

	private String messageId;

	private String name;

	private String value;

	private Timestamp dt;

	public ParameterValueStruct(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Timestamp getDt() {
		return dt;
	}

	public void setDt(Timestamp dt) {
		this.dt = dt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParameterValueStruct [deviceId=");
		builder.append(deviceId);
		builder.append(", messageId=");
		builder.append(messageId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", value=");
		builder.append(value);
		builder.append(", dt=");
		builder.append(dt);
		builder.append("]");
		return builder.toString();
	}

}
