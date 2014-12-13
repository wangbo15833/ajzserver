// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyclglDAOImpl.java

package com.zagerbone.syclgl;

import com.shenghao.arch.baseobject.BasicObject;
import com.shenghao.arch.exception.BussinessProcessException;
import com.shenghao.arch.util.DBTransUtil;
import com.shenghao.arch.util.Tools;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.Query;
import java.io.PrintStream;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.syclgl:
//			Syclgl, SyclglDAO

public class SyclglDAOImpl extends BasicObject
	implements SyclglDAO
{

	public SyclglDAOImpl()
	{
	}

	public String createSyclgl(Syclgl syclgl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		String s = syclgl.getProp("fsr");
		Object obj = null;
		Object obj1 = null;
		try
		{
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("select * from  TCSyyq where 1=1 ");
			stringbuffer.append(" and tp_testtoolnum='").append(syclgl.getProp("tp_testtoolnum")).append("'");
			stringbuffer.append(" and (tp_carname='' or tp_carname='null' or tp_carname is null)");
			log.info(stringbuffer.toString());
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset.size() == 0)
				throw new BussinessProcessException((new StringBuilder()).append("当前车辆所用试验仪器(").append(syclgl.getProp("tp_testtoolnum")).append(")正在被使用!").toString());
			String s1 = null;
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("update sys_billcode set next_value= next_value+1 ");
			stringbuffer1.append("select next_value,prefix from sys_billcode where displayName='tp_testnum' ");
			log.info(stringbuffer1.toString());
			System.out.println(stringbuffer1.toString());
			for (CachedRowSet cachedrowset1 = dbtrans.getResultBySelect(stringbuffer1.toString()); cachedrowset1.next();)
				s1 = (new StringBuilder()).append(cachedrowset1.getString("prefix")).append(cachedrowset1.getString("next_value")).toString();

			String s2 = "0.0";
			stringbuffer = new StringBuffer("insert into TCtestcar(");
			stringbuffer.append("dataCreateTime,tp_carname, tp_cartype, tp_carnum, tp_carproduction, tp_carouttime, tp_carcheckdate, tp_carcheckmeb,");
			stringbuffer.append(" tp_carcheckmebphone, tp_testnum, tp_testtoolnum, tp_teststarttime, tp_testendtime,tp_testtotaltime, tp_testmeb, tp_testmebphone");
			stringbuffer.append(")values(");
			stringbuffer.append("getDate(),");
			stringbuffer.append("'").append(syclgl.getProp("tp_carname")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_cartype")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_carnum")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_carproduction")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_carouttime")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_carcheckdate")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_carcheckmeb")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_carcheckmebphone")).append("',");
			stringbuffer.append("'").append(s1).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_testtoolnum")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_teststarttime")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_testendtime")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_testtotaltime")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_testmeb")).append("',");
			stringbuffer.append("'").append(syclgl.getProp("tp_testmebphone")).append("')");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			stringbuffer = new StringBuffer("update TCSyyq set ");
			stringbuffer.append("tp_carname='").append(syclgl.getProp("tp_carname")).append("', ");
			stringbuffer.append("tp_testnum='").append(s1).append("', ");
			stringbuffer.append("tp_teststarttime='").append(syclgl.getProp("tp_teststarttime")).append("', ");
			stringbuffer.append("tp_carnum='").append(syclgl.getProp("tp_carnum")).append("', ");
			stringbuffer.append("tp_carproduction='").append(syclgl.getProp("tp_carproduction")).append("'");
			stringbuffer.append("where 1=1 ");
			stringbuffer.append(" and tp_testtoolnum='").append(syclgl.getProp("tp_testtoolnum")).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag1 = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return s;
	}

	public String publishPersonalSyclgl(Syclgl syclgl)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		boolean flag = false;
		String s = syclgl.getProp("jsr");
		String as[] = Tools.stringCode(s);
		Object obj = null;
		try
		{
			for (int i = 0; i < as.length; i++)
			{
				StringBuffer stringbuffer = new StringBuffer("insert into TCtestcar(dataCreateTime,tp_carname, tp_cartype, tp_carnum, tp_carproduction, tp_carouttime, tp_carcheckdate, tp_carcheckmeb, tp_carcheckmebphone, tp_testnum, tp_testtooltypenum, tp_testtoolnum, tp_teststarttime, tp_testtotaltime, tp_testmeb, tp_testmebphone) values(getDate(),'");
				stringbuffer.append("'").append(syclgl.getProp("tp_carname")).append("',");
				stringbuffer.append(syclgl.getProp("tp_cartype")).append("',");
				stringbuffer.append(syclgl.getProp("tp_carproduction")).append("',");
				stringbuffer.append(syclgl.getProp("tp_carouttime")).append("',");
				stringbuffer.append(syclgl.getProp("tp_carcheckdate")).append("',");
				stringbuffer.append(syclgl.getProp("tp_carcheckmeb")).append("',");
				stringbuffer.append(syclgl.getProp("tp_carcheckmebphone")).append("',");
				stringbuffer.append(syclgl.getProp("tp_testnum")).append("',");
				stringbuffer.append(syclgl.getProp("tp_testtooltypenum")).append("',");
				stringbuffer.append(syclgl.getProp("tp_testtoolnum")).append("',");
				stringbuffer.append(syclgl.getProp("tp_teststarttime")).append("',");
				stringbuffer.append(syclgl.getProp("tp_testmeb")).append("',");
				stringbuffer.append(syclgl.getProp("tp_testmebphone")).append("')");
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
		return syclgl.getProp("fsr");
	}

	public Query findSyclglList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		log.info((new StringBuilder()).append("body=======11====================================").append(hashmap).toString());
		String s = Tools.nvl((String)hashmap.get("id"));
		String s1 = Tools.nvl((String)hashmap.get("tp_testnum"));
		String s2 = Tools.nvl((String)hashmap.get("tp_carnum"));
		String s3 = Tools.nvl((String)hashmap.get("tp_carname"));
		String s4 = Tools.nvl((String)hashmap.get("tp_cartype"));
		String s5 = Tools.nvl((String)hashmap.get("tp_carproduction"));
		String s6 = Tools.nvl((String)hashmap.get("tp_testtooltypenum"));
		String s7 = Tools.nvl((String)hashmap.get("tp_teststarttime"));
		String s8 = Tools.nvl((String)hashmap.get("tp_testendtime"));
		String s9 = Tools.nvl((String)hashmap.get("CReoportStatus"));
		String s10 = Tools.nvl((String)hashmap.get("tp_testtoolnum"));
		String s11 = Tools.nvl((String)hashmap.get("CMonitorStatus"));
		log.info((new StringBuilder()).append("tp_testnum======11============================").append(s1).toString());
		log.info((new StringBuilder()).append("tp_carname======11=====================================").append(s3).toString());
		log.info((new StringBuilder()).append("tp_testtoolnum======11============================").append(s10).toString());
		log.info((new StringBuilder()).append("queryCon.getTotalNum()===============").append(query.getTotalNum()).toString());
		Object obj1 = null;
		Object obj2 = null;
		try
		{
			StringBuffer stringbuffer2 = new StringBuffer();
			if (!s.equals(""))
				stringbuffer2.append(" and id ='").append(s).append("' ");
			if (!s3.equals(""))
				stringbuffer2.append(" and tp_carname ='").append(s3).append("' ");
			if (!s10.equals(""))
				stringbuffer2.append(" and tp_testtoolnum ='").append(s10).append("' ");
			if (!s11.equals(""))
				stringbuffer2.append(" and CMonitorStatus ='").append(s11).append("' ");
			if (!s1.equals(""))
				stringbuffer2.append(" and tp_testnum ='").append(s1).append("' ");
			if (!s2.equals(""))
				stringbuffer2.append(" and tp_carnum ='").append(s2).append("' ");
			if (!s4.equals(""))
				stringbuffer2.append(" and tp_cartype ='").append(s4).append("' ");
			if (!s5.equals(""))
				stringbuffer2.append(" and tp_carproduction ='").append(s5).append("' ");
			if (!s6.equals(""))
				stringbuffer2.append(" and tp_testtooltypenum ='").append(s6).append("' ");
			if (!s9.equals(""))
				stringbuffer2.append(" and CReoportStatus ='").append(s9).append("' ");
			if (!s7.equals(""))
				stringbuffer2.append(" and tp_teststarttime ='").append(s7).append("' ");
			if (!s8.equals(""))
				stringbuffer2.append(" and tp_testendtime ='").append(s8).append("' ");
			log.info(stringbuffer2.toString());
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from TCtestcar where 1=1 ");
				stringbuffer.append(stringbuffer2.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from TCtestcar where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from TCtestcar where 1=1 ").append(stringbuffer2.toString()).append(" order by id asc ) ");
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

	public Query findSyclglList_noPage(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = Tools.nvl((String)hashmap.get("id"));
		String s1 = Tools.nvl((String)hashmap.get("tp_testnum"));
		String s2 = Tools.nvl((String)hashmap.get("tp_carnum"));
		String s3 = Tools.nvl((String)hashmap.get("tp_carname"));
		String s4 = Tools.nvl((String)hashmap.get("tp_testtoolnum"));
		String s5 = Tools.nvl((String)hashmap.get("CMonitorStatus"));
		String s6 = Tools.nvl((String)hashmap.get("tp_cartype"));
		String s7 = Tools.nvl((String)hashmap.get("tp_carproduction"));
		String s8 = Tools.nvl((String)hashmap.get("tp_testtooltypenum"));
		String s9 = Tools.nvl((String)hashmap.get("tp_teststarttime"));
		String s10 = Tools.nvl((String)hashmap.get("tp_testendtime"));
		String s11 = Tools.nvl((String)hashmap.get("CReoportStatus"));
		log.info("findSyclglList_noPage==================================");
		log.info((new StringBuilder()).append("tp_carname==================================").append(s3).toString());
		log.info((new StringBuilder()).append("queryCon.getTotalNum()==================================").append(query.getTotalNum()).toString());
		StringBuffer stringbuffer = null;
		try
		{
			if (query.getTotalNum() == 0)
			{
				stringbuffer = new StringBuffer("select * from TCtestcar where 1=1 ");
				if (s != null && !s.equals(""))
				{
					stringbuffer.append(" and id ='");
					stringbuffer.append(s).append("'");
				}
				if (s1 != null && !s1.equals(""))
				{
					stringbuffer.append(" and tp_testnum ='");
					stringbuffer.append(s1).append("'");
				}
				if (s3 != null && !s3.equals(""))
				{
					stringbuffer.append(" and tp_carname like '%");
					stringbuffer.append(s3).append("%'");
				}
				if (s6 != null && !s6.equals(""))
				{
					stringbuffer.append(" and tp_cartype ='");
					stringbuffer.append(s6).append("'");
				}
				if (s2 != null && !s2.equals(""))
				{
					stringbuffer.append(" and tp_carnum ='");
					stringbuffer.append(s2).append("'");
				}
				if (s7 != null && !s7.equals(""))
				{
					stringbuffer.append(" and tp_carproduction ='");
					stringbuffer.append(s7).append("'");
				}
				if (s4 != null && !s4.equals(""))
				{
					stringbuffer.append(" and tp_testtoolnum ='");
					stringbuffer.append(s5).append("'");
				}
				if (s8 != null && !s8.equals(""))
				{
					stringbuffer.append(" and tp_testtooltypenum ='");
					stringbuffer.append(s8).append("'");
				}
				if (s5 != null && !s5.equals(""))
				{
					stringbuffer.append(" and CMonitorStatus ='");
					stringbuffer.append(s5).append("'");
				}
				if (s11 != null && !s11.equals(""))
				{
					stringbuffer.append(" and CReoportStatus ='");
					stringbuffer.append(s11).append("'");
				}
				if (s9 != null && !s9.equals(""))
				{
					stringbuffer.append(" and tp_teststarttime ='");
					stringbuffer.append(s9).append("'");
				}
				if (s10 != null && !s10.equals(""))
				{
					stringbuffer.append(" and tp_testendtime ='");
					stringbuffer.append(s10).append("'");
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

	public Query findPersonalSyclglList(Query query)
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
				StringBuffer stringbuffer = new StringBuffer("select * from TCtestcar where 1=1 ");
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
			stringbuffer1.append(" * from syclgl where syclglid not in (select top ");
			stringbuffer1.append(i).append(" syclglid from syclgl where jsr = '");
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
			stringbuffer1.append(" order by syclglid desc) ");
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
			stringbuffer1.append(s3).append("' order by syclglid desc");
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

	public Syclgl findSyclglById(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Syclgl syclgl = new Syclgl();
		StringBuffer stringbuffer = new StringBuffer("select * from TCtestcar where syclglid ='");
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
				syclgl.setSyclglid(cachedrowset.getString("syclglid"));
				for (int j = 1; j <= i; j++)
					syclgl.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));

			}
			stringbuffer = new StringBuffer("update syclgl set remark='1' where syclglid=");
			stringbuffer.append("syclglid").append("");
			dbtransutil.addSql(stringbuffer.toString());
			dbtransutil.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return syclgl;
	}

	public String modifySyclgl(Syclgl syclgl)
		throws BussinessProcessException
	{
		String s = Tools.nvl(syclgl.getProp("ModifyMode"));
		try
		{
			DBTrans dbtrans = new DBTrans();
			boolean flag = false;
			Object obj = null;
			Object obj1 = null;
			Object obj2 = null;
			Object obj3 = null;
			StringBuffer stringbuffer = new StringBuffer("update TCtestcar set ");
			stringbuffer.append("tp_carname='").append(syclgl.getProp("tp_carname")).append("',");
			stringbuffer.append("tp_cartype='").append(syclgl.getProp("tp_cartype")).append("',");
			stringbuffer.append("tp_carproduction='").append(syclgl.getProp("tp_carproduction")).append("',");
			stringbuffer.append("tp_testmeb='").append(syclgl.getProp("tp_testmeb")).append("',");
			stringbuffer.append("tp_testmebphone='").append(syclgl.getProp("tp_testmebphone")).append("',");
			stringbuffer.append("tp_carcheckmeb='").append(syclgl.getProp("tp_carcheckmeb")).append("',");
			stringbuffer.append("tp_carcheckmebphone='").append(syclgl.getProp("tp_carcheckmebphone")).append("'");
			stringbuffer.append("where id = '").append(syclgl.getSyclglid()).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return syclgl.getSyclglid();
	}

	public String modifySyclgl_dogd(Syclgl syclgl)
		throws BussinessProcessException
	{
		String s = Tools.nvl(syclgl.getProp("tp_testnum"));
		try
		{
			DBTrans dbtrans = new DBTrans();
			boolean flag = false;
			Object obj = null;
			Object obj1 = null;
			StringBuffer stringbuffer = new StringBuffer("update TCtestcar set ");
			stringbuffer.append((new StringBuilder()).append("tp_cargdzt='1'  where tp_testnum = '").append(s).append("' ").toString());
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return syclgl.getSyclglid();
	}

	public String modifySyclgl_cancal(Syclgl syclgl)
		throws BussinessProcessException
	{
		String s = Tools.nvl(syclgl.getProp("CMonitorStatus"));
		String s1 = Tools.nvl(syclgl.getProp("Flag_bgzt"));
		String s2 = Tools.nvl(syclgl.getProp("ModifyMode"));
		try
		{
			DBTrans dbtrans = new DBTrans();
			boolean flag = false;
			StringBuffer stringbuffer = null;
			String s3 = null;
			String s4 = null;
			String s5 = null;
			CachedRowSet cachedrowset = null;
			if (!s2.equals("cancal"))
				throw new Exception("不是状态变更！");
			stringbuffer = new StringBuffer();
			stringbuffer.append("select top 1 * from TCfaultinfo where 1=1 ");
			stringbuffer.append(" and tp_testtoolnum='").append(syclgl.getProp("tp_testtoolnum")).append("'");
			stringbuffer.append(" and tp_carname='").append(syclgl.getProp("tp_carname")).append("'");
			stringbuffer.append(" order by id");
			log.info(stringbuffer.toString());
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset.size() == 0)
				throw new BussinessProcessException((new StringBuilder()).append("当前车辆(").append(syclgl.getProp("tp_carname")).append(")所用试验仪器(").append(syclgl.getProp("tp_testtoolnum")).append(")还未发送数据!").toString());
			while (cachedrowset.next()) 
			{
				s3 = cachedrowset.getString("tp_testtime");
				s5 = cachedrowset.getString("tp_testtime");
			}
			stringbuffer = new StringBuffer();
			stringbuffer.append("select max(id) as id from TCfaultinfo where 1=1 ");
			stringbuffer.append("and tp_testtoolnum='").append(syclgl.getProp("tp_testtoolnum")).append("' ");
			stringbuffer.append("and tp_carname='").append(syclgl.getProp("tp_carname")).append("' ");
			stringbuffer.append(" order by id");
			log.info(stringbuffer.toString());
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			String s6;
			for (s6 = null; cachedrowset.next(); s6 = cachedrowset.getString("id"));
			stringbuffer = new StringBuffer();
			stringbuffer.append("select tp_harvesthour,tp_testtime from TCfaultinfo where 1=1 ");
			stringbuffer.append("and tp_testtoolnum='").append(syclgl.getProp("tp_testtoolnum")).append("'");
			stringbuffer.append("and tp_carname='").append(syclgl.getProp("tp_carname")).append("'");
			stringbuffer.append("and id='").append(s6).append("'");
			log.info(stringbuffer.toString());
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			String s7 = "0.0";
			while (cachedrowset.next()) 
			{
				s7 = com.zagerbone.util.Tools.nvl0(cachedrowset.getString("tp_harvesthour"));
				s4 = cachedrowset.getString("tp_testtime");
			}
			s4 = s5;
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("update TCtestcar set ");
			stringbuffer1.append("tp_carname='").append(syclgl.getProp("tp_carname")).append("',");
			stringbuffer1.append("tp_cartype='").append(syclgl.getProp("tp_cartype")).append("',");
			stringbuffer1.append("CMonitorStatus='").append(syclgl.getProp("CMonitorStatus")).append("',");
			if (s.equals("1"))
			{
				stringbuffer1.append("TCjk_initTime='").append(s3).append("',");
				stringbuffer1.append("TCjk_startTime='").append(s4).append("',");
				stringbuffer1.append("Tharvesttime='").append(s7).append("',");
				stringbuffer1.append("tp_teststarttime='").append(s5).append("', ");
				stringbuffer1.append("Flag_bgzt='").append(syclgl.getProp("Flag_bgzt")).append("' ");
			} else
			{
				stringbuffer1.append("TCjk_endTime=").append("getDate()").append(", ");
				stringbuffer1.append("tp_testendtime=").append("getDate()").append("  ");
			}
			stringbuffer1.append(" where id = '").append(syclgl.getSyclglid()).append("'");
			log.info(stringbuffer1.toString());
			dbtrans.addSql(stringbuffer1.toString());
			flag = dbtrans.executeSql();
			if (s.equals("0"))
			{
				StringBuffer stringbuffer2 = new StringBuffer();
				stringbuffer2.append("update TCSyyq set tp_carname='' ");
				stringbuffer2.append(" ,tp_testnum='' ");
				stringbuffer2.append(" ,tp_carnum=''  ");
				stringbuffer2.append(" ,tp_teststarttime=null ");
				stringbuffer2.append(" ,tp_carproduction=''  ");
				stringbuffer2.append(" where 1=1 ");
				stringbuffer2.append(" and tp_testtoolnum='").append(syclgl.getProp("tp_testtoolnum")).append("'");
				log.info(stringbuffer2.toString());
				dbtrans.addSql(stringbuffer2.toString());
				boolean flag1 = dbtrans.executeSql();
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return syclgl.getSyclglid();
	}

	public String deleteSyclgl(String s)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		int i = Integer.parseInt(s);
		StringBuffer stringbuffer = new StringBuffer("delete from TCtestcar where id = ");
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

	public boolean hasNewSyclgl(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("select syclglid from syclgl where jsr = '");
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
