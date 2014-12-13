// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sys_displayCreate_8Action.java

package com.zagerbone.sys_display.servlet;

import com.zagerbone.event.EventResponse;
import com.zagerbone.exception.BussinessProcessException;
import com.zagerbone.sys_display.dao.Sys_displayDAOFactory;
import com.zagerbone.sys_display.dao.Sys_displayDAOImpl;
import com.zagerbone.util.*;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSetMetaData;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

public class Sys_displayCreate_8Action extends HttpServlet
{

	public Sys_displayCreate_8Action()
	{
	}

	public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		Logger logger = Logger.getLogger(SysTrace.DBLOGGER);
		EventResponse eventresponse = new EventResponse();
		eventresponse.setFunctionId("FIIP000.000.003.81");
		logger.info((new StringBuilder()).append("ActionName:").append(eventresponse.getFunctionId()).toString());
		String s = Tools.nvl(httpservletrequest.getParameter("dir"));
		if (s.equals(""))
			s = "sys_display";
		String s1 = "";
		DBTrans dbtrans = new DBTrans(s1);
		try
		{
			dbtrans.beginTransaction();
			String s2 = null;
			eventresponse.isSafe(httpservletrequest);
			String s3 = Tools.nvl(httpservletrequest.getParameter("tablename_sour"));
			if (s3.equals(""))
				throw new BussinessProcessException("no_displayNameRef");
			String s4 = Tools.nvl(httpservletrequest.getParameter("tablename"));
			if (s4.equals(""))
				throw new BussinessProcessException("no_tablename");
			String s5 = Tools.nvl(httpservletrequest.getParameter("edit_actionNameHead"));
			if (s5.equals(""))
				throw new BussinessProcessException("no_edit_actionNameHead");
			String s6 = (new StringBuilder()).append(s5).append("02").toString();
			String s7 = (new StringBuilder()).append(s5).append("02").toString();
			String s8 = Tools.nvl(httpservletrequest.getParameter("tableName_dataArray"));
			GeneralDataArray generaldataarray = Sys_displayDAOFactory.getDAO(dbtrans).getDataArray(s8);
			GeneralObject generalobject = new GeneralObject();
			String s9 = (new StringBuilder()).append("select * from sys_display where displayName = '").append(s3).append("'").toString();
			Sys_displayDAOFactory.getDAO(dbtrans).getDataBySQL(s9);
			System.out.println((new StringBuilder()).append("sqlStr==================================").append(s9).toString());
			CachedRowSet cachedrowset = Sys_displayDAOFactory.getDAO(dbtrans).getDataBySQL(s9);
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				for (int j = 1; j <= i; j++)
					if (!resultsetmetadata.getColumnName(j).equals("id"))
						generalobject.setProp(resultsetmetadata.getColumnName(j), Tools.nvl(cachedrowset.getString(j)));

			}
			generalobject.setProp("tablename", s4);
			generalobject.setProp("displayName", (new StringBuilder()).append(s4).append("_display").toString());
			generalobject.setProp("edit_actionNameHead", s5);
			generalobject.setProp("query_actionName", s6);
			generalobject.setProp("listactionName", s7);
			new MyServlet_errorInfo(generaldataarray.col_arrayNovoid, generaldataarray.col_arrayHeadNovoid, generalobject);
			s2 = Sys_displayDAOFactory.getDAO(dbtrans).create(generalobject);
			dbtrans.commitTransaction();
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s2);
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
