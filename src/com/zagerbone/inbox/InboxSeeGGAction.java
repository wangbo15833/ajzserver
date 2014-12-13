package com.zagerbone.inbox;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.inbox:
//			Inbox, InboxDAOFactory, InboxDAO

public class InboxSeeGGAction extends BaseActionSupport
{

	public InboxSeeGGAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		InboxDAO inboxdao = InboxDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== InboxSeeGGAction mode is "+ mode+" ===***");
		try
		{
			String id = Tools.GBK(request.getParameter("id")); 
			System.out.println("item wanna be modified 's id is : "+id);
			inboxdao.readGginfo(id,request);
			eventresponse.setSuccessFlag(true);
		}
		catch (Exception exception)
		{
			
			exception.printStackTrace();
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode(event.getFunctionID());
			eventresponse.setErrorMessage(exception.getMessage().replace('\n', ' '));
			return eventresponse;
		}
		return eventresponse;
	}

	public Object processEvent(Event event)
		throws BussinessProcessException
	{
		Inbox inbox = new Inbox();
		return inbox;
	}
}
