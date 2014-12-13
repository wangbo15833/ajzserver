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
<meta http-equiv="X-UA-Compatible"content="IE=EmulateIE 7"><!--IE 7 mode -->
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="js/jquery.min.js"> </script>
<script type="text/javascript" src="./js/map.js"></script>
 <link rel="stylesheet" href="js/jquery-ui.css">
<script src="js/jquery-ui-src.js"></script>
<script language="JScript" type="text/javascript"  src="js/jquery.ui.datepicker-zh-CN.js"> </script>
<style>
.putin{
width:50px;
background:#ffffff;
border-bottom-color:#000;
 border-bottom-width:1px;
 border-top-width:0px;
 border-left-width:0px;
 border-right-width:0px; solid #ff6633;
 color: #000000; FONT-SIZE: 9pt;
 FONT-STYLE: normal;
 FONT-VARIANT: normal;
 FONT-WEIGHT: normal;
 HEIGHT: 18px;
 LINE-HEIGHT: normal;
 background:#eef;
 }
</style> <style>
body{ padding:4px; margin:0px; }


#drag_window{position:absolute; height:200px; width:500px; top:150px; background:#fff; border:1px solid #ccc; font-size:12px; box-shadow:2px 2px 3px #999; -moz-user-select:none;z-index:-10; }
#drag_window_title{background:#eee; border-bottom:1px solid #ccc; line-height:24px;}
#drag_window_title h4{ margin:0px; padding:0px; padding-left:8px;}
#drag_window_main{padding:4px;overflow:scroll-y;}
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
</head>


