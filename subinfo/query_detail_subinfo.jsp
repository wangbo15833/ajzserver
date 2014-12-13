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
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>
<link href="../css/calendar-win2k-cold-1.css" rel=stylesheet type=text/css>
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<link rel="stylesheet"  href="css/001.css" type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 	type=text/css>
<script type="text/javascript" src="./js/map.js"></script>
<script language="JavaScript" src="../share/js/utils.js"></script>
<script language="JScript" type="text/javascript"  src="./js/calendar.js"> </script>
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
 text-align:center;
 }
#drag_window_title h4{
 margin:0px;
 padding:0px;
 padding-left:8px;
 }
#drag_window_main{
	padding:4px;
	overflow:scroll-y;
	height:160px;
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
	$("#title4").click(function(){
	$("#title4").css("background",($("#cont4").css("display")=="none"?"#abd":"#adb"	));
		$("#cont4").toggle("middle");
	});
});
</script>
</head>


<%
String titleName="详细信息";
try{
	CachedRowSet crs = (CachedRowSet)request.getAttribute("crs");
	CachedRowSet crs2 = (CachedRowSet)request.getAttribute("crs2");
	CachedRowSet crs_xmzg = (CachedRowSet)request.getAttribute("crs_xmzg");
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div  id="man_zone" style="overflow-y:scroll;overflow-x:scroll;width:1000px color:#333;text-align:center; border: 1px #dedfd7 solid;">
<div class="maincontainer">
<div class = "maintitle"><font size="+2">项目信息查询</font></div>
	<div class="container" id="container1">
		<div class="title" id = "title1"><font id="basicInfo" size=+1>项目基本信息</font>
		</div>
		<div class="cont" id = "cont1">
		<div style="text-align:center;font-size:12px;">备案编号：<input class="putin"  name="bacode"  id="bacode"/><input type="hidden"  name='sub_code'  id="sub_code"/>备案日期：<input class="putin"  id="barq" name="barq">受理人：<input class="putin"  id="slr" name="slr">
		</div>
		<table border=1 cellspacing=0 cellpadding=0 width=1000 style="text-align:left;align:center;margin:0 auto;">
      <tr>
        <td  valign=center>工程名称：<input class="putin"  name='gcname'  id="gcname"/></td>
        <td  colspan=1 valign=center>工程地址：<input class="putin"  id="gcdz"  name="gcdz" /></td>
        <td  colspan=1 valign=center>纬度：<input class="putin"  id="latitude"  name="latitude" /></td>
        <td  colspan=1 valign=center>经度：<input class="putin"  id="longtitude"  name="longtitude" /></td>
      </tr>
      <tr>
        <td  valign=center>建筑面积：<input class="putin"  id="jzmj" name="jzmj">M<sup>2</sup></td>
        <td  valign=center>结构类型：<input class="putin"  id="jglx"  name="jglx" /></td>
        <td  colspan=2 valign=center>结构层数: <input class="putin"  id="jgcs"  name="jgcs" />层</td>
      </tr>
      <tr>
        <td  valign=center>工程造价：<input class="putin"  id="gczj"  name="gczj" />万元</td>
        <td  valign=center>建筑类别：<input class="putin"  id="jzlb"  name="jzlb" /></td>
        <td  colspan=2 valign=center>工程个数：<input class="putin"  id="gcgs"  name="gcgs" />个</td>
      </tr>
      <tr>
        <td  valign=center>总包安全生产许可证编号：<input class="putin"  id="zbsafelic"  name="zbsafelic" /></td>
        <td  rowspan=2 valign=center>基础类型：<input class="putin"  id="jclx"  name="jclx" /></td>
        <td  rowspan=2 valign=center>开竣工时间：</td>
		<td> <input class="putin"  id="kgsj"  name="kgsj" /></td>
      </tr>
      <tr>
        <td  valign=center>分包安全生产许可证编号：<input class="putin"  id="fbsafelic"  name="fbsafelic" /></td>
		<td><input class="putin"  id="jgsj"  name="jgsj" /></td>
      </tr>
	  </table>
	</div>
	</div>
	<div class="container" id="container2">
		<div class="title" id = "title2"><font id="basicInfo" size=+1>相关单位信息</font>
		</div>
		<div class="cont" id = "cont2">
		 <table border=1 cellspacing=0 cellpadding=0 width=1000>
			  <tr>
				<td  colspan=3 rowspan=2>各方责任主体和有关机构</td>
				<td  colspan=2>法人及联系电话</td>
				<td  colspan=2 valign=center>项目负责人、电话</td>
				<td  colspan=2 valign=center>技术负责人</td>
			  </tr>  
			  <tr>   
				<td  colspan=2>资质等级及证号</td>
				<td  colspan=2 valign=center>资格证书、证书编号</td>
				<td  colspan=2 valign=center>电话</td>
			  </tr>
			  <tr>
				<td  colspan=3 rowspan=2 valign=center>建设单位:<input class="putin"  id="jsdw"  name="jsdw" /></td>
				<td  valign=center><input class="putin"  id="js_fr_name"  name="js_fr_name" /></td>
				<td  valign=center><input class="putin"  id="js_fr_ph"  name="js_fr_ph" /></td>
				<td  valign=center><input class="putin"  id="js_xm_name"  name="js_xm_name" /></td>
				<td  valign=center><input class="putin"  id="js_xm_ph"  name="js_xm_ph" /></td>
				<td  valign=center><input class="putin"  id="js_js_name"  name="js_js_name" /></td>
				<td  valign=center><input class="putin"  id="js_js_ph"  name="js_js_ph" /></td>
			  </tr>
			  <tr>
				<td  valign=center><input class="putin"  id="js_fr_zs"  name="js_fr_zs" /></td>
				<td  valign=center><input class="putin"  id="js_fr_zsbh"  name="js_fr_zsbh" /></td>
				<td  valign=center><input class="putin"  id="js_xm_zs"  name="js_xm_zs" /></td>
				<td  valign=center><input class="putin"  id="js_xm_zsbh"  name="js_xm_zsbh" /></td>
				<td  valign=center><input class="putin"  id="js_js_zs"  name="js_js_zs" /></td>
				<td  valign=center><input class="putin"  id="js_js_zsbh"  name="js_js_zsbh" /></td>
			  </tr>
			  <tr>
				<td  colspan=3 rowspan=2 valign=center>勘察单位:<input class="putin"  id="kcdw"  name="kcdw" /></td>
				<td  valign=center><input class="putin"  id="kc_fr_name"  name="kc_fr_name" /></td>
				<td  valign=center><input class="putin"  id="kc_fr_ph"  name="kc_fr_ph" /></td>
				<td  valign=center><input class="putin"  id="kc_xm_name"  name="kc_xm_name" /></td>
				<td  valign=center><input class="putin"  id="kc_xm_ph"  name="kc_xm_ph" /></td>
				<td  valign=center><input class="putin"  id="kc_js_name"  name="kc_js_name" /></td>
				<td  valign=center><input class="putin"  id="kc_js_ph"  name="kc_js_ph" /></td>
			  </tr>
			  <tr>
				<td  valign=center><input class="putin"  id="kc_fr_zs"  name="kc_fr_zs" /></td>
				<td  valign=center><input class="putin"  id="kc_fr_zsbh"  name="kc_fr_zsbh" /></td>
				<td  valign=center><input class="putin"  id="kc_xm_zs"  name="kc_xm_zs" /></td>
				<td  valign=center><input class="putin"  id="kc_xm_zsbh"  name="kc_xm_zsbh" /></td>
				<td  valign=center><input class="putin"  id="kc_js_zs"  name="kc_js_zs" /></td>
				<td  valign=center><input class="putin"  id="kc_js_zsbh"  name="kc_js_zsbh" /></td>
			  </tr>
			  <tr>
				<td  colspan=3 rowspan=2 valign=center>设计单位:<input class="putin"  id="sjdw"  name="sjdw" /></td>
				<td  valign=center><input class="putin"  id="sj_fr_name"  name="sj_fr_name" /></td>
				<td  valign=center><input class="putin"  id="sj_fr_ph"  name="sj_fr_ph" /></td>
				<td  valign=center><input class="putin"  id="sj_xm_name"  name="sj_xm_name" /></td>
				<td  valign=center><input class="putin"  id="sj_xm_ph"  name="sj_xm_ph" /></td>
				<td  valign=center><input class="putin"  id="sj_js_name"  name="sj_js_name" /></td>
				<td  valign=center><input class="putin"  id="sj_js_ph"  name="sj_js_ph" /></td>
			  </tr>
			  <tr>
				<td  valign=center><input class="putin"  id="sj_fr_zs"  name="sj_fr_zs" /></td>
				<td  valign=center><input class="putin"  id="sj_fr_zsbh"  name="sj_fr_zsbh" /></td>
				<td  valign=center><input class="putin"  id="sj_xm_zs"  name="sj_xm_zs" /></td>
				<td  valign=center><input class="putin"  id="sj_xm_zsbh"  name="sj_xm_zsbh" /></td>
				<td  valign=center><input class="putin"  id="sj_js_zs"  name="sj_js_zs" /></td>
				<td  valign=center><input class="putin"  id="sj_js_zsbh"  name="sj_js_zsbh" /></td>
			  </tr>
			  <tr>
				<td  colspan=2 rowspan=4>施工单位</td>
				<td  rowspan=2 valign=center>总包:<input class="putin"  id="sgzbdw"  name="sgzbdw" /></td>
				<td  valign=center><input class="putin"  id="sgzb_fr_name"  name="sgzb_fr_name" /></td>
				<td  valign=center><input class="putin"  id="sgzb_fr_ph"  name="sgzb_fr_ph" /></td>
				<td  valign=center><input class="putin"  id="sgzb_xm_name"  name="sgzb_xm_name" /></td>
				<td  valign=center><input class="putin"  id="sgzb_xm_ph"  name="sgzb_xm_ph" /></td>
				<td  valign=center><input class="putin"  id="sgzb_js_name"  name="sgzb_js_name" /></td>
				<td  valign=center><input class="putin"  id="sgzb_js_ph"  name="sgzb_js_ph" /></td>
			  </tr>
			  <tr>
				<td  valign=center><input class="putin"  id="sgzb_fr_zs"  name="sgzb_fr_zs" /></td>
				<td  valign=center><input class="putin"  id="sgzb_fr_zsbh"  name="sgzb_fr_zsbh" /></td>
				<td  valign=center><input class="putin"  id="sgzb_xm_zs"  name="sgzb_xm_zs" /></td>
				<td  valign=center><input class="putin"  id="sgzb_xm_zsbh"  name="sgzb_xm_zsbh" /></td>
				<td  valign=center><input class="putin"  id="sgzb_js_zs"  name="sgzb_js_zs" /></td>
				<td  valign=center><input class="putin"  id="sgzb_js_zsbh"  name="sgzb_js_zsbh" /></td>
			  </tr>
			  <tr>
				<td  colspan=1 rowspan=2 valign=center>分包:<input class="putin"  id="sgfbdw"  name="sgfbdw" /></td>
				<td  valign=center><input class="putin"  id="sgfb_fr_name"  name="sgfb_fr_name" /></td>
				<td  valign=center><input class="putin"  id="sgfb_fr_ph"  name="sgfb_fr_ph" /></td>
				<td  valign=center><input class="putin"  id="sgfb_xm_name"  name="sgfb_xm_name" /></td>
				<td  valign=center><input class="putin"  id="sgfb_xm_ph"  name="sgfb_xm_ph" /></td>
				<td  valign=center><input class="putin"  id="sgfb_js_name"  name="sgfb_js_name" /></td>
				<td  valign=center><input class="putin"  id="sgfb_js_ph"  name="sgfb_js_ph" /></td>
			  </tr>
			  <tr>
				<td  valign=center><input class="putin"  id="sgfb_fr_zs"  name="sgfb_fr_zs" /></td>
				<td  valign=center><input class="putin"  id="sgfb_fr_zsbh"  name="sgfb_fr_zsbh" /></td>
				<td  valign=center><input class="putin"  id="sgfb_xm_zs"  name="sgfb_xm_zs" /></td>
				<td  valign=center><input class="putin"  id="sgfb_xm_zsbh"  name="sgfb_xm_zsbh" /></td>
				<td  valign=center><input class="putin"  id="sgfb_js_zs"  name="sgfb_js_zs" /></td>
				<td  valign=center><input class="putin"  id="sgfb_js_zsbh"  name="sgfb_js_zsbh" /></td>
			  </tr>
			  <tr>
				<td  colspan=3 rowspan=2 valign=center>监理单位:<input class="putin"  id="jldw"  name="jldw" /></td>
				<td  valign=center><input class="putin"  id="jl_fr_name"  name="jl_fr_name" /></td>
				<td  valign=center><input class="putin"  id="jl_fr_ph"  name="jl_fr_ph" /></td>
				<td  valign=center><input class="putin"  id="jl_xm_name"  name="jl_xm_name" /></td>
				<td  valign=center><input class="putin"  id="jl_xm_ph"  name="jl_xm_ph" /></td>
				<td  valign=center><input class="putin"  id="jl_js_name"  name="jl_js_name" /></td>
				<td  valign=center><input class="putin"  id="jl_js_ph"  name="jl_js_ph" /></td>
			  </tr>
			  <tr>
				<td  valign=center><input class="putin"  id="jl_fr_zs"  name="jl_fr_zs" /></td>
				<td  valign=center><input class="putin"  id="jl_fr_zsbh"  name="jl_fr_zsbh" /></td>
				<td  valign=center><input class="putin"  id="jl_xm_zs"  name="jl_xm_zs" /></td>
				<td  valign=center><input class="putin"  id="jl_xm_zsbh"  name="jl_xm_zsbh" /></td>
				<td  valign=center><input class="putin"  id="jl_js_zs"  name="jl_js_zs" /></td>
				<td  valign=center><input class="putin"  id="jl_js_zsbh"  name="jl_js_zsbh" /></td>
			  </tr>
			  
			  </table>
			  <table border=1 cellspacing=0 cellpadding=0 width=1000>
			  <tr>
				<td  colspan=1 valign=center>施工单位项目经理</td>
				<td  colspan=1 valign=center><input class="putin"  id="sg_xmjl_name"  name="sg_xmjl_name" /></td>
				<td  colspan=1>项目经理资质、号码、身份证号</td>
				<td  colspan=1 valign=center><input class="putin"  id="sg_xmjl_zz"  name="sg_xmjl_zz" />、<input class="putin"  id="sg_xmjl_ph"  name="sg_xmjl_ph" />、<input class="putin"   id="sg_xmjl_id"  name="sg_xmjl_id" /></td>
			  </tr>
			  <tr>
				<td  colspan=1>总监理工程师</td>
				<td  colspan=1 valign=center><input class="putin"  id="zj_gcs_name"  name="zj_gcs_name" /></td>
				<td  colspan=1>资格证书、号码、身份证号</td>
				<td  colspan=1 valign=center><input class="putin"  id="zj_gcs_zz"  name="zj_gcs_zz" />、<input class="putin"  id="zj_gcs_ph"  name="zj_gcs_ph" />、<input class="putin"  id="zj_gcs_id"  name="zj_gcs_id" /></td>
			  </tr>
			  </table>
		</div>
	</div>
	
	<div class="container" id="container3">
		<div class="title" id = "title3"><font id="basicInfo" size=+1>安保体系人员名单</font></div>
		<div class="cont" id = "cont3">
		  <table border=1 cellspacing=0 cellpadding=0 width=1000>
			  <tr>
				<td colspan=6>总包现场安全保障体系人员名单</td>
				<td colspan=6>分包现场安全保障体系人员名单</td>
			  </tr>
			  <tr>
				<td>姓名</td>
				<td >性别</td>
				<td >职称</td>
				<td >专业</td>
				<td >电话</td>
				<td>考核证号</td>
				<td >姓名</td>
				<td >性别</td>
				<td >职称</td>
				<td >专业</td>
				<td >电话</td>
				<td>考核证号</td>
			  </tr>
			  <tr>
				<td valign=center> <input class="putin"  id="zb_aq_name_1"  name="zb_aq_name_1" />	</td>
				<td valign=center> <input class="putin"  id="zb_aq_gen_1"  name="zb_aq_gen_1" />	</td>
				<td valign=center> <input class="putin"  id="zb_aq_zc_1"  name="zb_aq_zc_1" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_zy_1"  name="zb_aq_zy_1" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_ph_1"  name="zb_aq_ph_1" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_khzh_1"  name="zb_aq_khzh_1" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_name_1"  name="fb_aq_name_1" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_gen_1"  name="fb_aq_gen_1" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_zc_1"  name="fb_aq_zc_1" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_zy_1"  name="fb_aq_zy_1" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_ph_1"  name="fb_aq_ph_1" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_khzh_1"  name="fb_aq_khzh_1" />	</td>
			  </tr>
			  <tr>
				<td valign=center> <input class="putin"  id="zb_aq_name_2"  name="zb_aq_name_2" />	</td>
				<td valign=center> <input class="putin"  id="zb_aq_gen_2"  name="zb_aq_gen_2" />	</td>
				<td valign=center> <input class="putin"  id="zb_aq_zc_2"  name="zb_aq_zc_2" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_zy_2"  name="zb_aq_zy_2" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_ph_2"  name="zb_aq_ph_2" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_khzh_2"  name="zb_aq_khzh_2" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_name_2"  name="fb_aq_name_2" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_gen_2"  name="fb_aq_gen_2" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_zc_2"  name="fb_aq_zc_2" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_zy_2"  name="fb_aq_zy_2" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_ph_2"  name="fb_aq_ph_2" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_khzh_2"  name="fb_aq_khzh_2" />	</td>
			  </tr>
			  <tr>
				<td valign=center> <input class="putin"  id="zb_aq_name_3"  name="zb_aq_name_3" />	</td>
				<td valign=center> <input class="putin"  id="zb_aq_gen_3"  name="zb_aq_gen_3" />	</td>
				<td valign=center> <input class="putin"  id="zb_aq_zc_3"  name="zb_aq_zc_3" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_zy_3"  name="zb_aq_zy_3" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_ph_3"  name="zb_aq_ph_3" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_khzh_3"  name="zb_aq_khzh_3" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_name_3"  name="fb_aq_name_3" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_gen_3"  name="fb_aq_gen_3" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_zc_3"  name="fb_aq_zc_3" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_zy_3"  name="fb_aq_zy_3" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_ph_3"  name="fb_aq_ph_3" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_khzh_3"  name="fb_aq_khzh_3" />	</td>
			  </tr>
			  <tr>
				<td valign=center> <input class="putin"  id="zb_aq_name_4"  name="zb_aq_name_4" />	</td>
				<td valign=center> <input class="putin"  id="zb_aq_gen_4"  name="zb_aq_gen_4" />	</td>
				<td valign=center> <input class="putin"  id="zb_aq_zc_4"  name="zb_aq_zc_4" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_zy_4"  name="zb_aq_zy_4" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_ph_4"  name="zb_aq_ph_4" />		</td>
				<td valign=center> <input class="putin"  id="zb_aq_khzh_4"  name="zb_aq_khzh_4" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_name_4"  name="fb_aq_name_4" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_gen_4"  name="fb_aq_gen_4" />	</td>
				<td valign=center> <input class="putin"  id="fb_aq_zc_4"  name="fb_aq_zc_4" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_zy_4"  name="fb_aq_zy_4" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_ph_4"  name="fb_aq_ph_4" />		</td>
				<td valign=center> <input class="putin"  id="fb_aq_khzh_4"  name="fb_aq_khzh_4" />	</td>
			  </tr>
			</table>
		</div>
		</div>
	
	<div class="container" id="container4">
		<div class="title" id = "title4"><font id="basicInfo" size=+1>项目整改信息</font></div>
		<div class="cont" id = "cont4">
		  <table border=1 cellspacing=0 cellpadding=0 width=1000><%
			if(crs_xmzg.size()==0){
			%>
			<tr>
			<td><font color="darkgreen">该项目没有整改记录</font></td>
			</tr><%
			}else{
			%>
			<tr>
			<td>整改类别</td>
			<td>开始时间</td>
			<td>结束时间</td>
			<td>说    明</td>
			</tr><%
				while(crs_xmzg.next()){
					%>
			<tr>
				<td><%=crs_xmzg.getString("zglb")%></td>
				<td><%=crs_xmzg.getString("start_time")%></td>
				<td><%=crs_xmzg.getString("end_time")%></td>
				<td style="width:500px;"><div style="width:100%;"><%=crs_xmzg.getString("cont")%></div></td>
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
					cont = (json[i].cont==undefined?"------------":json[i].cont).substring(0,10);
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
					if(json[i].zglb==undefined){
						zglb="<font color='darkgreen'>此项目无整改记录</font>";
					}else if(json[i].zglb=="停工整改"){
						zglb="<font color='darkred'>"+json[i].zglb+"</font>";
					}else if(json[i].zglb=="限期整改"){
						zglb="<font color='red'>"+json[i].zglb+"</font>";
					}
					inStr=inStr +"<li><a onclick=\"queryzg('"+zgid+"')\"><span >"+gcnamedisplay+"</span><span>"+zglb+"</span><span>"+cont+"</span></li>\n";
				}
				inStr="<ul>\n"+inStr+"\n</ul>";
				$("#drag_window_title").html("<h4><font color='darkorange'>"+dept+"</font> &nbsp;的相关项目信息</h4>");
				$("#drag_window_main").html(inStr);
				$("#drag_window_main").css({"height":"160px","overflow-y":"scroll"});
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
	<%crs.next();
		String entabcol="sub_code;bacode;gcname;gcdz;jzmj;jglx;jgcs;gczj;jzlb;gcgs;zbsafelic;jclx;kgsj;fbsafelic;jgsj;latitude;longtitude";
		String ai[]=entabcol.split(";");
		System.out.println("=============crs.size());===="+crs.size());
		System.out.println("=============crs2.size());===="+crs2.size());
		
		for(int i=0 ; i<ai.length;i++){
		System.out.println("================="+ai[i]);
			%>g('<%=ai[i]%>').value="<%=crs.getString(ai[i])%>";
			$('#<%=ai[i]%>').attr("readonly","readonly");
			//g('<%=ai[i]%>').size=g('<%=ai[i]%>').value.length;
			<%
		}
		while(crs2.next()){
		System.out.println("================="+crs2.getString("parname"));
			%>g('<%=crs2.getString("parname")%>').value="<%=crs2.getString("parvalue")%>";
			$('#<%=crs2.getString("parname")%>').attr("readonly","readonly");//g('<%=crs2.getString("parname")%>').size=(g('<%=crs2.getString("parname")%>').value.length>5?g('<%=crs2.getString("parname")%>').value.length:5);
			<%
		}
		
	%>
  
  }
function autofil(){}
autofill();
changeWidth();
</script>


<%
crs.close();
crs2.close();
}catch (Exception exception)
{
exception.printStackTrace();
}
%>