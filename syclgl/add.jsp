<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ include  file='../identification.jsp' %> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet"  href="../css/common.css" type="text/css" />
<link rel="stylesheet"  href="../css/calendar-win2k-cold-1.css" type=text/css>
<link rel="stylesheet"  href="css/calendar-win2k-cold-1.css"  	type="text/css">
<link rel="stylesheet"  href="css/001.css"  type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 	type=text/css>
<title>添加车辆</title>
<script language="JScript" type="text/javascript"  src="../js/calendar.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-setup.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-zh.js"> </script>
<script>
function testColValue(){
	var colName 		  = new Array("tp_carnum" ,"tp_carname","tp_testtoolnum","tp_testtotaltime","tp_carproduction");
	var colHeadName  	  = new Array("车辆编号","车辆名称","试验仪编号","试验总时长","车辆单位"	);
	for(var j=0; j<colName.length; j++){
		if(document.getElementById(colName[j]).value ==''){
			alert(colHeadName[j]+"不能为空!");
			document.getElementById(colName[j]).focus();
			return false;
		}
	}
	return true;
}
</script>
</head>
<body>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe> 
<div id="man_zone" >
  <form  name="formHead"  action="../MainServlet_sys" onsubmit="return testColValue();" method="post" target="submitframe">
    <input type="hidden" name="fid" value="FCL00.00.00.01" />
    <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <th colspan="4"><font size="+1">新增试验车辆</font></th>
	  <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td colspan="4" align="right"><span class="left-title"><font color="red">试验编号:</font>
              <input type="text" name="tp_testnum" id="tp_testnum"  value="" disabled="disabled" style="border:0px blue solid; border:1px blue;background-color:#dddddd;" readonly=""/>(自动生成)
        </span></td>
      </tr>
	  <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
      <tr>
        <td width="25%" class="left_title_1"><span class="left-title">***车辆单位</span></td>
        <td width="20%" id="td_tp_carproduction"><select name="tp_carproduction" id="tp_carproduction" style="width:180px">
          <option value="">&nbsp;&nbsp;-=请选择=-&nbsp;&nbsp;</option>
          <%=com.zagerbone.util.Tools.popListWithSelect("select work_unit,work_unit from userinfo_tgy group by work_unit","")%>
        </select>
        </td>
        <td width="21%" class="left_title_1">***试验仪编号</td>
        <td width="34%"><select name="tp_testtoolnum" style="width:150px">
          <option value="">&nbsp;&nbsp;-=请选择=-&nbsp;&nbsp;</option>
          <%=com.zagerbone.util.Tools.popListWithSelect("select tp_testtoolnum,tp_testtoolnum from TCSyyq where (tp_testnum='' or tp_testnum is null)","")%>
        </select></td>
      </tr>
      <tr>
        <td class="left_title_2">***车辆编号</td>
        <td><input type="text" name="tp_carnum" id="tp_carnum" /></td>
        <td class="left_title_2">***车辆名称</td>
        <td><input type="text" name="tp_carname" id="tp_carname" /></td>
      </tr>
      <tr>
        <td class="left_title_1">车辆型号</td>
        <td><input type="text" name="tp_cartype" id="tp_cartype" /></td>
        <td class="left_title_1">***试验总时长(小时)</td>
        <td><input type="text" name="tp_testtotaltime" id="tp_testtotaltime"  value="200"/>
        </td>
      </tr>
	  
	  <tr>
        <td class="left_title_1">试验起始时间</td>
        <td ><input type="text" name="tp_teststarttime" id="tp_teststarttime"  value="2010-10-01" ondblclick="return showCalendar('tp_teststarttime','y-mm-dd');" /><img src="../imags/calbtn.gif"  onclick="return showCalendar('tp_teststarttime','y-mm-dd');" align="absmiddle" /></td>
        <td class="left_title_1">试验结束时间</td>
        <td><input type="text" name="tp_testendtime" id="tp_testendtime"  value="2010-10-01" ondblclick="return showCalendar('tp_testendtime','y-mm-dd');" /><img src="../imags/calbtn.gif"  onclick="return showCalendar('tp_testendtime','y-mm-dd');" align="absmiddle" /></td>
      </tr>
	  
      <tr>
        <td class="left_title_1">出厂日期</td>
        <td><input type="text" name="tp_carouttime" id="tp_carouttime" value="2010-10-01" ondblclick="return showCalendar('tp_carouttime','y-mm-dd');" /><img src="../imags/calbtn.gif"  onclick="return showCalendar('tp_carouttime','y-mm-dd');" align="absmiddle" /></td>
        <td class="left_title_1">抽检日期</td>
        <td><input type="text" name="tp_carcheckdate" id="tp_carcheckdate" value="2010-10-01" ondblclick="return showCalendar('tp_carcheckdate','y-mm-dd');" /><img src="../imags/calbtn.gif"  onclick="return showCalendar('tp_carcheckdate','y-mm-dd');" align="absmiddle" /></td>
      </tr>
      <tr>
        <td class="left_title_1">车辆单位联系人</td>
        <td><input type="text" name="tp_carcheckmeb" id="tp_carcheckmeb" /></td>
        <td class="left_title_1">鉴定站试验负责人</td>
        <td><input type="text" name="tp_testmeb" id="tp_testmeb" /></td>
      </tr>
      <tr>
        <td class="left_title_2" nowrap="nowrap"><span class="left_title_1">车辆单位联系人</span>电话(手机)</td>
        <td><input type="text" name="tp_carcheckmebphone" id="tp_carcheckmebphone" /></td>
        <td class="left_title_2">鉴定站试验负责人联系电话(手机)</td>
        <td><input type="text" name="tp_testmebphone" id="tp_testmebphone" /></td>
      </tr>
      <tr style="display:none">
        <td class="left_title_2">试验仪型号</td>
        <td><input type="text" name="tp_testtooltypenum" id="tp_testtooltypenum" value=""/></td>
        <td class="left_title_2"></td>
        <td></td>
      </tr>
      <tr >
        <td colspan="4" class="left_title_4" align="center"><font color="blue">***为必填项。</font></td>
      </tr>
    </table>
    <div align="center" style="margin-top:5px;">
      <input type="submit" name="button" id="button" value="提交"   />
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

