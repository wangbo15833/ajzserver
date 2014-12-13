// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   XMLAnalyst.java

package com.zagerbone.util.xml;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.*;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class XMLAnalyst
{

	public XMLAnalyst()
	{
	}

	public static HashMap getData(HashMap hashmap)
		throws Exception
	{
		SAXBuilder saxbuilder = new SAXBuilder();
		System.out.println("111对数据源信息进行列表显示开始______1");
		Document document = saxbuilder.build(new FileInputStream("D:\\nbyh_lnc\\syscompanyName.xml"));
		System.out.println("111对数据源信息进行列表显示开始______2");
		Element element = document.getRootElement();
		System.out.println("111对数据源信息进行列表显示开始______3");
		List list = element.getChildren();
		int i = 0;
		i = list.size();
		System.out.println((new StringBuilder()).append("size==============").append(i).toString());
		System.out.println("111对数据源信息进行列表显示开始");
		ArrayList arraylist = new ArrayList();
		arraylist.add("companyName");
		arraylist.add("deptName");
		arraylist.add("userName");
		arraylist.add("logoName");
		arraylist.add("helpName");
		HashMap hashmap1 = new HashMap();
		HashMap hashmap2 = new HashMap();
		HashMap hashmap3 = new HashMap();
		HashMap hashmap4 = new HashMap();
		for (int j = 0; j < i; j++)
		{
			Element element1 = (Element)list.get(j);
			ArrayList arraylist1 = new ArrayList();
			for (int k = 0; k < arraylist.size(); k++)
			{
				Element element2 = element1.getChild((String)arraylist.get(k));
				String s = element2.getText();
				System.out.println((new StringBuilder()).append("jndiname_text===================").append(s).toString());
				arraylist1.add(s);
			}

			hashmap1.put((String)arraylist1.get(0), (String)arraylist1.get(1));
			hashmap2.put((String)arraylist1.get(0), (String)arraylist1.get(2));
			hashmap3.put((String)arraylist1.get(0), (String)arraylist1.get(3));
			hashmap4.put((String)arraylist1.get(0), (String)arraylist1.get(4));
		}

		hashmap.put("hp_deptName", hashmap1);
		hashmap.put("hp_userName", hashmap2);
		hashmap.put("hp_logoName", hashmap3);
		hashmap.put("hp_helpName", hashmap4);
		return hashmap;
	}

	public static ArrayList getData(String s, ArrayList arraylist)
		throws Exception
	{
		ArrayList arraylist1 = new ArrayList();
		SAXBuilder saxbuilder = new SAXBuilder();
		Document document = saxbuilder.build(new FileInputStream(s));
		Element element = document.getRootElement();
		List list = element.getChildren();
		int i = 0;
		i = list.size();
		System.out.println((new StringBuilder()).append("size=").append(i).toString());
		for (int j = 0; j < i; j++)
		{
			Element element1 = (Element)list.get(j);
			HashMap hashmap = new HashMap();
			for (int k = 0; k < arraylist.size(); k++)
			{
				Element element2 = element1.getChild((String)arraylist.get(k));
				String s1 = element2.getText();
				System.out.println((new StringBuilder()).append("jndiname_text===================").append(s1).toString());
				hashmap.put((String)arraylist.get(k), s1);
			}

			arraylist1.add(hashmap);
		}

		System.out.println((new StringBuilder()).append("al_result=").append(arraylist1).toString());
		return arraylist1;
	}
}
