package com.zagerbone.txinfo;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.Tools;
// Referenced classes of package com.zagerbone.txinfo:
//			Txinfo, TxinfoDAOFactory, TxinfoDAO

public class TxinfoCreateAction extends BaseActionSupport
{

	public TxinfoCreateAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		TxinfoDAO txinfodao = TxinfoDAOFactory.getDAO();
		String s = "";
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== TxinfoCreateAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{				
				CachedRowSet crs=txinfodao.getAllCol();
				request.setAttribute("crs_cols",crs);
				eventresponse.setSuccessFlag(true);
				HashMap hp_pri = txinfodao.getPri(event.getRequest());
				request.setAttribute("hp_pri",hp_pri);
				return eventresponse;
			}else{
				log.info("***=== 新增单位类型 TxinfoCreateAction ===***");
				Txinfo txinfo = (Txinfo)processEvent(event);
				String s1 = txinfodao.createTxinfo(txinfo,event.getRequest());
				eventresponse.setSuccessFlag(true);
				eventresponse.setBody(s1);
				System.out.println("*** === step 2 in TxinfoModifyAction === ***");
				request.setAttribute("flag1","true");
				HashMap hp_pri = txinfodao.getPri(event.getRequest());
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
		Txinfo txinfo = new Txinfo();
		HashMap hashmap = event.getBody();
		txinfo.setHashMap(hashmap);
		log.info(hashmap);
		return txinfo;
	}
}
