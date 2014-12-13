// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyclglModifyAction.java

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

public class SyclglModifyAction extends BaseActionSupport
{

	public SyclglModifyAction()
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
			String s = syclgldao.modifySyclgl(syclgl);
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
		syclgl.setProp("tp_carname", (String)hashmap.get("tp_carname"));
		syclgl.setProp("tp_cartype", (String)hashmap.get("tp_cartype"));
		syclgl.setProp("tp_carnum", (String)hashmap.get("tp_carnum"));
		syclgl.setProp("tp_carproduction", (String)hashmap.get("tp_carproduction"));
		syclgl.setProp("tp_carouttime", (String)hashmap.get("tp_carouttime"));
		syclgl.setProp("tp_carcheckdate", (String)hashmap.get("tp_carcheckdate"));
		syclgl.setProp("tp_carcheckmeb", (String)hashmap.get("tp_carcheckmeb"));
		syclgl.setProp("tp_carcheckmebphone", (String)hashmap.get("tp_carcheckmebphone"));
		syclgl.setProp("tp_testnum", (String)hashmap.get("tp_testnum"));
		syclgl.setProp("tp_testtooltypenum", (String)hashmap.get("tp_testtooltypenum"));
		syclgl.setProp("tp_testtoolnum", (String)hashmap.get("tp_testtoolnum"));
		syclgl.setProp("tp_teststarttime", (String)hashmap.get("tp_teststarttime"));
		syclgl.setProp("tp_testendtime", (String)hashmap.get("tp_testendtime"));
		syclgl.setProp("tp_testtotaltime", (String)hashmap.get("tp_testtotaltime"));
		syclgl.setProp("tp_testmeb", (String)hashmap.get("tp_testmeb"));
		syclgl.setProp("tp_testmebphone", (String)hashmap.get("tp_testmebphone"));
		String s = Tools.nvl((String)hashmap.get("ModifyMode"));
		syclgl.setProp("ModifyMode", s);
		String s1 = Tools.nvl((String)hashmap.get("CMonitorStatus"));
		if (s1.equals("È¡Ïû"))
			s1 = "0";
		syclgl.setProp("CMonitorStatus", s1);
		return syclgl;
	}
}
