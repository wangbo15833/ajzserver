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
<body leftmargin="0" topmargin="0" rightmargin="0">
<div id="man_zone">
<!--<div id="nav">
    <ul>
      <li id="man_nav_1" onclick="location = 'syyqgl/search.jsp'"  class="bg_image">数据查询</li>
			<li id="man_nav_1" onclick="location = 'syyqgl/add.jsp'" class="bg_image">数据新增</li>
      <li id="man_nav_2" onclick="list_sub_nav(id,'数据列表')"  class="bg_image"><font style="color:#aaaaaa">数据列表</font></li>
      <li id="man_nav_3" onclick="														"   class="bg_image_onclick">数据编辑</li>
      <li id="man_nav_4" onclick="alert('帮助请查看专门的页面!');"  class="bg_image">在线帮助</li>     
  </ul>
</div>-->
  <%
String [] col_name 	= {"id","Cname","Ctype","Cnum","Cmake","Couttime","Cjtime","Ctestmb","Ctestdh","Tnum","Tyqxh","Tyqnum","Tstime","Ttime","Tmeb","Tmebdh","dataCreateTime","dataOperman"};
String [] col_headname 	= {"id","车辆<br>名称","车辆<br>型号","车辆出<br>厂编号","车辆制<br>造单位","Couttime","Cjtime","Ctestmb","Ctestdh","Tnum","Tyqxh","Tyqnum","Tstime","Ttime","Tmeb","Tmebdh","dataCreateTime","dataOperman"};
%>
  <%
	crs.next();
%>

<iframe id='submitframe' name='submitframe' width='100%' height='200' src='' frameborder='NONE' style='display:none'></iframe>
  <form  name="formHead"  action="MainServlet_sys" onsubmit="return testColValue();" method="post" target="submitframe">
    <input type="hidden" name="fid" value="FCL00.00.08.04" />
    <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
        <th colspan="4">试验仪器修改</th>
      <tr style="display:none">
        <td width="24%" class="left_title_1"><span class="left-title">id</span></td>
        <td width="22%"><label for="Cname"></label>
        <input type="text" name="id" id="id"  value="<%= Tools.nvl(crs.getString("id"))%>"/></td>
        <td width="17%" class="left_title_1"></td>
        <td width="37%"></td>
      </tr>
	  <tr>
      <td width="24%" class="left_title_1"><span class="left-title">试验仪器编号</span></td>
      <td width="22%"><label for="textfield"></label><input type="text" name="tp_testtoolnum" id="tp_testtoolnum "  value="<%= Tools.nvl(crs.getString("tp_testtoolnum"))%>"/></td>
			<td width="17%" class="left_title_1">试验仪器型号</td>
      <td width="37%"><input type="text" name="tp_testtooltypenum" id="tp_testtooltypenum" value="<%= Tools.nvl(crs.getString("tp_testtooltypenum"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_2">目前测试号</td>
      <td><input type="text" name="tp_testnum" id="tp_testnum" value="<%= Tools.nvl(crs.getString("tp_testnum"))%>"/></td>
            <td class="left_title_2">目前测试开始时间</td>
      <td><input type="text" name="tp_teststarttime" id="tp_teststarttime" value="<%= Tools.nvl(crs.getString("tp_teststarttime"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_1">目前测试总时长</td>
      <td><input type="text" name="tp_testtotaltime" id="tp_testtotaltime" value="<%= Tools.nvl(crs.getString("tp_testtotaltime"))%>"/></td>
            <td class="left_title_1">目前测试仪器对应车辆编号</td>
      <td><input type="text" name="tp_carnum" id="tp_carnum" value="<%= Tools.nvl(crs.getString("tp_carnum"))%>"/></td>
    </tr>
    <tr>
      <td class="left_title_2">目前测试仪器对应车辆名称</td>
      <td><input type="text" name="tp_carname" id="tp_carname" value="<%= Tools.nvl(crs.getString("tp_carname"))%>"/></td>
            <td class="left_title_2">目前测试仪器对应车辆型号</td>
      <td><input type="text" name="tp_cartype" id="tp_cartype" value="<%= Tools.nvl(crs.getString("tp_cartype"))%>"/></td>
    </tr>
    </table>
    <div align="center" style="margin-top:5px;">
      <input type="submit" name="button" id="button" value="提交" />
      <input type="reset" name="button2" id="button2" value="重置" />
	  <input type="submit" name="button" id="button" value="返回"   onclick="history.go(-1);"/>
    </div>
  </form>
</div>
</body>
</html>