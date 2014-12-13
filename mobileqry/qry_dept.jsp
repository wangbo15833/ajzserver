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
	String deptname = new String(request.getParameter("deptname").getBytes("ISO-8859-1"),"utf-8");
	DBTrans dbtrans = new DBTrans(); 
	CachedRowSet crs = null;
	CachedRowSet tempcrs = null;
	String sql= "select * from dept where dept_name like'%"+deptname+"%'";
	System.out.println("===手机端查询单位信息==="+sql);
	crs = dbtrans.getResultBySelect(sql);
%>
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<script src="js/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$(".sub_title").each(function(i){
		var keyword = "<%=deptname%>";
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
	<span><a >单位查询<a></span>
</div>
<%
 if(crs.size()==0){
 %><div class="noinfo"><div>没有查询到相应的信息</div></div><%
 }
Map<String,Integer> mapxq = new HashMap<String ,Integer>();
Map<String,Integer> maptg = new HashMap<String ,Integer>();
String gcname="";
String zglb ="";
int tempi=0;
 while(crs.next()){
 mapxq.clear();
 maptg.clear();
 %>
 
<div class="sub">
	<div class="sub_title"><span><%=crs.getString("dept_name")%></span></div>
	<div class="sub_cont">
		<div class="sub_item"><span>单位类型：</span><span><%=crs.getString("dept_dwlx")%></span></div>
		<div class="sub_item"><span>单位地址（邮编）：</span><span><%=crs.getString("dept_addr")%></span></div>
		<div class="sub_item"><span>联系电话：</span><span><%=crs.getString("con_phone")%></span></div>
		<div class="sub_item"><span>成立时间：</span><span><%=crs.getString("est_date")%></span></div>
		<div class="sub_item"><span>经济类型：</span><span><%=crs.getString("com_type")%></span></div>
		<%
		String sqlstr= "select distinct(s.id),s.gcname,s.sub_code,zg.id as zgid, zg.zglb from subinfo as s "+
			"left join subinfoasso as ao "+
			"on  s.sub_code = ao.sub_code "+
			"left join xmzg as zg "+
			"on  zg.xmcode = s.sub_code "+
			"where (ao.parname='sgzbdw' or ao.parname='sgfbdw'  or ao.parname='jldw' "+
			" or ao.parname='kcdw'  or ao.parname='sjdw'  or ao.parname='jsdw' ) "+
			"and ao.parvalue='"+crs.getString("dept_name")+"' order by s.sub_code";
		System.out.println(sqlstr);
		tempcrs = dbtrans.getResultBySelect(sqlstr);
		while(tempcrs.next()){
			gcname = tempcrs.getString("gcname");
			zglb = Tools.nvl(tempcrs.getString("zglb"));
			if(!mapxq.containsKey(gcname) && !maptg.containsKey(gcname)){
				mapxq.put(gcname,0);
				maptg.put(gcname,0);
			}
			
			if(zglb.equals("限期整改")){
				tempi =mapxq.get(gcname);
				tempi++;
				mapxq.put(gcname,tempi);
			}else if (zglb.equals("停工整改")){
				tempi =mapxq.get(gcname);
				tempi++;
				maptg.put(gcname,tempi);
			}
		}
		for( String key: mapxq.keySet()){
			%>
			<div class="sub_item"><div><span>参与项目：</span><span><%=key%></span></div><div><span>限期整改：</span><span><%=mapxq.get(key)%>次;&nbsp;&nbsp;</span><span>停工整改：</span><span><%=maptg.get(key)%>次;&nbsp;&nbsp;</span></div></div><%
		}
		%>
		<%%>
	</div>
</div>
<%}%>
</body>
</html>
<%}catch(Exception e){e.printStackTrace();}%>