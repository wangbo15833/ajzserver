// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbgglModifyAction.java

package com.zagerbone.gzbggl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.gzbggl:
//			Gzbggl, GzbgglDAOFactory, GzbgglDAO

public class GzbgglModifyAction extends BaseActionSupport
{

	public GzbgglModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		GzbgglDAO gzbggldao = GzbgglDAOFactory.getDAO();
		try
		{
			Gzbggl gzbggl = (Gzbggl)processEvent(event);
			String s = gzbggldao.modifyGzbggl(gzbggl);
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
		Gzbggl gzbggl = new Gzbggl();
		HashMap hashmap = event.getBody();
		gzbggl.setGzbgglid((String)hashmap.get("id"));
		gzbggl.setProp("tp_reportoperator", event.getRealname());
		gzbggl.setProp("tp_carname", (String)hashmap.get("tp_carname"));
		gzbggl.setProp("tp_carnum", (String)hashmap.get("tp_carnum"));
		gzbggl.setProp("tp_testnum", (String)hashmap.get("tp_testnum"));
		gzbggl.setProp("tp_testtime", (String)hashmap.get("tp_testtime"));
		gzbggl.setProp("tp_faultcode", (String)hashmap.get("tp_faultcode"));
		gzbggl.setProp("tp_faultname", (String)hashmap.get("tp_faultname"));
		gzbggl.setProp("tp_fault_stoptime", (String)hashmap.get("tp_fault_stoptime"));
		gzbggl.setProp("tp_fault_recovertime", (String)hashmap.get("tp_fault_recovertime"));
		gzbggl.setProp("tp_fault_delay", (String)hashmap.get("tp_fault_delay"));
		gzbggl.setProp("tp_workhour", (String)hashmap.get("tp_workhour"));
		gzbggl.setProp("tp_harvesthour", (String)hashmap.get("tp_harvesthour"));
		gzbggl.setProp("tp_faulttype", (String)hashmap.get("tp_faulttype"));
		gzbggl.setProp("tp_reportoperator", (String)hashmap.get("tp_reportoperator"));
		String s = Tools.nvl((String)hashmap.get("CMonitorStatus"));
		if (s.equals("È¡Ïû"))
			s = "0";
		gzbggl.setProp("CMonitorStatus", s);
		return gzbggl;
	}
}
