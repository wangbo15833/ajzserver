package com.zagerbone.tongz;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.tongz:
//			Tongz, TongzDAOFactory, TongzDAO

public class TongzCreateAction extends BaseActionSupport
{

	public TongzCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		TongzDAO tongzdao = TongzDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== TongzCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{				
				CachedRowSet crs=tongzdao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				HashMap hp_pri = tongzdao.getPri(event.getRequest());
				request.setAttribute("hp_pri",hp_pri);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 TongzCreateAction ===***");
				Tongz tongz = (Tongz)processEvent(event);
				String s1 = tongzdao.createTongz(tongz,event.getRequest());
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in TongzModifyAction === ***");
				request.setAttribute("flag1","true");
				HashMap hp_pri = tongzdao.getPri(event.getRequest());
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
		Tongz tongz = new Tongz();
		HashMap hashmap = event.getBody();
		tongz.setHashMap(hashmap);
		log.info(hashmap);
		return tongz;
	}
}
