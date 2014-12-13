package com.zagerbone.subinfo;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import java.util.HashMap;
import java.lang.*;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.subinfo:
//			SubinfoDAOFactory, SubinfoDAO

public class SubinfoQueryListAction extends BaseActionSupport
{

	public SubinfoQueryListAction()
	{
	}

	public EventResponse perform(Event event)
	{
		SubinfoDAO subinfodao = SubinfoDAOFactory.getDAO();
		EventResponse eventresponse = new EventResponse();		
		HttpServletRequest request = event.getRequest();
		try
		{
			Query query = (Query)processEvent(event);
			Query query1 = subinfodao.findSubinfoList(query,event.getRequest());
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(query1);
			CachedRowSet crs=subinfodao.getAllCol();
			request.setAttribute("crs_cols",crs);
			HashMap hp_pri = subinfodao.getPri(event.getRequest());
			request.setAttribute("hp_pri",hp_pri);
		}
		catch (Exception exception)
		{
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode("601");
			eventresponse.setErrorMessage(exception.getMessage());
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
		System.out.println("***=== the QueryString in SubinfoQueryListAction is "+s3+" ===***");
		query.info();
		query.setConditionBody(hashmap);
		return query;
	}
}
