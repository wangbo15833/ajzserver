package com.zagerbone.xmzg;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.xmzg:
//			Xmzg, XmzgDAOFactory, XmzgDAO

public class XmzgModifyAction extends BaseActionSupport
{

	public XmzgModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		XmzgDAO xmzgdao = XmzgDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== XmzgModifyAction mode is "+ mode+" ===***");
		try
		{
		
			if(mode.equals("before"))
			{
			Xmzg Xmzg = (Xmzg)processEvent(event);
			//System.out.println(mode.equals("before"));
			String id = Tools.GBK(request.getParameter("id")); 
			System.out.println("item wanna be modified 's id is : "+id);
			
			HashMap hashmap = event.getBody();
			Query query = new Query();
			String page = (String)hashmap.get("page");
			String rowsperpage = (String)hashmap.get("rowsperpage");
			String total = (String)hashmap.get("total");
			String queryString = (String)hashmap.get("queryString");
			queryString = (new StringBuilder()).append("fid=").append(event.getFunctionID()).append(queryString).toString();
			if (page == null || page.equals(""))
				page = "1";
			if (rowsperpage == null || rowsperpage.equals(""))
				rowsperpage = "15";
			if (total == null || total.equals(""))
				total = "0";
			rowsperpage = "10";
			query.setCurrentPageNum(Integer.parseInt(page));
			query.setRowsPerPage(Integer.parseInt(rowsperpage));
			query.setTotalNum(Integer.parseInt(total));
			query.setQueryString(queryString);
			System.out.println("***=== the QueryString in XmzgFindListAction is "+queryString+" ===***");
			query.info();
			
			hashmap.put("id",id);
			hashmap.remove("mode");
			query.setConditionBody(hashmap);
			Query query1 = xmzgdao.findXmzgList(query,request);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(query1);
			CachedRowSet crs=xmzgdao.getAllCol();
			request.setAttribute("crs_cols",crs);
			HashMap hp_pri = xmzgdao.getPri(event.getRequest());
			request.setAttribute("hp_pri",hp_pri);
			}else{
			Xmzg xmzg = (Xmzg)processEvent(event);
			String s = xmzgdao.modifyXmzg(xmzg,event.getRequest());
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
			System.out.println("*** === step 2 in XmzgModifyAction === ***");
			request.setAttribute("flag1","true");
			HashMap hp_pri = xmzgdao.getPri(event.getRequest());
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
		Xmzg xmzg = new Xmzg();
		HashMap hashmap = event.getBody();		
		xmzg.setid((String)hashmap.get("id"));
		hashmap.remove("id");
		xmzg.setHashMap(hashmap);
		return xmzg;
	}
}
