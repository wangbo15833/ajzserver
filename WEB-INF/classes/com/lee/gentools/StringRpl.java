// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   StringRpl.java

package com.lee.gentools;

import java.io.*;

public class StringRpl
{

	public static String read(File file)
	{
		StringBuffer stringbuffer = new StringBuffer();
		Object obj = null;
		try
		{
			BufferedReader bufferedreader = new BufferedReader(new FileReader(file));
			String s;
			while ((s = bufferedreader.readLine()) != null) 
				stringbuffer.append((new StringBuilder()).append(s).append("\n").toString());
			bufferedreader.close();
		}
		catch (FileNotFoundException filenotfoundexception)
		{
			filenotfoundexception.printStackTrace();
		}
		catch (IOException ioexception)
		{
			ioexception.printStackTrace();
		}
		return stringbuffer.toString();
	}

	public static boolean write(String s, File file)
	{
		BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
		bufferedwriter.write(s);
		bufferedwriter.flush();
		bufferedwriter.close();
		return true;
		IOException ioexception;
		ioexception;
		ioexception.printStackTrace();
		return false;
	}

	public StringRpl()
	{
	}

	public static void rpl(String s, String s1, String s2, String s3)
	{
		File file = new File(s);
		File file1 = new File(s1);
		String s4 = read(file);
		System.out.println(s4);
		s4 = s4.replaceAll(s2, s3);
		System.out.println(write(s4, file1));
	}
}
