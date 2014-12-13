package com.zagerbone.dept;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import java.lang.*;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.dept:
//			DeptDAOFactory, DeptDAO

public class DeptQueryDetailAction extends BaseActionSupport
{

	public DeptQueryDetailAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		DeptDAO deptdao = DeptDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== DeptQueryDetailAction mode is "+ mode+" ===***");
		try
		{
			
			HashMap hashmap = event.getBody();
			String deptid = (String)hashmap.get("deptid");
			System.out.println("item wanna be showdetail 's deptid is : "+deptid);
			
			String a =deptdao.findDeptByThisId(deptid, request);
			eventresponse.setSuccessFlag(true);
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
		query.setCurrentPageNum(Integer.parseInt(s));
		query.setRowsPerPage(Integer.parseInt(s1));
		query.setTotalNum(Integer.parseInt(s2));
		query.setQueryString(s3);
		System.out.println("***=== the QueryString in DeptQueryDetailAction is "+s3+" ===***");
		query.info();
		query.setConditionBody(hashmap);
		return query;
	}
}
