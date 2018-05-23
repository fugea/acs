package com.suma.acs.entity;

import com.suma.acs.dao.QueryField;

public class Device extends BaseEntity {

    @QueryField
    private String oui;
    @QueryField
    private String productClass;
    @QueryField
    private String manufacturer;
    @QueryField
    private String registerDate;
    @QueryField
    private String upTime;
    @QueryField
    private String regionCode;
    @QueryField
    private String caID;
    @QueryField
    private String sversion;
    @QueryField
    private String hversion;

    public String getOui() {
        return oui;
    }

    public void setOui(String oui) {
        this.oui = oui;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getCaID() {
        return caID;
    }

    public void setCaID(String caID) {
        this.caID = caID;
    }

    public String getSversion() {
        return sversion;
    }

    public void setSversion(String sversion) {
        this.sversion = sversion;
    }

    public String getHversion() {
        return hversion;
    }

    public void setHversion(String hversion) {
        this.hversion = hversion;
    }

    @Override
    public String toString() {
        return "Device{" +
                "oui='" + oui + '\'' +
                ", productClass='" + productClass + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", upTime='" + upTime + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", caID='" + caID + '\'' +
                ", sversion='" + sversion + '\'' +
                ", hversion='" + hversion + '\'' +
                '}';
    }
}
