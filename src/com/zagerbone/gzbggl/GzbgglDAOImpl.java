// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbgglDAOImpl.java

package com.zagerbone.gzbggl;

import com.shenghao.arch.baseobject.BasicObject;
import com.shenghao.arch.exception.BussinessProcessException;
import com.shenghao.arch.util.DBTransUtil;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.Query;
import com.zagerbone.util.Tools;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.gzbggl:
//			Gzbggl, GzbgglDAO

public class GzbgglDAOImpl extends BasicObject
	implements GzbgglDAO
{

	public GzbgglDAOImpl()
	{
	}

	public String createGzbggl_forCNam(DBTrans dbtrans, Gzbggl gzbggl)
		throws BussinessProcessException
	{
		DBTrans dbtrans1 = dbtrans;
		boolean flag = false;
		String s = gzbggl.getProp("fsr");
		Object obj = null;
		try
		{
			Object obj1 = null;
			String s2 = null;
			boolean flag5 = false;
			String s3 = null;
			String s4 = null;
			Object obj2 = null;
			String s6 = null;
			boolean flag6 = false;
			boolean flag7 = false;
			String s7 = null;
			String s9 = null;
			Object obj3 = null;
			Object obj4 = null;
			String s13 = null;
			String s14 = null;
			Object obj5 = null;
			String s16 = null;
			String s17 = null;
			String s18 = null;
			String s19 = "0";
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("select * from TCtestcar where 1=1 and id='").append(gzbggl.getProp("id")).append("'");
			log.info(stringbuffer.toString());
			CachedRowSet cachedrowset = dbtrans1.getResultBySelect(stringbuffer.toString());
			if (cachedrowset.size() == 0)
				throw new BussinessProcessException("找不到数据!");
			while (cachedrowset.next()) 
			{
				String s15 = cachedrowset.getString("tp_carproduction");
				s14 = cachedrowset.getString("tp_carname");
				s4 = cachedrowset.getString("tp_testnum");
				s16 = cachedrowset.getString("TCjk_startTime");
				s17 = cachedrowset.getString("TCjk_endTime");
			}
			log.info((new StringBuilder()).append("tp_carname=============================================================================================").append(s14).toString());
			stringbuffer = new StringBuffer();
			stringbuffer.append("select tp_faultcode,faultdegree from TCfaultstandard where 1=1 and flgMonitor='1' order by faultdegree");
			log.info(stringbuffer.toString());
			CachedRowSet cachedrowset3 = dbtrans1.getResultBySelect(stringbuffer.toString());
			boolean flag8 = false;
			s7 = "";
			s9 = "";
			s18 = "";
			s6 = "";
			s19 = "0";
			String s21 = "";
			stringbuffer = new StringBuffer();
			stringbuffer.append("select min(id) as id from TCfaultinfo where 1=1 ");
			stringbuffer.append(" and  tp_car_eginespeed>500 ");
			stringbuffer.append(" and  tp_testtime>'").append(s16).append("' ");
			stringbuffer.append(" and  tp_testtime<'").append(s17).append("' ");
			stringbuffer.append(" and  tp_testnum='").append(s4).append("' ");
			log.info(stringbuffer.toString());
			cachedrowset = dbtrans1.getResultBySelect(stringbuffer.toString());
			if (cachedrowset.next())
				s21 = Tools.nvl0(cachedrowset.getString("id"));
			log.info((new StringBuilder()).append("id_lst==========").append(s21).toString());
			stringbuffer = new StringBuffer();
			stringbuffer.append("select id from TCfaultinfo where tp_testnum='").append(s4).append("' and id>=").append(s21).append(" order by id asc");
			log.info(stringbuffer.toString());
			CachedRowSet cachedrowset4 = dbtrans1.getResultBySelect(stringbuffer.toString());
			do
			{
				if (!cachedrowset4.next())
					break;
				String s1 = Tools.nvl0(cachedrowset4.getString("id"));
				StringBuffer stringbuffer1 = new StringBuffer();
				stringbuffer1.append("select * from TCfaultinfo where 1=1 and id=").append(s1).append("");
				stringbuffer1.append(" and  tp_car_eginespeed>=500 ");
				log.info(stringbuffer1.toString());
				CachedRowSet cachedrowset1 = dbtrans1.getResultBySelect(stringbuffer1.toString());
				if (cachedrowset1.size() > 0)
				{
					String s10 = "";
					cachedrowset1.next();
					cachedrowset3.beforeFirst();
					do
					{
						if (!cachedrowset3.next())
							break;
						String s22 = cachedrowset3.getString("tp_faultcode");
						log.info((new StringBuilder()).append("faultcode================").append(s22).toString());
						String s8 = cachedrowset1.getString(s22);
						if (!s8.equals("1"))
							continue;
						s10 = s22;
						break;
					} while (true);
					if (s10.equals(""))
					{
						if (flag8)
						{
							flag8 = false;
							s18 = "";
							String s5 = cachedrowset1.getString("tp_testtime");
							int i = Tools.dateMinus_gap(s5, s2);
							StringBuffer stringbuffer2 = new StringBuffer();
							stringbuffer2.append("update  TCfaultreport set ");
							stringbuffer2.append(" tp_fault_recovertime='").append(s5).append("',");
							stringbuffer2.append(" tp_fault_delay='").append(i).append("' ");
							stringbuffer2.append(" where id=").append(s6);
							log.info(stringbuffer2.toString());
							dbtrans1.addSql(stringbuffer2.toString());
							boolean flag1 = dbtrans1.executeSql();
							stringbuffer2 = new StringBuffer();
							stringbuffer2.append("update  TCfaultreport set");
							stringbuffer2.append(" TCfaultreport.tp_faultname=TCfaultstandard.tp_faultname ");
							stringbuffer2.append(" from TCfaultstandard ");
							stringbuffer2.append(" where TCfaultreport.id=").append(s6);
							stringbuffer2.append(" and TCfaultreport.tp_faultcode=TCfaultstandard.tp_faultcode ");
							log.info(stringbuffer2.toString());
							dbtrans1.addSql(stringbuffer2.toString());
							flag1 = dbtrans1.executeSql();
							stringbuffer2 = new StringBuffer();
							stringbuffer2.append("update TCfaultreport set");
							stringbuffer2.append(" TCfaultreport.tp_faulttype='重大' ");
							stringbuffer2.append(" from TCfaultstandard ");
							stringbuffer2.append(" where TCfaultreport.id=").append(s6);
							stringbuffer2.append(" and TCfaultreport.tp_faultcode=TCfaultstandard.tp_faultcode ");
							stringbuffer2.append(" and TCfaultreport.tp_fault_delay > TCfaultstandard.zdtimeVal ");
							log.info(stringbuffer2.toString());
							dbtrans1.addSql(stringbuffer2.toString());
							flag1 = dbtrans1.executeSql();
							stringbuffer2 = new StringBuffer();
							stringbuffer2.append("update TCfaultreport set");
							stringbuffer2.append(" TCfaultreport.tp_faulttype='一般' ");
							stringbuffer2.append(" from TCfaultstandard ");
							stringbuffer2.append(" where TCfaultreport.id=").append(s6);
							stringbuffer2.append(" and TCfaultreport.tp_faultcode=TCfaultstandard.tp_faultcode ");
							stringbuffer2.append(" and TCfaultreport.tp_fault_delay <= TCfaultstandard.zdtimeVal ");
							stringbuffer2.append(" and TCfaultreport.tp_fault_delay > TCfaultstandard.qwtimeVal ");
							log.info(stringbuffer2.toString());
							dbtrans1.addSql(stringbuffer2.toString());
							flag1 = dbtrans1.executeSql();
							stringbuffer2 = new StringBuffer();
							stringbuffer2.append("update TCfaultreport set");
							stringbuffer2.append(" TCfaultreport.tp_faulttype='轻微' ");
							stringbuffer2.append(" from TCfaultstandard ");
							stringbuffer2.append(" where TCfaultreport.id=").append(s6);
							stringbuffer2.append(" and TCfaultreport.tp_faultcode=TCfaultstandard.tp_faultcode ");
							stringbuffer2.append(" and TCfaultreport.tp_fault_delay <= TCfaultstandard.qwtimeVal ");
							stringbuffer2.append(" and TCfaultstandard.qwtimeVal >0 ");
							log.info(stringbuffer2.toString());
							dbtrans1.addSql(stringbuffer2.toString());
							flag1 = dbtrans1.executeSql();
						}
					} else
					if (s10.equals(s18))
					{
						String s11 = cachedrowset1.getString("tp_testtime");
						StringBuffer stringbuffer3 = new StringBuffer();
						stringbuffer3.append("update  TCfaultreport set ");
						stringbuffer3.append(" tp_fault_insisttime='").append(s11).append("' ");
						stringbuffer3.append(" where id=").append(s6);
						log.info(stringbuffer3.toString());
						dbtrans1.addSql(stringbuffer3.toString());
						boolean flag2 = dbtrans1.executeSql();
					} else
					{
						s13 = cachedrowset1.getString("tp_testtime");
						if (!s18.equals(""))
						{
							String s12 = cachedrowset1.getString("tp_testtime");
							StringBuffer stringbuffer4 = new StringBuffer();
							stringbuffer4.append("update  TCfaultreport set ");
							stringbuffer4.append(" tp_fault_overtime='").append(s12).append("' ");
							stringbuffer4.append(" where id=").append(s6);
							log.info(stringbuffer4.toString());
							dbtrans1.addSql(stringbuffer4.toString());
							boolean flag3 = dbtrans1.executeSql();
						}
						StringBuffer stringbuffer5 = new StringBuffer();
						stringbuffer5.append("insert into TCfaultreport(");
						stringbuffer5.append("tp_fault_info_id,tp_faultcode,tp_carname, tp_testnum, tp_fault_begintime");
						stringbuffer5.append(")values(");
						stringbuffer5.append("'").append(s3).append("',");
						stringbuffer5.append("'").append(s10).append("',");
						stringbuffer5.append("'").append(s14).append("',");
						stringbuffer5.append("'").append(s4).append("',");
						stringbuffer5.append("'").append(s13).append("')");
						stringbuffer5.append(" select @@IDENTITY as id");
						log.info(stringbuffer5.toString());
						for (CachedRowSet cachedrowset5 = dbtrans1.getResultBySelect(stringbuffer5.toString()); cachedrowset5.next();)
							s6 = cachedrowset5.getString("id");

						s18 = s10;
					}
					String s20 = s1;
					continue;
				}
				if (!flag8)
				{
					StringBuffer stringbuffer6 = new StringBuffer();
					stringbuffer6.append("select * from TCfaultinfo where 1=1 and id=").append(s1).append("");
					stringbuffer6.append(" and  tp_car_eginespeed<500 ");
					log.info(stringbuffer6.toString());
					CachedRowSet cachedrowset2 = dbtrans1.getResultBySelect(stringbuffer6.toString());
					cachedrowset2.next();
					s2 = cachedrowset2.getString("tp_testtime");
					if (!s18.equals(""))
					{
						flag8 = true;
						s2 = cachedrowset2.getString("tp_testtime");
						int j = Tools.dateMinus_gap(s2, s13);
						StringBuffer stringbuffer7 = new StringBuffer();
						stringbuffer7.append("update  TCfaultreport set ");
						stringbuffer7.append(" tp_fault_stoptime='").append(s2).append("', ");
						stringbuffer7.append(" tp_fault_work='").append(j).append("' ");
						stringbuffer7.append(" where id=").append(s6);
						log.info(stringbuffer7.toString());
						dbtrans1.addSql(stringbuffer7.toString());
						boolean flag4 = dbtrans1.executeSql();
					}
				}
			} while (true);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return s;
	}

	public String createGzbggl(Gzbggl gzbggl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		String s = gzbggl.getProp("fsr");
		Object obj = null;
		try
		{
			StringBuffer stringbuffer = new StringBuffer("insert into TCfaultreport(");
			stringbuffer.append("tp_carproduction,tp_carname, tp_carnum, tp_testnum, TCjk_startTime, tp_faultcode,tp_faultname, tp_fault_stoptime, tp_fault_recovertime,tp_fault_delay, tp_workhour, tp_harvesthour, tp_faulttype,tp_reportoperator");
			stringbuffer.append(")values(");
			stringbuffer.append("'").append(gzbggl.getProp("tp_carproduction")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_carname")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_carnum")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_testnum")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("TCjk_startTime")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_faultcode")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_faultname")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_fault_stoptime")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_fault_recovertime")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_fault_delay")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_workhour")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_harvesthour")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_faulttype")).append("',");
			stringbuffer.append("'").append(gzbggl.getProp("tp_reportoperator")).append("')");
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

	public String publishPersonalGzbggl(Gzbggl gzbggl)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		boolean flag = false;
		String s = gzbggl.getProp("jsr");
		String as[] = com.shenghao.arch.util.Tools.stringCode(s);
		Object obj = null;
		try
		{
			for (int i = 0; i < as.length; i++)
			{
				StringBuffer stringbuffer = new StringBuffer("insert into TCfaultreport(tp_carname, tp_carnum, tp_testnum, tp_testtime, tp_faultcode, tp_fault_stoptime, tp_fault_recovertime, tp_workhour, tp_harvesthour, tp_faulttype, tp_reportoperator,'");
				stringbuffer.append("'").append(gzbggl.getProp("tp_carname")).append("',");
				stringbuffer.append(gzbggl.getProp("tp_carnum")).append("',");
				stringbuffer.append(gzbggl.getProp("tp_testtime")).append("',");
				stringbuffer.append(gzbggl.getProp("tp_faultcode")).append("',");
				stringbuffer.append(gzbggl.getProp("tp_fault_stoptime")).append("',");
				stringbuffer.append(gzbggl.getProp("tp_fault_recovertime")).append("',");
				stringbuffer.append(gzbggl.getProp("tp_workhour")).append("',");
				stringbuffer.append(gzbggl.getProp("tp_harvesthour")).append("',");
				stringbuffer.append(gzbggl.getProp("tp_faulttype")).append("',");
				stringbuffer.append(gzbggl.getProp("tp_reportoperator")).append("',");
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
		return gzbggl.getProp("fsr");
	}

	public Query findGzbgglList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("id"));
		String s1 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_carproduction"));
		String s2 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_carname"));
		String s3 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_carnum"));
		String s4 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_testnum"));
		String s5 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_testtime"));
		String s6 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_faultcode"));
		String s7 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_faultname"));
		String s8 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_fault_stoptime"));
		String s9 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_fault_recovertime"));
		String s10 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_fault_delay"));
		String s11 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_workhour"));
		String s12 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_harvesthour"));
		String s13 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_faulttype"));
		String s14 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_reportoperator"));
		log.info((new StringBuilder()).append("tp_carname==================================").append(s2).toString());
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
				stringbuffer2.append(" and tp_carproduction like'%");
				stringbuffer2.append(s1).append("%'");
			}
			if (s2 != null && !s2.equals(""))
			{
				stringbuffer2.append(" and tp_carname like'%");
				stringbuffer2.append(s2).append("%'");
			}
			if (s3 != null && !s3.equals(""))
			{
				stringbuffer2.append(" and tp_carnum = '");
				stringbuffer2.append(s3).append("'");
			}
			if (s4 != null && !s4.equals(""))
			{
				stringbuffer2.append(" and tp_testnum = '");
				stringbuffer2.append(s4).append("'");
			}
			if (s5 != null && !s5.equals(""))
			{
				stringbuffer2.append(" and tp_testtime = '");
				stringbuffer2.append(s5).append("'");
			}
			if (s6 != null && !s6.equals(""))
			{
				stringbuffer2.append(" and tp_faultcode like '%");
				stringbuffer2.append(s6).append("%'");
			}
			if (s7 != null && !s7.equals(""))
			{
				stringbuffer2.append(" and tp_faultname like '%");
				stringbuffer2.append(s7).append("%'");
			}
			if (s8 != null && !s8.equals(""))
			{
				stringbuffer2.append(" and tp_fault_stoptime = '");
				stringbuffer2.append(s8).append("'");
			}
			if (s9 != null && !s9.equals(""))
			{
				stringbuffer2.append(" and tp_fault_recovertime = '");
				stringbuffer2.append(s9).append("'");
			}
			if (s10 != null && !s10.equals(""))
			{
				stringbuffer2.append(" and tp_fault_delay like '%");
				stringbuffer2.append(s10).append("%'");
			}
			if (s11 != null && !s11.equals(""))
			{
				stringbuffer2.append(" and tp_workhour = '");
				stringbuffer2.append(s11).append("'");
			}
			if (s12 != null && !s12.equals(""))
			{
				stringbuffer2.append(" and tp_harvesthour = '");
				stringbuffer2.append(s12).append("'");
			}
			if (s13 != null && !s13.equals(""))
			{
				stringbuffer2.append(" and tp_faulttype like '%");
				stringbuffer2.append(s13).append("%'");
			}
			if (s14 != null && !s14.equals(""))
			{
				stringbuffer2.append(" and tp_reportoperator like'%");
				stringbuffer2.append(s14).append("%'");
			}
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from TCfaultreport where 1=1 ");
				log.info(stringbuffer2.toString());
				stringbuffer.append(stringbuffer2.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from TCfaultreport where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from TCfaultreport where 1=1 ").append(stringbuffer2.toString()).append(" ) ");
			stringbuffer1.append(stringbuffer2.toString());
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

	public Query findGzbgglList_noPage(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("id"));
		String s1 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_carname"));
		String s2 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("CMonitorStatus"));
		log.info("findGzbgglList_noPage==================================");
		log.info((new StringBuilder()).append("tp_carname==================================").append(s1).toString());
		log.info((new StringBuilder()).append("queryCon.getTotalNum()==================================").append(query.getTotalNum()).toString());
		StringBuffer stringbuffer = null;
		try
		{
			if (query.getTotalNum() == 0)
			{
				stringbuffer = new StringBuffer("select * from TCfaultreport where 1=1 ");
				if (s != null && !s.equals(""))
				{
					stringbuffer.append(" and id ='");
					stringbuffer.append(s).append("'");
				}
				if (s1 != null && !s1.equals(""))
				{
					stringbuffer.append(" and tp_carname ='");
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

	public Query findPersonalGzbgglList(Query query)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("fsrqq"));
		String s1 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("fsrqz"));
		String s2 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tzzt"));
		String s3 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("jsr"));
		Object obj1 = null;
		try
		{
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from TCfaultreport where 1=1 ");
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
			stringbuffer1.append(" * from gzbggl where gzbgglid not in (select top ");
			stringbuffer1.append(i).append(" gzbgglid from gzbggl where jsr = '");
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
			stringbuffer1.append(" order by gzbgglid desc) ");
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
			stringbuffer1.append(s3).append("' order by gzbgglid desc");
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

	public Gzbggl findGzbgglById(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Gzbggl gzbggl = new Gzbggl();
		StringBuffer stringbuffer = new StringBuffer("select * from TCfaultreport where gzbgglid ='");
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
				gzbggl.setGzbgglid(cachedrowset.getString("gzbgglid"));
				for (int j = 1; j <= i; j++)
					gzbggl.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), com.shenghao.arch.util.Tools.nvl(cachedrowset.getString(j)));

			}
			stringbuffer = new StringBuffer("update gzbggl set remark='1' where gzbgglid=");
			stringbuffer.append("gzbgglid").append("");
			dbtransutil.addSql(stringbuffer.toString());
			dbtransutil.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return gzbggl;
	}

	public String modifyGzbggl(Gzbggl gzbggl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("update TCfaultreport set ");
		stringbuffer.append("tp_carname='").append(gzbggl.getProp("tp_carname")).append("',");
		stringbuffer.append("tp_carnum='").append(gzbggl.getProp("tp_carnum")).append("',");
		stringbuffer.append("tp_testnum='").append(gzbggl.getProp("tp_testnum")).append("',");
		stringbuffer.append("TCjk_startTime='").append(gzbggl.getProp("TCjk_startTime")).append("',");
		stringbuffer.append("tp_faultcode='").append(gzbggl.getProp("tp_faultcode")).append("',");
		stringbuffer.append("tp_faultname='").append(gzbggl.getProp("tp_faultname")).append("',");
		stringbuffer.append("tp_fault_stoptime='").append(gzbggl.getProp("tp_fault_stoptime")).append("',");
		stringbuffer.append("tp_fault_recovertime='").append(gzbggl.getProp("tp_fault_recovertime")).append("',");
		stringbuffer.append("tp_fault_delay='").append(gzbggl.getProp("tp_fault_delay")).append("',");
		stringbuffer.append("tp_workhour='").append(gzbggl.getProp("tp_workhour")).append("',");
		stringbuffer.append("tp_harvesthour='").append(gzbggl.getProp("tp_harvesthour")).append("',");
		stringbuffer.append("tp_faulttype='").append(gzbggl.getProp("tp_faulttype")).append("',");
		stringbuffer.append("tp_reportoperator='").append(gzbggl.getProp("tp_reportoperator")).append("' where id = '");
		stringbuffer.append(gzbggl.getGzbgglid()).append("'");
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
		return gzbggl.getGzbgglid();
	}

	public String deleteGzbggl(String s)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		int i = Integer.parseInt(s);
		StringBuffer stringbuffer = new StringBuffer("delete from TCfaultreport where id = ");
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

	public boolean hasNewGzbggl(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("select gzbgglid from gzbggl where jsr = '");
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