<%
String titleName="修改项目信息";
String flag1 =(String)request.getAttribute("flag1"); 
try{
	HashMap hp_pri = (HashMap)(request.getAttribute("hp_pri"));
	System.out.println(hp_pri.toString());
	String filter_str = Tools.nvl((String)(hp_pri.get("filter_str")));
	String mypri = Tools.nvl((String)(hp_pri.get("mypri")));
  if (flag1==null || flag1.equals(""))
  { 
	CachedRowSet crs = (CachedRowSet)request.getAttribute("crs");
	CachedRowSet crs2 = (CachedRowSet)request.getAttribute("crs2");
	
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div  id="man_zone" style="overflow-y:scroll; color:#333; margin:0 auto;text-align:center;">
<div style="width:1000px;border: 1px #999 solid; ">
<form name="formHead" action="./MainServlet_sys" target="_self"  method="post" onKeyDown="if(event.keyCode==13)event.keyCode=9" ><input class="putin" type="hidden" name="fid" value="UII00.00.04.04">
<div style="text-align:center;margin-top:30px;"><div style="font-size:22.0pt;font-family:宋体;text-align:center;">秦皇岛市建设工程安全监督备案表</div></div>
  <div style="margin-top:20px;text-align:right;font-size:12px;width:1000px;">备案编号：<input class="putin"  name="bacode"  id="bacode"/></div>
  <div align="center" style="font-size:13px;width:1000px;"><input type="hidden"  name='sub_code' ondblclick="autofil(this.id)" id="sub_code"/><input type="hidden"  name='mode' ondblclick="autofil(this.id)" id="mode" value=""/>
    <table border=1 cellspacing=0 cellpadding=0 width=1000 style="text-align:left;">
      <tr>
        <td  valign=center>工程名称：<input class="putin"  name='gcname' ondblclick="autofil(this.id)" id="gcname"/></td>
        <td  colspan=1 valign=center>工程地址：<input class="putin" ondblclick="autofil(this.id)" id="gcdz"  name="gcdz" /></td>
        <td  colspan=1 valign=center>纬度：<input class="putin"  id="latitude"  name="latitude" /></td>
        <td  colspan=1 valign=center>经度：<input class="putin"  id="longtitude"  name="longtitude" /></td>
      </tr>
      <tr>
        <td  valign=center>建筑面积：<input class="putin" ondblclick="autofil(this.id)" id="jzmj" name="jzmj">M<sup>2</sup></td>
        <td  valign=center>结构类型：<input class="putin" ondblclick="autofil(this.id)" id="jglx"  name="jglx" /></td>
        <td  colspan=2 valign=center>结构层数: <input class="putin" ondblclick="autofil(this.id)" id="jgcs"  name="jgcs" />层</td>
      </tr>
      <tr>
        <td  valign=center>工程造价：<input class="putin" ondblclick="autofil(this.id)" id="gczj"  name="gczj" />万元</td>
        <td  valign=center>建筑类别：<input class="putin" ondblclick="autofil(this.id)" id="jzlb"  name="jzlb" /></td>
        <td  colspan=2 valign=center>工程个数：<input class="putin" ondblclick="autofil(this.id)" id="gcgs"  name="gcgs" />个</td>
      </tr>
      <tr>
        <td  valign=center>总包安全生产许可证编号：<input class="putin" ondblclick="autofil(this.id)" id="zbsafelic"  name="zbsafelic" /></td>
        <td  rowspan=2 valign=center>基础类型：<input class="putin" ondblclick="autofil(this.id)" id="jclx"  name="jclx" /></td>
        <td  rowspan=2 valign=center>开竣工时间：</td>
		<td> <input class="putin datepicker" id="kgsj"  name="kgsj" /></td>
      </tr>
      <tr>
        <td  valign=center>分包安全生产许可证编号：<input class="putin" ondblclick="autofil(this.id)" id="fbsafelic"  name="fbsafelic" /></td>
		<td><input class="putin datepicker" id="jgsj"  name="jgsj"  /></td>
      </tr>
	  </table>
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
        <td  colspan=3 rowspan=2 valign=center>建设单位:<input class="putin" ondblclick="autofil(this.id)" id="jsdw"  name="jsdw" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_fr_name"  name="js_fr_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_fr_ph"  name="js_fr_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_xm_name"  name="js_xm_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_xm_ph"  name="js_xm_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_js_name"  name="js_js_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_js_ph"  name="js_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_fr_zs"  name="js_fr_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_fr_zsbh"  name="js_fr_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_xm_zs"  name="js_xm_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_xm_zsbh"  name="js_xm_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_js_zs"  name="js_js_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="js_js_zsbh"  name="js_js_zsbh" /></td>
      </tr>
      <tr>
        <td  colspan=3 rowspan=2 valign=center>勘察单位:<input class="putin" ondblclick="autofil(this.id)" id="kcdw"  name="kcdw" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_fr_name"  name="kc_fr_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_fr_ph"  name="kc_fr_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_xm_name"  name="kc_xm_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_xm_ph"  name="kc_xm_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_js_name"  name="kc_js_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_js_ph"  name="kc_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_fr_zs"  name="kc_fr_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_fr_zsbh"  name="kc_fr_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_xm_zs"  name="kc_xm_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_xm_zsbh"  name="kc_xm_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_js_zs"  name="kc_js_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="kc_js_zsbh"  name="kc_js_zsbh" /></td>
      </tr>
      <tr>
		<td  colspan=3 rowspan=2 valign=center>设计单位:<input class="putin" ondblclick="autofil(this.id)" id="sjdw"  name="sjdw" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_fr_name"  name="sj_fr_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_fr_ph"  name="sj_fr_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_xm_name"  name="sj_xm_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_xm_ph"  name="sj_xm_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_js_name"  name="sj_js_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_js_ph"  name="sj_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_fr_zs"  name="sj_fr_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_fr_zsbh"  name="sj_fr_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_xm_zs"  name="sj_xm_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_xm_zsbh"  name="sj_xm_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_js_zs"  name="sj_js_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sj_js_zsbh"  name="sj_js_zsbh" /></td>
	  </tr>
	  <tr>
        <td  colspan=1 rowspan=4>施工单位</td>
        <td  colspan=2 rowspan=2 valign=center>总包:<input class="putin" ondblclick="autofil(this.id)" id="sgzbdw"  name="sgzbdw" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_fr_name"  name="sgzb_fr_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_fr_ph"  name="sgzb_fr_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_xm_name"  name="sgzb_xm_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_xm_ph"  name="sgzb_xm_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_js_name"  name="sgzb_js_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_js_ph"  name="sgzb_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_fr_zs"  name="sgzb_fr_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_fr_zsbh"  name="sgzb_fr_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_xm_zs"  name="sgzb_xm_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_xm_zsbh"  name="sgzb_xm_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_js_zs"  name="sgzb_js_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgzb_js_zsbh"  name="sgzb_js_zsbh" /></td>
	  </tr>
      <tr>
        <td  colspan=2 rowspan=2 valign=center>分包:<input class="putin" ondblclick="autofil(this.id)" id="sgfbdw"  name="sgfbdw" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_fr_name"  name="sgfb_fr_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_fr_ph"  name="sgfb_fr_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_xm_name"  name="sgfb_xm_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_xm_ph"  name="sgfb_xm_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_js_name"  name="sgfb_js_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_js_ph"  name="sgfb_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_fr_zs"  name="sgfb_fr_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_fr_zsbh"  name="sgfb_fr_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_xm_zs"  name="sgfb_xm_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_xm_zsbh"  name="sgfb_xm_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_js_zs"  name="sgfb_js_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="sgfb_js_zsbh"  name="sgfb_js_zsbh" /></td>
	  </tr>
      <tr>
        <td  colspan=3 rowspan=2 valign=center>监理单位:<input class="putin" ondblclick="autofil(this.id)" id="jldw"  name="jldw" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_fr_name"  name="jl_fr_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_fr_ph"  name="jl_fr_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_xm_name"  name="jl_xm_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_xm_ph"  name="jl_xm_ph" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_js_name"  name="jl_js_name" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_js_ph"  name="jl_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_fr_zs"  name="jl_fr_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_fr_zsbh"  name="jl_fr_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_xm_zs"  name="jl_xm_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_xm_zsbh"  name="jl_xm_zsbh" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_js_zs"  name="jl_js_zs" /></td>
        <td  valign=center><input class="putin" ondblclick="autofil(this.id)" id="jl_js_zsbh"  name="jl_js_zsbh" /></td>
	  </tr>
	  </table>
	  <table border=1 cellspacing=0 cellpadding=0 width=1000>
      <tr>
        <td  colspan=1 valign=center>施工单位项目经理</td>
        <td  colspan=1 valign=center><input class="putin" ondblclick="autofil(this.id)" id="sg_xmjl_name"  name="sg_xmjl_name" /></td>
        <td  colspan=1>项目经理资质、号码、身份证号</td>
        <td  colspan=1 valign=center><input class="putin" ondblclick="autofil(this.id)" id="sg_xmjl_zz"  name="sg_xmjl_zz" />、<input class="putin" ondblclick="autofil(this.id)" id="sg_xmjl_ph"  name="sg_xmjl_ph" />、<input class="putin"  ondblclick="autofil(this.id)" id="sg_xmjl_id"  name="sg_xmjl_id" /></td>
      </tr>
      <tr>
        <td  colspan=1>总监理工程师</td>
        <td  colspan=1 valign=center><input class="putin" ondblclick="autofil(this.id)" id="zj_gcs_name"  name="zj_gcs_name" /></td>
        <td  colspan=1>资格证书、号码、身份证号</td>
        <td  colspan=1 valign=center><input class="putin" ondblclick="autofil(this.id)" id="zj_gcs_zz"  name="zj_gcs_zz" />、<input class="putin" ondblclick="autofil(this.id)" id="zj_gcs_ph"  name="zj_gcs_ph" />、<input class="putin" ondblclick="autofil(this.id)" id="zj_gcs_id"  name="zj_gcs_id" /></td>
      </tr>
	  </table>
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
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_name_1"  name="zb_aq_name_1" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_gen_1"  name="zb_aq_gen_1" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_zc_1"  name="zb_aq_zc_1" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_zy_1"  name="zb_aq_zy_1" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_ph_1"  name="zb_aq_ph_1" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_khzh_1"  name="zb_aq_khzh_1" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_name_1"  name="fb_aq_name_1" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_gen_1"  name="fb_aq_gen_1" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_zc_1"  name="fb_aq_zc_1" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_zy_1"  name="fb_aq_zy_1" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_ph_1"  name="fb_aq_ph_1" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_khzh_1"  name="fb_aq_khzh_1" />	</td>
      </tr>
      <tr>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_name_2"  name="zb_aq_name_2" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_gen_2"  name="zb_aq_gen_2" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_zc_2"  name="zb_aq_zc_2" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_zy_2"  name="zb_aq_zy_2" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_ph_2"  name="zb_aq_ph_2" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_khzh_2"  name="zb_aq_khzh_2" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_name_2"  name="fb_aq_name_2" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_gen_2"  name="fb_aq_gen_2" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_zc_2"  name="fb_aq_zc_2" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_zy_2"  name="fb_aq_zy_2" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_ph_2"  name="fb_aq_ph_2" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_khzh_2"  name="fb_aq_khzh_2" />	</td>
      </tr>
      <tr>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_name_3"  name="zb_aq_name_3" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_gen_3"  name="zb_aq_gen_3" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_zc_3"  name="zb_aq_zc_3" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_zy_3"  name="zb_aq_zy_3" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_ph_3"  name="zb_aq_ph_3" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_khzh_3"  name="zb_aq_khzh_3" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_name_3"  name="fb_aq_name_3" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_gen_3"  name="fb_aq_gen_3" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_zc_3"  name="fb_aq_zc_3" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_zy_3"  name="fb_aq_zy_3" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_ph_3"  name="fb_aq_ph_3" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_khzh_3"  name="fb_aq_khzh_3" />	</td>
      </tr>
      <tr>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_name_4"  name="zb_aq_name_4" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_gen_4"  name="zb_aq_gen_4" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_zc_4"  name="zb_aq_zc_4" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_zy_4"  name="zb_aq_zy_4" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_ph_4"  name="zb_aq_ph_4" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="zb_aq_khzh_4"  name="zb_aq_khzh_4" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_name_4"  name="fb_aq_name_4" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_gen_4"  name="fb_aq_gen_4" />	</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_zc_4"  name="fb_aq_zc_4" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_zy_4"  name="fb_aq_zy_4" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_ph_4"  name="fb_aq_ph_4" />		</td>
        <td valign=center> <input class="putin" ondblclick="autofil(this.id)" id="fb_aq_khzh_4"  name="fb_aq_khzh_4" />	</td>
      </tr>
    </table>
  <div style="margin-top:20px;text-align:right;font-size:12px;width:1000px;height:30px;"> 备案日期：<input class="putin" ondblclick="autofil(this.id)" id="barq" name="barq">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 受理人：<input class="putin" ondblclick="autofil(this.id)" id="slr" name="slr"></div>
</div>
</form>
</div>
<div style = "text-align:center">
	<input type="button" name="button" value="确 定" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="reset" name="button" value="重 填" style="width:80;height:23 ">&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" name="button" value="返 回" style="width:80;height:23 " onClick="javascript:history.go(-1);">
</div>
</div>
</body>
     <div id="drag_window">
    	<div id="drag_window_title" >
        	<h4>请从列表中选择相关信息</h4>
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
  <!--百度地图容器-->
  
  <div id="baidumap"  style="border:10px solid #ccc ;position: absolute; z-index: -10; width: 350px; height: 350px;" ondblclick="hidemap()">
  </div>
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
function myFunction1(mode,con,dept,fil){
// alert("in my functrion 1 ");
	loadXMLDoc("./ajax/getsubpar.jsp?_="+Math.round(Math.random()*100000)+"&mode="+mode+"&con="+con+"&dept="+dept,function(){
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			var str = (xmlhttp.responseText);
			var inStr = "";
			str=(str).replace(/[\r|\n]/gm,'');
			//alert(str);
			json = eval("("+str+")");
			
			if(mode=="getdept"){
			for ( i=0;i<json.length;i++){
				inStr=inStr +"<li><a onclick=\"filltab("+i+",'"+fil+"')\">"+json[i].dept_dwlx+"  :  "+json[i].dept_name+"</li>\n";
			}
			inStr="<ul>\n"+inStr+"\n</ul>";
			//alert(inStr);
			 $("#drag_window_main").html(inStr);
			 $("#drag_window_main").css({"overflow-y":"scroll","height":"160px;"});
			 showlist();
			 }
			 
			if(mode=="getemp"){
			for ( i=0;i<json.length;i++){
				inStr=inStr +"<li><a onclick=\"filltab("+i+",'"+fil+"')\">"+json[i].emp_dept+"  :  "+json[i].emp_lb+"  :  "+json[i].emp_name+"  :  "+json[i].cell_pho+"</li>\n";
			}
			inStr="<ul>\n"+inStr+"\n</ul>";
			//alert(inStr);
			 $("#drag_window_main").html(inStr);
			 $("#drag_window_main").css({"overflow-y":"scroll","height":"160px"});
			 showlist();
			 }
		}
	  });
}


</script>
<script>
function $s(s){
	return document.getElementById(s);
	}
function showlist(){
	try{
		//alert($("#drag_window"));
		if($s("drag_window").style.zIndex!="9" && $s("drag_window").style.zIndex!="-9"){
			var mw = $s('drag_window').clientWidth;
			var mh = $s('drag_window').clientHeight;
			var dh = document.body.clientHeight;
			var dw = document.body.clientWidth;
			var dl = $s('man_zone').scrollLeft;
			var dt = $s('man_zone').scrollTop;
			$s('drag_window').style.top=(dh-dt-mh)/2;
			$s('drag_window').style.left=(dw-dl-mw)/2;
			$s('drag_window').style.zIndex="9";
			
		}else{
			$s("drag_window").style.zIndex=$s("drag_window").style.zIndex=="9"?"-9":"9"
		}
	}catch(e){
	alert(e.description );
	}
}
</script>
<script>
$(document).click(function(){
//alert("document click");
	$("#drag_window").css("z-index",-9);
});
$("#drag_window").click(function(){
	return false;
});
</script>
<script>
function filltab(i,fil){
	if(fil=="jsdw"){
		$("#jsdw").val(json[i].dept_name);
		showlist();
	}
	
	if(fil=="jsdwfr"){
		$("#js_fr_name").val(json[i].emp_name);
		$("#js_fr_ph").val(json[i].cell_pho);
		$("#js_fr_zs").val(json[i].fzdw);
		$("#js_fr_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="jsdwxm"){
		$("#js_xm_name").val(json[i].emp_name);
		$("#js_xm_ph").val(json[i].cell_pho);
		$("#js_xm_zs").val(json[i].fzdw);
		$("#js_xm_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="jsdwjs"){
		$("#js_js_name").val(json[i].emp_name);
		$("#js_js_ph").val(json[i].cell_pho);
		$("#js_js_zs").val(json[i].fzdw);
		$("#js_js_zsbh").val(json[i].zsbh);
		showlist();
	}
	//以上 建设单位
	if(fil=="kcdw"){
		$("#kcdw").val(json[i].dept_name);
		showlist();
	}
	
	if(fil=="kcdwfr"){
		$("#kc_fr_name").val(json[i].emp_name);
		$("#kc_fr_ph").val(json[i].cell_pho);
		$("#kc_fr_zs").val(json[i].fzdw);
		$("#kc_fr_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="kcdwxm"){
		$("#kc_xm_name").val(json[i].emp_name);
		$("#kc_xm_ph").val(json[i].cell_pho);
		$("#kc_xm_zs").val(json[i].fzdw);
		$("#kc_xm_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="kcdwjs"){
		$("#kc_js_name").val(json[i].emp_name);
		$("#kc_js_ph").val(json[i].cell_pho);
		$("#kc_js_zs").val(json[i].fzdw);
		$("#kc_js_zsbh").val(json[i].zsbh);
		showlist();
	}
	//以上 勘查单位
	if(fil=="sjdw"){
		$("#sjdw").val(json[i].dept_name);
		showlist();
	}
	
	if(fil=="sjdwfr"){
		$("#sj_fr_name").val(json[i].emp_name);
		$("#sj_fr_ph").val(json[i].cell_pho);
		$("#sj_fr_zs").val(json[i].fzdw);
		$("#sj_fr_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="sjdwxm"){
		$("#sj_xm_name").val(json[i].emp_name);
		$("#sj_xm_ph").val(json[i].cell_pho);
		$("#sj_xm_zs").val(json[i].fzdw);
		$("#sj_xm_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="sjdwjs"){
		$("#sj_js_name").val(json[i].emp_name);
		$("#sj_js_ph").val(json[i].cell_pho);
		$("#sj_js_zs").val(json[i].fzdw);
		$("#sj_js_zsbh").val(json[i].zsbh);
		showlist();
	}
	//以上 设计单位
	if(fil=="sgzbdw"){
		$("#sgzbdw").val(json[i].dept_name);
		showlist();
	}
	
	if(fil=="sgzbdwfr"){
		$("#sgzb_fr_name").val(json[i].emp_name);
		$("#sgzb_fr_ph").val(json[i].cell_pho);
		$("#sgzb_fr_zs").val(json[i].fzdw);
		$("#sgzb_fr_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="sgzbdwxm"){
		$("#sgzb_xm_name").val(json[i].emp_name);
		$("#sgzb_xm_ph").val(json[i].cell_pho);
		$("#sgzb_xm_zs").val(json[i].fzdw);
		$("#sgzb_xm_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="sgzbdwjs"){
		$("#sgzb_js_name").val(json[i].emp_name);
		$("#sgzb_js_ph").val(json[i].cell_pho);
		$("#sgzb_js_zs").val(json[i].fzdw);
		$("#sgzb_js_zsbh").val(json[i].zsbh);
		showlist();
	}
	//以上 施工总包
	if(fil=="sgfbdw"){
		$("#sgfbdw").val(json[i].dept_name);
		showlist();
	}
	
	if(fil=="sgfbdwfr"){
		$("#sgfb_fr_name").val(json[i].emp_name);
		$("#sgfb_fr_ph").val(json[i].cell_pho);
		$("#sgfb_fr_zs").val(json[i].fzdw);
		$("#sgfb_fr_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="sgfbdwxm"){
		$("#sgfb_xm_name").val(json[i].emp_name);
		$("#sgfb_xm_ph").val(json[i].cell_pho);
		$("#sgfb_xm_zs").val(json[i].fzdw);
		$("#sgfb_xm_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="sgfbdwjs"){
		$("#sgfb_js_name").val(json[i].emp_name);
		$("#sgfb_js_ph").val(json[i].cell_pho);
		$("#sgfb_js_zs").val(json[i].fzdw);
		$("#sgfb_js_zsbh").val(json[i].zsbh);
		showlist();
	}
	//以上 施工分包
	if(fil=="jldw"){
		$("#jldw").val(json[i].dept_name);
		showlist();
	}
	
	if(fil=="jldwfr"){
		$("#jl_fr_name").val(json[i].emp_name);
		$("#jl_fr_ph").val(json[i].cell_pho);
		$("#jl_fr_zs").val(json[i].fzdw);
		$("#jl_fr_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="jldwxm"){
		$("#jl_xm_name").val(json[i].emp_name);
		$("#jl_xm_ph").val(json[i].cell_pho);
		$("#jl_xm_zs").val(json[i].fzdw);
		$("#jl_xm_zsbh").val(json[i].zsbh);
		showlist();
	}
	if(fil=="jldwjs"){
		$("#jl_js_name").val(json[i].emp_name);
		$("#jl_js_ph").val(json[i].cell_pho);
		$("#jl_js_zs").val(json[i].fzdw);
		$("#jl_js_zsbh").val(json[i].zsbh);
		showlist();
	}
	//以上 监理
	
	
	changeWidth();
}
</script>
<script>
function autofil(id){
//alert(id);
	var jsdwfr ="js_fr_name;js_fr_ph;js_fr_zs;js_fr_zsbh;"
	var jsdwxm ="js_xm_name;js_xm_ph;js_xm_zs;js_xm_zsbh;"
	var jsdwjs ="js_js_name;js_js_ph;js_js_zs;js_js_zsbh;"
	
	var jsdw = "jsdw;"
	
	if(jsdw.indexOf(id)>-1){
		myFunction1("getdept","建设单位","--","jsdw");
	}
	if (jsdwfr.indexOf(id)>-1){
		if($("#jsdw").val()==""){
			alert("请先选择建设单位");
			return false;
		}else{
			myFunction1("getemp",$("#jsdw").val(),"--","jsdwfr");
		}
	}
	if (jsdwxm.indexOf(id)>-1){
		if($("#jsdw").val()==""){
			alert("请先选择建设单位");
			return false;
		}else{
			myFunction1("getemp",$("#jsdw").val(),"--","jsdwxm");
		}
	}
	if (jsdwjs.indexOf(id)>-1){
		if($("#jsdw").val()==""){
			alert("请先选择建设单位");
			return false;
		}else{
			myFunction1("getemp",$("#jsdw").val(),"--","jsdwjs");
		}
	}
	//以上是建设单位
	
	var kcdwfr ="kc_fr_name;kc_fr_ph;kc_fr_zs;kc_fr_zsbh;"
	var kcdwxm ="kc_xm_name;kc_xm_ph;kc_xm_zs;kc_xm_zsbh;"
	var kcdwjs ="kc_js_name;kc_js_ph;kc_js_zs;kc_js_zsbh;"
	
	var kcdw = "kcdw;"
	
	if(kcdw.indexOf(id)>-1){
		myFunction1("getdept","监管部门","--","kcdw");
	}
	if (kcdwfr.indexOf(id)>-1){
		if($("#kcdw").val()==""){
			alert("请先选择勘查单位");
			return false;
		}else{
			myFunction1("getemp",$("#kcdw").val(),"--","kcdwfr");
		}
	}
	if (kcdwxm.indexOf(id)>-1){
		if($("#kcdw").val()==""){
			alert("请先选择勘查单位");
			return false;
		}else{
			myFunction1("getemp",$("#kcdw").val(),"--","kcdwxm");
		}
	}
	if (kcdwjs.indexOf(id)>-1){
		if($("#kcdw").val()==""){
			alert("请先选择勘查单位");
			return false;
		}else{
			myFunction1("getemp",$("#kcdw").val(),"--","kcdwjs");
		}
	}
	//以上是勘查单位
	var sjdwfr ="sj_fr_name;sj_fr_ph;sj_fr_zs;sj_fr_zsbh;"
	var sjdwxm ="sj_xm_name;sj_xm_ph;sj_xm_zs;sj_xm_zsbh;"
	var sjdwjs ="sj_js_name;sj_js_ph;sj_js_zs;sj_js_zsbh;"
	
	var sjdw = "sjdw;"
	
	if(sjdw.indexOf(id)>-1){
		//\alert("没有设计单位");
		myFunction1("getdept","设计单位","--","sjdw");
	}
	if (sjdwfr.indexOf(id)>-1){
		if($("#sjdw").val()==""){
			alert("请先选择设计单位");
			return false;
		}else{
			myFunction1("getemp",$("#sjdw").val(),"--","sjdwfr");
		}
	}
	if (sjdwxm.indexOf(id)>-1){
		if($("#sjdw").val()==""){
			alert("请先选择设计单位");
			return false;
		}else{
			myFunction1("getemp",$("#sjdw").val(),"--","sjdwxm");
		}
	}
	if (sjdwjs.indexOf(id)>-1){
		if($("#sjdw").val()==""){
			alert("请先选择设计单位");
			return false;
		}else{
			myFunction1("getemp",$("#sjdw").val(),"--","sjdwjs");
		}
	}
	//以上是设计单位
	//以下施工总包
	var sgzbdwfr ="sgzb_fr_name;sgzb_fr_ph;sgzb_fr_zs;sgzb_fr_zsbh;"
	var sgzbdwxm ="sgzb_xm_name;sgzb_xm_ph;sgzb_xm_zs;sgzb_xm_zsbh;"
	var sgzbdwjs ="sgzb_js_name;sgzb_js_ph;sgzb_js_zs;sgzb_js_zsbh;"
	
	var sgzbdw = "sgzbdw;"
	
	if(sgzbdw.indexOf(id)>-1){
		myFunction1("getdept","建筑施工单位","--","sgzbdw");
	}
	if (sgzbdwfr.indexOf(id)>-1){
		if($("#sgzbdw").val()==""){
			alert("请先选择建筑施工单位");
			return false;
		}else{
			myFunction1("getemp",$("#sgzbdw").val(),"--","sgzbdwfr");
		}
	}
	if (sgzbdwxm.indexOf(id)>-1){
		if($("#sgzbdw").val()==""){
			alert("请先选择建筑施工单位");
			return false;
		}else{
			myFunction1("getemp",$("#sgzbdw").val(),"--","sgzbdwxm");
		}
	}
	if (sgzbdwjs.indexOf(id)>-1){
		if($("#sgzbdw").val()==""){
			alert("请先选择建筑施工单位");
			return false;
		}else{
			myFunction1("getemp",$("#sgzbdw").val(),"--","sgzbdwjs");
		}
	}
	//以上是施工总包
	//以下 施工分包
	var sgfbdwfr ="sgfb_fr_name;sgfb_fr_ph;sgfb_fr_zs;sgfb_fr_zsbh;"
	var sgfbdwxm ="sgfb_xm_name;sgfb_xm_ph;sgfb_xm_zs;sgfb_xm_zsbh;"
	var sgfbdwjs ="sgfb_js_name;sgfb_js_ph;sgfb_js_zs;sgfb_js_zsbh;"
	
	var sgfbdw = "sgfbdw;"
	
	if(sgfbdw.indexOf(id)>-1){
		myFunction1("getdept","建筑施工单位","--","sgfbdw");
	}
	if (sgfbdwfr.indexOf(id)>-1){
		if($("#sgfbdw").val()==""){
			alert("请先选择建筑施工单位");
			return false;
		}else{
			myFunction1("getemp",$("#sgfbdw").val(),"--","sgfbdwfr");
		}
	}
	if (sgfbdwxm.indexOf(id)>-1){
		if($("#sgfbdw").val()==""){
			alert("请先选择建筑施工单位");
			return false;
		}else{
			myFunction1("getemp",$("#sgfbdw").val(),"--","sgfbdwxm");
		}
	}
	if (sgfbdwjs.indexOf(id)>-1){
		if($("#sgfbdw").val()==""){
			alert("请先选择建筑施工单位");
			return false;
		}else{
			myFunction1("getemp",$("#sgfbdw").val(),"--","sgfbdwjs");
		}
	}
	//以上是施工分包
	
	//以下 监理单单位
	var jldwfr ="jl_fr_name;jl_fr_ph;jl_fr_zs;jl_fr_zsbh;"
	var jldwxm ="jl_xm_name;jl_xm_ph;jl_xm_zs;jl_xm_zsbh;"
	var jldwjs ="jl_js_name;jl_js_ph;jl_js_zs;jl_js_zsbh;"
	
	var jldw = "jldw;"
	
	if(jldw.indexOf(id)>-1){
		myFunction1("getdept","工程监理单位","--","jldw");
	}
	if (jldwfr.indexOf(id)>-1){
		if($("#jldw").val()==""){
			alert("请先选择工程监理单位");
			return false;
		}else{
			myFunction1("getemp",$("#jldw").val(),"--","jldwfr");
		}
	}
	if (jldwxm.indexOf(id)>-1){
		if($("#jldw").val()==""){
			alert("请先选择工程监理单位");
			return false;
		}else{
			myFunction1("getemp",$("#jldw").val(),"--","jldwxm");
		}
	}
	if (jldwjs.indexOf(id)>-1){
		if($("#jldw").val()==""){
			alert("请先选择工程监理单位");
			return false;
		}else{
			myFunction1("getemp",$("#jldw").val(),"--","jldwjs");
		}
	}
	//以上是监理单位
}
</script>

<script>
function changeWidth() { 
	for(i=0;i<$(".putin").length;i++){
		//$(".putin")[i].css("width","10");
		var len = getByteLen($($(".putin")[i]).val());
		$($(".putin")[i]).css("width",len<4?4*6+12:len*6+12);
	}
}
</script>
<script>
$(".putin").keydown(function(){ 
	var e = event.srcElement;
	//alert(e.id);
	var len = getByteLen($(e).val());
	$(e).css("width",len<4?4*6+12:len*6+12);
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
<script language=javascript>
	function g(s){
		return document.getElementById(s);
	}

	function doSave()
  {
    formHead.target = "submitframe";
	formHead.submit();
	//alert("已提交");
  }

  function doReturn()
  {
	window.top.indexFrame.frames['manFrame'].location = './MainServlet_sys?fid=UII00.00.04.01'
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
			<%
		}
		while(crs2.next()){
		System.out.println("================="+crs2.getString("parname"));
			%>g('<%=crs2.getString("parname")%>').value="<%=crs2.getString("parvalue")%>";
			<%
		}
		
	%>
  
  }
autofill();
changeWidth();
</script>

<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
<%
crs.close();
crs2.close();
}else{
	%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("修改项目成功！");
		window.top.indexFrame.frames['manFrame'].location = 'MainServlet_sys?fid=UII00.00.04.01<%=Integer.parseInt(mypri)==4?"":filter_str%>';
		}
	}
	checknow();
</script>
<%
}

}catch (Exception exception)
{
exception.printStackTrace();
}
%>