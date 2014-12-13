package com.zagerbone.user;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;

public class ConfirmUserBasicAction extends BaseActionSupport
{

	public ConfirmUserBasicAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserDAO userdao = UserDAOFactory.getDAO();
		try
		{
			User user = (User)processEvent(event);
			String s = userdao.confirmUserBasic(user);
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
		user.setProp("spr", event.getRealname());
		if (user.getProp("userid") == null || user.getProp("userid").equals(""))
			throw new BussinessProcessException("参数错误，userid不能为空！");
		else
			return user;
	}
}
