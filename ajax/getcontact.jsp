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
	String dept = new String(request.getParameter("dept").getBytes("ISO-8859-1"),"utf-8"); 
	String realname = new String(request.getParameter("realname").getBytes("ISO-8859-1"),"utf-8"); 
	DBTrans dbtrans = new DBTrans(); 
	CachedRowSet crs = null;
	System.out.println("ajax in === getcontact in ===mode is :"+mode);
	String contemp=null;
	String constr[] ;
	String sqlcon;
	sqlcon = "( work_unit like '%"+dept+"%' and realname like '%"+realname+"%' )";
	
		//JSONObject jo_dept= new JSONObject();
		JSONArray jsona = new JSONArray();
		JSONObject jsono= new JSONObject();
		String sql= "select username,realname,work_unit from userinfo where 1=1 and "+sqlcon+"  order by work_unit ";
		System.out.println("=======getcontact sql is ======="+sql);
		crs = dbtrans.getResultBySelect(sql);
		while(crs.next()){
			jsono= new JSONObject();
			jsono.put("account",crs.getString("username"));
			jsono.put("dept",crs.getString("work_unit"));
			jsono.put("realname",crs.getString("realname"));
			jsona.put(jsono);
		}
		System.out.println(jsona.toString());
		%><%=jsona.toString()%><%	

	dbtrans = null ;
	
}catch(Exception ex){ 
	ex.printStackTrace();
}
%>


