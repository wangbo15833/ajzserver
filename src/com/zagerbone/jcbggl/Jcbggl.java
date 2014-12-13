// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Jcbggl.java

package com.zagerbone.jcbggl;

import java.util.HashMap;

public class Jcbggl
{

	private String jcbgglid;
	private HashMap extProperties;

	public Jcbggl()
	{
		extProperties = new HashMap(1);
	}

	public void setJcbgglid(String s)
	{
		jcbgglid = s;
	}

	public String getJcbgglid()
	{
		return jcbgglid;
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
