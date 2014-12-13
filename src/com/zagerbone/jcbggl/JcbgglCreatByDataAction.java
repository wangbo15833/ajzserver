// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JcbgglCreatByDataAction.java

package com.zagerbone.jcbggl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.DBTrans;
import java.util.HashMap;
import org.apache.log4j.Logger;

// Referenced classes of package com.zagerbone.jcbggl:
//			Jcbggl, JcbgglDAOFactory, JcbgglDAO

public class JcbgglCreatByDataAction extends BaseActionSupport
{

	public JcbgglCreatByDataAction()
	{
	}

	public EventResponse perform(DBTrans dbtrans, Event event)
	{
		EventResponse eventresponse = new EventResponse();
		JcbgglDAO jcbggldao = JcbgglDAOFactory.getDAO();
		String s = "";
		try
		{
			log.info("hhhhhhhhhhhhhhhhhhhhhhhhh");
			Jcbggl jcbggl = (Jcbggl)processEvent(event);
			jcbggldao.createJcbggl_byData(dbtrans, jcbggl);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
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
		Jcbggl jcbggl = new Jcbggl();
		HashMap hashmap = event.getBody();
		jcbggl.setProp("tp_carname", (String)hashmap.get("tp_carname"));
		jcbggl.setProp("tp_testnum", (String)hashmap.get("tp_testnum"));
		jcbggl.setProp("tp_cartype", (String)hashmap.get("tp_cartype"));
		jcbggl.setProp("tp_testtooltypenum", (String)hashmap.get("tp_testtooltypenum"));
		jcbggl.setProp("tp_carnum", (String)hashmap.get("tp_carnum"));
		jcbggl.setProp("tp_testtoolnum", (String)hashmap.get("tp_testtoolnum"));
		jcbggl.setProp("TCjk_startTime", (String)hashmap.get("TCjk_startTime"));
		jcbggl.setProp("TCjk_endTime", (String)hashmap.get("TCjk_endTime"));
		jcbggl.setProp("tp_teststarttime", (String)hashmap.get("tp_teststarttime"));
		jcbggl.setProp("tp_testendtime", (String)hashmap.get("tp_testendtime"));
		jcbggl.setProp("tp_testtotaltime", (String)hashmap.get("tp_testtotaltime"));
		jcbggl.setProp("tp_testmeb", (String)hashmap.get("tp_testmeb"));
		jcbggl.setProp("tp_testmebphone", (String)hashmap.get("tp_testmebphone"));
		log.info(hashmap);
		return jcbggl;
	}
}
