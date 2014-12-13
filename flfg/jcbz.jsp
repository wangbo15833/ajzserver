<%@ page contentType="text/html; charset=utf-8"
import="com.zagerbone.user.User"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="com.zagerbone.util.DBTrans"
import="com.zagerbone.util.Tools"
import="java.text.*"
import="org.json.*"
import ="java.util.Iterator"
import ="java.util.Map.Entry"
import ="java.util.Set"
%>
<%@ include  file='../identification.jsp' %> 
<%
try{
	//String mode = Tools.GBK(request.getParameter("mode"));
	//String dept = new String(request.getParameter("dept").getBytes("ISO-8859-1"),"utf-8"); 
	//String realname = new String(request.getParameter("realname").getBytes("ISO-8859-1"),"utf-8"); 
	DBTrans dbtrans = new DBTrans(); 
	CachedRowSet crs = null;
	// System.out.println("ajax in === getcontact in ===mode is :"+mode);
	// sqlcon = "( work_unit like '%"+dept+"%' and realname like '%"+realname+"%' )";
	
		//JSONObject jo_dept= new JSONObject();
		JSONArray jsona = new JSONArray();
		JSONObject jsono= new JSONObject();
		// String sql= "select username,realname,work_unit from userinfo where 1=1 and "+sqlcon+"  order by work_unit ";
		// System.out.println("=======getcontact sql is ======="+sql);
		// crs = dbtrans.getResultBySelect(sql);
		// while(crs.next()){
			// jsono= new JSONObject();
			// jsono.put("account",crs.getString("username"));
			// jsono.put("dept",crs.getString("work_unit"));
			// jsono.put("realname",crs.getString("realname"));
			// jsona.put(jsono);
		// }
		// System.out.println(jsona.toString());
		

	// dbtrans = null ;
	Boolean flag1=true;
	String titleName="检查标准";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"  href="../css/common.css" type="text/css" />
<script language="JScript" type="text/javascript"  src="../js/jquery.min.js"> </script>
<style>
#main_zone,#slave_zone{
float:left;
}
#main_zone input{
width:80px;
}
</style>
</head>
<%
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
	<div id='main_zone'>
		<div>
			<span>源文本
			</span>
			<span>
				<textarea id='txtsrc'></textarea>			
			</span>			
		</div>
		<div>
			<span><input type='input' id='pattern1'/><input type='button' id='btn1' value='条件1'/></span>
			<span><input type='input' id='pattern2'/><input type='button' id='btn2' value='条件2'/></span>
			<span><input type='input' id='pattern3'/><input type='button' id='btn3' value='分析文本'/></span>
			<span><input type='input' id='pattern4'/><input type='button' id='btn4' value='button3'/></span>
		</div>
		<div>
			<span>编译后文本
			</span>
			<span>
				<textarea id='txtdis'></textarea>			
			</span>			
		</div>
	</div>
<div id="slave_zone">
	<form name="formHead" action="./MainServlet_sys" target="_self" method="post" >
	<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
		<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
			<tr>
				<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
			</tr>
			<tr>
				<td align="center" colspan="4">
					<input id="send"  type="button" name="button" value="修 改" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;
					<input type="button" name="button" value="另 存" style="width:80;height:23 " onClick="doNewSave();">&nbsp;&nbsp;
					<input type="button" name="button" value="取 消" style="width:80;height:23 " onClick="javascript:history.go(-1);">
				</td>
			</tr>
		</tr>
		</table>
	</form>
</div>
</div>
</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
</iframe> 
<script language=javascript>
  function doSave()
  {
    if(!window.confirm("是否将修改的消息重新下发给列表中的收件人？") && !document.getElementById('rec_name').value==""){
  return;
  }
  if(document.getElementById('rec_name').value==""){
	if(!window.confirm("收件人列表为空，消息将从原有收件人的消息列表中删除，确定要修改吗？")){
		return;
	}
  }
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
	document.getElementById("fid").value="UII00.07.02.03";
	formHead.submit();
	//alert("已提交");
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
  }
	
</script>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("修改成功！");
		//window.top.indexFrame.frames['manFrame'].location = 'MainServlet_sys?fid=UII00.07.02.01';
		}
	}
</script>
<script>
$(document).ready(function(){
	$("#btn1").click(function(){
		var par= new RegExp($("#pattern1").val());
		var result = par.test($("#txtsrc").val());
		$("#txtdis").val(result);
	});

	$("#btn3").click(function(){
		$("#txtdis").val("");
		var strsrc = $("#txtsrc").val();
		var line  = new Array();
		var temline =new Array();
		temline= strsrc.split("\n");
		for(i =0 ;i<temline.length;i++){
			if(temline[i]!=""){
				line.push(temline[i]);
			}
		}
		var par1= new RegExp($("#pattern1").val());
		var par2 =new RegExp($("#pattern2").val());
		var par3 =new RegExp($("#pattern3").val());
		var par4 =new RegExp($("#pattern4").val());
		var curline;
		var nextline;
		for (i =0;i<line.length;i++){
			curline=line[i];
			if(i<line.length-1){
				nextline=line[i+1];
			}else{
				nextline="";
			}
			if(par1.test(curline)&&!par2.test(curline)&&par3.test(nextline)&&!par4.test(nextline)){
				$("#txtdis").val($("#txtdis").val()+curline+"=="+par4.test(nextline)+"--\n");
			}
		}
	});
});
</script>
<%
}
catch (Exception exception)
{
exception.printStackTrace();
}
%>

