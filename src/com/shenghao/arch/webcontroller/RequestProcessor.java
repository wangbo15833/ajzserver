// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RequestProcessor.java

package com.shenghao.arch.webcontroller;

import com.shenghao.arch.action.BaseAction;
import com.shenghao.arch.actionmapping.*;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.util.SysTrace;
import com.zagerbone.user.User;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.Tools;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// Referenced classes of package com.shenghao.arch.webcontroller:
//			RequestHead

public class RequestProcessor
{

	private ServletContext context;
	private HttpSession session;

	public void init(ServletContext servletcontext, HttpSession httpsession)
	{
		context = servletcontext;
		session = httpsession;
	}

	public RequestProcessor()
	{
	}

	public EventResponse processRequest(HttpServletRequest httpservletrequest)
		throws ServletException, UnsupportedEncodingException
	{
		Event event = null;
		EventResponse eventresponse = new EventResponse();
		User user = null;
		String s = null;
		String s1 = null;
		DBTrans dbtrans = new DBTrans("");
		RequestHead requesthead = null;
		String s2 = null;
		requesthead = (RequestHead)httpservletrequest.getAttribute("reqHead");
		s2 = requesthead.getFunctionID();
		SysTrace.info((new StringBuilder()).append("functionId = ").append(s2).toString());
		String s3 = (String)httpservletrequest.getAttribute("error");
		SysTrace.info((new StringBuilder()).append("login = ").append(s3).toString());
		if (s3 != null)
		{
			eventresponse.setErrorCode("431");
			eventresponse.setErrorMessage(s3);
			eventresponse.setFunctionID(requesthead.getFunctionID());
			return eventresponse;
		}
		if (s2 == null || s2.equals(""))
		{
			eventresponse.setErrorCode("432");
			eventresponse.setErrorMessage("FunctionID为空！");
			return eventresponse;
		}
		if (httpservletrequest.getSession(false) != null)
			user = (User)httpservletrequest.getSession(false).getAttribute(SysTrace.LOGIN_USER);
		SysTrace.info((new StringBuilder()).append("user = ").append(user).toString());
		event = doRequest(httpservletrequest);
		eventresponse.setFunctionID(event.getFunctionID());
		initActionMappings("");
		ActionMapping actionmapping = (ActionMapping)context.getAttribute(SysTrace.ACTION_MAPPINGS);
		s = actionmapping.getAction(s2);
		if (s == null)
		{
			SysTrace.info("actionName is null");
			eventresponse.setErrorCode("433");
			eventresponse.setErrorMessage("配置文件(mappings.xml)中没有此功能ID！");
			return eventresponse;
		}
		s1 = actionmapping.getTranssurpport(s2);
		String s4 = actionmapping.getLoglevel(s2);
		SysTrace.info((new StringBuilder()).append("actionName = ").append(s).toString());
		SysTrace.info((new StringBuilder()).append("transsurpport = ").append(s1).toString());
		SysTrace.info((new StringBuilder()).append("loglevel = ").append(s4).toString());
		BaseAction baseaction = null;
		HttpSession httpsession = httpservletrequest.getSession(false);
		try
		{
			baseaction = (BaseAction)Class.forName(s).newInstance();
			baseaction.init(context, httpsession);
		}
		catch (ClassNotFoundException classnotfoundexception)
		{
			classnotfoundexception.printStackTrace();
		}
		catch (InstantiationException instantiationexception)
		{
			instantiationexception.printStackTrace();
		}
		catch (IllegalAccessException illegalaccessexception)
		{
			illegalaccessexception.printStackTrace();
		}
		if (s1 == null || s1.equals("1"))
			try
			{
				eventresponse = baseaction.perform(event);
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
		if (eventresponse.getFunctionID() == null || eventresponse.getFunctionID().equals(""))
		{
			eventresponse.setFunctionID(event.getFunctionID());
		} else
		{
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode("434");
			eventresponse.setErrorMessage((new StringBuilder()).append("未找到").append(s).append("类，请检查ActionMapping！").toString());
		}
		if (event.getFunctionID().equals("F00.10.00.00"))
		{
			user = (User)eventresponse.getBody();
			httpservletrequest.getSession().setAttribute(SysTrace.LOGIN_USER, user);
		}
		if (event.getFunctionID().equals("F00.00.00.05"))
		{
			HashMap hashmap = null;
			hashmap = (HashMap)eventresponse.getGeneralBody();
			if (hashmap == null)
				return eventresponse;
			user = (User)eventresponse.getBody();
			httpservletrequest.getSession().setAttribute(SysTrace.LOGIN_USER, user);
			HashMap hashmap1 = (HashMap)hashmap.get("hp_user");
			HttpSession httpsession1 = httpservletrequest.getSession(true);
			httpsession1.setMaxInactiveInterval(7200);
			int i = httpsession1.getMaxInactiveInterval();
			SysTrace.info((new StringBuilder()).append("session=").append(httpsession1).toString());
			SysTrace.info((new StringBuilder()).append("maxInactiveInternal=").append(i).toString());
			httpsession1.setAttribute("hp_user", hashmap1);
			SysTrace.info((new StringBuilder()).append("1,").append(hashmap1).toString());
			httpsession1.getAttribute("hp_user");
			SysTrace.info((new StringBuilder()).append("2,").append(hashmap1).toString());
			ArrayList arraylist = (ArrayList)hashmap.get("al_fid");
			httpsession1.setAttribute("al_fid", arraylist);
			HashMap hashmap2 = (HashMap)hashmap.get("companyInfo");
			httpsession1.setAttribute("companyInfo", hashmap2);
			String s5 = (String)hashmap.get("menuScriptInfo");
			String s6 = (String)hashmap.get("str_0class");
			httpservletrequest.setAttribute("menuScriptInfo", s5);
			httpservletrequest.setAttribute("str_0class", s6);
		}
		try
		{
			if (user != null)
				eventresponse.setUserid(user.getProp("userid"));
		}
		catch (Exception exception1)
		{
			SysTrace.debug((new StringBuilder()).append("finally : ").append(exception1.getMessage()).toString());
		}
		System.out.println((new StringBuilder()).append("eventresponse.getFunctionID()=======before return====").append(eventresponse.getFunctionID()).toString());
		return eventresponse;
	}

	private Event doRequest(HttpServletRequest httpservletrequest)
		throws UnsupportedEncodingException
	{
		Event event = new Event();
		HashMap hashmap = new HashMap(1);
		StringBuffer stringbuffer = new StringBuffer("");
		RequestHead requesthead = (RequestHead)httpservletrequest.getAttribute("reqHead");
		User user = (User)httpservletrequest.getSession().getAttribute(SysTrace.LOGIN_USER);
		httpservletrequest.setAttribute("errorcode", "421");
		Enumeration enumeration = httpservletrequest.getParameterNames();
		do
		{
			if (!enumeration.hasMoreElements())
				break;
			String s = (String)enumeration.nextElement();
			String as[] = httpservletrequest.getParameterValues(s);
			if (as == null || as.length == 0)
				hashmap.put(s, "");
			else
			if (as.length == 1)
			{
				hashmap.put(s, new String(Tools.fixOra(as[0]).getBytes("ISO-8859-1")));
				System.out.println((new StringBuilder()).append("query string in requestprocessor while nofixOra getBytes8859-1 is:====").append(new String(as[0].getBytes("ISO-8859-1"))).toString());
			} else
			{
				for (int i = 0; i < as.length; i++)
					as[i] = new String(Tools.GBK(as[i]));

				hashmap.put(s, as);
			}
			if (!s.equals("page") && !s.equals("total") && !s.equals("fid") && !s.equals("selectrole"))
			{
				int j = 0;
				while (j < as.length) 
				{
					stringbuffer.append("&").append(s).append("=").append(as[j]);
					j++;
				}
			}
		} while (true);
		httpservletrequest.setAttribute("errorcode", "422");
		hashmap.put("queryString", new String(Tools.GBK(stringbuffer.toString())));
		System.out.println((new StringBuilder()).append("query string in requestprocessor is:====").append(Tools.GBK(stringbuffer.toString())).toString());
		event.setCurip(httpservletrequest.getRemoteAddr());
		if (user != null)
		{
			event.setUserid(user.getProp("userid"));
			event.setRealname(user.getProp("realname"));
		}
		hashmap.put(SysTrace.LOGIN_USER, user);
		event.setBody(hashmap);
		event.setFunctionID(requesthead.getFunctionID());
		event.setSystemid(SysTrace.SYSTEMID);
		event.setRequest(httpservletrequest);
		return event;
	}

	private void initActionMappings(String s)
	{
		ActionMappingDAO actionmappingdao = ActionMappingDAOFactory.getDAO();
		ActionMapping actionmapping = actionmappingdao.buildActionMapping(s);
		context.setAttribute(SysTrace.ACTION_MAPPINGS, actionmapping);
	}
}
