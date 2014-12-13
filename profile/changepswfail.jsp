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
<%@ page import="com.shenghao.arch.event.EventResponse"%>
<%

EventResponse eventResponse = (EventResponse)request.getAttribute("result");

System.out.println(eventResponse.getErrorMessage());
%>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script language="JScript" type="text/javascript"  src="js/jq1-4-1.js"> </script>
</head>
<body>
<div id="man_zone">
<div style='width:90%;height:90%;text-align:center;'>
<div style="padding-top:120px;"><img src="image2/gif/gif_46_016.gif"/></div><div><h3>修改过程出现错误，请检查您输入的密码！</h3>
</div>
</div>
</div>
</body>
<script>
	$(document).ready(function(){
	alert('修改失败：<%=eventResponse.getErrorMessage()%>');
	history.go(-1);
	});
</script>
</html>