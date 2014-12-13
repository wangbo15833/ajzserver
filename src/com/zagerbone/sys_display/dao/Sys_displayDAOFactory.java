// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sys_displayDAOFactory.java

package com.zagerbone.sys_display.dao;

import com.zagerbone.util.DBTrans;

// Referenced classes of package com.zagerbone.sys_display.dao:
//			Sys_displayDAOImpl, Sys_displayDAO

public class Sys_displayDAOFactory
{

	public Sys_displayDAOFactory()
	{
	}

	public static Sys_displayDAO getDAO()
	{
		return new Sys_displayDAOImpl();
	}

	public static Sys_displayDAOImpl getDAO(String s)
	{
		return new Sys_displayDAOImpl(s);
	}

	public static Sys_displayDAOImpl getDAO(DBTrans dbtrans)
	{
		return new Sys_displayDAOImpl(dbtrans);
	}
}
