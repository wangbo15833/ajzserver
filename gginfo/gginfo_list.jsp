<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="com.zagerbone.util.Query"%>
<%@ page import="com.zagerbone.util.Tools"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ include  file='../identification.jsp' %> 
<%
try{
//System.out.println("hp_user=***="+hp_user.toString());
String listMode= Tools.nvl(request.getParameter("listMode")); 
//System.out.println("listMode==="+listMode);
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
  CachedRowSet crs_cols = (CachedRowSet)request.getAttribute("crs_cols");
  HashMap hp_pri = (HashMap)request.getAttribute("hp_pri");
  String mypri = (String)(hp_pri.get("mypri"));
  if(queryCon!=null)
  {
    crs = (CachedRowSet)queryCon.getConditionBody();
  }
%>
<%
	String titleName = "公告信息列表";
	ArrayList al_colname = new ArrayList();
	ArrayList al_colnamec = new ArrayList();
	ArrayList al_colomit = new ArrayList();
	while(crs_cols.next()){
		if(Tools.nvl(crs_cols.getString("list_disp")).equals("1")){	
			al_colname.add(crs_cols.getString("colname"));	
			al_colnamec.add(crs_cols.getString("colnamec"));
			al_colomit.add(crs_cols.getString("omit"));
		}
	}
%>
<head>
<title><%=titleName%></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="../css/001.css"  							type="text/css" >
<link rel="stylesheet"  href="../css/style1.css" type=text/css>
<link rel="stylesheet"  href="css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="js/tools.js"> </script>
<script language="JScript" type="text/javascript"  src="js/jq1-4-1.js"> </script>
<script language="JScript" type="text/javascript"  src="js/jquery.contextmenu.r2.packed.js"> </script>
<script> 
$(document).ready(function(){
	submitframe.location="_blank";
});
</script> 
<script> 
function confirmButton(operationMode, tr_row,id_value){
	var ok = false; 
/* 	if(operationMode== 'input' ){ 
		ok= window.confirm('您将要输入数据,\n'+'您确定吗?')	
	}	
	else if(operationMode== 'update' ){ 
		ok= window.confirm('您将要修改数据,\n'+'您确定吗?')	
	}	 
	else*/ 
	if(operationMode== 'delete' ){ 
		ok= window.confirm('您将要删除数据,\n'+'您确定吗?')	
	}	
	if(ok==false){
		return false;
	}

	submitframe.location = "MainServlet_sys?fid=UII00.08.01.02&id="+id_value;
	
}
</script> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="15">
<iframe name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
<div id="man_zone" >
	<TABLE class=table style="BORDER-COLLAPSE: collapse; " borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff  border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2" ><font size="+1"><%=titleName%></font></td>
	</tr>
</table>
<div style="width:100%; overflow-x:scroll; height:415px;">
<table width='100%' border='0' cellspacing='0' cellpadding='0'>
	<tr>
		<td bgcolor='#dedfd7'>
			<table border="0" cellspacing="1" cellpadding="0"  align="center" width="100%">
			<form name="frmList" target="submitframe" ></form>
				<tr style="width:100%;">
				<%
					for(int jk=0; jk<al_colname.size(); jk++){
						if (((String)al_colname.get(jk)).equals("id")){
						%><td style="width :40px" align="left" class="center_title_1" nowrap="nowrap">&nbsp;&nbsp;<%=(String)al_colnamec.get(jk)%></TD>
						<%
						}else{
						%><td align="left" class="center_title_1" nowrap="nowrap">&nbsp;&nbsp;<%=(String)al_colnamec.get(jk)%></TD>
						<%}
					}
				%>
				<%
				//通过公告信息进行身份判断 
				if(Integer.parseInt(mypri)>1){
				%>
				   <td align="center" width="18%" class="center_title_1" nowrap="nowrap">操&nbsp;&nbsp;作</td>
				   <%
				   }%>
				</tr>
				<%
				if(crs==null||crs.size()==0)
				{
				%>
				<tr align="center"> 
					<td height="15" colspan="<%=Integer.parseInt(mypri)>1?al_colname.size()+1:al_colname.size()%>"><p align="center" class="text2">未查询到公告信息</p></td>
				</tr>
				<%
				}
				else
				{
					int i_row = 1;
					while(crs.next())
					{
				%>
				<tr bgcolor='FFFFFF' class='font_px12_menu' id="tr_<%=crs.getString("id")%>" onMouseOver="javascript:this.style.background= '#ECF2FC';" onMouseOut="javascript:this.style.background= '#FFFFFF';">
					<%
					for(int jk=0; jk<al_colname.size(); jk++){
						%>
						<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_work_unit">&nbsp;&nbsp;<%=Tools.omit(crs.getString((String)al_colname.get(jk)),(String)al_colomit.get(jk))%></td>
						<%
					}
					%>
					<%
					//通过公告信息进行身份判断 
					if(Integer.parseInt(mypri)>1){
					%>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_edit2">
						<a href="#" onclick="show('MainServlet_sys?fid=UII00.08.01.05&id=<%=crs.getString("id")%>')"><font color="#053080">详细</font></a>
						<%if(Integer.parseInt(mypri)>2){%><a href="#" onclick="modify('MainServlet_sys?fid=UII00.08.01.04&id=<%=crs.getString("id")%>&mode=before')"><font color="#053080">修改</font></a>
						<a href="#" onclick="confirmButton('delete', 0, <%=crs.getString("id")%>);"><font color="#ff0000" class="tdrecord" style="font-size:12px" >删除</font></a>
						<%}%>
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
<div class="contextMenu" id="myMenu1" style="">
  <ul>
	<li id="see"><img src="./image2/icons/newspaper.png" /> 详细</li>
	<li id="edit"><img src="./image2/icons/highlighter.png" /> 修改</li>
	<li id="delete"><img src="./image2/icons/close.png" /> 删除</li>
  </ul>
</div>
<script>
$('.font_px12_menu').contextMenu('myMenu1', {
  bindings: {
	'see': function(t) {
	  //alert('Trigger was '+t.id+'\nAction was see');
	  show('MainServlet_sys?fid=UII00.08.01.05&id='+ t.id.split("_")[1])
	},
	'edit': function(t) {
	  //alert('Trigger was '+t.id+'\nAction was edit');
	  modify('MainServlet_sys?fid=UII00.08.01.04&mode=before&id='+ t.id.split("_")[1])
	},
	'save': function(t) {
	  alert('Trigger was '+t.id+'\nAction was Save');
	},
	'delete': function(t) {
	  //alert('Trigger was '+t.id+'\nAction was Delete');
	  confirmButton('delete', 0,  t.id.split("_")[1]);
	}
  }
});
</script>
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
function refrh(){
	location.reload();
	}
function modify(s)
  {
	  window.top.indexFrame.frames['manFrame'].location = s;

	}
function show(s)
  {
	  window.top.indexFrame.frames['manFrame'].location = s;

	}
</script>
<%
crs_cols.close();
crs.close();
System.out.println(queryCon.getQueryString());
}
catch (Exception exception4)
{
exception4.printStackTrace();
}

%>