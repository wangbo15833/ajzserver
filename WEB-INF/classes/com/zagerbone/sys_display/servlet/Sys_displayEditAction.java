// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sys_displayEditAction.java

package com.zagerbone.sys_display.servlet;

import com.zagerbone.event.EventResponse;
import com.zagerbone.sys_display.dao.Sys_displayDAOFactory;
import com.zagerbone.sys_display.dao.Sys_displayDAOImpl;
import com.zagerbone.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;

public class Sys_displayEditAction extends HttpServlet
{

	public Sys_displayEditAction()
	{
	}

	public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		Logger logger = Logger.getLogger(SysTrace.DBLOGGER);
		EventResponse eventresponse = new EventResponse();
		eventresponse.setFunctionId("FIIP000.000.003.03");
		logger.info((new StringBuilder()).append("ActionName:").append(eventresponse.getFunctionId()).toString());
		String s = Tools.delNull(httpservletrequest.getParameter("dir"), "sys_display");
		String s1 = Tools.delNull(httpservletrequest.getParameter("fileName"), "sys_display_edit.jsp");
		logger.info((new StringBuilder()).append(s).append("+").append(s1).toString());
		String s2 = "";
		DBTrans dbtrans = new DBTrans(s2);
		try
		{
			dbtrans.beginTransaction();
			eventresponse.isSafe(httpservletrequest);
			String s3 = Tools.nvl(httpservletrequest.getParameter("tableName_dataArray"));
			GeneralDataArray generaldataarray = Sys_displayDAOFactory.getDAO(dbtrans).getDataArrayView(s3);
			dbtrans.commitTransaction();
			httpservletrequest.setAttribute("Sys_displayDATA", generaldataarray);
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(PageTools.editContentTable_front("formHead"));
			stringbuffer.append(EditFrameTools.editContentTable(generaldataarray.col_arrayHead, generaldataarray.col_array, generaldataarray.col_arrayDisplayType, generaldataarray.col_arrayResponse, generaldataarray.col_arrayPop, "#e7f0f7", "font_px13", 3, "5"));
			stringbuffer.append(PageTools.editContentTable_div_origin("div_origin", "#e7f0f7", "30"));
			stringbuffer.append(PageTools.editContentTable_after());
			stringbuffer.append(PageTools.editSubmitTable("#e7f0f7", "font_px13", "5"));
			stringbuffer.append(FrameTools.arrayFill_originOrder("formHead", generaldataarray.col_array, generaldataarray.col_arrayDisplayType));
			stringbuffer.append(PageTools.editSubmitFunction("../MainServlet/FIIP000.000.003"));
			httpservletrequest.setAttribute("eidtContent", stringbuffer.toString());
			eventresponse.setSuccessFlag(true);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			eventresponse.setSuccessFlag(false);
			dbtrans.rollbackTransaction();
		}
		httpservletrequest.setAttribute("result", eventresponse);
		if (eventresponse.isSuccessFlag())
			getServletContext().getRequestDispatcher(httpservletresponse.encodeURL((new StringBuilder()).append("/").append(s).append("/").append(s1).toString())).forward(httpservletrequest, httpservletresponse);
		else
			getServletContext().getRequestDispatcher(httpservletresponse.encodeURL((new StringBuilder()).append("/").append(s).append("/page_control.jsp").toString())).forward(httpservletrequest, httpservletresponse);
	}
}
