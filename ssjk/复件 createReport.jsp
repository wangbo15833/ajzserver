<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="java.util.HashMap"%>
<%@ include  file='../identification.jsp' %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
<title>生成报告</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="../css/001.css"  							type="text/css" >
<link rel="stylesheet"  href="../css/style1.css" type=text/css>
<link rel="stylesheet"  href="css/calendar-win2k-cold-1.css"  	type="text/css">
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
  <%
	crs.next();
%>
<body>
<div id="man_zone" style="padding-top:20px;">
<form  name="formHead"  action="MainServlet_sys" onsubmit="return testColValue();" method="post" target="submitframe">
<input type="hidden" name="fid" value="FCL00.00.04.11" />
<table width='97%' border='0' cellspacing='0' cellpadding='0'>
<tr>
<td bgcolor='#dedfd7'>
  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  <th colspan="4"><font size="+1">试验车辆监测完毕数据处理</font></th>
    <tr>
      <td width="25%"></td>
      <td width="25%"></td>
      <td width="26%"></td>
      <td width="24%"></td>
    </tr>
	
	<tr>
      <td  class="left_title_1">试验编号</td>
      <td><input type="text" name="tp_testnum" id="tp_testnum"  value="<%= Tools.nvl(crs.getString("tp_testnum"))%>" style="border:0px blue solid; border-bottom:1px blue;" readonly="readonly"/></td>
      <td class="left_title_1">车辆单位</td>
      <td><input type="text" name="tp_carproduction" id="tp_carproduction"  value="<%= Tools.nvl(crs.getString("tp_carproduction"))%>" style="border:0px blue solid; border-bottom:1px blue;" readonly="readonly"/></td>
    </tr>
	
    <tr>
      <td  class="left_title_1">车辆名称</td>
      <td><input type="text" name="tp_carname" id="tp_carname"  value="<%= Tools.nvl(crs.getString("tp_carname"))%>" readonly="readonly"/></td>
      <td class="left_title_1">车辆型号</td>
      <td><input type="text" name="tp_cartype" id="tp_cartype"  value="<%= Tools.nvl(crs.getString("tp_cartype"))%>" readonly="readonly"/></td>
    </tr>
    <tr>
      <td class="left_title_2">车辆编号</td>
      <td><input type="text" name="tp_carnum" id="tp_carnum"  value="<%= Tools.nvl(crs.getString("tp_carnum"))%>" readonly="readonly"/></td>
      <td class="left_title_2">试验仪编号</td>
      <td><input type="text" name="tp_testtoolnum" id="tp_testtoolnum"  value="<%= Tools.nvl(crs.getString("tp_testtoolnum"))%>" readonly="readonly"/></td>
    </tr>
    <tr>
      <td class="left_title_2">监控起始时间</td>
      <td><input type="text" name="TCjk_startTime" id="TCjk_startTime"  value="<%= Tools.nvl(crs.getString("TCjk_startTime"))%>" readonly="readonly" /></td>
	  <td class="left_title_2">监控结束时间</td>
      <td><input type="text" name="TCjk_endTime" id="TCjk_endTime"  readonly="true" value="<%= Tools.nvl(crs.getString("TCjk_endTime"))%>"  /></td>
    </tr>
	<tr>
      <td class="left_title_2">测试起始时间</td>
      <td><input type="text" name="tp_teststarttime" id="tp_teststarttime"  value="<%= Tools.nvl(crs.getString("tp_teststarttime"))%>"  readonly="readonly"/></td>
	   <td class="left_title_2">测试结束时间</td>
        <td><input type="text" name="tp_testendtime" id="tp_testendtime"  readonly="true" value="<%= Tools.nvl(crs.getString("tp_testendtime"))%>"  /></td>
    </tr>
    <tr>
      <td class="left_title_1">试验总时长</td>
      <td><input type="text" name="tp_testtotaltime" id="tp_testtotaltime"  readonly="true" value="<%= Tools.nvl(crs.getString("tp_testtotaltime"))%>"  /></td>
            <td class="left_title_1"></td>
      <td></td>
    </tr>
    <tr>
      <td class="left_title_1">鉴定站试验负责人</td>
      <td><input type="text" name="tp_testmeb" id="tp_testmeb"  readonly="true" value="<%= Tools.nvl(crs.getString("tp_testmeb"))%>"  /></td>
           <td class="left_title_1">鉴定站试验负责人联系电话(手机)</td>
      <td><input type="text" name="tp_testmebphone" id="tp_testmebphone"  readonly="true" value="<%= Tools.nvl(crs.getString("tp_testmebphone"))%>"  /></td>
    </tr>
  </table>
	</td></tr></table>	
  <div align="center" style="padding-top:20px;">
  <input type="button" name="button" id="button" value="查看故障报告"  onclick="viewGZBG();" />
  <%
//通过角色进行身份判断 
if(usertype.equals("系统管理员")||usertype.equals("检测站人员")){
	%>
  <input type="button" name="button2" id="button2" value="生成可靠性报告" onclick="alert('已在实验档案中生成可靠性报告。请在检测报告中查询(*^__^*) ……！'); mySubmit();" />	
<%
}
%>
</div>
</form>
</div>
<script>
function mySubmit(){
	formHead.submit();
}
function viewGZBG(){
	//var theUrl 		= "ssjk/MY.html";
	var tp_testnum = document.getElementById('tp_testnum').value;
	var theUrl 		= "MainServlet_sys?fid=FCL00.00.06.02&tp_testnum="+tp_testnum;	
	var style			= "status:no;help:no;dialogWidth:65;dialogHeight:35;dialogTop:100;dialogLeft:250";
	//var theValue 	= window.showModalDialog(theUrl,null,style);
	winOpen(theUrl,700,400,50,50);
}
function viewJCBG(){
	//var theUrl 		= "ssjk/MY.html";
	var operStyle        = none;
    var operStyle_cancal = none;
	alert(operStyle+operStyle_cancal);
	var tp_testnum = document.getElementById('tp_testnum').value;
	var theUrl 		= "MainServlet_sys?fid=FCL00.00.04.03&tp_testnum="+tp_testnum+"&operStyle="+operStyle+"&operStyle_cancal="+operStyle_cancal;	
	
	var style			= "status:no;help:no;dialogWidth:65;dialogHeight:35;dialogTop:100;dialogLeft:250";
	//var theValue 	= window.showModalDialog(theUrl,null,style);
	winOpen(theUrl,700,400,50,50);
}
function winOpen(url, width, height, top, left){
	var attr = "width="+width+",height="+height+", location=1, menubar=1, scrollbars=1, toolbar=1, status=1, resizable=1, top="+top+",left="+left;
	var myWin = window.open(url, "testWindow", attr);
	myWin.focus();
}
</script>
<iframe id='submitframe' name='submitframe' width='100%' height='200' src='' frameborder='NONE' style='display:none'></iframe>
</body>
</html>