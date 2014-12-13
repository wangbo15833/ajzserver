package com.zagerbone.user;

import com.shenghao.arch.baseobject.BasicObject;
import com.shenghao.arch.exception.BussinessProcessException;
import com.shenghao.arch.util.DBTransUtil;
import com.shenghao.arch.util.Tools;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.Query;
import com.zagerbone.util.data.SysWorker;
import java.sql.ResultSetMetaData;
import java.util.*;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

public class UserDAOImpl extends BasicObject
	implements UserDAO
{

	DBTrans transUtil;

	public UserDAOImpl()
	{
		transUtil = null;
	}

	public String registUser(User user)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		String s = SysWorker.getAttribute("userName");
		StringBuffer stringbuffer = new StringBuffer("");
		stringbuffer.append((new StringBuilder()).append("select username from ").append(s).append(" where username = '").toString());
		stringbuffer.append(user.getProp("username")).append("'");
		log.info(stringbuffer.toString());
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
				log.info("crs is null!");
			else
				throw new BussinessProcessException("该用户已存在,请重新输入用户名！");
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		StringBuffer stringbuffer1 = new StringBuffer("");
		stringbuffer1.append((new StringBuilder()).append("insert into ").append(s).append("(username,password,realname, company, address,email,telephone,addition,usertype,flag,regdate,remark) values('").toString());
		stringbuffer1.append(user.getProp("username")).append("','");
		stringbuffer1.append(user.getProp("password")).append("','");
		stringbuffer1.append(user.getProp("realname")).append("','");
		stringbuffer1.append(user.getProp("company")).append("','");
		stringbuffer1.append(user.getProp("address")).append("','");
		stringbuffer1.append(user.getProp("email")).append("','");
		stringbuffer1.append(user.getProp("telephone")).append("','");
		stringbuffer1.append(user.getProp("addition")).append("','");
		stringbuffer1.append(user.getProp("usertype")).append("','");
		stringbuffer1.append(user.getProp("flag")).append("','");
		stringbuffer1.append(user.getProp("regdate")).append("','");
		stringbuffer1.append("待审批").append("',getDate(),'");
		stringbuffer1.append(user.getProp("remark")).append("')");
		log.info(stringbuffer1.toString());
		try
		{
			dbtrans.addSql(stringbuffer1.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
		}
		catch (Exception exception1)
		{
			throw new BussinessProcessException(exception1.getMessage());
		}
		return user.getProp("username");
	}

	public Query findUserList(Query query)
		throws BussinessProcessException
	{
		String s = SysWorker.getAttribute("userName");
		StringBuffer stringbuffer = new StringBuffer("");
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s1 = Tools.nvl((String)hashmap.get("work_unit"));
		String s2 = Tools.nvl((String)hashmap.get("username"));
		String s3 = Tools.nvl((String)hashmap.get("realname"));
		String s4 = Tools.nvl((String)hashmap.get("usertype"));
		String s5 = Tools.nvl((String)hashmap.get("userid"));
		Object obj1 = null;
		Object obj2 = null;
		try
		{
			StringBuffer stringbuffer3 = new StringBuffer();
			if (s1 != null && !s1.equals(""))
			{
				stringbuffer3.append(" and work_unit like '%");
				stringbuffer3.append(s1).append("%'");
			}
			if (s5 != null && !s5.equals(""))
			{
				stringbuffer3.append(" and userid like '%");
				stringbuffer3.append(s5).append("%'");
			}
			if (s3 != null && !s3.equals(""))
			{
				stringbuffer3.append(" and realname like '%");
				stringbuffer3.append(s3).append("%'");
			}
			if (s4 != null && !s4.equals(""))
			{
				stringbuffer3.append(" and usertype like '%");
				stringbuffer3.append(s4).append("%'");
			}
			if (s2 != null && !s2.equals(""))
			{
				stringbuffer3.append(" and username like '%");
				stringbuffer3.append(s2).append("%'");
			}
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer1 = new StringBuffer((new StringBuilder()).append("select * from ").append(s).append(" where 1=1 ").toString());
				stringbuffer1.append(stringbuffer3.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer1.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer2 = new StringBuffer();
			stringbuffer2.append("select top ").append(query.getRowsPerPage()).append((new StringBuilder()).append(" * from  ").append(s).append("  where id not in ").toString());
			stringbuffer2.append("(select top ").append(i).append((new StringBuilder()).append(" id from  ").append(s).append("  where 1=1 ").toString()).append(stringbuffer3.toString()).append(" order by flag asc ) ");
			stringbuffer2.append(stringbuffer3.toString());
			stringbuffer2.append("  order by flag asc ");
			query.setSql(stringbuffer2.toString());
			log.info(stringbuffer2.toString());
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer2.toString());
			query.setConditionBody(cachedrowset);
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return query;
	}

	public String loginUser(User user)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		StringBuffer stringbuffer = new StringBuffer("");
		String s = SysWorker.getAttribute("userName");
		String s1 = Tools.nvl(user.getProp("username"));
		String s2 = Tools.nvl(user.getProp("password"));
		String s3 = (new StringBuilder()).append(s1).append(s2).toString();
		if (s3.indexOf(" ( ") != -1)
			throw new BussinessProcessException("用户输入(不正常！");
		if (s3.indexOf(" )") != -1)
			throw new BussinessProcessException("用户输入)不正常！");
		if (s3.indexOf(" or ") != -1)
			throw new BussinessProcessException("用户输入or不正常！");
		if (s3.indexOf(" and ") != -1)
			throw new BussinessProcessException("用户输入and不正常！");
		if (s3.indexOf(" = ") != -1)
			throw new BussinessProcessException("用户输入=不正常！");
		stringbuffer.append((new StringBuilder()).append("select userid,password,flag,logintimes from ").append(s).append(" where 1=1 ").toString());
		stringbuffer.append(" and username = '").append(user.getProp("username")).append("'");
		log.info(stringbuffer.toString());
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
				throw new BussinessProcessException("该用户不存在！");
			if (cachedrowset.next())
			{
				user.setProp("userid", cachedrowset.getString("userid"));
				String s4 = cachedrowset.getString("password");
				String s5 = Tools.nvl(cachedrowset.getString("flag"));
				if (!s5.equals("正常"))
					throw new BussinessProcessException("用户状态不正常！");
				if (!s4.equals(user.getProp("password")))
					throw new BussinessProcessException("用户口令不正确！");
				StringBuffer stringbuffer1 = new StringBuffer((new StringBuilder()).append("update ").append(s).append(" set lastip='").toString());
				stringbuffer1.append(user.getProp("lastip")).append("',");
				stringbuffer1.append("lastdate = getDate(),logintimes = logintimes+1 where userid = '");
				stringbuffer1.append(user.getProp("userid")).append("'");
				log.info(stringbuffer1.toString());
				dbtrans.addSql(stringbuffer1.toString());
				boolean flag1 = dbtrans.executeSql();
				if (!flag1)
					throw new BussinessProcessException(dbtrans.getErrorMessage());
			}
		}
		catch (Exception exception)
		{
			StringBuffer stringbuffer2 = new StringBuffer("");
			stringbuffer2.append("select userid,password,flag,logintimes from userinfo where username = '");
			stringbuffer2.append(user.getProp("username")).append("'");
			log.info(stringbuffer2.toString());
			try
			{
				CachedRowSet cachedrowset1 = dbtrans.getResultBySelect(stringbuffer2.toString());
				if (cachedrowset1 == null || cachedrowset1.size() == 0)
					throw new BussinessProcessException("该用户不存在！");
				if (cachedrowset1.next())
				{
					user.setProp("userid", cachedrowset1.getString("userid"));
					String s6 = cachedrowset1.getString("password");
					String s7 = Tools.nvl(cachedrowset1.getString("flag"));
					if (!s7.equals("正常"))
						throw new BussinessProcessException("用户状态不正常！");
					if (!s6.equals(user.getProp("password")))
						throw new BussinessProcessException("用户口令不正确！");
					StringBuffer stringbuffer3 = new StringBuffer("update userinfo set lastip='");
					stringbuffer3.append(user.getProp("lastip")).append("',");
					stringbuffer3.append("lastdate = getDate(),logintimes = logintimes+1 where userid = '");
					stringbuffer3.append(user.getProp("userid")).append("'");
					log.info(stringbuffer3.toString());
					dbtrans.addSql(stringbuffer3.toString());
					boolean flag2 = dbtrans.executeSql();
					if (!flag2)
						throw new BussinessProcessException(dbtrans.getErrorMessage());
				}
			}
			catch (Exception exception1)
			{
				exception1.printStackTrace();
				throw new BussinessProcessException(exception1.getMessage());
			}
			
		}
		return user.getProp("userid");
	}

	public User findUserById(String s)
		throws BussinessProcessException
	{
		HashMap hashmap = new HashMap();
		User user = null;
		try
		{
			user = findUserById(s, hashmap);
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user;
	}

	public User findUserById(String s, HashMap hashmap)
		throws BussinessProcessException
	{
		CachedRowSet cachedrowset;
		User user;
		Exception exception3;
		DBTrans dbtrans = new DBTrans();
		cachedrowset = null;
		user = new User();
		if (hashmap == null)
			hashmap = new HashMap();
		StringBuffer stringbuffer = new StringBuffer((new StringBuilder()).append("select * from ").append(SysWorker.getAttribute("userName")).append(" where userid = '").toString());
		stringbuffer.append(s).append("'");
		log.info(stringbuffer.toString());
		try
		{
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
				throw new BussinessProcessException("该用户不存在！");
		}
		catch (Exception exception)
		{
			stringbuffer = new StringBuffer("select * from userinfo where userid = '");
			stringbuffer.append(s).append("'");
			log.info(stringbuffer.toString());
		}
		try
		{
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			log.info((new StringBuilder()).append("strSql.toString()==============================================================").append(stringbuffer.toString()).toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
				throw new BussinessProcessException("该用户不存在！");
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				for (int j = 1; j <= i; j++)
				{
					user.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
					hashmap.put(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
				}

			}
		}
		catch (Exception exception2)
		{
			throw new BussinessProcessException(exception2.getMessage());
		}
		finally { }
		try
		{
			cachedrowset.close();
		}
		catch (Exception exception4) { }
		return user;
	}

	public HashMap find_hp_user_ByByusername_password(String s, String s1)
		throws BussinessProcessException
	{
		HashMap hashmap = new HashMap();
		try
		{
			DBTransUtil dbtransutil = new DBTransUtil();
			CachedRowSet cachedrowset = null;
			StringBuffer stringbuffer = null;
			String s2 = SysWorker.getAttribute("userName");
			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from ").append(s2).append(" where 1=1 ");
			stringbuffer.append(" and username = '").append(s).append("' ");
			stringbuffer.append(" and password = '").append(s1).append("' ");
			log.info(stringbuffer.toString());
			if (s.indexOf("'") != -1)
				throw new BussinessProcessException("存在危险字符！");
			cachedrowset = dbtransutil.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
				throw new BussinessProcessException("该用户不存在！");
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				for (int j = 1; j <= i; j++)
					hashmap.put(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));

			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return hashmap;
	}

	public ArrayList find_functionids(HashMap hashmap)
		throws BussinessProcessException
	{
		if (hashmap == null)
			return null;
		Object obj = null;
		ArrayList arraylist = new ArrayList();
		String s1 = (String)hashmap.get("userroles");
		String as[] = s1.split(",");
label0:
		for (int i = 0; i < as.length; i++)
		{
			String s = (new StringBuilder()).append("select functionids from v_funrole where role_id='").append(as[i]).append("'").toString();
			CachedRowSet cachedrowset = getDataBySQL(s);
			Object obj1 = null;
			try
			{
				do
				{
					if (!cachedrowset.next())
						continue label0;
					String s2 = cachedrowset.getString("functionids");
					String as1[] = s2.split(",");
					int j = 0;
					while (j < as1.length) 
					{
						arraylist.add(as1[j]);
						j++;
					}
				} while (true);
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		}

		return arraylist;
	}

	public String confirmUser(User user)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		try
		{
			String s = SysWorker.getAttribute("userName");
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer = new StringBuffer((new StringBuilder()).append("update ").append(s).append(" set flag='正常',confirmdate = getDate(),confirmpeople = '").toString());
			stringbuffer.append(user.getProp("confirmpeople")).append("' where userid ='");
			stringbuffer.append(user.getProp("userid")).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user.getProp("userid");
	}

	public String forbidUser(User user)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		try
		{
			String s = SysWorker.getAttribute("userName");
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer = new StringBuffer((new StringBuilder()).append("update ").append(s).append(" set flag='已禁用',confirmdate = getDate(),confirmpeople = '").toString());
			stringbuffer.append(user.getProp("confirmpeople")).append("' where userid ='");
			stringbuffer.append(user.getProp("userid")).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user.getProp("userid");
	}

	public String deleteUser(String s)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		try
		{
			String s1 = SysWorker.getAttribute("userName");
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer = new StringBuffer((new StringBuilder()).append("delete from ").append(s1).append(" where userid ='").toString());
			stringbuffer.append(s).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s;
	}

	public String addPrivilege(User user)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		String s = user.getProp("userid");
		try
		{
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer = new StringBuffer("delete from user_function where userid ='");
			stringbuffer.append(s).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
			HashMap hashmap = user.getPrivlist();
			StringBuffer stringbuffer1;
			for (Iterator iterator = hashmap.keySet().iterator(); iterator.hasNext(); dbtrans.addSql(stringbuffer1.toString()))
			{
				String s1 = (String)iterator.next();
				String s2 = (String)hashmap.get(s1);
				stringbuffer1 = new StringBuffer("");
				stringbuffer1 = new StringBuffer("insert into user_function(userid,functionid,functionname) values('");
				stringbuffer1.append(s).append("','");
				stringbuffer1.append(s1).append("','");
				stringbuffer1.append(s2).append("')");
				log.info(stringbuffer1.toString());
			}

			flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s;
	}

	public String findPrivilege(String s)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		StringBuffer stringbuffer = new StringBuffer("");
		try
		{
			StringBuffer stringbuffer1 = new StringBuffer("");
			stringbuffer1 = new StringBuffer("select * from user_function where userid = '");
			stringbuffer1.append(s).append("'");
			log.info(stringbuffer1.toString());
			for (CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer1.toString()); cachedrowset.next(); stringbuffer.append(cachedrowset.getString("functionname")).append(","));
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return stringbuffer.toString();
	}

	public String modifyPassword(User user)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		try
		{
			String s = SysWorker.getAttribute("userName");
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer = new StringBuffer((new StringBuilder()).append("update ").append(s).append(" set username = '").toString());
			stringbuffer.append(user.getProp("username")).append("',password ='");
			stringbuffer.append(user.getProp("password")).append("' where userid ='");
			stringbuffer.append(user.getProp("userid")).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user.getProp("userid");
	}

	public String createUserBasic(User user)
		throws BussinessProcessException
	{
		String s = SysWorker.getAttribute("userName");
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		StringBuffer stringbuffer = new StringBuffer("");
		stringbuffer.append((new StringBuilder()).append("insert into ").append(s).append("(").toString());
		stringbuffer.append("userid,username, password, realname, company, address, email, telephone, addition, work_unit, work_unit_name, usertype, flag, regdate, ");
		stringbuffer.append("confirmdate, confirmpeople, lastdate, logintimes, lastip, remark, userroles, worker_code, userrole_codes)");
		stringbuffer.append("values('");
		stringbuffer.append(Tools.nvl(user.getProp("userid"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("username"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("password"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("realname"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("company"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("address"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("email"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("telephone"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("addition"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("work_unit"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("work_unit_name"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("usertype"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("flag"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("regdate"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("confirmdate"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("confirmpeople"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("lastdate"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("logintimes"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("lastip"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("remark"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("userroles"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("worker_code"))).append("','");
		stringbuffer.append(Tools.nvl(user.getProp("userrole_codes"))).append("' )");
		log.info(stringbuffer.toString());
		try
		{
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append((new StringBuilder()).append("update ").append(s).append(" set userid = id").toString());
			dbtrans.addSql(stringbuffer1.toString());
			flag1 = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user.getProp("userid");
	}

	public Query findUserBasicManageList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = Tools.nvl((String)hashmap.get("name"));
		String s1 = Tools.nvl((String)hashmap.get("workgroup"));
		Object obj1 = null;
		try
		{
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from user_basic where 1=1 ");
				if (s != null && !s.equals(""))
				{
					stringbuffer.append(" and name like '%");
					stringbuffer.append(s).append("%'");
				}
				if (s1 != null && !s1.equals(""))
				{
					stringbuffer.append(" and workgroup like '%");
					stringbuffer.append(s1).append("%'");
				}
				stringbuffer.append(" and state = '1'");
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer("select top ");
			stringbuffer1.append(query.getRowsPerPage());
			stringbuffer1.append(" * from user_basic where userid not in (select top ");
			stringbuffer1.append(i).append(" userid from user_basic where state = '1'");
			if (s != null && !s.equals(""))
			{
				stringbuffer1.append(" and name like '%");
				stringbuffer1.append(s).append("%'");
			}
			if (s1 != null && !s1.equals(""))
			{
				stringbuffer1.append(" and workgroup like '%");
				stringbuffer1.append(s1).append("%'");
			}
			stringbuffer1.append(" order by flag asc) ");
			if (s != null && !s.equals(""))
			{
				stringbuffer1.append(" and name like '%");
				stringbuffer1.append(s).append("%'");
			}
			if (s1 != null && !s1.equals(""))
			{
				stringbuffer1.append(" and workgroup like '%");
				stringbuffer1.append(s1).append("%'");
			}
			stringbuffer1.append(" and state = '1' order by flag asc");
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

	public Query findUserBasicList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		String s = Tools.nvl((String)hashmap.get("name"));
		String s1 = Tools.nvl((String)hashmap.get("workgroup"));
		Object obj1 = null;
		try
		{
			if (query.getTotalNum() == 0)
			{
				StringBuffer stringbuffer = new StringBuffer("select * from user_basic where flag='正常' ");
				if (s != null && !s.equals(""))
				{
					stringbuffer.append(" and name like '%");
					stringbuffer.append(s).append("%'");
				}
				if (s1 != null && !s1.equals(""))
				{
					stringbuffer.append(" and workgroup like '%");
					stringbuffer.append(s1).append("%'");
				}
				stringbuffer.append(" and state = '1'");
				query.setTotalNum(dbtrans.getRecNumBySelect(stringbuffer.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer("select top ");
			stringbuffer1.append(query.getRowsPerPage());
			stringbuffer1.append(" * from user_basic where userid not in (select top ");
			stringbuffer1.append(i).append(" userid from user_basic where flag='正常' and state='1'");
			if (s != null && !s.equals(""))
			{
				stringbuffer1.append(" and name like '%");
				stringbuffer1.append(s).append("%'");
			}
			if (s1 != null && !s1.equals(""))
			{
				stringbuffer1.append(" and workgroup like '%");
				stringbuffer1.append(s1).append("%'");
			}
			stringbuffer1.append(" order by flag,name) ");
			if (s != null && !s.equals(""))
			{
				stringbuffer1.append(" and name like '%");
				stringbuffer1.append(s).append("%'");
			}
			if (s1 != null && !s1.equals(""))
			{
				stringbuffer1.append(" and workgroup like '%");
				stringbuffer1.append(s1).append("%'");
			}
			stringbuffer1.append(" and flag='正常'and state='1' order by flag,name");
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

	public User findUserBasicById(String s)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		User user = new User();
		boolean flag = false;
		Object obj = null;
		try
		{
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer = new StringBuffer("select * from user_basic where userid ='");
			stringbuffer.append(s).append("' and state = '1'");
			log.info(stringbuffer.toString());
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
				throw new BussinessProcessException("您还没有填写个人信息,请到成员管理中填写个人信息！");
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				for (int j = 1; j <= i; j++)
					user.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));

			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user;
	}

	public String confirmUserBasic(User user)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		try
		{
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer = new StringBuffer("update userinfo set flag='正常',confirmdate = getDate(),confirmpeople = '");
			stringbuffer.append(user.getProp("confirmpeople")).append("' where userid ='");
			stringbuffer.append(user.getProp("userid")).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user.getProp("userid");
	}

	public String modifyUserBasic(User user)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		try
		{
			String s = SysWorker.getAttribute("userName");
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer = new StringBuffer((new StringBuilder()).append("update ").append(s).append(" set ").toString());
			stringbuffer.append("username ='").append(user.getProp("username")).append("',");
			stringbuffer.append("realname ='").append(user.getProp("realname")).append("',");
			stringbuffer.append("company ='").append(user.getProp("company")).append("',");
			stringbuffer.append("address ='").append(user.getProp("address")).append("',");
			stringbuffer.append("email ='").append(user.getProp("email")).append("',");
			stringbuffer.append("telephone ='").append(user.getProp("telephone")).append("',");
			stringbuffer.append("addition ='").append(user.getProp("addition")).append("'");
			stringbuffer.append("where userid ='").append(user.getProp("userid")).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user.getProp("userid");
	}

	public String modifyUserToFriend(String s, String s1)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		Object obj = null;
		try
		{
			StringBuffer stringbuffer = new StringBuffer("");
			stringbuffer = new StringBuffer("select * from  user_basic where userid ='");
			stringbuffer.append(s).append("'");
			log.info(stringbuffer.toString());
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() <= 0)
				throw new BussinessProcessException("没有该成员记录！");
			cachedrowset.next();
			stringbuffer = new StringBuffer("delete from user_basic where userid ='");
			stringbuffer.append(s).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			stringbuffer = new StringBuffer("insert into schoolmate(name,sex,origin,gradutetime,education,tutor,telephone,email,flag,djr,djrq) values('");
			stringbuffer.append(cachedrowset.getString("name")).append("','");
			stringbuffer.append(cachedrowset.getString("sex")).append("','");
			stringbuffer.append(cachedrowset.getString("origin")).append("','");
			stringbuffer.append(cachedrowset.getString("graduatetime")).append("','");
			stringbuffer.append(cachedrowset.getString("type")).append("','");
			stringbuffer.append(cachedrowset.getString("tutor")).append("','");
			stringbuffer.append(cachedrowset.getString("fixphone")).append("','");
			stringbuffer.append(cachedrowset.getString("email")).append("','");
			stringbuffer.append("待审批").append("','");
			stringbuffer.append(s1).append("',");
			stringbuffer.append("getDate()").append(")");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			if (!flag1)
				throw new BussinessProcessException(dbtrans.getErrorMessage());
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return s;
	}

	public String createUserBasicExcel()
		throws BussinessProcessException
	{
		return null;
	}

	public CachedRowSet getDataBySQL(String s)
		throws BussinessProcessException
	{
		return findBySQL(transUtil, s);
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
}
