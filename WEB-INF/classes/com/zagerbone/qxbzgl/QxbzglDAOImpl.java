// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QxbzglDAOImpl.java

package com.zagerbone.qxbzgl;

import com.shenghao.arch.baseobject.BasicObject;
import com.shenghao.arch.exception.BussinessProcessException;
import com.shenghao.arch.util.DBTransUtil;
import com.shenghao.arch.util.Tools;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.Query;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.qxbzgl:
//			Qxbzgl, QxbzglDAO

public class QxbzglDAOImpl extends BasicObject
	implements QxbzglDAO
{

	public QxbzglDAOImpl()
	{
	}

	public String createQxbzgl(Qxbzgl qxbzgl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		String s = qxbzgl.getProp("fsr");
		Object obj = null;
		try
		{
			StringBuffer stringbuffer = new StringBuffer("insert into TCParaCureStand(");
			stringbuffer.append("parameteName,parameteChName,configYPerValue,configYMinValue,configYValueCount");
			stringbuffer.append(")values(");
			stringbuffer.append("'").append(qxbzgl.getProp("parameteName")).append("',");
			stringbuffer.append("'").append(qxbzgl.getProp("parameteChName")).append("',");
			stringbuffer.append("'").append(qxbzgl.getProp("configYPerValue")).append("',");
			stringbuffer.append("'").append(qxbzgl.getProp("configYMinValue")).append("',");
			stringbuffer.append("'").append(qxbzgl.getProp("configYValueCount")).append("')");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return s;
	}

	public String publishPersonalQxbzgl(Qxbzgl qxbzgl)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		boolean flag = false;
		String s = qxbzgl.getProp("jsr");
		String as[] = Tools.stringCode(s);
		Object obj = null;
		try
		{
			for (int i = 0; i < as.length; i++)
			{
				StringBuffer stringbuffer = new StringBuffer("insert into TCParaCureStand(dataCreateTime,Cname, Ctype, Cnum, Cmake, Couttime, Cjtime, Ctestmb, Ctestdh, Tnum, Tyqxh, Tyqnum, Tstime, Ttime, Tmeb, Tmebdh) values(getDate(),'");
				stringbuffer.append("'").append(qxbzgl.getProp("Cname")).append("',");
				stringbuffer.append(qxbzgl.getProp("Ctype")).append("',");
				stringbuffer.append(qxbzgl.getProp("Cmake")).append("',");
				stringbuffer.append(qxbzgl.getProp("Couttime")).append("',");
				stringbuffer.append(qxbzgl.getProp("Cjtime")).append("',");
				stringbuffer.append(qxbzgl.getProp("Ctestmb")).append("',");
				stringbuffer.append(qxbzgl.getProp("Ctestdh")).append("',");
				stringbuffer.append(qxbzgl.getProp("Tnum")).append("',");
				stringbuffer.append(qxbzgl.getProp("Tyqxh")).append("',");
				stringbuffer.append(qxbzgl.getProp("Tyqnum")).append("',");
				stringbuffer.append(qxbzgl.getProp("Tstime")).append("',");
				stringbuffer.append(qxbzgl.getProp("Tmeb")).append("',");
				stringbuffer.append(qxbzgl.getProp("Tmebdh")).append("')");
				stringbuffer.append("0").append("')");
				log.info(stringbuffer.toString());
				dbtransutil.addSql(stringbuffer.toString());
			}

			boolean flag1 = dbtransutil.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return qxbzgl.getProp("fsr");
	}

	public Query findQxbzglList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = Tools.nvl((String)hashmap.get("id"));
		String s1 = Tools.nvl((String)hashmap.get("parameteName"));
		String s2 = Tools.nvl((String)hashmap.get("parameteChName"));
		log.info((new StringBuilder()).append("queryCon.getTotalNum()==================================").append(query.getTotalNum()).toString());
		Object obj1 = null;
		Object obj2 = null;
		try
		{
			StringBuffer stringbuffer2 = new StringBuffer();
			if (s != null && !s.equals(""))
			{
				stringbuffer2.append(" and id ='");
				stringbuffer2.append(s).append("'");
			}
			if (s1 != null && !s1.equals(""))
			{
				stringbuffer2.append(" and parameteName ='");
				stringbuffer2.append(s1).append("'");
			}
			if (s2 != null && !s2.equals(""))
			{
				stringbuffer2.append(" and parameteChName like '%");
				stringbuffer2.append(s2).append("%'");
			}
			log.info(stringbuffer2.toString());
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from TCParaCureStand where 1=1 ");
				stringbuffer.append(stringbuffer2.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from TCParaCureStand where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from TCParaCureStand where 1=1 ").append(stringbuffer2.toString()).append(" order by id asc ) ");
			stringbuffer1.append(stringbuffer2.toString());
			stringbuffer1.append("  order by id asc ");
			query.setSql(stringbuffer1.toString());
			log.info(stringbuffer1.toString());
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer1.toString());
			query.setConditionBody(cachedrowset);
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return query;
	}

	public Query findQxbzglList_noPage(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = Tools.nvl((String)hashmap.get("id"));
		String s1 = Tools.nvl((String)hashmap.get("Cname"));
		String s2 = Tools.nvl((String)hashmap.get("CMonitorStatus"));
		log.info("findQxbzglList_noPage==================================");
		log.info((new StringBuilder()).append("Cname==================================").append(s1).toString());
		log.info((new StringBuilder()).append("queryCon.getTotalNum()==================================").append(query.getTotalNum()).toString());
		StringBuffer stringbuffer = null;
		try
		{
			if (query.getTotalNum() == 0)
			{
				stringbuffer = new StringBuffer("select * from TCParaCureStand where 1=1 ");
				if (s != null && !s.equals(""))
				{
					stringbuffer.append(" and id ='");
					stringbuffer.append(s).append("'");
				}
				if (s1 != null && !s1.equals(""))
				{
					stringbuffer.append(" and Cname ='");
					stringbuffer.append(s1).append("'");
				}
				if (s2 != null && !s2.equals(""))
				{
					stringbuffer.append(" and CMonitorStatus ='");
					stringbuffer.append(s2).append("'");
				}
				log.info(stringbuffer.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			stringbuffer.append(" ");
			query.setSql(stringbuffer.toString());
			log.info(stringbuffer.toString());
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			query.setConditionBody(cachedrowset);
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return query;
	}

	public Query findPersonalQxbzglList(Query query)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = Tools.nvl((String)hashmap.get("fsrqq"));
		String s1 = Tools.nvl((String)hashmap.get("fsrqz"));
		String s2 = Tools.nvl((String)hashmap.get("tzzt"));
		String s3 = Tools.nvl((String)hashmap.get("jsr"));
		Object obj1 = null;
		try
		{
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from TCParaCureStand where 1=1 ");
				if (s != null && !s.equals(""))
				{
					stringbuffer.append(" and fsrq > '");
					stringbuffer.append(s).append("'");
				}
				if (s1 != null && !s1.equals(""))
				{
					stringbuffer.append(" and fsrq < '");
					stringbuffer.append(s1).append("'");
				}
				if (s2 != null && !s2.equals(""))
				{
					stringbuffer.append(" and tzzt like '%");
					stringbuffer.append(s2).append("%'");
				}
				stringbuffer.append(" and jsr = '").append(s3).append("'");
				query.setTotalNum(dbtransutil.getRecNumBySelect(stringbuffer.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer("select top ");
			stringbuffer1.append(query.getRowsPerPage());
			stringbuffer1.append(" * from qxbzgl where qxbzglid not in (select top ");
			stringbuffer1.append(i).append(" qxbzglid from qxbzgl where jsr = '");
			stringbuffer1.append(s3).append("'");
			if (s != null && !s.equals(""))
			{
				stringbuffer1.append(" and fsrq > '");
				stringbuffer1.append(s).append("'");
			}
			if (s1 != null && !s1.equals(""))
			{
				stringbuffer1.append(" and fsrq < '");
				stringbuffer1.append(s1).append("'");
			}
			stringbuffer1.append(" order by qxbzglid desc) ");
			if (s != null && !s.equals(""))
			{
				stringbuffer1.append(" and fsrq > '");
				stringbuffer1.append(s).append("'");
			}
			if (s1 != null && !s1.equals(""))
			{
				stringbuffer1.append(" and fsrq < '");
				stringbuffer1.append(s1).append("'");
			}
			if (s2 != null && !s2.equals(""))
			{
				stringbuffer1.append(" and tzzt like '%");
				stringbuffer1.append(s2).append("%'");
			}
			stringbuffer1.append(" and jsr = '");
			stringbuffer1.append(s3).append("' order by qxbzglid desc");
			query.setSql(stringbuffer1.toString());
			log.info(stringbuffer1.toString());
			CachedRowSet cachedrowset = dbtransutil.getResultBySelect(stringbuffer1.toString());
			query.setConditionBody(cachedrowset);
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return query;
	}

	public Qxbzgl findQxbzglById(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Qxbzgl qxbzgl = new Qxbzgl();
		StringBuffer stringbuffer = new StringBuffer("select * from TCParaCureStand where qxbzglid ='");
		stringbuffer.append(s).append("'");
		try
		{
			CachedRowSet cachedrowset = dbtransutil.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
				throw new BussinessProcessException("该通知不存在！");
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				qxbzgl.setQxbzglid(cachedrowset.getString("qxbzglid"));
				for (int j = 1; j <= i; j++)
					qxbzgl.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));

			}
			stringbuffer = new StringBuffer("update qxbzgl set remark='1' where qxbzglid=");
			stringbuffer.append("qxbzglid").append("");
			dbtransutil.addSql(stringbuffer.toString());
			dbtransutil.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return qxbzgl;
	}

	public String modifyQxbzgl(Qxbzgl qxbzgl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("update TCParaCureStand set ");
		stringbuffer.append("parameteName='").append(qxbzgl.getProp("parameteName")).append("',");
		stringbuffer.append("parameteChName='").append(qxbzgl.getProp("parameteChName")).append("',");
		stringbuffer.append("configYPerValue='").append(qxbzgl.getProp("configYPerValue")).append("',");
		stringbuffer.append("configYMinValue='").append(qxbzgl.getProp("configYMinValue")).append("',");
		stringbuffer.append("configYValueCount='").append(qxbzgl.getProp("configYValueCount")).append("' where id = '");
		stringbuffer.append(qxbzgl.getQxbzglid()).append("'");
		log.info(stringbuffer.toString());
		try
		{
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return qxbzgl.getQxbzglid();
	}

	public String deleteQxbzgl(String s)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		int i = Integer.parseInt(s);
		StringBuffer stringbuffer = new StringBuffer("delete from TCParaCureStand where id = ");
		stringbuffer.append(i).append("");
		try
		{
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s;
	}

	public boolean hasNewQxbzgl(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("select qxbzglid from qxbzgl where jsr = '");
		stringbuffer.append(s).append("' and remark='0'");
		try
		{
			CachedRowSet cachedrowset = dbtransutil.getResultBySelect(stringbuffer.toString());
			if (cachedrowset != null && cachedrowset.size() > 0)
				flag = true;
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return flag;
	}
}
