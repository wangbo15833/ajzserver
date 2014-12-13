// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseAction.java

package com.shenghao.arch.action;

import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.zagerbone.util.DBTrans;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public interface BaseAction
{

	public abstract void init(ServletContext servletcontext, HttpSession httpsession);

	public abstract void doStart();

	public abstract EventResponse perform(Event event)
		throws Exception;

	public abstract EventResponse perform(DBTrans dbtrans, Event event)
		throws Exception;

	public abstract Object processEvent(Event event)
		throws Exception;

	public abstract void doEnd();
}
