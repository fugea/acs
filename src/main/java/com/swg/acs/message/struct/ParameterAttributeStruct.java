/**
 * 
 */
package com.swg.acs.message.struct;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author satriaprayoga
 * 
 */
public class ParameterAttributeStruct implements Serializable {
	private static final long serialVersionUID = -3311395019311828664L;

	private Long deviceId;

	private String messageId;

	private String name;

	private int notification;

	private List<String> accessList;

	private Timestamp dt;

	public ParameterAttributeStruct() {
		accessList = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNotification() {
		return notification;
	}

	public void setNotification(int notification) {
		this.notification = notification;
	}

	public List<String> getAccessList() {
		return accessList;
	}

	public void setAccessList(List<String> accessList) {
		this.accessList = accessList;
	}

	public void addAccessList(String acl) {
		accessList.add(acl);
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
		builder.append("ParameterAttributeStruct [deviceId=");
		builder.append(deviceId);
		builder.append(", messageId=");
		builder.append(messageId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", notification=");
		builder.append(notification);
		builder.append(", accessList=");
		builder.append(accessList);
		builder.append(", dt=");
		builder.append(dt);
		builder.append("]");
		return builder.toString();
	}

}
