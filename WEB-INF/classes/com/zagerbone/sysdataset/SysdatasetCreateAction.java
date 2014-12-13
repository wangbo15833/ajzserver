package com.zagerbone.sysdataset;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.sysdataset:
//			Sysdataset, SysdatasetDAOFactory, SysdatasetDAO

public class SysdatasetCreateAction extends BaseActionSupport
{

	public SysdatasetCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		SysdatasetDAO sysdatasetdao = SysdatasetDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== SysdatasetCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{				
				CachedRowSet crs=sysdatasetdao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 SysdatasetCreateAction ===***");
				Sysdataset sysdataset = (Sysdataset)processEvent(event);
				String s1 = sysdatasetdao.createSysdataset(sysdataset);
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in SysdatasetModifyAction === ***");
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
		Sysdataset sysdataset = new Sysdataset();
		HashMap hashmap = event.getBody();
		sysdataset.setHashMap(hashmap);
		log.info(hashmap);
		return sysdataset;
	}
}
