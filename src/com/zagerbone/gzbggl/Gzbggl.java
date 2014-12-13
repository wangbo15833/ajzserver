// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Gzbggl.java

package com.zagerbone.gzbggl;

import java.util.HashMap;

public class Gzbggl
{

	private String gzbgglid;
	private HashMap extProperties;

	public Gzbggl()
	{
		extProperties = new HashMap(1);
	}

	public void setGzbgglid(String s)
	{
		gzbgglid = s;
	}

	public String getGzbgglid()
	{
		return gzbgglid;
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
