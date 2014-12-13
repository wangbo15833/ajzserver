<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.json.*"%>
<%
String usertype = null;
EventResponse 	eventResponse 	= (EventResponse)request.getAttribute("result");  
if(eventResponse.isSuccessFlag()){ 
	
	HashMap hp_general = (HashMap)eventResponse.getGeneralBody();
	HashMap hp_user = null;
	hp_user = (HashMap)hp_general.get("hp_user");
	System.out.println("=== mobile login hp_user: ==="+hp_user);
	usertype = (String)hp_user.get("usertype");

	
	JSONObject userinfo= new JSONObject();
	userinfo.put("work_unit",hp_user.get("work_unit"));
	userinfo.put("userid",hp_user.get("userid"));
	userinfo.put("account",hp_user.get("username"));
	userinfo.put("realname",hp_user.get("realname"));
	userinfo.put("usertype",hp_user.get("usertype"));
	%><%=userinfo.toString()%><%
}
%>