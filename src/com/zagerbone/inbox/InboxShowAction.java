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

public class InboxShowAction extends BaseActionSupport
{

	public InboxShowAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		InboxDAO inboxdao = InboxDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== InboxShowAction mode is "+ mode+" ===***");
		try
		{

			Inbox Inbox = (Inbox)processEvent(event);
			String id = Tools.GBK(request.getParameter("id")); 
			System.out.println("item wanna be checked's id is : "+id);
			
			CachedRowSet crs_byid = inboxdao.findInboxById(id,request);
			request.setAttribute("crs_byid",crs_byid);
			eventresponse.setSuccessFlag(true);
			CachedRowSet crs=inboxdao.getAllCol();
			request.setAttribute("crs_cols",crs);
			HashMap hp_pri = inboxdao.getPri(event.getRequest());
			request.setAttribute("hp_pri",hp_pri);

		}
		catch (Exception exception)
		{
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
		HashMap hashmap = event.getBody();		
		inbox.setid((String)hashmap.get("id"));
		hashmap.remove("id");
		inbox.setHashMap(hashmap);
		return inbox;
	}
}
