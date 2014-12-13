// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   EventResponse.java

package com.zagerbone.event;

import com.zagerbone.exception.BussinessProcessException;
import com.zagerbone.sys_right.dao.*;
import com.zagerbone.util.*;
import comp.zagerbone.user.User;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

public class EventResponse
{

	Logger log;
	private boolean successFlag;
	private String errorMessage;
	private String billcodeName;
	private String billcodeValue;
	private String billcodeName_2;
	private String billcodeValue_2;
	private Object body;
	private String functionId;
	private boolean modifyFlag;
	private String edition;
	private String editionValue;

	public EventResponse()
	{
		log = Logger.getLogger(SysTrace.DBLOGGER);
		successFlag = false;
		errorMessage = "";
		billcodeName = "";
		billcodeValue = "";
		billcodeName_2 = "";
		billcodeValue_2 = "";
		body = null;
		functionId = "";
		modifyFlag = false;
		edition = "";
		editionValue = "";
	}

	public EventResponse(String s)
	{
		log = Logger.getLogger(SysTrace.DBLOGGER);
		successFlag = false;
		errorMessage = "";
		billcodeName = "";
		billcodeValue = "";
		billcodeName_2 = "";
		billcodeValue_2 = "";
		body = null;
		functionId = "";
		modifyFlag = false;
		edition = "";
		editionValue = "";
		functionId = s;
		log.info((new StringBuilder()).append("EventResponse\tActionName:").append(s).toString());
	}

	public Object getBody()
	{
		return body;
	}

	public String getEditionValue()
	{
		return editionValue;
	}

	public boolean getModifyFlag()
	{
		return modifyFlag;
	}

	public boolean isSuccessFlag()
	{
		return successFlag;
	}

	public void setBody(Object obj)
	{
		body = obj;
	}

	public void setBillcodeName(String s)
	{
		billcodeName = s;
	}

	public String getBillcodeName()
	{
		return billcodeName;
	}

	public void setBillcodeValue(String s)
	{
		billcodeValue = s;
	}

	public String getBillcodeValue()
	{
		return billcodeValue;
	}

	public void setBillcodeName_2(String s)
	{
		billcodeName_2 = s;
	}

	public String getBillcodeName_2()
	{
		return billcodeName_2;
	}

	public void setBillcodeValue_2(String s)
	{
		billcodeValue_2 = s;
	}

	public String getBillcodeValue_2()
	{
		return billcodeValue_2;
	}

