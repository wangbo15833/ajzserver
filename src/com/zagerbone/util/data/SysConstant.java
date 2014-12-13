// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SysConstant.java

package com.zagerbone.util.data;

import java.util.HashMap;

public class SysConstant
{

	public static HashMap hashMap = new HashMap();
	public static String Print_getResultBySelect_strSql = "0";
	public static String Print_getResultBySelect_strSql_queryCon = "0";
	public static String Print_ServletQueryCon_QueryString = "0";
	public static String Print_DBObject_getRecNumBySelect_strSql = "0";
	public static String APPNAME = "TDJY";
	public static String JNDI = "connectionPool_tdjy";
	public static String VERSION = null;
	public static String SYSTEMID = null;
	public static String TRANSJNDI = "UserTransaction";
	public static String DBLOGGER = "dblogger";
	public static String DAOLOGGER = "daologger";
	public static String CHARACTERLOGGER = "characterlogger";
	public static HashMap ConstantCONPool = new HashMap();

	public static void initSysConstant()
	{
	}

	public SysConstant()
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
		initSysConstant();
	}
}
