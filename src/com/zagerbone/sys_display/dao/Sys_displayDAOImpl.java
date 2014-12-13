// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sys_displayDAOImpl.java

package com.zagerbone.sys_display.dao;

import com.zagerbone.exception.BussinessProcessException;
import com.zagerbone.util.*;
import java.util.*;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.sys_display.dao:
//			Sys_displayDAO

public class Sys_displayDAOImpl extends DBObject
	implements Sys_displayDAO
{

	String tableName;
	String tableNameView;
	DBTrans transUtil;
	String jndiName;
	String columnid;

	public Sys_displayDAOImpl()
	{
		tableName = "sys_display";
		tableNameView = "sys_display";
		transUtil = null;
		jndiName = "";
		columnid = "id";
	}

	public Sys_displayDAOImpl(String s)
	{
		tableName = "sys_display";
		tableNameView = "sys_display";
		transUtil = null;
		jndiName = "";
		columnid = "id";
		jndiName = s;
	}

	public Sys_displayDAOImpl(DBTrans dbtrans)
	{
		tableName = "sys_display";
		tableNameView = "sys_display";
		transUtil = null;
		jndiName = "";
		columnid = "id";
		transUtil = dbtrans;
	}

	public GeneralDataArray getDataArray()
		throws BussinessProcessException
	{
		return new GeneralDataArray(tableName);
	}

	public GeneralDataArray getDataArray(String s)
		throws BussinessProcessException
	{
		if (Tools.nvl(s).equals(""))
			s = tableName;
		return new GeneralDataArray(s);
	}

	public GeneralDataArray getDataArrayView()
		throws BussinessProcessException
	{
		return new GeneralDataArray(tableNameView);
	}

	public GeneralDataArray getDataArrayView(String s)
		throws BussinessProcessException
	{
		if (Tools.nvl(s).equals(""))
			s = tableNameView;
		return new GeneralDataArray(s);
	}

	public String create(GeneralObject generalobject)
		throws BussinessProcessException
	{
		return create(transUtil, generalobject, tableName);
	}

	public CachedRowSet find(Query query)
		throws BussinessProcessException
	{
		return find(transUtil, query, tableName, columnid);
	}

	public CachedRowSet findView(Query query)
		throws BussinessProcessException
	{
		return find(transUtil, query, tableNameView, columnid, "displayName");
	}

	public CachedRowSet findColumn(String s)
		throws BussinessProcessException
	{
		return findColumn(transUtil, tableName, s);
	}

	public GeneralObject findById(String s)
		throws BussinessProcessException
	{
		return findById(transUtil, tableName, s);
	}

	public String modify(GeneralObject generalobject, HashMap hashmap)
		throws BussinessProcessException
	{
		return modify(transUtil, tableName, columnid, generalobject, hashmap);
	}

	public String delete(String s)
		throws BussinessProcessException
	{
		return delete(transUtil, tableName, s);
	}

	public String setDataBySQL(String s)
		throws BussinessProcessException
	{
		return execBySQL(transUtil, s);
	}

	public CachedRowSet getDataBySQL(String s)
		throws BussinessProcessException
	{
		return findBySQL(transUtil, s);
	}

	public CachedRowSet getDataBySQL(Query query, String s)
		throws BussinessProcessException
	{
		return findBySQL(transUtil, query, s);
	}

	public String maxNo(String s, String s1)
		throws BussinessProcessException
	{
		return maxNo(transUtil, tableName, s, s1);
	}

	public String maxBillNo(String s, String s1)
		throws BussinessProcessException
	{
		return maxBillNo(transUtil, tableName, s, s1);
	}

	public String maxLine(String s, String s1)
		throws BussinessProcessException
	{
		return maxLine(transUtil, tableName, s, s1);
	}

	public void updateString(CachedRowSet cachedrowset, String s, String s1, String s2)
		throws BussinessProcessException
	{
		updateString(transUtil, tableName, columnid, cachedrowset, s, s1, s2);
	}

	public CachedRowSet getDataByPK(HashMap hashmap)
		throws BussinessProcessException
	{
		CachedRowSet cachedrowset = null;
		try
		{
			String s = (new StringBuilder()).append("select * from ").append(tableName).append(" where 1=1 ").toString();
			StringBuffer stringbuffer = new StringBuffer();
			Set set = hashmap.entrySet();
			Iterator iterator = set.iterator();
			Object obj = null;
			do
			{
				if (!iterator.hasNext())
					break;
				java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
				if ((String)entry.getValue() != null && !((String)entry.getValue()).equals(""))
				{
					stringbuffer.append(" and ").append((String)entry.getKey()).append("='");
					stringbuffer.append((String)entry.getValue()).append("'");
				}
			} while (true);
			s = (new StringBuilder()).append(s).append(stringbuffer.toString()).toString();
			cachedrowset = findBySQL(transUtil, s);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			String s1 = (new StringBuilder()).append("select top 1 * from ").append(tableName).toString();
			try
			{
				cachedrowset = findBySQL(transUtil, s1);
				GeneralDataArray generaldataarray = new GeneralDataArray(tableName);
				for (int i = 1; i < generaldataarray.col_array.length; i++)
				{
					cachedrowset.updateString("displayName", (String)hashmap.get("displayName"));
					cachedrowset.updateString("menu_height", "25px");
					cachedrowset.updateString("list_inner_height", "252px");
					cachedrowset.updateString("list_height", "285px");
					cachedrowset.updateString("edit_height", "305px");
					cachedrowset.updateString("query_height", "285px");
					cachedrowset.updateString("div_list_width", "101%");
					cachedrowset.updateString("div_menu_height", "31px");
					cachedrowset.updateString("div_leq_height", "500px");
				}

			}
			catch (Exception exception1)
			{
				exception1.printStackTrace();
			}
		}
		return cachedrowset;
	}

	public CachedRowSet getDisplayDataByPK(String s, String s1)
		throws BussinessProcessException
	{
		CachedRowSet cachedrowset = null;
		HashMap hashmap = new HashMap();
		hashmap.put(s, s1);
		cachedrowset = getDataByPK(hashmap);
		return cachedrowset;
	}
}
