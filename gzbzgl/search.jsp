<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<title>故障标准查询</title>
<%
String listMode= Tools.nvl(request.getParameter("listMode")); 
System.out.println("listMode=============================================="+listMode);
%>
</head>
<body>
<div id="man_zone">
<form  name="formHead"  action="../MainServlet_sys?listMode=<%=listMode%>" onsubmit="return testColValue();" method="post" target="_self" >
	<input type="hidden" name="fid" value="FCL00.00.05.02">
  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
   <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
  <th colspan="4"><font size="+1">故障标准查询</font></th>
   <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
    <tr>
      <td width="33%" class="left_title_1"><span class="left-title">故障代码</span></td>
      <td width="14%"><label for="textfield"><select name="tp_faultcode" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_faultcode,tp_faultcode from TCfaultstandard ","")%>
		</select></label>
		</td>
	  <td width="12%" class="left_title_1">故障名称</td>
      <td width="40%"><select name="tp_faultname" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_faultname,tp_faultname from TCfaultstandard ","")%>
		</select></td>
	  <td width="1%"></td>
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
