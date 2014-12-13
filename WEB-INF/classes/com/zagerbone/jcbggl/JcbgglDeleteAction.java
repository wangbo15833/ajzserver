// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JcbgglDeleteAction.java

package com.zagerbone.jcbggl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.jcbggl:
//			JcbgglDAOFactory, JcbgglDAO

public class JcbgglDeleteAction extends BaseActionSupport
{

	public JcbgglDeleteAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		JcbgglDAO jcbggldao = JcbgglDAOFactory.getDAO();
		try
		{
			String s = (String)processEvent(event);
			String s1 = jcbggldao.deleteJcbggl(s);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s1);
		}
		catch (Exception exception)
		{
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode("601");
			eventresponse.setErrorMessage(exception.getMessage().replace('\n', ' '));
			return eventresponse;
		}
		return eventresponse;
	}

	public Object processEvent(Event event)
		throws BussinessProcessException
	{
		HashMap hashmap = event.getBody();
		String s = (String)hashmap.get("id");
		if (s == null || s.equals(""))
			throw new BussinessProcessException("");
		else
			return s;
	}
}
