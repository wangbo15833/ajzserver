// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyyqglCreatAction.java

package com.zagerbone.syyqgl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;

// Referenced classes of package com.zagerbone.syyqgl:
//			Syyqgl, SyyqglDAOFactory, SyyqglDAO

public class SyyqglCreatAction extends BaseActionSupport
{

	public SyyqglCreatAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		SyyqglDAO syyqgldao = SyyqglDAOFactory.getDAO();
		String s = "";
		try
		{
			log.info("hhhhhhhhhhhhhhhhhhhhhhhhh");
			Syyqgl syyqgl = (Syyqgl)processEvent(event);
			String s1 = syyqgldao.createSyyqgl(syyqgl);
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
		Syyqgl syyqgl = new Syyqgl();
		HashMap hashmap = event.getBody();
		syyqgl.setProp("tp_testtooltypenum", (String)hashmap.get("tp_testtooltypenum"));
		syyqgl.setProp("tp_testtoolnum", (String)hashmap.get("tp_testtoolnum"));
		log.info(hashmap);
		if (syyqgl.getProp("tp_testtoolnum") == null || syyqgl.getProp("tp_testtoolnum").equals(""))
			throw new BussinessProcessException("试验仪编号不能为空!");
		else
			return syyqgl;
	}
}
