<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
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
<link rel="stylesheet"  href="../css/calendar-win2k-cold-1.css"  	type="text/css">
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
<div id="nav" style="display:none">
    <ul>
      <li id="man_nav_1" onclick="location = 'syclgl/search.jsp'"  	class="bg_image">数据查询</li>
			<li id="man_nav_1" onclick="location = 'syclgl/add.jsp'" 			class="bg_image">数据新增</li>
      <li id="man_nav_2" onclick="list_sub_nav(id,'数据列表')"  		class="bg_image"><font style="color:#aaaaaa">数据列表</font></li>
      <li id="man_nav_3" onclick="														"   	class="bg_image_onclick">数据编辑</li>
      <li id="man_nav_4" onclick="alert('帮助请查看专门的页面!');"  class="bg_image">在线帮助</li>     
  </ul>
</div>
<%
String [] col_name 	= {"id","tp_carname","tp_cartype","tp_caroutnum","tp_carproduction","tp_carouttime","tp_carcheckdate","tp_carcheckmeb","tp_carcheckmebphone","tp_testnum","tp_testtooltypenum","Tyqnum","tp_teststarttime","tp_testtotaltime","tp_operatemeb","tp_testmebphone","dataCreateTime","dataOperman"};
String [] col_headname 	= {"id","车辆名称","车辆型号","车辆出厂编号","车辆制造单位","tp_carouttime","tp_carcheckdate","tp_carcheckmeb","tp_carcheckmebphone","tp_testnum","tp_testtooltypenum","Tyqnum","tp_teststarttime","tp_testtotaltime","tp_operatemeb","tp_testmebphone","dataCreateTime","dataOperman"};
%>
  <%
	crs.next();
%>
  <form  name="formHead"  action="MainServlet_sys?listMode=cancal" onsubmit="return testColValue();" method="post" target="submitframe">
    <input type="hidden" name="fid" value="FCL00.00.00.041" />
    <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
      <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
      </tr>
	  <th colspan="4"><font size="+1">试验车辆监控状态变更</font>
              <input type="hidden" name="ModifyMode" id="ModifyMode"  value="cancal"/></th>
      <tr style="display:none">
        <td width="18%" class="left_title_1"><span class="left-title">id</span></td>
        <td width="15%"><label for="tp_carname"></label>
        <input type="text" name="id" id="id"  value="<%= Tools.nvl(crs.getString("id"))%>"/></td>
        <td width="28%" class="left_title_1"></td>
        <td width="39%"></td>
      </tr>
	  <tr>
	    <td colspan="4" align="right"><span class="left-title"><font color="red">试验编号:</font><input type="text" name="tp_testnum" id="tp_testnum" readonly="true"  value="<%= Tools.nvl(crs.getString("tp_testnum"))%>" style="border:0px blue solid; border-bottom:1px blue;"/></span></td>
	  </tr>
      <tr>
        <td width="18%" class="left_title_1"><span class="left-title">车辆名称</span></td>
        <td width="15%"><label for="tp_carname"></label><input type="text" name="tp_carname" id="tp_carname" readonly="true"  value="<%= Tools.nvl(crs.getString("tp_carname"))%>"/></td>
        <td width="28%" class="left_title_1">正式监控起始时间</td>
        <td width="39%"><input type="text" name="TCjk_startTime" id="TCjk_startTime" readonly="true"  value="<%= Tools.nvl(crs.getString("TCjk_startTime"))%>" /></td>
      </tr>
      <tr>
        <td class="left_title_2">车辆编号</td>
        <td><input type="text" name="tp_carnum" id="tp_carnum" readonly="true"  value="<%= Tools.nvl(crs.getString("tp_carnum"))%>"/></td>
        <td class="left_title_2">车辆型号</td>
        <td><input type="text" name="tp_cartype" id="tp_cartype" readonly="true"  value="<%= Tools.nvl(crs.getString("tp_cartype"))%>"/></td>
      </tr>
	  <tr>
        <td class="left_title_2">试验仪编号</td>
        <td><input type="text" name="tp_testtoolnum" id="tp_testtoolnum" readonly="true"  value="<%= Tools.nvl(crs.getString("tp_testtoolnum"))%>"/></td>
        <td class="left_title_2">试验仪型号</td>
        <td><input type="text" name="tp_testtooltypenum" id="tp_testtooltypenum" readonly="true"  value="<%= Tools.nvl(crs.getString("tp_testtooltypenum"))%>" /></td>
      </tr>
      <tr>
        <td class="left_title_2">车辆单位</td>
        <td><input type="text" name="tp_carproduction" id="tp_carproduction" readonly="true" value="<%= Tools.nvl(crs.getString("tp_carproduction"))%>"/></td>
        <td class="left_title_2">试验初始收割时长</td>
        <td><input type="text" name="Tharvesttime" id="Tharvesttime"   readonly="true"   value="<%= Tools.nvl(crs.getString("Tharvesttime"))%>"  /></td>
      </tr>
      <tr>
        <td class="left_title_1">正式监控结束时间</td>
        <td><input type="text" name="TCjk_endTime" id="TCjk_endTime" readonly="true" value="<%= Tools.nvl(crs.getString("TCjk_endTime"))%>" /></td>
        <td class="left_title_1">试验总时长</td>
        <td><input type="text" name="tp_testtotaltime" id="tp_testtotaltime" readonly="true"  value="<%= Tools.nvl(crs.getString("tp_testtotaltime"))%>"/></td>
      </tr>
      <tr >
        <td class="left_title_2">正式监控状态</td>
        <td><select name="CMonitorStatus" id="CMonitorStatus"  value="取消" >
		  <option value="">-请选择监控状态-</option>
          <option value="0">监控结束</option>
          <option value="1">监控开始</option>
        </select></td>
        <td width="13%" class="left_title_2">检测报告状态</td>
        <td width="40%"><select name="Flag_bgzt" id="Flag_bgzt" disabled="disabled"  value="取消" >
		<%  String CMonitorStatus = Tools.nvl(crs.getString("CMonitorStatus"));
		   if(CMonitorStatus.equals("0"))
		   {
		   %>
		   <option value="0">已出报告</option>
		   <%
		   }
		   else {
		   %>
          <option value="1">未出报告</option>
		  <%
		     }
		  %>
        </select></td>
      </tr>
    </table>
    <div align="center" style="margin-top:20px;">
      <input type="submit" name="button" id="button" value="确定" />
      <input type="reset" name="button2" id="button2" value="重置" />
    </div>
  </form>
  <iframe id='submitframe' name='submitframe' width='100%' height='200' src='' frameborder='NONE' style='display:none'></iframe>
</div>
</body>
</html>