/***********************************************************************
 * $ RPCMethod.java,v1.0 2012-12-27 16:26:00 $
 *
 * @author: jay
 *
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.swg.acs.message.struct;

import java.io.Serializable;

/**
 * @author jay
 * @created @2012-12-27-16:26:00
 */
public class RPCMethodStruct implements Serializable {
    private static final long serialVersionUID = 7342180206202447869L;

    private Long deviceId;
    private String messageId;
    private String method;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("RPCMethod");
        sb.append("{deviceId=").append(deviceId);
        sb.append(", messageId='").append(messageId).append('\'');
        sb.append(", method='").append(method).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
