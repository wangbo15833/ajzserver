package com.zagerbone.tsyj;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.tsyj:
//			Tsyj, TsyjDAOFactory, TsyjDAO

public class TsyjCreateAction extends BaseActionSupport
{

	public TsyjCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		TsyjDAO tsyjdao = TsyjDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== TsyjCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{				
				CachedRowSet crs=tsyjdao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				HashMap hp_pri = tsyjdao.getPri(event.getRequest());
				request.setAttribute("hp_pri",hp_pri);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 TsyjCreateAction ===***");
				Tsyj tsyj = (Tsyj)processEvent(event);
				String s1 = tsyjdao.createTsyj(tsyj,event.getRequest());
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in TsyjModifyAction === ***");
				request.setAttribute("flag1","true");
				HashMap hp_pri = tsyjdao.getPri(event.getRequest());
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
		Tsyj tsyj = new Tsyj();
		HashMap hashmap = event.getBody();
		tsyj.setHashMap(hashmap);
		log.info(hashmap);
		return tsyj;
	}
}
