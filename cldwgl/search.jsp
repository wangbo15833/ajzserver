<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<title>查询车辆单位</title>
<%
String listMode= Tools.nvl(request.getParameter("listMode")); 
System.out.println("listMode=============================================="+listMode);
String titleName= Tools.nvl(request.getParameter("titleName")); 
System.out.println("titleName=============================================="+titleName);
if(titleName.equals("")){
	titleName = "查询车辆单位";
}
%>
</head>
<body>
<div id="man_zone">
<form  name="formHead"  action="../MainServlet_sys?listMode=<%=listMode%>" onsubmit="return testColValue();" method="post" target="_self" >
	<input type="hidden" name="fid" value="FCL00.00.09.02">
  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
   <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
  <th colspan="4"><font size="+1"><%=titleName%></font></th>
  <tr>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	   <td>&nbsp;</td>
	</tr>
	<tr>
      <td width="34%" class="left_title_1">车辆单位名称</td>
      <td width="13%"><label for="textfield"></label>
	  <select name="work_unit" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct work_unit,work_unit from TCCldw order by work_unit desc","")%>
		</select></td>
	  
	  <td width="13%" class="left_title_1"><span class="left-title">车辆单位编号</span></td>
      <td width="40%"><label for="textfield"></label>
        <select name="work_unitCode" style="width:250 ">
          <option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
          <%=com.zagerbone.util.Tools.popListWithSelect("select distinct work_unitCode,work_unitCode from TCCldw order by work_unitCode desc","")%>
        </select></td>
    </tr>
    <tr  style="display:none;">
      <td class="left_title_2">目前测试号</td>
      <td><label for="textfield"></label>
	  <select name="tp_testnum" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_testnum,tp_testnum from TCSyyq order by tp_testnum desc","")%>
		</select></td>
            <td class="left_title_2">目前测试开始时间</td>
      <td><input type="text" name="tp_teststarttime" id="tp_teststarttime" /></td>
    </tr>
    <tr>
      <td colspan="4" align="center" class="left_title_3"><font color="blue">*无查询条件将显示所有数据。</font></td>
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
