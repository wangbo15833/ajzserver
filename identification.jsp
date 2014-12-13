<%@ page import="sun.jdbc.rowset.CachedRowSet"  %>
<%@ page import="java.util.*" %>

<%
HashMap hp_user = null;
String 	username 	= null;
String 	work_unit = null;
String 	usertype 	= null;
try{
	hp_user = (HashMap)session.getAttribute("hp_user");
	//System.out.println(hp_user.toString());
	if(hp_user != null ){
	
		username 	= (String)hp_user.get("realname");
		work_unit = (String)hp_user.get("work_unit");
		usertype 	= (String)hp_user.get("usertype");
		usertype 	= (String)hp_user.get("usertype");
	}
	
	if(hp_user == null ){
	out.println("<script>alert('"+client.core.util.Tools.GBK("必须先登录!")+"');\n//this.window.close();\n window.location='./login/login_quit.jsp?sessionkill=1'</script>");return;
	}

}catch(Exception ex){
	ex.printStackTrace();
	%>
	<script>
	alert("权限非法！");
	window.close();
	</script>
	<%
}
%>