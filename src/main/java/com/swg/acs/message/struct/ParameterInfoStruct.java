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
public class ParameterInfoStruct implements Serializable {
	private static final long serialVersionUID = -6946905385240989908L;

	private Long deviceId;

	private String messageId;

	private String name;

	private boolean writable;

	private Timestamp dt;

	public ParameterInfoStruct(String name, boolean writable) {
		this.name = name;
		this.writable = writable;
	}

	public String getName() {
		return name;
	}

	public boolean isWritable() {
		return writable;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public Timestamp getDt() {
		return dt;
	}

	public void setDt(Timestamp dt) {
		this.dt = dt;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setWritable(boolean writable) {
		this.writable = writable;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ParameterInfoStruct [deviceId=");
		builder.append(deviceId);
		builder.append(", messageId=");
		builder.append(messageId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", writable=");
		builder.append(writable);
		builder.append(", dt=");
		builder.append(dt);
		builder.append("]");
		return builder.toString();
	}

}
