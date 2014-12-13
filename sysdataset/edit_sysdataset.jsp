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
<script language="JScript" type="text/javascript"  src="./js/calendar.js"> </script>
</head>


<%
String titleName="数据维护修改";
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
	ArrayList al_datasrc = new ArrayList();
	ArrayList al_datasrctype = new ArrayList();
	while(crs_cols.next()){
		al_colname.add((String)crs_cols.getString("colname"));
		al_colnamec.add((String)crs_cols.getString("colnamec"));
		al_datasrc.add((String)crs_cols.getString("datasrc"));
		al_datasrctype.add((String)crs_cols.getString("datasrctype"));
	}
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="self" onKeyDown="if(event.keyCode==13)event.keyCode=9" style="padding-top:30px;" >
<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<input type="hidden" name="fid" value="UII00.10.02.04">
	<input type="hidden" name="id" value="1">
	<%
	int ik = 0;
	do {
	%>
	<tr><% if(ik<al_colname.size()){ %>
		<td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td>
		<%if(((String)al_datasrctype.get(ik)).equals("")){
		%><td width="41%"><input type="text" name="<%=al_colname.get(ik).toString()%>" size="25"<%if (al_colname.get(ik).toString().equals("id")){%> readonly="readonly"  style="background-color:#CCC" <%}%>value="<%=crs.getString(al_colname.get(ik).toString())%>"/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("popList")){
		%><td width="41%"><select name="<%=al_colname.get(ik).toString()%>" style="width:190px;">
			<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
			<%=com.zagerbone.util.Tools.popListWithSelect((String)al_datasrc.get(ik),crs.getString(al_colname.get(ik).toString()))%>
			</select></td>
		<%}else if(((String)al_datasrctype.get(ik)).equals("calendar")){
		%><td width="41%"><input type="text" onfocus="calendar()" name="<%=al_colname.get(ik).toString()%>" size="25"<%if (al_colname.get(ik).toString().equals("id")){%> readonly="readonly"  style="background-color:#CCC" <%}%>value="<%=crs.getString(al_colname.get(ik).toString())%>"/>&nbsp;&nbsp;</td> 
		<%}
		}else{%><td height="20" width="9%" align="right" class="text2">&nbsp;&nbsp;</td>
		<td width="41%">&nbsp;&nbsp;</td><%}
		ik++;
		if(ik<al_colname.size()){ %>
		<td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td>
		<%if(((String)al_datasrctype.get(ik)).equals("")){
		%><td width="41%"><input type="text" name="<%=al_colname.get(ik).toString()%>" size="25"<%if (al_colname.get(ik).toString().equals("id")){%> readonly="readonly"  style="background-color:#CCC" <%}%>value="<%=crs.getString(al_colname.get(ik).toString())%>"/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("popList")){
		%><td width="41%"><select name="<%=al_colname.get(ik).toString()%>" style="width:190px;">
			<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
			<%=com.zagerbone.util.Tools.popListWithSelect((String)al_datasrc.get(ik),crs.getString(al_colname.get(ik).toString()))%>
			</select></td>
		<%}else if(((String)al_datasrctype.get(ik)).equals("calendar")){
		%><td width="41%"><input type="text" onfocus="calendar()" name="<%=al_colname.get(ik).toString()%>" size="25"<%if (al_colname.get(ik).toString().equals("id")){%> readonly="readonly"  style="background-color:#CCC" <%}%>value="<%=crs.getString(al_colname.get(ik).toString())%>"/>&nbsp;&nbsp;</td> 
		<%}
		}else{
		%><td height="20" width="9%" align="right" class="text2">&nbsp;&nbsp;</td>
		<td width="41%">&nbsp;&nbsp;</td><%
		}
		ik++;
		if(ik>=al_colname.size()){
		break;
		}
		%>
	</tr>
	<%
	}while(true);
	%>
  <tr>
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
	 window.top.indexFrame.frames['manFrame'].location = '../MainServlet_sys?fid=UII00.10.02.01'
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
		alert("修改成功！");
	  window.top.indexFrame.frames['manFrame'].location = './MainServlet_sys?fid=UII00.10.02.01'
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