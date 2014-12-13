<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="com.zagerbone.util.Query"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>
<%@ page import ="java.util.Iterator"%>
<%@ page import ="java.util.Map.Entry"%>
<%@ page import ="java.util.Set"%>
<%@ include  file='../identification.jsp' %> 
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="./js/jquery.min.js"> </script>
<script language="JScript" type="text/javascript"  src="./js/check.js"> </script>
<style>
#confirmbutton{
width:145px;
height:42px;
line-height:42px;
padding-left:10px;
margin-right:10px;
display:inline-block;
cursor:pointer;
background:url(./image2/pic_button01.gif) no-repeat center;
background-attachment:scroll;
background-repeat:no-repeat;
background-position-x:left;
background-position-y:top;
background-size:auto;
background-origin:padding-box;
background-clip:border-box;
background-position-y:transparent;
}
#cnofirmfont{
font-family:黑体,Helvetica, Tahoma, Arial, sans-serif;
font-size:30px;
color:#fff;
}
</style>
<script>
var err0 ="没有输入密码；";
var err1 ="没有输入新密码；";
var err2 ="没有确认新密码；";
$(document).ready(function(){
$("#p0").blur(function(){
	if(isPasswd($("#p0").val())){
		$("#hit0").html("&nbsp;");
		err0="";
	}else{
		$("#hit0").html("<font color='red'>密码应为6-20个字母、数字</font>");
		err0="旧密码格式不正确；";
	}
});

$("#p1").blur(function(){
	if(isPasswd($("#p1").val())){
		$("#hit1").html("&nbsp;");
		err1="";
	}else{
		$("#hit1").html("<font color='red'>密码应为6-20个字母、数字</font>");
		err1="新密码格式不正确；";
	}
});

$("#p2").blur(function(){
	if($("#p2").val()==$("#p1").val()){
		$("#hit2").html("&nbsp;");
		err2="";
	}else{
		$("#hit2").html("<font color='red'>两次输入的密码不一致</font>");
		err2="两次输入的密码不一致；";
	}
});

$("#cnofirmfont").click(function(){
	if($("#p2").val()==$("#p1").val()){
		$("#hit2").html("&nbsp;");
		err2="";
	}else{
		$("#hit2").html("<font color='red'>两次输入的密码不一致</font>");
		err2="两次输入的密码不一致；";
	}
	err =err1+err2+err0;
	if(err==""){
	formHead.target = "submitframe";
	formHead.submit();
	}else{
		alert("错误："+err);
	}
});


});
</script>
</head>
<%
String titleName="用户登录密码修改";
String flag1 =(String)request.getAttribute("flag1"); 
try{


  if (flag1==null || flag1.equals(""))
  {  
	 flag1 ="false" ;
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="self"  method="post"  onKeyDown="if(event.keyCode==13)event.keyCode=9"  >
<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<input type="hidden" name="fid" id="fid" value="UII00.00.03.07"><input type="hidden" name="userid" id="userid" value="<%=hp_user.get("userid").toString()%>">
		
		
	<tr class="text2" style = "height:100%;">
		<td style = "height:100%;align:center;padding-top:100px;padding-bottom:100px;" >
				<div class="table" style="align:center;width:100%;height:100%;margin:0 auto;">
		<div style="">
		<table  style="width:600px;margin: 0 auto 0 auto;border:4px #dfd solid;padding: 0 auto;">
			<tr>
				<td style="text-align:right;width:200px;" valign="top">用户名：</td>
				<td><div><%=hp_user.get("username").toString()%></div>
				<div id="hit">&nbsp;</div>
				</td>
			</tr>
			<tr>
				<td style="text-align:right;" valign="top">旧密码：</td>
				<td><input id="p0" name="oldpsw" type="password"  maxlength="30" /></div>
				<div id="hit0">&nbsp;</div>
				</td>
			</tr>
			<tr>
				<td style="text-align:right" valign="top">新密码：</td>
				<td><input id="p1" name="newpsw1" type="password"  maxlength="30" /></div>
					<div id="hit1">&nbsp;</div>
				</td>
			</tr>
			<tr>
				<td  style="text-align:right" valign="top">再次输入新密码：</td>
				<td>
					<div class="oneline"><input id="p2"  name="newpsw2" type="password" maxlength="30" /></div>
					<div id="hit2">&nbsp;</div>
				</td>
			</tr>
			<tr>
				<td ></td>
				<td ><div  id="confirmbutton"><a id="cnofirmfont">确认修改</a></div></td>
			</tr>
		</table>
		</div>
    </div>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
</iframe> 

<%
}else{
	%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("登录修改成功，请使用新密码登录系统！");
		window.top.location ='./login/login_quit.jsp?sessionkill=1';
		}
	}
	checknow();
</script>
<%
}

}
catch (Exception exception)
{
exception.printStackTrace();
}
%>