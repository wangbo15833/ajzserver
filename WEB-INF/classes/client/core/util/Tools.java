// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tools.java

package client.core.util;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Tools
{

	public Tools()
	{
	}

	public static String getCurrentDate_hms()
	{
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		String s = simpledateformat.format(new Date());
		return s;
	}

	public static String getCurrentTime()
	{
		Calendar calendar = Calendar.getInstance();
		int i = calendar.get(1);
		int j = calendar.get(2);
		int k = calendar.get(5);
		int l = calendar.get(11);
		int i1 = calendar.get(12);
		int j1 = calendar.get(13);
		String s = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		String s5 = "";
		if (j + 1 < 10)
			s1 = (new StringBuilder()).append("0").append(j + 1).toString();
		else
			s1 = (new StringBuilder()).append("").append(j).toString();
		if (k < 10)
			s2 = (new StringBuilder()).append("0").append(k).toString();
		else
			s2 = (new StringBuilder()).append("").append(k).toString();
		if (l < 10)
			s3 = (new StringBuilder()).append("0").append(l).toString();
		else
			s3 = (new StringBuilder()).append("").append(l).toString();
		if (i1 < 10)
			s4 = (new StringBuilder()).append("0").append(i1).toString();
		else
			s4 = (new StringBuilder()).append("").append(i1).toString();
		if (j1 < 10)
			s5 = (new StringBuilder()).append("0").append(j1).toString();
		else
			s5 = (new StringBuilder()).append("").append(j1).toString();
		String s6 = (new StringBuilder()).append(i).append("-").append(s1).append("-").append(s2).append(" ").append(s3).append(":").append(s4).append(":").append(s5).toString();
		return s6;
	}

	public static String RegulateStrSize(String s, int i)
	{
		s = nvl(s);
		int j = s.length();
		if (j < i)
			return s;
		else
			return s.substring(0, i);
	}

	public static String delNull_toZeroPoint(String s)
	{
		return delNull(s, "0.00");
	}

	public static String delNull_toZero(String s)
	{
		return delNull(s, "0");
	}

	public static String delNull(String s, String s1)
	{
		if (s == null)
			s = "";
		else
			s = s.trim();
		return s != null && !s.equals("null") && !s.equals("") ? s : s1;
	}

	public static String nvl(String s, String s1)
	{
		return s != null && !s.equals("null") ? s : s1;
	}

	public static String nvl(String s)
	{
		return nvl(s, "");
	}

	public static String disposeDot(String s)
	{
		return disposeDot(s, 2);
	}

	public static String disposeDot(String s, int i)
	{
		s = delNull_toZeroPoint(s);
		String s1 = "";
		String s2 = "";
		int j = s.indexOf(".");
		if (j == -1)
		{
			s1 = s;
			s2 = "00";
		} else
		{
			s1 = s.substring(0, j);
			s2 = s.substring(j + 1);
			if (s2.length() == 1)
				s2 = (new StringBuilder()).append(s2).append("0").toString();
			else
			if (s2.length() > 2)
				s2 = s2.substring(0, 2);
		}
		s = (new StringBuilder()).append(s1).append(".").append(s2).toString();
		return s;
	}

	public static String dateToString(java.sql.Date date)
	{
		return desertionToLine(date);
	}

	public static java.sql.Date stringToDate(String s)
	{
		return sqlDate(s);
	}

	public static String desertionToLine(java.sql.Date date)
	{
		if (date == null)
			return "";
		String s = "";
		for (StringTokenizer stringtokenizer = new StringTokenizer(date.toString(), "-"); stringtokenizer.hasMoreTokens();)
			s = (new StringBuilder()).append(s).append(stringtokenizer.nextToken()).toString();

		return s;
	}

	public static java.sql.Date sqlDate(String s)
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
			java.sql.Date date = new java.sql.Date(gregoriancalendar.getTime().getTime());
			return date;
		}
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

	public static String toGb2312(String s)
	{
		return new String(nvl(s).getBytes("ISO-8859-1"), "GB2312");
		Exception exception;
		exception;
		return null;
	}

	public static String GBK(String s)
	{
		return new String(nvl(s).getBytes("ISO-8859-1"), "GBK");
		Exception exception;
		exception;
		return null;
	}

	public static String toIso88591(String s)
	{
		return new String(nvl(s).getBytes("GB2312"), "ISO-8859-1");
		Exception exception;
		exception;
		return null;
	}

	public static String closeMinutesSecond(String s)
	{
		s = s.replaceAll("'", "¡ä");
		s = s.replaceAll("\"", "¡å");
		return s;
		Exception exception;
		exception;
		return null;
	}

	public static String displayMinutesSecond(String s)
	{
		s = s.replaceAll("¡å", "\"");
		s = s.replaceAll("¡ä", "'");
		return s;
		Exception exception;
		exception;
		return null;
	}

	public static String BASE64Encoder(String s)
	{
		byte abyte0[] = s.getBytes();
		s = (new BASE64Encoder()).encode(abyte0);
		return s;
		Exception exception;
		exception;
		return null;
	}

	public static String BASE64Decoder(String s)
	{
		byte abyte0[] = (new BASE64Decoder()).decodeBuffer(s);
		s = new String(abyte0);
		return s;
		Exception exception;
		exception;
		return null;
	}

	public static String formatDateToString(String s)
	{
		if (s == null || s.equals("") || s.equals("null"))
			return "";
		else
			return s.substring(0, 10);
	}

	public static String getCurrentDate()
	{
		DateFormat dateformat = DateFormat.getDateInstance(2);
		String s = dateformat.format(new Date());
		if (s.length() == 8)
			s = (new StringBuilder()).append(s.substring(0, 5)).append("0").append(s.substring(5, 7)).append("0").append(s.substring(7, 8)).toString();
		if (s.length() == 9)
		{
			String s1 = s.substring(5, s.lastIndexOf("-"));
			try
			{
				if (Integer.parseInt(s1) > 10)
					s = (new StringBuilder()).append(s.substring(0, 8)).append("0").append(s.substring(8, 9)).toString();
				else
					s = (new StringBuilder()).append(s.substring(0, 5)).append("0").append(s.substring(5, 9)).toString();
			}
			catch (Exception exception) { }
		}
		return s;
	}

	public static String getDdbh()
	{
		return "";
	}

	public static double add(double d, double d1)
	{
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.add(bigdecimal1).doubleValue();
	}

	public static double sub(double d, double d1)
	{
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.subtract(bigdecimal1).doubleValue();
	}

	public static double mul(double d, double d1)
	{
		BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
		BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
		return bigdecimal.multiply(bigdecimal1).doubleValue();
	}

	public static double div(double d, double d1)
	{
		return div(d, d1, 10);
	}

	public static double div(double d, double d1, int i)
	{
		if (i < 0)
		{
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		} else
		{
			BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
			BigDecimal bigdecimal1 = new BigDecimal(Double.toString(d1));
			return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
		}
	}

	public static String round_to_2scale(double d, int i)
	{
		double d1 = round(d, i);
		return disposeDot(String.valueOf(d1));
	}

	public static double round(double d, int i)
	{
		if (i < 0)
		{
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		} else
		{
			BigDecimal bigdecimal = new BigDecimal(Double.toString(d));
			BigDecimal bigdecimal1 = new BigDecimal("1");
			return bigdecimal.divide(bigdecimal1, i, 4).doubleValue();
		}
	}

	public static void main(String args[])
	{
		System.out.println(round(123.8999999D, 2));
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

	public static String displayBackwardLine(String s)
	{
		s = s.replaceAll("\\\\", "\\\\\\\\");
		return s;
		Exception exception;
		exception;
		return null;
	}

	public static String nvl0(String s)
	{
		return s != null && !s.equals("null") && !s.equals("") ? s : "0";
	}

	public static String nvl000(String s)
	{
		return s != null && !s.equals("null") && !s.equals("") ? s : "0.00";
	}

	public static String round(String s)
		throws Exception
	{
		return round(s, 2);
	}

	public static String round(String s, int i)
		throws Exception
	{
		StringBuffer stringbuffer = new StringBuffer();
		for (int j = 0; j < i; j++)
			stringbuffer.append("0");

		DecimalFormat decimalformat = new DecimalFormat((new StringBuilder()).append("#0.").append(stringbuffer.toString()).toString());
		s = nvl0(s);
		double d = Double.parseDouble(s);
		BigDecimal bigdecimal = new BigDecimal(d);
		d = bigdecimal.setScale(i, 4).doubleValue();
		return decimalformat.format(d);
	}
}
