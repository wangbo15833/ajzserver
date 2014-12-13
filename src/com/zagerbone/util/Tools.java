package com.zagerbone.util;

import com.zagerbone.exception.BussinessProcessException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import java.math.BigDecimal;
import sun.jdbc.rowset.CachedRowSet;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

// Referenced classes of package com.zagerbone.util:
//			DBTrans

public class Tools
{

	public Tools()
	{
	}

	public static String popListWithSelect(String s, String s1)
	{
		try
		{
		StringBuffer stringbuffer;
		DBTrans dbtrans;
		CachedRowSet cachedrowset;
		String s2 = "";
		String s4 = "";
		stringbuffer = new StringBuffer("");
		dbtrans = new DBTrans();
		cachedrowset = null;
		for (cachedrowset = dbtrans.getResultBySelect(s); cachedrowset.next();)
		{
			String s3 = cachedrowset.getString(1);
			String s5 = cachedrowset.getString(2);
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

		
			cachedrowset.close();
		return stringbuffer.toString();
		}
		catch (Exception exception) {
		System.out.println((new StringBuilder()).append("popListWithSelect执行SQL语句时出错：").append(exception.getMessage()).toString());
		return "<input value='查询出错'></input>";
		}
	}

	public static double personalTax(double d)
	{
		double d1 = 0.0D;
		double d2 = 0.0D;
		double d12 = 0.0D;
		double d22 = 0.0D;
		if (d <= 0.0D)
			d1 = 0.0D;
		else
		if (d <= 475D)
		{
			double d3 = 475D;
			double d13 = 0.050000000000000003D;
			double d23 = 0.0D;
			d1 = d * d13 - d23;
		} else
		if (d <= 1825D)
		{
			double d4 = 1825D;
			double d14 = 0.10000000000000001D;
			double d24 = 25D;
			d1 = d * d14 - d24;
		} else
		if (d <= 4375D)
		{
			double d5 = 4375D;
			double d15 = 0.14999999999999999D;
			double d25 = 125D;
			d1 = d * d15 - d25;
		} else
		if (d <= 16375D)
		{
			double d6 = 16375D;
			double d16 = 0.20000000000000001D;
			double d26 = 375D;
			d1 = d * d16 - d26;
		} else
		if (d <= 31375D)
		{
			double d7 = 31375D;
			double d17 = 0.25D;
			double d27 = 1375D;
			d1 = d * d17 - d27;
		} else
		if (d <= 45375D)
		{
			double d8 = 45375D;
			double d18 = 0.29999999999999999D;
			double d28 = 3375D;
			d1 = d * d18 - d28;
		} else
		if (d <= 58375D)
		{
			double d9 = 58375D;
			double d19 = 0.34999999999999998D;
			double d29 = 6375D;
			d1 = d * d19 - d29;
		} else
		if (d <= 70375D)
		{
			double d10 = 70375D;
			double d20 = 0.40000000000000002D;
			double d30 = 10375D;
			d1 = d * d20 - d30;
		} else
		if (d > 70375D)
		{
			double d11 = 70375D;
			double d21 = 0.45000000000000001D;
			double d31 = 15375D;
			d1 = d * d21 - d31;
		}
		return d1;
	}

	public static String booleanTrans(String s)
		throws BussinessProcessException
	{
		String s1 = null;
		if (s.equals("false"))
			s1 = "0";
		else
		if (s.equals("true"))
			s1 = "1";
		return s1;
	}
	
	public static String omit(String s,Integer t)
		throws BussinessProcessException
	{
		String s1="";
		try
		{
			if(t==0){
				return nvl(s);
			}
			s=nvl(s);
			s=s.replaceAll("\n","");
			s=s.replaceAll("\r","");
			s1=nvl(s).length()>t?s.substring(0,t)+"...":s;
			System.out.println(s);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return s1;
	}
	
		public static String omitnd(String s,Integer t)
		throws BussinessProcessException
	{
		String s1="";
		try
		{
			if(t==0){
				return nvl(s);
			}
			s=nvl(s);
			s=s.replaceAll("\n","");
			s=s.replaceAll("\r","");
			s1=nvl(s).length()>t?s.substring(0,t):s;
			System.out.println(s);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return s1;
	}
	
		public static String omit(String s,String s2)
		throws BussinessProcessException
	{
		int t =0;
		String s1="";
		try
		{
			String s3="...";
			if(((nvl0(s2)).replaceAll(" ","")).indexOf("'")>0){s3="";}
			t = Integer.parseInt(((nvl0(s2)).replaceAll(" ","")).equals("")?"0":((nvl0(s2)).replaceAll(" ","").replaceAll("'","")));
			if(t==0){
				return nvl(s);
			}
			s=nvl(s);
			s=s.replaceAll("\n","");
			s=s.replaceAll("\r","");
			s1=nvl(s).length()>t?s.substring(0,t)+s3:s;
			System.out.println(s);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return s1;
	}

	public static int dateMinus_gap(String s, String s1)
		throws BussinessProcessException
	{
		int i = 0;
		try
		{
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = simpledateformat.parse(s);
			long l = date.getTime();
			date = simpledateformat.parse(s1);
			long l1 = date.getTime();
			long l2 = l - l1;
			l2 /= 60000L;
			i = (int)l2;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return i;
	}
	
	public static long dateMinus_gapms(String s, String s1)
		throws BussinessProcessException
	{
		long gapmm=0L;
		try
		{
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = simpledateformat.parse(s);
			long l = date.getTime();
			date = simpledateformat.parse(s1);
			long l1 = date.getTime();
			gapmm = l - l1;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return gapmm;
	}

	public static String dateMinus(String s, int i)
		throws BussinessProcessException
	{
		String s1 = null;
		try
		{
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = simpledateformat.parse(s);
			Date date1 = new Date(date.getTime() - (long)(i * 1000 * 60 * 60 * 24));
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			String s2 = String.valueOf(calendar.get(1));
			String s3 = String.valueOf(calendar.get(2) + 1);
			if (s3.length() < 2)
				s3 = (new StringBuilder()).append("0").append(s3).toString();
			String s4 = String.valueOf(calendar.get(5));
			if (s4.length() < 2)
				s4 = (new StringBuilder()).append("0").append(s4).toString();
			String s5 = (new StringBuilder()).append(s2).append("-").append(s3).append("-").append(s4).toString();
			s1 = s5;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return s1;
	}

	public static String dateMinus(String s, String s1)
		throws BussinessProcessException
	{
		String s2 = null;
		try
		{
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = simpledateformat.parse(s);
			Date date1 = new Date(date.getTime() - (long)Integer.parseInt(s1) * 1000L * 60L * 60L * 24L);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			String s3 = String.valueOf(calendar.get(1));
			String s4 = String.valueOf(calendar.get(2) + 1);
			if (s4.length() < 2)
				s4 = (new StringBuilder()).append("0").append(s4).toString();
			String s5 = String.valueOf(calendar.get(5));
			if (s5.length() < 2)
				s5 = (new StringBuilder()).append("0").append(s5).toString();
			String s6 = (new StringBuilder()).append(s3).append("-").append(s4).append("-").append(s5).toString();
			s2 = s6;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return s2;
	}

	public static String dateMinus(String s, String s1, String s2)
		throws BussinessProcessException
	{
		String s3 = null;
		try
		{
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = simpledateformat.parse(s);
			Date date1 = new Date((date.getTime() - (long)Integer.parseInt(s1) * 1000L * 60L * 60L * 24L) + (long)Integer.parseInt(s2) * 1000L * 60L * 60L * 24L);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date1);
			String s4 = String.valueOf(calendar.get(1));
			String s5 = String.valueOf(calendar.get(2) + 1);
			if (s5.length() < 2)
				s5 = (new StringBuilder()).append("0").append(s5).toString();
			String s6 = String.valueOf(calendar.get(5));
			if (s6.length() < 2)
				s6 = (new StringBuilder()).append("0").append(s6).toString();
			String s7 = (new StringBuilder()).append(s4).append("-").append(s5).append("-").append(s6).toString();
			s3 = s7;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return s3;
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

	public static String getCurrentDate_hms()
	{
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
		String s = simpledateformat.format(new Date());
		return s;
	}

	public static String formatDateToString(String s)
	{
		if (s == null || s.equals("") || s.equals("null"))
			return "";
		if (s.length() > 11)
			return s.substring(0, 10);
		else
			return s;
	}

	public static String formatDateToString_hms(String s)
	{
		if (s == null || s.equals("") || s.equals("null"))
			return "";
		if (s.length() > 19)
			return s.substring(0, 19);
		else
			return s;
	}

	public static String formatDateToSQLServeDate(String s)
	{
		s = formatDateToString(s);
		String s1 = "";
		String s2 = s.substring(0, 4);
		String s3 = s.substring(5, 7);
		String s4 = s.substring(8, 10);
		s1 = (new StringBuilder()).append(s3).append(" ").append(s4).append(" ").append(s2).toString();
		return s1;
	}

	public static Date getDateBydateSpan(Date date, int i)
	{
		long l = date.getTime();
		l -= i * 24 * 3600 * 1000;
		Date date1 = new Date(l);
		return date1;
	}

	public static String getDateBydateSpan(String s, int i)
	{
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpledateformat.parse(s, new ParsePosition(0));
		long l = date.getTime();
		l -= i * 24 * 3600 * 1000;
		Date date1 = new Date(l);
		String s1 = simpledateformat.format(date1);
		DateFormat dateformat = DateFormat.getDateInstance(2);
		return s1;
	}

	public static int dayMinus(Date date, Date date1)
	{
		long l = date.getTime() - date1.getTime();
		long l1 = (date.getTime() - date1.getTime()) / 0x5265c00L;
		return (int)l1;
	}

	public static int dayMinus(String s, String s1)
	{
		if (s1.equals(""))
			s1 = "1900-01-01";
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = simpledateformat.parse(s, new ParsePosition(0));
		Date date1 = simpledateformat.parse(s1, new ParsePosition(0));
		long l = date.getTime() - date1.getTime();
		long l1 = (date.getTime() - date1.getTime()) / 0x5265c00L;
		return (int)l1;
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

	public static String delNull_toZeroPoint(String s)
	{
		return delNull(s, "0.00");
	}

	public static String delNull_toZeroDot(String s)
	{
		return delNull(s, "0.00");
	}

	public static String delNull_toZero(String s)
	{
		return delNull(s, "0");
	}

	public static String delToZero(String s)
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
		return nvl(s, "").trim();
	}

	public static String nvl0(String s)
	{
		return s != null && !s.equals("null") && !s.equals("") ? s : "0";
	}

	public static String nvlNegative(String s)
	{
		return s != null && !s.equals("null") && !s.equals("") ? s : "-1";
	}

	public static String nvlTime(String s)
	{
		return s != null && !s.equals("null") && !s.equals("1900-01-01 00:00:00.0") ? s : "";
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

	public static boolean checkDigital(String s)
	{
		int i = 0;
		char ac[] = new char[1];
		StringBuffer stringbuffer = new StringBuffer(s);
		String s1 = "0123456789";
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

	public static int getFirstDigitalPos(String s)
	{
		int i = 0;
		char ac[] = new char[1];
		Object obj = null;
		StringBuffer stringbuffer = new StringBuffer(s);
		String s2 = "0123456789";
		do
		{
			if (i >= stringbuffer.length())
				break;
			ac[0] = stringbuffer.charAt(i);
			String s1 = new String(ac);
			if (s2.indexOf(s1) != -1)
				break;
			i++;
		} while (true);
		return i;
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

	public static boolean checkCharacter(String s)
	{
		int i = 0;
		char ac[] = new char[1];
		StringBuffer stringbuffer = new StringBuffer(s);
		String s1 = "abcdefghijklmnopqrstuvwxyz";
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

	public static boolean check_Number_Character(String s)
	{
		int i = 0;
		char ac[] = new char[1];
		StringBuffer stringbuffer = new StringBuffer(s);
		String s1 = "0123456789.abcdefghijklmnopqrstuvwxyz";
		for (; i < stringbuffer.length(); i++)
		{
			char c = stringbuffer.charAt(i);
			ac[0] = c;
			String s2 = new String(ac);
			s2 = s2.toLowerCase();
			if (s1.indexOf(s2) == -1)
				return false;
		}

		return true;
	}

	public static boolean check_EffectCharacter(String s)
	{
		int i = 0;
		char ac[] = new char[1];
		StringBuffer stringbuffer = new StringBuffer(s);
		String s1 = "0123456789.abcdefghijklmnopqrstuvwxyz_-";
		for (; i < stringbuffer.length(); i++)
		{
			char c = stringbuffer.charAt(i);
			ac[0] = c;
			String s2 = new String(ac);
			s2 = s2.toLowerCase();
			if (s1.indexOf(s2) == -1)
				return false;
		}

		return true;
	}

	public static boolean check_Part_id_Characters(String s)
	{
		int i = 0;
		char ac[] = new char[1];
		StringBuffer stringbuffer = new StringBuffer(s);
		String s1 = "0123456789.abcdefghijklmnopqrstuvwxyz_-*";
		for (; i < stringbuffer.length(); i++)
		{
			char c = stringbuffer.charAt(i);
			ac[0] = c;
			String s2 = new String(ac);
			s2 = s2.toLowerCase();
			if (s1.indexOf(s2) == -1)
				return false;
		}

		return true;
	}

	public static int IntegerStr(String s)
	{
		int i = 0;
		StringBuffer stringbuffer = new StringBuffer(s);
		String s1 = "0123456789";
		ArrayList arraylist = new ArrayList();
		char ac[] = new char[1];
		for (int j = 0; j < stringbuffer.length(); j++)
		{
			char c = stringbuffer.charAt(j);
			String s2 = new String(ac);
			if (s1.indexOf(s2) == -1)
				arraylist.add(s2);
		}

		for (int k = 0; k < arraylist.size(); k++)
			s = s.replace((String)arraylist.get(k), "");

		try
		{
			i = Integer.parseInt(nvl0(s));
		}
		catch (Exception exception) { }
		return i;
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
		try{
		return new String(nvl(s).getBytes("ISO-8859-1"), "GB2312");
		}catch(UnsupportedEncodingException uee){
		return null;
		}
	}

	public static String GBK(String s)
	{
		try{
		return new String(nvl(s).getBytes("ISO-8859-1"), "GBK");
		}catch(UnsupportedEncodingException uee){
		return null;
		}
	}

	public static String toIso88591(String s)
	{
		try{
		return new String(nvl(s).getBytes("GB2312"), "ISO-8859-1");
		}catch(UnsupportedEncodingException uee){
		return null;
		}
	}

	public static String closeMinutesSecond(String s)
	{
		try{
		s = s.replaceAll("'", "′");
		s = s.replaceAll("\"", "″");
		return s;
		}catch(Exception exception)
		{
		return null;
		}
	}

	public static String displayMinutesSecond(String s)
	{
		s = s.replaceAll("″", "\"");
		s = s.replaceAll("′", "'");
		return s;
	}

	public static String BASE64Encoder(String s)
	{
		
		byte abyte0[] = s.getBytes();
		s = (new BASE64Encoder()).encode(abyte0);
		return s;
	}

	public static String BASE64Decoder(String s)
	{
		try
		{
		byte abyte0[] = (new BASE64Decoder()).decodeBuffer(s);
		s = new String(abyte0);
		return s;
		}catch(IOException ioexception){
		return null;
		}
	}

	public static String UrlCSymbol(String s)
	{
		if (s.indexOf("?") == -1)
			s = "?";
		else
			s = "&";
		return s;
	}

	public static String UrlCSecSym(String s)
	{
		s = "&";
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

	public static String roundStr(String s)
	{
		s = nvl(s);
		s = s.substring(0, s.indexOf("."));
		return s;
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

	public static String round(String s, int i)
		throws Exception
	{
		StringBuffer stringbuffer = new StringBuffer();
		for (int j = 0; j < i; j++)
			stringbuffer.append("0");

		DecimalFormat decimalformat = null;
		if (i == 0)
			decimalformat = new DecimalFormat((new StringBuilder()).append("#0").append(stringbuffer.toString()).toString());
		else
			decimalformat = new DecimalFormat((new StringBuilder()).append("#0.").append(stringbuffer.toString()).toString());
		s = nvl0(s);
		double d = Double.parseDouble(s);
		BigDecimal bigdecimal = new BigDecimal(d);
		d = bigdecimal.setScale(i, 4).doubleValue();
		String s1 = "";
		return decimalformat.format(d);
	}

	public static String nvl000(String s)
	{
		return s != null && !s.equals("null") && !s.equals("") ? s : "0.00";
	}

	public static String round(String s)
		throws Exception
	{
		return nvl000(round(s, 2));
	}

	public static String round_0(String s)
		throws Exception
	{
		return nvl000(round(s, 0)).replace(".", "");
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

	public static Vector distinctVector(Collection collection)
	{
		HashSet hashset = new HashSet(collection);
		Iterator iterator = hashset.iterator();
		Vector vector = new Vector();
		for (; iterator.hasNext(); vector.add((String)iterator.next()));
		return vector;
	}

	public static ArrayList distinctArrayList(Collection collection)
	{
		HashSet hashset = new HashSet(collection);
		Iterator iterator = hashset.iterator();
		ArrayList arraylist = new ArrayList();
		for (; iterator.hasNext(); arraylist.add((String)iterator.next()));
		return arraylist;
	}

	public static Collection distinctCollection(Collection collection)
	{
		HashSet hashset = new HashSet(collection);
		HashSet hashset1 = hashset;
		return hashset1;
	}

	public static boolean compareArrayList(ArrayList arraylist, ArrayList arraylist1)
	{
		if (arraylist.size() != arraylist1.size())
			return false;
		ArrayList arraylist2 = new ArrayList();
		for (int i = 0; i < arraylist.size(); i++)
			arraylist2.add((String)arraylist.get(i));

		for (int j = 0; j < arraylist1.size(); j++)
			arraylist2.add((String)arraylist1.get(j));

		Collection collection = distinctCollection(arraylist);
		Collection collection1 = distinctCollection(arraylist1);
		Collection collection2 = distinctCollection(arraylist2);
		return collection.size() == collection2.size() && collection1.size() == collection2.size();
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
	}

	public static String popListWithSelect_void(String as[], String as1[], String s)
	{
		StringBuffer stringbuffer;
		stringbuffer = new StringBuffer("");
		String s1 = "";
		String s3 = "";
		stringbuffer.append("<option value='");
		stringbuffer.append("");
		stringbuffer.append("'>");
		stringbuffer.append("");
		stringbuffer.append("</option>");
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
	}

	public static String Str_10ch(String s)
	{
		return Str_form_len(s, 10);
	}

	public static String Str_15ch(String s)
	{
		return Str_form_len(s, 15);
	}

	public static String Str_25ch(String s)
	{
		return Str_form_len(s, 25);
	}

	public static String Str_26ch(String s)
	{
		return Str_form_len(s, 26);
	}

	public static String Str_5ch(String s)
	{
		return Str_form_len(s, 10);
	}

	public static String Str_8ch(String s)
	{
		return Str_form_len(s, 8);
	}

	public static String Str_xch(String s, int i)
	{
		String s1 = "";
		boolean flag = false;
		if (s.length() >= i)
			return s;
		if (s.length() < i)
		{
			int j = i - s.length();
			for (int k = 0; k < j; k++)
				s1 = (new StringBuilder()).append(s1).append("&nbsp;&nbsp;").toString();

			s = (new StringBuilder()).append(s).append(s1).toString();
		}
		return s;
	}

	public static String Str_form_len(String s, int i)
	{
		int j = 0;
		boolean flag = false;
		boolean flag1 = false;
		byte abyte0[] = s.getBytes();
		j = JudgeBytLen(s);
		if (j > i)
			return s;
		if (i >= j)
		{
			int k = i - j;
			String s1 = "";
			for (int l = 0; l < k; l++)
				s1 = (new StringBuilder()).append(s1).append("&nbsp;").toString();

			s = (new StringBuilder()).append(s).append(s1).toString();
		}
		return s;
	}

	public static String Str_formatToFixedLength(String s, int i)
	{
		s = nvl(s);
		int j = 0;
		boolean flag = false;
		boolean flag1 = false;
		byte abyte0[] = s.getBytes();
		j = JudgeBytLen(s);
		if (j > i)
			return s;
		if (i >= j)
		{
			int k = i - j;
			String s1 = "";
			for (int l = 0; l < k; l++)
				s1 = (new StringBuilder()).append(s1).append(" ").toString();

			s = (new StringBuilder()).append(s).append(s1).toString();
		}
		return s;
	}

	public static String Str_formatToFixedLen(String s, int i)
	{
		s = nvl(s);
		int j = 0;
		boolean flag = false;
		boolean flag1 = false;
		byte abyte0[] = s.getBytes();
		j = JudgeBytLen(s);
		if (j > i)
			return s;
		if (i >= j)
		{
			int k = i - j;
			String s1 = "";
			for (int l = 0; l < k; l++)
				s1 = (new StringBuilder()).append(s1).append("&nbsp;").toString();

			s = (new StringBuilder()).append(s).append(s1).toString();
		}
		return s;
	}

	public static String FormatToLargeFixedLen(String s, int i)
	{
		s = nvl(s);
		int j = 0;
		boolean flag = false;
		boolean flag1 = false;
		byte abyte0[] = s.getBytes();
		j = JudgeBytLen(s);
		if (j > i)
			return s;
		if (i >= j)
		{
			int k = i - j;
			String s1 = "";
			for (int l = 0; l < k; l++)
				s1 = (new StringBuilder()).append(s1).append("&nbsp;").toString();

			s = (new StringBuilder()).append(s).append(s1).toString();
		}
		return s;
	}

	public static String FormatToSmallFixedLen(String s, int i)
	{
		s = nvl(s);
		if (s.equals(""))
			s = "&nbsp;";
		int j = 0;
		boolean flag = false;
		boolean flag1 = false;
		byte abyte0[] = s.getBytes();
		j = JudgeBytLen(s);
		if (j <= i)
			return s;
		if (j > i)
		{
			int k = i - j;
			String s1 = "";
			int l = 0;
			do
			{
				if (l >= i)
					break;
				s1 = s.substring(0, l);
				int i1 = JudgeBytLen(s1);
				if (i1 > i - 5)
					break;
				l++;
			} while (true);
			s = (new StringBuilder()).append(s1).append("...").toString();
		}
		return s;
	}

	public static boolean isChineseChar(char c)
		throws UnsupportedEncodingException
	{
		return String.valueOf(c).getBytes("GBK").length > 1;
	}

	public static int JudgeBytLen(String s)
	{
		if (s.length() == 0)
		{
			return 0;
		} else
		{
			boolean flag = false;
			byte abyte0[] = s.getBytes();
			return abyte0.length;
		}
	}

	public static int JudgeBytLen(char c)
	{
		String s = String.valueOf(c);
		if (s.length() == 0)
		{
			return 0;
		} else
		{
			boolean flag = false;
			byte abyte0[] = s.getBytes();
			return abyte0.length;
		}
	}

	public static String deleteChcharacter(String s)
	{
		String s1 = s;
		String s2 = "";
		for (int i = 0; i < s.length(); i++)
		{
			String s3 = s.substring(i, i + 1);
			if (JudgeBytLen(s3) > 1)
				s1 = s1.replace(s3, "");
		}

		return s1;
	}

	public static String DelChinaChar(String s)
	{
		String s1 = s;
		String s2 = "";
		for (int i = 0; i < s.length(); i++)
		{
			String s3 = s.substring(i, i + 1);
			if (JudgeBytLen(s3) > 1)
				s1 = s1.replace(s3, "");
		}

		return s1;
	}

	public static String DelDigitalChar(String s)
	{
		String s1 = s;
		String s2 = "0123456789";
		StringBuffer stringbuffer = new StringBuffer(s2);
		char ac[] = new char[1];
		for (int i = 0; i < stringbuffer.length(); i++)
		{
			char c = stringbuffer.charAt(i);
			ac[0] = c;
			String s3 = new String(ac);
			s1 = s1.replace(s3, "");
		}

		return s1;
	}

	public static ArrayList divideStr(String s, int i)
	{
		ArrayList arraylist = new ArrayList();
		int j = s.length();
		int k = 0;
		for (k = 0; k < (j + 1) / i; k++)
		{
			String s1 = s.substring(k * i, (k + 1) * i);
			arraylist.add(s1);
		}

		k++;
		if (j > (k - 1) * i && j < k * i)
		{
			String s2 = s.substring((k - 1) * i);
			arraylist.add(s2);
		}
		return arraylist;
	}

	public static int getSize(CachedRowSet cachedrowset)
	{
		return cachedrowset != null ? cachedrowset.size() : 0;
	}

	public static void main(String args[])
	{
		System.out.println(round(123.8999999D, 2));
		System.out.println(getFirstDigitalPos("FBA001.001"));
		System.out.println("BL高啊高剪切");
		System.out.println(deleteChcharacter("BL高啊高剪切"));
	}

	public static String DelFeeItem_00(String s)
		throws Exception
	{
		String s1 = "";
		StringBuffer stringbuffer = new StringBuffer();
		String as[] = s.split(",");
		for (int i = 0; i < as.length; i++)
		{
			if (as[i].indexOf(":0.0") != -1)
			{
				as[i] = "";
				continue;
			}
			if (as[i].indexOf(":") != -1)
			{
				String s2 = as[i].substring(0, as[i].indexOf(":"));
				String s3 = as[i].substring(as[i].indexOf(":") + 1);
				s3 = round(s3);
				stringbuffer.append(",").append((new StringBuilder()).append(s2).append(":").append(s3).toString());
			}
		}

		if (stringbuffer.length() > 1)
			s1 = stringbuffer.toString().substring(1);
		return s1;
	}

	HashMap getHpYMonth(String s)
	{
		HashMap hashmap = new HashMap();
		if (!s.equals(""))
		{
			String s1 = s.substring(0, 7);
			String s2 = s1.substring(0, 4);
			String s3 = s1.substring(5, 7);
			String s4 = "";
			String s5 = "";
			int i = Integer.parseInt(s3);
			int j = Integer.parseInt(s2);
			if (i < 8 && i % 2 == 1)
			{
				s4 = (new StringBuilder()).append(s1).append("-").append("01").toString();
				s5 = (new StringBuilder()).append(s1).append("-").append("31").toString();
			} else
			if (i == 2)
			{
				if (j % 4 == 0)
				{
					s4 = (new StringBuilder()).append(s1).append("-").append("01").toString();
					s5 = (new StringBuilder()).append(s1).append("-").append("29").toString();
				} else
				if (j % 4 != 0)
				{
					s4 = (new StringBuilder()).append(s1).append("-").append("01").toString();
					s5 = (new StringBuilder()).append(s1).append("-").append("28").toString();
				}
			} else
			if (i > 2 && i < 8 && i % 2 == 0)
			{
				s4 = (new StringBuilder()).append(s1).append("-").append("01").toString();
				s5 = (new StringBuilder()).append(s1).append("-").append("30").toString();
			} else
			if (i >= 8 && i % 2 == 0)
			{
				s4 = (new StringBuilder()).append(s1).append("-").append("01").toString();
				s5 = (new StringBuilder()).append(s1).append("-").append("31").toString();
			} else
			if (i >= 8 && i % 2 == 1)
			{
				s4 = (new StringBuilder()).append(s1).append("-").append("01").toString();
				s5 = (new StringBuilder()).append(s1).append("-").append("30").toString();
			}
			hashmap.put("wzwrp_monthStart", s4);
			hashmap.put("wzwrp_monthEnd", s5);
		} else
		if (s.equals(""))
		{
			hashmap.put("wzwrp_monthStart", "");
			hashmap.put("wzwrp_monthEnd", "");
		}
		return hashmap;
	}

	public static ArrayList changeAyToAL(String as[])
	{
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < as.length; i++)
			arraylist.add(as[i]);

		return arraylist;
	}
}
