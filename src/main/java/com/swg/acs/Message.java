/**
 * 
 */
package com.swg.acs;

import java.io.Serializable;

import javax.xml.soap.SOAPMessage;

import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessageBody;

/**
 * Kelas dasar Message yang direquest/diresponse oleh CPE dan ACS
 * 
 * @author satriaprayoga
 * 
 */
public abstract class Message implements Serializable {

	private static final long serialVersionUID = 2147832877293437589L;

	public static final String CWMP = "cwmp";

	public static final String PARAMETER_KEY = "ParameterKey";

	public static final String COMMAND_KEY = "CommandKey";

	public static final String XSI_TYPE = "xsi:type";

	public static final String XSD_STRING = "xsd:string";

	public static final String XSD_UNSIGNEDINT = "xsd:unsignedInt";

	public static final String XSD_INT = "xsd:int";

	public static final String XSD_BOOLEAN = "xsd:boolean";

	public static final String XSD_DATETIME = "xsd:dateTime";

	public static final String XSD_BASE64 = "xsd:base64";

	public static final String SOAP_ARRAY_TYPE = "SOAP-ENC:arrayType";

	public static final String FAULT_CODE = "FaultCode";

	public static final String FAULT_STRING = "FaultString";

	public static final String SOAP_ENV_PREFIX = "SOAP-ENV";

	public static final String SOAP_ENV_URN = "http://schemas.xmlsoap.org/soap/envelope/";

	public static final String SOAP_ENC_PREFIX = "SOAP-ENC";

	public static final String SOAP_ENC_URN = "http://schemas.xmlsoap.org/soap/encoding/";

	public static final String XSD_PREFIX = "xsd";

	public static final String XSD_URN = "http://www.w3.org/2001/XMLSchema";

	public static final String XSI_PREFIX = "xsi";

	public static final String XSI_URN = "http://www.w3.org/2001/XMLSchema-instance";

	public static final String CWMP_PREFIX = "cwmp";

	public static final String CWMP_URN = "urn:dslforum-org:cwmp-1-2";

	protected String uri = "urn:dslforum-org:cwmp-1-2";

	protected String name;

	private String id;

	private boolean noMore = false;

	protected MessageBody messageBody;

	protected MessageHeader messageHeader;

	protected MessageEnvelope messageEnvelope;

	public Message() {
		this.name = getClass().getSimpleName();
		messageBody = new CwmpMessageBody(name);
	}

	public Message(String name) {
		this.name = name;
		messageBody = new CwmpMessageBody(name);
	}

	/**
	 * Implementasikan method ini untuk konfigurasi SOAP-Header
	 * 
	 * @param header
	 * @param argumentFactory
	 */
	protected abstract void configureHeader(MessageHeader header,
			ArgumentFactory argumentFactory,String cwmpId);

	/**
	 * Implementasikan method ini untuk konfigurasi SOAP-Envelope
	 * 
	 * @param envelope
	 */
	protected abstract void configureEnvelope(MessageEnvelope envelope);

	/**
	 * Implementasikan method ini untuk konfigurasi SOAP-Body. Ini berhubungan
	 * langsung dengan isi pesan
	 * 
	 * @param bodyPart
	 * @param argumentFactory
	 */
	protected abstract void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory);

	/**
	 * Implementasikan method ini untuk menyesuaikan SOAP message yang diparsing
	 * dari argument method messageBody ke dalam properti yang diperlukan kelas
	 * turunan dari kelas ini
	 * 
	 * @param messageBody
	 */
	protected abstract void configureParse(MessageBody messageBody,String id);

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		if (id == null) {
			id = "device_did_not_send_id";
			// id = "ID:intrnl.unset.id."+((name!=null) ? name : "")
			// +(Calendar.getInstance().getTimeInMillis()+3600*1000)+"."+hashCode();
		}
		return id;
	}

	public boolean isNoMore() {
		return noMore;
	}

	public void setNoMore(boolean noMore) {
		this.noMore = noMore;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri == null ? CWMP_URN : uri;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public MessageBody getMessageBody() {
		return messageBody;
	}

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	public MessageEnvelope getMessageEnvelope() {
		return messageEnvelope;
	}

	/**
	 * method untuk menginstantiasi sebuah {@link SOAPMessage} dari kelas ini
	 */
	public void config(String cwmpId) {
		ArgumentFactory factory = ArgumentFactory.getInstance();
		// configureEnvelope(messageEnvelope);
		configureHeader(messageHeader, factory,cwmpId);
		configureBody(messageBody, factory);
	}

	/**
	 * method untuk mengkonfigurasi object ini dari sebuah {@link SOAPMessage}
	 * (setelah parsing)
	 */
	public void configParse() {
		configureParse(messageBody,id);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Message");
		sb.append("{id='").append(id).append('\'');
		sb.append(", uri='").append(uri).append('\'');
		sb.append(", name='").append(name).append('\'');
		sb.append(", noMore=").append(noMore);
		sb.append('}');
		return sb.toString();
	}
}
