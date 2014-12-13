package com.zagerbone.jdjh;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.jdjh:
//			Jdjh, JdjhDAOFactory, JdjhDAO

public class JdjhCreateAction extends BaseActionSupport
{

	public JdjhCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		JdjhDAO jdjhdao = JdjhDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== JdjhCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{				
				CachedRowSet crs=jdjhdao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				HashMap hp_pri = jdjhdao.getPri(event.getRequest());
				request.setAttribute("hp_pri",hp_pri);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 JdjhCreateAction ===***");
				Jdjh jdjh = (Jdjh)processEvent(event);
				String s1 = jdjhdao.createJdjh(jdjh,event.getRequest());
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in JdjhModifyAction === ***");
				request.setAttribute("flag1","true");
				HashMap hp_pri = jdjhdao.getPri(event.getRequest());
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
		Jdjh jdjh = new Jdjh();
		HashMap hashmap = event.getBody();
		jdjh.setHashMap(hashmap);
		log.info(hashmap);
		return jdjh;
	}
}
