package com.zagerbone.role;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

// Referenced classes of package com.zagerbone.role:
//			Role, RoleDAOFactory, RoleDAO

public class RoleModifyAction extends BaseActionSupport
{

	public RoleModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		RoleDAO roledao = RoleDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== RoleModifyAction mode is "+ mode+" ===***");
		try
		{
		
			if(mode.equals("before"))
			{
			Role Role = (Role)processEvent(event);
			//System.out.println(mode.equals("before"));
			String id = Tools.GBK(request.getParameter("id")); 
			System.out.println("item wanna be modified 's id is : "+id);
			
			HashMap hashmap = event.getBody();
				Query query = new Query();
				String s = (String)hashmap.get("page");
				String s1 = (String)hashmap.get("rowsperpage");
				String s2 = (String)hashmap.get("total");
				String s3 = (String)hashmap.get("queryString");
				s3 = (new StringBuilder()).append("fid=").append(event.getFunctionID()).append(s3).toString();
				if (s == null || s.equals(""))
					s = "1";
				if (s1 == null || s1.equals(""))
					s1 = "15";
				if (s2 == null || s2.equals(""))
					s2 = "0";
				s1 = "10";
				query.setCurrentPageNum(Integer.parseInt(s));
				query.setRowsPerPage(Integer.parseInt(s1));
				query.setTotalNum(Integer.parseInt(s2));
				query.setQueryString(s3);
				System.out.println("***=== the QueryString in RoleFindListAction is "+s3+" ===***");
				query.info();
			
			hashmap.put("id",id);
			hashmap.remove("mode");
			query.setConditionBody(hashmap);
			Query query1 = roledao.findRoleList(query,event.getRequest());
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(query1);
			}else{
			Role role = (Role)processEvent(event);
			String s = roledao.modifyRole(role,event.getRequest());
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
			System.out.println("*** === step 2 in RoleModifyAction === ***");
			request.setAttribute("flag1","true");
			}
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
		//System.out.println("processEvent 1 ");
		Role role = new Role();
		HashMap hashmap = event.getBody();		
		//System.out.println("processEvent 2 ");
		role.setid((String)hashmap.get("id"));		
		//System.out.println("processEvent 3 ");
		role.setProp("role_name", (String)hashmap.get("role_name"));
		//System.out.println("processEvent 4 ");
		role.setProp("role_view_right", (String)hashmap.get("role_view_right"));
		role.setProp("role_all_right", (String)hashmap.get("role_all_right"));
		role.setProp("role_bz", (String)hashmap.get("role_bz"));
		return role;
	}
}
