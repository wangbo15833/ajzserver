package com.shenghao.arch.webcontroller;

import com.shenghao.arch.actionmapping.ActionMappingDAO;
import com.shenghao.arch.actionmapping.ActionMappingDAOFactory;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.util.SysTrace;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.*;
import javax.servlet.http.*;

public class MainServlet_sys extends HttpServlet
{

	private RequestProcessor processor;

	public MainServlet_sys()
	{
		processor = null;
	}

	public void init()
	{
		String s = getInitParameter("actionmappingsrc");
		String s1 = getInitParameter("SYSTEMNAME");
		SysTrace.info((new StringBuilder()).append("System ").append(s1).append(" init begin...").toString());
		SysTrace.JNDI = getInitParameter("JNDI");
		initActionMappings(s);
		SysTrace.info("System init end....");
	}

	private void initActionMappings(String s)
	{
		com.shenghao.arch.actionmapping.ActionMapping actionmapping = null;
		ActionMappingDAO actionmappingdao = null;
		actionmappingdao = ActionMappingDAOFactory.getDAO();
		actionmapping = actionmappingdao.buildActionMapping(s);
		getServletContext().setAttribute(SysTrace.ACTION_MAPPINGS, actionmapping);
	}

	private void doProcess(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		SysTrace.info("------------------------------------------------------------------");
		long l = 0L;
		if (SysTrace.PERFORMANCE_FLAG)
		{
			l = System.currentTimeMillis();
			SysTrace.info((new StringBuilder()).append("******** System response time Start = ").append(new Date(l)).toString());
		}
		SysTrace.info("***=== MainServlet_sys 's doProcess is started! ===***");
		EventResponse eventresponse = new EventResponse();
		httpservletrequest.setAttribute("errorcode", "321");
		initRequestProcessor(httpservletrequest.getSession(false));
		httpservletrequest.setAttribute("errorcode", "322");
		SysTrace.info("***=== MainServlet_sys 's processResponse is started! ===***");
		eventresponse = processor.processRequest(httpservletrequest);
		processResponse(httpservletrequest, httpservletresponse, eventresponse);
		if (SysTrace.PERFORMANCE_FLAG)
		{
			long l1 = System.currentTimeMillis();
			SysTrace.info((new StringBuilder()).append("******* EndTime = ").append(new Date(l1)).append(" Interval = ").append(l1 - l).toString());
		}
		SysTrace.info("----------Bussiness Process end------------");
	}

	private void initRequestProcessor(HttpSession httpsession)
	{
		processor = new RequestProcessor();
		processor.init(getServletContext(), httpsession);
	}

	private void processResponse(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, EventResponse eventresponse)
		throws IOException, ServletException
	{
		if (eventresponse.getErrorCode().equals(""))
			eventresponse.setErrorCode((String)httpservletrequest.getAttribute("errorcode"));
		httpservletrequest.setAttribute("result", eventresponse);
		getServletContext().getRequestDispatcher("/ViewDispatchServlet").forward(httpservletrequest, httpservletresponse);
	}

	public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		doProcess(httpservletrequest, httpservletresponse);
	}

	public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		doProcess(httpservletrequest, httpservletresponse);
	}

	public void service(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws IOException, ServletException
	{
		doProcess(httpservletrequest, httpservletresponse);
	}
}
