<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.json.*"%>
<%
	JSONObject userinfo= new JSONObject();
	userinfo.put("work_unit",hp_user.get("work_unit"));
	userinfo.put("userid",hp_user.get("userid"));
	userinfo.put("account",hp_user.get("username"));
	userinfo.put("realname",hp_user.get("realname"));
	userinfo.put("usertype",hp_user.get("usertype"));
	%><%=userinfo.toString()%><%

%>