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
<%@ include  file='../identification.jsp' %> 
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<script language="JavaScript" src="../share/js/utils.js"></script>
<script language="JScript" type="text/javascript"  src="js/jquery.min.js"> </script>
<style>
table,table tr,table tr td,.container{
	font-size;18px;
	font-color:#222222;
	margin:0 auto;	
}
.putin{
	width:50px;
	background:#ffffff;
	border-bottom-color:#000;
	border-bottom-width:0px;
	border-top-width:0px;
	border-left-width:0px;
	border-right-width:1px; solid #ff6633;
	color:#402323;
	FONT-SIZE: 14px;
	FONT-STYLE: normal;
	FONT-VARIANT: normal;
	FONT-WEIGHT: normal;
	HEIGHT: 15px;
	LINE-HEIGHT: normal;
}
  .maincontainer{
	width:100%;
	height:97%;
	border: 0px #dedfd7 solid;
	overflaw-y:scroll;
	/* border-bottom: 0px #dedfd7 solid; */
 }
 .maintitle{
	width:100%;
	background:#f0f0f0;
	border: 1px #dedfd7 solid;
 }

 .container{
	width:98%;
	margin:0 auto;
	padding-top:20px;
	border: 1px #dedfd7 solid;
	padding-bottom:5px;
 }
 
 .container .title{
	width:100%;
	background:#abd;
	border: 1px #dedfd7 solid;
	text-align:center;
 }
 .container .title:hover{
	cursor:pointer;
	color:#8888ff;
 }
 .container .cont{
	border: 1px #dedfd7 solid;
	margin:0 0 0 0;
 }
 .container .title font{
 }
 #drag_window{
 position:absolute;
 height:200px;
 width:500px;
 top:150px;
 background:#fff;
 border:1px solid #ccc;
 font-size:12px;
 box-shadow:2px 2px 3px #999;
 -moz-user-select:none;
 z-index:-10;
 }
#drag_window_title{
background:#eee;
 border-bottom:1px solid #ccc;
 line-height:24px;
 }
#drag_window_title h4{
 margin:0px;
 padding:0px;
 padding-left:8px;
 }
#drag_window_main{
	padding:4px;
	overflow:scroll-y;
}
/*span设置宽度*/
span {
 /*background-color:#ffcc00;*/
 display:-moz-inline-box;
 display:inline-block;
 width:150px;
 }
 

</style> 
<script>
$(document).ready(function(){
	$("#title1").click(function(){
	$("#title1").css("background",($("#cont1").css("display")=="none"?"#abd":"#adb"	));
		$("#cont1").toggle("middle");
		});
	//$("#title1").css("background",($("#title1").css("background")=="#abd"?"#adb":"#abd"	));
	$("#title2").click(function(){
	$("#title2").css("background",($("#cont2").css("display")=="none"?"#abd":"#adb"	));
		$("#cont2").toggle("middle");
	});
	$("#title3").click(function(){
	$("#title3").css("background",($("#cont3").css("display")=="none"?"#abd":"#adb"	));
		$("#cont3").toggle("middle");
	});
});
</script>
</head>


