<%@ page contentType="text/html; charset=GBK"
import="com.zagerbone.user.User"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="com.zagerbone.util.DBTrans"
import="com.zagerbone.util.Tools"
import="java.text.*"%><%
try{
	String mode = Tools.GBK(request.getParameter("mode"));
	//String rec_name = Tools.GBK(java.net.URLDecoder.decode(request.getParameter("rec_name"))); 
	String rec_account = new String(request.getParameter("rec_account").getBytes("ISO-8859-1"),"utf-8"); 
	//System.out.println("ajax in === rec_name:"+(new String(request.getParameter("rec_name").getBytes("ISO-8859-1"),"utf-8")));
	DBTrans dbtrans = new DBTrans();
	CachedRowSet crs = null;
	System.out.println("ajax in === mode:"+mode);
	
	if (mode.equals("checkname")){
		String col = Tools.GBK(request.getParameter("col"));
		String val = Tools.GBK(request.getParameter("val"));
		if(col.equals("admin_name")){
	
			crs = dbtrans.getResultBySelect("select * from userinfo where realname='"+val+"' ");
			if (crs.size()==0){
				out.write("admin_name:0");
				}else{
				out.write("admin_name:1");
				}
		}
		if(col.equals("dept_name")){
	
			crs = dbtrans.getResultBySelect("select * from dept where dept_name='"+val+"' ");
			if (crs.size()==0){
				out.write("dept_name:0");
				}else{
				out.write("dept_name:1");
				}
		}
		if(col.equals("admin_account")){
	
			crs = dbtrans.getResultBySelect("select * from userinfo where username='"+val+"' ");
			if (crs.size()==0){
				out.write("admin_account:0");
				}else{
				out.write("admin_account:1");
				}
		}
	}

	if (mode.equals("newmsg")){
		crs = dbtrans.getResultBySelect("select count(*) as epr1 from inbox where rec_account='"+rec_account+"' and isread='0' and msgtype='Í¨Öª'");
		crs.next();
		String tz=crs.getString("epr1");
		
		crs = dbtrans.getResultBySelect("select count(*) as epr2 from inbox where rec_account='"+rec_account+"' and isread='0' and msgtype='ÌáÐÑ'");
		crs.next();
		String tx=crs.getString("epr2");
		
		
		System.out.println("-------------------------------"+tz+";"+tx);
		out.write(tz+";"+tx);
		/* if (crs.size()==0){
			out.write("no repeat");
			}else{
			out.write("repeat");
			} */
	}
	
	if(mode.equals("rolename")){
	
		String name = Tools.GBK(request.getParameter("name")); 
			crs = dbtrans.getResultBySelect("select * from role where role_name='"+name+"' ");
			if (crs.size()==0){
				out.write("ok");
				}else{
				out.write("repeat");
				}
	}
	
	dbtrans = null ;
}catch(Exception ex){ 
	ex.printStackTrace();
}
%>


