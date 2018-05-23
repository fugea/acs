package com.swg.acs.message.struct;

public class InformTime {
	
	private String sn ;
	private long lastTime ;
	private long curTime;
	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn;
	}
	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * @return the lastTime
	 */
	public long getLastTime() {
		return lastTime;
	}
	/**
	 * @param lastTime the lastTime to set
	 */
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	/**
	 * @return the curTime
	 */
	public long getCurTime() {
		return curTime;
	}
	/**
	 * @param curTime the curTime to set
	 */
	public void setCurTime(long curTime) {
		this.curTime = curTime;
	}

}
