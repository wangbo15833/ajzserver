package com.zagerbone.tabmap;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.tabmap:
//			TabmapDAOFactory, TabmapDAO

public class FindTabmapListAction extends BaseActionSupport
{

	public FindTabmapListAction()
	{
	}

	public EventResponse perform(Event event)
	{
		TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
		EventResponse eventresponse = new EventResponse();
		try
		{
			Query query = (Query)processEvent(event);
			
		System.out.println("====================am i here: ");
			Query query1 = tabmapdao.findTabmapList(query);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(query1);
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
		System.out.println("====================RowsPerPage: "+s1);
		query.setTotalNum(Integer.parseInt(s2));
		query.setQueryString(s3);
		query.setConditionBody(hashmap);
		return query;
	}
}
