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
			//alert("_<%= col_name[jk]%>");
			if(document.getElementById("tr_"+tr_row+"_<%= col_name[jk]%>") != undefined){
				//alert(document.getElementById("tr_"+tr_row+"_<%= col_name[jk]%>").innerHTML);
				document.getElementById("tr_"+tr_row+"_<%= col_name[jk]%>").innerHTML="";
			}
			<%
		}
	%>
	//alert(document.getElementById("tr_1_id").innerHTML);
	//document.getElementById("tr_"+tr_row+"_edit").innerHTML="&nbsp;";
	document.getElementById("tr_"+tr_row+"_edit2").innerHTML="&nbsp;";
	submitframe.location = "MainServlet_sys?fid=F00.00.00.06&userid="+id_value;
	return ok;
	//alert(document.getElementById("tr_9_flag").innerHTML);
}
</script> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="30">
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
<div id="man_zone" style="padding-top:30px;">
<table width='100%' border='0' align='center' cellpadding='1' cellspacing='1' bordercolor='#bdd7d6' bgcolor='#bdd7d6'  rules='none' style="font-size:12px;">
	<tr id='sub_title'><td align="center"><font size="+1">用户信息列表</font></td></tr>
</table>
<table width='101%' border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td bgcolor='#dedfd7'>
			<table border="0" cellspacing="1" cellpadding="0"  align="center" width="100%">
			<form name="frmList" target="submitframe"></form>
				<tr>
					<td align="left" width="10%" class="center_title_1" nowrap="nowrap">&nbsp;&nbsp;工作单位</TD>
					<td align="left" width="10%" class="center_title_1" nowrap="nowrap">&nbsp;&nbsp;用户名</TD>
					<td align="left" width="10%" class="center_title_1" nowrap="nowrap">&nbsp;&nbsp;真实姓名</TD>
					<td align="center" width="10%" class="center_title_1" nowrap="nowrap">身份类型</TD>
					<td align="center" width="24%" class="center_title_1" nowrap="nowrap">电子信箱</td>
					<td align="center" width="10%" class="center_title_1" nowrap="nowrap">用户状态</td>
					<td align="center" width="15%" class="center_title_1" nowrap="nowrap">注册日期</td>
				   <%
				//通过角色进行身份判断 
				if(usertype.equals("系统管理员")||usertype.equals("检测站人员")){
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
					<td height="2" align="left" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_work_unit">&nbsp;&nbsp;<%=Tools.nvl(crs.getString("work_unit"))%></td>
					<td height="2" align="left" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_username">&nbsp;&nbsp;<%=Tools.nvl(crs.getString("username"))%></td>
					<td height="2" align="left" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_realname">&nbsp;&nbsp;<%=Tools.nvl(crs.getString("realname"))%></td>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_usertype"><%=Tools.nvl(crs.getString("usertype"))%></td>
					<td height="2" align="left" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_email"><%=Tools.nvl(crs.getString("email"))%></td>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_flag"><%=Tools.nvl(crs.getString("flag"))%></td>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_regdate"><%=Tools.nvl(crs.getString("regdate")).substring(0,10)%></td>
					<%
					//通过角色进行身份判断 
					if(usertype.equals("系统管理员")||usertype.equals("检测站人员")){
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
<table height="10"><tr><td>&nbsp;</td></tr></table>
<table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" >
	<tr> 
		<td width="20%" align="right" class="text2">&nbsp;&nbsp;合计<%=queryCon.getTotalNum()%>条</td>
		<td width="30%" align="center" class="text2">第<%=queryCon.getCurrentPageNum()%>页/共<%=queryCon.getMaxPageNum()%>页&nbsp; </td>
		<td width="50%" align="center" nowrap class="text2">              
			<a href="MainServlet_sys?<%=queryCon.getMinPage()%>" target="_self">首页</a>&nbsp;
			<a href="MainServlet_sys?<%=queryCon.getPreviousPage()%>" target="_self">前一页</a>&nbsp;
			<a href="MainServlet_sys?<%=queryCon.getNextPage()%>" target="_self">后一页</a>&nbsp;
			<a href="MainServlet_sys?<%=queryCon.getMaxPage()%>" target="_self">尾页</a>&nbsp; 
		</td>
		<td width="10%">&nbsp;</td>
	</tr>
</table>
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