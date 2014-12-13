// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tools.java

package com.shenghao.arch.util;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.shenghao.arch.util:
//			DBTransUtil, SysTrace

public class Tools
{

	public Tools()
	{
	}

	public static String nvl(String s, String s1)
	{
		return s == null || s.equals("null") ? s1 : s;
	}

	public static String nvl(String s)
	{
		return nvl(s, "");
	}

	public static String dateToString(Date date)
	{
		return desertionToLine(date);
	}

	public static Date stringToDate(String s)
	{
		return sqlDate(s);
	}

	public static String desertionToLine(Date date)
	{
		if (date == null)
			return "";
		String s = "";
		for (StringTokenizer stringtokenizer = new StringTokenizer(date.toString(), "-"); stringtokenizer.hasMoreTokens();)
			s = (new StringBuilder()).append(s).append(stringtokenizer.nextToken()).toString();

		return s;
	}

	public static Date sqlDate(String s)
	{
		if (s == null)
			return null;
		if (s.length() != 8)
		{
			return null;
		} else
		{
			int i = Integer.parseInt(s.substring(0, 4));
			int j = Integer.parseInt(s.substring(4, 6)) - 1;
			int k = Integer.parseInt(s.substring(6, 8));
			GregorianCalendar gregoriancalendar = new GregorianCalendar(i, j, k);
			Date date = new Date(gregoriancalendar.getTime().getTime());
			return date;
		}
	}

	public static String popListWithSelect(String s, String s1) {
		StringBuffer stringbuffer;
		DBTransUtil dbtransutil;
		CachedRowSet cachedrowset;
		String s2 = "";
		String s4 = "";
		stringbuffer = new StringBuffer("");
		dbtransutil = new DBTransUtil();
		cachedrowset = null;
		try {
			for (cachedrowset = dbtransutil.getResultBySelect(s); cachedrowset
					.next();) {
				String s3 = cachedrowset.getString(1);
				String s5 = cachedrowset.getString(2);
				if (s3.equals(s1)) {
					stringbuffer.append("<option value='");
					stringbuffer.append(s3);
					stringbuffer.append("' selected>");
					stringbuffer.append(s5);
					stringbuffer.append("</option>");
				} else {
					stringbuffer.append("<option value='");
					stringbuffer.append(s3);
					stringbuffer.append("'>");
					stringbuffer.append(s5);
					stringbuffer.append("</option>");
				}
			}
			return stringbuffer.toString();
		} catch (Exception exception1) {
			SysTrace.debug((new StringBuilder()).append(
					"popListWithSelect执行SQL语句时出错：").append(
					exception1.getMessage()).toString());
			return stringbuffer.toString();
		}

		finally {
			try {
				cachedrowset.close();
			} catch (Exception exception2) {

			}

		}
//		return stringbuffer.toString();
	}

	public static String showImage(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7)
	{
		StringBuffer stringbuffer = new StringBuffer("没有图片");
		StringBuffer stringbuffer1 = new StringBuffer("");
		String s8 = "";
		if (s.equals("0"))
		{
			if (s1.equals("2"))
				stringbuffer = new StringBuffer("图片审核中");
			else
			if (s4.equals("small"))
			{
				stringbuffer = new StringBuffer("<img src='");
				stringbuffer.append("/ShowImage?id=");
				stringbuffer.append(s2);
				stringbuffer.append("&idname=");
				stringbuffer.append(s3);
				stringbuffer.append("&colname=");
				stringbuffer.append(s4);
				stringbuffer.append("&tabname=");
				stringbuffer.append(s5);
				stringbuffer.append("' border='0' width='");
				stringbuffer.append(s6);
				stringbuffer.append("' height='");
				stringbuffer.append(s7);
				stringbuffer.append("'>");
			} else
			{
				stringbuffer = new StringBuffer("<img src='");
				stringbuffer.append("/ShowImage?id=");
				stringbuffer.append(s2);
				stringbuffer.append("&idname=");
				stringbuffer.append(s3);
				stringbuffer.append("&colname=");
				stringbuffer.append(s4);
				stringbuffer.append("&tabname=");
				stringbuffer.append(s5);
				stringbuffer.append("' border='0'>");
			}
		} else
		if (s.equals("1"))
			stringbuffer = new StringBuffer("没有图片");
		return stringbuffer.toString();
	}

