<%@ page contentType="text/html; charset=gb2312" %>

<%@ page import="java.util.*"%>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="com.shenghao.arch.manage.user.*"%>
<%@ page import="com.zagerbone.user.*"%>

<% 
  EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
	if(eventResponse.getFunctionID().equals("UII99.99.99.02")){ 
      String userid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("É¾³ý³É¹¦£¡"));
      out.println("<script language=JavaScript>");
      out.println("parent.location.reload(); ");
	  out.println("</script>");
      return;
    }
%>