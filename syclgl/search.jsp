<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="java.util.HashMap"%>
<%@ include  file='../identification.jsp' %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<title>试验车辆查询</title>
<%
String listMode= Tools.nvl(request.getParameter("listMode")); 
System.out.println("listMode=============================================="+listMode);
String titleName= Tools.GBK(request.getParameter("titleName")); 
System.out.println("titleName=============================================="+titleName);
if(titleName.equals("")){
	titleName = "试验车辆查询";
}
%>
<link rel="stylesheet"  href="../css/common.css" type="text/css" />
<link rel="stylesheet"  href="../css/calendar-win2k-cold-1.css" type=text/css>
<link rel="stylesheet"  href="css/001.css"       type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 	 type=text/css>
<link rel="stylesheet"  href="css/calendar-win2k-cold-1.css"  	type="text/css">
<script language="JScript" type="text/javascript"  src="../js/calendar.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-setup.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-zh.js"> </script>
<script>
function testColValue(){
	return true;
}
</script>
</head>
<body>
<div id="man_zone">
<form  name="formHead"  action="../MainServlet_sys?listMode=<%=listMode%>" onsubmit="return testColValue();" method="post" target="_self" >
	<input type="hidden" name="fid" value="FCL00.00.00.02">
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
      <td width="297"  class="left_title_1"><span class="left-title">试验编号</span></td>
      <td width="380" ><label for="textfield"></label><select name="tp_testnum" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_testnum,tp_testnum from TCtestcar order by tp_testnum desc","")%>
		</select></td>
		
            <td class="left_title_1">车辆单位</td>
      <td id="td_tp_carproduction"><select name="tp_carproduction" style="width:150px">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select work_unit,work_unit from userinfo_tgy group by work_unit","")%>
				</select></td>

    </tr>
    <tr>
      <td class="left_title_2">车辆编号</td>
      <td><select name="tp_carnum" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_carnum,tp_carnum from TCtestcar order by tp_carnum desc","")%>
				</select></td>
				
							<td width="180" class="left_title_2">车辆名称</td>
      <td width="476"><select name="tp_carname" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_carname,tp_carname from TCtestcar","")%>
				</select></td>
    </tr>    
    <tr>
      <td class="left_title_1">试验仪器编号</td>
      <td><select name="tp_testtoolnum" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_testtoolnum,tp_testtoolnum from TCtestcar order by tp_testtoolnum desc","")%>
				</select></td>
            <td class="left_title_1">试验仪器型号</td>
      <td><select name="tp_testtooltypenum" style="width:250 ">
					<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
					<%=com.zagerbone.util.Tools.popListWithSelect("select distinct tp_testtooltypenum,tp_testtooltypenum from TCSyyq order by tp_testtooltypenum desc","")%>
				</select></td>
    </tr>
    <tr>
      <td class="left_title_2">试验开始时间</td>
      <td style="width:380px"><input type="text" name="tp_teststarttime" id="tp_teststarttime" value="" ondblclick="return showCalendar('tp_teststarttime','y-mm-dd');" /><img src="../imags/calbtn.gif"  onclick="return showCalendar('tp_teststarttime','y-mm-dd');" align="absmiddle" /></td>
      <td class="left_title_2">试验结束时间</td>
      <td style="width:380px"><input type="text" name="tp_testendtime" id="tp_testendtime" value="" ondblclick="return showCalendar('tp_testendtime','y-mm-dd');" /><img src="../imags/calbtn.gif"  onclick="return showCalendar('tp_testendtime','y-mm-dd');" align="absmiddle" /></td>
    </tr>
    <tr>
      <td colspan="4" align="center" class="left_title_3"><font color="blue">* 无查询条件将显示所有数据。</font></td>
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
if(usertype.equals("系统管理员")){//检测站人员也只能查看本单位的内容//||usertype.equals("检测站人员")
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
