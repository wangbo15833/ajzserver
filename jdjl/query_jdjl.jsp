<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><%@ 
page contentType="text/html; charset=gb2312"
import="com.zagerbone.user.User"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="java.text.*"%><%@ include  file='../identification.jsp'  
%><head>
<title>查询</title>
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
try{
	String flag1 =(String)request.getAttribute("flag1"); 
	if (flag1==null || flag1.equals(""))
	 {  
		flag1 ="false" ;
		User user = new User();
		user = (User)session.getAttribute("curUser");
		String titleName = "监督记录查询";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CachedRowSet crs_cols = (CachedRowSet)request.getAttribute("crs_cols");
		ArrayList al_colname = new ArrayList();
		ArrayList al_colnamec = new ArrayList();
		while(crs_cols.next()){
			al_colname.add((String)crs_cols.getString("colname"));
			al_colnamec.add((String)crs_cols.getString("colnamec"));
		}
%>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone" >
<form name="formHead" action="./MainServlet_sys"  onKeyDown="if(event.keyCode==13)event.keyCode=9" style="padding-top:5px;">
    <input type="hidden" name="fid" value="UII00.04.04.01">
	<TABLE class=table style="BORDER-COLLAPSE: collapse;" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
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
		<td width="41%"><input type="text" name="<%=al_colname.get(ik).toString()%>" size="25" value=""/>&nbsp;&nbsp;</td> 
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
  <tr>
	  <td height="20" width="9%" align="right" class="text2">查询方式：</td>
		<td>
			<label><input type="radio" name="isblur" value="1" checked="checked"/>模糊</label>
			<label><input type="radio" name="isblur" value="0" />精确</label>
		</td>
		<td>
		</td>
		<td>
		</td>
  </tr>
  	<td align="center" colspan="4">
		<input type="button" name="button" value="确 定" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" name="button" value="重 填" style="width:80;height:23 ">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="返 回" style="width:80;height:23 " onClick="javascript:history.go(-1);">
	</td>
  </tr>
</table>
</form>

</div>
</body>
</html>
</iframe> 
<script language=javascript>
  function doSave()
  {
    formHead.target = "_self";
	formHead.submit();
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
  }
</script>
<%
crs_cols.close();
}
else{
%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("增加成功！");
	  window.top.indexFrame.frames['manFrame'].location = './MainServlet_sys?fid=UII00.04.04.01'
		}
	}
	checknow();
</script>
<%
}
}catch(Exception ex){
	ex.printStackTrace();
}
%>
