// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportUserBasicAction.java

package com.zagerbone.user;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;

// Referenced classes of package com.zagerbone.user:
//			UserDAOFactory, UserDAO

public class ExportUserBasicAction extends BaseActionSupport
{

	public ExportUserBasicAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserDAO userdao = UserDAOFactory.getDAO();
		try
		{
			String s = userdao.createUserBasicExcel();
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
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
		return "ok";
	}
}
