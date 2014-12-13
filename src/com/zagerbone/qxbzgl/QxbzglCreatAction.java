// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QxbzglCreatAction.java

package com.zagerbone.qxbzgl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;

// Referenced classes of package com.zagerbone.qxbzgl:
//			Qxbzgl, QxbzglDAOFactory, QxbzglDAO

public class QxbzglCreatAction extends BaseActionSupport
{

	public QxbzglCreatAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		QxbzglDAO qxbzgldao = QxbzglDAOFactory.getDAO();
		String s = "";
		try
		{
			log.info("hhhhhhhhhhhhhhhhhhhhhhhhh");
			Qxbzgl qxbzgl = (Qxbzgl)processEvent(event);
			String s1 = qxbzgldao.createQxbzgl(qxbzgl);
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
		Qxbzgl qxbzgl = new Qxbzgl();
		HashMap hashmap = event.getBody();
		qxbzgl.setProp("parameteName", (String)hashmap.get("parameteName"));
		qxbzgl.setProp("parameteChName", (String)hashmap.get("parameteChName"));
		qxbzgl.setProp("configYPerValue", (String)hashmap.get("configYPerValue"));
		qxbzgl.setProp("configYMinValue", (String)hashmap.get("configYMinValue"));
		qxbzgl.setProp("configYValueCount", (String)hashmap.get("configYValueCount"));
		log.info(hashmap);
		if (qxbzgl.getProp("parameteName") == null || qxbzgl.getProp("parameteName").equals(""))
			throw new BussinessProcessException("");
		if (qxbzgl.getProp("configYPerValue") == null || qxbzgl.getProp("configYPerValue").equals(""))
			throw new BussinessProcessException("");
		if (qxbzgl.getProp("configYMinValue") == null || qxbzgl.getProp("configYMinValue").equals(""))
			throw new BussinessProcessException("");
		if (qxbzgl.getProp("configYValueCount") == null || qxbzgl.getProp("configYValueCount").equals(""))
			throw new BussinessProcessException("");
		else
			return qxbzgl;
	}
}
