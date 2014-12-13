<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="com.zagerbone.util.Query"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>
<%@ page import ="java.util.Iterator"%>
<%@ page import ="java.util.Map.Entry"%>
<%@ page import ="java.util.Set"%>
<%
System.out.println("客户端进入查询页面");
%>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script src="js/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$(".sub_title").click(function(){
		$(this).find("~.sub_cont").slideToggle("fast");
	});
});
</script>
<style>
a{
text-decoration:none;
}
li{
list-style-type:none;
//circle
}
ul{
padding:0;
margin:0;
}
.main_nav{
text-align:center;
background:#eeaaaa;
color:#aa55ee;
font-size:24px;
margin-bottom:30px;
}
.logo{
width:24px;
height:24px;
}
.sub_nav{
height:500px;
overflow:scroll;
}


.sub_nav_ul li{
	text-align:left;
	background:#aaaaee;
	color:#5555aa;
	font-size:24px;
	padding-left:20px;
	margin-bottom:10px;
}
.forin{
	width:60%;
	font-size:18px;
}
.qrybtn{
	font-size:18px;
}
.sub_cont{
	display:none;
}
.hint{
	font-size:14px;
	color:#555555;
	text-align:center;
}
</style>
</head>
<body bgcolor="#dddddd" width=100% height=100%>
<div class="main_nav" >
	<span><img class="logo" src="image/logo.png"/></span>
	<span>快捷查询</span>
</div>
<div class="sub_nav">
	<ul class="sub_nav_ul">
		<li>
			<div class="sub_title">查询项目信息</div>
			<div class="sub_cont">
				<div><input type = "input" class="forin" value="" ><input id="subqry" class="qrybtn" type="button" value="查询"></div>
				<div class="hint">提示：根据输入的信息对项目名称进行过滤</div>
			</div>
		</li>
		<li>
			<div class="sub_title">查询单位信息</div>
			<div class="sub_cont">
				<div><input type = "input" class="forin" value="" ><input id="deptqry" class="qrybtn" type="button" value="查询"></div>
				<div class="hint">提示：根据输入的信息对单位名称进行过滤</div>
			</div>
		</li>
		<li>
			<div class="sub_title">查询监督计划</div>
			<div class="sub_cont">
				<input type = "input" class="forin" value="" ><span class="hint">项目名称</span><br/>
				<select class="forin" >
					<option selected="selected" value="基础检查">基础检查</option>
					<option value="主体检查">主体检查</option>
					<option value="装修检查">装修检查</option>
					<option value="随机抽查">随机抽查</option>
				</select>
				<span class="hint">监督检查类别</span><br/>
				<input type = "input" class="forin" value="" ><span class="hint">计划检查时间,格式：2010-01</span><br/>
				<input id="jdjhqry" class="qrybtn" type="button" value="查询">
			</div>
		</li>
		<li>
			<div class="sub_title">查询检查记录</div>
			<div class="sub_cont">
				<input type = "input" class="forin" value="" ><input id="jdjlqry" class="qrybtn" type="button" value="查询">
			</div>
		</li>
		<li>
			<div class="sub_title">查询设备备案信息</div>
			<div class="sub_cont">
				<input type = "input" class="forin" value="" ><input id="qzbaqry" class="qrybtn" type="button" value="查询">
			</div>
		</li>
		<li>
			<div class="sub_title">查询安全生产数据库</div>
			<div class="sub_cont">
				<input type = "input" class="forin" value="" ><input id="jcbzqry" class="qrybtn" type="button" value="查询">
			</div>
		</li>
	</ul>
</div>
</body>
<script>
$(document).ready(function(){
	$(".qrybtn").click(function(){
		var thisid = $(this).attr("id");
		if(thisid=="subqry"){//--项目查询
			var qrystr = $(this).siblings(".forin").val();
			qrystr = encodeURI(qrystr);
			location = "qry_sub.jsp?subname="+qrystr;
		}else if(thisid=="deptqry"){//单位查询
			var qrystr = $(this).siblings(".forin").val();
			qrystr = encodeURI(qrystr);
			location = "qry_dept.jsp?deptname="+qrystr;
		}else if(thisid=="jdjhqry"){//监督计划查询
			var gcname;
			var jdlb;
			var jhtime;
			$(this).prevAll(".forin").each(function(i){
				switch(i){
				case 0:
					jhtime=encodeURI($(this).val());
				break;
				case 1:
					jdlb=encodeURI($(this).val());
				break;
				case 2:
					gcname=encodeURI($(this).val());
				break;
				}
			});
			alert(gcname+"--"+jdlb+"--"+jhtime);
			//qrystr = encodeURI(qrystr);
			//location = "qry_dept.jsp?deptname="+qrystr;
		}else if(thisid=="jdjlqry"){//监督记录查询
		
		}else if(thisid=="qzbaqry"){//起重备案，设备信息查询
		
		}else if(thisid=="jcbzqry"){//检查标准查询
		
		}
		
	});
});
</script>
</html>