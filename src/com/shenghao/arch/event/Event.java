// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Event.java

package com.shenghao.arch.event;

import java.io.Serializable;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class Event
	implements Serializable
{

	private HttpServletRequest request;
	private HashMap body;
	private String functionID;
	private String action;
	private String userid;
	private String realname;
	private String apanage;
	private String department;
	private String curip;
	private String systemid;

	public Event()
	{
		request = null;
		functionID = "";
		action = "";
		userid = "";
		realname = "";
		apanage = "";
		department = "";
		curip = "";
		systemid = "";
	}

	public String getFunctionID()
	{
		return functionID;
	}

	public void setFunctionID(String s)
	{
		functionID = s;
	}

	public HashMap getBody()
	{
		return body;
	}

	public void setBody(HashMap hashmap)
	{
		body = hashmap;
	}

	public HttpServletRequest getRequest()
	{
		return request;
	}

	public void setRequest(HttpServletRequest httpservletrequest)
	{
		request = httpservletrequest;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String s)
	{
		action = s;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String s)
	{
		userid = s;
	}

	public String getRealname()
	{
		return realname;
	}

	public void setRealname(String s)
	{
		realname = s;
	}

	public String getCurip()
	{
		return curip;
	}

	public void setCurip(String s)
	{
		curip = s;
	}

	public String getApanage()
	{
		return apanage;
	}

	public void setApanage(String s)
	{
		apanage = s;
	}

	public String getDepartment()
	{
		return department;
	}

	public void setDepartment(String s)
	{
		department = s;
	}

	public String getSystemid()
	{
		return systemid;
	}

	public void setSystemid(String s)
	{
		systemid = s;
	}
}
