<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><%@ 
page contentType="text/html; charset=gb2312"
import="com.zagerbone.user.User"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="java.text.*"%><%@ include  file='../identification.jsp'  
%><head>
<title>新增投诉意见</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="js/jquery.min.js"> </script>
<script type="text/javascript" src="./js/map.js"></script>
 <link rel="stylesheet" href="js/jquery-ui.css">
<script src="js/jquery-ui-src.js"></script>
<script language="JScript" type="text/javascript"  src="js/jquery.ui.datepicker-zh-CN.js"> </script>
 <style>
#drag_window{position:absolute; height:300px; width:500px; top:150px; background:#fff; border:1px solid #ccc; font-size:12px; box-shadow:2px 2px 3px #999; -moz-user-select:none;z-index:-10; }
#drag_window_title{background:#eee; border-bottom:1px solid #ccc; line-height:24px;text-align:center;}
#drag_window_title h4{ margin:0px; padding:0px;font-size:20px;}
#drag_window_main{padding:4px;overflow:scroll-y;}
#drag_window_qry{text-align:center;}
</style>
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
	$("#rec_named").click(function(){showlist();return false;});
	$(document).click(function(){$("#drag_window").css("z-index",-9);});
	$("#drag_window").click(function(){return false;});
});
</script>
<script>
//--------------------------------------
var xmlhttp;
var json;
function loadXMLDoc(url,cfunc){
	if (window.XMLHttpRequest){
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	}else{// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=cfunc;
	xmlhttp.open("post",url,true);
	xmlhttp.send();
}
function myFunction(dept,realname){
// alert("in my functrion 1 ");
	var mode="getcontact";
	var reqStr="./ajax/getcontact.jsp?_="+Math.round(Math.random()*100000)+"&mode="+mode+"&dept="+encodeURI(dept)+"&realname="+encodeURI(realname);
	//alert(reqStr);
	loadXMLDoc(reqStr,function(){
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var str = (xmlhttp.responseText);
			var inStr = "";
			str=(str).replace(/[\r|\n]/gm,'');
			json = eval("("+str+")");
			var tempdept;
			var temprealname;
			var tempaccount;
			for ( i=0;i<json.length;i++){
				tempdept = json[i].dept;
				temprealname = json[i].realname;
				tempaccount = json[i].account;
				tempdept = tempdept.replace(new RegExp(dept,"gm"),"<font color='orange'>"+dept+"</font>");
				temprealname = temprealname.replace(new RegExp(realname,"gm"),"<font color='orange'>"+realname+"</font>");
				inStr=inStr +"<li><a onclick=\"filltab('"+ json[i].dept+"','"+ json[i].realname+"','"+ json[i].account+"')\">"+tempdept+"  :  "+temprealname+"</li>\n";
			}
			inStr="<ul>\n"+inStr+"\n</ul>";
			 $("#drag_window_main").html(inStr);
			 $("#drag_window_main").css({"overflow-y":"scroll","height":"240px"});
		}
	  });
}
</script>
<script>
$(document).ready(function(){
	$("#confirmbtn").click(function(){
		myFunction($.trim($("#drag_con_dept").val()),$.trim($("#drag_con_name").val()));
	});
	$("#rec_name").val("");
	$("#send").attr("disabled",true);
});
</script>
<script>
function filltab(dept,realname,account){
	var rec_named="";
	var rec_name = $("#rec_name").val();
	var rec_nameAr = new Array();
	var rec_nameArS = new Array();
	var isContain = false;
	rec_nameAr = rec_name.split(";");
	for (var i = 0 ; i < rec_nameAr.length ; i++){
		if (rec_nameAr[i].indexOf(account)>-1){
			isContain = true;
		}else if(rec_nameAr[i]!=""){
			rec_nameArS.push(rec_nameAr[i]);
		}
	}
	if(!isContain){
		rec_nameArS.push(dept+","+realname+","+account);
	}
	rec_name=rec_nameArS.join(";");
	$("#rec_name").val(rec_name);
	
	for (var i = 0 ; i < rec_nameArS.length ; i++){
		rec_named = rec_named+rec_nameArS[i].split(",")[1]+";";
	}
	$("#rec_named").html(rec_named);
	if($("#rec_name").val()==""){
		$("#send").attr("disabled",true);
	}else{
		$("#send").attr("disabled",false);
	}
}
</script>
</head>
<%
try{
	String flag1 =(String)request.getAttribute("flag1"); 
	HashMap hp_pri = (HashMap)(request.getAttribute("hp_pri"));
	System.out.println(hp_pri.toString());
	String filter_str = Tools.nvl((String)(hp_pri.get("filter_str")));
	String mypri = Tools.nvl((String)(hp_pri.get("mypri")));
	CachedRowSet crs_user = (CachedRowSet)hp_pri.get("crs_user");
  if (flag1==null || flag1.equals(""))
  {   
		flag1 ="false" ;
		User user = new User();
		user = (User)session.getAttribute("curUser");
		String titleName = "新增投诉意见";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		CachedRowSet crs_cols = (CachedRowSet)request.getAttribute("crs_cols");
		ArrayList al_colname = new ArrayList();
		ArrayList al_colnamec = new ArrayList();
		ArrayList al_datasrc = new ArrayList();
		ArrayList al_datasrctype = new ArrayList();
		while(crs_cols.next()){
			if(Tools.nvl(crs_cols.getString("new_disp")).equals("1")){	
				al_colname.add((String)crs_cols.getString("colname"));
				al_colnamec.add((String)crs_cols.getString("colnamec"));
				al_datasrc.add((String)crs_cols.getString("datasrc"));
				al_datasrctype.add((String)crs_cols.getString("datasrctype"));
			}
		}
%>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="sframe" method="post" onKeyDown="if(event.keyCode==13)event.keyCode=9" >
    <input type="hidden" name="fid" value="UII00.11.01.03">
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<%
	int ik = 0;
	int jk = 0 ;
	while(ik<al_colname.size()){
			if(jk%2==0){
		%>
	<tr><%}%>		
		<%if(((String)al_datasrctype.get(ik)).equals("")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" name="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" size="25"<%if (al_colname.get(ik).toString().equals("id")){%> disabled="disabled" style="background-color:#CCC" value="由系统生成"<%}else{%>value=""<%}%>/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("popList")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><select id="<%=al_colname.get(ik).toString()%>"  name="<%=al_colname.get(ik).toString()%>" style="width:190px;">
			<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
			<%=com.zagerbone.util.Tools.popListWithSelect((String)al_datasrc.get(ik),"")%>
			</select></td>
		<%}else if(((String)al_datasrctype.get(ik)).equals("check")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" onblur="myFunction(this.value)" size="25" value=""/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("getmap")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" ondblclick="showmap()" size="25" value=""/>双击 打开/隐藏 地图</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("calendar")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" 
		class="datepicker"	id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25" value=""/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("textarea")){
		if(jk%2==1){
		%><td  width="9%"></td><td  width="41%"></td></tr><%
		}
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="91%" colspan="3"><textarea cols="100%" rows="10" id="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25" value=""></textarea>&nbsp;&nbsp;</td> 
		<%jk=1;}else if(((String)al_datasrctype.get(ik)).equals("moreline")){
		if(jk%2==1){
		%><td  width="9%"></td><td  width="41%"></td></tr><%
		}		
		%><td height="60" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td colspan="3" width="91%" >单击添加收件人:<div id="rec_named" style="padding: 3 3 3 3 ;background-color:#ddddff;height:54px;width:96%" ></div></td><input type="hidden" id="rec_name" name="rec_name" value=""/>
		<%jk=1;
		}
		jk++;
		ik++;
		if(jk%2==0){
		%>
	</tr><%}
		}
		if(jk%2==1){
		%><td height="20" width="9%" align="right" class="text2">&nbsp;&nbsp;</td>
		<td width="41%">&nbsp;&nbsp;</td>
	</tr>		
		<%
		}
		%>
  <tr>
  	<td align="center" colspan="4">
		<input id="send" type="button" name="button" value="确 定" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" name="button" value="重 填" style="width:80;height:23 ">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="返 回" style="width:80;height:23 " onClick="javascript:history.go(-1);">
	</td>
  </tr>
</table>
</form>

</div>
<div id="rec_namebook" style="position:absolute;background-color:#eeeeff;height:200px;width:600px;z-index: 10; border:2px solid #ccc ;display:none;overflow:scroll;">
<%	String preu = "";
	String curu = "";
	boolean newd = false;
	while(crs_user.next()){
		curu=crs_user.getString("work_unit");
		String realname =crs_user.getString("realname");
		String theid =crs_user.getString("id");
		if(!curu.equals(preu)){
		
		newd = true;
		}
		if(newd && !preu.equals("")){
		%></div><%}
		if (newd){
%>
<div style="clear:left;">
		<div style="color:#000; font:bold;"><%=curu%>:</div>
		<%
		}
%>		<div style="height:auto;  width:100%;">
		<div style="padding:5 3 5 3px;float:left;  border-bottom:2px dotted #00C;width:56px; height:20px;">
        <div style="font-size:14px; width:50px; color:#666; background-color:#ddddff;" id ="r<%=theid%>" onclick="selrec(this.id);"><%=realname%></div>
		</div><%
  newd = false;
  preu =curu;
%>
</div>
<%}
 %>
</div>
</div>
</body>
     <div id="drag_window">
    	<div id="drag_window_title" >
        	<h4>请从列表中选择收件人</h4>
        </div>
		<div id="drag_window_qry">单位：
		<input id="drag_con_dept" />姓名：
		<input id="drag_con_name" />
		<input type="button" id="confirmbtn" value="查询" />
		</div >
        <div id="drag_window_main">
        	<ul>
            	<li>请输入过滤字符</li>
            </ul>
        </div>
    </div>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:block"></iframe>
</iframe> 
<script language=javascript>
  function doSave()
  {
  if(document.getElementById("cont")!=null){
	s=document.getElementById("cont").innerHTML;
	s=s.replace(/[\r\n]/gm,"%40");}
    formHead.target = "submitframe";
	formHead.submit();
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
  }
</script>
<script>
  function checkfilter()
  {
	<%if(Integer.parseInt(mypri)>=3){%>
		<%
		Map<String,String> mp_filter = new HashMap<String,String>();
		String ai[] = filter_str.split("&");
		for(int j = 1 ; j<ai.length ;j++){
			String bi[] = ai[j].split("="); 
			mp_filter.put(bi[0],bi[1]);
		}
		
		for (String key : mp_filter.keySet()){
		
		%>if (document.getElementById('<%=key%>')!=null){
			document.getElementById('<%=key%>').value='<%=mp_filter.get(key)%>';
			document.getElementById('<%=key%>').readOnly=true;
			document.getElementById('<%=key%>').style.color="#aaa";
		}<%
		}
		}
		%>
  
  }
  checkfilter();
</script>
<%
crs_cols.close();
}else{%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("增加成功！");
		window.top.indexFrame.frames['manFrame'].location = 'MainServlet_sys?fid=UII00.11.01.01<%=Integer.parseInt(mypri)==4?"":filter_str%>';
		}
	}
	checknow();
</script>

<%}
}catch(Exception ex){
	ex.printStackTrace();
}
%>


