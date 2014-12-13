// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyyqglModifyAction.java

package com.zagerbone.syyqgl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import java.util.HashMap;
import org.apache.log4j.Logger;

// Referenced classes of package com.zagerbone.syyqgl:
//			Syyqgl, SyyqglDAOFactory, SyyqglDAO

public class SyyqglModifyAction extends BaseActionSupport
{

	public SyyqglModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		SyyqglDAO syyqgldao = SyyqglDAOFactory.getDAO();
		try
		{
			Syyqgl syyqgl = (Syyqgl)processEvent(event);
			String s = syyqgldao.modifySyyqgl(syyqgl);
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
		Syyqgl syyqgl = new Syyqgl();
		HashMap hashmap = event.getBody();
		log.info((new StringBuilder()).append("hashBody==================").append(hashmap).toString());
		log.info((new StringBuilder()).append("hashBody.get(tp_testtoolnum) ==").append(hashmap.get("tp_testtoolnum")).toString());
		syyqgl.setSyyqglid((String)hashmap.get("id"));
		syyqgl.setProp("tp_testtoolnum", Tools.nvl((String)hashmap.get("tp_testtoolnum")));
		syyqgl.setProp("tp_testtooltypenum", Tools.nvl((String)hashmap.get("tp_testtooltypenum")));
		syyqgl.setProp("tp_testnum", Tools.nvl((String)hashmap.get("tp_testnum")));
		syyqgl.setProp("tp_teststarttime", Tools.nvl((String)hashmap.get("tp_teststarttime")));
		syyqgl.setProp("tp_testtotaltime", Tools.nvl((String)hashmap.get("tp_testtotaltime")));
		syyqgl.setProp("tp_carnum", Tools.nvl((String)hashmap.get("tp_carnum")));
		syyqgl.setProp("tp_carname", Tools.nvl((String)hashmap.get("tp_carname")));
		syyqgl.setProp("tp_carproduction", Tools.nvl((String)hashmap.get("tp_carproduction")));
		String s = Tools.nvl((String)hashmap.get("CMonitorStatus"));
		if (s.equals("取消"))
			s = "0";
		syyqgl.setProp("CMonitorStatus", s);
		log.info((new StringBuilder()).append("=============================").append(syyqgl.getProp("tp_testtoolnum")).toString());
		if (syyqgl.getProp("tp_testtoolnum").equals(""))
			throw new BussinessProcessException("仪器编号不能为空!");
		else
			return syyqgl;
	}
}
