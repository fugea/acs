/**
 * 
 */
package com.swg.acs.message.datamodel;

/**
 * @author satriaprayoga
 *
 */
public class DataModel {

	private DataModelItem head;
	private DataModelItem tail;
	
	public DataModel() {
	}
	
	public void append(Object value){
		DataModelItem item=new DataModelItem(value);
		if(head==null){
			head=tail=item;
		}else{
			tail.next=item;
			tail=item;
		}
	}
	
	public boolean isEmpty(){
		return head==null;
	}
	
	public Object getValue(){
		return tail.value;
	}
	
	public String getFullDesc(){
		if(isEmpty()){
			throw new DataModelException("Empty Data Model");
		}
		StringBuilder builder=new StringBuilder();
		DataModelItem n=head;
		while(n!=null){
			builder.append(n.value);
			n=n.next;
		}
		return builder.toString();
	}
	
	static final class DataModelItem{
		protected DataModelItem next;
		protected Object value;
		
		public DataModelItem() {
			this(null,null);
		}
		
		public DataModelItem(DataModelItem next,Object value){
			this.next=next;
			this.value=value;
		}
		
		public DataModelItem(Object value){
			this(null,value);
		}
	}
}
