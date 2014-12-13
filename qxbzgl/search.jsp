<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<title>试验车辆查询</title>
<%
String listMode= Tools.nvl(request.getParameter("listMode")); 
System.out.println("listMode=============================================="+listMode);
%>
<%
String [] col_name 			= {"id","parameteName","configYPerValue","configYMinValue","configYValueCount"};
String [] col_headname 	= {"id","参数名称","Y轴单位间隔","Y轴最小单位","Y轴长度"};
%>
</head>

<body>
<div id="man_zone">
<form  name="formHead"  action="../MainServlet_sys?listMode=<%=listMode%>" onsubmit="return testColValue();" method="post" target="_self" >
	<input type="hidden" name="fid" value="FCL00.00.07.02">
  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
  <th colspan="4"><font size="+1">曲线参数查询</font></th>
  <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
    <tr>
      <td width="33%" class="left_title_1"><span class="left-title">参数名称</span></td>
      <td width="15%"><label for="textfield"></label><select name="parameteName" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct parameteName,parameteName from TCParaCureStand ","")%>
		</select></td>
	  <td width="16%" class="left_title_1">参数中文名称</td>
	  <td width="36%"><select name="parameteChName" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct parameteChName,parameteChName from TCParaCureStand ","")%>
		</select></td>
    </tr>
    <tr>
      <td class="left_title_2">Y轴单位间隔</td>
      <td><select name="configYPerValue" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct configYPerValue,configYPerValue from TCParaCureStand ","")%>
		</select></td>
            <td class="left_title_2">Y轴最小单位</td>
      <td><select name="configYMinValue" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct configYMinValue,configYMinValue from TCParaCureStand ","")%>
		</select></td>
    </tr>
    <tr>
      <td class="left_title_1">Y轴长度</td>
      <td><select name="configYValueCount" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct configYValueCount,configYValueCount from TCParaCureStand ","")%>
		</select></td>
            <td class="left_title_1">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
  <div align="center" style="margin-top:20px;">
  <input type="submit" name="QueryBn" id="button" value="查询" />
  <input type="reset" name="button2" id="button2" value="重置" />
  </div>
</form>
</div>
</body>
</html>
