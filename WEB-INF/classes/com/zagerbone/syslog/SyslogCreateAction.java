package com.zagerbone.syslog;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.syslog:
//			Syslog, SyslogDAOFactory, SyslogDAO

public class SyslogCreateAction extends BaseActionSupport
{

	public SyslogCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== SyslogCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{				
				CachedRowSet crs=syslogdao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 SyslogCreateAction ===***");
				Syslog syslog = (Syslog)processEvent(event);
				String s1 = syslogdao.createSyslog(syslog);
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in SyslogModifyAction === ***");
				request.setAttribute("flag1","true");
			}
		}
		catch (Exception exception)
		{
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode("601");
			eventresponse.setErrorMessage(exception.getMessage().replace('\n', ' '));
			return eventresponse;
		}
		return eventresponse;
	}

	public Object processEvent(Event event)
		throws BussinessProcessException
	{
		Syslog syslog = new Syslog();
		HashMap hashmap = event.getBody();
		syslog.setHashMap(hashmap);
		log.info(hashmap);
		return syslog;
	}
}
