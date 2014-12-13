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
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
</head>


<%
String titleName="消息修改";
String flag1 =(String)request.getAttribute("flag1"); 
try{

	
  HashMap hp_pri = (HashMap)request.getAttribute("hp_pri");
  String mypri = (String)(hp_pri.get("mypri"));
	System.out.println(hp_pri.toString());
	String filter_str = Tools.nvl((String)(hp_pri.get("filter_str")));
  if (flag1==null || flag1.equals(""))
  {  
	 flag1 ="false" ;
	 /*  EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
	  Query queryCon = (Query)eventResponse.getBody(); 
	  CachedRowSet crs = null;
	  if(queryCon!=null)
	  {
		crs = (CachedRowSet)queryCon.getConditionBody();
	  } */
	  
	CachedRowSet crs = (CachedRowSet)request.getAttribute("crs_byid");
		crs.next();
	CachedRowSet crs_cols = (CachedRowSet)request.getAttribute("crs_cols");
	ArrayList al_colname = new ArrayList();
	ArrayList al_colnamec = new ArrayList();
	ArrayList al_datasrc = new ArrayList();
	ArrayList al_datasrctype = new ArrayList();
	
	try{
		crs.getString("msgtype");
		while(crs_cols.next()){
			if(Tools.nvl(crs_cols.getString("deta_disp")).equals("1")){	
				al_colname.add(Tools.nvl((String)crs_cols.getString("colname")));
				al_colnamec.add(Tools.nvl((String)crs_cols.getString("colnamec")));
				al_datasrc.add(Tools.nvl((String)crs_cols.getString("datasrc")));
				al_datasrctype.add(Tools.nvl((String)crs_cols.getString("datasrctype")));
			}
		}
	}catch(Exception e){
		al_colname.add("ggcode");
		al_colnamec.add("公告编号");
		al_datasrc.add("");
		al_datasrctype.add("");
		
		al_colname.add("title");
		al_colnamec.add("标题");
		al_datasrc.add("");
		al_datasrctype.add("");
		
		al_colname.add("gg_time");
		al_colnamec.add("发布时间");
		al_datasrc.add("");
		al_datasrctype.add("");
		
		al_colname.add("auth");
		al_colnamec.add("发布人");
		al_datasrc.add("");
		al_datasrctype.add("");
		
		al_colname.add("cont");
		al_colnamec.add("内容");
		al_datasrc.add("");
		al_datasrctype.add("textarea");
		
		
	}
 %>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="_self"  method="post"  onKeyDown="if(event.keyCode==13)event.keyCode=9"  >
<!-- <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#6595d6 cellSpacing=0 cellPadding=3 width="90%" align=center bgColor=#ffffff border=1>-->
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<input type="hidden" name="fid" id="fid" value="UII01.01.02.04"><input type="hidden" name="id" id="id" value="<%=crs.getString("id")%>"><input type="hidden" name="msgtype" id="msgtype" value="<%try{%><%=crs.getString("msgtype")%><%}catch(Exception e){%><%="公告"%><%}%>">
	<%
	int ik = 0;
	int jk=0;
	while(ik<al_colname.size()){
			if(jk%2==0){
		%>
	<tr><%}
		if(!((String)al_datasrctype.get(ik)).equals("textarea")){
		%><td height="20" width="9%" align="right" class="text2" ><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><%=Tools.nvl(crs.getString(al_colname.get(ik).toString()))%></td>
		<%}else{
		if(jk%2==1){
		%><td  width="9%"></td><td  width="41%"></td></tr><%
		}
		%><td height="20"  width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td  width="91%" colspan="3" style='color:#333;font-size:15px;font:宋体;' ><%=(Tools.nvl(crs.getString(al_colname.get(ik).toString()))).replaceAll("%40","</br>")%></td> 
		<%jk=1;}
		%><%
		ik++;
		jk++;
		if(jk%2==0){
		%>
	</tr><%}
		}
		if(jk%2==1){
		%><td height="20" width="9%" align="right" class="text2">&nbsp;&nbsp;</td>
		<td width="41%">&nbsp;&nbsp;</td>
	</tr>		
		<%
		}
		%>
  <tr>
  	<td align="center" colspan="4">
		<input type="button" id="button1" name="button" value="标记为已读" style="width:120;height:23 " onClick="doSave();">&nbsp;&nbsp;
		<input type="button" name="button" value="返    回" style="width:120;height:23 " onClick="javascript:history.go(-1);">
	</td>
  </tr>
</table>
</form>
</div>
</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
</iframe> 
<script language=javascript>
  function doSave()
  {
  if(document.getElementById("cont")!=null){
	s=document.getElementById("cont").innerHTML;
	s=s.replace(/[\r\n]/gm,"%40");
	document.getElementById("cont").value=s;}
	//alert(document.getElementById("id").value);
    formHead.target = "submitframe";
	//document.getElementById("cont").disabled=true;
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
	document.getElementById("fid").value="UII01.01.02.03";
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
	<%if(Integer.parseInt(mypri)>=3){%>
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
			document.getElementById('<%=key%>').style.color='#aaaaaa';
			document.getElementById('<%=key%>').readOnle=true;
		}<%
		}
		}
		%>
  
  }
  function checkisread(){
	if (<%if (crs.getString("isread").equals("1")){%><%="true"%><%}else{%><%="false"%><%}%>){
		
		document.getElementById("button1").value='此<%=crs.getString("msgtype")%>已读';
		document.getElementById("button1").disabled=true;
	}
  
  }
  checkisread();
  checknow();
  checkfilter();
</script>
<%
crs.close();
crs_cols.close();
}else{
String msgtype = (String)request.getAttribute("msgtype");
	%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		//alert("修改成功！");
		window.top.indexFrame.frames['manFrame'].location = 'MainServlet_sys?fid=UII01.01.02.01&msgtype=<%=msgtype%><%=Integer.parseInt(mypri)>4?"":filter_str%>';
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