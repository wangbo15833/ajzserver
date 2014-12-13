package com.zagerbone.tabmap;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.tabmap:
//			Tabmap, TabmapDAOFactory, TabmapDAO

public class TabmapModifyAction extends BaseActionSupport
{

	public TabmapModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== TabmapModifyAction mode is "+ mode+" ===***");
		try
		{
		
			if(mode.equals("before"))
			{
			Tabmap Tabmap = (Tabmap)processEvent(event);
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
			System.out.println("***=== the QueryString in TabmapFindListAction is "+queryString+" ===***");
			query.info();
			
			hashmap.put("id",id);
			hashmap.remove("mode");
			query.setConditionBody(hashmap);
			Query query1 = tabmapdao.findTabmapList(query);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(query1);
			CachedRowSet crs=tabmapdao.getAllCol();
			request.setAttribute("crs_cols",crs);
			}else{
			Tabmap tabmap = (Tabmap)processEvent(event);
			String s = tabmapdao.modifyTabmap(tabmap);
			System.out.println("*** === step 2 in TabmapModifyAction === ***");
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
			request.setAttribute("flag1","true");
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
		Tabmap tabmap = new Tabmap();
		HashMap hashmap = event.getBody();		
		tabmap.setid((String)hashmap.get("id"));
		hashmap.remove("id");
		tabmap.setHashMap(hashmap);
		return tabmap;
	}
}
