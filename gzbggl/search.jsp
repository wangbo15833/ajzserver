<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="java.util.HashMap"%>
<%@ include  file='../identification.jsp' %> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<title>故障报告查询</title>
<%
String listMode= Tools.nvl(request.getParameter("listMode")); 
System.out.println("listMode=============================================="+listMode);
%>
</head>
<body>
<div id="man_zone">
<form  name="formHead"  action="../MainServlet_sys?listMode=<%=listMode%>" onsubmit="return testColValue();" method="post" target="_self" >
	<input type="hidden" name="fid" value="FCL00.00.06.02">
  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
    <th colspan="4"><font size="+1">故障报告查询</font></th>
    <tr>
      <td width="33%" class="left_title_1"><span class="left-title">测试编号</span></td>
      <td width="12%"><select name="tp_testnum" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_testnum,tp_testnum from TCfaultreport order by tp_testnum desc","")%>
		</select></td>
			<td width="21%" class="left_title_1"></td>
      <td width="34%"></td>
    </tr>
    <tr>
      <td width="33%" class="left_title_1"><span class="left-title">车辆单位</span></td>
      <td width="12%"  id="td_tp_carproduction"><select name="tp_carproduction" style="width:150px">
							<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
							<%=com.zagerbone.util.Tools.popListWithSelect("select work_unit,work_unit from userinfo_tgy group by work_unit","")%>
						</select></td>
			<td width="21%" class="left_title_1">车辆名称</td>
      <td width="34%"><select name="tp_carname" style="width:230 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_carname, tp_carname from TCfaultreport","")%>
		</select></td>
    </tr>
    <tr>
      <td class="left_title_1">车辆编号</td>
      <td ><label for="textfield"></label><select name="tp_carnum" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_carnum,tp_carnum from TCfaultreport where tp_carnum is not null order by tp_carnum desc","")%>
				</select></td>
		
     <td class="left_title_1">故障类型</td>
      <td><label for="textfield"></label><select name="tp_faulttype" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_faulttype,tp_faulttype from TCfaultreport where tp_faulttype is not null ","")%>
				</select></td>
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
<%
if(usertype.equals("系统管理员")){//||usertype.equals("检测站人员")
	;
}
else{
	%>
	<script>
	
	document.getElementById('td_tp_carproduction').innerHTML='<input type="text" name="tp_carproduction" id="tp_carproduction"  readOnly />';
	document.getElementById('tp_carproduction').value ='<%=work_unit%>'; 
	</script>	
	<%
}
%>