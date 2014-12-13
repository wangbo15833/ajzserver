// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JcbgglDAOImpl.java

package com.zagerbone.jcbggl;

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

// Referenced classes of package com.zagerbone.jcbggl:
//			Jcbggl, JcbgglDAO

public class JcbgglDAOImpl extends BasicObject
	implements JcbgglDAO
{

	public JcbgglDAOImpl()
	{
	}

	public String createJcbggl(Jcbggl jcbggl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		String s = jcbggl.getProp("fsr");
		Object obj = null;
		try
		{
			StringBuffer stringbuffer = new StringBuffer("insert into TCtestreport(");
			stringbuffer.append("tp_carproduction,tp_carname, tp_carnum, tp_testnum, tp_testtoolnum, tp_testtooltypenum, tp_testtime, tp_faultcode, tp_fault_stoptime, tp_fault_recovertime, tp_workhour, tp_harvesthour, tp_faulttype,tp_reportoperator");
			stringbuffer.append(")values(");
			stringbuffer.append("'").append(jcbggl.getProp("tp_carproduction")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_carname")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_carnum")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_testnum")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_testtoolnum")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_testtooltypenum")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_testtime")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_faultcode")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_fault_stoptime")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_fault_recovertime")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_workhour")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_harvesthour")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_faulttype")).append("',");
			stringbuffer.append("'").append(jcbggl.getProp("tp_reportoperator")).append("')");
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

	public void createJcbggl_byData(DBTrans dbtrans, Jcbggl jcbggl)
		throws BussinessProcessException
	{
		DBTrans dbtrans1 = dbtrans;
		boolean flag = false;
		Object obj = null;
		try
		{
			log.info("---------------------------------------");
			String s = jcbggl.getProp("tp_testnum");
			String s1 = jcbggl.getProp("tp_carname");
			String s2 = jcbggl.getProp("TCjk_startTime");
			String s3 = jcbggl.getProp("TCjk_endTime");
			String s4 = null;
			float f = 0.0F;
			String s5 = null;
			String s6 = "0";
			Object obj1 = null;
			Object obj2 = null;
			Object obj3 = null;
			Object obj4 = null;
			float f1 = 0.0F;
			float f4 = 0.0F;
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer = new StringBuffer();
			stringbuffer.append("insert into TCtestreport(").append("tp_carproduction,tp_carname, tp_carnum, tp_testnum, tp_testtoolnum, tp_testtooltypenum, TCjk_startTime,TCjk_endTime,tp_teststarttime,tp_testendtime)");
			stringbuffer.append("select ").append("tp_carproduction,tp_carname, tp_carnum, tp_testnum, tp_testtoolnum, tp_testtooltypenum, TCjk_startTime,TCjk_endTime,tp_teststarttime,tp_testendtime ");
			stringbuffer.append(" from TCtestcar where 1=1 ");
			stringbuffer.append(" and tp_carname='").append(s1).append("' ");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append(" select @@IDENTITY as id");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset.next();)
				s5 = cachedrowset.getString("id");

			stringbuffer = new StringBuffer();
			f = Tools.dateMinus_gap(s3, s2);
			stringbuffer.append("update TCtestcar set Flag_bgzt='1' where 1=1 ").append(" and tp_testnum='").append(s).append("' ");
			log.info(stringbuffer.toString());
			dbtrans1.addSql(stringbuffer.toString());
			dbtrans1.executeSql();
			stringbuffer = new StringBuffer();
			stringbuffer.append("update TCtestreport set TCjk_totaltime='").append(f).append("'");
			stringbuffer.append("where 1=1 ").append(" and tp_testnum='").append(s).append("' ");
			log.info(stringbuffer.toString());
			dbtrans1.addSql(stringbuffer.toString());
			dbtrans1.executeSql();
			Object obj5 = null;
			String s13 = "0";
			stringbuffer = new StringBuffer();
			stringbuffer.append("select count(id) as cishu  from TCfaultreport ");
			stringbuffer.append(" where 1=1 ");
			stringbuffer.append(" and tp_carname='").append(s1).append("' ");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append(" and tp_faulttype='").append("ÇáÎ¢").append("' ");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset1 = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset1.next();)
				s13 = cachedrowset1.getString("cishu");

			String s14 = "0";
			stringbuffer = new StringBuffer();
			stringbuffer.append("select count(id) as cishu  from TCfaultreport ");
			stringbuffer.append(" where 1=1 ");
			stringbuffer.append(" and tp_carname='").append(s1).append("' ");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append(" and tp_faulttype='").append("Ò»°ã").append("' ");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset2 = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset2.next();)
				s14 = cachedrowset2.getString("cishu");

			String s15 = "0";
			stringbuffer = new StringBuffer();
			stringbuffer.append("select count(id) as cishu  from TCfaultreport ");
			stringbuffer.append(" where 1=1 ");
			stringbuffer.append(" and tp_carname='").append(s1).append("' ");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append(" and tp_faulttype='").append("ÖØ´ó").append("' ");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset3 = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset3.next();)
				s15 = cachedrowset3.getString("cishu");

			String s16 = "0";
			stringbuffer = new StringBuffer();
			stringbuffer.append("select count(id) as cishu  from TCfaultreport ");
			stringbuffer.append(" where 1=1 ");
			stringbuffer.append(" and tp_carname='").append(s1).append("' ");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append(" and tp_faulttype!='").append("ÇáÎ¢").append("' ");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset4 = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset4.next();)
				s16 = cachedrowset4.getString("cishu");

			String s17 = "0";
			stringbuffer = new StringBuffer();
			stringbuffer.append("select count(id) as cishu  from TCfaultreport ");
			stringbuffer.append(" where 1=1 ");
			stringbuffer.append(" and tp_carname='").append(s1).append("' ");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset5 = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset5.next();)
				s17 = cachedrowset5.getString("cishu");

			stringbuffer = new StringBuffer();
			stringbuffer.append("select min(id) as id from TCfaultreport ");
			stringbuffer.append(" where 1=1 ");
			stringbuffer.append(" and tp_carname='").append(s1).append("' ");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset6 = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset6.next();)
				s6 = cachedrowset6.getString("id");

			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from TCfaultreport ");
			stringbuffer.append(" where 1=1 ");
			stringbuffer.append(" and id='").append(s6).append("' ");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset7 = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset7.next();)
			{
				s4 = cachedrowset7.getString("tp_fault_begintime");
				String s7 = cachedrowset7.getString("tp_fault_begintime");
			}

			float f7 = Tools.dateMinus_gap(s4, s2);
			float f8 = 0.0F;
			CachedRowSet cachedrowset10 = null;
			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from TCfaultreport where 1=1 and tp_testnum='").append(s).append("'");
			stringbuffer.append("order by id asc");
			log.info(stringbuffer.toString());
			cachedrowset10 = dbtrans1.getResultBySelect(stringbuffer.toString());
			float f9 = 0.0F;
			do
			{
				if (!cachedrowset10.next())
					break;
				String s8 = cachedrowset10.getString("tp_fault_begintime");
				String s11 = cachedrowset10.getString("TCjk_startTime");
				log.info((new StringBuilder()).append("cur_tp_fault_begintime======11============================").append(s8).toString());
				log.info((new StringBuilder()).append("last_tp_fault_begintime======11============================").append(s11).toString());
				if (cachedrowset10.size() > 0)
				{
					for (int i = 1; i < cachedrowset10.size(); i++)
						if (i > 1)
						{
							float f11 = 0.0F;
							f11 = Tools.dateMinus_gap(s8, s11);
							f9 += f11;
							s11 = s8;
							cachedrowset10.next();
							s8 = cachedrowset10.getString("tp_fault_begintime");
						}

					f8 = f9 / (float)(cachedrowset10.size() - 1);
				}
			} while (true);
			float f10 = 0.0F;
			CachedRowSet cachedrowset11 = null;
			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from TCfaultreport where 1=1 and tp_fault_delay>0");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append("order by id asc");
			log.info(stringbuffer.toString());
			cachedrowset11 = dbtrans1.getResultBySelect(stringbuffer.toString());
			float f12 = 0.0F;
			do
			{
				if (!cachedrowset11.next())
					break;
				String s18 = cachedrowset11.getString("tp_fault_delay");
				float f5 = Float.parseFloat(s18);
				if (cachedrowset11.size() > 0)
				{
					for (int j = 1; j < cachedrowset11.size(); j++)
						if (j > 1)
						{
							f12 += f5;
							cachedrowset11.next();
							String s19 = cachedrowset11.getString("tp_fault_delay");
							f5 = Float.parseFloat(s19);
						}

					f10 = f12 / (float)(cachedrowset11.size() - 1);
				}
			} while (true);
			float f13 = 0.0F;
			CachedRowSet cachedrowset12 = null;
			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from TCfaultreport where 1=1 and tp_fault_work>0");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append("order by id asc");
			log.info(stringbuffer.toString());
			cachedrowset12 = dbtrans1.getResultBySelect(stringbuffer.toString());
			float f14 = 0.0F;
			do
			{
				if (!cachedrowset12.next())
					break;
				String s20 = cachedrowset11.getString("tp_fault_work");
				float f2 = Float.parseFloat(s20);
				if (cachedrowset12.size() > 0)
				{
					int k = 1;
					while (k < cachedrowset12.size()) 
					{
						if (k > 1)
						{
							f14 += f2;
							cachedrowset12.next();
							String s21 = cachedrowset12.getString("tp_fault_work");
							f2 = Float.parseFloat(s21);
						}
						k++;
					}
				}
			} while (true);
			f13 = (f - f14 - f12) / f;
			float f15 = 0.0F;
			stringbuffer = new StringBuffer();
			stringbuffer.append("select min(id) as id from TCfaultreport ");
			stringbuffer.append(" where 1=1 ");
			stringbuffer.append(" and tp_carname='").append(s1).append("' ");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append(" and tp_faulttype!='").append("ÇáÎ¢").append("' ");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset8 = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset8.next();)
				s6 = cachedrowset8.getString("id");

			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from TCfaultreport ");
			stringbuffer.append(" where 1=1 ");
			stringbuffer.append(" and id='").append(s6).append("' ");
			log.info(stringbuffer.toString());
			for (CachedRowSet cachedrowset9 = dbtrans1.getResultBySelect(stringbuffer.toString()); cachedrowset9.next();)
			{
				s4 = cachedrowset9.getString("tp_fault_begintime");
				String s9 = cachedrowset9.getString("tp_fault_begintime");
			}

			f7 = Tools.dateMinus_gap(s4, s2);
			float f16 = 0.0F;
			CachedRowSet cachedrowset13 = null;
			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from TCfaultreport where 1=1 and tp_testnum='").append(s).append("'");
			stringbuffer.append(" and tp_faulttype!='").append("ÇáÎ¢").append("' ");
			stringbuffer.append("order by id asc");
			log.info(stringbuffer.toString());
			cachedrowset13 = dbtrans1.getResultBySelect(stringbuffer.toString());
			f9 = 0.0F;
			do
			{
				if (!cachedrowset13.next())
					break;
				String s10 = cachedrowset13.getString("tp_fault_begintime");
				String s12 = cachedrowset10.getString("TCjk_startTime");
				if (cachedrowset13.size() > 0)
				{
					for (int l = 1; l < cachedrowset13.size(); l++)
						if (l > 1)
						{
							float f18 = 0.0F;
							f18 = Tools.dateMinus_gap(s10, s12);
							f9 += f18;
							s12 = s10;
						}

					f16 = f9 / (float)(cachedrowset13.size() - 1);
				}
			} while (true);
			float f17 = 0.0F;
			cachedrowset11 = null;
			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from TCfaultreport where 1=1 and tp_fault_delay>0");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append(" and tp_faulttype!='").append("ÇáÎ¢").append("' ");
			stringbuffer.append("order by id asc");
			log.info(stringbuffer.toString());
			cachedrowset11 = dbtrans1.getResultBySelect(stringbuffer.toString());
			f12 = 0.0F;
			do
			{
				if (!cachedrowset11.next())
					break;
				String s22 = cachedrowset11.getString("tp_fault_delay");
				float f6 = Float.parseFloat(s22);
				if (cachedrowset11.size() > 0)
				{
					for (int i1 = 1; i1 < cachedrowset11.size(); i1++)
						if (i1 > 1)
						{
							f12 += f6;
							cachedrowset11.next();
							String s23 = cachedrowset11.getString("tp_fault_delay");
							f6 = Float.parseFloat(s23);
						}

					f17 = f12 / (float)(cachedrowset11.size() - 1);
				}
			} while (true);
			float f19 = 0.0F;
			cachedrowset12 = null;
			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from TCfaultreport where 1=1 and tp_fault_work>0");
			stringbuffer.append(" and tp_testnum='").append(s).append("' ");
			stringbuffer.append(" and tp_faulttype!='").append("ÇáÎ¢").append("' ");
			stringbuffer.append("order by id asc");
			log.info(stringbuffer.toString());
			cachedrowset12 = dbtrans1.getResultBySelect(stringbuffer.toString());
			f14 = 0.0F;
			do
			{
				if (!cachedrowset12.next())
					break;
				String s24 = cachedrowset11.getString("tp_fault_work");
				float f3 = Float.parseFloat(s24);
				if (cachedrowset12.size() > 0)
				{
					int j1 = 1;
					while (j1 < cachedrowset12.size()) 
					{
						if (j1 > 1)
						{
							f14 += f3;
							cachedrowset12.next();
							String s25 = cachedrowset12.getString("tp_fault_work");
							f3 = Float.parseFloat(s25);
						}
						j1++;
					}
				}
			} while (true);
			f19 = (f - f14 - f12) / f;
			log.info((new StringBuilder()).append("FAef======11============================").append(f19).toString());
			stringbuffer = new StringBuffer();
			stringbuffer.append("update  TCtestreport set ");
			stringbuffer.append(" Nqw='").append(s13).append("', ");
			stringbuffer.append(" Nyb='").append(s14).append("', ");
			stringbuffer.append(" Nzd='").append(s15).append("', ");
			stringbuffer.append(" Nfqw='").append(s16).append("', ");
			stringbuffer.append(" Ntotal='").append(s17).append("', ");
			stringbuffer.append(" TAfirst='").append(f7).append("', ");
			stringbuffer.append(" TAgzjg='").append(f8).append("', ");
			stringbuffer.append(" TArep='").append(f10).append("', ");
			stringbuffer.append(" TAef ='").append(f13).append("', ");
			stringbuffer.append(" FAfirst='").append(f15).append("', ");
			stringbuffer.append(" FAgzjg='").append(f16).append("', ");
			stringbuffer.append(" FArep='").append(f17).append("', ");
			stringbuffer.append(" FAef ='").append(f19).append("' ");
			stringbuffer.append(" where id=").append(s5);
			log.info(stringbuffer.toString());
			dbtrans1.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans1.executeSql();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
	}

	public String publishPersonalJcbggl(Jcbggl jcbggl)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		boolean flag = false;
		String s = jcbggl.getProp("jsr");
		String as[] = com.shenghao.arch.util.Tools.stringCode(s);
		Object obj = null;
		try
		{
			for (int i = 0; i < as.length; i++)
			{
				StringBuffer stringbuffer = new StringBuffer("insert into TCtestreport(tp_carname, tp_carnum, tp_testnum, tp_testtoolnum,tp_testtooltypenum,tp_testtime, tp_faultcode, tp_fault_stoptime, tp_fault_recovertime, tp_workhour, tp_harvesthour, tp_faulttype, tp_reportoperator,'");
				stringbuffer.append("'").append(jcbggl.getProp("tp_carname")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_carnum")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_testnum")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_testtoolnum")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_testtooltypenum")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_testtime")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_faultcode")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_fault_stoptime")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_fault_recovertime")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_workhour")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_harvesthour")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_faulttype")).append("',");
				stringbuffer.append("'").append(jcbggl.getProp("tp_reportoperator")).append("')");
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
		return jcbggl.getProp("fsr");
	}

	public Query findJcbgglList(Query query)
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
		String s5 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_testtoolnum"));
		String s6 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_testtooltypenum"));
		String s7 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_testtime"));
		String s8 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_faultcode"));
		String s9 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_fault_stoptime"));
		String s10 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_fault_recovertime"));
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
				stringbuffer2.append(" and tp_carproduction ='");
				stringbuffer2.append(s1).append("'");
			}
			if (s2 != null && !s2.equals(""))
			{
				stringbuffer2.append(" and tp_carname ='");
				stringbuffer2.append(s2).append("'");
			}
			if (s3 != null && !s3.equals(""))
			{
				stringbuffer2.append(" and tp_carnum ='");
				stringbuffer2.append(s3).append("'");
			}
			if (s4 != null && !s4.equals(""))
			{
				stringbuffer2.append(" and tp_testnum ='");
				stringbuffer2.append(s4).append("'");
			}
			if (s5 != null && !s5.equals(""))
			{
				stringbuffer2.append(" and tp_testtoolnum ='");
				stringbuffer2.append(s5).append("'");
			}
			if (s6 != null && !s6.equals(""))
			{
				stringbuffer2.append(" and tp_testtooltypenum ='");
				stringbuffer2.append(s6).append("'");
			}
			if (s7 != null && !s7.equals(""))
			{
				stringbuffer2.append(" and tp_testtime ='");
				stringbuffer2.append(s7).append("'");
			}
			if (s8 != null && !s8.equals(""))
			{
				stringbuffer2.append(" and tp_faultcode ='");
				stringbuffer2.append(s8).append("'");
			}
			if (s9 != null && !s9.equals(""))
			{
				stringbuffer2.append(" and tp_fault_stoptime ='");
				stringbuffer2.append(s).append("'");
			}
			if (s10 != null && !s10.equals(""))
			{
				stringbuffer2.append(" and tp_fault_recovertime ='");
				stringbuffer2.append(s10).append("'");
			}
			if (s11 != null && !s11.equals(""))
			{
				stringbuffer2.append(" and tp_workhour ='");
				stringbuffer2.append(s11).append("'");
			}
			if (s12 != null && !s12.equals(""))
			{
				stringbuffer2.append(" and tp_harvesthour ='");
				stringbuffer2.append(s12).append("'");
			}
			if (s13 != null && !s13.equals(""))
			{
				stringbuffer2.append(" and tp_faulttype ='");
				stringbuffer2.append(s13).append("'");
			}
			if (s14 != null && !s14.equals(""))
			{
				stringbuffer2.append(" and tp_reportoperator ='");
				stringbuffer2.append(s14).append("'");
			}
			log.info(stringbuffer2.toString());
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from TCtestreport where 1=1 ");
				stringbuffer.append(stringbuffer2.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from TCtestreport where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from TCtestreport where 1=1 ").append(stringbuffer2.toString()).append(" order by id asc ) ");
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

	public Query findJcbgglList_noPage(Query query)
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
		String s5 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_testtoolnum"));
		String s6 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_testtooltypenum"));
		String s7 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_testtime"));
		String s8 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_faultcode"));
		String s9 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_fault_stoptime"));
		String s10 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_fault_recovertime"));
		String s11 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_workhour"));
		String s12 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_harvesthour"));
		String s13 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_faulttype"));
		String s14 = com.shenghao.arch.util.Tools.nvl((String)hashmap.get("tp_reportoperator"));
		log.info("findJcbgglList_noPage==================================");
		log.info((new StringBuilder()).append("tp_carname==================================").append(s2).toString());
		log.info((new StringBuilder()).append("queryCon.getTotalNum()==================================").append(query.getTotalNum()).toString());
		StringBuffer stringbuffer = null;
		try
		{
			if (query.getTotalNum() == 0)
			{
				stringbuffer = new StringBuffer("select * from TCtestreport where 1=1 ");
				if (s != null && !s.equals(""))
				{
					stringbuffer.append(" and id ='");
					stringbuffer.append(s).append("'");
				}
				if (s1 != null && !s1.equals(""))
				{
					stringbuffer.append(" and tp_carproduction ='");
					stringbuffer.append(s1).append("'");
				}
				if (s2 != null && !s2.equals(""))
				{
					stringbuffer.append(" and tp_carname ='");
					stringbuffer.append(s2).append("'");
				}
				if (s3 != null && !s3.equals(""))
				{
					stringbuffer.append(" and tp_carnum ='");
					stringbuffer.append(s3).append("'");
				}
				if (s4 != null && !s4.equals(""))
				{
					stringbuffer.append(" and tp_testnum ='");
					stringbuffer.append(s4).append("'");
				}
				if (s5 != null && !s5.equals(""))
				{
					stringbuffer.append(" and tp_testtoolnum ='");
					stringbuffer.append(s5).append("'");
				}
				if (s6 != null && !s6.equals(""))
				{
					stringbuffer.append(" and tp_testtooltypenum ='");
					stringbuffer.append(s6).append("'");
				}
				if (s7 != null && !s7.equals(""))
				{
					stringbuffer.append(" and tp_testtime ='");
					stringbuffer.append(s7).append("'");
				}
				if (s8 != null && !s8.equals(""))
				{
					stringbuffer.append(" and tp_faultcode ='");
					stringbuffer.append(s8).append("'");
				}
				if (s9 != null && !s9.equals(""))
				{
					stringbuffer.append(" and tp_fault_stoptime ='");
					stringbuffer.append(s).append("'");
				}
				if (s10 != null && !s10.equals(""))
				{
					stringbuffer.append(" and tp_fault_recovertime ='");
					stringbuffer.append(s10).append("'");
				}
				if (s11 != null && !s11.equals(""))
				{
					stringbuffer.append(" and tp_workhour ='");
					stringbuffer.append(s11).append("'");
				}
				if (s12 != null && !s12.equals(""))
				{
					stringbuffer.append(" and tp_harvesthour ='");
					stringbuffer.append(s12).append("'");
				}
				if (s13 != null && !s13.equals(""))
				{
					stringbuffer.append(" and tp_faulttype ='");
					stringbuffer.append(s13).append("'");
				}
				if (s14 != null && !s14.equals(""))
				{
					stringbuffer.append(" and tp_reportoperator ='");
					stringbuffer.append(s14).append("'");
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

	public Query findPersonalJcbgglList(Query query)
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
				StringBuffer stringbuffer = new StringBuffer("select * from TCtestreport where 1=1 ");
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
			stringbuffer1.append(" * from jcbggl where jcbgglid not in (select top ");
			stringbuffer1.append(i).append(" jcbgglid from jcbggl where jsr = '");
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
			stringbuffer1.append(" order by jcbgglid desc) ");
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
			stringbuffer1.append(s3).append("' order by jcbgglid desc");
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

	public Jcbggl findJcbgglById(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Jcbggl jcbggl = new Jcbggl();
		StringBuffer stringbuffer = new StringBuffer("select * from TCtestreport where jcbgglid ='");
		stringbuffer.append(s).append("'");
		try
		{
			CachedRowSet cachedrowset = dbtransutil.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
				throw new BussinessProcessException("¸ÃÍ¨Öª²»´æÔÚ£¡");
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				jcbggl.setJcbgglid(cachedrowset.getString("jcbgglid"));
				for (int j = 1; j <= i; j++)
					jcbggl.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), com.shenghao.arch.util.Tools.nvl(cachedrowset.getString(j)));

			}
			stringbuffer = new StringBuffer("update jcbggl set remark='1' where jcbgglid=");
			stringbuffer.append("jcbgglid").append("");
			dbtransutil.addSql(stringbuffer.toString());
			dbtransutil.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return jcbggl;
	}

	public String modifyJcbggl(Jcbggl jcbggl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		StringBuffer stringbuffer = null;
		stringbuffer = new StringBuffer();
		stringbuffer.append("update TCtestreport set ");
		stringbuffer.append("tp_carproduction='").append(jcbggl.getProp("tp_carproduction")).append("',");
		stringbuffer.append("tp_carname='").append(jcbggl.getProp("tp_carname")).append("',");
		stringbuffer.append("tp_carnum='").append(jcbggl.getProp("tp_carnum")).append("',");
		stringbuffer.append("tp_testnum='").append(jcbggl.getProp("tp_testnum")).append("',");
		stringbuffer.append("tp_testtoolnum='").append(jcbggl.getProp("tp_testtoolnum")).append("',");
		stringbuffer.append("tp_testtooltypenum='").append(jcbggl.getProp("tp_testtooltypenum")).append("',");
		stringbuffer.append("tp_testtime='").append(jcbggl.getProp("tp_testtime")).append("',");
		stringbuffer.append("tp_faultcode='").append(jcbggl.getProp("tp_faultcode")).append("',");
		stringbuffer.append("tp_fault_stoptime='").append(jcbggl.getProp("tp_fault_stoptime")).append("',");
		stringbuffer.append("tp_fault_recovertime='").append(jcbggl.getProp("tp_fault_recovertime")).append("',");
		stringbuffer.append("tp_workhour='").append(jcbggl.getProp("tp_workhour")).append("',");
		stringbuffer.append("tp_harvesthour='").append(jcbggl.getProp("tp_harvesthour")).append("',");
		stringbuffer.append("tp_faulttype='").append(jcbggl.getProp("tp_faulttype")).append("',");
		stringbuffer.append("tp_reportoperator='").append(jcbggl.getProp("tp_reportoperator")).append("' where id = '");
		stringbuffer.append(jcbggl.getJcbgglid()).append("'");
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
		return jcbggl.getJcbgglid();
	}

	public String deleteJcbggl(String s)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		int i = Integer.parseInt(s);
		StringBuffer stringbuffer = new StringBuffer("delete from TCtestreport where id = ");
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

	public boolean hasNewJcbggl(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("select jcbgglid from jcbggl where jsr = '");
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
