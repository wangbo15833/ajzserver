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
<meta http-equiv="Content-Type" content="text/html; charset=GB2312">
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link href="../css/calendar-win2k-cold-1.css" rel="stylesheet" type=text/css>
<link rel="stylesheet"  href="css/001.css" type="text/css" >
<link rel="stylesheet"  href="css/style1.css" type=text/css>
<link rel="stylesheet"  href="css/calendar-win2k-cold-1.css" type="text/css">
<style   media=print> 
.noPrint{display:none;} 
.PageNext{page-break-after:   avoid;   page-break-before:   avoid;} 
</style> 
<script language="JScript" type="text/javascript"  src="../js/calendar.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-setup.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/calendar-zh.js"> </script>
<script>
function editSubmit_button(){//设置确定按钮的操作容许735
	if(parent.activeIframe == 'listframe'){
		parent.openIframe("listframe");
		parent.closeIframe("editframe");
	}
}
function printit()
  {   
  if(confirm('确定打印吗？'))
    {/*不打印按钮*/
      var win=window.open("","检测报告打印","width=800,height=700,scrollbars=yes,resizable=yes,status=yes");<!--menubar=yes,-->
      win.document.write(document.getElementById('aa').parentElement.innerHTML)
      win.document.write("<script>print();</"+"script>");
	  win.document.getElementById('button').style.display='none';
	  win.document.getElementById('button2').style.display='none';
      win.location.reload();
    }
  }
