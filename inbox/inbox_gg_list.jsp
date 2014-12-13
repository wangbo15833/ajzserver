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

<%@ page import ="java.util.Iterator"%>
<%@ page import ="java.util.Map.Entry"%>
<%@ page import ="java.util.Set"%>

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
  String msgtype = (String)request.getAttribute("msgtype");
  String titlecolor = "#ccccff";
  String titleName = "消息列表";
  if(msgtype.equals("通知")){
	titlecolor = "#ccccff";
	titleName = "通知列表";
  }else if(msgtype.equals("提醒")){
	titlecolor = "#ffcc99";
	titleName = "提醒列表";
  }else if(msgtype.equals("公告")){
	titlecolor = "#ccff99";
	titleName = "公告列表";
  }
  if(queryCon!=null)
  {
    crs = (CachedRowSet)queryCon.getConditionBody();
  }
%>
<%
	
	ArrayList al_colname = new ArrayList();
	ArrayList al_colnamec = new ArrayList();
	ArrayList al_colomit = new ArrayList();
	
	Map<String,String> mp_tr = new HashMap<String,String>();
	Map<String,String> mp_td = new HashMap<String,String>();
	String sta_seg = "isNew";
	
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
<link rel="stylesheet"  href="css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="js/tools.js"> </script>
<script language="JScript" type="text/javascript"  src="js/jq1-4-1.js"> </script>
<script language="JScript" type="text/javascript"  src="js/jquery.contextmenu.r2.packed.js"> </script>
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

	submitframe.location = "MainServlet_sys?fid=UII01.01.02.02&id="+id_value;
	
}
</script> 
</head>
<body leftmargin="0" topmargin="0" rightmargin="0" bottommargin="15">
<iframe name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
<div id="man_zone" >
	<TABLE class=table style="BORDER-COLLAPSE: collapse; " borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff  border=1>
	<tr>
		<td bgColor="<%=titlecolor%>" height=25 colspan="4" align="center" class="text2" ><font size="+1"><%=titleName%></font></td>
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
				//通过消息进行身份判断 
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
					<td height="15" colspan="<%=Integer.parseInt(mypri)>1?al_colname.size()+1:al_colname.size()%>"><p align="center" class="text2">未查询到消息</p></td>
				</tr>
				<%
				}
				else
				{
					int i_row = 1;
					while(crs.next()){
					//---------------------------
					mp_tr.put("tr_"+Tools.nvl(crs.getString("id")),Tools.nvl(crs.getString(sta_seg)));
					mp_td.put("tr_"+Tools.nvl(crs.getString("id"))+"_"+sta_seg,Tools.nvl(crs.getString(sta_seg)));
					//---------------------------
				%>
				<tr bgcolor='FFFFFF' class='font_px12_menu' id="tr_<%=Tools.nvl(crs.getString("id"))%>" onMouseOver="javascript:this.style.background= '#ECF2FC';" onMouseOut="javascript:this.style.background= '#FFFFFF';">
					<%
					for(int jk=0; jk<al_colname.size(); jk++){
						%>
						<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=Tools.nvl(crs.getString("id"))%>_<%=(String)(al_colname.get(jk))%>">&nbsp;<%System.out.println(al_colname.get(jk));%>&nbsp;<%=Tools.omit(crs.getString((String)al_colname.get(jk)),(String)al_colomit.get(jk))%></td>
						<%
					}
					%>
					<%
					//通过消息进行身份判断 
					if(Integer.parseInt(mypri)>1){
					%>
					<td height="2" align="center" class="text3" nowrap="nowrap" id="tr_<%=i_row%>_edit2">
						<!--a href="#" onclick="show('MainServlet_sys?fid=UII01.01.02.05&msgtype=<%=msgtype%>&id=<%=crs.getString("id")%>')"><font color="#053080">详细</font></a-->
						<%if(Integer.parseInt(mypri)>2){%><a href="#" onclick="modify('MainServlet_sys?fid=UII01.01.02.08&msgtype=<%=msgtype%>&id=<%=crs.getString("id")%>&mode=before')"><font color="#053080">查看</font></a>
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
	<li id="see"><img src="./image2/icons/newspaper.png" /> 查看</li>
  </ul>
</div>


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

function mp(){
<%for (String key : mp_tr.keySet()){
	if((mp_tr.get(key)).equals("是")){
%>	if (document.getElementById('<%=key%>')!=null){		
		try{
		document.getElementById('<%=key%>').style.background= '#ffbbbb';
		document.getElementById('<%=key%>').style.font= 'bold';
		}catch(err)
		{}
		document.getElementById('<%=key%>').onmouseout=function (){this.style.background= '#ffbbbb';};
		document.getElementById('<%=key%>').onmouseover=function(){this.style.background= '#ffaaaa';};
	}
	<%}}%>
}
mp();
</script>
<script>

$('.font_px12_menu').contextMenu('myMenu1', {
  bindings: {
	'see': function(t) {
	  //alert('Trigger was '+t.id+'\nAction was see');
	  modify('MainServlet_sys?fid=UII01.01.02.08&id='+t.id.split("_")[1]+'&mode=before')
	}
  }
});
</script>

</html>

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