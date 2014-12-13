<%@ page import="sun.jdbc.rowset.CachedRowSet"  %>
<%@ page import="java.util.*" %>
<%
HashMap hp_user = null;
String 	username 	= null;
String 	work_unit = null;
String 	usertype 	= null;
try{
	hp_user = (HashMap)session.getAttribute("hp_user");
	if(hp_user != null ){
	
		username 	= (String)hp_user.get("realname");
		work_unit = (String)hp_user.get("work_unit");
		usertype 	= (String)hp_user.get("usertype");
	}
	
	if(hp_user == null ){
	out.println("<script>alert('"+client.core.util.Tools.GBK("必须先登录")+"');this.window.close();</script>");return;
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