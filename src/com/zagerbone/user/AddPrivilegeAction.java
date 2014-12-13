package com.zagerbone.user;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;

public class AddPrivilegeAction extends BaseActionSupport
{

	public AddPrivilegeAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserDAO userdao = UserDAOFactory.getDAO();
		try
		{
			User user = (User)processEvent(event);
			String s = userdao.addPrivilege(user);
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
		String as[] = (String[])(String[])hashmap.get("roleid");
		HashMap hashmap1 = new HashMap();
		for (int i = 0; i < as.length; i++)
		{
			String s = as[i];
			String s1 = "";
			if (s.equals("F00.00.00.00"))
				s1 = "系统管理";
			else
			if (s.equals("F00.00.02.00"))
				s1 = "通知管理";
			else
			if (s.equals("F00.00.04.00"))
				s1 = "校友管理";
			else
			if (s.equals("F00.00.05.00"))
				s1 = "书籍管理";
			else
			if (s.equals("F00.00.06.00"))
				s1 = "论文管理";
			else
			if (s.equals("F00.00.07.00"))
				s1 = "采购管理";
			else
			if (s.equals("F00.00.08.00"))
				s1 = "费用管理";
			else
			if (s.equals("F00.00.09.00"))
				s1 = "科研管理";
			if (!s.equals("123") && !s.equals("234"))
				hashmap1.put(s, s1);
		}

		user.setPrivlist(hashmap1);
		if (user.getProp("userid") == null || user.getProp("userid").equals(""))
			throw new BussinessProcessException("参数错误，userid不能为空！");
		else
			return user;
	}
}
