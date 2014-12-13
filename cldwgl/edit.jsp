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

  <%
String [] col_name 	= {"id","Cname","Ctype","Cnum","Cmake","Couttime","Cjtime","Ctestmb","Ctestdh","Tnum","Tyqxh","Tyqnum","Tstime","Ttime","Tmeb","Tmebdh","dataCreateTime","dataOperman"};
String [] col_headname 	= {"id","车辆<br>名称","车辆<br>型号","车辆出<br>厂编号","车辆制<br>造单位","Couttime","Cjtime","Ctestmb","Ctestdh","Tnum","Tyqxh","Tyqnum","Tstime","Ttime","Tmeb","Tmebdh","dataCreateTime","dataOperman"};
%>
  <%
	crs.next();
%>

<iframe id='submitframe' name='submitframe' width='100%' height='200' src='' frameborder='NONE' style='display:none'></iframe>
  <form  name="formHead"  action="MainServlet_sys" onsubmit="return testColValue();" method="post" target="submitframe">
    <input type="hidden" name="fid" value="FCL00.00.09.04" />
    <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
          <th colspan="4">车辆单位修改</th>
      <tr style="display:none">
        <td width="17%" class="left_title_1"><span class="left-title">id</span></td>
        <td width="30%"><label for="Cname"></label>
            <input type="text" name="id" id="id"  value="<%= Tools.nvl(crs.getString("id"))%>"/></td>
        <td width="17%" class="left_title_1"></td>
        <td width="36%"></td>
      </tr>
	  <tr>
      <td width="33%" class="left_title_1"><span class="left-title">车辆单位编号</span></td>
      <td width="14%"><label for="textfield"></label><input type="text" name="work_unitCode" id="work_unitCode "  value="<%= Tools.nvl(crs.getString("work_unitCode"))%>"/></td>
      <td width="34%" class="left_title_1">车辆单位名称</td>
      <td width="19%"><input type="text" name="work_unit" id="work_unit" value="<%= Tools.nvl(crs.getString("work_unit"))%>"/></td>
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