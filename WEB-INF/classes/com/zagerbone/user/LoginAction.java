// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LoginAction.java

package com.zagerbone.user;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
//import com.zagerbone.sys_display.dao.Sys_displayDAOFactory;
import com.zagerbone.util.*;
import com.zagerbone.util.data.SysWorker;
import java.io.PrintStream;
import java.util.*;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.user:
//			User, UserDAOFactory, UserDAO

public class LoginAction extends BaseActionSupport
{

	public LoginAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserDAO userdao = UserDAOFactory.getDAO();
		DBTrans dbtrans = new DBTrans("");
		log.info("LoginAction1======================================");
		try
		{
			dbtrans.beginTransaction();
			//com.zagerbone.sys_display.dao.Sys_displayDAOImpl sys_displaydaoimpl = Sys_displayDAOFactory.getDAO(dbtrans);
			HashMap hashmap = new HashMap();
			User user = (User)processEvent(event);
			String s = user.getProp("companyName");
			log.info((new StringBuilder()).append("companyName=").append(s).toString());
			SysWorker.setHp_worker(s);
			String s1 = userdao.loginUser(user);
			log.info((new StringBuilder()).append("userid=").append(s1).toString());
			user = userdao.findUserById(s1, hashmap);
			String s2 = event.getCurip();
			hashmap.put("lastip", s2);
			String s3 = user.getProp("work_unit");
			log.info((new StringBuilder()).append("work_unit=").append(s3).toString());
			ArrayList arraylist = new ArrayList();
			Query query = new Query();
			HashMap hashmap1 = new HashMap();
			hashmap1.put("TakeEffect", "1");
			query.setConditionBody(hashmap1);
			Object obj = null;
			Object obj1 = null;
			Object obj2 = null;
			Object obj3 = null;
			Object obj4 = null;
			HashMap hashmap2 = new HashMap();
			hashmap2.put("hp_user", hashmap);
			eventresponse.setGeneralBody(hashmap2);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(user);
			System.out.println((new StringBuilder()).append("hp_user=").append(hashmap).toString());
			System.out.println((new StringBuilder()).append("username=").append(Tools.nvl((String)hashmap.get("username"))).toString());
			dbtrans.commitTransaction();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode(event.getFunctionID());
			eventresponse.setErrorMessage(exception.getMessage().replace('\n', ' '));
			dbtrans.rollbackTransaction();
			return eventresponse;
		}
		log.info((new StringBuilder()).append("LoginAction2======================= returnValue.getSuccessFlag()===============").append(eventresponse.isSuccessFlag()).toString());
		return eventresponse;
	}

	public Object processEvent(Event event)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		User user = new User();
		HashMap hashmap = event.getBody();
		log.info((new StringBuilder()).append("hashBody=").append(hashmap).toString());
		user.setProp("companyName", (String)hashmap.get("companyName"));
		user.setProp("username", (String)hashmap.get("username"));
		user.setProp("password", (String)hashmap.get("password"));
		user.setProp("lastip", event.getCurip());
		if (user.getProp("username") == null || user.getProp("username").equals(""))
			throw new BussinessProcessException("必须输入用户名！");
		if (user.getProp("password") == null || user.getProp("password").equals(""))
			throw new BussinessProcessException("必须输入密码！");
		Object obj = null;
		StringBuffer stringbuffer = new StringBuffer("");
		stringbuffer.append("select flag from userinfo where username = '");
		stringbuffer.append((String)hashmap.get("username")).append("'");
		log.info(stringbuffer.toString());
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset.next())
			{
				String s = Tools.nvl(cachedrowset.getString("flag"));
				if (!s.equals("正常"))
					throw new BussinessProcessException("管理员没有开启该用户使用权限，请与单位管理员联系！");
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user;
	}

	public String toStr(HashSet hashset)
	{
		String s = "";
		HashSet hashset1 = hashset;
		for (Iterator iterator = hashset1.iterator(); iterator.hasNext();)
		{
			String s1 = (String)iterator.next();
			s = (new StringBuilder()).append(s).append(",'").append(s1).append("'").toString();
		}

		if (s.length() > 1)
			return s.substring(1);
		else
			return "''";
	}
}
