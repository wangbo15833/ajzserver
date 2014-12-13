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
<title>日志过滤条件</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>
<link href="../css/calendar-win2k-cold-1.css" rel=stylesheet type=text/css>
<link rel="stylesheet"  href="../css/common.css" type="text/css" />
<link rel="stylesheet"  href="css/001.css"  										type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 									type=text/css>
<script language="JavaScript" src="../share/js/utils.js"></script>
<script language="JScript" type="text/javascript"  src="../js/calendar.js"> </script>
</head>


<%
String titleName="日志过滤条件";
try{

 %>
<!--<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" bgcolor="#CCCCCC" class="body1">-->
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="../MainServlet_sys" target="_self" onKeyDown="if(event.keyCode==13)event.keyCode=9" style="padding-top:30px;" >
<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<input type="hidden" name="fid" value="UII00.10.01.01">
  <tr> 
    <td height="20" align="right" class="text2">事件名称：</td>
	<td width="41%"><select name="event" style="width:190px;">
			<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
			<%=com.zagerbone.util.Tools.popListWithSelect("select e,e from (select distinct event as e from syslog) as bb","")%>
			</select>
	</td>
	<td height="20" align="right" class="text2">发生时间：</td><td><input type="text" name="e_datetime" size="30" value=""></td>
  </tr>
  <tr> 
    <td height="20" align="right" class="text2">操作者：</td><td><input type="text" name="e_operator" size="30" value=""></td>
    <td height="20" align="right" class="text2">操作账号：</td><td><input type="text" name="e_account" size="30" value=""></td>
  </tr>
    <tr> 
	</td>
    <td height="20" align="right" class="text2">操作表名：</td><td><input type="text" name="tabname" size="30" value=""></td>
	<!--td height="20" align="right" class="text2">语句：</td><td><input type="text" name="cent" size="30" value=""></td-->
	<td height="20" align="right" class="text2"></td><td></td>
  </tr>

  	<td align="center" colspan="4">
		<input type="button" name="button" value="确 定" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="取消" style="width:80;height:23 " onClick="javascript:history.go(-1);">
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
    //formHead.target = "man_frame";
	formHead.submit();
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
  }
  </script>
<%
}
catch (Exception exception4)
{
exception4.printStackTrace();
}
%>