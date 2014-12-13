<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=gb2312"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="com.zagerbone.util.DBTrans"
import="com.zagerbone.util.Tools"
import="java.text.*"
import="org.json.*"
import="java.util.Iterator"
import="java.util.Map.Entry"
import="java.util.Set" %><%
System.out.println("查询项目信息");
try{
	String subname = new String(request.getParameter("subname").getBytes("ISO-8859-1"),"utf-8");
	DBTrans dbtrans = new DBTrans(); 
	CachedRowSet crs = null;
	CachedRowSet crs_asso = null;
	CachedRowSet tempcrs = null;
	String sql= "select * from subinfo where gcname like'%"+subname+"%'";
	System.out.println("===手机端查询项目信息==="+sql);
	crs = dbtrans.getResultBySelect(sql);
	
	String 	sgzbdw ="";
	String	sgfbdw ="";
	String	kcdw ="";
	String	sjdw ="";
	String	jldw ="";
	String	jsdw ="";
%>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script src="js/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$(".sub_title").each(function(i){
		var keyword = "<%=subname%>";
		var str = $(this).find("span").html();
		str = str.replace(new RegExp(keyword,"g"),"<font color='darkorange'>"+keyword+"</font>");
		$(this).find("span").html(str);
	});
	$(".sub_title").click(function(){
		$(this).siblings(".sub_cont").slideToggle("fast");
	});
});
</script>
<style>
a{text-decoration:none;}
li{list-style-type:none;
//circle
}
ul{padding:0;margin:0;}
.main_nav{text-align:center;background:#eeaaaa;color:#aa55ee;font-size:20px;margin-bottom:30px;}
.noinfo{width:100%;text-align:center;}
.noinfo div{width:50%;color:darkorange;font-size:30px;display:inline-block;}
.sub{margin-bottom:10px;}
.sub_title{background:lightgray;font-color:darkblue;font-size:24px;text-align:center;}
.sub_cont{width:100%;text-align:left;margin-bottom:20px;display:none;}
.sub_item{width:100%;display:inline-block;background:#aaaaee;margin-top:5px;}
</style>
<!--父text-align:center 与 子 display:inline-block;才能使div居中-->
</head>
<body bgcolor="#dddddd" width=100% height=100%>
<div class="main_nav" >
	<span><a href="mainqry.jsp">快捷查询<a></span>
	<span><a>&gt;&gt;<a></span>
	<span><a >项目查询<a></span>
</div>
<%
 if(crs.size()==0){
 %><div class="noinfo"><div>没有查询到相应的信息</div></div><%
 }
 String parname="";
 String xqzg="";
 String tgzg="";
 while(crs.next()){
	sql="select count(id) as exp from xmzg where xmcode='"+crs.getString("sub_code")+"' and zglb='限期整改' ";
	tempcrs= dbtrans.getResultBySelect(sql);
	tempcrs.next();
	xqzg = tempcrs.getString("exp");
	
	sql="select count(id) as exp from xmzg where xmcode='"+crs.getString("sub_code")+"' and zglb='停工整改' ";
	tempcrs= dbtrans.getResultBySelect(sql);
	tempcrs.next();
	tgzg = tempcrs.getString("exp");
	
	sql="select * from subinfoasso where sub_code='"+crs.getString("sub_code")+"'";
	crs_asso = dbtrans.getResultBySelect(sql);
	
	
	while(crs_asso.next()){
		parname = crs_asso.getString("parname");
		if(parname.equals("sjdw")){
			sjdw=crs_asso.getString("parvalue");
		}else if(parname.equals("jsdw")){
			jsdw=crs_asso.getString("parvalue");
		}else if(parname.equals("kcdw")){
			kcdw=crs_asso.getString("parvalue");
		}else if(parname.equals("jldw")){
			jldw=crs_asso.getString("parvalue");
		}else if(parname.equals("sgzbdw")){
			sgzbdw=crs_asso.getString("parvalue");
		}else if(parname.equals("sgfbdw")){
			sgfbdw=crs_asso.getString("parvalue");
		}
	}
	
 %>
 
<div class="sub">
	<div class="sub_title"><span><%=crs.getString("gcname")%></span></div>
	<div class="sub_cont">
		<div class="sub_item"><span>工程地址：</span><span><%=crs.getString("gcdz")%></span></div>
		<div class="sub_item"><span>开工时间：</span><span><%=crs.getString("kgsj")%></span></div>
		<div class="sub_item"><span>竣工时间：</span><span><%=crs.getString("jgsj")%></span></div>
		<div class="sub_item"><span>建设单位：</span><span><%=jsdw%></span></div>
		<div class="sub_item"><span>勘察单位：</span><span><%=kcdw%></span></div>
		<div class="sub_item"><span>设计单位：</span><span><%=sjdw%></span></div>
		<div class="sub_item"><span>施工总包单位：</span><span><%=sgzbdw%></span></div>
		<div class="sub_item"><span>施工分包单位：</span><span><%=sgfbdw%></span></div>
		<div class="sub_item"><span>监理单位：</span><span><%=jldw%></span></div>
		<div class="sub_item"><span>限期整改次数：</span><span><%=xqzg%></span></div>
		<div class="sub_item"><span>停工整改次数：</span><span><%=tgzg%></span></div>
	</div>
</div>
<%}%>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();}%>