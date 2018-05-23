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
public class EventStruct implements Serializable {
	private static final long serialVersionUID = -3646559338576796664L;

	public static final String BOOTSTRAP = "0 BOOTSTRAP";
	public static final String BOOT = "1 BOOT";
	public static final String PERIODIC = "2 PERIODIC";
	public static final String SCHEDULED = "3 SCHEDULED";
	public static final String VALUE_CHANGE = "4 VALUE CHANGE";
	public static final String KICKED = "5 KICKED";
	public static final String CONNECTION_REQUEST = "6 CONNECTION REQUEST";
	public static final String TRANSFER_COMPLETE = "7 TRANSFER COMPLETE";
	public static final String DIAGNOSITICS_COMPLETE = "8 DIAGNOSTICS COMPLETE";
	public static final String M_TAG = "M ";
	public static final String X_TAG = "X ";
	
	
	
	private Long deviceId;

	private String messageId;

	private final String eventCode;

	private final String commandKey;

	private Timestamp dt;

	public EventStruct(String eventCode, String commandKey) {
		this.eventCode = eventCode;
		this.commandKey = commandKey;
	}

	public String getEventCode() {
		return eventCode;
	}

	public String getCommandKey() {
		return commandKey;
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
		builder.append("EventStruct [deviceId=");
		builder.append(deviceId);
		builder.append(", messageId=");
		builder.append(messageId);
		builder.append(", eventCode=");
		builder.append(eventCode);
		builder.append(", commandKey=");
		builder.append(commandKey);
		builder.append(", dt=");
		builder.append(dt);
		builder.append("]");
		return builder.toString();
	}

}
