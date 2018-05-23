/**
 * 
 */
package com.swg.acs.message.cwmp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.swg.acs.Argument;
import com.swg.acs.MessageArgument;

/**
 * @author satriaprayoga
 *
 */
class CwmpArgument implements MessageArgument {
	
	private static final long serialVersionUID = 3035340938743945695L;
	private String uri;
	private String prefix;
	private String name;
	private String value;
	
	private List<MessageArgument> arguments;
	private Attribute attribute;
	
	public CwmpArgument() {
		this(null,null,null);
	}
	
	public CwmpArgument(String name) {
		this(name,null,null);
	}
	
	public CwmpArgument(String name,String prefix,String uri) {
		this.name=name;
		this.prefix=prefix;
		this.uri=uri;
		this.arguments=new ArrayList<MessageArgument>();
	}
	
	@Override
	public MessageArgument addMessageArgument(MessageArgument argument) {
		if(!arguments.contains(argument))
			arguments.add(argument);
		return argument;
	}

	
	@Override
	public MessageArgument getChild(String name) {
		MessageArgument child=null;
		for(MessageArgument arg:arguments){
			if(arg.getName().equals(name)){
				child=arg;
			}
		}
		return child;
	}

	@Override
	public void setAttribute(String name, String value) {
		attribute=new Attribute(name, value);
	}

	@Override
	public Iterator<MessageArgument> childIterator() {
		return arguments.iterator();
	}

	@Override
	public boolean hasChild() {
		return arguments.size()==0;
	}

	@Override
	public Argument setValue(String value) {
		this.value=value;
		return this;
	}

	@Override
	public String getValue() {
		return value;
	}
	
	@Override
	public String getAttribute(String name) {
		return attribute.getName().equals(name)?attribute.getValue():null;
	}
	
	@Override
	public int childSize() {
		return arguments.size();
	}
	
	@Override
	public String getName() {
		return name;
	}

    public void setUri(String uri) {
        if (this.uri == null) {
            this.uri = uri;
        }
    }

    public String getUri() {
		return uri;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	@Override
	public String getAttributeName() {
		if(attribute!=null)
			return attribute.getName();
		else 
			return null;
	}

}
