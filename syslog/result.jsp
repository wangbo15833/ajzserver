<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="com.zagerbone.util.Query"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>
<%@ include  file='../identification.jsp' %> 
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
<% 
System.out.println("=== delete over , in result.jsp !");
  EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
	//if(eventResponse.getFunctionID().equals("UII00.10.01.02")){ 
      //String userid = (String)eventResponse.getBody();
      //out.println(SysTrace.showMsg("删除成功！"));
      %><script language=JavaScript>
	  alert('删除成功');
      parent.location.reload(); 
	  </script>
</body>
</html>