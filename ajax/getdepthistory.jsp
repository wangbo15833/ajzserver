<%@ page contentType="text/html; charset=utf-8"
import="com.zagerbone.user.User"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="com.zagerbone.util.DBTrans"
import="com.zagerbone.util.Tools"
import="java.text.*"
import="org.json.*"%><%
try{
	String mode = Tools.GBK(request.getParameter("mode"));
	String con = Tools.GBK(request.getParameter("con")); 
	String dept = Tools.GBK(request.getParameter("dept")); 
	DBTrans dbtrans = new DBTrans(); 
	CachedRowSet crs = null;
	System.out.println("ajax in === getdepthistory in subinfo showdetail ===mode is :"+mode);
	
	if(mode.equals("getdepthistory")){
		JSONObject jo_dept= new JSONObject();
		JSONArray ja_dept = new JSONArray();
			JSONObject dept_single= new JSONObject();
			String sql= "select *,zg.id as zgid from subinfo as s left join subinfoasso as ao on  s.sub_code = ao.sub_code left join xmzg as zg on ao.sub_code = zg.xmcode  where ao.parname='"+con+"' and ao.parvalue='"+dept+"' order by s.sub_code";
			System.out.println(sql);
			crs = dbtrans.getResultBySelect(sql);
			while(crs.next()){
				dept_single= new JSONObject();
				dept_single.put("gcname",crs.getString("gcname"));
				dept_single.put("zglb",crs.getString("zglb"));
				dept_single.put("zgid",crs.getString("zgid"));
				dept_single.put("cont",crs.getString("cont"));
				dept_single.put("start_time",crs.getString("start_time"));
				dept_single.put("end_time",crs.getString("end_time"));
				ja_dept.put(dept_single);
			}
		%><%=ja_dept.toString()%><%	
	}

	dbtrans = null ;
	
}catch(Exception ex){ 
	ex.printStackTrace();
}
%>


