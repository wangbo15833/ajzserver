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

public class InboxModifyAction extends BaseActionSupport
{

	public InboxModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		InboxDAO inboxdao = InboxDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== InboxModifyAction mode is "+ mode+" ===***");
		try
		{
		
			if(mode.equals("before"))
			{
			Inbox Inbox = (Inbox)processEvent(event);
			//System.out.println(mode.equals("before"));
			String id = Tools.GBK(request.getParameter("id")); 
			System.out.println("item wanna be modified 's id is : "+id);
			
			CachedRowSet crs_byid = inboxdao.findInboxById(id,request);
			request.setAttribute("crs_byid",crs_byid);
			eventresponse.setSuccessFlag(true);
			CachedRowSet crs=inboxdao.getAllCol();
			request.setAttribute("crs_cols",crs);
			HashMap hp_pri = inboxdao.getPri(event.getRequest());
			request.setAttribute("hp_pri",hp_pri);
			}else{
			Inbox inbox = (Inbox)processEvent(event);
			String s = inboxdao.modifyInbox(inbox,event.getRequest());
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
			System.out.println("*** === step 2 in InboxModifyAction === ***");
			request.setAttribute("flag1","true");
			HashMap hp_pri = inboxdao.getPri(event.getRequest());
			request.setAttribute("hp_pri",hp_pri);
			
			////
			String msgtype = Tools.GBK(request.getParameter("msgtype"));
			request.setAttribute("msgtype",msgtype);
			}
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
		try{
		HashMap hashmap = event.getBody();
		inbox.setid((String)(hashmap.get("id")));
		hashmap.remove("id");
		inbox.setHashMap(hashmap);
		return inbox;
		}catch(Exception e){
			e.printStackTrace();
		}
		return inbox;
	}
}
