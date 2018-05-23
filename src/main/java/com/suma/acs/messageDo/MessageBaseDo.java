package com.suma.acs.messageDo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class MessageBaseDo implements MessageDo {
	protected HttpServletRequest request;

	private HttpSession session;

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpSession getSession() {
		return session;
	}
	
}
