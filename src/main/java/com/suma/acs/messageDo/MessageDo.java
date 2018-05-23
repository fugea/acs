/***********************************************************************
 * $ com.topvision.acsserver.message.Msg,v1.0 2012-8-22 14:27:22 $
 *
 * @author: jay
 *
 * (c)Copyright 2011 Topvision All rights reserved.
 ***********************************************************************/
package com.suma.acs.messageDo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.swg.acs.Message;

/**
 * @author jay
 * @created @2012-8-22-14:27:22
 */
public interface MessageDo {
	
    Message process(Message request);

    void setSession(HttpSession session);
    
    Message getResult();
    
    void setRequest(HttpServletRequest request) ;
}