<%
try{
	CachedRowSet crs = (CachedRowSet)request.getAttribute("crs");
	CachedRowSet crs_emp = (CachedRowSet)request.getAttribute("crs_emp");
	CachedRowSet crs_sub = (CachedRowSet)request.getAttribute("crs_sub");
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div  id="man_zone" style="overflow-y:scroll;overflow-x:scroll;width:1000px color:#333;text-align:center; border: 1px #dedfd7 solid;">
<div class="maincontainer">
<div class = "maintitle"><font size="+2">单位信息查询</font></div>
	<div class="container" id="container1">
		<div class="title" id = "title1"><font id="basicInfo" size=+1>单位基本信息</font>
		</div>
		<div class="cont" id = "cont1">
		 <table border=1 cellspacing=0 cellpadding=0 width=1000>
				<tr>		
					<td >企业名称：</td><td><input class="putin" id="dept_name" name="dept_name" /></td> 
							
					<td >企业类型：</td><td><input class="putin" id="dept_dwlx"  name="dept_dwlx"/></td>
						
					<td >营业执照注册号：</td><td><input class="putin" name="lic_code" id="lic_code" size="25"value=""/></td> 
							
					<td >经济类型：</td><td><input class="putin" name="com_type" id="com_type" size="25"value=""/></td> 
					
				</tr>
				<tr>		
					<td >设立时间：</td><td><input class="putin" id="est_date" name="est_date" size="25" value=""/></td> 
							
					<td >联系电话：</td><td><input class="putin" name="con_phone" id="con_phone" size="25"value=""/></td> 
							
					<td >组织机构代码证：</td><td><input class="putin" id="org_code" name="org_code" value=""/></td> 
							
					<td >主项资质：</td><td><input class="putin" id="zxzz" name="zxzz" value=""/></td> 
					
				</tr>
				<tr>
					<td >增项资质：</td><td><input class="putin" id="zengxzz" name="zengxzz" /></td> 
							
					<td >资质证书编号：</td><td><input class="putin" id="zz_code" name="zz_code" /></td> 
						
					<td >注册地址(邮编)：</td><td><input class="putin" name="dept_addr" id="dept_addr" value=""/></td> 
						
					<td colspan=3 >纬度：<input class="putin" id="latitude" name="latitude" id="latitude"value=""/>经度：<input class="putin" id="longtitude" name="longtitude" id="longtitude"  value=""/></td> 
				</tr>
			</table>
	</div>
	</div>
	<div class="container" id="container2">
		<div class="title" id = "title2"><font id="basicInfo" size=+1>单位人员信息</font>
		</div>
		<div class="cont" id = "cont2">
		 <table border=1 cellspacing=0 cellpadding=0 width=1000>
			<tr>		
				<td >人员姓名：</td>
						
				<td >身份证号：</td>
					
				<td >人员性别：</td>
						
				<td >出生日期：</td> 
				
			</tr>
			<%while(crs_emp.next()){%>
			<tr>
				<td ><input class="putin" name="emp_name" id="emp_name" size="25"value="<%=crs_emp.getString("emp_name")%>"/></td> 
				
				<td ><input class="putin" name="idcard" id="idcard" size="25"value="<%=crs_emp.getString("idcard")%>"/></td> 
				
				<td ><input class="putin" id="emp_gender"  name="emp_gender" value="<%=crs_emp.getString("emp_gender")%>"/></td>
				
				<td ><input class="putin" id="emp_lb" name="emp_lb" value="<%=crs_emp.getString("emp_lb")%>"/></td>
			</tr>
			<%}%>
		</table>

		</div>
	</div>
	
	<div class="container" id="container3">
		<div class="title" id = "title3"><font id="basicInfo" size=+1>相关项目信息</font></div>
		<div class="cont" id = "cont3">
		  <table border=1 cellspacing=0 cellpadding=0 width=1000><%
			if(crs_sub.size()==0){
			%>
			<tr>
			<td><font color="darkgreen">该单位没有相关项目</font></td>
			</tr><%
			}else{
			%>
			<tr>
			<td>工程名称</td>
			<td>开工时间</td>
			<td>竣工时间</td>
			<td>地    址</td>
			</tr><%
				while(crs_sub.next()){
					%>
			<tr>
				<td><%=crs_sub.getString("gcname")%></td>
				<td><%=crs_sub.getString("kgsj")%></td>
				<td><%=crs_sub.getString("jgsj")%></td>
				<td style="width:400px;"><div style="width:100%;"><%=crs_sub.getString("gcdz")%></div></td>
			</tr>
					<%
				}
			}
			%>
			</table>
		</div>
		</div>
		<div style = "text-align:center"><input type="button" name="button" value="返 回" style="width:80;height:30 " onClick="javascript:history.go(-1);"></div>	
	</div>

</div>


</div>
</body>
     <div id="drag_window">
    	<div id="drag_window_title" >
        	<h4>此单位的相关项目信息</h4>
        </div>
        <div id="drag_window_main">
        	<ul>
            	<li>content</li>
            	<li>content</li>
            	<li>content</li>
            	<li>content</li>
            </ul>
        </div>
    </div>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
</iframe>
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
function myFunction1(mode,con,dept){
// alert("in my functrion 1 ");
	loadXMLDoc("./ajax/getdepthistory.jsp?_="+Math.round(Math.random()*100000)+"&mode="+mode+"&con="+con+"&dept="+dept,function(){
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var str = (xmlhttp.responseText);
			var inStr = "";
			str=(str).replace(/[\r|\n]/gm,'');
			//alert(str);
			json = eval("("+str+")");
			
			var zgid = '';
            var pregcname = '';
			var curgcname ='';
			var nextgcname ='--';
			var zglb = '';
			var cont = '';
			if(json.length!=0){
				for ( i=0;i<json.length;i++){
					zgid = (json[i].zgid==undefined?"--":json[i].zgid);
					curgcname = json[i].gcname;
					cont = (json[i].cont==undefined?"--":json[i].cont).substring(0,10);
					if(i+1<json.length){
						nextgcname = json[i+1].gcname;
					}else{
						nextgcname='--';
					}
					if (curgcname==pregcname && curgcname==nextgcname){
						gcnamedisplay = "&nbsp;&nbsp;&nbsp;┣";
					}else if(curgcname==pregcname && curgcname!=nextgcname){
						gcnamedisplay = "&nbsp;&nbsp;&nbsp;┗";
					}else if(curgcname!=pregcname){
						gcnamedisplay=curgcname;
					}
					pregcname = curgcname;
					zglb = (json[i].zglb==undefined?"<font color='darkgreen'>此项目无整改记录</font>":"<font color='darkred'>"+json[i].zglb+"</font>");
					inStr=inStr +"<li><a onclick=\"queryzg('"+zgid+"')\"><span >"+gcnamedisplay+"</span><span>"+zglb+"</span><span>"+cont+"</span></li>\n";
				}
				inStr="<ul>\n"+inStr+"\n</ul>";
				$("#drag_window_title").html("<h4><font color='darkorange'>"+dept+"</font> &nbsp;的相关项目信息</h4>");
				$("#drag_window_main").html(inStr);
			 }else{
				inStr="<div>该单位没有相关的项目信息</div>";
				 $("#drag_window_main").html(inStr);
			 }
		}
			 
	  });
}

