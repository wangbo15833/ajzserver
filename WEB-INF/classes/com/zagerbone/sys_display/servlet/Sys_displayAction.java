// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sys_displayAction.java

package com.zagerbone.sys_display.servlet;

import com.zagerbone.event.EventResponse;
import com.zagerbone.exception.BussinessProcessException;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class Sys_displayAction extends HttpServlet
{

	public Sys_displayAction()
	{
	}

	public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		EventResponse eventresponse = new EventResponse();
		String s = "sys_display";
		try
		{
			String s1 = (String)httpservletrequest.getAttribute("safetyError");
			if (s1.equals("yes"))
				throw new BussinessProcessException("请先登陆后再操作！");
			eventresponse.setFunctionId("FIIP000.000.003.00");
			eventresponse.setSuccessFlag(true);
		}
		catch (Exception exception)
		{
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorMessage(exception.getMessage().replace('\n', ' '));
		}
		httpservletrequest.setAttribute("result", eventresponse);
		getServletContext().getRequestDispatcher(httpservletresponse.encodeURL((new StringBuilder()).append("/").append(s).append("/page_control.jsp").toString())).forward(httpservletrequest, httpservletresponse);
	}
}
