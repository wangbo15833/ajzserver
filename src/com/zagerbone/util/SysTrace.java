// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SysTrace.java

package com.zagerbone.util;

import com.zagerbone.util3.MySysds;
import java.util.HashMap;

public class SysTrace
{

	public static HashMap hashMap = new HashMap();
	public static HashMap hp_type = new HashMap();
	public static HashMap hp_pswd = new HashMap();
	public static String APPNAME = "TDJY";
	public static String JNDI = "connectionPool_tdjy";
	public static String VERSION = null;
	public static String SYSTEMID = null;
	public static String TRANSJNDI = "UserTransaction";
	public static String DBLOGGER = "dblogger";
	public static String DAOLOGGER = "daologger";
	public static String CHARACTERLOGGER = "characterlogger";

	public static void initSysTrace()
	{
		try
		{
			MySysds.getDataSources(hashMap, hp_type);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	public SysTrace()
	{
	}

	public static String showMsg(String s)
	{
		StringBuffer stringbuffer = new StringBuffer("<script language='javascript'>alert(\"");
		stringbuffer.append(s);
		stringbuffer.append("\");</script>");
		return stringbuffer.toString();
	}

	static 
	{
		initSysTrace();
	}
}
