package com.zagerbone.subinfo;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.subinfo:
//			Subinfo, SubinfoDAOFactory, SubinfoDAO

public class SubinfoShowAction extends BaseActionSupport
{

	public SubinfoShowAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		SubinfoDAO subinfodao = SubinfoDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== SubinfoShowAction mode is "+ mode+" ===***");
		try
		{
			
			HashMap hashmap = event.getBody();
			String sub_code = (String)hashmap.get("sub_code");
			System.out.println("item wanna be modified 's sub_code is : "+sub_code);
			
			String a =subinfodao.findSubinfoById(sub_code, request);
			eventresponse.setSuccessFlag(true);
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

	public String processEvent(Event event)
		throws BussinessProcessException
	{
		HashMap hashmap = event.getBody();		
		return ((String)hashmap.get("sub_code"));		 
	}
}
