// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbzglModifyAction.java

package com.zagerbone.gzbzgl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.gzbzgl:
//			Gzbzgl, GzbzglDAOFactory, GzbzglDAO

public class GzbzglModifyAction extends BaseActionSupport
{

	public GzbzglModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		GzbzglDAO gzbzgldao = GzbzglDAOFactory.getDAO();
		try
		{
			Gzbzgl gzbzgl = (Gzbzgl)processEvent(event);
			String s = gzbzgldao.modifyGzbzgl(gzbzgl);
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
		Gzbzgl gzbzgl = new Gzbzgl();
		HashMap hashmap = event.getBody();
		gzbzgl.setGzbzglid((String)hashmap.get("id"));
		gzbzgl.setProp("tp_faultcode", Tools.nvl((String)hashmap.get("tp_faultcode")));
		gzbzgl.setProp("tp_faultname", Tools.nvl((String)hashmap.get("tp_faultname")));
		gzbzgl.setProp("qwbs", 	Tools.nvl((String)hashmap.get("qwbs")));
		gzbzgl.setProp("qwtime",Tools.nvl((String)hashmap.get("qwtime")));
		gzbzgl.setProp("ybbs", 	Tools.nvl((String)hashmap.get("ybbs")));
		gzbzgl.setProp("ybtime",Tools.nvl((String)hashmap.get("ybtime")));
		gzbzgl.setProp("zdbs", 	Tools.nvl((String)hashmap.get("zdbs")));
		gzbzgl.setProp("zdtime",Tools.nvl((String)hashmap.get("zdtime")));
		gzbzgl.setProp("add1",	Tools.nvl((String)hashmap.get("add1")));
		gzbzgl.setProp("add2",	Tools.nvl((String)hashmap.get("add2")));
		gzbzgl.setProp("add3",	Tools.nvl((String)hashmap.get("add3")));
		gzbzgl.setProp("add4",	Tools.nvl((String)hashmap.get("add4")));
		gzbzgl.setProp("add5",	Tools.nvl((String)hashmap.get("add5")));
		if (gzbzgl.getProp("tp_faultcode").equals(""))
			throw new BussinessProcessException("tp_faultcode²»ÄÜÎª¿Õ");
		if (gzbzgl.getProp("tp_faultname").equals(""))
			throw new BussinessProcessException("");
		else
			return gzbzgl;
	}
}