	public void setErrorMessage(String s)
	{
		errorMessage = s;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setEdition(String s)
	{
		edition = s;
	}

	public String getEdition()
	{
		return edition;
	}

	public void setEditionValue(String s)
	{
		editionValue = s;
	}

	public void setSuccessFlag(boolean flag)
	{
		successFlag = flag;
	}

	public void setModifyFlag(boolean flag)
	{
		modifyFlag = flag;
	}

	public String getFunctionId()
	{
		return functionId;
	}

	public void setFunctionId(String s)
	{
		functionId = s;
	}

	public boolean setFunctionId(String s, String s1, String s2)
	{
		boolean flag = true;
		functionId = s;
		String s3 = (new StringBuilder()).append("select * from sys_functionid where function_id ='").append(s).append("'").toString();
		try
		{
			CachedRowSet cachedrowset = Sys_rightDAOFactory.getDAO().getDataBySQL(s3);
			if (cachedrowset.next())
			{
				String s4 = Tools.nvl(cachedrowset.getString("display_actionName"));
				String s5 = Tools.nvl(cachedrowset.getString("display_jndiName"));
				String s6 = Tools.nvl(cachedrowset.getString("display_dirFile"));
				if (s5.equals("0"))
					flag = false;
				else
				if (s5.equals("1"))
					flag = true;
				if (s4.equals("1"))
					log.info((new StringBuilder()).append("ActionName:").append(s).toString());
				if (s6.equals("1"))
					log.info((new StringBuilder()).append("dir+fileName=:").append(s1).append("+").append(s2).toString());
			} else
			{
				log.info((new StringBuilder()).append("ActionName:").append(s).toString());
				log.info((new StringBuilder()).append("dir+fileName=:").append(s1).append("+").append(s2).toString());
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return flag;
	}

	public boolean isRight(HttpServletRequest httpservletrequest)
		throws BussinessProcessException
	{
		boolean flag = false;
		Object obj = null;
		Object obj1 = null;
		String s2 = "";
		String s3 = "";
		DBTrans dbtrans = new DBTrans(s2, (new StringBuilder()).append(functionId).append("--权限判别,").toString());
		try
		{
			dbtrans.beginTransaction();
			String s = (new StringBuilder()).append("select apprightCon from v_funapps, v_funmapping where v_funapps.apptableName =v_funmapping.tablename and v_funmapping.functionid ='").append(functionId).append("'").toString();
			log.info((new StringBuilder()).append("strSql=").append(s).toString());
			CachedRowSet cachedrowset = Sys_rightDAOFactory.getDAO(dbtrans).getDataBySQL(s);
			cachedrowset.next();
			String s4 = Tools.nvl(cachedrowset.getString("apprightCon"));
			if (s4.equals("1"))
			{
				HashMap hashmap = (HashMap)httpservletrequest.getSession().getAttribute("hp_user");
				String s5 = (String)hashmap.get("userid");
				String s1 = (new StringBuilder()).append("select * from qry_user_role_function where functionid='").append(functionId).append("' and userid='").append(s5).append("'").toString();
				log.info((new StringBuilder()).append("strSql=").append(s1).toString());
				CachedRowSet cachedrowset1 = Sys_rightDAOFactory.getDAO(dbtrans).getDataBySQL(s1);
				if (cachedrowset1.size() > 0)
				{
					flag = true;
					log.info((new StringBuilder()).append(functionId).append("--权限判别,判别结果：有操作权限").toString());
				} else
				{
					log.info((new StringBuilder()).append(functionId).append("--权限判别,判别结果：无操作权限").toString());
				}
			} else
			{
				flag = true;
				log.info((new StringBuilder()).append(functionId).append("--权限判别,判别结果：本功能无权限要求").toString());
			}
			dbtrans.commitTransaction();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			dbtrans.rollbackTransaction();
		}
		if (!flag)
			throw new BussinessProcessException("无权限操作！");
		else
			return flag;
	}

	public boolean isRightNeed()
		throws BussinessProcessException
	{
		boolean flag = false;
		Object obj = null;
		Object obj1 = null;
		String s1 = "";
		DBTrans dbtrans = new DBTrans(s1, (new StringBuilder()).append(functionId).append("--权限判别,").toString());
		try
		{
			dbtrans.beginTransaction();
			String s = (new StringBuilder()).append("select appid from v_funmapping where functionid ='").append(functionId).append("'").toString();
			log.info((new StringBuilder()).append("strSql=").append(s).toString());
			CachedRowSet cachedrowset = Sys_rightDAOFactory.getDAO(dbtrans).getDataBySQL(s);
			log.info((new StringBuilder()).append("crs.size=").append(cachedrowset.size()).toString());
			cachedrowset.next();
			String s2 = cachedrowset.getString("appid");
			s = (new StringBuilder()).append("select apprightCon from v_funapps where appid ='").append(s2).append("'").toString();
			cachedrowset = Sys_rightDAOFactory.getDAO(dbtrans).getDataBySQL(s);
			dbtrans.commitTransaction();
			cachedrowset.next();
			String s3 = cachedrowset.getString("apprightCon");
			if (s3.equals("1"))
				flag = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			dbtrans.rollbackTransaction();
		}
		return flag;
	}

	public CachedRowSet getFunapps()
		throws BussinessProcessException
	{
		Object obj = null;
		CachedRowSet cachedrowset = null;
		String s2 = "";
		DBTrans dbtrans = new DBTrans(s2, (new StringBuilder()).append(functionId).append("--权限域判别,").toString());
		try
		{
			dbtrans.beginTransaction();
			String s = (new StringBuilder()).append("select tablename from v_funmapping where functionid ='").append(functionId).append("'").toString();
			cachedrowset = Sys_rightDAOFactory.getDAO(dbtrans).getDataBySQL(s);
			if (cachedrowset.next() && cachedrowset.size() > 0)
			{
				String s3 = Tools.nvl(cachedrowset.getString("tablename"));
				String s1 = (new StringBuilder()).append("select * from v_funapps where apptableName ='").append(s3).append("'").toString();
				System.out.println((new StringBuilder()).append("strSql=").append(s1).toString());
				cachedrowset = Sys_rightDAOFactory.getDAO(dbtrans).getDataBySQL(s1);
			} else
			{
				cachedrowset = null;
			}
			dbtrans.commitTransaction();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			dbtrans.rollbackTransaction();
		}
		return cachedrowset;
	}

	public boolean isDomain(HttpServletRequest httpservletrequest)
		throws BussinessProcessException
	{
		boolean flag5;
		boolean flag6;
		ArrayList arraylist;
		Query query;
		HashMap hashmap;
		String s1;
		DBTrans dbtrans;
		Object obj = null;
		boolean flag = true;
		flag5 = false;
		flag6 = false;
		arraylist = new ArrayList();
		query = (Query)httpservletrequest.getAttribute("queryCon");
		hashmap = (HashMap)query.getConditionBody();
		s1 = query.getWhereConExpr();
		dbtrans = new DBTrans("", (new StringBuilder()).append(functionId).append("--权限域判别,").toString());
		String s2;
		String s4;
		String s5;
		dbtrans.beginTransaction();
		HashMap hashmap1 = (HashMap)httpservletrequest.getSession().getAttribute("hp_user");
		s2 = (String)hashmap1.get("userid");
		String s3 = (String)hashmap1.get("role");
		s4 = (String)hashmap1.get("username");
		s5 = (String)hashmap1.get("work_unit");
		String s = (new StringBuilder()).append("select functionid  from sys_userright where 1=1 and userid='").append(s2).append("'").toString();
		log.info(s);
		Object obj1 = null;
		for (CachedRowSet cachedrowset = Sys_rightDAOFactory.getDAO(dbtrans).getDataBySQL(s); cachedrowset.next(); arraylist.add(cachedrowset.getString("functionid")));
		boolean flag1 = arraylist.contains(functionId);
		if (flag1)
			break MISSING_BLOCK_LABEL_247;
		dbtrans.commitTransaction();
		return false;
		String s6;
		String s9;
		CachedRowSet cachedrowset1;
		s6 = null;
		Object obj2 = null;
		Object obj3 = null;
		s9 = null;
		cachedrowset1 = null;
		cachedrowset1 = getFunapps();
		if (cachedrowset1 != null)
			break MISSING_BLOCK_LABEL_280;
		dbtrans.commitTransaction();
		return false;
		boolean flag4;
		try
		{
			for (; cachedrowset1.next(); log.info((new StringBuilder()).append("app_userName=").append(s9).append(",username_temp=").append(s4).toString()))
			{
				String s7 = Tools.nvl(cachedrowset1.getString("app_departCon"));
				s6 = Tools.nvl(cachedrowset1.getString("app_departName"));
				String s8 = Tools.nvl(cachedrowset1.getString("app_userCon"));
				s9 = Tools.nvl(cachedrowset1.getString("app_userName"));
				if (s7.equals("1"))
					flag5 = true;
				if (s8.equals("1"))
					flag6 = true;
				log.info((new StringBuilder()).append("app_departName=").append(s6).append(",depart_temp=").append(s5).toString());
			}

			log.info((new StringBuilder()).append(functionId).append("--权限域判别,判别结果：域控制要求如下：部门控制域=").append(flag5).append(",用户控制域=").append(flag6).toString());
			String s10 = functionId.substring(0, functionId.lastIndexOf("."));
			String s11 = (new StringBuilder()).append(s10).append(".99").toString();
			String s12 = (new StringBuilder()).append(s10).append(".98").toString();
			if (flag5)
			{
				boolean flag2 = arraylist.contains(s11);
				log.info((new StringBuilder()).append(functionId).append("--权限域判别,部门控制域权限存在=").append(flag2).toString());
				if (!flag2)
					hashmap.put(s6, s5);
			}
			if (flag6)
			{
				boolean flag3 = arraylist.contains(s12);
				log.info((new StringBuilder()).append(functionId).append("--权限域判别,用户控制域权限存在=").append(flag3).toString());
				if (!flag3)
				{
					String s13 = (new StringBuilder()).append("select * from v_funmapping where 1=1 and functionid='").append(s12).append("'").toString();
					log.info(s13);
					CachedRowSet cachedrowset2 = Sys_rightDAOFactory.getDAO(dbtrans).getDataBySQL(s13);
					String s14 = "";
					String s15 = "";
					for (String s16 = ""; cachedrowset2.next(); s16 = null)
					{
						s14 = Tools.nvl(cachedrowset2.getString("funcConMode"));
						s15 = Tools.nvl(cachedrowset2.getString("funcConExpr"));
					}

					if (s14.equals("IN"))
					{
						String s17 = s15.substring(s15.indexOf("select ") + 6, s15.indexOf(" from "));
						s17 = s17.replace(" ", "");
						log.info((new StringBuilder()).append("col_element=").append(s17).toString());
						s1 = (new StringBuilder()).append(s1).append(" and ").append(s17).append(" in (").append(s15).append("'").append(s2).append("')").toString();
						query.setWhereConExpr(s1);
					} else
					{
						hashmap.put(s9, s2);
					}
				}
			}
			flag4 = true;
			dbtrans.commitTransaction();
		}
		catch (Exception exception)
		{
			flag4 = false;
			exception.printStackTrace();
			dbtrans.rollbackTransaction();
		}
		return flag4;
	}

	public boolean isSafe(HttpServletRequest httpservletrequest)
		throws BussinessProcessException
	{
		boolean flag = true;
		String s = (String)httpservletrequest.getAttribute("safetyError");
		if (s.equals("yes"))
		{
			flag = false;
			throw new BussinessProcessException("请先登陆后再操作！");
		} else
		{
			return flag;
		}
	}

	public boolean isRecord(HttpServletRequest httpservletrequest, String s, String s1, String s2)
		throws BussinessProcessException
	{
		boolean flag = false;
		try
		{
			User user = (User)httpservletrequest.getSession().getAttribute("user");
			String s3 = user.getProp("role");
			String s4 = user.getProp("username");
			String s5 = getFunctionId();
			String s6 = (new StringBuilder()).append("insert into sys_record  (time,username,function_id,obj_id,objname,function_map) values(GETDATE() ,'").append(s4).append("','").append(s2).append("','").append(s).append("','").append(s1).append("','").append(s5).append("')  ").toString();
			Sys_rightDAOFactory.getDAO().setDataBySQL(s6);
			flag = true;
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return flag;
	}
}
