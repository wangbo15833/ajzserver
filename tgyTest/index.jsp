<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="java.util.HashMap"%>
<%
String usertype = null;
EventResponse 	eventResponse 	= (EventResponse)request.getAttribute("result");  
if(eventResponse.isSuccessFlag()){ 
	
	HashMap hp_general = (HashMap)eventResponse.getGeneralBody();
	HashMap hp_user = null;
	hp_user = (HashMap)hp_general.get("hp_user");
	System.out.println("hp_user=== step in the MIS ==="+hp_user);
	usertype = (String)hp_user.get("usertype");
	
	//request.getSession();
	session.setAttribute("Myusertype", usertype);
	session.setAttribute("hp_user", hp_user);
}
	
	
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link rel="shortcut icon" href="favicon.ico"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<title>建设工程安全管理服务系统-秦皇岛微讯信息技术有限公司</title>


</head>
<body>
<%
{
%>
<iframe id='indexFrame' name='indexFrame' src='tgyTest/index_frame.jsp' style='width:100%; height:680px; border:none; background:#fff;'></iframe>
<%
}

if(usertype.equals("")){
%>
<iframe id='indexFrame' name='indexFrame' src='tgyTest/index_frame.jsp' style='width:100%; height:680px; border:none; background:#fff;'></iframe>
<%
}
%>
</body>
</html>
