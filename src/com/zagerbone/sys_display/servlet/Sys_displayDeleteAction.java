// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sys_displayDeleteAction.java

package com.zagerbone.sys_display.servlet;

import com.zagerbone.event.EventResponse;
import com.zagerbone.sys_display.dao.Sys_displayDAOFactory;
import com.zagerbone.sys_display.dao.Sys_displayDAOImpl;
import com.zagerbone.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class Sys_displayDeleteAction extends HttpServlet
{

	public Sys_displayDeleteAction()
	{
	}

	public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		Logger logger = Logger.getLogger(SysTrace.DBLOGGER);
		EventResponse eventresponse = new EventResponse();
		eventresponse.setFunctionId("FIIP000.000.003.05");
		logger.info((new StringBuilder()).append("ActionName:").append(eventresponse.getFunctionId()).toString());
		String s = Tools.nvl(httpservletrequest.getParameter("dir"));
		if (s.equals(""))
			s = "sys_display";
		String s1 = "";
		DBTrans dbtrans = new DBTrans(s1);
		try
		{
			dbtrans.beginTransaction();
			eventresponse.isSafe(httpservletrequest);
			String s2 = Tools.nvl(httpservletrequest.getParameter("tableName_dataArray"));
			com.zagerbone.util.GeneralDataArray generaldataarray = Sys_displayDAOFactory.getDAO(dbtrans).getDataArray(s2);
			String s3 = httpservletrequest.getParameter("id");
			s3 = Sys_displayDAOFactory.getDAO(dbtrans).delete(s3);
			dbtrans.commitTransaction();
			httpservletrequest.setAttribute("Sys_displayDATA", generaldataarray);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s3);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorMessage(Tools.nvl(exception.getMessage()).replace('\n', ' '));
			dbtrans.rollbackTransaction();
		}
		httpservletrequest.setAttribute("result", eventresponse);
		getServletContext().getRequestDispatcher(httpservletresponse.encodeURL((new StringBuilder()).append("/").append(s).append("/page_control.jsp").toString())).forward(httpservletrequest, httpservletresponse);
	}
}
