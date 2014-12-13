// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JcbgglModifyAction.java

package com.zagerbone.jcbggl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.jcbggl:
//			Jcbggl, JcbgglDAOFactory, JcbgglDAO

public class JcbgglModifyAction extends BaseActionSupport
{

	public JcbgglModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		JcbgglDAO jcbggldao = JcbgglDAOFactory.getDAO();
		try
		{
			Jcbggl jcbggl = (Jcbggl)processEvent(event);
			String s = jcbggldao.modifyJcbggl(jcbggl);
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
		Jcbggl jcbggl = new Jcbggl();
		HashMap hashmap = event.getBody();
		jcbggl.setJcbgglid((String)hashmap.get("id"));
		jcbggl.setProp("tp_reportoperator", event.getRealname());
		jcbggl.setProp("tp_carname", (String)hashmap.get("tp_carname"));
		jcbggl.setProp("tp_carnum", (String)hashmap.get("tp_carnum"));
		jcbggl.setProp("tp_testnum", (String)hashmap.get("tp_testnum"));
		jcbggl.setProp("tp_testtoolnum", (String)hashmap.get("tp_testtoolnum"));
		jcbggl.setProp("tp_testtooltypenum", (String)hashmap.get("tp_testtooltypenum"));
		jcbggl.setProp("tp_testtime", (String)hashmap.get("tp_testtime"));
		jcbggl.setProp("tp_faultcode", (String)hashmap.get("tp_faultcode"));
		jcbggl.setProp("tp_fault_stoptime", (String)hashmap.get("tp_fault_stoptime"));
		jcbggl.setProp("tp_fault_recovertime", (String)hashmap.get("tp_fault_recovertime"));
		jcbggl.setProp("tp_workhour", (String)hashmap.get("tp_workhour"));
		jcbggl.setProp("tp_harvesthour", (String)hashmap.get("tp_harvesthour"));
		jcbggl.setProp("tp_faulttype", (String)hashmap.get("tp_faulttype"));
		jcbggl.setProp("tp_reportoperator", (String)hashmap.get("tp_reportoperator"));
		String s = Tools.nvl((String)hashmap.get("CMonitorStatus"));
		if (s.equals("È¡Ïû"))
			s = "0";
		jcbggl.setProp("CMonitorStatus", s);
		return jcbggl;
	}
}
