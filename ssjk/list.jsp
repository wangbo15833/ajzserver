<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%
try{
String usertype = (String)session.getAttribute("Myusertype");
System.out.println("usertype=============================================="+usertype);

String listMode= Tools.nvl(request.getParameter("listMode")); 
System.out.println("listMode=============================================="+listMode);
String operStyle = "none";
String operStyle_cancal = "none";
if(listMode.equals("")){
	operStyle = "block";
}else if(listMode.equals("cancal")){
	operStyle_cancal = "block"; 
}
  EventResponse eventResponse = (EventResponse)request.getAttribute("result"); 
	Query queryCon = null;
	CachedRowSet crs = null;
	if(eventResponse != null){
		queryCon = (Query)eventResponse.getBody();  
		crs = (CachedRowSet)queryCon.getConditionBody();
	}
%>
<%
String [] col_name = {"id","tp_carproduction","tp_testnum","tp_carname","tp_carnum","tp_testtoolnum","tp_testtime","tp_faultinfo","tp_car_longitude","tp_car_latitude","tp_car_placestate","tp_longitude_dir","tp_latitude_dir","tp_car_watertemp","tp_car_oillevel","tp_car_eginespeed","tp_jlspeed_one","tp_jlspeed_two","tp_workhour","tp_harvesthour","tp_oiltemp","tp_car_speed","tp_tlgt_speed","tp_gtwb_speed","tp_stateinfo","tp_oversign","tp_checkcode","BIT0","BIT1","BIT2","BIT3","BIT4","BIT5","BIT6","BIT7","BIT8","BIT9","BIT10","BIT11","BIT12","BIT13","BIT14","BIT15","BIT16","BIT17","BIT18","tp_st_BIT_one","tp_st_BIT_two","tp_st_BIT_three","tp_st_BIT_four","tp_st_BIT_five","tp_st_BIT_six","tp_st_BIT_seven","tp_st_BIT_eight","tp_st_BIT_nine","tp_st_BIT_ten","tp_st_BIT_eleven","tp_st_BIT_twelve","tp_st_BIT_thirteen","tp_st_BIT_fourteen","tp_st_BIT_fifteen","tp_st_BIT_sixteen","tp_ft_stopMachine","tp_startsign","tp_datacode"};

String [] col_headname 	= {"id","车辆单位","测试编号","车辆名称","车辆编号","测试仪器编号","测试时间","故障信息","车辆经度","车辆纬度","车辆定位状态","车辆经度方向","车辆纬度方向","车辆冷却水温","车辆燃油液位","车辆发动机转速","1#绞龙转速","2#绞龙转速","车辆工作小时","车辆收割时间","车辆液压油温","车辆车速","车辆脱粒滚筒转速","车辆割台尾部转速","车辆状态信息","测试结束符","测试校验码","机油压力低","充电指示","拍草","满仓","燃油液位低","发动机水温高","紧急停车","1#绞龙转速报警","2#绞龙转速报警","液压油温高报警","脱离滚筒失速","割台传动失速","割台升故障","割台降故障","左转向故障","右转向故障","1#自动深浅故障","2#自动深浅故障","保留不足","状态信息1","状态信息2","状态信息3","状态信息4","状态信息5","状态信息6","状态信息7","状态信息8","状态信息9","状态信息10","状态信息11","状态信息12","状态信息13","状态信息14","状态信息15","状态信息16","测试车辆停机","测试起始符","测试数据编码"};
%>
<head>
<title>车辆监控记录列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="../css/001.css"  							type="text/css" >
<link rel="stylesheet"  href="../css/style1.css" type=text/css>
<link rel="stylesheet"  href="../css/calendar-win2k-cold-1.css"  	type="text/css">
<link rel="stylesheet"  href="css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="js/tools.js"> </script>
<style>
td{font-size:12px; padding-left:3px;padding-right:3px;}
th{font-size:14px; font-weight:bold; margin-bottom:20px;}
</style>
<script>
var preClassName = "man_nav_1";
function list_sub_nav(Id,sortname){
   if(preClassName != ""){
      getObject(preClassName).className="bg_image";
   }
   if(getObject(Id).className == "bg_image"){
      getObject(Id).className="bg_image_onclick";
      preClassName = Id;	//保持全局变量
	  //联动左边栏目  MenuObj
	 }
}
function getObject(objectId) {
    if(document.getElementById && document.getElementById(objectId)) {
	// W3C DOM
	return document.getElementById(objectId);
    } else if (document.all && document.all(objectId)) {
	// MSIE 4 DOM
	return document.all(objectId);
    } else if (document.layers && document.layers[objectId]) {
	// NN 4 DOM.. note: this won't find nested layers
	return document.layers[objectId];
    } else {
	return false;
    }
}
function editSubmit_button(){//设置确定按钮的操作容许
	if(parent.activeIframe == 'listframe'){
		parent.openIframe("listframe");
		parent.closeIframe("editframe");
	}
}
function confirmButton(operationMode, tr_row,id_value){
	var ok = false; 
	alert(tr_row);
	//document.getElementById("tr_"+tr_row).innerHTML="";
	if(operationMode== 'input' ){ 
		ok= window.confirm('您将要输入数据,\n'+'您确定吗?')	
	}	
	else if(operationMode== 'update' ){ 
		ok= window.confirm('您将要修改数据,\n'+'您确定吗?')	
	}	
	else if(operationMode== 'delete' ){ 
		ok= window.confirm('您将要删除数据,\n'+'您确定吗?')	
	}	
	if(ok==false){
		return false;
	}	
	<%
		for(int jk=0; jk<col_name.length; jk++){
		//for(int jk=0; jk<1; jk++){
			%>
			document.getElementById("tr_"+tr_row+"_<%= col_name[jk]%>").innerHTML="";
			<%
		}
	%>
	//alert(document.getElementById("tr_1_id").innerHTML);
	document.getElementById("tr_"+tr_row+"_edit").innerHTML="&nbsp;";
	submitframe.location = "MainServlet_sys?fid=FCL00.00.01.05&id="+id_value;
	return ok;
}
</script> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0">
<%
/*
usertype = "系统管理员";
usertype = "检测站人员";
usertype = "车辆单位";
usertype = "其他人员";
*/
//通过角色进行身份判断 
if(usertype.equals("系统管理员")||usertype.equals("检测站人员")){
	operStyle = "block";
}
else{
	operStyle = "none";
}
%>
<div id="man_zone" style="padding-top:20px;">
  <table width='100%' border='0' align='center' cellpadding='1' cellspacing='1' bordercolor='#bdd7d6' bgcolor='#bdd7d6'  rules='none' style="font-size:12px; display:block;">
    <tr id='sub_title'>
      <th align="center"><font size="+1">车辆监控查询记录列表</font></th>
    </tr>
  </table>
  <div style="width:100%; overflow-x:scroll">
    <table width='101%' border='0' cellspacing='0' cellpadding='0'>
      <tr>
        <td bgcolor='#dedfd7'><table width="100%" border="0" cellpadding="0" cellspacing="1"  class="table_style">
          <tr   style="font-size:13px" >
           
            <td width="80px" align="center" class="left_title_1" style="display:<%=operStyle_cancal%>">操作</td>
            <%
		for(int jk=0; jk<col_name.length; jk++){
			%>
            <td width="10%" align="center" nowrap="nowrap" style="padding-left:2px; padding-right:2px;" class="left_title_1"><span class="left_title_2" ><%=col_headname[jk]%></span></td>
            <%
		}
		%>
          </tr>
          <tr   style="font-size:13px" >
            
            <td width="20%" align="center" class="left_title_1" style="display:<%=operStyle_cancal%>"></td>
            <%
		for(int jk=0; jk<col_name.length; jk++){
			%>
            <td width="10%" align="center" nowrap="nowrap" class="left_title_1"><span class="left_title_2" ><%=col_name[jk]%></span></td>
            <%
		}
		%>
          </tr>
          <%
	if(crs==null||crs.size()==0)
	{
%>
          <script>alert('没有数据记录');</script>
          <%
		for(int ik=1; ik<=15; ik++){
			%>
          <tr  bgcolor='FFFFFF' class='font_px12'>
            
            <%
			for(int jk=0; jk<col_name.length; jk++){
				%>
            <td align="center"  style="font-size:12px" nowrap="nowrap" >&nbsp;</td>
            <%
			}
			%>
          </tr>
          <%
		}
		%>
          <%
	}
	else
	{
		int i_row = 1;
		while(crs.next())
		{
			%>
          <tr bgcolor='FFFFFF' class='font_px12' id="tr_<%=i_row%>" onmouseover="javascript:this.style.background= '#ECF2FC';" onmouseout="javascript:this.style.background= '#FFFFFF';">
            <td align="center"  nowrap="nowrap" id="tr_<%=i_row%>_edit" style="display:<%=operStyle_cancal%>"><a href="MainServlet_sys?fid=FCL00.00.00.13&id=<%=crs.getString("id")%>" target="_self"><font color="#ff0000" class="tdrecord" style="font-size:12px" >变更</font></a> </td>
            <%
		String col_value = null;
		for(int jk=0; jk<col_name.length; jk++){
			col_value = Tools.nvl(crs.getString(col_name[jk]));
			if(col_name[jk].equals("CMonitorStatus")){
				if(col_value.equals("0")){
					col_value = "<div style='color:red;'>监控完毕</div>";
				}
				else if(col_value.equals("1")){
					col_value = "<div style='color:blue;'>监控</div>";
				}
			}
			else if(col_name[jk].equals("CReoportStatus")){	
				if(col_value.equals("0")){
					col_value = "<div style='color:red;'>未出报告</div>";
				}
				else if(col_value.equals("1")){
					col_value = "<div style='color:blue;'>已出报告</div>";
				}	
			}
			else if(col_name[jk].equals("Couttime") || col_name[jk].equals("Cjtime") || col_name[jk].equals("tp_testtime")|| col_name[jk].equals("dataCreateTime")){
				col_value = Tools.formatDateToString_hms(col_value);
			}
			%>
            <td align="center"  nowrap="nowrap" id="tr_<%=i_row%>_<%= col_name[jk]%>"><%= col_value%></td>
            <%
		}
		%>
          </tr>
          <%
		i_row++;
		}
		for(int ik=i_row; ik<=15; ik++){
			%>
          <tr  bgcolor='FFFFFF' class='font_px12'>
           
            <td align="center"  nowrap="nowrap" style="display:<%=operStyle%>"></td>
            <%
			for(int jk=0; jk<col_name.length; jk++){
				%>
            <td align="center"  style="font-size:12px" nowrap="nowrap" >&nbsp;</td>
            <%
			}
			%>
          </tr>
          <%
		}
	}
%>
        </table></td>
      </tr>
    </table>
  </div>
  <iframe id='submitframe' name='submitframe' width='100%' height='200' src='' frameborder='NONE' style='display:none'></iframe>
  <div id='queryConTable_id' style='display:block;'>
    <table width='100%' border='0' align='center' cellpadding='1' cellspacing='1' bordercolor='#bdd7d6' bgcolor='#bdd7d6'  rules='none' style="font-size:12px;">
      <tr id='sub_title'>
        <td width='55%' scope='col' align='left' id='listTitle' style="background-color:#F3F8F7;">数据列表显示区(全部记录)</td>
        <td width='13%' align='right' nowrap>&nbsp;&nbsp;合计<%=queryCon.getTotalNum()%>条</td>
        <td width='14%' align='right' nowrap>第<%=queryCon.getCurrentPageNum()%>页/共<%=queryCon.getMaxPageNum()%>页&nbsp;</td>
        <td width='18%' align='right' nowrap ><a href='MainServlet_sys?<%=queryCon.getMinPage()%>' 			target='_self' id='queryConTable_beginPage'>首页</a>&nbsp; <a href='MainServlet_sys?<%=queryCon.getPreviousPage()%>'       target='_self' id='queryConTable_lastPage'>前一页</a>&nbsp; <a href='MainServlet_sys?<%=queryCon.getNextPage()%>' 		    target='_self' id='queryConTable_nextPage'>后一页</a>&nbsp; <a href='MainServlet_sys?<%=queryCon.getMaxPage()%>' 			target='_self' id='queryConTable_endPage'>尾页</a>&nbsp; 
		<a title='输入页码请回车'>转<input type='text' name='CurPageNum' 	id='CurPageNum' value='<%=queryCon.getCurrentPageNum()%>' class='tex2' style='width:30px; height:15px; vertical-align:middle; padding:0px; font-size:12px' onKeyDown='lxl();'>页</a>
</td>
<script> 	
function lxl(){	
	if(event.keyCode==13){			
	var pageNum = document.getElementById('CurPageNum').value;	
		if(!judgeDigtal(pageNum)){	
			alert('非法页码!');	
			return;		
		}		
		if(pageNum < <%=queryCon.getMinPageNum()%> || pageNum><%=queryCon.getMaxPageNum()%>){		
			alert('页码出了范围!');	
			return;		
		}	
		location ='MainServlet_sys?<%=queryCon.getQueryString()%>&page='+pageNum ; 
	}		
}			
</script>
        <td width='0%' align='right'	style='display:none;height:15px;vertical-align:top'><input type='text' name='totalNum' 	style='height:18px;width:20px;font-size:12px'		id='totalNum' value='5' /></td>
        <td width='0%' align='right'	style='display:none;height:15px;vertical-align:top'><input type='text' name='CurLineNum' 	style='height:18px;width:20px;font-size:12px'	id='CurLineNum' value='' /></td>
        <td width='0%' align='right'	style='display:none;height:15px;vertical-align:top'><input type='text' name='CurLineCheck' style='height:18px;width:20px;font-size:12px'	id='CurLineNum' value='' /></td>
      </tr>
    </table>
  </div>
</div>
</body>
</html>
<%
}catch(Exception ex){
	ex.printStackTrace();
}
%>
