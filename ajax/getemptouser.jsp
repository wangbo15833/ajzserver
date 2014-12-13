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
	String pri = Tools.GBK(request.getParameter("pri"));
	String deptid = Tools.GBK(request.getParameter("deptid")); 
	DBTrans dbtrans = new DBTrans();
	CachedRowSet crs = null;
	CachedRowSet crs_dept = null;
	System.out.println("ajax in === getemptouser:"+deptid);
	JSONObject emp= new JSONObject();
	JSONArray ja_emp = new JSONArray();
	if (Integer.parseInt(pri)<4){
			System.out.println("the sql is ========"+"select * from dept where deptid='"+deptid+"' ");
			crs_dept = dbtrans.getResultBySelect("select * from dept where deptid='"+deptid+"' ");
			crs_dept.next();
			String eff_time = crs_dept.getString("eff_time");
			JSONObject emp_single= new JSONObject();
			crs = dbtrans.getResultBySelect("select * from emp where deptid='"+deptid+"' and isblind='0' order by deptid ");
			while(crs.next()){
				emp_single= new JSONObject();
				emp_single.put("emp_name",crs.getString("emp_name"));
				emp_single.put("empid",crs.getString("empid"));
				emp_single.put("emp_role","免费用户");
				emp_single.put("emp_dept",crs.getString("emp_dept"));
				emp_single.put("deptid",crs.getString("deptid"));
				emp_single.put("eff_time",eff_time);
				ja_emp.put(emp_single);
			}
	}else{
		JSONObject emp_single= new JSONObject();
		crs = dbtrans.getResultBySelect("select * from emp where 1=1 and isblind='0' order by deptid");
		while(crs.next()){
			emp_single= new JSONObject();
			emp_single.put("emp_name",crs.getString("emp_name"));
			emp_single.put("empid",crs.getString("empid"));
			emp_single.put("emp_role",crs.getString("emp_role"));
			emp_single.put("emp_dept",crs.getString("emp_dept"));
			emp_single.put("deptid",crs.getString("deptid"));
			emp_single.put("eff_time","2013-07-01");
			ja_emp.put(emp_single);
		}
	}
	dbtrans = null ;
	crs.close();
	System.out.println("-------------*************** ja_emp is :"+ ja_emp.toString());
	%><%=ja_emp.toString()%><%
}catch(Exception ex){ 
	ex.printStackTrace();
}
%>


