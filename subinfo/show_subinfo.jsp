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
.putin{
width:50px;
background:#ffffff;
border-bottom-color:#000;
 border-bottom-width:0px;
 border-top-width:0px;
 border-left-width:0px;
 border-right-width:0px; solid #ff6633;
 color: #000000; FONT-SIZE: 9pt;
 FONT-STYLE: normal;
 FONT-VARIANT: normal;
 FONT-WEIGHT: normal;
 HEIGHT: 18px;
 LINE-HEIGHT: normal;
 }
</style> 
</head>


<%
String titleName="详细信息";
try{
	CachedRowSet crs = (CachedRowSet)request.getAttribute("crs");
	CachedRowSet crs2 = (CachedRowSet)request.getAttribute("crs2");
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div  id="man_zone" style="overflow-y:scroll; color:#333; margin:0 auto;text-align:center;">
<div style="width:1000px;border: 1px #999 solid; ">
<div style="text-align:center;margin-top:30px;"><div style="font-size:22.0pt;font-family:宋体;text-align:center;">秦皇岛市建设工程安全监督备案表</div></div>
  <div style="margin-top:20px;text-align:right;font-size:12px;width:1000px;">备案编号：<input class="putin"  name="bacode"  id="bacode"/></div>
  <div align="center" style="font-size:13px;width:1000px;"><input type="hidden"  name='sub_code'  id="sub_code"/>
    <table border=1 cellspacing=0 cellpadding=0 width=1000 style="text-align:left;">
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
  <div style="margin-top:20px;text-align:right;font-size:12px;width:1000px;height:30px;"> 备案日期：<input class="putin"  id="barq" name="barq">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 受理人：<input class="putin"  id="slr" name="slr"></div>
</div>
</div>
<div style = "text-align:center">
<input type="button" name="button" value="返 回" style="width:80;height:23 " onClick="javascript:history.go(-1);">
</div>
</div>
</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
</iframe>

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
		$($(".putin")[i]).css("width",len<4?4*6+12:len*6+12);
	}
}
</script>
<script language=javascript>
	function g(s){
		return document.getElementById(s);
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
			
			g('<%=ai[i]%>').disabled=false;
			g('<%=ai[i]%>').size=g('<%=ai[i]%>').value.length;
			<%
		}
		while(crs2.next()){
		System.out.println("================="+crs2.getString("parname"));
			%>g('<%=crs2.getString("parname")%>').value="<%=crs2.getString("parvalue")%>";
			g('<%=crs2.getString("parname")%>').disabled=false;
			g('<%=crs2.getString("parname")%>').size=(g('<%=crs2.getString("parname")%>').value.length>5?g('<%=crs2.getString("parname")%>').value.length:5);
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