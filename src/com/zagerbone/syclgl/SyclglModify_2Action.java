// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyclglModify_2Action.java

package com.zagerbone.syclgl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import java.util.HashMap;
import org.apache.log4j.Logger;

// Referenced classes of package com.zagerbone.syclgl:
//			Syclgl, SyclglDAOFactory, SyclglDAO

public class SyclglModify_2Action extends BaseActionSupport
{

	public SyclglModify_2Action()
	{
	}

	public EventResponse perform(Event event)
	{
		log.info("SyclglModifyAction====================================================================================");
		EventResponse eventresponse = new EventResponse();
		SyclglDAO syclgldao = SyclglDAOFactory.getDAO();
		try
		{
			Syclgl syclgl = (Syclgl)processEvent(event);
			String s = syclgldao.modifySyclgl_cancal(syclgl);
			eventresponse.setSuccessFlag(true);
			HashMap hashmap = new HashMap();
			hashmap.put("ModifyMode", syclgl.getProp("ModifyMode"));
			hashmap.put("CMonitorStatus", syclgl.getProp("CMonitorStatus"));
			hashmap.put("id", s);
			eventresponse.setBody(hashmap);
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
		Syclgl syclgl = new Syclgl();
		HashMap hashmap = event.getBody();
		syclgl.setSyclglid((String)hashmap.get("id"));
		syclgl.setProp("dataOperman", event.getRealname());
		syclgl.setProp("tp_testnum", (String)hashmap.get("tp_testnum"));
		syclgl.setProp("tp_carname", (String)hashmap.get("tp_carname"));
		syclgl.setProp("tp_cartype", (String)hashmap.get("tp_cartype"));
		syclgl.setProp("tp_testtoolnum", (String)hashmap.get("tp_testtoolnum"));
		String s = Tools.nvl((String)hashmap.get("ModifyMode"));
		syclgl.setProp("ModifyMode", s);
		String s1 = Tools.nvl((String)hashmap.get("CMonitorStatus"));
		if (s1.equals("È¡Ïû"))
			s1 = "0";
		syclgl.setProp("CMonitorStatus", s1);
		return syclgl;
	}
}
