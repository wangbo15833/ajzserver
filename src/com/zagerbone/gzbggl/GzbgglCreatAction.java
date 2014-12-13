// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbgglCreatAction.java

package com.zagerbone.gzbggl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;

// Referenced classes of package com.zagerbone.gzbggl:
//			Gzbggl, GzbgglDAOFactory, GzbgglDAO

public class GzbgglCreatAction extends BaseActionSupport
{

	public GzbgglCreatAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		GzbgglDAO gzbggldao = GzbgglDAOFactory.getDAO();
		String s = "";
		try
		{
			log.info("hhhhhhhhhhhhhhhhhhhhhhhhh");
			Gzbggl gzbggl = (Gzbggl)processEvent(event);
			String s1 = gzbggldao.createGzbggl(gzbggl);
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
		Gzbggl gzbggl = new Gzbggl();
		HashMap hashmap = event.getBody();
		gzbggl.setProp("tp_carname", (String)hashmap.get("tp_carname"));
		gzbggl.setProp("tp_carnum", (String)hashmap.get("tp_carnum"));
		gzbggl.setProp("tp_testnum", (String)hashmap.get("tp_testnum"));
		gzbggl.setProp("TCjk_startTime", (String)hashmap.get("TCjk_startTime"));
		gzbggl.setProp("tp_faultcode", (String)hashmap.get("tp_faultcode"));
		gzbggl.setProp("tp_fault_stoptime", (String)hashmap.get("tp_fault_stoptime"));
		gzbggl.setProp("tp_fault_recovertime", (String)hashmap.get("tp_fault_recovertime"));
		gzbggl.setProp("tp_workhour", (String)hashmap.get("tp_workhour"));
		gzbggl.setProp("tp_harvesthour", (String)hashmap.get("tp_harvesthour"));
		gzbggl.setProp("tp_faulttype", (String)hashmap.get("tp_faulttype"));
		gzbggl.setProp("tp_reportoperator", (String)hashmap.get("tp_reportoperator"));
		log.info(hashmap);
		if (gzbggl.getProp("tp_testnum") == null || gzbggl.getProp("tp_testnum").equals(""))
			throw new BussinessProcessException("²âÊÔ±àºÅ²»ÄÜÎª¿Õ£¡");
		else
			return gzbggl;
	}
}
