<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><%@ 
page contentType="text/html; charset=gb2312"
import="com.zagerbone.user.User"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="java.text.*"%><%@ include  file='../identification.jsp'  
%><%
try{
	String flag1 =(String)request.getAttribute("flag1"); 
	HashMap hp_pri = (HashMap)(request.getAttribute("hp_pri"));
	System.out.println(hp_pri.toString());
	String filter_str = Tools.nvl((String)(hp_pri.get("filter_str")));
	String mypri = Tools.nvl((String)(hp_pri.get("mypri")));
		System.out.println("-----------*************"+hp_user.toString());
  if (flag1==null || flag1.equals(""))
  {   
		flag1 ="false" ;
		User user = new User();
		//HashMap hp_user = (HashMap)session.getAttribute("hp_user");	
		System.out.println("the user is====="+user.toString());
		String titleName = "新增用户信息";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		CachedRowSet crs_cols = (CachedRowSet)request.getAttribute("crs_cols");
		ArrayList al_colname = new ArrayList();
		ArrayList al_colnamec = new ArrayList();
		ArrayList al_datasrc = new ArrayList();
		ArrayList al_datasrctype = new ArrayList();
		while(crs_cols.next()){
			if(Tools.nvl(crs_cols.getString("deta_disp")).equals("1")){	
				al_colname.add((String)crs_cols.getString("colname"));
				al_colnamec.add((String)crs_cols.getString("colnamec"));
				al_datasrc.add((String)crs_cols.getString("datasrc"));
				al_datasrctype.add((String)crs_cols.getString("datasrctype"));
			}
		}
%>
<head>
<title>新增用户信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="js/jquery.min.js"> </script>
<script type="text/javascript" src="./js/map.js"></script>
 <link rel="stylesheet" href="js/jquery-ui.css">
<script src="js/jquery-ui-src.js"></script>
<script language="JScript" type="text/javascript"  src="js/jquery.ui.datepicker-zh-CN.js"> </script>
<style>
body{ padding:4px; margin:0px; }


#drag_window{position:absolute; height:200px; width:300px; top:150px; background:#fff; border:1px solid #ccc; font-size:12px; box-shadow:2px 2px 3px #999; -moz-user-select:none;z-index:-10; }
#drag_window_title{background:#eee; border-bottom:1px solid #ccc; line-height:24px;}
#drag_window_title h4{ margin:0px; padding:0px; padding-left:8px;}
#drag_window_main{padding:4px;overflow-y;scroll;height:160px;}
</style>
<script language="JScript" type="text/javascript"  src="./js/check.js"> </script>
<script>
//--------------------------------------
var xmlhttp;
var json;
var err3="未填写账号";
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
function myFunction1(){
// alert("in my functrion 1 ");
try{
	loadXMLDoc("./ajax/getemptouser.jsp?_="+Math.round(Math.random()*100000)+"&pri=<%=mypri%>&deptid=<%=(String)hp_user.get("deptid")%>",function(){
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var str = (xmlhttp.responseText);
			var inStr = "";
			str=(str).replace(/[\r|\n]/gm,'');
			//alert(str);
			json = eval("("+str+")");
			for ( i=0;i<json.length;i++){
				inStr=inStr +"<li><a onclick='filltab("+i+")'>"+json[i].emp_dept+"  :  "+json[i].emp_name+"</li>\n";
			}
			inStr="<ul>\n"+inStr+"\n</ul>";
			//alert(inStr);
			 $("#drag_window_main").html(inStr);
			 $("#drag_window_main").css({"overflow-y":"scroll","height":"160px;"});
			showlist();
			//alert("myfun");
		}
	  });
	  }catch(e){alert(e.description)}
}

function check(colname,val)
{
// alert("11");
if (colname=="username"){
	if(!isRegisterUserName(val)){
		alert("账号应为3-20位的 字母 数字 . _ 组合并且以字母开头，请检查格式");
		$("#username").css("background","#e88");
		return false;
	}
}
if(val=='')
{
	return false;
}
loadXMLDoc("./ajax/check.jsp?_="+Math.round(Math.random()*100000)+"&mode=checkname&col=admin_account&val="+val,function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
		var str = (xmlhttp.responseText);
		str=(str).replace(/[\r|\n]/gm,'');
			if(str.split(":")[1]=="0"){
				err3="";
				$("#username").css("background","#fff");
			}else{
				err3="账号重复,请使用其它账号！";
				alert(err3);
				$("#username").css("background","#e88");
			} 
	}
  });
}
function filltab(i){
	$("#usertype").val(json[i].emp_role);
	$("#work_unit").val(json[i].emp_dept);
	$("#deptid").val(json[i].deptid);
	$("#realname").val(json[i].emp_name);
	$("#eff_date").val(json[i].eff_time);
	$("#empid").val(json[i].empid);
	$("#flag").val("已禁用");
	alert(getCurDate("YYYY-MM-DD"));
	$("#regdate").val(getCurDate("YYYY-MM-DD"));
	showlist();
	$("#buttonconfirm").attr("disabled",false);
}
function getCurDate(s){
	var now = new Date();
	var res ="";
	res = s.replace("YYYY",now.getFullYear());
	res = res.replace("MM",now.getMonth()+1);
	res = res.replace("DD",now.getDate());
	return res;
}
</script>
<script>
$(document).ready(function(){
	$("#buttonlist").bind("click",function(){
		//alert("buttonlist clicked");
		//alert("buttonlist clicked");
		myFunction1();
		return false;
	});
	$("#buttonconfirm").attr("disabled",true);
	
	//取消弹窗的泡泡
	$(document).click(function(ev){
		if($("#drag_window").css("z-index")=="9")
		$("#drag_window").css("z-index","-9");
		//alert("after");
	});
	$("#drag_window").click(function(ev){
		return false;
	});
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
</head>

<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >

<div id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="sframe"  method="post" onKeyDown="if(event.keyCode==13)event.keyCode=9" >
    <input type="hidden" name="fid" value="UII00.03.01.03">
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>

	<%
	int ik = 0;
	while(ik<al_colname.size()){
			if(ik%2==0){
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
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" onblur="check('<%=al_colname.get(ik).toString()%>',this.value)" size="25" value=""/><font color="red">* 必填 </font></td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("getmap")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" size="25" value=""/>双击 打开/隐藏 地图</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("calendar")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" class="datepicker" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25" value=""/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("textarea")){
		if(ik%2==1){
		%><td  width="9%"></td><td  width="41%"></td></tr><%
		}
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="91%" colspan="3"><textarea cols="100%" rows="10" id="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25" value=""></textarea>&nbsp;&nbsp;</td> 
		<%}
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
		<input type="button" name="buttonlist" id="buttonlist" value="选择人员" style="width:80;height:23 "  >&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" name="button"id="buttonconfirm"  value="确 定" style="width:80;height:23 "  onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" name="button" value="重 填" style="width:80;height:23 ">&nbsp;&nbsp;&nbsp;&nbsp;
		<!--input type="button" name="button" value="打 印" style="width:80;height:23 " onclick="javascript:window.top.indexFrame.frames['manFrame'].print();">&nbsp;&nbsp;&nbsp;&nbsp;
		--><input type="button" name="button" value="返 回" style="width:80;height:23 " onClick="javascript:history.go(-1);">
	</td>
  </tr>
</table>
</form>

</div>
</body>
</html>
     <div id="drag_window">
    	<div id="drag_window_title" >
        	<h4>请选择要绑定用户的人员</h4>
        </div>
        <div id="drag_window_main" >
        	<ul>
            	<li>content</li>
            	<li>content</li>
            	<li>content</li>
            	<li>content</li>
            </ul>
        </div>
    </div>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none;"></iframe>
</iframe> 
<script language=javascript>
  function doSave()
  {
	  if((err3)!=""){
		alert("错误："+err3);
		return;
		}
		
  $("#usertype").val("免费用户");
  $("#usertype").attr("disabled",false);
  $("#eff_date").attr("disabled",false);
  $("#realname").attr("disabled",false);
  $("#empid").attr("disabled",false);
  $("#regdate").attr("disabled",false);
  $("#deptid").attr("disabled",false);
  $("#work_unit").attr("disabled",false);
    formHead.target = "submitframe";
	formHead.submit();
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
  }
  function checkfilter()
  {
	<%if(Integer.parseInt(mypri)==3){%>
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
			//document.getElementById('<%=key%>').disabled='disabled';
		}<%
		}
		}
		%>
  <%if(Integer.parseInt(mypri)<4){
  %>
  $("#usertype").val("免费用户");
  $("#usertype").attr("disabled",true);
  $("#eff_date").attr("disabled",true);
  $("#realname").attr("disabled",true);
  $("#empid").attr("disabled",true);
  $("#regdate").attr("disabled",true);
  $("#deptid").attr("disabled",true);
  $("#work_unit").attr("disabled",true);
  <%
  }
  %>
  }
  checkfilter();
</script>
<script>
function showlist(){
	try{
		//alert($("#drag_window"));
		if($("#drag_window").css("z-index")!="9" && $("#drag_window").css("z-index")!="-9"){
			var mw = $('#drag_window')[0].clientWidth;
			var mh = $('#drag_window')[0].clientHeight;
			var dh = document.body.clientHeight;
			var dw = document.body.clientWidth;
			var dl = $('#man_zone')[0].scrollLeft;
			var dt = $('#man_zone')[0].scrollTop;
			$('#drag_window').css("top",(dh-dt-mh)/2);
			$('#drag_window').css("left",(dw-dl-mw)/2);
			$('#drag_window').css("z-index","9");
			
		}else{
			$("#drag_window").css("z-index",$("#drag_window").css("z-index")=="9"?"-9":"9");
		}
	}catch(e){
	alert(e.description );
	}
}
</script>
<%
crs_cols.close();
}else{%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("增加成功！用户的初始密码为123456，请及时登录修改");
		window.top.indexFrame.frames['manFrame'].location = 'MainServlet_sys?fid=UII00.03.01.01<%=Integer.parseInt(mypri)==4?"":filter_str%>';
		}
	}
	checknow();
</script>
<%}
}catch(Exception ex){
	ex.printStackTrace();
}
%>


