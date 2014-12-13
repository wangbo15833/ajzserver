package com.zagerbone.dwlx;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.dwlx:
//			Dwlx, DwlxDAOFactory, DwlxDAO

public class DwlxCreateAction extends BaseActionSupport
{

	public DwlxCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		DwlxDAO dwlxdao = DwlxDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== DwlxCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{				
				CachedRowSet crs=dwlxdao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 DwlxCreateAction ===***");
				Dwlx dwlx = (Dwlx)processEvent(event);
				String s1 = dwlxdao.createDwlx(dwlx);
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in DwlxModifyAction === ***");
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
		Dwlx dwlx = new Dwlx();
		HashMap hashmap = event.getBody();
		dwlx.setHashMap(hashmap);
		log.info(hashmap);
		return dwlx;
	}
}
