// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RoleDeleteAction.java

package com.zagerbone.role;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// Referenced classes of package com.zagerbone.role:
//			RoleDAOFactory, RoleDAO

public class RoleDeleteAction extends BaseActionSupport
{

	public RoleDeleteAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		RoleDAO roledao = RoleDAOFactory.getDAO();
		try
		{
			String s = (String)processEvent(event);
			String s1 = roledao.deleteRole(s,event.getRequest());
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
