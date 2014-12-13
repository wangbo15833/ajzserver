// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ActionMapping.java

package com.shenghao.arch.actionmapping;

import com.shenghao.arch.manage.function.Function;
import java.util.HashMap;

public class ActionMapping
{

	private HashMap functionMappings;

	public ActionMapping()
	{
	}

	public String getAction(String s)
	{
		if (functionMappings == null)
			return null;
		if (functionMappings.containsKey(s))
			return ((Function)functionMappings.get(s)).getAction();
		else
			return null;
	}

	public String getLogin(String s)
	{
		if (functionMappings == null)
			return null;
		if (functionMappings.containsKey(s))
			return ((Function)functionMappings.get(s)).getLogin();
		else
			return null;
	}

	public String getLoglevel(String s)
	{
		if (functionMappings == null)
			return null;
		if (functionMappings.containsKey(s))
			return ((Function)functionMappings.get(s)).getLoglevel();
		else
			return null;
	}

	public String getTranssurpport(String s)
	{
		if (functionMappings == null)
			return null;
		if (functionMappings.containsKey(s))
			return ((Function)functionMappings.get(s)).getTranssurpport();
		else
			return null;
	}

	public int getSecuritylevel(String s)
	{
		if (functionMappings == null)
			return 0;
		if (functionMappings.containsKey(s))
			return ((Function)functionMappings.get(s)).getSecuritylevel();
		else
			return 0;
	}

	public String getView(String s, String s1)
	{
		if (functionMappings == null)
			return null;
		if (functionMappings.containsKey(s))
		{
			if (s1.equals("Success"))
				return ((Function)functionMappings.get(s)).getViewsuccess();
			if (s1.equals("Fault"))
				return ((Function)functionMappings.get(s)).getViewfault();
			else
				return ((Function)functionMappings.get(s)).getViewerror();
		} else
		{
			return null;
		}
	}

	public HashMap getFunctionMappings()
	{
		return functionMappings;
	}

	public void setFunctionMappings(HashMap hashmap)
	{
		functionMappings = hashmap;
	}
}
