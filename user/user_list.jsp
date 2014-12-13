<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="com.zagerbone.util.Query"%>
<%@ page import="java.util.HashMap"%>
<%@ include  file='../identification.jsp' %> 
<%
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
  Query queryCon = (Query)eventResponse.getBody(); 
  CachedRowSet crs = null;
  if(queryCon!=null)
  {
    crs = (CachedRowSet)queryCon.getConditionBody();
  }
%>
<%
String [] col_name 	= {"id","userid","username","password","work_unit","work_unit_name","realname","usertype","email","flag","regdate","confirmdate","confirmpeople","lastdate","logintimes","lastip","remark","userroles","worker_code","userrole_codes","col_order"};
String [] col_headname 	= {"id","userid","username","password","work_unit","work_unit_name","realname","usertype","email","flag","regdate","confirmdate","confirmpeople","lastdate","logintimes","lastip","remark","userroles","worker_code","userrole_codes","col_order"};
%>
<head>
<title>用户列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="../css/001.css"  							type="text/css" >
<link rel="stylesheet"  href="../css/style1.css" type=text/css>
<link rel="stylesheet"  href="../css/calendar-win2k-cold-1.css"  	type="text/css">
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
			%>
			if(document.getElementById("tr_"+tr_row+"_<%= col_name[jk]%>") != undefined){
				document.getElementById("tr_"+tr_row+"_<%= col_name[jk]%>").innerHTML="";
			}
			<%
		}
	%>
	document.getElementById("tr_"+tr_row+"_edit2").innerHTML="&nbsp;";
	submitframe.location = "MainServlet_sys?fid=F00.00.00.06&userid="+id_value;
	return ok;
}
</script> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="30">
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
<div id="man_zone" style="padding-top:30px; height:1000px;">
<table width='100%' border='0' align='center' cellpadding='1' cellspacing='1' bordercolor='#bdd7d6' bgcolor='#bdd7d6'  rules='none' style="font-size:12px;">
	<tr id='sub_title'><td align="center"><font size="+1">用户信息列表</font></td></tr>
</table>
<div style="width:100%; overflow-x:scroll; height:400px;">
<table width='101%' border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td bgcolor='#dedfd7'>
			<table border="0" cellspacing="1" cellpadding="0"  align="center" width="100%">
			<form name="frmList" target="submitframe"></form>
				<tr>
					<td align="left" width="10%" class="center_title_1" nowrap="nowrap">&nbsp;&nbsp;车辆单位</TD>
					<td align="left" width="10%" class="center_title_1" nowrap="nowrap">&nbsp;&nbsp;用户名</TD>
					<td align="left" width="10%" class="center_title_1" nowrap="nowrap">&nbsp;&nbsp;真实姓名</TD>
					<td align="center" width="10%" class="center_title_1" nowrap="nowrap">身份类型</TD>
					<td align="center" width="24%" class="center_title_1" nowrap="nowrap">电子信箱</td>
					<td align="center" width="10%" class="center_title_1" nowrap="nowrap">用户状态</td>
					<td align="center" width="15%" class="center_title_1" nowrap="nowrap">注册日期</td>
				   <%
				//通过角色进行身份判断 
				if(usertype.equals("系统管理员")){//||usertype.equals("检测站人员")
				%>
				   <td align="center" width="21%" class="center_title_1" nowrap="nowrap">操&nbsp;&nbsp;作</td>
				   <%
				   }%>
				</tr>
				<%
				if(crs==null||crs.size()==0)
				{
				%>
				<tr align="center"> 
					<td height="15" colspan="8"><p align="center" class="text2">未查询到用户</td>
				</tr>
				<%
				}
				else
				{
					int i_row = 1;
					while(crs.next())
					{
				%>
				<tr bgcolor='FFFFFF' class='font_px12' id="tr_<%=i_row%>" onMouseOver="javascript:this.style.background= '#ECF2FC';" onMouseOut="javascript:this.style.background= '#FFFFFF';">
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_work_unit">&nbsp;&nbsp;<%=Tools.nvl(crs.getString("work_unit"))%></td>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_username">&nbsp;&nbsp;<%=Tools.nvl(crs.getString("username"))%></td>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_realname">&nbsp;&nbsp;<%=Tools.nvl(crs.getString("realname"))%></td>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_usertype"><%=Tools.nvl(crs.getString("usertype"))%></td>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_email"><%=Tools.nvl(crs.getString("email"))%></td>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_flag"><%=Tools.nvl(crs.getString("flag"))%></td>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_regdate"><%=Tools.nvl(crs.getString("regdate")).substring(0,10)%></td>
					<%
					//通过角色进行身份判断 
					if(usertype.equals("系统管理员")){//||usertype.equals("检测站人员")
					%>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_edit2">
						<a href="MainServlet_sys?fid=F00.00.00.11&userid=<%=crs.getString("userid")%>" target="submitframe"><font color="#053080">审批</font></a>
						<a onclick="return confirmButton('delete', <%=i_row%>, <%=crs.getString("userid")%>);" target="submitframe"><font color="#ff0000" class="tdrecord" style="font-size:12px" >删除</font></a>
					</td>
					<%
					}
					%>
				</tr>
					<%
						i_row++;
						}
					}
					%>
			</table>
		</td>
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
		<td width='18%' align='right' nowrap >
		<a href='MainServlet_sys?<%=queryCon.getMinPage()%>' 			target='_self' id='queryConTable_beginPage'>首页</a>&nbsp;
		<a href='MainServlet_sys?<%=queryCon.getPreviousPage()%>' target='_self' id='queryConTable_lastPage'>前一页</a>&nbsp;
		<a href='MainServlet_sys?<%=queryCon.getNextPage()%>' 		target='_self' id='queryConTable_nextPage'>后一页</a>&nbsp;
		<a href='MainServlet_sys?<%=queryCon.getMaxPage()%>' 			target='_self' id='queryConTable_endPage'>尾页</a>&nbsp;
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
	</tr>
</table>
</div>
</div>
</body>
</html>
<script language="javascript">
function  fuquan(userid,username)
{
 	var theUrl ="../user/user_role.jsp?userid="+userid+"&username="+username;
    var style="status:no;help:no;dialogWidth:40;dialogHeight:25;dialogTop:150;dialogLeft:200";
    theValue=window.showModalDialog(theUrl,null,style);
	if(theValue==null){
	  theValue="";
	}
	if(theValue.length==0||theValue=="undefined"){
	}
	else{
	  location = location;
	}
}
</script>