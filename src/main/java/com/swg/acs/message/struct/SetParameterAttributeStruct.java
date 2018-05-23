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
public class SetParameterAttributeStruct implements Serializable {
	private static final long serialVersionUID = 2126388516325760046L;

	private Long deviceId;

	private Long messageId;

	private String name;

	private boolean notificationChange;

	private int notification;

	private boolean accessListChange;

	private List<String> accessList;

	private Timestamp dt;

	public SetParameterAttributeStruct() {
		accessList = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNotificationChange() {
		return notificationChange;
	}

	public void setNotificationChange(boolean notificationChange) {
		this.notificationChange = notificationChange;
	}

	public int getNotification() {
		return notification;
	}

	public void setNotification(int notification) {
		this.notification = notification;
	}

	public boolean isAccessListChange() {
		return accessListChange;
	}

	public void setAccessList(List<String> accessList) {
		this.accessList = accessList;
	}

	public List<String> getAccessList() {
		return accessList;
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

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public Timestamp getDt() {
		return dt;
	}

	public void setDt(Timestamp dt) {
		this.dt = dt;
	}

	public void setAccessListChange(boolean accessListChange) {
		this.accessListChange = accessListChange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SetParameterAttributeStruct [deviceId=");
		builder.append(deviceId);
		builder.append(", messageId=");
		builder.append(messageId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", notificationChange=");
		builder.append(notificationChange);
		builder.append(", notification=");
		builder.append(notification);
		builder.append(", accessListChange=");
		builder.append(accessListChange);
		builder.append(", accessList=");
		builder.append(accessList);
		builder.append(", dt=");
		builder.append(dt);
		builder.append("]");
		return builder.toString();
	}

}
