// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sys_displayQueryAction.java

package com.zagerbone.sys_display.servlet;

import com.zagerbone.event.EventResponse;
import com.zagerbone.sys_display.dao.Sys_displayDAOFactory;
import com.zagerbone.sys_display.dao.Sys_displayDAOImpl;
import com.zagerbone.util.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

public class Sys_displayQueryAction extends HttpServlet
{

	public Sys_displayQueryAction()
	{
	}

	public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		Logger logger = Logger.getLogger(SysTrace.DBLOGGER);
		EventResponse eventresponse = new EventResponse();
		eventresponse.setFunctionId("FIIP000.000.003.00");
		logger.info((new StringBuilder()).append("ActionName:").append(eventresponse.getFunctionId()).toString());
		String s = Tools.delNull(httpservletrequest.getParameter("dir"), "sys_display");
		String s1 = Tools.delNull(httpservletrequest.getParameter("fileName"), "sys_display_query.jsp");
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
			CachedRowSet cachedrowset = Sys_displayDAOFactory.getDAO(dbtrans).getDisplayDataByPK("displayName", "sys_display");
			cachedrowset.next();
			String s4;
			s4 = s4 = "314";
			int i = Integer.parseInt(s4) - 26 - 2;
			httpservletrequest.setAttribute("Sys_displayDATA", generaldataarray);
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(FrameEditTitleTools.titleHeadTable_forQuery("", "", ""));
			stringbuffer.append("<table width='100%' border='0' align='center' cellpadding='1' cellspacing='1' bordercolor='#317992' bgcolor='#bdd7d6' class='1pxborder_topVoid' rules='none' >");
			stringbuffer.append("<tr>");
			stringbuffer.append("\t<td scope='col' align='left' id='sub_title'>");
			stringbuffer.append((new StringBuilder()).append("\t<div style='overflow:auto; height=").append(i).append("'>").toString());
			stringbuffer.append(QueryFrameTools.queryContentTable_front("FormHead", "../MainServlet/FIIP000.000.003.02", "listframe"));
			stringbuffer.append(QueryFrameTools.queryContentTable(generaldataarray.col_queryArrayHead, generaldataarray.col_queryArray, generaldataarray.col_queryArrayDisplayType, generaldataarray.col_queryArrayResponse, generaldataarray.col_queryArrayPop, "#e7f0f7", "font_px13", 2, "0", "", "25%", "25%", "FormHead", null, 23, 8));
			stringbuffer.append(QueryFrameTools.queryContentTable_after());
			stringbuffer.append(FrameTools.arrayFill_originOrder("FormHead", generaldataarray.col_queryArray, generaldataarray.col_queryArrayDisplayType));
			stringbuffer.append("</div>");
			stringbuffer.append("<table width='100%' height='40px' border='0' cellpadding='0' cellspacing='0' bordercolor='#FFFFFF' bgcolor='#3399FF'>");
			stringbuffer.append("<tr bgcolor='#e7f0f7' class='font_px13'>");
			stringbuffer.append("<td align='center'>");
			stringbuffer.append("\t<input type='submit' \tname='editSubmit' \tvalue='²éÑ¯'  onClick='closeQueryframe(); FormHead.submit(); '>&nbsp; &nbsp; &nbsp; &nbsp;");
			stringbuffer.append("\t<input type='reset' \tname='resetButton' \tvalue='ÖØÌî' \tonClick='FormHead.reset();'> ");
			stringbuffer.append("</td>");
			stringbuffer.append("</tr>");
			stringbuffer.append("</table>");
			stringbuffer.append("<script>");
			stringbuffer.append("function closeQueryframe(){");
			stringbuffer.append("\tparent.document.getElementById('queryframe').style.display = 'none';");
			stringbuffer.append("\tparent.document.getElementById('div_queryframe').style.display = 'none';");
			stringbuffer.append("\tparent.parent.titleFrame.menuframe.closeQueryImg();");
			stringbuffer.append("}");
			stringbuffer.append("</script>");
			stringbuffer.append("</td></tr></table>");
			httpservletrequest.setAttribute("queryContent", stringbuffer.toString());
			if (s1.equals("sys_display_report.jsp"))
			{
				StringBuffer stringbuffer1 = new StringBuffer();
				stringbuffer1.append(FrameEditTitleTools.titleHeadTable_forQuery("", "", ""));
				httpservletrequest.setAttribute("queryContent", stringbuffer1.toString());
			}
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
