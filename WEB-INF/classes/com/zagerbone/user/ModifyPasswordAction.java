// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ModifyPasswordAction.java

package com.zagerbone.user;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.user:
//			User, UserDAOFactory, UserDAO

public class ModifyPasswordAction extends BaseActionSupport
{

	public ModifyPasswordAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserDAO userdao = UserDAOFactory.getDAO();
		try
		{
			User user = (User)processEvent(event);
			String s = userdao.modifyPassword(user);
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
		user.setProp("userid", (String)hashmap.get("userid"));
		user.setProp("username", (String)hashmap.get("username"));
		user.setProp("password", (String)hashmap.get("password"));
		user.setProp("repassword", (String)hashmap.get("repassword"));
		if (user.getProp("userid") == null || user.getProp("userid").equals(""))
			throw new BussinessProcessException("参数错误，userid不能为空！");
		if (user.getProp("username") == null || user.getProp("username").equals(""))
			throw new BussinessProcessException("用户名不能为空！");
		if (user.getProp("password") == null || user.getProp("password").equals(""))
			throw new BussinessProcessException("密码不能为空！");
		if (!user.getProp("password").equals(user.getProp("repassword")))
			throw new BussinessProcessException("密码输入不一致！");
		else
			return user;
	}
}
