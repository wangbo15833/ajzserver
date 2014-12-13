// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SysDbServer.java

package com.zagerbone.util.data;

import com.zagerbone.util.xml.XMLAnalyst;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SysDbServer
{

	public static String WORKERNAME = "Lnc";
	public static HashMap hp_worker = new HashMap();
	public static ArrayList al_worker = null;
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
			String s = "C:\\System_data\\sysDSName.xml";
			ArrayList arraylist = new ArrayList();
			arraylist.add("DSServerIp");
			arraylist.add("DSName");
			al_worker = XMLAnalyst.getData(s, arraylist);
			for (int i = 0; i < 1; i++)
			{
				HashMap hashmap = (HashMap)al_worker.get(i);
				hp_worker = hashmap;
			}

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	public static void setHp_worker(String s)
	{
		WORKERNAME = s;
		int i = 0;
		do
		{
			if (i >= al_worker.size())
				break;
			HashMap hashmap = (HashMap)al_worker.get(i);
			if (((String)hashmap.get("companyName")).equals(WORKERNAME))
			{
				hp_worker = hashmap;
				break;
			}
			i++;
		} while (true);
		System.out.println((new StringBuilder()).append("set hp_worker=").append(hp_worker).toString());
	}

	public static String getAttribute(String s)
	{
		return (String)hp_worker.get(s);
	}

	public static void setAttribute(String s, String s1)
	{
		hp_worker.put(s, s1);
	}

	public SysDbServer()
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
