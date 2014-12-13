<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ include  file='../identification.jsp' %> 
<%
String listMode= Tools.nvl(request.getParameter("listMode")); 
System.out.println("listMode=============================================="+listMode);
String operStyle 				= "none";
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
String [] col_name 	= {"id","tp_faultcode","tp_faultname","faultdegree","flgMonitor","qwbs","qwtime","qwtimeVal","ybbs","ybtime","ybtimeVal","zdbs","zdtime","zdtimeVal","add1","add1Val","add2","add3","add3Val","add4","add5"};
String [] col_headname 	= {"id","故障代码",    "故障名称",    "故障程度","监控标识", "轻微标识","轻微停机时长","轻微停机时长值","一般标识","一般停机时长","一般停机时长值","重大标识","重大停机时长","重大停机时长值","其它判断条件1","条件值1","其它判断条件2","其它判断条件3","条件值3","其它判断条件4","其它判断条件5"};
%>
<head>
<title>故障标准列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="../css/001.css"  type="text/css" >
<link rel="stylesheet"  href="../css/style1.css" type=text/css>
<link rel="stylesheet"  href="../css/calendar-win2k-cold-1.css"  type="text/css">
<link rel="stylesheet"  href="css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="js/tools.js"> </script>
<script>
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
	submitframe.location = "MainServlet_sys?fid=FCL00.00.05.05&id="+id_value;
	return ok;
}
</script> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0">
<div id="man_zone" style="padding-top:20px;">
<table width='100%' border='0' align='center' cellpadding='1' cellspacing='1' bordercolor='#bdd7d6' bgcolor='#bdd7d6'  rules='none' style="font-size:12px;">
	<tr id='sub_title'>
	  <td align="center"><font size="+1">故障标准列表</font></td>
	</tr>
</table>
<div style="width:100%; overflow-x:scroll">
<table width='101%' border='0' cellspacing='0' cellpadding='0'>
<tr>
<td bgcolor='#dedfd7'>
<table width="100%" border="0" cellpadding="0" cellspacing="1"  class="table_style">
	<tr   style="font-size:13px" >
	<td width="20%" align="center" class="left_title_1" style="display:<%=operStyle%>">操作</td>
	<td width="20%" align="center" class="left_title_1" style="display:<%=operStyle_cancal%>">操作</td>
	<%
	for(int jk=0; jk<col_name.length; jk++){
		%>
	  <td width="10%" align="center" nowrap="nowrap" style="padding-left:2px; padding-right:2px;" class="left_title_1"><span class="left_title_2" ><%=col_headname[jk]%></span></td>
	  <%
	}
	%>
	</tr>
	<tr   style="font-size:13px" ><!--  显示英文标题  -->
		<td width="20%" align="center" class="left_title_1" style="display:<%=operStyle%>"></td>
		<td width="20%" align="center" class="left_title_1" style="display:<%=operStyle_cancal%>"></td>
		<%
		for(int jk=0; jk<col_name.length; jk++){
		%>
		<td width="10%" align="center" nowrap="nowrap" style="padding-left:2px; padding-right:2px;" class="left_title_1"><span class="left_title_2" ><%=col_name[jk]%></span></td>
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
		%>
		<%
		}
		else
		{
		int i_row = 1;
		while(crs.next())
		{
		%>
     <tr  bgcolor='FFFFFF' class='font_px12' id="tr_<%=i_row%>">
		 <td align="center"  nowrap="nowrap" id="tr_<%=i_row%>_edit" style="display:<%=operStyle%>">
		<a href="MainServlet_sys?fid=FCL00.00.05.03&id=<%=crs.getString("id")%>" target="_self"><font color="#ff0000" class="tdrecord" style="font-size:12px" >修改</font></a>
		<a onclick="return confirmButton('delete', <%=i_row%>, <%=crs.getString("id")%>);" href="MainServlet_sys?fid=FCL00.00.05.05&id=<%=crs.getString("id")%>" target="submitframe"><font color="#ff0000" class="tdrecord" style="font-size:12px" >删除</font></a>
		</td>
		<td align="center"  nowrap="nowrap" id="tr_<%=i_row%>_edit" style="display:<%=operStyle_cancal%>">
		<a href="MainServlet_sys?fid=FCL00.00.00.13&id=<%=crs.getString("id")%>" target="_self"><font color="#ff0000" class="tdrecord" style="font-size:12px" >变更</font></a>
		</td>
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
			%>
		<td align="center"  style="font-size:12px" nowrap="nowrap" id="tr_<%=i_row%>_<%= col_name[jk]%>"><%= col_value%></td>
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
		 	<td align="center"  nowrap="nowrap" style="display:<%=operStyle_cancal%>"></td>
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
</table>
</td></tr></table>
</div>
<iframe id='submitframe' name='submitframe' width='100%' height='200' src='' frameborder='NONE' style='display:none'></iframe>
<div id='queryConTable_id' style='display:block;'>
<table width='100%' border='0' align='center' cellpadding='1' cellspacing='1' bordercolor='#bdd7d6' bgcolor='#bdd7d6'  rules='none' style="font-size:12px;">
<tr id='sub_title'>
<td width='55%' scope='col' align='left' id='listTitle' style="background-color:#F3F8F7;">数据列表显示区(全部记录)</td>
<td width='13%' align='right' nowrap>&nbsp;&nbsp;合计<%=queryCon.getTotalNum()%>条</td>
<td width='14%' align='right' nowrap>第<%=queryCon.getCurrentPageNum()%>页/共<%=queryCon.getMaxPageNum()%>页&nbsp;</td>
<td width='18%' align='right' nowrap >
	<a href='MainServlet_sys?<%=queryCon.getMinPage()%>' 		target='_self' id='queryConTable_beginPage'>首页</a>&nbsp;
	<a href='MainServlet_sys?<%=queryCon.getPreviousPage()%>'   target='_self' id='queryConTable_lastPage'>前一页</a>&nbsp;
	<a href='MainServlet_sys?<%=queryCon.getNextPage()%>' 		target='_self' id='queryConTable_nextPage'>后一页</a>&nbsp;
	<a href='MainServlet_sys?<%=queryCon.getMaxPage()%>' 		target='_self' id='queryConTable_endPage'>尾页</a>&nbsp;
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
<td width='0%' align='right'	style='display:none;height:15px;vertical-align:top'><input type='text' name='totalNum' 	style='height:18px;width:20px;font-size:12px'		id='totalNum' value='5' ></td>
<td width='0%' align='right'	style='display:none;height:15px;vertical-align:top'><input type='text' name='CurLineNum' 	style='height:18px;width:20px;font-size:12px'	id='CurLineNum' value=''></td>
<td width='0%' align='right'	style='display:none;height:15px;vertical-align:top'><input type='text' name='CurLineCheck' style='height:18px;width:20px;font-size:12px'	id='CurLineNum' value='' 	></td>
</tr>
</table>
</div>
</div>
</body>
</html>