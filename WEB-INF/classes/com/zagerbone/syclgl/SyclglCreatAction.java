// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyclglCreatAction.java

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

public class SyclglCreatAction extends BaseActionSupport
{

	public SyclglCreatAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		SyclglDAO syclgldao = SyclglDAOFactory.getDAO();
		String s = "";
		try
		{
			log.info("hhhhhhhhhhhhhhhhhhhhhhhhh----------------hhhh");
			Syclgl syclgl = (Syclgl)processEvent(event);
			log.info("hhhhhhhhhhhhhhhhhhhhhhhhh-----22-----------hhhh");
			String s1 = syclgldao.createSyclgl(syclgl);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s1);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
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
		Syclgl syclgl = new Syclgl();
		HashMap hashmap = event.getBody();
		syclgl.setProp("tp_carname", Tools.nvl((String)hashmap.get("tp_carname")));
		syclgl.setProp("tp_carnum", Tools.nvl((String)hashmap.get("tp_carnum")));
		syclgl.setProp("tp_cartype", (String)hashmap.get("tp_cartype"));
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
		syclgl.setProp("Tharvesttime", Tools.nvl0((String)hashmap.get("Tharvesttime")));
		log.info(hashmap);
		if (syclgl.getProp("tp_carname").equals(""))
			throw new BussinessProcessException("车辆名称不能为空");
		if (syclgl.getProp("tp_testtoolnum").equals(""))
			throw new BussinessProcessException("试验仪编号不能为空!");
		if (syclgl.getProp("tp_testtotaltime").equals(""))
			throw new BussinessProcessException("试验总时长不能为空!");
		else
			return syclgl;
	}
}
