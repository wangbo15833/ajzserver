// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Gzbzgl.java

package com.zagerbone.gzbzgl;

import java.util.HashMap;

public class Gzbzgl
{

	private String gzbzglid;
	private HashMap extProperties;

	public Gzbzgl()
	{
		extProperties = new HashMap(1);
	}

	public void setGzbzglid(String s)
	{
		gzbzglid = s;
	}

	public String getGzbzglid()
	{
		return gzbzglid;
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
