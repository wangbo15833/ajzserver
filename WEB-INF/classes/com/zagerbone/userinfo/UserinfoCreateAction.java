package com.zagerbone.userinfo;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.userinfo:
//			Userinfo, UserinfoDAOFactory, UserinfoDAO

public class UserinfoCreateAction extends BaseActionSupport
{

	public UserinfoCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		UserinfoDAO userinfodao = UserinfoDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== UserinfoCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{	
				userinfodao.checkUserNumber(request);
				CachedRowSet crs=userinfodao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				HashMap hp_pri = userinfodao.getPri(event.getRequest());
				request.setAttribute("hp_pri",hp_pri);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 UserinfoCreateAction ===***");
				Userinfo userinfo = (Userinfo)processEvent(event);
				String s1 = userinfodao.createUserinfo(userinfo,event.getRequest());
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in UserinfoModifyAction === ***");
				request.setAttribute("flag1","true");
				HashMap hp_pri = userinfodao.getPri(event.getRequest());
				request.setAttribute("hp_pri",hp_pri);
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
		Userinfo userinfo = new Userinfo();
		HashMap hashmap = event.getBody();
		userinfo.setHashMap(hashmap);
		log.info(hashmap);
		return userinfo;
	}
}
