package com.zagerbone.signup;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;

public class SignupCreateActionMobile extends BaseActionSupport
{

	public SignupCreateActionMobile()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		SignupDAO signupdao = SignupDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== SignupCreateActionMobile mode is "+ mode+" ===***");
		try
		{
			log.info("***===ÊÖ»úÇ©µ½ SignupCreateActionMobile ===***");
			Signup signup = (Signup)processEvent(event);
			signup.setProp("signtime",Tools.getCurrentDate_hms());
			String s1 = signupdao.createSignup(signup);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s1);
			System.out.println("*** === step 2 in SignupModifyAction === ***");
			request.setAttribute("flag1","true");
		}
		catch (Exception exception)
		{
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode("601");
			eventresponse.setErrorMessage(exception.getMessage().replace('\n', ' '));
			return eventresponse;
		}
		return eventresponse;
	}

	public Object processEvent(Event event)
		throws BussinessProcessException
	{
		Signup signup = new Signup();
		HashMap hashmap = event.getBody();
		hashmap.remove("id");
		signup.setHashMap(hashmap);
		log.info(hashmap);
		
		
		return signup;
	}
}
