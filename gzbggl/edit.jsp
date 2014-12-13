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
<title>故障报告修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="../css/001.css"  							type="text/css" >
<link rel="stylesheet"  href="../css/style1.css" type=text/css>
<link rel="stylesheet"  href="../css/calendar-win2k-cold-1.css"  	type="text/css">
<link rel="stylesheet"  href="css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="js/calendar.js"> </script>
<script language="JScript" type="text/javascript"  src="js/calendar-setup.js"> </script>
<script language="JScript" type="text/javascript"  src="js/calendar-zh.js"> </script>
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
<div id="man_zone" style="padding-top:20px">
<%
	crs.next();
%>
<form  name="formHead"  action="MainServlet_sys" onsubmit="return testColValue();" method="post" target="submitframe">
	<input type="hidden" name="fid" value="FCL00.00.06.04">
  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <th colspan="4">故障报告修改</th>
		<tr style="display:none">
      <td width="33%" class="left_title_1"><span class="left-title">id</span></td>
      <td width="14%"><label for="Cname"></label><input type="text" name="id" id="id"  value="<%= Tools.nvl(crs.getString("id"))%>"/></td>
      <td width="21%" class="left_title_1"></td>
      <td width="32%"></td>
    </tr>
    <tr>
      <td width="33%" class="left_title_1"><span class="left-title">车辆名称</span></td>
      <td width="14%"><label for="textfield"></label><input type="text" name="tp_carname" id="tp_carname"  value="<%= Tools.nvl(crs.getString("tp_carname"))%>"/></td>
			<td width="21%" class="left_title_1">车辆编号</td>
      <td width="32%"><input type="text" name="tp_carnum" id="tp_carnum" value="<%= Tools.nvl(crs.getString("tp_carnum"))%>" /></td>
    </tr>
    <tr>
      <td class="left_title_2">测试编号</td>
      <td><input type="text" name="tp_testnum" id="tp_testnum"  value="<%= Tools.nvl(crs.getString("tp_testnum"))%>"/></td>
            <td class="left_title_2">测试时间</td>
      <td><input type="text" name="tp_testtime" id="tp_testtime" value="<%= Tools.nvl(crs.getString("tp_testtime"))%>" /></td>
    </tr>
	<tr>
      <td class="left_title_1">测试故障代码</td>
      <td><input type="text" name="tp_faultcode" id="tp_faultcode" value="<%= Tools.nvl(crs.getString("tp_faultcode"))%>" /></td>
            <td class="left_title_1">测试故障名称</td>
      <td><input type="text" name="tp_faultname" id="tp_faultname" value="<%= Tools.nvl(crs.getString("tp_faultname"))%>" /></td>
    </tr>
    <tr>
      <td class="left_title_2">测试故障停机时间</td>
      <td><input type="text" name="tp_fault_stoptime" id="tp_fault_stoptime" value="<%= Tools.nvl(crs.getString("tp_fault_stoptime"))%>" /></td>
            <td class="left_title_2">测试故障修复时间</td>
      <td><input type="text" name="tp_fault_recovertime" id="tp_fault_recovertime" value="<%= Tools.nvl(crs.getString("tp_fault_recovertime"))%>" /></td>
    </tr>
    <tr>
      <td class="left_title_1">测试故障停机时长</td>
      <td><input type="text" name="tp_fault_delay" id="tp_fault_delay" value="<%= Tools.nvl(crs.getString("tp_fault_delay"))%>" /></td>
            <td class="left_title_1">测试车辆工作小时</td>
      <td><input type="text" name="tp_workhour" id="tp_workhour" value="<%= Tools.nvl(crs.getString("tp_workhour"))%>" /></td>
    </tr>
    <tr>
      <td class="left_title_2">测试车辆收割时间</td>
      <td><input type="text" name="tp_harvesthour" id="tp_harvesthour" value="<%= Tools.nvl(crs.getString("tp_harvesthour"))%>" /></td>
            <td class="left_title_2">测试故障类型</td>
      <td><input type="text" name="tp_faulttype" id="tp_faulttype" value="<%= Tools.nvl(crs.getString("tp_faulttype"))%>" /></td>
    </tr>
    <tr>
      <td class="left_title_1">故障报告提交人</td>
      <td><input type="text" name="tp_reportoperator" id="tp_reportoperator" value="<%= Tools.nvl(crs.getString("tp_reportoperator"))%>" /></td>
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