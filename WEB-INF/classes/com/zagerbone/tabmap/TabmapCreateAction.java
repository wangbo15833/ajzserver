package com.zagerbone.tabmap;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.tabmap:
//			Tabmap, TabmapDAOFactory, TabmapDAO

public class TabmapCreateAction extends BaseActionSupport
{

	public TabmapCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== TabmapCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{				
				CachedRowSet crs=tabmapdao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 TabmapCreateAction ===***");
				Tabmap tabmap = (Tabmap)processEvent(event);
				String s1 = tabmapdao.createTabmap(tabmap);
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in TabmapModifyAction === ***");
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
		Tabmap tabmap = new Tabmap();
		HashMap hashmap = event.getBody();
		tabmap.setHashMap(hashmap);
		log.info(hashmap);
		return tabmap;
	}
}
