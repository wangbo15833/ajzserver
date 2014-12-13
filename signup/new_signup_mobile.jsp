<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.json.*"%>
<%
EventResponse 	eventResponse 	= (EventResponse)request.getAttribute("result");  
if(eventResponse.isSuccessFlag()){ 
	System.out.println("=== mobile sig  complish===");
	
	JSONObject sig= new JSONObject();
	sig.put("sta","ok");
	%><%=sig.toString()%><%
}
%>