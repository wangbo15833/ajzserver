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
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>
<link href="../css/calendar-win2k-cold-1.css" rel=stylesheet type=text/css>
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<link rel="stylesheet"  href="css/001.css"  										type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 									type=text/css>
<script type="text/javascript" src="./js/map.js"></script>
<script language="JavaScript" src="../share/js/utils.js"></script>
<script language="JScript" type="text/javascript"  src="./js/calendar.js"> </script>
</head>


<%
String titleName="详细信息";
try{
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
	HashMap hp_pri = (HashMap)(request.getAttribute("hp_pri"));
	String filter_str = Tools.nvl((String)(hp_pri.get("filter_str")));
	while(crs_cols.next()){
		if(Tools.nvl(crs_cols.getString("deta_disp")).equals("1")){	
			al_colname.add((String)crs_cols.getString("colname"));
			al_colnamec.add((String)crs_cols.getString("colnamec"));
			al_datasrc.add((String)crs_cols.getString("datasrc"));
			al_datasrctype.add((String)crs_cols.getString("datasrctype"));
		}
	}
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="self" onKeyDown="if(event.keyCode==13)event.keyCode=9"  >
<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
		<%
	int ik = 0;
	while(ik<al_colname.size()){
			if(ik%2==0){
		%>
	<tr><%}%>
		<td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td>
		<td width="41%"><%=crs.getString(al_colname.get(ik).toString())%></td>

		<%/*if(((String)al_datasrctype.get(ik)).replaceAll(" ","").equals("") ||((String)al_datasrctype.get(ik)).equals("check")){
		%><td width="41%"><input type="text" name="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>"  size="25"<%if (al_colname.get(ik).toString().equals("id")){%> readonly="readonly"  style="background-color:#CCC" <%}%>value="<%=crs.getString(al_colname.get(ik).toString())%>"/>&nbsp;&nbsp;</td>
		<%}else if(((String)al_datasrctype.get(ik)).equals("popList")){
		%><td width="41%"><select name="<%=al_colname.get(ik).toString()%>" style="width:190px;">
			<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
			<%=com.zagerbone.util.Tools.popListWithSelect((String)al_datasrc.get(ik),crs.getString(al_colname.get(ik).toString()))%>
			</select></td>
		<%}else if(((String)al_datasrctype.get(ik)).equals("getmap")){
		%><td width="41%"><input type="text" name="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" ondblclick="showmap()" size="25" value="<%=crs.getString(al_colname.get(ik).toString())%>"/>双击 打开/隐藏 地图</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("calendar")){
		%><td width="41%"><input type="text" onfocus="calendar()" name="<%=al_colname.get(ik).toString()%>" size="25" value="<%=crs.getString(al_colname.get(ik).toString())%>"/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("textarea")){
		%><td width="41%"><textarea cols="50" rows="10" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25"><%=(crs.getString(al_colname.get(ik).toString())).replaceAll("%40","\r\n")%></textarea>&nbsp;&nbsp;</td> 
		<%}
		*/%><%
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
		<input type="button" name="button" value="返 回" style="width:80;height:23 " onClick="doReturn();">
	</td>
  </tr>
</table>
</form>
</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
  <!--百度地图容器-->
  
  <div id="baidumap"  style="border:10px solid #ccc ;position: absolute; z-index: -10; width: 350px; height: 350px;" ondblclick="hidemap()">
  </div>
</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
</iframe> 
<script language=javascript>
  function doReturn()
  {
	window.top.indexFrame.frames['manFrame'].location = './MainServlet_sys?fid=UII00.08.01.01<%=filter_str%>'
  }

</script>
<%
crs.close();
crs_cols.close();
}catch (Exception exception)
{
exception.printStackTrace();
}
%>