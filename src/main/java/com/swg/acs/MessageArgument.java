/**
 * 
 */
package com.swg.acs;

import java.util.Iterator;


/**
 * Interface untuk element SOAP-XML, dengan fitur tambahan untuk mengakses child element dari element yang diwakili interface ini
 * 
 * @see Argument
 * @author satriaprayoga
 *
 */
public interface MessageArgument extends Argument{
	/**
	 * Memasukan child element ke dalam interface ini
	 * @param argument
	 * @return
	 */
	MessageArgument addMessageArgument(MessageArgument argument);
	/**
	 * Ambil child element dengan nama tertentu
	 * @param name
	 * @return
	 */
	MessageArgument getChild(String name);
	/**
	 * Iterator 
	 * @return
	 */
	Iterator<MessageArgument> childIterator();
	/**
	 * apakah instance dari kelas ini punya child element atau tidak
	 * @return
	 */
	boolean hasChild();
	/**
	 * jumlah child element dari instance kelas ini
	 * @return
	 */
	int childSize();
}
