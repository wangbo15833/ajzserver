// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyyqglFindListAction.java

package com.zagerbone.syyqgl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import com.zagerbone.util.Tools;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.syyqgl:
//			SyyqglDAOFactory, SyyqglDAO

public class SyyqglFindListAction extends BaseActionSupport
{

	public SyyqglFindListAction()
	{
	}

	public EventResponse perform(Event event)
	{
		SyyqglDAO syyqgldao = SyyqglDAOFactory.getDAO();
		EventResponse eventresponse = new EventResponse();
		try
		{
			Query query = (Query)processEvent(event);
			int i = query.getRowsPerPage();
			Query query1 = null;
			if (i == 0)
				query1 = syyqgldao.findSyyqglList_noPage(query);
			else
				query1 = syyqgldao.findSyyqglList(query);
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
		query.setTotalNum(Integer.parseInt(s2));
		query.setQueryString(s3);
		query.setConditionBody(hashmap);
		String s4 = Tools.nvl((String)hashmap.get("pageDisp"));
		if (s4.equals("0"))
			query.setRowsPerPage(Integer.parseInt(s4));
		return query;
	}
}
