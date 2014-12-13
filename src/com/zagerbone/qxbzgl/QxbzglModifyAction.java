// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QxbzglModifyAction.java

package com.zagerbone.qxbzgl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.qxbzgl:
//			Qxbzgl, QxbzglDAOFactory, QxbzglDAO

public class QxbzglModifyAction extends BaseActionSupport
{

	public QxbzglModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		QxbzglDAO qxbzgldao = QxbzglDAOFactory.getDAO();
		try
		{
			Qxbzgl qxbzgl = (Qxbzgl)processEvent(event);
			String s = qxbzgldao.modifyQxbzgl(qxbzgl);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
		}
		catch (Exception exception)
		{
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode(event.getFunctionID());
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
		qxbzgl.setQxbzglid((String)hashmap.get("id"));
		qxbzgl.setProp("parameteName", (String)hashmap.get("parameteName"));
		qxbzgl.setProp("parameteChName", (String)hashmap.get("parameteChName"));
		qxbzgl.setProp("configYPerValue", (String)hashmap.get("configYPerValue"));
		qxbzgl.setProp("configYMinValue", (String)hashmap.get("configYMinValue"));
		qxbzgl.setProp("configYValueCount", (String)hashmap.get("configYValueCount"));
		if (qxbzgl.getProp("parameteName") == null || qxbzgl.getProp("parameteName").equals(""))
			throw new BussinessProcessException("参数名称不能为空!");
		if (qxbzgl.getProp("parameteChName") == null || qxbzgl.getProp("parameteChName").equals(""))
			throw new BussinessProcessException("参数中文名称不能为空!");
		else
			return qxbzgl;
	}
}