	public static String showImage(String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, 
			String s8)
	{
		StringBuffer stringbuffer = new StringBuffer("没有图片");
		StringBuffer stringbuffer1 = new StringBuffer("");
		String s9 = "";
		if (s.equals("0"))
			if (s4.equals("small"))
			{
				stringbuffer = new StringBuffer("<img src='");
				stringbuffer.append("/ShowImage?id=");
				stringbuffer.append(s2);
				stringbuffer.append("&idname=");
				stringbuffer.append(s3);
				stringbuffer.append("&colname=");
				stringbuffer.append(s4);
				stringbuffer.append("&tabname=");
				stringbuffer.append(s5);
				stringbuffer.append("' border='0' width='");
				stringbuffer.append(s6);
				stringbuffer.append("' height='");
				stringbuffer.append(s7);
				stringbuffer.append("'>");
			} else
			{
				stringbuffer = new StringBuffer("<img src='");
				stringbuffer.append("/ShowImage?id=");
				stringbuffer.append(s2);
				stringbuffer.append("&idname=");
				stringbuffer.append(s3);
				stringbuffer.append("&colname=");
				stringbuffer.append(s4);
				stringbuffer.append("&tabname=");
				stringbuffer.append(s5);
				stringbuffer.append("' border='0'>");
			}
		return stringbuffer.toString();
	}

	public static String fixEnter(String s, String s1)
	{
		if (s == null || s.equals(""))
			return "";
		StringTokenizer stringtokenizer = new StringTokenizer(s, "\n");
		StringBuffer stringbuffer = new StringBuffer();
		do
		{
			if (!stringtokenizer.hasMoreTokens())
				break;
			String s2 = stringtokenizer.nextToken();
			if (s2 != null && !s2.equals(""))
				stringbuffer = stringbuffer.append((new StringBuilder()).append(s2).append(s1).toString());
		} while (true);
		return stringbuffer.substring(0, stringbuffer.lastIndexOf(s1));
	}

	public static String fixString(String s)
	{
		int i = 0;
		if (s == null || s.equals(""))
			return "";
		char c;
		StringBuffer stringbuffer;
		for (stringbuffer = new StringBuffer(s); i < stringbuffer.length();)
			if ((c = stringbuffer.charAt(i)) == '"')
			{
				stringbuffer.replace(i, i + 1, "&#34;");
				i += 5;
			} else
			if (c == '&')
			{
				stringbuffer.replace(i, i + 1, "&amp;");
				i += 5;
			} else
			if (c == '<')
			{
				stringbuffer.replace(i, i + 1, "&lt;");
				i += 4;
			} else
			if (c == '>')
			{
				stringbuffer.replace(i, i + 1, "&gt;");
				i += 4;
			} else
			{
				i++;
			}

		return stringbuffer.toString();
	}

	public static String fixOra(String s)
	{
		int i = 0;
		if (s == null || s.equals(""))
			return "";
		StringBuffer stringbuffer;
		for (stringbuffer = new StringBuffer(s); i < stringbuffer.length();)
		{
			char c = stringbuffer.charAt(i);
			if (c == '\'')
			{
				stringbuffer.replace(i, i + 1, "''");
				i += 2;
			} else
			{
				i++;
			}
		}

		return stringbuffer.toString();
	}

	public static boolean checkNumber(String s)
	{
		int i = 0;
		char ac[] = new char[1];
		StringBuffer stringbuffer = new StringBuffer(s);
		String s1 = "0123456789.";
		for (; i < stringbuffer.length(); i++)
		{
			char c = stringbuffer.charAt(i);
			ac[0] = c;
			String s2 = new String(ac);
			if (s1.indexOf(s2) == -1)
				return false;
		}

		return true;
	}

	public static boolean compareString(String s, String s1)
	{
		int i = 0;
		char ac[] = new char[1];
		StringBuffer stringbuffer = new StringBuffer(s);
		String s2 = s1;
		for (; i < stringbuffer.length(); i++)
		{
			char c = stringbuffer.charAt(i);
			ac[0] = c;
			String s3 = new String(ac);
			if (s2.indexOf(s3) == -1)
				return false;
		}

		return true;
	}

	public static String[] toStringArray(Object obj)
	{
		String as[] = null;
		String s = null;
		try
		{
			s = (String)obj;
		}
		catch (Exception exception)
		{
			as = (String[])(String[])obj;
		}
		if (s == null || s.equals(""))
		{
			if (as == null || as.equals(""))
				return null;
		} else
		{
			as = new String[1];
			as[0] = s;
		}
		return as;
	}

	public static String[] stringCode(String s)
	{
		StringTokenizer stringtokenizer = new StringTokenizer(s, "，");
		int i = stringtokenizer.countTokens();
		String as[] = new String[i];
		for (int j = 0; stringtokenizer.hasMoreTokens(); j++)
			as[j] = stringtokenizer.nextToken();

		return as;
	}

	public static String stringDateTen(String s)
	{
		if (nvl(s) == null || nvl(s).equals(""))
			return "";
		else
			return s.substring(0, 10);
	}
}
