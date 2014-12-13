// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BasicObject.java

package com.zagerbone.baseobject;

import com.zagerbone.util.SysTrace;
import org.apache.log4j.Logger;

public class BasicObject
{

	protected static Logger log;

	public BasicObject()
	{
	}

	static 
	{
		log = Logger.getLogger(SysTrace.DAOLOGGER);
	}
}
