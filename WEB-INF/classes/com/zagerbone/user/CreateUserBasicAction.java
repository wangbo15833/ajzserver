// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   CreateUserBasicAction.java

package com.zagerbone.user;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.shenghao.arch.util.Tools;
import com.zagerbone.util.DBTrans;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.user:
//			User, UserDAOFactory, UserDAO

public class CreateUserBasicAction extends BaseActionSupport
{

	public CreateUserBasicAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserDAO userdao = UserDAOFactory.getDAO();
		try
		{
			User user = (User)processEvent(event);
			String s = userdao.createUserBasic(user);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
		}
		catch (Exception exception)
		{
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode(event.getFunctionID());
			eventresponse.setErrorMessage(exception.getMessage().replace('\n', ' '));
			return eventresponse;
		}
		return eventresponse;
	}

	public Object processEvent(Event event)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		User user = new User();
		HashMap hashmap = event.getBody();
		user.setProp("userid", event.getUserid());
		user.setProp("username", (String)hashmap.get("username"));
		user.setProp("password", (String)hashmap.get("password"));
		user.setProp("realname", (String)hashmap.get("realname"));
		user.setProp("address", (String)hashmap.get("address"));
		user.setProp("email", (String)hashmap.get("email"));
		user.setProp("telephone", (String)hashmap.get("telephone"));
		user.setProp("addition", (String)hashmap.get("addition"));
		user.setProp("realname", (String)hashmap.get("realname"));
		user.setProp("work_unit", Tools.nvl((String)hashmap.get("work_unit")));
		user.setProp("work_unit_name", (String)hashmap.get("work_unit_name"));
		user.setProp("usertype", (String)hashmap.get("usertype"));
		user.setProp("flag", (String)hashmap.get("flag"));
		user.setProp("regdate", (String)hashmap.get("regdate"));
		user.setProp("confirmdate", (String)hashmap.get("confirmdate"));
		user.setProp("confirmpeople", (String)hashmap.get("confirmpeople"));
		user.setProp("lastdate", (String)hashmap.get("lastdate"));
		user.setProp("remark", (String)hashmap.get("remark"));
		user.setProp("userrole_codes", (String)hashmap.get("userrole_codes"));
		if (user.getProp("username").equals(""))
			throw new BussinessProcessException("必须输入用户名！");
		if (user.getProp("realname") == null || user.getProp("realname").equals(""))
			throw new BussinessProcessException("必须输入姓名！");
		if (user.getProp("usertype") == null || user.getProp("usertype").equals(""))
			throw new BussinessProcessException("必须输入身份类别！");
		if (user.getProp("work_unit").equals(""))
			throw new BussinessProcessException("必须输入工作单位！");
		if (user.getProp("password").equals(""))
			throw new BussinessProcessException("必须输入密码！");
		Object obj = null;
		StringBuffer stringbuffer = new StringBuffer("");
		stringbuffer.append("select username from userinfo_tgy where username = '");
		stringbuffer.append((String)hashmap.get("username")).append("'");
		log.info(stringbuffer.toString());
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0)
			{
				log.info("crs is null!");
				log.info((new StringBuilder()).append("============regdate===========================").append((String)hashmap.get("regdate")).toString());
			} else
			{
				throw new BussinessProcessException("该用户已存在,请重新输入用户名！");
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return user;
	}
}
