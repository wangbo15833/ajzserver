<%@ page import="java.util.*"%>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="com.shenghao.arch.manage.user.*"%>
<%@ page import="com.zagerbone.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="../css/common.css" type="text/css" />
<title>测试车辆检测报告</title>
</head>

<body>
<div id="man_zone">
<form action="#" onsubmit="">
<table width='97%' border='0' cellspacing='0' cellpadding='0'>
<tr>
<td bgcolor='#dedfd7'>

  <table width="100%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  <th colspan="4">测试车辆检测报告</th>
    <tr>
      <td width="25%"></td>
      <td width="25%"></td>
      <td width="26%"></td>
      <td width="24%"></td>
    </tr>
    
    <tr>
      <td  class="left_title_1">试验编号</td>
      <td><input type="text" name="tp_testnum" id="tp_testnum" value="<%= Tools.nvl(crs.getString("tp_carname"))%>" /></td>
      <td class="left_title_1">车辆名称</td>
      <td><input type="text" name="tp_carname" id="tp_carname" value="<%= Tools.nvl(crs.getString("tp_carname"))%>" /></td>
    </tr>
	<tr>
      <td class="left_title_2">车辆编号</td>
      <td><input type="text" name="tp_carnum" id="tp_carnum" value="<%= Tools.nvl(crs.getString("tp_carnum"))%>" /></td>
            <td class="left_title_2">车辆型号</td>
      <td><input type="text" name="tp_cartype" id="tp_cartype" value="<%= Tools.nvl(crs.getString("tp_cartype"))%>" /></td>
    </tr>
    <tr>
      <td class="left_title_2">测试仪器编号</td>
      <td><input type="text" name="tp_testtoolnum" id="tp_testtoolnum" value="<%= Tools.nvl(crs.getString("tp_testtoolnum"))%>" /></td>
            <td class="left_title_2">测试仪器型号</td>
      <td><input type="text" name="tp_testtooltypenum" id="tp_testtooltypenum" value="<%= Tools.nvl(crs.getString("tp_testtooltypenum"))%>" /></td>
    </tr>
    <tr>
      <td class="left_title_1">测试开始时间</td>
      <td><input type="text" name="tp_teststarttime" id="tp_teststarttime" value="<%= Tools.nvl(crs.getString("tp_teststarttime"))%>" /></td>
            <td class="left_title_1">测试结束时间</td>
      <td><input type="text" name="tp_testendtime" id="tp_testendtime" value="<%= Tools.nvl(crs.getString("tp_testendtime"))%>" /></td>
    </tr>
    <tr>
      <td class="left_title_2">测试总时长</td>
      <td><input type="text" name="tp_testtotaltime" id="tp_testtotaltime"  value="<%= Tools.nvl(crs.getString("tp_testtotaltime"))%>" /></td>
            <td class="left_title_2">车辆收割时长</td>
      <td><input type="text" name="tp_harvesthour" id="tp_harvesthour" value="<%= Tools.nvl(crs.getString("tp_harvesthour"))%>" /></td>
    </tr>
    <tr>
      <td class="left_title_1">鉴定站测试负责人</td>
      <td><input type="text" name="tp_carname" id="tp_carname"  value="<%= Tools.nvl(crs.getString("tp_carname"))%>" /></td>
           <td class="left_title_1">鉴定站测试负责人联系电话</td>
      <td><input type="text" name="tp_carname" id="tp_carname"  value="<%= Tools.nvl(crs.getString("tp_carname"))%>" /></td>
    </tr>
  </table>
	</td></tr></table>
	
	<br />
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
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_1_1"><ul>
      <li>2.监控结束时间： </li>
    </ul></td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2">&nbsp;</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_1_1"><ul>
      <li>3.试验故障统计： </li>
    </ul></td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
      <ul>
        <li><img src="images/list.jpg" width="19" height="15"  />轻微故障次数Nq：</li>
      </ul>
    </ul></td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
      <ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />一般故障次数Ny：</li>
      </ul>
    </ul></td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
      <ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />重大故障次数Nz：</li>
      </ul>
    </ul></td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
      <ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />非轻微故障次数NF：NF=Ny+Nz：</li>
      </ul>
    </ul></td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><ul>
      <ul>
        <li><img src="images/list.jpg" alt="" width="19" height="15"  />全部故障次数N：N=Nq+Ny+Nz：    </li>
      </ul>
    </ul></td>
    <td>*</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_1_1">4.按全部故障计算的可靠性指标：</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><img src="images/list.jpg" alt="" width="19" height="15"  />首次故障前平均故障时间</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><img src="images/list.jpg" alt="" width="19" height="15"  />平均故障间隔时间</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><img src="images/list.jpg" alt="" width="19" height="15"  />平均修复时间</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><img src="images/list.jpg" alt="" width="19" height="15"  />有效度</td>
    <td>*</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_1_1">5.按非轻微故障计算的可靠性指标：</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><img src="images/list.jpg" alt="" width="19" height="15"  />首次故障前平均故障时间</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><img src="images/list.jpg" alt="" width="19" height="15"  />平均故障间隔时间</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><img src="images/list.jpg" alt="" width="19" height="15"  />平均修复时间</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2"><img src="images/list.jpg" alt="" width="19" height="15"  />有效度</td>
    <td>*</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_1_1">6.可靠性试验结论：</td>
    <td>*</td>
  </tr>
  <tr>
    <td class="left_title_2_2">&nbsp;</td>
    <td>*</td>
  </tr>
  <tr >
  	<td >&nbsp;</td>
    <td  align="left" style="padding-left:300px;;">试验报告生成时间：<br />
      负责人（签字）：<br />
      试验单位(盖章)：</td>
  </tr>
  </table>
</td></tr></table>	
	
	
  <div align="center" style="padding-top:20px;">
  <input type="submit" name="button" id="button" value="打印报告" />
  <input type="reset" name="button2" id="button2" value="导出报告" />
</div>
</form>
</div>
</body>
</html>