</script>
<script>
function queryzg(s)
  {
	if(s=="--"){
		alert("此项目没有整改信息");
		return;
	}
	  window.top.indexFrame.frames['manFrame'].location ='MainServlet_sys?fid=UII00.04.03.05&id='+s;

	}
</script>
<script>
function showdrag(){
	var mw = $('#drag_window')[0].clientWidth;
	var mh = $('#drag_window')[0].clientHeight;
	var dh = document.body.clientHeight;
	var dw = document.body.clientWidth;
	var dl = $('#man_zone')[0].scrollLeft;
	var dt = $('#man_zone')[0].scrollTop;
	$('#drag_window').css("top",(dh-dt-mh)/2);
	$('#drag_window').css("left",(dw-dl-mw)/2);
	$("#drag_window").css("z-index","10");
	$("#drag_window_main").html("<h2>数据加载中......</h2>");
}
</script>
<script>
$(document).ready(function(){
$("#sjdw").hover (
function(){
	if($("#sjdw").val()==''){
		return;
	}
	//移入
	//alert("move in");
	showdrag();
	myFunction1("getdepthistory","sjdw",$("#sjdw").val());
},
function(){
	//移出
	//alert("move out");
	//$("#drag_window").css("z-index","-10");
});
$("#jsdw").hover (
function(){
	if($("#jsdw").val()==''){
		return;
	}
	//移入
	showdrag();
	myFunction1("getdepthistory","jsdw",$("#jsdw").val());
},
function(){
	//移出
	//alert("move out");
	//$("#drag_window").css("z-index","-10");
});
$("#kcdw").hover (
function(){
	if($("#kcdw").val()==''){
		return;
	}
	//移入
	showdrag();
	myFunction1("getdepthistory","kcdw",$("#kcdw").val());
},
function(){
	//移出
	//alert("move out");
	//$("#drag_window").css("z-index","-10");
});
$("#jldw").hover (
function(){
	if($("#jldw").val()==''){
		return;
	}
	//移入
	showdrag();
	myFunction1("getdepthistory","jldw",$("#jldw").val());
},
function(){
	//移出
	//alert("move out");
	//$("#drag_window").css("z-index","-10");
});
$("#sgzbdw").hover (
function(){
	if($("#sgzbdw").val()==''){
		return;
	}
	//移入
	showdrag();
	myFunction1("getdepthistory","sgzbdw",$("#sgzbdw").val());
},
function(){
	//移出
	//alert("move out");
	//$("#drag_window").css("z-index","-10");
});
$("#sgfbdw").hover (
function(){
	if($("#sgfbdw").val()==''){
		return;
	}
	//移入
	showdrag();
	myFunction1("getdepthistory","sgfbdw",$("#sgfbdw").val());
},
function(){
	//移出
	//alert("move out");
	//$("#drag_window").css("z-index","-10");
});
});
</script>
<script>
$(document).click(function(){
//alert("document click");
	$("#drag_window").css("z-index",-10);
	//$("#baidumap").css("z-index",-9);
	
});
$("#drag_window").click(function(){
	return false;
});
</script>
<script>
function getByteLen(val) {
	var len = 0;
	for (var i = 0; i < val.length; i++) {
		var a = val.charAt(i);
		if (a.match(/[^\x00-\xff]/ig) != null){
			len += 2;
		}else{
			len += 1;
		}
	}
	return len;
}
</script>
 <script>
