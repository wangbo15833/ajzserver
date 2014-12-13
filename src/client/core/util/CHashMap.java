// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CHashMap.java

package client.core.util;

import java.util.HashMap;

public class CHashMap
{

	HashMap hp_page;
	String name_prefix;

	public CHashMap()
	{
		hp_page = new HashMap();
		name_prefix = "";
	}

	public void setName_prefix(String s)
	{
		name_prefix = s;
	}

	public void put(String s, Object obj)
	{
		hp_page.put((new StringBuilder()).append(name_prefix).append(s).toString(), obj);
	}

	public Object get(String s)
	{
		return hp_page.get((new StringBuilder()).append(name_prefix).append(s).toString());
	}
}
