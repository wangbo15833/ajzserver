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
	System.out.println("ajax in === getsubpar===mode is :"+mode);
	
	if(mode.equals("getdept")){
		JSONObject jo_dept= new JSONObject();
		JSONArray ja_dept = new JSONArray();
			JSONObject dept_single= new JSONObject();
			crs = dbtrans.getResultBySelect("select * from dept where dept_dwlx='"+con+"' ");
			while(crs.next()){
				dept_single= new JSONObject();
				dept_single.put("dept_dwlx",crs.getString("dept_dwlx"));
				dept_single.put("dept_name",crs.getString("dept_name"));
				ja_dept.put(dept_single);
			}
		%><%=ja_dept.toString()%><%	
	}
	
	if(mode.equals("getemp")){
		JSONObject jo_dept= new JSONObject();
		JSONArray ja_dept = new JSONArray();
			JSONObject dept_single= new JSONObject();
			crs = dbtrans.getResultBySelect("select * from emp where emp_dept='"+con+"' ");
			while(crs.next()){
				dept_single= new JSONObject();
				dept_single.put("emp_dept",crs.getString("emp_dept"));
				dept_single.put("emp_lb",crs.getString("emp_lb"));
				dept_single.put("cell_pho",crs.getString("cell_pho"));
				dept_single.put("emp_name",crs.getString("emp_name"));
				dept_single.put("idcard",crs.getString("idcard"));
				dept_single.put("fzdw",crs.getString("fzdw"));
				dept_single.put("zsbh",crs.getString("zsbh"));
				ja_dept.put(dept_single);
			}
		%><%=ja_dept.toString()%><%	
	}
	dbtrans = null ;
	
}catch(Exception ex){ 
	ex.printStackTrace();
}
%>


