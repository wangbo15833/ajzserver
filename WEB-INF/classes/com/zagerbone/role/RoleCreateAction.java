package com.zagerbone.role;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;

// Referenced classes of package com.zagerbone.role:
//			Role, RoleDAOFactory, RoleDAO

public class RoleCreateAction extends BaseActionSupport
{

	public RoleCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		RoleDAO roledao = RoleDAOFactory.getDAO();
		String s = "";
		try
		{
			log.info("***=== ÐÂÔö½ÇÉ« RoleCreateAction ===***");
			Role role = (Role)processEvent(event);
			String s1 = roledao.createRole(role,event.getRequest());
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s1);
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
		Role role = new Role();
		HashMap hashmap = event.getBody();
		role.setProp("role_name", (String)hashmap.get("role_name"));
		role.setProp("role_view_right", (String)hashmap.get("role_view_right"));
		role.setProp("role_all_right", (String)hashmap.get("role_all_right"));
		role.setProp("role_bz", (String)hashmap.get("role_bz"));
		log.info(hashmap);
		return role;
	}
}
