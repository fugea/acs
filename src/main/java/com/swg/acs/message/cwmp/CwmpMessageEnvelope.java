/**
 * 
 */
package com.swg.acs.message.cwmp;

import java.util.HashMap;
import java.util.Map;

import com.swg.acs.MessageEnvelope;

/**
 * @author satriaprayoga
 *
 */
public class CwmpMessageEnvelope implements MessageEnvelope {
	private static final long serialVersionUID = 2897011685276570355L;

	private Map<String, String> namespaceMap;

	public CwmpMessageEnvelope() {
		namespaceMap = new HashMap<String, String>();
	}

	@Override
	public void addNamespace(String name, String value) {
		namespaceMap.put(name, value);
	}

	@Override
	public Map<String, String> getNamespaceMap() {
		return namespaceMap;
	}

	@Override
	public String getNamespaceValue(String name) {
		return namespaceMap.get(name);
	}

}
