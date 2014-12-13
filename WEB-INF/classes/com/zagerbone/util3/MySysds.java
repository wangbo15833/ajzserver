// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MySysds.java

package com.zagerbone.util3;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class MySysds
{

	public MySysds()
	{
	}

	public static void main(String args[])
		throws Exception
	{
		SAXBuilder saxbuilder = new SAXBuilder();
		Document document = saxbuilder.build(new FileInputStream("C:\\System_data\\sysds.xml"));
		Element element = document.getRootElement();
		List list = element.getChildren();
		HashMap hashmap = new HashMap();
		int i = 0;
		i = list.size();
		System.out.println((new StringBuilder()).append("size==============").append(i).toString());
		for (int j = 0; j < i; j++)
		{
			Element element1 = (Element)list.get(j);
			Element element2 = element1.getChild("jndiname");
			String s = element2.getText();
			Element element3 = element1.getChild("dsname");
			String s1 = element3.getText();
			Element element4 = element1.getChild("dstype");
			String s2 = element4.getText();
			Element element5 = element1.getChild("dspswd");
			String s3 = element5.getText();
		}

		System.out.println("getDataSources===================");
		getDataSources(hashmap);
	}

	public static HashMap getDataSources()
		throws Exception
	{
		SAXBuilder saxbuilder = new SAXBuilder();
		Document document = saxbuilder.build(new FileInputStream("C:\\System_data\\sysds.xml"));
		Element element = document.getRootElement();
		List list = element.getChildren();
		HashMap hashmap = new HashMap();
		int i = 0;
		i = list.size();
		for (int j = 0; j < i; j++)
		{
			Element element1 = (Element)list.get(j);
			Element element2 = element1.getChild("jndiname");
			String s = element2.getText();
			Element element3 = element1.getChild("dsname");
			String s1 = element3.getText();
			Element element4 = element1.getChild("dstype");
			String s2 = element4.getText();
			Element element5 = element1.getChild("dspswd");
			String s3 = element5.getText();
			hashmap.put(s, s1);
		}

		return hashmap;
	}

	public static HashMap getDataSources(HashMap hashmap)
		throws Exception
	{
		SAXBuilder saxbuilder = new SAXBuilder();
		Document document = saxbuilder.build(new FileInputStream("C:\\System_data\\sysds.xml"));
		Element element = document.getRootElement();
		List list = element.getChildren();
		int i = 0;
		i = list.size();
		System.out.println("111对数据源信息进行列表显示开始");
		for (int j = 0; j < i; j++)
		{
			Element element1 = (Element)list.get(j);
			Element element2 = element1.getChild("jndiname");
			String s = element2.getText();
			Element element3 = element1.getChild("dsname");
			String s1 = element3.getText();
			Element element4 = element1.getChild("dstype");
			String s2 = element4.getText();
			Element element5 = element1.getChild("dspswd");
			String s3 = element5.getText();
			hashmap.put(s, s1);
		}

		return hashmap;
	}

	public static void getDataSources(HashMap hashmap, HashMap hashmap1)
		throws Exception
	{
		SAXBuilder saxbuilder = new SAXBuilder();
		Document document = saxbuilder.build(new FileInputStream("C:\\System_data\\sysds.xml"));
		Element element = document.getRootElement();
		List list = element.getChildren();
		int i = 0;
		i = list.size();
		for (int j = 0; j < i; j++)
		{
			Element element1 = (Element)list.get(j);
			Element element2 = element1.getChild("jndiname");
			String s = element2.getText();
			Element element3 = element1.getChild("dsname");
			String s1 = element3.getText();
			Element element4 = element1.getChild("dstype");
			String s2 = element4.getText();
			Element element5 = element1.getChild("dspswd");
			String s3 = element5.getText();
			hashmap.put(s, s1);
			hashmap1.put(s, s2);
		}

	}

	public static void getDataSources(HashMap hashmap, HashMap hashmap1, HashMap hashmap2)
		throws Exception
	{
		SAXBuilder saxbuilder = new SAXBuilder();
		Document document = saxbuilder.build(new FileInputStream("C:\\System_data\\sysds.xml"));
		Element element = document.getRootElement();
		List list = element.getChildren();
		int i = 0;
		i = list.size();
		System.out.println("333对数据源信息进行列表显示开始");
		for (int j = 0; j < i; j++)
		{
			Element element1 = (Element)list.get(j);
			Element element2 = element1.getChild("jndiname");
			String s = element2.getText();
			Element element3 = element1.getChild("dsname");
			String s1 = element3.getText();
			Element element4 = element1.getChild("dstype");
			String s2 = element4.getText();
			Element element5 = element1.getChild("dspswd");
			String s3 = element5.getText();
			hashmap.put(s, s1);
			hashmap1.put(s, s2);
			hashmap2.put(s, s3);
		}

		System.out.println("对数据源信息进行列表显示结束");
	}
}
