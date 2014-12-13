<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="com.zagerbone.util.Query"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>
<%@ include  file='../identification.jsp' %> 
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="js/jquery.min.js"> </script>
<style>
#drag_window{position:absolute; height:300px; width:500px; top:150px; background:#fff; border:1px solid #ccc; font-size:12px; box-shadow:2px 2px 3px #999; -moz-user-select:none;z-index:-10; }
#drag_window_title{background:#eee; border-bottom:1px solid #ccc; line-height:24px;text-align:center;}
#drag_window_title h4{ margin:0px; padding:0px;font-size:20px;}
#drag_window_main{padding:4px;overflow:scroll-y;}
#drag_window_qry{text-align:center;}
</style>
</head>


<%
String titleName="详细信息";
try{
	  EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
	  Query queryCon = (Query)eventResponse.getBody(); 
	  CachedRowSet crs = null;
	  if(queryCon!=null)
	  {
		crs = (CachedRowSet)queryCon.getConditionBody();
		crs.next();
	  }
	CachedRowSet crs_cols = (CachedRowSet)request.getAttribute("crs_cols");
	ArrayList al_colname = new ArrayList();
	ArrayList al_colnamec = new ArrayList();
	ArrayList al_datasrc = new ArrayList();
	ArrayList al_datasrctype = new ArrayList();
	HashMap hp_pri = (HashMap)(request.getAttribute("hp_pri"));
	String filter_str = Tools.nvl((String)(hp_pri.get("filter_str")));
	String mypri = Tools.nvl((String)(hp_pri.get("mypri")));
	String red = (String)(request.getAttribute("red"));
	String nored = (String)(request.getAttribute("nored"));
	
	while(crs_cols.next()){
		if(Tools.nvl(crs_cols.getString("deta_disp")).equals("1")){	
			al_colname.add((String)crs_cols.getString("colname"));
			al_colnamec.add((String)crs_cols.getString("colnamec"));
			al_datasrc.add((String)crs_cols.getString("datasrc"));
			al_datasrctype.add((String)crs_cols.getString("datasrctype"));
		}
	}
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="self" onKeyDown="if(event.keyCode==13)event.keyCode=9"  >
<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
		<%
	int ik = 0;
	while(ik<al_colname.size()){
			if(ik%2==0){
		%>
	<tr><%}
		if(!((String)al_datasrctype.get(ik)).equals("textarea")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td id="<%=(String)al_colname.get(ik)%>" width="41%"><%=Tools.nvl(crs.getString(al_colname.get(ik).toString()))%></td>
		<%}else{
		if(ik%2==1){
		%><td  width="9%"></td><td  width="41%"></td></tr><%
		}
		%><td height="20"  width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td  width="91%" colspan="3" ><%=Tools.nvl(crs.getString(al_colname.get(ik).toString())).replaceAll("%40","</br>")%></td> 
		<%}
		%><%
		ik++;
		if(ik%2==0){
		%>
	</tr><%}
		}
		if(ik%2==1){
		%><td height="20" width="9%" align="right" class="text2">&nbsp;&nbsp;</td>
		<td width="41%">&nbsp;&nbsp;</td>
	</tr>		
		<%
		}
		%>
  <tr>
  	<td align="center" colspan="4">
		<input type="button" name="button" value="返 回" style="width:80;height:23 " onclick="javascript:doReturn();">
	</td>
  </tr>
</table>
</form>
</div>

     <div id="drag_window">
    	<div id="drag_window_title" >
        	<h4>消息阅读状态</h4>
        </div>
        <div id="drag_window_main">
        	<ul>
            	<li> </li>
            </ul>
        </div>
    </div>
</body>

</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
</iframe> 
<script>
$(document).ready(function(){
	$("#rec_name").html("<div id='sw' style='background:#ffddee;'>点此查看阅读情况</div>");
});
</script>
<script>
$(function()
{
	drag("drag_window_title","drag_window");
});
/* 拖拽 */
function drag(dragDiv,tagDiv){		//句柄，对象
	
	var e,offsetT,offsetL,downX,downY; 
	$("#"+dragDiv).css('cursor','move');		
	$("#"+dragDiv).mousedown(function(e)//当鼠标按下的时候
	{
		$('#'+tagDiv).bind('selectstart',function(){return false;});
		e = e||window.event;
		offsetT = $($('#'+tagDiv)).get(0).offsetTop;//相对TOP
		offsetL =$($('#'+tagDiv)).get(0).offsetLeft;//相对LEFT
		downX = e.clientX;//按下鼠标的时候的TOP
		downY = e.clientY;//按下鼠标时候的LEFT
		$(document).mousemove (function(e){//鼠标移动的时候
			e = e||window.event;
			$('#'+tagDiv).css('top',offsetT + (e.clientY - downY) + "px");
			$('#'+tagDiv).css('left',offsetL + (e.clientX - downX) + "px");
		});
	}).mouseup(function(e){//当鼠标松开的时候
			$('#'+tagDiv).unbind('selectstart');
			$(document).unbind('mousemove');
		});	
}
</script>
<script>
function showlist(){
	try{
		if($("#drag_window").css("z-index")!="9" && $("#drag_window").css("z-index")!="-9"){
			var mw = $("#drag_window")[0].clientWidth;
			var mh = $("#drag_window")[0].clientHeight;
			var dh = document.body.clientHeight;
			var dw = document.body.clientWidth;
			var dl = $("#man_zone")[0].scrollLeft;
			var dt = $("#man_zone")[0].scrollTop;
			$("#drag_window").css("top",(dh-dt-mh)/2);
			$("#drag_window").css("left",(dw-dl-mw)/2);
			$("#drag_window").css("z-index",9);
		}else{
			$("#drag_window").css("z-index",$("#drag_window").css("z-index")=="9"?"-9":"9");
		}
	}catch(e){
		alert(e.description );
	}
}
</script>
<script>
//禁止冒泡 点击其它地方弹窗消失等
$(document).ready(function(){
	$("#sw").click(function(){showlist();return false;});
	$(document).click(function(){$("#drag_window").css("z-index",-9);});
	$("#drag_window").click(function(){return false;});
});
</script>
<script>
	var allName="<%=crs.getString("rec_name")%>";
	var red="<%=red%>";
	var nored="<%=nored%>";

	var redlist="";
	var noredlist="";
	var dellist="";
	var ai = new Array();
	ai =allName.split(";");
	for(i=0 ; i<ai.length ; i++){
		ai[i]=ai[i].split(",")[1];
	}
	for(i=0 ; i<ai.length ; i++){
	// for(str:ai){
		if (red.indexOf(ai[i])>-1){
			redlist=redlist+ai[i]+";";
		}else if (nored.indexOf(ai[i])>-1){
			noredlist=noredlist+ai[i]+";";
		}else{
			dellist=dellist+ai[i]+";";
		}
	}
		var cont ="";
		if (redlist!="")
		{
			cont = "<font color='#337733'>"+redlist+"  --已   读</font>"+"</br>";
		}
		if (noredlist!="")
		{
			cont=cont+"<font color='#773333'>"+noredlist+"  --未   读</font>"+"</br>";
		}
		if (dellist!="")
		{
			cont=cont+"<font color='#333377'>"+dellist+"  --已读并删除</font>"+"";
		}
		//alert(cont);
		$("#drag_window_main").html(cont);
		$("#drag_window_main").css({"overflow-y":"scroll","height":"240px"});
</script>
<script>
  function doReturn()
  {

	window.top.indexFrame.frames['manFrame'].location = './MainServlet_sys?fid=UII00.07.01.01<%=Integer.parseInt(mypri)<=3?filter_str:""%>'
  }

</script>

<%
crs.close();
crs_cols.close();
}catch (Exception exception)
{
exception.printStackTrace();
}
%>