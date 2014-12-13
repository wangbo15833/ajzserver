package com.zagerbone.user;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;

public class DeleteUserAction extends BaseActionSupport
{

	public DeleteUserAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserDAO userdao = UserDAOFactory.getDAO();
		try
		{
			log.info("userid11111===================================");
			String s = (String)processEvent(event);
			String s1 = userdao.deleteUser(s);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s1);
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
		log.info((new StringBuilder()).append("userid===================================").append(hashmap.get("userid")).toString());
		if (user.getProp("userid") == null || user.getProp("userid").equals(""))
			throw new BussinessProcessException("参数错误，userid不能为空！");
		else
			return user.getProp("userid");
	}
}