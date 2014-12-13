// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ModifyUserBasicAction.java

package com.zagerbone.user;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.user:
//			User, UserDAOFactory, UserDAO

public class ModifyUserBasicAction extends BaseActionSupport
{

	public ModifyUserBasicAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserDAO userdao = UserDAOFactory.getDAO();
		try
		{
			User user = (User)processEvent(event);
			String s = userdao.modifyUserBasic(user);
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
		user.setProp("realname", (String)hashmap.get("realname"));
		user.setProp("company", (String)hashmap.get("company"));
		user.setProp("address", (String)hashmap.get("address"));
		user.setProp("password", (String)hashmap.get("password"));
		user.setProp("email", (String)hashmap.get("email"));
		user.setProp("telephone", (String)hashmap.get("telephone"));
		user.setProp("userroles", (String)hashmap.get("userroles"));
		user.setProp("addition", (String)hashmap.get("addition"));
		user.setProp("work_unit", (String)hashmap.get("work_unit"));
		user.setProp("work_unit_name", (String)hashmap.get("work_unit_name"));
		user.setProp("usertype", (String)hashmap.get("usertype"));
		user.setProp("flag", (String)hashmap.get("flag"));
		user.setProp("regdate", (String)hashmap.get("regdate"));
		user.setProp("confirmdate", (String)hashmap.get("confirmdate"));
		user.setProp("confirmpeople", (String)hashmap.get("confirmpeople"));
		user.setProp("lastdate", (String)hashmap.get("lastdate"));
		user.setProp("remark", (String)hashmap.get("remark"));
		user.setProp("userrole_codes", (String)hashmap.get("userrole_codes"));
		if (user.getProp("realname") == null || user.getProp("realname").equals(""))
			throw new BussinessProcessException("±ÿ–Î ‰»Î–’√˚£°");
		else
			return user;
	}
}
