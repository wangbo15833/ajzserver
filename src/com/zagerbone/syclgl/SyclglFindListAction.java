// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyclglFindListAction.java

package com.zagerbone.syclgl;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import com.zagerbone.util.Tools;
import java.util.HashMap;

public class SyclglFindListAction extends BaseActionSupport
{

	public SyclglFindListAction()
	{
	}

	public EventResponse perform(Event event)
	{
		SyclglDAO syclgldao = SyclglDAOFactory.getDAO();
		EventResponse eventresponse = new EventResponse();
		try
		{
			System.out.println("SyclglFindListAction is in the perform==============!!!!!!!!!!!!!!!!!!");
			Query query = (Query)processEvent(event);
			int i = query.getRowsPerPage();
			Query query1 = null;
			if (i == 0)
				query1 = syclgldao.findSyclglList_noPage(query);
			else
				query1 = syclgldao.findSyclglList(query);
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
		String page = (String)hashmap.get("page");
		String rowsperpage = (String)hashmap.get("rowsperpage");
		String total = (String)hashmap.get("total");
		String queryString = (String)hashmap.get("queryString");
		queryString = (new StringBuilder()).append("fid=").append(event.getFunctionID()).append(queryString).toString();
		if (page == null || page.equals(""))
			page = "1";
		if (rowsperpage == null || rowsperpage.equals(""))
			rowsperpage = "15";
		if (total == null || total.equals(""))
			total = "0";
		rowsperpage = "15";
		query.setCurrentPageNum(Integer.parseInt(page));
		query.setRowsPerPage(Integer.parseInt(rowsperpage));
		query.setTotalNum(Integer.parseInt(total));
		query.setQueryString(queryString);
		query.setConditionBody(hashmap);
		String s4 = Tools.nvl((String)hashmap.get("pageDisp"));
		if (s4.equals("0"))
			query.setRowsPerPage(Integer.parseInt(s4));
		return query;
	}
}
