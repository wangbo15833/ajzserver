// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DBObject.java

package com.zagerbone.util;

import com.zagerbone.baseobject.BasicObject;
import com.zagerbone.exception.BussinessProcessException;
import com.zagerbone.sys_display.dao.Sys_displayDAOFactory;
import com.zagerbone.sys_display.dao.Sys_displayDAOImpl;
import com.zagerbone.util.data.SysConstant;
import com.zagerbone.util.sql.SQLUpdate;
import com.zagerbone.util.sql.SQLView;
import java.sql.ResultSetMetaData;
import java.util.*;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.util:
//			GeneralDataArray, GeneralApps, DBTrans, MySQL_insert, 
//			MySQL_selCon, MySQL_update, GeneralObject, Query, 
//			SysTrace, Tools

public class DBObject extends BasicObject
{

	Logger log;
	String tableName_list;
	DBTrans transUtil;
	String jndiName;
	String columnid;

	public void info(String s)
	{
		StringBuffer stringbuffer = new StringBuffer("DBObject: ");
		stringbuffer.append(s);
		log.info(stringbuffer.toString());
	}

	public DBObject()
	{
		log = Logger.getLogger(SysTrace.DBLOGGER);
		tableName_list = "";
		transUtil = null;
		jndiName = "";
		columnid = "id";
	}

	public GeneralDataArray getDataArray_DBObj(String s)
		throws BussinessProcessException
	{
		return new GeneralDataArray(s);
	}

	public GeneralDataArray getDataArray_DBObj(String s, String s1)
		throws BussinessProcessException
	{
		if (Tools.nvl(s).equals(""))
			s = s1;
		return new GeneralDataArray(s);
	}

	public GeneralDataArray getDataArrayView_DBObj(String s)
		throws BussinessProcessException
	{
		return new GeneralDataArray(s);
	}

	public GeneralDataArray getDataArrayView_DBObj(String s, String s1)
		throws BussinessProcessException
	{
		if (Tools.nvl(s).equals(""))
			s = s1;
		return new GeneralDataArray(s);
	}

	public GeneralDataArray getTDA_view_DBObj(String s, String s1, String s2)
		throws BussinessProcessException
	{
		if (Tools.nvl(s).equals(""))
			s = s2;
		if (Tools.nvl(s1).equals(""))
			return new GeneralDataArray(s);
		else
			return new GeneralDataArray((new StringBuilder()).append(s).append(",").append(s1).toString());
	}

	public GeneralDataArray getTDA_DBObj(String s, String s1, String s2)
		throws BussinessProcessException
	{
		if (Tools.nvl(s).equals(""))
			s = s2;
		if (Tools.nvl(s1).equals(""))
			return new GeneralDataArray(s);
		else
			return new GeneralDataArray((new StringBuilder()).append(s).append(",").append(s1).toString());
	}

	public GeneralDataArray getGeneralDataArray_view_DBObj(String s, String s1, String s2, String s3)
		throws BussinessProcessException
	{
		if (!Tools.nvl(s2).equals(""))
			s = s2;
		if (!Tools.nvl(s3).equals(""))
			s1 = s3;
		if (Tools.nvl(s1).equals(""))
			return new GeneralDataArray(s);
		else
			return new GeneralDataArray((new StringBuilder()).append(s).append(",").append(s1).toString());
	}

	public GeneralDataArray getGeneralDataArray_DBObj(String s, String s1, String s2, String s3)
		throws BussinessProcessException
	{
		if (!Tools.nvl(s2).equals(""))
			s = s2;
		if (!Tools.nvl(s3).equals(""))
			s1 = s3;
		if (Tools.nvl(s1).equals(""))
			return new GeneralDataArray(s);
		else
			return new GeneralDataArray((new StringBuilder()).append(s).append(",").append(s1).toString());
	}

	public GeneralApps getApps_DBObj(String s, String s1, String s2, String s3)
		throws BussinessProcessException
	{
		if (Tools.nvl(s).equals(""))
			s = s2;
		if (Tools.nvl(s1).equals(""))
			return new GeneralApps(s);
		else
			return new GeneralApps((new StringBuilder()).append(s).append(",").append(s1).toString(), "", s3);
	}

