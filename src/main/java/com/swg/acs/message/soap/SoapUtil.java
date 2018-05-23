/**
 * 
 */
package com.swg.acs.message.soap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;

/**
 * @author satriaprayoga
 * 
 */
public final class SoapUtil {

	public static String SOAP_ENV_PREFIX = "SOAP-ENV";

	public static String SOAP_ENV_URN = "http://schemas.xmlsoap.org/soap/envelope/";

	public static String SOAP_ENC_PREFIX = "SOAP-ENC";

	public static String SOAP_ENC_URN = "http://schemas.xmlsoap.org/soap/encoding/";

	public static String XSD_PREFIX = "xsd";

	public static String XSD_URN = "http://www.w3.org/2001/XMLSchema";

	public static String XSI_PREFIX = "xsi";

	public static String XSI_URN = "http://www.w3.org/2001/XMLSchema-instance";

	public static String CWMP_PREFIX = "cwmp";

	public static String CWMP_URN = "urn:dslforum-org:cwmp-1-2";

	private static SoapUtil instance;

	static MessageFactory messageFactory;

	static SOAPFactory soapFactory;

	private SoapUtil() throws SOAPException {
		messageFactory = MessageFactory.newInstance();
		soapFactory = SOAPFactory.newInstance();
	}

	public final static SoapUtil getInstance() {
		try {
			if (instance == null)
				instance = new SoapUtil();
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return instance;

	}

	/**
	 * Get {@link SOAPBodyElement} of a {@link SOAPMessage} object. This method
	 * is use to get the {@code <SOAP-ENV:body/>} in a SOAP Message
	 * 
	 * @param message
	 * @return
	 * @throws SOAPException
	 */
	public SOAPBodyElement getRequest(SOAPMessage message) throws SOAPException {
		SOAPBodyElement bodyElement = null;
		@SuppressWarnings("unchecked")
		Iterator<Node> iterator = message.getSOAPBody().getChildElements();
		while (iterator.hasNext()) {
			Node element = iterator.next();
			if (element.getNodeType() == Node.ELEMENT_NODE) {
				bodyElement = (SOAPBodyElement) element;
			}
		}
		return bodyElement;
	}

	/**
	 * Get {@link SOAPBodyElement} main request name. This method is use to get
	 * the method name of a {@code <SOAP-ENV:body/>} in a SOAP Message
	 * 
	 * @param message
	 * @return
	 * @throws SOAPException
	 */
	public String getRequestName(SOAPMessage message) throws SOAPException {
		if (message.getSOAPBody().hasFault())
			return "Fault";
		return getRequest(message).getLocalName();
	}

	/**
	 * Get a {@link SOAPElement} with a provided name inside another
	 * {@link SOAPElement}
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @throws SOAPException
	 */
	public SOAPElement getRequestChildElement(SOAPElement request, String name)
			throws SOAPException {
		SOAPElement element = null;
		@SuppressWarnings("unchecked")
		Iterator<Node> iterator = request.getChildElements();
		while (iterator.hasNext()) {
			Node e = iterator.next();
			String elementName = e.getLocalName();
			if (elementName != null && elementName.equals(name)) {
				element = (SOAPElement) e;
			}
		}
		return element;
	}

	/**
	 * Get the Iterator object of {@link SOAPElement} child elements
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @throws SOAPException
	 */
	@SuppressWarnings("unchecked")
	public Iterator<Node> getRequestChildElements(SOAPElement request,
			String name) throws SOAPException {
		return request.getChildElements(soapFactory.createName(name));
	}

	/**
	 * Get a {@link SOAPElement} with a fully provided name, prefix, and URI
	 * inside another {@link SOAPElement}
	 * 
	 * @param request
	 * @param name
	 * @param prefix
	 * @param uri
	 * @return
	 * @throws SOAPException
	 */
	public Node getRequestChildElement(SOAPElement request, String name,
			String prefix, String uri) throws SOAPException {
		return (Node) request.getChildElements(
				soapFactory.createName(name, prefix, uri)).next();
	}

	public Node getRequestChildElement(SOAPElement request, Name name)
			throws SOAPException {
		return (Node) request.getChildElements(name).next();
	}

	/**
	 * Get a value of {@link SOAPElement} inside another {@link SOAPElement}
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @throws SOAPException
	 */
	public String getRequestElementValue(SOAPElement request, String name)
			throws SOAPException {
		return getRequestChildElement(request, name).getValue();
	}

	public String getRequestElementValue(SOAPElement request, String name,
			String reference) throws SOAPException {
		String v = getRequestChildElement(request, name).getValue();
		return (v != null) ? v : reference;
	}

	public String getRequestElementValue(SOAPElement request, Name name)
			throws SOAPException {
		return getRequestChildElement(request, name).getValue();
	}

	public int getArrayCount(SOAPElement element) throws SOAPException {
		Name nameArray = soapFactory.createName("arrayType", "soap-enc",
				"http://schemas.xmlsoap.org/soap/encoding/");
		String attr = element.getAttributeValue(nameArray);
		if (attr == null) {
			return 0;
		}
		attr = attr.replaceAll(" ", "");
		int i = attr.indexOf('[');
		String c = attr.substring(i + 1, attr.length() - 1);

		return Integer.parseInt(c);
	}

	/**
	 * Get a CWMP Specific request element ("ParameterList")
	 * 
	 * @param body
	 * @return
	 * @throws SOAPException
	 */
	public Map<String, String> getParameterList(SOAPElement body)
			throws SOAPException {
		return getParameterList(body, "ParameterValueStruct", "Value");
	}

	protected Map<String, String> getParameterList(SOAPElement body,
			String structName, String valueName) throws SOAPException {
		@SuppressWarnings("unchecked")
		Iterator<Node> elements = getRequestChildElement(body, "ParameterList")
				.getChildElements(soapFactory.createName(structName));
		Name nameKey = soapFactory.createName("Name");
		Name nameValue = soapFactory.createName(valueName);
		Map<String, String> map = new HashMap<String, String>();
		while (elements.hasNext()) {
			Node param = elements.next();
			if (param instanceof SOAPElement) {
				SOAPElement soapElement = (SOAPElement) param;
				String key = getRequestElementValue(soapElement, nameKey);
				String value = getRequestElementValue(soapElement, nameValue);
				if (value == null)
					value = "";
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * Get {@link SOAPHeaderElement} of SOAP Message inside its
	 * {@link SOAPHeader} part
	 * 
	 * @param header
	 * @param name
	 * @param prefix
	 * @param uri
	 * @return
	 * @throws SOAPException
	 */
	public SOAPHeaderElement getHeaderElement(SOAPHeader header, String name,
			String prefix, String uri) throws SOAPException {
		@SuppressWarnings("unchecked")
		Iterator<SOAPElement> iterator = header.getChildElements(soapFactory
				.createName(name, prefix, uri));
		if (iterator == null || !iterator.hasNext())
			return null;
		return (SOAPHeaderElement) iterator.next();
	}

	public String getHeaderElementValue(SOAPHeader header, String name,
			String prefix, String uri) throws SOAPException {
		SOAPHeaderElement element = getHeaderElement(header, name, prefix, uri);
		if (element == null)
			return null;
		return getHeaderElement(header, name, prefix, uri).getValue();
	}
}
