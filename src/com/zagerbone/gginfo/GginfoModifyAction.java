package com.zagerbone.gginfo;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.gginfo:
//			Gginfo, GginfoDAOFactory, GginfoDAO

public class GginfoModifyAction extends BaseActionSupport
{

	public GginfoModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		GginfoDAO gginfodao = GginfoDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== GginfoModifyAction mode is "+ mode+" ===***");
		try
		{
		
			if(mode.equals("before"))
			{
			Gginfo Gginfo = (Gginfo)processEvent(event);
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
			System.out.println("***=== the QueryString in GginfoFindListAction is "+queryString+" ===***");
			query.info();
			
			hashmap.put("id",id);
			hashmap.remove("mode");
			query.setConditionBody(hashmap);
			Query query1 = gginfodao.findGginfoList(query,request);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(query1);
			CachedRowSet crs=gginfodao.getAllCol();
			request.setAttribute("crs_cols",crs);
			HashMap hp_pri = gginfodao.getPri(event.getRequest());
			request.setAttribute("hp_pri",hp_pri);
			}else{
			Gginfo gginfo = (Gginfo)processEvent(event);
			String s = gginfodao.modifyGginfo(gginfo,event.getRequest());
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
			System.out.println("*** === step 2 in GginfoModifyAction === ***");
			request.setAttribute("flag1","true");
			HashMap hp_pri = gginfodao.getPri(event.getRequest());
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
		Gginfo gginfo = new Gginfo();
		HashMap hashmap = event.getBody();		
		gginfo.setid((String)hashmap.get("id"));
		hashmap.remove("id");
		gginfo.setHashMap(hashmap);
		return gginfo;
	}
}
