<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.zagerbone.user.*"%>
<html>
<head>
<title>修改密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<script language="JavaScript" src="../share/js/utils.js"></script>
<STYLE type=text/css>
.td {
   height:20px;
}
</STYLE>
</head>
<%
	User user = (User)session.getAttribute("curUser");
%>
<!--<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" bgcolor="#CCCCCC" class="body1">-->
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<form name="formHead" action="../MainServlet_sys" target="sframe" onKeyDown="if(event.keyCode==13)event.keyCode=9" >
<input type="hidden" name="fid" value="F00.00.00.13">
<input type="hidden" name="userid" value="<%= user.getProp("userid") %>">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td height="30" colspan="2">&nbsp;</td>
	  </tr>
	  <tr>
		<td height="30" colspan="2">&nbsp;</td>
	  </tr>
	  <tr align="center"> 
		<td  width="30%" align="right" class="text">用户名：</td>
		<td  width="70%" align="left"><input type="text" name="username"  value="<%= user.getProp("username") %>"></td>
	  </tr>
	  <tr>
		<td height="3" colspan="2">&nbsp;</td>
	  </tr>
	  <tr>
		<td align="right" class="text">新密码：</td>
		<td><input type="password" name="password"> <font color="#FF0000">*</font></td>
	  </tr>
		<tr>
		<td height="5" colspan="2">&nbsp;</td>
	  </tr>
	  <tr>
		<td align="right" class="text">确认密码：</td>
		<td><input type="password" name="repassword"> <font color="#FF0000">*</font></td>
	  </tr>
	  <tr>
		<td class="td" colspan="2">&nbsp;</td>
	  </tr>
	  <tr>
		<td align="center" colspan="2" style="padding-right:300px;">
			<input type="button" name="button" value="确 定" style="width:50;height:23 " onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" name="button" value="取 消" style="width:50;height:23 " onClick="javascript:doClose();">
		</td>
	  </tr>
</table>
</form>
</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
</iframe> 
<script language=javascript>
  function doSave()
  {
    formHead.target = "submitframe";
	formHead.submit();
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
  }
</script>