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
public class DeviceIdStruct implements Serializable {

	private static final long serialVersionUID = 2607823294795434673L;

	private Long deviceId;

	private String messageId;

	private String manufacturer;

	private String oui;

	private String productClass;

	private String serialNumber;

	private Timestamp dt;

	public DeviceIdStruct() {
	}

	public DeviceIdStruct(String manufacturer, String oui, String productClass,
			String serialNumber) {
		this.manufacturer = manufacturer;
		this.oui = oui;
		this.productClass = productClass;
		this.serialNumber = serialNumber;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getOui() {
		return oui;
	}

	public String getProductClass() {
		return productClass;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setOui(String oui) {
		this.oui = oui;
	}

	public void setProductClass(String productClass) {
		this.productClass = productClass;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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
		builder.append("DeviceIdStruct [deviceId=");
		builder.append(deviceId);
		builder.append(", messageId=");
		builder.append(messageId);
		builder.append(", manufacturer=");
		builder.append(manufacturer);
		builder.append(", oui=");
		builder.append(oui);
		builder.append(", productClass=");
		builder.append(productClass);
		builder.append(", serialNumber=");
		builder.append(serialNumber);
		builder.append(", dt=");
		builder.append(dt);
		builder.append("]");
		return builder.toString();
	}

}
