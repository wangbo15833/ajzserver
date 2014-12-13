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
<title>故障标准修改</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="../css/001.css" type="text/css" >
<link rel="stylesheet"  href="../css/style1.css" type=text/css>
<link rel="stylesheet"  href="../css/calendar-win2k-cold-1.css" type="text/css">
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
	crs.next();
%>
<form  name="formHead"  action="MainServlet_sys" onsubmit="return testColValue();" method="post" target="submitframe">
	<input type="hidden" name="fid" value="FCL00.00.05.04">
  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
   <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
    <th colspan="4"><font size="+1">故障标准修改</font></th>
	 <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
		<tr style="display:none">
      <td width="33%" class="left_title_1"><span class="left-title">id</span></td>
      <td width="15%"><label for="Cname"></label><input type="text" name="id" id="id"  value="<%= Tools.nvl(crs.getString("id"))%>"/></td>
      <td width="12%" class="left_title_1"></td>
      <td width="40%"></td>
    </tr>
    <tr>
      <td width="33%" class="left_title_1"><span class="left-title">故障代码</span></td>
      <td width="15%"><label for="textfield"></label><input type="text" name="tp_faultcode" id="tp_faultcode"   value="<%= Tools.nvl(crs.getString("tp_faultcode"))%>"/></td>
			<td width="12%" class="left_title_1">故障名称</td>
      <td width="40%"><input type="text" name="tp_faultname" id="tp_faultname"  value="<%= Tools.nvl(crs.getString("tp_faultname"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_2">微标识</td>
      <td><input type="text" name="qwbs" id="qwbs"  value="<%= Tools.nvl(crs.getString("qwbs"))%>"/></td>
            <td class="left_title_2">轻微停机时长</td>
      <td><input type="text" name="qwtime" id="qwtime"  value="<%= Tools.nvl(crs.getString("qwtime"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_1">一般标识</td>
      <td><input type="text" name="ybbs" id="ybbs"  value="<%= Tools.nvl(crs.getString("ybbs"))%>"/></td>
            <td class="left_title_1">一般停机时长</td>
      <td><input type="text" name="ybtime" id="ybtime"  value="<%= Tools.nvl(crs.getString("ybtime"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_2">重大标识</td>
      <td><input type="text" name="zdbs" id="zdbs"  value="<%= Tools.nvl(crs.getString("zdbs"))%>"/></td>
            <td class="left_title_2">重大停机时长</td>
      <td><input type="text" name="zdtime" id="zdtime"  value="<%= Tools.nvl(crs.getString("zdtime"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_1">其它判断条件1</td>
      <td><input type="text" name="add1" id="add1"  value="<%= Tools.nvl(crs.getString("add1"))%>"/></td>
            <td class="left_title_1">其它判断条件2</td>
      <td><input type="text" name="add2" id="add2"  value="<%= Tools.nvl(crs.getString("add2"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_2">其它判断条件3</td>
      <td><input type="text" name="add3" id="add3"  value="<%= Tools.nvl(crs.getString("add3"))%>"/></td>
            <td class="left_title_2">其它判断条件4</td>
      <td><input type="text" name="add4" id="add4"  value="<%= Tools.nvl(crs.getString("add4"))%>"/></td>
	</tr>
	<tr>
      <td class="left_title_1">其它判断条件5</td>
      <td><input type="text" name="add5" id="add5"  value="<%= Tools.nvl(crs.getString("add5"))%>"/></td>
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