function preview()
  {  
      var win=window.open("","检测报告预览","width=800,height=700,scrollbars=yes,resizable=yes,status=yes");
      win.document.write(document.getElementById('aa').parentElement.innerHTML)
	  win.document.getElementById('button').style.display='none';
	  win.document.getElementById('button2').style.display='none';      
      //win.location.reload();   
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
	crs.next();//FCL00.00.04.04
%>
<form  name="formHead"  action="MainServlet_sys" onsubmit="return testColValue();" method="post" target="submitframe">
	<input type="hidden" name="fid" value="FCL00.00.04.04">
<form action="jcreport.jsp" method="post" name="form">
<table id="aa" height="200px"  width='97%' border='0' cellspacing='0' cellpadding='0'>
<tr>
<td>
<table  height="200px"  width='97%' border='0' cellspacing='0' cellpadding='0'>
<tr>
<td bgcolor='#dedfd7'>

  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  <th colspan="4">测试车辆检测报告</th>
    <tr>
      <td width="21%"></td>
      <td width="21%"></td>
      <td width="22%"></td>
      <td width="36%"></td>
    </tr>
    
    <tr>
      <td  class="left_title_1">试验编号</td>
      <td><input type="text" name="tp_testnum" id="tp_testnum" value="<%= Tools.nvl(crs.getString("tp_testnum"))%>" readonly="true" /></td>
      <!--检测报告？应不应被修改，设置成只读-->
	  <td class="left_title_1">车辆单位</td>
      <td><input type="text" name="tp_carproduction" id="tp_carproduction" value="<%= Tools.nvl(crs.getString("tp_carproduction"))%>" readonly="true" /></td>
    </tr>
	<tr>
	  <td class="left_title_2">车辆编号</td>
      <td><input type="text" name="tp_carnum" id="tp_carnum" value="<%= Tools.nvl(crs.getString("tp_carnum"))%>" readonly="true" /></td>
      <td class="left_title_2">车辆名称</td>
      <td><input type="text" name="tp_carname" id="tp_carname" value="<%= Tools.nvl(crs.getString("tp_carname"))%>" readonly="true" /></td>
      
    </tr>
    <tr>
      <td class="left_title_1">测试仪器编号</td>
      <td><input type="text" name="tp_testtoolnum" id="tp_testtoolnum" value="<%= Tools.nvl(crs.getString("tp_testtoolnum"))%>"  readonly="true"/></td>
            <td class="left_title_1">测试仪器型号</td>
      <td><input type="text" name="tp_testtooltypenum" id="tp_testtooltypenum" value="<%= Tools.nvl(crs.getString("tp_testtooltypenum"))%>" readonly="true" /></td>
    </tr>
    <tr>
      <td class="left_title_2">测试开始时间</td>
      <td><input type="text" name="tp_teststarttime" id="tp_teststarttime" value="<%= Tools.nvl(crs.getString("tp_teststarttime"))%>" readonly="true" /></td>
            <td class="left_title_2">测试结束时间</td>
      <td><input type="text" name="tp_testendtime" id="tp_testendtime" value="<%= Tools.nvl(crs.getString("tp_testendtime"))%>" readonly="true" /></td>
    </tr>
    <tr>
      <td class="left_title_1">测试总时长</td>
      <td><input type="text" name="tp_testtotaltime" id="tp_testtotaltime"  value="<%= Tools.nvl(crs.getString("tp_testtotaltime"))%>"  readonly="true"/></td>
            <td class="left_title_1">车辆收割时长</td>
      <td><input type="text" name="tp_harvesthour" id="tp_harvesthour" value="<%= Tools.nvl(crs.getString("tp_harvesthour"))%>" readonly="true" /></td>
    </tr>
    <tr>
      <td class="left_title_2">测试负责人</td>
      <td><input type="text" name="tp_testmeb" id="tp_testmeb"  value="<%= Tools.nvl(crs.getString("tp_testmeb"))%>"  readonly="true"/></td>
           <td class="left_title_2">测试负责人联系电话</td>
      <td><input type="text" name="tp_testmebphone" id="tp_testmebphone"  value="<%= Tools.nvl(crs.getString("tp_testmebphone"))%>" readonly="true" /></td>
    </tr>
  </table>
	</td></tr></table>
<table width='97%' border='0' cellspacing='0' cellpadding='0'>
<tr>
<td bgcolor='#dedfd7'>
  <table width="100%" border="0" cellpadding="3" cellspacing="1" class="table_style">
  <tr>
    <td width="35%" ></td>
    <td width="65%"></td>
  </tr>
  <tr>
    <td class="left_title_1_1" >1.监控开始时间：</td>
    <td><input type="text" name="TCjk_startTime" id="TCjk_startTime"  value="<%= Tools.nvl(crs.getString("TCjk_startTime"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_1_1">2.监控结束时间：</td>
    <td><input type="text" name="TCjk_endTime" id="TCjk_endTime"  value="<%= Tools.nvl(crs.getString("TCjk_endTime"))%>"  readonly="true"/></td>
  </tr>
  <!--<tr>
    <td class="left_title_2_2">&nbsp;</td>
    <td>*</td>
  </tr>-->
  <tr>
    <td class="left_title_1_1">3.检测故障统计：</td>
    <td></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
      
        <li><img src="images/list.jpg" width="19" height="15"  />轻微故障次数Nq：</li>
      
    </ul></td>
    <td><input type="text" name="Nqw" id="Nqw"  value="<%= Tools.nvl(crs.getString("Nqw"))%>"  readonly="true"/></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
      
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />一般故障次数Ny：</li>
      
    </ul></td>
    <td><input type="text" name="Nyb" id="Nyb"  value="<%= Tools.nvl(crs.getString("Nyb"))%>"  readonly="true"/></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />重大故障次数Nz：</li>
    </ul></td>
    <td><input type="text" name="Nzd" id="Nzd"  value="<%= Tools.nvl(crs.getString("Nzd"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />非轻微故障次数NF：NF=Ny+Nz：</li>
    </ul></td>
    <td><input type="text" name="Nfqw" id="Nfqw"  value="<%= Tools.nvl(crs.getString("Nfqw"))%>"  readonly="true"/></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
         <li><img src="images/list.jpg" alt="" width="19" height="15"  />全部故障次数N：N=Nq+Ny+Nz：    </li>
    </ul></td>
    <td><input type="text" name="Ntotal" id="Ntotal"  value="<%= Tools.nvl(crs.getString("Ntotal"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_1_1">4.按全部故障计算的可靠性指标：</td>
    <td></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />首次故障前平均故障时间</li>
    </ul></td>
    <td><input type="text" name="TAfirst" id="TAfirst"  value="<%= Tools.nvl(crs.getString("TAfirst"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />平均故障间隔时间</li>
    </ul></td>
    <td><input type="text" name="TAgzjg" id="TAgzjg"  value="<%= Tools.nvl(crs.getString("TAgzjg"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />平均修复时间</li>
    </ul></td>
    <td><input type="text" name="TArep" id="TArep"  value="<%= Tools.nvl(crs.getString("TArep"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />有效度</li>
    </ul></td>
    <td><input type="text" name="TAef" id="TAef"  value="<%= Tools.nvl(crs.getString("TAef"))%>" readonly="true" /></td>
  </tr>
  <!--<tr>
    <td>&nbsp;</td>
    <td>*</td>
  </tr>-->
  <tr>
    <td class="left_title_1_1">5.按非轻微故障计算的可靠性指标：</td>
    <td></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />首次故障前平均故障时间</li>
    </ul></td>
    <td><input type="text" name="FAfirst" id="FAfirst"  value="<%= Tools.nvl(crs.getString("FAfirst"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />平均故障间隔时间</li>
    </ul></td>
    <td><input type="text" name="FAgzjg" id="FAgzjg"  value="<%= Tools.nvl(crs.getString("FAgzjg"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />平均修复时间</li>
    </ul></td>
    <td><input type="text" name="FArep" id="FArep"  value="<%= Tools.nvl(crs.getString("FArep"))%>" readonly="true"/></td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />有效度</li>
    </ul></td>
    <td><input type="text" name="Faef" id="Faef"  value="<%= Tools.nvl(crs.getString("Faef"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_1_1">6.可靠性试验结论：</td>
    <td><input type="text" name="result" id="result"  value="<%= Tools.nvl(crs.getString("result"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_1_1">报告生成时间：</td>
    <td><input type="text" name="reporttime" id="reporttime"  value="<%= Tools.nvl(crs.getString("reporttime"))%>" readonly="true" /></td>
  </tr>
  <tr>
    <td class="left_title_2_2">负责人（签字）：</td>
    <td class="left_title_2_2">检测单位(盖章)：</td><!--<input type="text" name="tp_reportoperator" id="tp_reportoperator"  value=""  readonly="true"/>--->
  </tr>
  <!--<tr>
    <td class="left_title_1_1">检测单位(盖章)：</td>
    <td></td><input type="text" name="testcompany" id="testcompany"  value="" readonly="true" />-->
  </table>
</td></tr></table>	
	</td>
    </tr>
    </table>
</form>
  <div align="center" style="padding-top:20px;"> 
  <input class="noPrint" type="submit" name="button" id="button" value="预览报告"  onclick="javascript:preview()"/>
  <input class="noPrint" type="button" name="button2" id="button2" value="打印报告" onclick="javascript:printit()"/>
</div>
</form>
</div>
</body>
</html>
