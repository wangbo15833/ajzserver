// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseActionSupport.java

package com.shenghao.arch.action;

import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.util.SysTrace;
import com.zagerbone.util.DBTrans;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

// Referenced classes of package com.shenghao.arch.action:
//			BaseAction

public class BaseActionSupport
	implements BaseAction
{

	protected ServletContext ctx;
	protected HttpSession session;
	protected String curIp;
	protected static Logger log;

	public BaseActionSupport()
	{
		curIp = "";
	}

	public void init(ServletContext servletcontext, HttpSession httpsession)
	{
		ctx = servletcontext;
		session = httpsession;
		if (httpsession != null)
			curIp = (String)httpsession.getAttribute("remoteaddr");
	}

	public void doStart()
	{
	}

	public EventResponse perform(Event event)
		throws Exception
	{
		return null;
	}

	public EventResponse perform(DBTrans dbtrans, Event event)
		throws Exception
	{
		return null;
	}

	public Object processEvent(Event event)
		throws Exception
	{
		return null;
	}

	public void doEnd()
	{
	}

	static 
	{
		log = Logger.getLogger(SysTrace.ACTIONLOGGER);
	}
}
