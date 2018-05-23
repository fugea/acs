/**
 * 
 */
package com.swg.acs;

import java.io.Serializable;
import java.util.Map;

/**
 * Interface yang berhubungan dengan element SOAP-ENV
 * 
 * @author satriaprayoga
 * 
 */
public interface MessageEnvelope extends Serializable {

    /**
     * Masukin namespace ke dalam SOAP-ENV
     * 
     * @param name
     * @param value
     */
    void addNamespace(String name, String value);

    /**
     * Return Map untuk seluruh namespace
     * 
     * @return
     */
    Map<String, String> getNamespaceMap();

    /**
     * Return namespace dengan nama tertentu
     * 
     * @param name
     * @return
     */
    String getNamespaceValue(String name);
}
