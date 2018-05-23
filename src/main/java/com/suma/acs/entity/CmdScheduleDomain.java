package com.suma.acs.entity;

import java.util.Objects;

public class CmdScheduleDomain extends BaseEntity {
    private Long cmdID;

    private String serialNumber;

    private String methodName;

    private String eventCode;

    private Integer status;

    private Integer priority;

    private String parameters;

    private String createTime;

    private String executeTime;

    private String reverse1;

    public Long getCmdID() {
        return cmdID;
    }

    public void setCmdID(Long cmdID) {
        this.cmdID = cmdID;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getReverse1() {
        return reverse1;
    }

    public void setReverse1(String reverse1) {
        this.reverse1 = reverse1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CmdScheduleDomain that = (CmdScheduleDomain) o;
        return Objects.equals(cmdID, that.cmdID) &&
                Objects.equals(serialNumber, that.serialNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cmdID, serialNumber);
    }

    @Override
    public String toString() {
        return "CmdScheduleDomain{" +
                "cmdID=" + cmdID +
                ", serialNumber='" + serialNumber + '\'' +
                ", methodName='" + methodName + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", parameters='" + parameters + '\'' +
                ", createTime='" + createTime + '\'' +
                ", executeTime='" + executeTime + '\'' +
                ", reverse1='" + reverse1 + '\'' +
                '}';
    }
}
