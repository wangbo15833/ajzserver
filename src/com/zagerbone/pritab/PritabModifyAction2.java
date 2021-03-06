package com.zagerbone.pritab;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.pritab:
//			Pritab, PritabDAOFactory, PritabDAO

public class PritabModifyAction2 extends BaseActionSupport
{

	public PritabModifyAction2()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		PritabDAO pritabdao = PritabDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== PritabModifyAction2 mode is "+ mode+" ===***");
		try
		{
		
			if(mode.equals("before"))
			{
			Pritab pritab = (Pritab)processEvent(event);
			//System.out.println(mode.equals("before"));
			String id = Tools.GBK(request.getParameter("id")); 
			System.out.println("item wanna be modified 's id is : "+id);
			/*
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
			System.out.println("***=== the QueryString in PritabFindListAction is "+queryString+" ===***");
			query.info();
			
			hashmap.put("id",id);
			hashmap.remove("mode");
			query.setConditionBody(hashmap);
			Query query1 = pritabdao.findPritabList(query);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(query1);
			CachedRowSet crs=pritabdao.getAllCol();
			request.setAttribute("crs_cols",crs);
			*/
			CachedRowSet crs=pritabdao.getAllPri(id);
			request.setAttribute("crs_pri",crs);
			eventresponse.setSuccessFlag(true);
			
			}else{
			System.out.println("Step 2 is start!!!!!!!!!!!!!");
			Pritab pritab = (Pritab)processEvent(event);
			String s = pritabdao.modifyPritab2(pritab);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
			System.out.println("*** === step 2 in PritabModifyAction === ***");
			request.setAttribute("flag1","true");
			}
		}
		catch (Exception exception)
		{
		exception.printStackTrace();
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode(event.getFunctionID());
			//eventresponse.setErrorMessage(exception.getMessage().replace('\n', ' '));
			return eventresponse;
		}
		return eventresponse;
	}

	public Object processEvent(Event event)
		throws BussinessProcessException
	{
		Pritab pritab = new Pritab();
		HashMap hashmap = event.getBody();		
		pritab.setid((String)hashmap.get("id"));
		hashmap.remove("id");
		pritab.setHashMap(hashmap);
		return pritab;
	}
}
