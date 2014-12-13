package com.zagerbone.jcjh;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.jcjh:
//			Jcjh, JcjhDAOFactory, JcjhDAO

public class JcjhCreateAction extends BaseActionSupport
{

	public JcjhCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		JcjhDAO jcjhdao = JcjhDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== JcjhCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{				
				CachedRowSet crs=jcjhdao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 JcjhCreateAction ===***");
				Jcjh jcjh = (Jcjh)processEvent(event);
				String s1 = jcjhdao.createJcjh(jcjh);
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in JcjhModifyAction === ***");
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
		Jcjh jcjh = new Jcjh();
		HashMap hashmap = event.getBody();
		jcjh.setHashMap(hashmap);
		log.info(hashmap);
		return jcjh;
	}
}
