// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RegistUserAction.java

package com.zagerbone.user;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.user:
//			User, UserDAOFactory, UserDAO

public class RegistUserAction extends BaseActionSupport
{

	public RegistUserAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserDAO userdao = UserDAOFactory.getDAO();
		try
		{
			User user = (User)processEvent(event);
			String s = userdao.registUser(user);
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
		User user = new User();
		HashMap hashmap = event.getBody();
		user.setProp("username", (String)hashmap.get("username"));
		user.setProp("password", (String)hashmap.get("password"));
		user.setProp("repassword", (String)hashmap.get("repassword"));
		user.setProp("realname", (String)hashmap.get("realname"));
		user.setProp("usertype", (String)hashmap.get("usertype"));
		user.setProp("email", (String)hashmap.get("email"));
		user.setProp("remark", (String)hashmap.get("remark"));
		if (user.getProp("username") == null || user.getProp("username").equals(""))
			throw new BussinessProcessException("必须输入用户名！");
		if (user.getProp("password") == null || user.getProp("password").equals(""))
			throw new BussinessProcessException("必须输入密码！");
		if (user.getProp("repassword") == null || user.getProp("repassword").equals(""))
			throw new BussinessProcessException("必须输入密码！");
		if (!user.getProp("password").equals(user.getProp("repassword")))
			throw new BussinessProcessException("密码输入不一致！");
		if (user.getProp("realname") == null || user.getProp("realname").equals(""))
			throw new BussinessProcessException("必须输入真实姓名！");
		if (user.getProp("usertype") == null || user.getProp("usertype").equals(""))
			throw new BussinessProcessException("必须选择身份类型！");
		if (user.getProp("email") == null || user.getProp("email").equals(""))
			throw new BussinessProcessException("必须输入email！");
		else
			return user;
	}
}
