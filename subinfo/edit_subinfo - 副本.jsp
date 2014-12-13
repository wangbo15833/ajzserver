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
<%@ include  file='../identification.jsp' %> 
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>
<link href="../css/calendar-win2k-cold-1.css" rel=stylesheet type=text/css>
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<link rel="stylesheet"  href="css/001.css"  										type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 									type=text/css>
<script language="JavaScript" src="../share/js/utils.js"></script>
<script language="JScript" type="text/javascript"  src="./js/calendar.js"> </script>
</head>
<style>
input{
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
 }
</style> 

<%
String titleName="项目信息修改";
String flag1 =(String)request.getAttribute("flag1"); 
try{

	HashMap hp_pri = (HashMap)(request.getAttribute("hp_pri"));
	System.out.println(hp_pri.toString());
	String filter_str = Tools.nvl((String)(hp_pri.get("filter_str")));
	String mypri = Tools.nvl((String)(hp_pri.get("mypri")));
  if (flag1==null || flag1.equals(""))
  {  
	 flag1 ="false" ;
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

	
	
	while(crs_cols.next()){
		if(Tools.nvl(crs_cols.getString("deta_disp")).equals("1")){	
			al_colname.add((String)crs_cols.getString("colname"));
			al_colnamec.add((String)crs_cols.getString("colnamec"));
			al_datasrc.add((String)crs_cols.getString("datasrc"));
			al_datasrctype.add((String)crs_cols.getString("datasrctype"));
		}
	}
 %>
 <!--/////////////////////////////////-->

<body style="overflow-y:scroll;">
<div  id="man_zone" style="overflow-y:scroll; color:#333; margin:0 auto;text-align:center;">
<div style="width:1000px;border: 1px #999 solid; ">
<div style="text-align:center;margin-top:30px;"><div style="font-size:22.0pt;font-family:宋体;text-align:center;">秦皇岛市建设工程安全监督备案表</div></div>
  <div style="margin-top:20px;text-align:right;font-size:12px;width:1000px;">备案编号：<input  name="bacode"  id="bacode"/></div>
  <div align="center" style="font-size:13px;width:1000px;">
    <table border=1 cellspacing=0 cellpadding=0 width=1000 style="text-align:left;">
      <tr>
        <td  valign=center>工程名称：<input  name='gcname' onclick="autofil(this.id)" id="gcname"/></td>
        <td  colspan=3 valign=center>工程地址：<input onclick="autofil(this.id)" id="gcdz"  name="gcdz" /></td>
      </tr>
      <tr>
        <td  valign=center>建筑面积：<input onclick="autofil(this.id)" id="jzmj" name="jzmj">M<sup>2</sup></td>
        <td  valign=center>结构类型：<input onclick="autofil(this.id)" id="jglx"  name="jglx" /></td>
        <td  colspan=2 valign=center>结构层数: <input onclick="autofil(this.id)" id="jgcs"  name="jgcs" />层</td>
      </tr>
      <tr>
        <td  valign=center>工程造价：<input onclick="autofil(this.id)" id="gczj"  name="gczj" />万元</td>
        <td  valign=center>建筑类别：<input onclick="autofil(this.id)" id="jzlb"  name="jzlb" /></td>
        <td  colspan=2 valign=center>工程个数：<input onclick="autofil(this.id)" id="gcgs"  name="gcgs" />个</td>
      </tr>
      <tr>
        <td  valign=center>总包安全生产许可证编号：<input onclick="autofil(this.id)" id="zbsafelic"  name="zbsafelic" /></td>
        <td  rowspan=2 valign=center>基础类型：<input onclick="autofil(this.id)" id="jclx"  name="jclx" /></td>
        <td  rowspan=2 valign=center>开竣工时间：</td>
		<td> <input onclick="autofil(this.id)" id="kgsj"  name="kgsj" /></td>
      </tr>
      <tr>
        <td  valign=center>分包安全生产许可证编号：<input onclick="autofil(this.id)" id="fbsafelic"  name="fbsafelic" /></td>
		<td><input onclick="autofil(this.id)" id="jgsj"  name="jgsj" /></td>
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
        <td  colspan=3 rowspan=2 valign=center>建设单位:<input onclick="autofil(this.id)" id="jsdw"  name="jsdw" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_fr_name"  name="js_fr_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_fr_ph"  name="js_fr_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_xm_name"  name="js_xm_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_xm_ph"  name="js_xm_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_js_name"  name="js_js_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_js_ph"  name="js_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input onclick="autofil(this.id)" id="js_fr_zs"  name="js_fr_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_fr_zsbh"  name="js_fr_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_xm_zs"  name="js_xm_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_xm_zsbh"  name="js_xm_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_js_zs"  name="js_js_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="js_js_zsbh"  name="js_js_zsbh" /></td>
      </tr>
      <tr>
        <td  colspan=3 rowspan=2 valign=center>勘察单位:<input onclick="autofil(this.id)" id="kcdw"  name="kcdw" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_fr_name"  name="kc_fr_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_fr_ph"  name="kc_fr_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_xm_name"  name="kc_xm_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_xm_ph"  name="kc_xm_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_js_name"  name="kc_js_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_js_ph"  name="kc_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_fr_zs"  name="kc_fr_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_fr_zsbh"  name="kc_fr_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_xm_zs"  name="kc_xm_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_xm_zsbh"  name="kc_xm_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_js_zs"  name="kc_js_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="kc_js_zsbh"  name="kc_js_zsbh" /></td>
      </tr>
      <tr>
		<td  colspan=3 rowspan=2 valign=center>设计单位:<input onclick="autofil(this.id)" id="sjdw"  name="sjdw" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_fr_name"  name="sj_fr_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_fr_ph"  name="sj_fr_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_xm_name"  name="sj_xm_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_xm_ph"  name="sj_xm_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_js_name"  name="sj_js_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_js_ph"  name="sj_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_fr_zs"  name="sj_fr_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_fr_zsbh"  name="sj_fr_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_xm_zs"  name="sj_xm_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_xm_zsbh"  name="sj_xm_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_js_zs"  name="sj_js_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sj_js_zsbh"  name="sj_js_zsbh" /></td>
	  </tr>
	  <tr>
        <td  colspan=2 rowspan=4>施工单位</td>
        <td  rowspan=2 valign=center>总包:<input onclick="autofil(this.id)" id="sgzbdw"  name="sgzbdw" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_fr_name"  name="sgzb_fr_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_fr_ph"  name="sgzb_fr_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_xm_name"  name="sgzb_xm_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_xm_ph"  name="sgzb_xm_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_js_name"  name="sgzb_js_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_js_ph"  name="sgzb_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_fr_zs"  name="sgzb_fr_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_fr_zsbh"  name="sgzb_fr_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_xm_zs"  name="sgzb_xm_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_xm_zsbh"  name="sgzb_xm_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_js_zs"  name="sgzb_js_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgzb_js_zsbh"  name="sgzb_js_zsbh" /></td>
	  </tr>
      <tr>
        <td  colspan=1 rowspan=2 valign=center>分包:<input onclick="autofil(this.id)" id="sgfbdw"  name="sgfbdw" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_fr_name"  name="sgfb_fr_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_fr_ph"  name="sgfb_fr_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_xm_name"  name="sgfb_xm_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_xm_ph"  name="sgfb_xm_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_js_name"  name="sgfb_js_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_js_ph"  name="sgfb_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_fr_zs"  name="sgfb_fr_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_fr_zsbh"  name="sgfb_fr_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_xm_zs"  name="sgfb_xm_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_xm_zsbh"  name="sgfb_xm_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_js_zs"  name="sgfb_js_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="sgfb_js_zsbh"  name="sgfb_js_zsbh" /></td>
	  </tr>
      <tr>
        <td  colspan=3 rowspan=2 valign=center>监理单位<input onclick="autofil(this.id)" id="jldw"  name="jldw" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_fr_name"  name="jl_fr_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_fr_ph"  name="jl_fr_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_xm_name"  name="jl_xm_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_xm_ph"  name="jl_xm_ph" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_js_name"  name="jl_js_name" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_js_ph"  name="jl_js_ph" /></td>
      </tr>
      <tr>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_fr_zs"  name="jl_fr_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_fr_zsbh"  name="jl_fr_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_xm_zs"  name="jl_xm_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_xm_zsbh"  name="jl_xm_zsbh" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_js_zs"  name="jl_js_zs" /></td>
        <td  valign=center><input onclick="autofil(this.id)" id="jl_js_zsbh"  name="jl_js_zsbh" /></td>
	  </tr>
      <tr>
        <td  colspan=2 valign=center>施工单位项目经理</td>
        <td  colspan=3 valign=center><input onclick="autofil(this.id)" id="sg_xmjl_name"  name="sg_xmjl_name" /></td>
        <td  colspan=2>项目经理资质、号码、身份证号</td>
        <td  colspan=2 valign=center><input onclick="autofil(this.id)" id="sg_xmjl_zz"  name="sg_xmjl_zz" /><input onclick="autofil(this.id)" id="sg_xmjl_ph"  name="sg_xmjl_ph" /><input onclick="autofil(this.id)" id="sg_xmjl_id"  name="sg_xmjl_id" /></td>
      </tr>
      <tr>
        <td  colspan=2>总监理工程师</td>
        <td  colspan=3 valign=center><input onclick="autofil(this.id)" id="sg_xmjl_name"  name="sg_xmjl_name" /></td>
        <td  colspan=2>资格证书、号码、身份证号</td>
        <td  colspan=2 valign=center><input onclick="autofil(this.id)" id="zj_gcs_zz"  name="zj_gcs_zz" /><input onclick="autofil(this.id)" id="zj_gcs_ph"  name="zj_gcs_ph" /><input onclick="autofil(this.id)" id="zj_gcs_id"  name="zj_gcs_id" /></td>
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
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_name_1"  name="zb_aq_name_1" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_gen_1"  name="zb_aq_gen_1" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_zc_1"  name="zb_aq_zc_1" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_zy_1"  name="zb_aq_zy_1" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_ph_1"  name="zb_aq_ph_1" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_khzh_1"  name="zb_aq_khzh_1" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_name_1"  name="fb_aq_name_1" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_gen_1"  name="fb_aq_gen_1" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_zc_1"  name="fb_aq_zc_1" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_zy_1"  name="fb_aq_zy_1" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_ph_1"  name="fb_aq_ph_1" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_khzh_1"  name="fb_aq_khzh_1" />	</td>
      </tr>
      <tr>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_name_2"  name="zb_aq_name_2" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_gen_2"  name="zb_aq_gen_2" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_zc_2"  name="zb_aq_zc_2" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_zy_2"  name="zb_aq_zy_2" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_ph_2"  name="zb_aq_ph_2" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_khzh_2"  name="zb_aq_khzh_2" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_name_2"  name="fb_aq_name_2" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_gen_2"  name="fb_aq_gen_2" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_zc_2"  name="fb_aq_zc_2" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_zy_2"  name="fb_aq_zy_2" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_ph_2"  name="fb_aq_ph_2" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_khzh_2"  name="fb_aq_khzh_2" />	</td>
      </tr>
      <tr>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_name_3"  name="zb_aq_name_3" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_gen_3"  name="zb_aq_gen_3" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_zc_3"  name="zb_aq_zc_3" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_zy_3"  name="zb_aq_zy_3" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_ph_3"  name="zb_aq_ph_3" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_khzh_3"  name="zb_aq_khzh_3" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_name_3"  name="fb_aq_name_3" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_gen_3"  name="fb_aq_gen_3" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_zc_3"  name="fb_aq_zc_3" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_zy_3"  name="fb_aq_zy_3" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_ph_3"  name="fb_aq_ph_3" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_khzh_3"  name="fb_aq_khzh_3" />	</td>
      </tr>
      <tr>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_name_4"  name="zb_aq_name_4" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_gen_4"  name="zb_aq_gen_4" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_zc_4"  name="zb_aq_zc_4" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_zy_4"  name="zb_aq_zy_4" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_ph_4"  name="zb_aq_ph_4" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="zb_aq_khzh_4"  name="zb_aq_khzh_4" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_name_4"  name="fb_aq_name_4" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_gen_4"  name="fb_aq_gen_4" />	</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_zc_4"  name="fb_aq_zc_4" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_zy_4"  name="fb_aq_zy_4" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_ph_4"  name="fb_aq_ph_4" />		</td>
        <td valign=center> <input onclick="autofil(this.id)" id="fb_aq_khzh_4"  name="fb_aq_khzh_4" />	</td>
      </tr>
    </table>
  <div style="margin-top:20px;text-align:right;font-size:12px;width:1000px;height:30px;"> 备案日期：<input onclick="autofil(this.id)" id="barq" name="barq">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 受理人：<input onclick="autofil(this.id)" id="slr" name="slr"></div>
</div>
</div>
</div>
</body>
</html>
<script>
function autofil(id)
{
	
}
</script>
<!--/////////////////////////////////-->
<!--body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div  id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="self"  method="post"  onKeyDown="if(event.keyCode==13)event.keyCode=9"  >
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<input type="hidden" name="fid"  id="fid" value="UII00.00.04.04"><input type="hidden" name=" id"  id=" id" value="<%=crs.getString(" id")%>">
		<%
	int ik = 0;
	while(ik<al_colname.size()){
			if(ik%2==0){
		%>
	<tr><%}%>
		
		<%if(((String)al_datasrctype.get(ik)).replaceAll(" ","").equals("") ||((String)al_datasrctype.get(ik)).equals("check")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" name="<%=al_colname.get(ik).toString()%>"  id="<%=al_colname.get(ik).toString()%>"  size="25"<%if (al_colname.get(ik).toString().equals(" id")){%> readonly="readonly"  style="background-color:#CCC" <%}%>value="<%=Tools.nvl(crs.getString(al_colname.get(ik).toString()))%>"/>&nbsp;&nbsp;</td>
		<%}else if(((String)al_datasrctype.get(ik)).equals("popList")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><select  id="<%=al_colname.get(ik).toString()%>"  name="<%=al_colname.get(ik).toString()%>" style="width:190px;">
			<option    value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
			<%=com.zagerbone.util.Tools.popListWithSelect((String)al_datasrc.get(ik),Tools.nvl(crs.getString(al_colname.get(ik).toString())))%>
			</select></td>
		<%}else if(((String)al_datasrctype.get(ik)).equals("getmap")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text"   id="<%=al_colname.get(ik).toString()%>"  name="<%=al_colname.get(ik).toString()%>"  id="<%=al_colname.get(ik).toString()%>" ondblclick="showmap()" size="25" value="<%=Tools.nvl(crs.getString(al_colname.get(ik).toString()))%>"/>双击 打开/隐藏 地图</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("calendar")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" onfocus="calendar()"   id="<%=al_colname.get(ik).toString()%>"   name="<%=al_colname.get(ik).toString()%>" size="25" value="<%=Tools.nvl(crs.getString(al_colname.get(ik).toString()))%>"/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("textarea")){
		if(ik%2==1){
		%><td  width="9%"></td><td  width="41%"></td></tr><%
		}		
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td colspan="3" width="91%"><textarea cols="100%" rows="10"  id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25"><%=(Tools.nvl(crs.getString(al_colname.get(ik).toString()))).replaceAll("%40","\r\n")%></textarea>&nbsp;&nbsp;</td> 
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
		<input type="button" name="button" value="修 改" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;
		<input type="button" name="button" value="另 存" style="width:80;height:23 " onClick="doNewSave();">&nbsp;&nbsp;
		<input type="button" name="button" value="取 消" style="width:80;height:23 " onClick="javascript:history.go(-1);">
	</td>
  </tr>
</table>
</form>
</div>
  <!--百度地图容器->
  
  <div  id="baidumap"  style="border:10px solid #ccc ;position: absolute;top:200px;left:200px;z-index: -10; width: 350px; height: 350px;" ondblclick="hidemap()">
  </div>
 <div>
</body>
</html>
<iframe  id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
</iframe --> 
<script language=javascript>
  function doSave()
  {
  if(document.getElementById("cont")!=null){
	s=document.getElementById("cont").innerHTML;
	s=s.replace(/[\r\n]/gm,"%40");
	document.getElementById("cont").value=s;}
    formHead.target = "submitframe";
	formHead.submit();
	//alert("已提交");
  }
  function doNewSave()
  {
  if(document.getElementById("cont")!=null){
	s=document.getElementById("cont").innerHTML;
	s=s.replace(/[\r\n]/gm,"%40");
	document.getElementById("cont").value=s;}
    formHead.target = "submitframe";
	document.getElementById("fid").value="UII00.00.04.03";
	formHead.submit();
	//alert("已提交");
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
  }
  function  checknow()
  {
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
			document.getElementById('<%=key%>').disabled='disabled';
		}<%
		}
		}
		%>
  
  }
  checknow();
  checkfilter();
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>

<%
crs.close();
crs_cols.close();
}else{
	%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("修改成功！");
		window.top.indexFrame.frames['manFrame'].location = 'MainServlet_sys?fid=UII00.00.04.01<%=Integer.parseInt(mypri)==4?"":filter_str%>';
		}
	}
	checknow();
</script>
<%
}

}
catch (Exception exception)
{
exception.printStackTrace();
}
%>