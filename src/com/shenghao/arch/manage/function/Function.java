// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Function.java

package com.shenghao.arch.manage.function;


public class Function
{

	private String functionid;
	private String action;
	private String viewmain;
	private String viewsuccess;
	private String viewfault;
	private String viewerror;
	private String funtype;
	private String funtypename;
	private String note;
	private String operator;
	private String login;
	private String loginname;
	private String loglevel;
	private String loglevelname;
	private String opertime;
	private String flag;
	private String flagname;
	private String transsurpport;
	private int securitylevel;
	private String transname;
	private String functionname;

	public Function()
	{
		functionid = "";
		action = "";
		viewmain = "";
		viewsuccess = "";
		viewfault = "";
		viewerror = "";
		funtype = "";
		funtypename = "";
		note = "";
		operator = "";
		login = "";
		loginname = "";
		loglevel = "";
		loglevelname = "";
		opertime = "";
		flag = "";
		flagname = "";
		transsurpport = "";
		securitylevel = 0;
		transname = "";
		functionname = "";
	}

	public String getViewsuccess()
	{
		return viewsuccess;
	}

	public String getViewfault()
	{
		return viewfault;
	}

	public String getViewerror()
	{
		return viewerror;
	}

	public String getNote()
	{
		return note;
	}

	public String getFuntype()
	{
		return funtype;
	}

	public String getFunctionid()
	{
		return functionid;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String s)
	{
		action = s;
	}

	public void setFunctionid(String s)
	{
		functionid = s;
	}

	public void setFuntype(String s)
	{
		funtype = s;
	}

	public void setNote(String s)
	{
		note = s;
	}

	public void setViewerror(String s)
	{
		viewerror = s;
	}

	public void setViewfault(String s)
	{
		viewfault = s;
	}

	public void setViewsuccess(String s)
	{
		viewsuccess = s;
	}

	public String getOperator()
	{
		return operator;
	}

	public String getOpertime()
	{
		return opertime;
	}

	public void setOperator(String s)
	{
		operator = s;
	}

	public void setOpertime(String s)
	{
		opertime = s;
	}

	public void setViewmain(String s)
	{
		viewmain = s;
	}

	public String getViewmain()
	{
		return viewmain;
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String s)
	{
		login = s;
	}

	public String getLoginname()
	{
		return loginname;
	}

	public String getFuntypename()
	{
		return funtypename;
	}

	public String getFlagname()
	{
		return flagname;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String s)
	{
		flag = s;
	}

	public void setFlagname(String s)
	{
		flagname = s;
	}

	public void setFuntypename(String s)
	{
		funtypename = s;
	}

	public void setLoginname(String s)
	{
		loginname = s;
	}

	public String getLoglevelname()
	{
		return loglevelname;
	}

	public String getLoglevel()
	{
		return loglevel;
	}

	public void setLoglevel(String s)
	{
		loglevel = s;
	}

	public void setLoglevelname(String s)
	{
		loglevelname = s;
	}

	public String getTranssurpport()
	{
		return transsurpport;
	}

	public void setTranssurpport(String s)
	{
		transsurpport = s;
	}

	public String getTransname()
	{
		return transname;
	}

	public void setTransname(String s)
	{
		transname = s;
	}

	public int getSecuritylevel()
	{
		return securitylevel;
	}

	public void setSecuritylevel(int i)
	{
		securitylevel = i;
	}

	public String getFunctionname()
	{
		return functionname;
	}

	public void setFunctionname(String s)
	{
		functionname = s;
	}
}
