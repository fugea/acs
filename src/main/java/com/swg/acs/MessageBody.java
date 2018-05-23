/**
 * 
 */
package com.swg.acs;

/**
 * Interface yang merepresentasikan suatu SOAP-BODY di dalam sebuah SOAP Message Object
 * @author satriaprayoga
 *
 */
public interface MessageBody extends MessageArgument{

	/**
	 * test apakah child element di dalam interface ini adalah sebuah fault
	 * @return
	 */
	boolean hasFault();
}
