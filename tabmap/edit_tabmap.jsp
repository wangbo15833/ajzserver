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
<title>修改角色信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>
<link href="../css/calendar-win2k-cold-1.css" rel=stylesheet type=text/css>
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<link rel="stylesheet"  href="css/001.css"  										type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 									type=text/css>
<link rel="stylesheet"  href="css/calendar-win2k-cold-1.css"  	type="text/css">
<script language="JavaScript" src="../share/js/utils.js"></script>
<script language="JScript" type="text/javascript"  src="../js/calendar.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-setup.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-zh.js"> </script>
</head>


<%
String titleName="字典数据修改";
String flag1 =(String)request.getAttribute("flag1"); 
try{
  if (flag1==null || flag1.equals(""))
  {  
	 flag1 ="false" ;
	  EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
	  Query queryCon = (Query)eventResponse.getBody(); 
	  CachedRowSet crs = null;
	  if(queryCon!=null)
	  {
		crs = (CachedRowSet)queryCon.getConditionBody();
		crs.next();
	  }
	CachedRowSet crs_cols = (CachedRowSet)request.getAttribute("crs_cols");
	ArrayList al_colname = new ArrayList();
	ArrayList al_colnamec = new ArrayList();
	while(crs_cols.next()){
		al_colname.add((String)crs_cols.getString("colname"));
		al_colnamec.add((String)crs_cols.getString("colnamec"));
	}
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="edit_zone" style="height:450;">
<form name="formHead" action="./MainServlet_sys" target="self" onKeyDown="if(event.keyCode==13)event.keyCode=9" style="padding-top:5px;" >
<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse;" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<input type="hidden" name="fid" id="fid" value="UII99.99.99.04">
	<input type="hidden" id="mode" name="mode" value="altercol">
	<%
	int ik = 0;
	while(ik<al_colname.size()){
			if(ik%2==0){
		%>
	<tr><%}%>
		<td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td>
		<td width="41%"><input type="text" name="<%=al_colname.get(ik).toString()%>" size="25"<%if (al_colname.get(ik).toString().equals("id")){%> readonly="readonly"  style="background-color:#CCC" <%}%>value="<%=Tools.nvl(crs.getString(al_colname.get(ik).toString()))%>"/>&nbsp;&nbsp;<font color="#FF0000">**</font></td> 
		<%
		ik++;
		if(ik%2==0){
		%>
	</tr><%}
		}
		if(ik%2==1){
		%><td height="20" width="9%" align="right" class="text2">&nbsp;&nbsp;</td>
		<td width="41%">&nbsp;&nbsp;</td>
	</tr>		
		<%
		}
		%>
  <tr>
  	<td align="center" colspan="4">
		<input type="button" name="button" value="修改列" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="增加列" style="width:80;height:23 " onClick="newcol();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="删除列" style="width:80;height:23 " onClick="delcol();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="新建表" style="width:80;height:23 " onClick="newtab();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="只增加列信息" style="width:80;height:55 " onClick="createinfo();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="只修改列信息" style="width:80;height:55 " onClick="modifyinfo();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="只删除列信息" style="width:80;height:55 " onClick="delinfo();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="删除表" style="width:80;height:23 " onClick="droptab();">&nbsp;&nbsp;&nbsp;&nbsp;
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
    if(!window.confirm("您确定要更修改此表，并修改相关信息？"))
  {return;}
	document.getElementById("mode").value="altercol";
    formHead.target = "submitframe";
	formHead.submit();
  }
  function newcol(){
  if(!window.confirm("您确定要增加新列，并添加相关信息？"))
  {return;}
	document.getElementById("mode").value="newcol";
    formHead.target = "submitframe";
	formHead.submit();  
  }
  function delcol(){
    if(!window.confirm("您确定要删除此列并，删除相关信息？"))
  {return;}
	document.getElementById("mode").value="delcol";
    formHead.target = "submitframe";
	formHead.submit();  
  }
  function newtab(){
    if(!window.confirm("您确定要新建一个表？"))
  {return;}
	document.getElementById("mode").value="newtab";
    formHead.target = "submitframe";
	formHead.submit();  
  }
  function delinfo(){
  if(!window.confirm("您确定要只删除列信息？"))
  {return;}
	document.getElementById("fid").value="UII99.99.99.04";
    formHead.target = "submitframe";
	formHead.submit();
  }
  function createinfo(){
  if(!window.confirm("您确定要只增加列信息？"))
  {return;}
	document.getElementById("fid").value="UII99.99.99.03";
    formHead.target = "submitframe";
	formHead.submit();
  }createinfo
  function modifyinfo(){
  if(!window.confirm("您确定要只修改列信息？"))
  {return;}
	document.getElementById("mode").value="modcol";
	document.getElementById("fid").value="UII99.99.99.04";
    formHead.target = "submitframe";
	formHead.submit();
  }
  function droptab(){
  if(!window.confirm("您确定要删除此表？"))
  {return;}
	document.getElementById("mode").value="droptab";
    formHead.target = "submitframe";
	formHead.submit();
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
  }
  function  checknow()
  {
	if(<%=flag1%>){
	 window.top.indexFrame.frames['manFrame'].location = '../MainServlet_sys?fid=UII99.99.99.01'
	}
  }
  checknow();
</script>
<%
crs.close();
crs_cols.close();
}else{
	%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("操作成功！");
	  //window.top.indexFrame.frames['manFrame'].location = './MainServlet_sys?fid=UII99.99.99.01'
	  window.top.indexFrame.frames['manFrame'].location.reload();
		}
	}
	checknow();
</script>
<%
}
}catch (Exception exception)
{
exception.printStackTrace();
}
%>