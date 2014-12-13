// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sys_displayFindListAction.java

package com.zagerbone.sys_display.servlet;

import com.zagerbone.event.EventResponse;
import com.zagerbone.sys_display.dao.Sys_displayDAOFactory;
import com.zagerbone.sys_display.dao.Sys_displayDAOImpl;
import com.zagerbone.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class Sys_displayFindListAction extends HttpServlet
{

	public Sys_displayFindListAction()
	{
	}

	public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		Logger logger = Logger.getLogger(SysTrace.DBLOGGER);
		EventResponse eventresponse = new EventResponse();
		eventresponse.setFunctionId("FIIP000.000.003.02");
		logger.info((new StringBuilder()).append("ActionName:").append(eventresponse.getFunctionId()).toString());
		String s = "";
		DBTrans dbtrans = new DBTrans(s);
		String s1 = Tools.nvl(httpservletrequest.getParameter("dir"));
		if (s1.equals(""))
			s1 = "sys_display";
		String s2 = Tools.nvl(httpservletrequest.getParameter("fileName"));
		if (s2.equals(""))
			s2 = "sys_display_list.jsp";
		try
		{
			dbtrans.beginTransaction();
			eventresponse.isSafe(httpservletrequest);
			String s3 = Tools.nvl(httpservletrequest.getParameter("tableName_dataArray"));
			GeneralDataArray generaldataarray = Sys_displayDAOFactory.getDAO(dbtrans).getDataArrayView(s3);
			ServletQueryCon servletquerycon = new ServletQueryCon(generaldataarray, httpservletrequest);
			com.zagerbone.util.Query query = servletquerycon.getQuery();
			servletquerycon.setLikeQuery("displayName");
			servletquerycon.setLikeQuery("displayDescription");
			sun.jdbc.rowset.CachedRowSet cachedrowset = Sys_displayDAOFactory.getDAO(dbtrans).findView(query);
			sun.jdbc.rowset.CachedRowSet cachedrowset1 = Sys_displayDAOFactory.getDAO(dbtrans).findView(query);
			dbtrans.commitTransaction();
			httpservletrequest.setAttribute("Sys_displayDATA", generaldataarray);
			httpservletrequest.setAttribute("crs", cachedrowset);
			httpservletrequest.setAttribute("crs_desc", cachedrowset1);
			httpservletrequest.setAttribute("queryCon", query);
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(ListFrameTools.listContentTable_ALL_onceforALL("listframe", "editframe", "../MainServlet/FIIP000.000.003.02", query, new SysCSS("272", "500%", "editSubmit_button();"), cachedrowset, cachedrowset1, generaldataarray.col_arrayHead, generaldataarray.col_array, generaldataarray.col_arrayDataType));
			httpservletrequest.setAttribute("listContent", stringbuffer.toString());
			eventresponse.setSuccessFlag(true);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorMessage(Tools.nvl(exception.getMessage()).replace('\n', ' '));
			dbtrans.rollbackTransaction();
		}
		httpservletrequest.setAttribute("result", eventresponse);
		if (eventresponse.isSuccessFlag())
			getServletContext().getRequestDispatcher(httpservletresponse.encodeURL((new StringBuilder()).append("/").append(s1).append("/").append(s2).toString())).forward(httpservletrequest, httpservletresponse);
		else
			getServletContext().getRequestDispatcher(httpservletresponse.encodeURL((new StringBuilder()).append("/").append(s1).append("/page_control.jsp").toString())).forward(httpservletrequest, httpservletresponse);
	}
}
