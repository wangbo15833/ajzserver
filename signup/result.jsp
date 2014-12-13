<%@ page contentType="text/html; charset=gb2312" %>

<%@ page import="java.util.*"%>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="com.shenghao.arch.manage.user.*"%>
<%@ page import="com.zagerbone.user.*"%>

<% 
  EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
	if(eventResponse.getFunctionID().equals("UII00.09.02.02")){ 
      String userid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("É¾³ý³É¹¦£¡"));
      out.println("<script language=JavaScript>");
      out.println("parent.location.reload(); ");
	  out.println("</script>");
      return;
    }
%>