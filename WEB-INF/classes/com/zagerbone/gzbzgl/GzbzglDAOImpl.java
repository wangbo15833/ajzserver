// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbzglDAOImpl.java

package com.zagerbone.gzbzgl;

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

// Referenced classes of package com.zagerbone.gzbzgl:
//			Gzbzgl, GzbzglDAO

public class GzbzglDAOImpl extends BasicObject
	implements GzbzglDAO
{

	public GzbzglDAOImpl()
	{
	}

	public String createGzbzgl(Gzbzgl gzbzgl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		String s = gzbzgl.getProp("fsr");
		Object obj = null;
		try
		{
			StringBuffer stringbuffer = new StringBuffer("insert into TCfaultstandard(");
			stringbuffer.append("tp_faultcode,tp_faultname,qwbs,qwtime,ybbs,ybtime,zdbs,zdtime,add1,add2,add3,add4,add5");
			stringbuffer.append(")values(");
			stringbuffer.append("'").append(gzbzgl.getProp("tp_faultcode")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("tp_faultname")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("qwbs")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("qwtime")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("ybbs")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("ybtime")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("zdbs")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("zdtime")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("add1")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("add2")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("add3")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("add4")).append("',");
			stringbuffer.append("'").append(gzbzgl.getProp("add5")).append("')");
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

	public String publishPersonalGzbzgl(Gzbzgl gzbzgl)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		boolean flag = false;
		String s = gzbzgl.getProp("jsr");
		String as[] = Tools.stringCode(s);
		Object obj = null;
		try
		{
			for (int i = 0; i < as.length; i++)
			{
				StringBuffer stringbuffer = new StringBuffer("insert into TCfaultstandard(dataCreateTime,Cname, Ctype, Cnum, Cmake, Couttime, Cjtime, Ctestmb, Ctestdh, Tnum, Tyqxh, Tyqnum, Tstime, Ttime, Tmeb, Tmebdh) values(getDate(),'");
				stringbuffer.append("'").append(gzbzgl.getProp("Cname")).append("',");
				stringbuffer.append(gzbzgl.getProp("Ctype")).append("',");
				stringbuffer.append(gzbzgl.getProp("Cmake")).append("',");
				stringbuffer.append(gzbzgl.getProp("Couttime")).append("',");
				stringbuffer.append(gzbzgl.getProp("Cjtime")).append("',");
				stringbuffer.append(gzbzgl.getProp("Ctestmb")).append("',");
				stringbuffer.append(gzbzgl.getProp("Ctestdh")).append("',");
				stringbuffer.append(gzbzgl.getProp("Tnum")).append("',");
				stringbuffer.append(gzbzgl.getProp("Tyqxh")).append("',");
				stringbuffer.append(gzbzgl.getProp("Tyqnum")).append("',");
				stringbuffer.append(gzbzgl.getProp("Tstime")).append("',");
				stringbuffer.append(gzbzgl.getProp("Tmeb")).append("',");
				stringbuffer.append(gzbzgl.getProp("Tmebdh")).append("')");
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
		return gzbzgl.getProp("fsr");
	}

	public Query findGzbzglList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = Tools.nvl((String)hashmap.get("id"));
		String s1 = Tools.nvl((String)hashmap.get("tp_faultcode"));
		String s2 = Tools.nvl((String)hashmap.get("tp_faultname"));
		log.info((new StringBuilder()).append("tp_faultcode==================================").append(s1).toString());
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
				stringbuffer2.append(" and tp_faultcode ='");
				stringbuffer2.append(s1).append("'");
			}
			if (s2 != null && !s2.equals(""))
			{
				stringbuffer2.append(" and tp_faultname ='");
				stringbuffer2.append(s2).append("'");
			}
			log.info(stringbuffer2.toString());
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from TCfaultstandard where 1=1 ");
				stringbuffer.append(stringbuffer2.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from TCfaultstandard where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from TCfaultstandard where 1=1 ").append(stringbuffer2.toString()).append(" order by id asc ) ");
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

	public Query findGzbzglList_noPage(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = Tools.nvl((String)hashmap.get("id"));
		String s1 = Tools.nvl((String)hashmap.get("tp_faultcode"));
		String s2 = Tools.nvl((String)hashmap.get("tp_faultname"));
		log.info("findGzbzglList_noPage==================================");
		log.info((new StringBuilder()).append("tp_faultcode==================================").append(s1).toString());
		log.info((new StringBuilder()).append("queryCon.getTotalNum()==================================").append(query.getTotalNum()).toString());
		StringBuffer stringbuffer = null;
		try
		{
			if (query.getTotalNum() == 0)
			{
				stringbuffer = new StringBuffer("select * from TCfaultstandard where 1=1 ");
				if (s != null && !s.equals(""))
				{
					stringbuffer.append(" and id ='");
					stringbuffer.append(s).append("'");
				}
				if (s1 != null && !s1.equals(""))
				{
					stringbuffer.append(" and tp_faultcode ='");
					stringbuffer.append(s1).append("'");
				}
				if (s2 != null && !s2.equals(""))
				{
					stringbuffer.append(" and tp_faultname ='");
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

	public Query findPersonalGzbzglList(Query query)
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
				StringBuffer stringbuffer = new StringBuffer("select * from TCfaultstandard where 1=1 ");
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
			stringbuffer1.append(" * from gzbzgl where gzbzglid not in (select top ");
			stringbuffer1.append(i).append(" gzbzglid from gzbzgl where jsr = '");
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
			stringbuffer1.append(" order by gzbzglid desc) ");
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
			stringbuffer1.append(s3).append("' order by gzbzglid desc");
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

	public Gzbzgl findGzbzglById(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Gzbzgl gzbzgl = new Gzbzgl();
		StringBuffer stringbuffer = new StringBuffer("select * from TCfaultstandard where gzbzglid ='");
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
				gzbzgl.setGzbzglid(cachedrowset.getString("gzbzglid"));
				for (int j = 1; j <= i; j++)
					gzbzgl.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));

			}
			stringbuffer = new StringBuffer("update gzbzgl set remark='1' where gzbzglid=");
			stringbuffer.append("gzbzglid").append("");
			dbtransutil.addSql(stringbuffer.toString());
			dbtransutil.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return gzbzgl;
	}

	public String modifyGzbzgl(Gzbzgl gzbzgl)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("update TCfaultstandard set ");
		stringbuffer.append("tp_faultcode='").append(gzbzgl.getProp("tp_faultcode")).append("',");
		stringbuffer.append("tp_faultname='").append(gzbzgl.getProp("tp_faultname")).append("',");
		stringbuffer.append("qwbs='").append(gzbzgl.getProp("qwbs")).append("',");
		stringbuffer.append("qwtime='").append(gzbzgl.getProp("qwtime")).append("',");
		stringbuffer.append("ybbs='").append(gzbzgl.getProp("ybbs")).append("',");
		stringbuffer.append("ybtime='").append(gzbzgl.getProp("ybtime")).append("',");
		stringbuffer.append("zdbs='").append(gzbzgl.getProp("zdbs")).append("',");
		stringbuffer.append("zdtime='").append(gzbzgl.getProp("zdtime")).append("',");
		stringbuffer.append("add1='").append(gzbzgl.getProp("add1")).append("',");
		stringbuffer.append("add2='").append(gzbzgl.getProp("add2")).append("',");
		stringbuffer.append("add3='").append(gzbzgl.getProp("add3")).append("',");
		stringbuffer.append("add4='").append(gzbzgl.getProp("add4")).append("',");
		stringbuffer.append("add5='").append(gzbzgl.getProp("add5")).append("'  ");
		stringbuffer.append("where id = '").append(gzbzgl.getGzbzglid()).append("'");
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
		return gzbzgl.getGzbzglid();
	}

	public String deleteGzbzgl(String s)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		int i = Integer.parseInt(s);
		StringBuffer stringbuffer = new StringBuffer("delete from TCfaultstandard where id = ");
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

	public boolean hasNewGzbzgl(String s)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("select gzbzglid from gzbzgl where jsr = '");
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