function changeWidth() { 
	for(i=0;i<$(".putin").length;i++){
		//$(".putin")[i].css("width","10");
		var len = getByteLen($($(".putin")[i]).val());
		$($(".putin")[i]).css("width",len<4?4*7+14:len*7+14);
		$($(".putin")[i]).attr("readonly","readonly");
	}
}
</script>
<script language=javascript>
	function g(s){
		return document.getElementById(s);
	}
	
  function doReturn()
  {
	window.top.indexFrame.frames['manFrame'].location = './MainServlet_sys?fid=UII00.00.04.06'
  }
  function autofill(){
	//alert("111");
	<%crs.next();
		String entabcol="dept_name;dept_dwlx;con_phone;dept_addr;latitude;longtitude;dept_bz;lic_code;com_type;est_date;org_code;zxzz;zengxzz;zz_code;deptid";
		String ai[]=entabcol.split(";");
		System.out.println("=============crs.size());===="+crs.size());
		for(int i=0 ; i<ai.length;i++){
		System.out.println("================="+ai[i]);
			%>if($('#<%=ai[i]%>')[0]!=null){$('#<%=ai[i]%>').val("<%=crs.getString(ai[i])%>");
			$('#<%=ai[i]%>').attr("readonly","readonly");
			}
			<%
		}
		
		/*
		String emp_list="emp_name;emp_gender;emp_birthday;emp_dept;phone;home_addr;emp_lb;emp_zw;emp_zc;major;cell_pho;fzdw;fzsj;zsbh;yxq;zgxl;empid;idcard";
		String empi[]=emp_list.split(";");
		System.out.println("=============crs_emp.size());===="+crs_emp.size());
		for(int i=0 ; i<empi.length;i++){
		System.out.println("================="+empi[i]);
		System.out.println("======empi[i]-------"+empi[i]);
			%>if($('#<%=empi[i]%>')[0]!=null){$('#<%=empi[i]%>').val("<%=crs_emp.getString(empi[i])%>");
			$('#<%=empi[i]%>').attr("readonly","readonly");
			}
			<%
		}
		*/
	%>
  
  }
function autofil(){}
autofill();
changeWidth();
</script>

<%
crs.close();
crs_emp.close();
crs_sub.close();
}catch (Exception exception)
{
exception.printStackTrace();
}
%>