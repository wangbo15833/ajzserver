package com.zagerbone.sysdataset;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.sysdataset:
//			Sysdataset, SysdatasetDAOFactory, SysdatasetDAO

public class SysdatasetModifyAction extends BaseActionSupport
{

	public SysdatasetModifyAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		SysdatasetDAO sysdatasetdao = SysdatasetDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== SysdatasetModifyAction mode is "+ mode+" ===***");
		try
		{
		
			if(mode.equals("before"))
			{
			Sysdataset Sysdataset = (Sysdataset)processEvent(event);
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
			System.out.println("***=== the QueryString in SysdatasetFindListAction is "+queryString+" ===***");
			query.info();
			
			hashmap.put("id",id);
			hashmap.remove("mode");
			query.setConditionBody(hashmap);
			Query query1 = sysdatasetdao.findSysdatasetList(query);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(query1);
			CachedRowSet crs=sysdatasetdao.getAllCol();
			request.setAttribute("crs_cols",crs);
			}else{
			Sysdataset sysdataset = (Sysdataset)processEvent(event);
			String s = sysdatasetdao.modifySysdataset(sysdataset);
			eventresponse.setSuccessFlag(true);
			eventresponse.setBody(s);
			System.out.println("*** === step 2 in SysdatasetModifyAction === ***");
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
		Sysdataset sysdataset = new Sysdataset();
		HashMap hashmap = event.getBody();		
		sysdataset.setid((String)hashmap.get("id"));
		hashmap.remove("id");
		sysdataset.setHashMap(hashmap);
		return sysdataset;
	}
}
