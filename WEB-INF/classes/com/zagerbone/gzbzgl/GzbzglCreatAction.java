// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbzglCreatAction.java

package com.zagerbone.gzbzgl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;

// Referenced classes of package com.zagerbone.gzbzgl:
//			Gzbzgl, GzbzglDAOFactory, GzbzglDAO

public class GzbzglCreatAction extends BaseActionSupport
{

	public GzbzglCreatAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		GzbzglDAO gzbzgldao = GzbzglDAOFactory.getDAO();
		String s = "";
		try
		{
			log.info("hhhhhhhhhhhhhhhhhhhhhhhhh");
			Gzbzgl gzbzgl = (Gzbzgl)processEvent(event);
			String s1 = gzbzgldao.createGzbzgl(gzbzgl);
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
		Gzbzgl gzbzgl = new Gzbzgl();
		HashMap hashmap = event.getBody();
		gzbzgl.setProp("tp_faultcode", (String)hashmap.get("tp_faultcode"));
		gzbzgl.setProp("tp_faultname", (String)hashmap.get("tp_faultname"));
		gzbzgl.setProp("qwbs", (String)hashmap.get("qwbs"));
		gzbzgl.setProp("qwtime", (String)hashmap.get("qwtime"));
		gzbzgl.setProp("ybbs", (String)hashmap.get("ybbs"));
		gzbzgl.setProp("ybtime", (String)hashmap.get("ybtime"));
		gzbzgl.setProp("zdbs", (String)hashmap.get("zdbs"));
		gzbzgl.setProp("zdtime", (String)hashmap.get("zdtime"));
		gzbzgl.setProp("add1", (String)hashmap.get("add1"));
		gzbzgl.setProp("add2", (String)hashmap.get("add2"));
		gzbzgl.setProp("add3", (String)hashmap.get("add3"));
		gzbzgl.setProp("add4", (String)hashmap.get("add4"));
		gzbzgl.setProp("add5", (String)hashmap.get("add5"));
		log.info(hashmap);
		if (gzbzgl.getProp("tp_faultcode") == null || gzbzgl.getProp("tp_faultcode").equals(""))
			throw new BussinessProcessException("故障代码不能为空!");
		if (gzbzgl.getProp("tp_faultname") == null || gzbzgl.getProp("tp_faultname").equals(""))
			throw new BussinessProcessException("故障名称不能为空!");
		else
			return gzbzgl;
	}
}
