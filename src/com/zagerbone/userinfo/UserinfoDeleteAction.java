package com.zagerbone.userinfo;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;


public class UserinfoDeleteAction extends BaseActionSupport
{

	public UserinfoDeleteAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserinfoDAO userinfodao = UserinfoDAOFactory.getDAO();
		try
		{
			String s = (String)processEvent(event);
			System.out.println("===  step in UserinfoDeleteAction === id is :"+s);
			String s1 = userinfodao.deleteUserinfo(s,event.getRequest());
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
