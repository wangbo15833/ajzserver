<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>

<%
  EventResponse eventResponse = (EventResponse)request.getAttribute("result"); 
	Query queryCon = null;
	CachedRowSet crs = null;
	if(eventResponse != null){
  queryCon = (Query)eventResponse.getBody();  
  crs = (CachedRowSet)queryCon.getConditionBody();
	}
%>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="../css/001.css"  							type="text/css" >
<link rel="stylesheet"  href="../css/style1.css" type=text/css>
<link rel="stylesheet"  href="../css/calendar-win2k-cold-1.css"  	type="text/css">
<link rel="stylesheet"  href="css/common.css" type="text/css" />
<script>
function editSubmit_button(){//设置确定按钮的操作容许
	if(parent.activeIframe == 'listframe'){
		parent.openIframe("listframe");
		parent.closeIframe("editframe");
	}
}
</script>  
</head>
<body leftmargin="0" topmargin="0" rightmargin="0">
<div id="man_zone">
<%
String [] col_name 			= {"id","parameteName","parameteChName","configYPerValue","configYMinValue","configYValueCount"};
String [] col_headname 	= {"id","参数名称","参数中文名称","Y轴单位间隔","Y轴最小单位","Y轴长度"};
%>
<%
	crs.next();
%>
<form  name="formHead"  action="MainServlet_sys" onsubmit="return testColValue();" method="post" target="submitframe">
	<input type="hidden" name="fid" value="FCL00.00.07.04">
  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
  <th colspan="4"><font size="+1">曲线参数修改</font></th>
  <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
  <tr style="display:none">
      <td width="33%" class="left_title_1"><span class="left-title">id</span></td>
      <td width="15%"><label for="Cname"></label><input type="text" name="id" id="id"  value="<%= Tools.nvl(crs.getString("id"))%>"/></td>
      <td width="15%" class="left_title_1"></td>
      <td width="37%"></td>
    </tr>
    <tr>
      <td width="33%" class="left_title_1"><span class="left-title">参数名称</span></td>
      <td width="15%"><label for="Cname"></label>
        <input type="text" name="parameteName" id="parameteName"  value="<%= Tools.nvl(crs.getString("parameteName"))%>"/></td>
      <td width="15%" class="left_title_1">参数中文名称</td>
      <td width="37%"><input type="text" name="parameteChName" id="parameteChName"  value="<%= Tools.nvl(crs.getString("parameteChName"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_2">Y轴单位间隔</td>
      <td><input type="text" name="configYPerValue" id="configYPerValue"  value="<%= Tools.nvl(crs.getString("configYPerValue"))%>"/></td>
            <td class="left_title_2">Y轴最小单位</td>
      <td><input type="text" name="configYMinValue" id="configYMinValue"  value="<%= Tools.nvl(crs.getString("configYMinValue"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_1">Y轴长度</td>
      <td><input type="text" name="configYValueCount" id="configYValueCount"  value="<%= Tools.nvl(crs.getString("configYValueCount"))%>"/></td>
            <td class="left_title_1">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <div align="center" style="margin-top:20px;">
	<input type="submit" name="button" id="button" value="提交" />
  <input type="reset" name="button2" id="button2" value="重置" />
  </div>
</form>
<iframe id='submitframe' name='submitframe' width='100%' height='200' src='' frameborder='NONE' style='display:none'></iframe>
</div>
</body>
</html>