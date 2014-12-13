// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Syclgl.java

package com.zagerbone.syclgl;

import java.util.HashMap;

public class Syclgl
{

	private String syclglid;
	private HashMap extProperties;

	public Syclgl()
	{
		extProperties = new HashMap(1);
	}

	public void setSyclglid(String s)
	{
		syclglid = s;
	}

	public String getSyclglid()
	{
		return syclglid;
	}

	public void setProp(String s, String s1)
	{
		extProperties.put(s, s1);
	}

	public String getProp(String s)
	{
		if (extProperties.containsKey(s))
			return (String)extProperties.get(s);
		else
			return null;
	}
}
