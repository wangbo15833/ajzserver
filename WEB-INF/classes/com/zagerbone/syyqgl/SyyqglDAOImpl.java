// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyyqglDAOImpl.java

package com.zagerbone.syyqgl;

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

// Referenced classes of package com.zagerbone.syyqgl:
//			Syyqgl, SyyqglDAO

public class SyyqglDAOImpl extends BasicObject
	implements SyyqglDAO
{

	public SyyqglDAOImpl()
	{
	}

	public String createSyyqgl(Syyqgl syyqgl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		String s = syyqgl.getProp("fsr");
		Object obj = null;
		try
		{
			StringBuffer stringbuffer = new StringBuffer("insert into TCSyyq(");
			stringbuffer.append("tp_testtoolnum, tp_testtooltypenum");
			stringbuffer.append(")values(");
			stringbuffer.append("'").append(syyqgl.getProp("tp_testtoolnum")).append("',");
			stringbuffer.append("'").append(syyqgl.getProp("tp_testtooltypenum")).append("')");
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

	public String publishPersonalSyyqgl(Syyqgl syyqgl)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		boolean flag = false;
		String s = syyqgl.getProp("jsr");
		String as[] = Tools.stringCode(s);
		Object obj = null;
		try
		{
			for (int i = 0; i < as.length; i++)
			{
				StringBuffer stringbuffer = new StringBuffer("insert into TCSyyq(dataCreateTime,Cname, Ctype, Cnum, Cmake, Couttime, Cjtime, Ctestmb, Ctestdh, Tnum, Tyqxh, tp_testtoolnum, Tstime, Ttime, Tmeb, Tmebdh) values(getDate(),'");
				stringbuffer.append("'").append(syyqgl.getProp("Cname")).append("',");
				stringbuffer.append(syyqgl.getProp("Ctype")).append("',");
				stringbuffer.append(syyqgl.getProp("Cmake")).append("',");
				stringbuffer.append(syyqgl.getProp("Couttime")).append("',");
				stringbuffer.append(syyqgl.getProp("Cjtime")).append("',");
				stringbuffer.append(syyqgl.getProp("Ctestmb")).append("',");
				stringbuffer.append(syyqgl.getProp("Ctestdh")).append("',");
				stringbuffer.append(syyqgl.getProp("Tnum")).append("',");
				stringbuffer.append(syyqgl.getProp("Tyqxh")).append("',");
				stringbuffer.append(syyqgl.getProp("tp_testtoolnum")).append("',");
				stringbuffer.append(syyqgl.getProp("Tstime")).append("',");
				stringbuffer.append(syyqgl.getProp("Tmeb")).append("',");
				stringbuffer.append(syyqgl.getProp("Tmebdh")).append("')");
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
		return syyqgl.getProp("fsr");
	}

	public Query findSyyqglList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = Tools.nvl((String)hashmap.get("id"));
		String s1 = Tools.nvl((String)hashmap.get("tp_testtoolnum"));
		String s2 = Tools.nvl((String)hashmap.get("tp_testtooltypenum"));
		String s3 = Tools.nvl((String)hashmap.get("tp_testnum"));
		String s4 = Tools.nvl((String)hashmap.get("tp_teststarttime"));
		String s5 = Tools.nvl((String)hashmap.get("tp_testtotaltime"));
		String s6 = Tools.nvl((String)hashmap.get("tp_carnum"));
		String s7 = Tools.nvl((String)hashmap.get("tp_carname"));
		String s8 = Tools.nvl((String)hashmap.get("tp_carproduction"));
		log.info((new StringBuilder()).append("tp_carname==================================").append(s7).toString());
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
				stringbuffer2.append(" and tp_testtoolnum ='");
				stringbuffer2.append(s1).append("'");
			}
			if (s2 != null && !s2.equals(""))
			{
				stringbuffer2.append(" and tp_testtooltypenum ='");
				stringbuffer2.append(s2).append("'");
			}
			if (s3 != null && !s3.equals(""))
			{
				stringbuffer2.append(" and tp_testnum ='");
				stringbuffer2.append(s3).append("'");
			}
			if (s4 != null && !s4.equals(""))
			{
				stringbuffer2.append(" and tp_teststarttime ='");
				stringbuffer2.append(s4).append("'");
			}
			if (s5 != null && !s5.equals(""))
			{
				stringbuffer2.append(" and tp_testtotaltime ='");
				stringbuffer2.append(s5).append("'");
			}
			if (s6 != null && !s6.equals(""))
			{
				stringbuffer2.append(" and tp_carnum ='");
				stringbuffer2.append(s6).append("'");
			}
			if (s7 != null && !s7.equals(""))
			{
				stringbuffer2.append(" and tp_carname ='");
				stringbuffer2.append(s7).append("'");
			}
			if (s8 != null && !s8.equals(""))
			{
				stringbuffer2.append(" and tp_carproduction ='");
				stringbuffer2.append(s8).append("'");
			}
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from TCSyyq where 1=1 ");
				stringbuffer.append(stringbuffer2.toString());
				log.info(stringbuffer.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from TCSyyq where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from TCSyyq where 1=1 ").append(stringbuffer2.toString()).append(" order by id asc ) ");
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

	public Query findSyyqglList_noPage(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		log.info((new StringBuilder()).append("body=======11====================================").append(hashmap).toString());
		String s = Tools.nvl((String)hashmap.get("id"));
		String s1 = Tools.nvl((String)hashmap.get("tp_testtoolnum"));
		String s2 = Tools.nvl((String)hashmap.get("tp_testtooltypenum"));
		String s3 = Tools.nvl((String)hashmap.get("tp_testnum"));
		String s4 = Tools.nvl((String)hashmap.get("tp_teststarttime"));
		String s5 = Tools.nvl((String)hashmap.get("tp_testtotaltime"));
		String s6 = Tools.nvl((String)hashmap.get("tp_carnum"));
		String s7 = Tools.nvl((String)hashmap.get("tp_carname"));
		String s8 = Tools.nvl((String)hashmap.get("tp_carproduction"));
		log.info("findSyyqglList_noPage==================================");
		log.info((new StringBuilder()).append("tp_carname==================================").append(s7).toString());
		log.info((new StringBuilder()).append("queryCon.getTotalNum()==================================").append(query.getTotalNum()).toString());
		StringBuffer stringbuffer = null;
		Object obj1 = null;
		try
		{
			StringBuffer stringbuffer1 = new StringBuffer();
			if (s != null && !s.equals(""))
			{
				stringbuffer1.append(" and id ='");
				stringbuffer1.append(s).append("'");
			}
			if (s1 != null && !s1.equals(""))
			{
				stringbuffer1.append(" and tp_testtoolnum ='");
				stringbuffer1.append(s1).append("'");
			}
			if (s2 != null && !s2.equals(""))
			{
				stringbuffer1.append(" and tp_testtooltypenum ='");
				stringbuffer1.append(s2).append("'");
			}
			if (s3 != null && !s3.equals(""))
			{
				stringbuffer1.append(" and tp_testnum ='");
				stringbuffer1.append(s3).append("'");
			}
			if (s4 != null && !s4.equals(""))
			{
				stringbuffer1.append(" and tp_teststarttime ='");
				stringbuffer1.append(s4).append("'");
			}
			if (s5 != null && !s5.equals(""))
			{
				stringbuffer1.append(" and tp_testtotaltime ='");
				stringbuffer1.append(s5).append("'");
			}
			if (s6 != null && !s6.equals(""))
			{
				stringbuffer1.append(" and tp_carnum ='");
				stringbuffer1.append(s6).append("'");
			}
			if (s7 != null && !s7.equals(""))
			{
				stringbuffer1.append(" and tp_carname ='");
				stringbuffer1.append(s7).append("'");
			}
			if (s8 != null && !s8.equals(""))
			{
				stringbuffer1.append(" and tp_carproduction ='");
				stringbuffer1.append(s8).append("'");
			}
			log.info(stringbuffer1.toString());
			if (query.getTotalNum() == 0)
			{
				stringbuffer = new StringBuffer("select * from TCSyyq where 1=1 ");
				stringbuffer.append(stringbuffer1.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			stringbuffer.append(" ");
			stringbuffer.append(" order by id asc ");
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

	public Query findPersonalSyyqglList(Query query)
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
				StringBuffer stringbuffer = new StringBuffer("select * from TCSyyq where 1=1 ");
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
			stringbuffer1.append(" * from syyqgl where syyqglid not in (select top ");
			stringbuffer1.append(i).append(" syyqglid from syyqgl where jsr = '");
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
			stringbuffer1.append(" order by syyqglid asc ");
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
			stringbuffer1.append(s3).append("' order by syyqglid asc");
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

	public Syyqgl findSyyqglById(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Syyqgl syyqgl = new Syyqgl();
		StringBuffer stringbuffer = new StringBuffer("select * from TCSyyq where syyqglid ='");
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
				syyqgl.setSyyqglid(cachedrowset.getString("syyqglid"));
				for (int j = 1; j <= i; j++)
					syyqgl.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));

			}
			stringbuffer = new StringBuffer("update syyqgl set remark='1' where syyqglid=");
			stringbuffer.append("syyqglid").append("");
			dbtransutil.addSql(stringbuffer.toString());
			dbtransutil.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return syyqgl;
	}

	public String modifySyyqgl(Syyqgl syyqgl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		StringBuffer stringbuffer = null;
		stringbuffer = new StringBuffer();
		stringbuffer.append("update TCSyyq set ");
		stringbuffer.append("tp_testtoolnum='").append(syyqgl.getProp("tp_testtoolnum")).append("',");
		stringbuffer.append("tp_testtooltypenum='").append(syyqgl.getProp("tp_testtooltypenum")).append("',");
		stringbuffer.append("tp_testnum='").append(syyqgl.getProp("tp_testnum")).append("',");
		stringbuffer.append("tp_teststarttime='").append(syyqgl.getProp("tp_teststarttime")).append("',");
		stringbuffer.append("tp_testtotaltime='").append(syyqgl.getProp("tp_testtotaltime")).append("',");
		stringbuffer.append("tp_carnum='").append(syyqgl.getProp("tp_carnum")).append("',");
		stringbuffer.append("tp_carname='").append(syyqgl.getProp("tp_carname")).append("',");
		stringbuffer.append("tp_carproduction='").append(syyqgl.getProp("tp_carproduction")).append("' ");
		stringbuffer.append("where id='").append(syyqgl.getSyyqglid()).append("'");
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
		return syyqgl.getSyyqglid();
	}

	public String deleteSyyqgl(String s)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		int i = Integer.parseInt(s);
		StringBuffer stringbuffer = new StringBuffer("delete from TCSyyq where id = ");
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

	public boolean hasNewSyyqgl(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("select syyqglid from syyqgl where jsr = '");
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
