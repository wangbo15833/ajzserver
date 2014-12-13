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

public class SubinfoModifyAction extends BaseActionSupport
{

	public SubinfoModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		SubinfoDAO subinfodao = SubinfoDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== SubinfoModifyAction mode is "+ mode+" ===***");
		try
		{
		
			if(mode.equals("before"))
			{
						HashMap hashmap = event.getBody();
			String sub_code = (String)hashmap.get("sub_code");
			System.out.println("item wanna be modified 's sub_code is : "+sub_code);
			
			String a =subinfodao.findSubinfoById(sub_code, request);
			eventresponse.setSuccessFlag(true);
			
			hashmap.remove("mode");
			eventresponse.setSuccessFlag(true);
			HashMap hp_pri = subinfodao.getPri(event.getRequest());
			request.setAttribute("hp_pri",hp_pri);
			}else{
			Subinfo subinfo = (Subinfo)processEvent(event);
			String s = subinfodao.modifySubinfo(subinfo,event.getRequest());
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
			System.out.println("*** === step 2 in SubinfoModifyAction === ***");
			request.setAttribute("flag1","true");
			HashMap hp_pri = subinfodao.getPri(event.getRequest());
			request.setAttribute("hp_pri",hp_pri);
			}
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
		Subinfo subinfo = new Subinfo();
		HashMap hashmap = event.getBody();		
		subinfo.setid((String)hashmap.get("id"));
		hashmap.remove("id");
		subinfo.setHashMap(hashmap);
		return subinfo;
	}
}
