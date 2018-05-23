/***********************************************************************
 * $Id: DeviceStat.java,v1.0 2012-8-31 下午1:09:45 $
 * 
 * @author: loyal
 * 
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.swg.acs.message.struct;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author loyal
 * @created @2012-8-31-下午1:09:45
 * 
 */
public class DeviceStat implements Serializable {
	private static final long serialVersionUID = 8433236147771867402L;

	private Long deviceId;

	private String messageId;

	private Integer maxEnvelopes;

	private Integer retryCount;

	private String currentTime;

	private Timestamp dt;

	public Long getDeviceId() {
		return deviceId;
	}

	public Integer getMaxEnvelopes() {
		return maxEnvelopes;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public void setMaxEnvelopes(Integer maxEnvelopes) {
		this.maxEnvelopes = maxEnvelopes;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
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
		builder.append("DeviceStat [deviceId=");
		builder.append(deviceId);
		builder.append(", messageId=");
		builder.append(messageId);
		builder.append(", maxEnvelopes=");
		builder.append(maxEnvelopes);
		builder.append(", retryCount=");
		builder.append(retryCount);
		builder.append(", currentTime=");
		builder.append(currentTime);
		builder.append(", dt=");
		builder.append(dt);
		builder.append("]");
		return builder.toString();
	}

}
