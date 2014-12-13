// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SysTrace.java

package com.shenghao.arch.util;

import java.util.Properties;
import org.apache.log4j.Logger;

public class SysTrace
{

	public static String JNDI = "jdbc/oasys";
	public static String VERSION = null;
	public static String SYSTEMID = null;
	public static String LOG4J_PROPERTIES = "/log4j.properties";
	public static String TRANSJNDI = "UserTransaction";
	public static String EMAILQUEUE = null;
	public static String EQCONNFACTORY = null;
	public static String SMTPSERVER = null;
	public static String ICBCURL = null;
	public static String MERCHANTID = null;
	public static String INTERFACETYPE = null;
	public static String MERURL = null;
	public static String ICBCCERTFILE = null;
	public static String ICBCKEYFILE = null;
	public static String ICBCKEYPASS = null;
	public static String ICBCBANKCERTFILE = null;
	public static String CMBURL = null;
	public static String CMBCONO = null;
	public static String CMBMERURL = null;
	public static String CMBKEYFILE = null;
	public static String CMBBRANCHID = null;
	public static String DBLOGGER = "dblogger";
	public static String ARCHLOGGER;
	public static String ACTIONLOGGER = "actionlogger";
	public static String DAOLOGGER = "daologger";
	public static String SHOP_CONTEXTROOT = "http://127.0.0.1:60/";
	public static String ACTION_MAPPINGS = "ACTION_MAPPINGS";
	public static String LOGIN_USER = "curUser";
	public static boolean PERFORMANCE_FLAG = true;
	private static Logger log;
	public static Properties wordList = null;

	public SysTrace()
	{
	}

	public static void debug(String s)
	{
		log.debug(s);
	}

	public static void info(String s)
	{
		log.info(s);
	}

	public static void warn(String s)
	{
		log.warn(s);
	}

	public static void error(String s)
	{
		log.error(s);
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
		ARCHLOGGER = "archlogger";
		log = Logger.getLogger(ARCHLOGGER);
	}
}
