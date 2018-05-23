/**
 * 
 */
package com.swg.acs.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.swg.acs.MessageArgument;
import com.swg.acs.MessageBody;
import com.swg.acs.message.cwmp.ArgumentFactory;
import com.swg.acs.message.cwmp.CwmpMessage;

/**
 * @author satriaprayoga
 *
 */
public class GetOptionsResponse extends CwmpMessage{
	private static final long serialVersionUID = 1452457475351220634L;
	
	private List<OptionStruct> optionList;
	private String cwmpId;
	public String getCwmpId(){
		return cwmpId;
	}
	
	public GetOptionsResponse() {
		super("GetOptionsResponse");
		optionList=new ArrayList<GetOptionsResponse.OptionStruct>();
	}

	class OptionStruct {
		protected String optionName;
		protected String voucherSN;
		protected int state;
		protected int mode;
		protected String startDate;
		protected String expirationDate;
		protected boolean  transferable;

	}
	
	@Override
	protected void configureBody(MessageBody bodyPart,
			ArgumentFactory argumentFactory) {
		
	}
	
	@Override
	protected void configureParse(MessageBody messageBody,String cwmpId) {
		this.cwmpId = cwmpId;
		MessageArgument optionArg=messageBody.getChild("OptionList");
		Iterator<MessageArgument> iterator=optionArg.childIterator();
		while(iterator.hasNext()){
			MessageArgument arg=iterator.next();
			OptionStruct struct=new OptionStruct();
			struct.optionName=arg.getChild("OptionName").getValue();
			struct.voucherSN=arg.getChild("VoucherSN").getValue();
			struct.state=Integer.parseInt(arg.getChild("State").getValue());
			struct.mode=Integer.parseInt(arg.getChild("Mode").getValue());
			struct.startDate=arg.getChild("StartDate").getValue();
			struct.expirationDate=arg.getChild("ExpirationDate").getValue();
			struct.transferable=Boolean.parseBoolean(arg.getChild("IsTransferable").getValue());
			optionList.add(struct);
		}
	}


	public List<OptionStruct> getOptionList() {
		return optionList;
	}
}
