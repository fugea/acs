/**
 * 
 */
package com.swg.acs.message.cwmp;

import java.io.Serializable;

/**
 * @author satriaprayoga
 * 
 */
public class SetParameterValuesFault implements Serializable {
	private static final long serialVersionUID = -7799354628549662011L;

	private final String parameterName;

	private final String faultCode;

	private final String faultString;

	public SetParameterValuesFault(String parameterName, String faultCode,
			String faultString) {
		this.parameterName = parameterName;
		this.faultCode = faultCode;
		this.faultString = faultString;
	}

	public String getFaultCode() {
		return faultCode;
	}

	public String getFaultString() {
		return faultString;
	}

	public String getParameterName() {
		return parameterName;
	}
}
