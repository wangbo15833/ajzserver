<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.zagerbone.user.User"%>
<%@ page import="com.shenghao.arch.util.*"%>
<html>
<head>
<title>修改个人信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>
<link href="../css/calendar-win2k-cold-1.css" rel=stylesheet type=text/css>
<link rel="stylesheet"  href="../css/common.css" type="text/css" />
<link rel="stylesheet"  href="css/001.css"  										type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 									type=text/css>
<link rel="stylesheet"  href="css/calendar-win2k-cold-1.css"  	type="text/css">
<script language="JavaScript" src="../share/js/utils.js"></script>
<script language="JScript" type="text/javascript"  src="../js/calendar.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-setup.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-zh.js"> </script>
</head>
<%
	User user = new User();
	user = (User)session.getAttribute("curUser");
	String titleName = com.zagerbone.util.Tools.GBK(request.getParameter("titleName"));
	if(titleName.equals("")){
		titleName = "修改用户个人信息";
	}
%>
<!--<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" bgcolor="#CCCCCC" class="body1">-->
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="../MainServlet_sys" target="sframe" onKeyDown="if(event.keyCode==13)event.keyCode=9" style="padding-top:30px;" >
<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<input type="hidden" name="fid" value="F00.00.00.10">
	<input type="hidden" name="userid" value="<%= user.getProp("userid")%>">
   <tr>
		<td height="20" width="9%" align="right" class="text2">用户名：</td>
		<td width="41%">
		<input type="text" name="username" size="25" readonly=""  value="<%=user.getProp("username")%>">&nbsp;&nbsp;<font color="#FF0000">**</font>
		</td> 
		<td height="20" width="15%" align="right" class="text2">姓 名：</td>
		<td width="35%"><input type="text" name="realname" value="<%= user.getProp("realname")%>" size="30"></td>
	</tr>
  <!--<tr> <object width="32" height="32">
</object>

	<td height="20" align="right" class="text2">原始密码：</td><td><input type="password" name="password" size="30" value=''>&nbsp;&nbsp;<font color="#FF0000">**</font></td>
    <td height="20" align="right" class="text2">新密码：</td>
	<td><input type="password" name="new_password" size="30"   value=''></td>
	</tr>
	<%= user.getProp("new_password")%><%= user.getProp("new_password_again")%>
	<td height="20" align="right" class="text2">再次新密码：</td>
	<td><input type="password" name="new_password_again" size="30" value=''></td>
	<td height="20" align="right" class="text2"><!--身份类别：</td>
	<td><input type="text" name="usertype" readonly="true" value="<%= user.getProp("usertype")%>" size="30"></td>
  </tr>-->
  <tr> 
    <td height="20" align="right" class="text2">车辆单位：</td><td><input type="text" name="work_unit" size="30" readonly="readonly" value="<%= user.getProp("work_unit")%>">&nbsp;&nbsp;<font color="#FF0000">**</font></td>
	<td height="20" align="right" class="text2">单位地址：</td><td><input type="text" name="address" size="30" value="<%= user.getProp("address")%>"></td>
  </tr>
  <tr> 
  <td height="20" align="right" class="text2">电子信箱：</td><td><input type="text" name="email" size="30" value="<%= user.getProp("email")%>"></td>
	<td height="20" align="right" class="text2" nowrap="nowrap">联系电话：</td>
	<td><input type="text" name="telephone" size="25" value="<%= user.getProp("telephone")%>"></td>
  </tr>
  <tr> 
    <td height="20" align="right" class="text2" >备注：</td><td colspan="3"><textarea name="addition" cols="81" rows="8"><%=user.getProp("addition")%></textarea>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
  <tr>
  	<td align="right" colspan="4">
		<input type="button" name="button" value="确 定" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" name="button" value="重 填" style="width:80;height:23 ">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="返 回" style="width:80;height:23 " onClick="javascript:history.go(-1);">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</td>
  </tr>
</table>
</form>
</div>
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