// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PageTools.java

package com.zagerbone.util3;

import com.zagerbone.util.Tools;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Iterator;
import sun.jdbc.rowset.CachedRowSet;

public class PageTools
{

	public PageTools()
	{
	}

	public static String arrayFill_originOrder(String s, String as[], String as1[])
	{
		StringBuffer stringbuffer = new StringBuffer("");
		int i = as.length;
		stringbuffer.append("<script>");
		try
		{
			for (int j = 0; j < i; j++)
			{
				if (!as1[j].equals("special") && !as1[j].equals("select"))
					continue;
				stringbuffer.append((new StringBuilder()).append("fill(document.").append(s).append(".").toString());
				if (as1[j].equals("special"))
					stringbuffer.append((new StringBuilder()).append(as[j]).append("_select").toString());
				else
				if (as1[j].equals("select"))
					stringbuffer.append(as[j]);
				stringbuffer.append((new StringBuilder()).append(",").append(as[j]).append("_array);").toString());
			}

			stringbuffer.append("</script>");
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		return stringbuffer.toString();
	}

	public static String popListWithSelect(String as[], String as1[], String s)
	{
		StringBuffer stringbuffer;
		stringbuffer = new StringBuffer("");
		String s1 = "";
		String s3 = "";
		for (int i = 0; i < as.length; i++)
		{
			String s2 = as1[i];
			String s4 = as[i];
			if (s2.equals(s))
			{
				stringbuffer.append("<option value='");
				stringbuffer.append(s2);
				stringbuffer.append("' selected>");
				stringbuffer.append(s4);
				stringbuffer.append("</option>");
			} else
			{
				stringbuffer.append("<option value='");
				stringbuffer.append(s2);
				stringbuffer.append("'>");
				stringbuffer.append(s4);
				stringbuffer.append("</option>");
			}
		}

		return stringbuffer.toString();
		Exception exception;
		exception;
		System.out.println(exception.getMessage());
		return stringbuffer.toString();
		Exception exception1;
		exception1;
		return stringbuffer.toString();
	}

	public static String JsArray(Collection collection, String s)
	{
		StringBuffer stringbuffer = new StringBuffer("");
		try
		{
			stringbuffer.append("<script>");
			stringbuffer.append((new StringBuilder()).append("var ").append(s).append(" = new Array();\t").toString());
			stringbuffer.append((new StringBuilder()).append(s).append("[").append("0").append("]=").append("\"").append("").append("\"").append(";").toString());
			Iterator iterator = collection.iterator();
			for (int i = 1; iterator.hasNext(); i++)
			{
				String s1 = (String)iterator.next();
				stringbuffer.append((new StringBuilder()).append(s).append("[").append(i).append("]=").append("\"").append(s1).append("\"").append("\n; ").toString());
			}

			stringbuffer.append("</script>");
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		return stringbuffer.toString();
	}

	public static String JsArray(CachedRowSet cachedrowset, String s, String s1)
	{
		StringBuffer stringbuffer = new StringBuffer("");
		try
		{
			stringbuffer.append("<script>");
			stringbuffer.append((new StringBuilder()).append("var ").append(s1).append(" = new Array();\t").toString());
			stringbuffer.append((new StringBuilder()).append(s1).append("[").append("0").append("]=").append("\"").append("").append("\"").append(";").toString());
			for (int i = 1; cachedrowset.next(); i++)
			{
				String s2 = Tools.nvl(cachedrowset.getString(s));
				stringbuffer.append((new StringBuilder()).append(s1).append("[").append(i).append("]=").append("\"").append(s2).append("\"").append("\n; ").toString());
			}

			stringbuffer.append("</script>");
			cachedrowset.first();
			cachedrowset.previous();
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		return stringbuffer.toString();
	}

	public static String JsArray_novoid(CachedRowSet cachedrowset, String s, String s1)
	{
		StringBuffer stringbuffer = new StringBuffer("");
		try
		{
			stringbuffer.append("<script>");
			stringbuffer.append((new StringBuilder()).append("var ").append(s1).append(" = new Array(); ").toString());
			for (int i = 0; cachedrowset.next(); i++)
			{
				String s2 = Tools.nvl(cachedrowset.getString(s));
				stringbuffer.append((new StringBuilder()).append(s1).append("[").append(i).append("]=").append("\"").append(s2).append("\"").append("\n; ").toString());
			}

			stringbuffer.append("</script>");
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		return stringbuffer.toString();
	}

	public static String JsArray_novoid(Collection collection, String s)
	{
		StringBuffer stringbuffer = new StringBuffer("");
		try
		{
			stringbuffer.append("<script>");
			stringbuffer.append((new StringBuilder()).append("var ").append(s).append(" = new Array(); ").toString());
			Iterator iterator = collection.iterator();
			for (int i = 0; iterator.hasNext(); i++)
			{
				String s1 = (String)iterator.next();
				stringbuffer.append((new StringBuilder()).append(s).append("[").append(i).append("]=").append("\"").append(s1).append("\"").append("\n; ").toString());
			}

			stringbuffer.append("</script>");
		}
		catch (Exception exception)
		{
			System.out.println(exception.getMessage());
		}
		return stringbuffer.toString();
	}

	public static String popListWithSelect_crs(CachedRowSet cachedrowset, String s, String s1)
	{
		String as[];
		String as1[];
		StringBuffer stringbuffer;
		if (cachedrowset == null)
			return "";
		int i = cachedrowset.size();
		as = new String[i];
		as1 = new String[i];
		try
		{
			cachedrowset.beforeFirst();
			for (int j = 0; cachedrowset.next(); j++)
			{
				as[j] = cachedrowset.getString(s);
				as1[j] = cachedrowset.getString(s);
			}

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		stringbuffer = new StringBuffer("");
		String s2 = "";
		String s4 = "";
		for (int k = 0; k < as.length; k++)
		{
			String s3 = as1[k];
			String s5 = as[k];
			if (s3.equals(s1))
			{
				stringbuffer.append("<option value='");
				stringbuffer.append(s3);
				stringbuffer.append("' selected>");
				stringbuffer.append(s5);
				stringbuffer.append("</option>");
			} else
			{
				stringbuffer.append("<option value='");
				stringbuffer.append(s3);
				stringbuffer.append("'>");
				stringbuffer.append(s5);
				stringbuffer.append("</option>");
			}
		}

		return stringbuffer.toString();
		Exception exception1;
		exception1;
		System.out.println(exception1.getMessage());
		return stringbuffer.toString();
		Exception exception2;
		exception2;
		return stringbuffer.toString();
	}
}
