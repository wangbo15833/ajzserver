<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.zagerbone.user.User"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.*"%>
<%@ include  file='../identification.jsp' %> 

<head>
<title>新增用户信息</title>
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
		titleName = "新增用户";
	}
%>

<%
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
%>
<!--
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" bgcolor="#CCCCCC" class="body1">-->
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="../MainServlet_sys" target="sframe" onKeyDown="if(event.keyCode==13)event.keyCode=9" style="padding-top:30px;">
    <input type="hidden" name="fid" value="F00.00.00.07">
	<input type="hidden" name="userid" value="<%= user.getProp("userid")%>">
<!--
  <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	
   <tr>
		<td height="20" width="9%" align="right" class="text2">用户名：</td>
		<td width="41%">
		<input type="text" name="username" size="25" value="">&nbsp;&nbsp;<font color="#FF0000">**</font>
		</td> 
		<td height="20" width="15%" align="right" class="text2">姓 名：</td>
		<td width="35%"><input type="text" name="realname" value="" size="30">&nbsp;&nbsp;<font color="#FF0000">**</font></td>
	</tr>
  <tr> 
    <td height="20" align="right" class="text2">身份类别：</td>
	<td id="td_usertype">
	<select name="usertype" style="width:215px;">
		<option value="">-=请选择=-</option>
		<option value="车辆单位">车辆单位</option>
		<option value="检测站人员">检测站人员</option>
		<option value="系统管理员">系统管理员</option>
		<option value="其他人员">其他人员</option>
	</select>
	&nbsp;&nbsp;<font color="#FF0000">**</font></td>
	<td height="20" align="right" class="text2">密码：</td><td><input type="password" name="password" size="30" value=''>&nbsp;&nbsp;<font color="#FF0000">**</font></td>
  </tr>
  <tr> 
    <td height="20" align="right" class="text2">车辆单位：</td>
		<td id="td_work_unit">
		<select name="work_unit" style="width:215px;">
          <option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
          <%=com.zagerbone.util.Tools.popListWithSelect("select distinct work_unit,work_unit from TCcldw order by work_unit desc","")%>
        </select>
		
		<font color="#FF0000">**</font>
		
		</td>
	<td height="20" align="right" class="text2">单位地址：</td><td><input type="text" name="dwdh" size="30"></td>
  </tr>
  <tr> 
	  <td height="20" align="right" class="text2">电子信箱：</td>
	  <td><input type="text" name="email" size="30"></td>
	  <td height="20" align="right" class="text2" nowrap="nowrap">邮编：</td>
	  <td><input type="text" name="telephone" size="25"></td>
  </tr>
  <tr> 
	<td height="20" align="right" class="text2" nowrap="nowrap">联系电话：</td>
	<td><input type="text" name="fixphone" size="23">&nbsp;&nbsp;</td>
    <td height="20" align="right" class="text2">注册日期：</td>
    <td><input type="text" name="regdate" size="23" value="<%=df.format(new Date())%>" readonly=""></td>
  </tr>
  <tr> 
    <td height="20" align="right" class="text2" >备注：</td><td colspan="3"><textarea name="remark" cols="81" rows="8"></textarea>&nbsp;&nbsp;&nbsp;&nbsp;</td>
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
<%
if(usertype.equals("系统管理员")){//检测站人员也只能查看本单位的内容//||usertype.equals("检测站人员")
	;
}
else{
	%>
	<script>
	
	document.getElementById('td_work_unit').innerHTML='<input type="text" name="work_unit" id="work_unit"  readOnly />';
	document.getElementById('work_unit').value ='<%=work_unit%>'; 
	</script>	
	<%
}
%>


