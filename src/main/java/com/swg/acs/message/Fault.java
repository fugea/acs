/**
 * 
 */
package com.swg.acs.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.swg.acs.Argument;
import com.swg.acs.Message;
import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.MessageEnvelope;
import com.swg.acs.MessageHeader;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessageBody;
import com.swg.acs.message.cwmp.CwmpMessageEnvelope;
import com.swg.acs.message.cwmp.CwmpMessageHeader;
import com.swg.acs.message.struct.FaultStruct;

/**
 * @author satriaprayoga
 * 
 */
public class Fault extends Message {
	private static final long serialVersionUID = 2857982661541431939L;

	protected SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	public static final String FCODE_REQUEST_DENIED = "9001";

	public static final String FCODE_INTERNAL = "9002";

	public static final String FCODE_INVALID_ARGS = "9003";

	public static final String FCODE_RESOURCE_EXCEEDED = "9004";

	public static final String FCODE_INVALID_PARAMETER_NAME = "9005";

	public static final String FCODE_INVALID_PARAMETER_TYPE = "9006";

	public static final String FCODE_INVALID_PARAMETER_VALUE = "9007";

	public static final String FCODE_PARAMETER_READONLY = "9008";

	public static final String FCODE_NOTIFICATION_REJECTED = "9009";

	public static final String FCODE_DOWNLOAD_FAILURE = "9010";

	public static final String FCODE_UPLOAD_FAILURE = "9011";

	public static final String FCODE_FILE_TRANSFER_AUTHENTICATION_FAILURE = "9012";

	public static final String FCODE_PROTOCOL_NOT_SUPPORTED = "9013";

	public static final String FCODE_DLF_MULTICAST = "9014";

	public static final String FCODE_DLF_NO_CONTACT = "9015";

	public static final String FCODE_DLF_FILE_ACCESS = "9016";

	public static final String FCODE_DLF_UNABLE_TO_COMPLETE = "9017";

	public static final String FCODE_DLF_FILE_CORRUPTED = "9018";

	public static final String FCODE_DLF_FILE_AUTHENTICATION = "9019";

	public static final String FCODE_ACS_METHOD_NOT_SUPPORTED = "8000";

	public static final String FCODE_ACS_REQUEST_DENIED = "8001";

	public static final String FCODE_ACS_INTERNAL_ERROR = "8002";

	public static final String FCODE_ACS_INVALID_ARGS = "8003";

	public static final String FCODE_ACS_RESOURCE_EXCEEDED = "8004";

	public static final String FCODE_ACS_RETRY = "8005";

	private String faultCode;

	private String faultString;

	private List<FaultStruct> parameterValuesFaults;

	public Fault(String faultCode, String faultString) {
		super("Fault");
		messageBody = new CwmpMessageBody("Fault", CWMP_PREFIX, getUri());
		messageEnvelope = new CwmpMessageEnvelope();
		messageHeader = new CwmpMessageHeader();
		this.faultCode = faultCode;
		this.faultString = faultString;
		parameterValuesFaults = new ArrayList<FaultStruct>();
	}

	@Override
	protected void configureHeader(MessageHeader header,
			ArgumentFactory argumentFactory,String cwmpId) {
		Argument idarg = header.addMessageArgument(argumentFactory
				.createMessageArgument("ID", CWMP_PREFIX, getUri()));
		idarg.setAttribute("SOAP-ENV:mustUnderstand",
				header.isMustUnderstand() ? "1" : "0");
		idarg.setValue(cwmpId);//getId()
	}

	@Override
	protected void configureEnvelope(MessageEnvelope envelope) {
		envelope.addNamespace(SOAP_ENC_PREFIX, SOAP_ENC_URN);
		envelope.addNamespace(XSD_PREFIX, XSD_URN);
		envelope.addNamespace(XSI_PREFIX, XSI_URN);
		envelope.addNamespace(CWMP_PREFIX, getUri());
	}

	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		bodyPart.addMessageArgument(
				argumentFactory.createMessageArgument("FaultCode")).setValue(
				faultCode);
		bodyPart.addMessageArgument(
				argumentFactory.createMessageArgument("FaultString")).setValue(
				faultString);
		for (FaultStruct fault : parameterValuesFaults) {
			MessageArgument faultParamArg = bodyPart
					.addMessageArgument(argumentFactory
							.createMessageArgument("SetParameterValuesFault"));
			faultParamArg.addMessageArgument(
					argumentFactory.createMessageArgument("ParameterName"))
					.setValue(fault.getParameterName());
			faultParamArg.addMessageArgument(
					argumentFactory.createMessageArgument("FaultCode"))
					.setValue(fault.getFaultCode());
			faultParamArg.addMessageArgument(
					argumentFactory.createMessageArgument("FaultString"))
					.setValue(fault.getFaultString());
		}
	}

	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {

	}

	public void addParameterFault(String parameterName, String faultCode,
			String faultString) {
		FaultStruct fault = new FaultStruct();
		fault.setParameterName(parameterName);
		fault.setFaultCode(faultCode);
		fault.setFaultString(encode(faultString));
		parameterValuesFaults.add(fault);
	}

	public String getFaultCode() {
		return faultCode;
	}

	public String getFaultString() {
		return faultString;
	}

	public List<FaultStruct> getParameterValuesFaults() {
		return parameterValuesFaults;
	}

	public static String encode(String source) {
		if (source == null)
			return "";
		source = source.replace("\'", " ");
		source = source.replace("\"", "  ");
		return source;
	}
}
