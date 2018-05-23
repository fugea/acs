package com.swg.acs.message.struct;

import java.io.Serializable;

public class FaultStruct implements Serializable {

	String faultCode;
	
	String faultString;
	
	String parameterName;

	public String getFaultCode() {
		return faultCode;
	}

	public void setFaultCode(String faultCode) {
		this.faultCode = faultCode;
	}

	public String getFaultString() {
		return faultString;
	}

	public void setFaultString(String faultString) {
		this.faultString = faultString;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	@Override
	public String toString() {
		return "FaultStruct [faultCode=" + faultCode + ", faultString=" + faultString + ", parameterName="
				+ parameterName + "]";
	}
	
	
}