	public GeneralApps getGeneralApps_DBObj(String s, String s1, String s2, String s3)
		throws BussinessProcessException
	{
		if (!Tools.nvl(s2).equals(""))
			s = s2;
		if (Tools.nvl(s1).equals(""))
			return new GeneralApps(s);
		else
			return new GeneralApps((new StringBuilder()).append(s).append(",").append(s1).toString(), "", s3);
	}

	public CachedRowSet getDisp_DBObj(String s)
		throws BussinessProcessException
	{
		CachedRowSet cachedrowset = null;
		DBTrans dbtrans = new DBTrans("", "--获取显示字典,");
		try
		{
			dbtrans.beginTransaction();
			cachedrowset = Sys_displayDAOFactory.getDAO(dbtrans).getDisplayDataByPK("displayName", s);
			cachedrowset.next();
			dbtrans.commitTransaction();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			dbtrans.rollbackTransaction();
		}
		return cachedrowset;
	}

	public String create(DBTrans dbtrans, GeneralObject generalobject, String s)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		StringBuffer stringbuffer = null;
		boolean flag = false;
		MySQL_insert mysql_insert = new MySQL_insert(s, generalobject);
		stringbuffer = mysql_insert.getValue();
		try
		{
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return "result";
	}

	public String create_and_return_id(DBTrans dbtrans, GeneralObject generalobject, String s)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		String s1 = null;
		Object obj = null;
		StringBuffer stringbuffer = null;
		boolean flag = false;
		MySQL_insert mysql_insert = new MySQL_insert(s, generalobject);
		stringbuffer = mysql_insert.getValue_and_id();
		try
		{
			log.info(stringbuffer.toString());
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null)
				throw new BussinessProcessException("No record!");
			cachedrowset.next();
			s1 = cachedrowset.getString("id");
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s1;
	}

	public CachedRowSet find(DBTrans dbtrans, Query query, String s, String s1)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		CachedRowSet cachedrowset = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		MySQL_selCon mysql_selcon = new MySQL_selCon(hashmap);
		StringBuffer stringbuffer = mysql_selcon.getValue();
		StringBuffer stringbuffer1 = (new StringBuffer("select * from ")).append(s).append(" where 1=1 ");
		StringBuffer stringbuffer2 = (new StringBuffer("select * from ")).append(s).append(" where 1=1 ");
		String s2 = query.getWhereConExpr();
		stringbuffer1.append(s2);
		stringbuffer2.append(s2);
		try
		{
			if (query.getTotalNum() == 0)
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer1.append(stringbuffer.toString()).toString()));
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			if (query.getPageType().equals(""))
			{
				stringbuffer2.append(stringbuffer.toString());
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString(), query);
			} else
			{
				stringbuffer2.append(stringbuffer.toString());
				log.info((new StringBuilder()).append("不分页条件:").append(stringbuffer2.toString()).toString());
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString());
			}
			if (cachedrowset == null)
				throw new BussinessProcessException("No record!");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return cachedrowset;
	}

	public CachedRowSet findByTables(DBTrans dbtrans, Query query, String s, String s1, String s2)
		throws BussinessProcessException
	{
		CachedRowSet cachedrowset = null;
		try
		{
			if (dbtrans == null)
				dbtrans = new DBTrans();
			HashMap hashmap = (HashMap)query.getConditionBody();
			info((new StringBuilder()).append("body").append(hashmap).toString());
			StringBuffer stringbuffer = null;
			stringbuffer = new StringBuffer();
			SQLView sqlview = new SQLView();
			HashMap hashmap1 = sqlview.createView_sql(dbtrans, s, hashmap, s2);
			String s3 = (String)hashmap1.get("mainSQL");
			s2 = (String)hashmap1.get("orderBySQL");
			stringbuffer.append(s3);
			info(stringbuffer.toString());
			try
			{
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
				if (SysConstant.Print_DBObject_getRecNumBySelect_strSql.equals("1"))
					info("获取记录集合数******:调用DBTrans中的方法getRecNumBySelect");
				if (query.getCurrentPageNum() <= 0)
					query.setCurrentPageNum(1);
				stringbuffer.append(s2);
				if (query.getPageType().equals(""))
					cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString(), query);
				else
					cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
				if (cachedrowset == null)
					throw new BussinessProcessException("No record!");
			}
			catch (Exception exception1)
			{
				exception1.printStackTrace();
				throw new BussinessProcessException(exception1.getMessage());
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return cachedrowset;
	}

	public CachedRowSet find(DBTrans dbtrans, Query query, String s, String s1, String s2)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		String s3 = "";
		if (!s2.equals(""))
			s3 = (new StringBuilder()).append(" order by ").append(s2).toString();
		CachedRowSet cachedrowset = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		info((new StringBuilder()).append("body").append(hashmap).toString());
		MySQL_selCon mysql_selcon = new MySQL_selCon(hashmap);
		StringBuffer stringbuffer = mysql_selcon.getValue();
		StringBuffer stringbuffer1 = (new StringBuffer("select * from ")).append(s).append(" where 1=1 ");
		StringBuffer stringbuffer2 = (new StringBuffer("select * from ")).append(s).append(" where 1=1 ");
		String s4 = query.getWhereConExpr();
		stringbuffer1.append(s4);
		stringbuffer2.append(s4);
		try
		{
			query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer1.append(stringbuffer.toString()).toString()));
			if (SysConstant.Print_DBObject_getRecNumBySelect_strSql.equals("1"))
				info("获取记录集合数******:调用DBTrans中的方法getRecNumBySelect");
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			if (query.getPageType().equals(""))
			{
				stringbuffer2.append(stringbuffer.toString());
				stringbuffer2.append(s3);
				info((new StringBuilder()).append("获取记录集合,分页SQL:").append(stringbuffer2.toString()).append(",调用DBTrans中的getResultBySelect").toString());
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString(), query);
			} else
			{
				stringbuffer2.append(stringbuffer.toString());
				stringbuffer2.append(s3);
				info((new StringBuilder()).append("获取记录集合,不分页SQL:").append(stringbuffer2.toString()).toString());
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString());
			}
			if (cachedrowset == null)
				throw new BussinessProcessException("No record!");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return cachedrowset;
	}

	public CachedRowSet find(DBTrans dbtrans, Query query, String s, String s1, String s2, boolean flag)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		String s3 = "";
		if (!s2.equals(""))
			s3 = (new StringBuilder()).append(" order by ").append(s2).toString();
		CachedRowSet cachedrowset = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		MySQL_selCon mysql_selcon = new MySQL_selCon(hashmap);
		StringBuffer stringbuffer = mysql_selcon.getValue();
		StringBuffer stringbuffer1 = (new StringBuffer("select * from ")).append(s).append(" where 1=1 ");
		StringBuffer stringbuffer2 = (new StringBuffer("select * from ")).append(s).append(" where 1=1 ");
		String s4 = query.getWhereConExpr();
		stringbuffer1.append(s4);
		stringbuffer2.append(s4);
		try
		{
			if (query.getTotalNum() == 0)
			{
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer1.append(stringbuffer.toString()).toString()));
				if (SysConstant.Print_DBObject_getRecNumBySelect_strSql.equals("1"))
					info("获取记录集合数******:调用DBTrans中的方法getRecNumBySelect");
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			if (query.getPageType().equals(""))
			{
				stringbuffer2.append(stringbuffer.toString());
				stringbuffer2.append(s3);
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString(), query);
			} else
			{
				stringbuffer2.append(stringbuffer.toString());
				stringbuffer2.append(s3);
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString());
			}
			if (cachedrowset == null)
				throw new BussinessProcessException("No record!");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return cachedrowset;
	}

	public CachedRowSet find(DBTrans dbtrans, Query query, String s, String s1, String s2, String s3)
		throws BussinessProcessException
	{
		info("开始进入......");
		if (dbtrans == null)
			dbtrans = new DBTrans();
		String s4 = "";
		if (!s2.equals(""))
			s4 = (new StringBuilder()).append(" order by ").append(s2).toString();
		CachedRowSet cachedrowset = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		MySQL_selCon mysql_selcon = new MySQL_selCon(hashmap);
		StringBuffer stringbuffer = mysql_selcon.getValue();
		StringBuffer stringbuffer1 = (new StringBuffer("select * from ")).append(s).append(" where 1=1 ");
		StringBuffer stringbuffer2 = (new StringBuffer("select * from ")).append(s).append(" where 1=1 ");
		String s5 = query.getWhereConExpr();
		stringbuffer1.append(s5);
		stringbuffer2.append(s5);
		try
		{
			if (query.getTotalNum() == 0)
			{
				if (!s3.equals(""))
					query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer1.append(stringbuffer.toString()).append(" and ").append(s3).toString()));
				else
					query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer1.append(stringbuffer.toString()).toString()));
				if (SysConstant.Print_DBObject_getRecNumBySelect_strSql.equals("1"))
					info("获取记录集合数******:调用DBTrans中的方法getRecNumBySelect");
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			if (query.getPageType().equals(""))
			{
				stringbuffer2.append(stringbuffer.toString());
				if (!s3.equals(""))
					stringbuffer2.append(" and ").append(s3);
				stringbuffer2.append(s4);
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString(), query);
			} else
			{
				stringbuffer2.append(stringbuffer.toString());
				if (!s3.equals(""))
					stringbuffer2.append(" and ").append(s3);
				stringbuffer2.append(s4);
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString());
			}
			if (cachedrowset == null)
				throw new BussinessProcessException("No record!");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		info("完成退出......");
		return cachedrowset;
	}

	public CachedRowSet findIds(DBTrans dbtrans, Query query, String s, String s1, String s2)
		throws BussinessProcessException
	{
		info("find(DBTrans transUtil, Query queryCon, String tableName, String columnid, String orderCon_temp),开始进入......");
		if (dbtrans == null)
			dbtrans = new DBTrans();
		String s3 = (new StringBuilder()).append(" order by ").append(s2).toString();
		CachedRowSet cachedrowset = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		MySQL_selCon mysql_selcon = new MySQL_selCon(hashmap);
		StringBuffer stringbuffer = mysql_selcon.getValue();
		StringBuffer stringbuffer1 = (new StringBuffer("select id from ")).append(s).append(" where 1=1 ");
		StringBuffer stringbuffer2 = (new StringBuffer("select id from ")).append(s).append(" where 1=1 ");
		String s4 = query.getWhereConExpr();
		stringbuffer1.append(s4);
		stringbuffer2.append(s4);
		try
		{
			if (query.getTotalNum() == 0)
			{
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer1.append(stringbuffer.toString()).toString()));
				if (SysConstant.Print_DBObject_getRecNumBySelect_strSql.equals("1"))
					info("获取记录集合数******:调用DBTrans中的方法getRecNumBySelect");
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			if (query.getPageType().equals(""))
			{
				stringbuffer2.append(stringbuffer.toString());
				stringbuffer2.append(s3);
				info((new StringBuilder()).append("获取记录集合,分页SQL:").append(stringbuffer2.toString()).append(",调用DBTrans中的getResultBySelect").toString());
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString(), query);
			} else
			{
				stringbuffer2.append(stringbuffer.toString());
				stringbuffer2.append(s3);
				info((new StringBuilder()).append("获取记录集合,不分页SQL:").append(stringbuffer2.toString()).toString());
				cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString());
			}
			if (cachedrowset == null)
				throw new BussinessProcessException("No record!");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		info("find(DBTrans transUtil, Query queryCon, String tableName, String columnid, String orderCon_temp),完成退出......");
		return cachedrowset;
	}

	public CachedRowSet findColumn(DBTrans dbtrans, String s, String s1)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		CachedRowSet cachedrowset = null;
		StringBuffer stringbuffer = (new StringBuffer("select ")).append(s1).append(" from ").append(s).append(" where 1=1 ");
		log.info((new StringBuilder()).append("sbr_sql:").append(stringbuffer.toString()).toString());
		try
		{
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null)
				throw new BussinessProcessException("No find!");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return cachedrowset;
	}

	public String modify(DBTrans dbtrans, String s, String s1, GeneralObject generalobject, HashMap hashmap)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		String s2 = "";
		String s3 = "";
		Set set = hashmap.entrySet();
		Iterator iterator = set.iterator();
		Object obj = null;
		if (iterator.hasNext())
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			s2 = (String)entry.getKey();
			s3 = (String)entry.getValue();
		}
		MySQL_update mysql_update = new MySQL_update(s, s2, s3, generalobject);
		StringBuffer stringbuffer = mysql_update.getValue();
		log.info(stringbuffer.toString());
		try
		{
			dbtrans.addSql(stringbuffer.toString());
			dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return (String)hashmap.get(s1);
	}

	public String modify(DBTrans dbtrans, String s, String s1, GeneralObject generalobject, HashMap hashmap, ArrayList arraylist)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		String s2 = "";
		String s3 = "";
		Set set = hashmap.entrySet();
		Iterator iterator = set.iterator();
		Object obj = null;
		if (iterator.hasNext())
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			s2 = (String)entry.getKey();
			s3 = (String)entry.getValue();
		}
		SQLUpdate sqlupdate = new SQLUpdate(s, s2, s3, generalobject, arraylist);
		StringBuffer stringbuffer = sqlupdate.getValue();
		log.info(stringbuffer.toString());
		try
		{
			dbtrans.addSql(stringbuffer.toString());
			dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return (String)hashmap.get(s1);
	}

	public String modify_hp_condition(DBTrans dbtrans, String s, String s1, GeneralObject generalobject, HashMap hashmap)
		throws BussinessProcessException
	{
		log.info("modify_hp_condition========================================");
		if (dbtrans == null)
			dbtrans = new DBTrans();
		String s2 = "";
		String s4 = "";
		Set set = hashmap.entrySet();
		Iterator iterator = set.iterator();
		Object obj = null;
		if (iterator.hasNext())
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			String s3 = (String)entry.getKey();
			String s5 = (String)entry.getValue();
		}
		MySQL_update mysql_update = new MySQL_update(s, hashmap, generalobject);
		StringBuffer stringbuffer = mysql_update.getValue_hp_condition(hashmap);
		log.info(stringbuffer.toString());
		try
		{
			dbtrans.addSql(stringbuffer.toString());
			dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return (String)hashmap.get(s1);
	}

	public GeneralObject findById(DBTrans dbtrans, String s, String s1)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		Object obj = null;
		GeneralObject generalobject = new GeneralObject();
		StringBuffer stringbuffer = (new StringBuffer("select * from ")).append(s).append(" where ").append("id").append(" = '");
		stringbuffer.append(s1).append("'");
		log.info("-------------------------------------------------------------------------");
		log.info(stringbuffer.toString());
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			log.info(cachedrowset);
			log.info(Integer.valueOf(cachedrowset.size()));
			if (cachedrowset == null || cachedrowset.size() == 0)
			{
				log.info("-------------");
				throw new BussinessProcessException("this peopleInfo doesnot existed!");
			}
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				for (int j = 1; j <= i; j++)
					generalobject.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));

			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return generalobject;
	}

	public GeneralObject findById(DBTrans dbtrans, String s, String s1, String s2)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		Object obj = null;
		GeneralObject generalobject = new GeneralObject();
		StringBuffer stringbuffer = (new StringBuffer("select * from ")).append(s).append(" where ").append(s1).append(" = '");
		stringbuffer.append(s2).append("'");
		log.info("-------------------------------------------------------------------------");
		log.info(stringbuffer.toString());
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
				throw new BussinessProcessException("this peopleInfo doesnot existed!");
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				for (int j = 1; j <= i; j++)
					generalobject.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));

			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return generalobject;
	}

	public String delete(DBTrans dbtrans, String s, String s1)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		StringBuffer stringbuffer = (new StringBuffer("delete from ")).append(s).append(" where id = '");
		stringbuffer.append(s1).append("'");
		log.info(stringbuffer.toString());
		try
		{
			dbtrans.addSql(stringbuffer.toString());
			dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s1;
	}

	public String delete(DBTrans dbtrans, String s, HashMap hashmap)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		StringBuffer stringbuffer = (new StringBuffer("delete from ")).append(s).append(" where 1 = 1 ");
		Set set = hashmap.entrySet();
		Iterator iterator = set.iterator();
		Object obj = null;
		String s2;
		for (; iterator.hasNext(); stringbuffer.append(s2).append("'"))
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			String s1 = (String)entry.getKey();
			s2 = (String)entry.getValue();
			stringbuffer.append(" and ").append(s1).append("= '");
		}

		try
		{
			dbtrans.addSql(stringbuffer.toString());
			dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return "id";
	}

	public String delete(DBTrans dbtrans, String s, String s1, String s2)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		StringBuffer stringbuffer = (new StringBuffer("delete from ")).append(s).append(" where ").append(s1).append("= '");
		stringbuffer.append(s2).append("'");
		log.info(stringbuffer.toString());
		try
		{
			dbtrans.addSql(stringbuffer.toString());
			dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s2;
	}

	public String execBySQL(DBTrans dbtrans, String s)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		boolean flag = false;
		try
		{
			dbtrans.addSql(s);
			boolean flag1 = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		String s1 = "result";
		return s1;
	}

	public String execBySQL_noBatch(DBTrans dbtrans, String s)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		boolean flag = false;
		try
		{
			dbtrans.addSql(s);
			boolean flag1 = dbtrans.executeSql_noBatch();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		String s1 = "result";
		return s1;
	}

	public CachedRowSet findBySQL(DBTrans dbtrans, String s)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		CachedRowSet cachedrowset = null;
		try
		{
			cachedrowset = dbtrans.getResultBySelect(s);
			if (cachedrowset == null)
				throw new BussinessProcessException("没有查到结果！");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return cachedrowset;
	}

	public CachedRowSet findBySQL(DBTrans dbtrans, String s, String s1)
		throws BussinessProcessException
	{
		if (dbtrans == null)
		{
			log.info("No Connection!");
			dbtrans = new DBTrans();
		}
		CachedRowSet cachedrowset = null;
		if (!s1.equals("no"))
			log.info(s);
		try
		{
			cachedrowset = dbtrans.getResultBySelect(s);
			if (cachedrowset == null)
				throw new BussinessProcessException("没有查到结果！");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return cachedrowset;
	}

	public CachedRowSet findBySQL(DBTrans dbtrans, Query query, String s)
		throws BussinessProcessException
	{
		if (dbtrans == null)
		{
			log.info("No Connection!");
			dbtrans = new DBTrans();
		}
		CachedRowSet cachedrowset = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		StringBuffer stringbuffer = new StringBuffer();
		MySQL_selCon mysql_selcon = new MySQL_selCon(hashmap);
		stringbuffer = mysql_selcon.getValue();
		s = (new StringBuilder()).append(s).append(stringbuffer.toString()).toString();
		log.info(s);
		try
		{
			if (query.getTotalNum() == 0)
				query.setTotalNum(dbtrans.getRecNumBySelect(s.toString()));
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			cachedrowset = dbtrans.getResultBySelect(s, query);
			if (cachedrowset == null)
				throw new BussinessProcessException("没有查到结果！");
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return cachedrowset;
	}

	public String maxNo(DBTrans dbtrans, String s, String s1, String s2)
		throws BussinessProcessException
	{
		String s3 = s1;
		String s4 = null;
		String s5 = (new StringBuilder()).append("select max(").append(s2).append(") as ").append(s2).append(" from ").append(s).append(" where ").append(s2).append(" like '").append(s3).append("%'").toString();
		CachedRowSet cachedrowset = findBySQL(dbtrans, s5);
		try
		{
			cachedrowset.next();
			s4 = Tools.nvl(cachedrowset.getString(s2)).trim();
			if (s4.equals(""))
			{
				s4 = (new StringBuilder()).append(s3).append("00001").toString();
			} else
			{
				int i = Integer.parseInt(s4.substring(s4.length() - 5));
				s4 = (new StringBuilder()).append(s3).append(String.format("%05d", new Object[] {
					Integer.valueOf(i + 1)
				})).toString();
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s4;
	}

	public String maxBillNo(DBTrans dbtrans, String s, String s1, String s2)
		throws BussinessProcessException
	{
		String s3 = Tools.getCurrentDate().replace("-", "");
		String s4 = (new StringBuilder()).append(s1).append(s3).toString();
		String s5 = null;
		String s6 = (new StringBuilder()).append("select max(").append(s2).append(") as ").append(s2).append(" from ").append(s).append(" where ").append(s2).append(" like '").append(s4).append("%'").toString();
		CachedRowSet cachedrowset = findBySQL(dbtrans, s6);
		try
		{
			cachedrowset.next();
			s5 = Tools.nvl(cachedrowset.getString(s2)).trim();
			if (s5.equals(""))
			{
				s5 = (new StringBuilder()).append(s4).append("00001").toString();
			} else
			{
				int i = Integer.parseInt(s5.substring(s5.length() - 5));
				s5 = (new StringBuilder()).append(s4).append(String.format("%05d", new Object[] {
					Integer.valueOf(i + 1)
				})).toString();
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s5;
	}

	public String maxLine(DBTrans dbtrans, String s, String s1, String s2)
		throws BussinessProcessException
	{
		String s3 = null;
		String s4 = (new StringBuilder()).append("select max(Line)+1 as Line from ").append(s).append(" where ").append(s1).append("='").append(s2).append("'").toString();
		CachedRowSet cachedrowset = findBySQL(dbtrans, s4);
		try
		{
			cachedrowset.next();
			s3 = Tools.nvl(cachedrowset.getString("Line"));
			if (s3.equals(""))
				s3 = "1";
			int i = Integer.parseInt(s3);
			s3 = String.format("%04d", new Object[] {
				Integer.valueOf(i)
			});
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s3;
	}

	public void updateString(DBTrans dbtrans, String s, String s1, CachedRowSet cachedrowset, String s2, String s3, String s4)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		try
		{
			CachedRowSet cachedrowset1;
			for (; cachedrowset.next(); cachedrowset.updateString(s2, Tools.nvl(cachedrowset1.getString(s4))))
			{
				Query query = new Query();
				HashMap hashmap = new HashMap();
				hashmap.put(s3, Tools.nvl(cachedrowset.getString(s2)));
				query.setConditionBody(hashmap);
				cachedrowset1 = find(dbtrans, query, s, s1);
				cachedrowset1.next();
			}

			cachedrowset.beforeFirst();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
	}

	public CachedRowSet find(DBTrans dbtrans, String s, HashMap hashmap)
		throws BussinessProcessException
	{
		if (dbtrans == null)
			dbtrans = new DBTrans();
		CachedRowSet cachedrowset = null;
		StringBuffer stringbuffer = (new StringBuffer("select * from ")).append(s).append(" where 1=1 ");
		Set set = hashmap.entrySet();
		Iterator iterator = set.iterator();
		Object obj = null;
		StringBuffer stringbuffer1 = new StringBuffer("");
		try
		{
			java.util.Map.Entry entry;
			for (; iterator.hasNext(); stringbuffer1.append(" and ").append((String)entry.getKey()).append("='").append((String)entry.getValue()).append("'"))
				entry = (java.util.Map.Entry)iterator.next();

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		stringbuffer.append(stringbuffer1.toString());
		log.info((new StringBuilder()).append("sbr_sql:").append(stringbuffer.toString()).toString());
		try
		{
			log.info(stringbuffer.toString());
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null)
				throw new BussinessProcessException("没有记录");
		}
		catch (Exception exception1)
		{
			exception1.printStackTrace();
			throw new BussinessProcessException(exception1.getMessage());
		}
		return cachedrowset;
	}
}
