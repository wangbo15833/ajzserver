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
String titleName="角色权限修改";
String flag1 =(String)request.getAttribute("flag1"); 
try{
  if (flag1==null || flag1.isEmpty())
  {  
	 flag1 ="false" ;
	  //EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
	  //Query queryCon = (Query)eventResponse.getBody(); 
	CachedRowSet crs_pri = null;
	crs_pri = (CachedRowSet)request.getAttribute("crs_pri");
	System.out.println("the size of crs_pri is "+ crs_pri.size());
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="self" onKeyDown="if(event.keyCode==13)event.keyCode=9" style="padding-top:5px;" >
<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>

	<input type="hidden" name="fid" value="UII00.01.03.05"/>

	<%
	int ik = 0;
	while(crs_pri.next()) {
		if (ik==0){
	%>  
    	<input type="hidden" name="role_name" value="<%=crs_pri.getString("role_name")%>"/>   
    <tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%>:<%=crs_pri.getString("role_name")%></font></td>
	</tr>   
	<tr>
        <td style="width:120px;" class="text2">表中文名</td>
		<td style="width:90px;" class="text2">表名</td>
        <td width="41%"  class="text2">权限</td>
		<td width="41%" class="text2">过滤字符串</td>
    </tr>
    <%}%>
	<tr>
        <td style="width:120px;" class="text2"><%=crs_pri.getString("tabnamec")%></td>
        <td style="width:90px;" class="text2"><%=crs_pri.getString("tabname")%></td>
        <td width="41%" class="text2">
        <input type="hidden" name="<%="tabname_"+ik%>" value="<%=crs_pri.getString("tabname")%>"/>
        <label><input type="radio" name="<%="prilevel_"+ik%>" value="1" <%if (crs_pri.getString("prilevel").equals("1")){%>checked="checked"<%}%> />
    不能访问</label>&nbsp;&nbsp;<label><input type="radio" name="<%="prilevel_"+ik%>" value="2" <%if (crs_pri.getString("prilevel").equals("2")){%>checked="checked"<%}%> />
    查询</label>&nbsp;&nbsp;<label><input type="radio" name="<%="prilevel_"+ik%>" value="3"  <%if (crs_pri.getString("prilevel").equals("3")){%>checked="checked"<%}%>/>
    查询并修改</label>&nbsp;&nbsp;<label><input type="radio" name="<%="prilevel_"+ik%>" value="4"  <%if (crs_pri.getString("prilevel").equals("4")){%>checked="checked"<%}%>/>
    完全权限</label></td>
	<td width="41%" class="text2"><input type="text" name="<%="filter_str_"+ik%>" value="<%=crs_pri.getString("filter_str")%>"/></td>
    </tr>

        <%
		ik++;
	}
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
	 window.top.indexFrame.frames['manFrame'].location = '../MainServlet_sys?fid=UII00.00.00.01&listMode=list'
	}
  }
  checknow();
</script>
<%
crs_pri.close();
}else{
	%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("修改成功！");
	  window.top.indexFrame.frames['manFrame'].location = './MainServlet_sys?fid=UII00.00.00.01&listMode=list'
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