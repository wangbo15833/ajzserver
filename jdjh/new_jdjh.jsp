<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><%@ 
page contentType="text/html; charset=gb2312"
import="com.zagerbone.user.User"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="java.text.*"%><%@ include  file='../identification.jsp'  
%><head>
<title>新增监督计划</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<link rel="stylesheet"  href="css/001.css"  										type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 									type=text/css>
<script type="text/javascript" src="./js/map.js"></script>

<script language="JavaScript" src="../share/js/utils.js"></script>
<script language="JScript" type="text/javascript"  src="./js/calendar.js"> </script>
</head>
<%
try{
	String flag1 =(String)request.getAttribute("flag1"); 
	HashMap hp_pri = (HashMap)(request.getAttribute("hp_pri"));
	System.out.println(hp_pri.toString());
	String filter_str = Tools.nvl((String)(hp_pri.get("filter_str")));
	String mypri = Tools.nvl((String)(hp_pri.get("mypri")));
  if (flag1==null || flag1.equals(""))
  {   
		flag1 ="false" ;
		User user = new User();
		user = (User)session.getAttribute("curUser");
		String titleName = "新增监督计划";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

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
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone">
<form name="formHead" action="./MainServlet_sys" target="sframe"  method="post" onKeyDown="if(event.keyCode==13)event.keyCode=9" >
    <input type="hidden" name="fid" value="UII00.04.01.03">
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<%
	int ik = 0;
	while(ik<al_colname.size()){
			if(ik%2==0){
		%>
	<tr><%}%>		
		<%if(((String)al_datasrctype.get(ik)).equals("")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" name="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" size="25"<%if (al_colname.get(ik).toString().equals("id")){%> disabled="disabled" style="background-color:#CCC" value="由系统生成"<%}else{%>value=""<%}%>/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("popList")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><select id="<%=al_colname.get(ik).toString()%>"  name="<%=al_colname.get(ik).toString()%>" style="width:190px;">
			<option value="">&nbsp;&nbsp;-=&nbsp;&nbsp;请选择&nbsp;&nbsp;=-&nbsp;&nbsp;</option>
			<%=com.zagerbone.util.Tools.popListWithSelect((String)al_datasrc.get(ik),"")%>
			</select></td>
		<%}else if(((String)al_datasrctype.get(ik)).equals("check")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" onblur="myFunction(this.value)" size="25" value=""/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("calendar")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" onfocus="calendar()" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25" value=""/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("textarea")){
		if(ik%2==1){
		%><td  width="9%"></td><td  width="41%"></td></tr><%
		}
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="91%" colspan="3"><textarea cols="100%" rows="10" id="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25" value=""></textarea>&nbsp;&nbsp;</td> 
	</tr>
		<%}
		ik++;

		}
		%>
  <tr>
  	<td align="center" colspan="4">
		<input type="button" name="button" value="确 定" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" name="button" value="重 填" style="width:80;height:23 ">&nbsp;&nbsp;&nbsp;&nbsp;
		<!--input type="button" name="button" value="打 印" style="width:80;height:23 " onclick="javascript:window.top.indexFrame.frames['manFrame'].print();">&nbsp;&nbsp;&nbsp;&nbsp;
		--><input type="button" name="button" value="返 回" style="width:80;height:23 " onClick="javascript:history.go(-1);">
	</td>
  </tr>
</table>
</form>

</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
  <!--百度地图容器-->
  
  <div id="baidumap"  style="border:10px solid #ccc ;position: absolute; z-index: -10; width: 350px; height: 350px;" ondblclick="hidemap()">
  </div>

</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:block"></iframe>
</iframe> 
<script language=javascript>
  function doSave()
  {
  if(document.getElementById("cont")!=null){
	s=document.getElementById("cont").innerHTML;
	s=s.replace(/[\r\n]/gm,"%40");}
    formHead.target = "submitframe";
	formHead.submit();
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
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
  checkfilter();
</script>
<%
crs_cols.close();
}else{%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("增加成功！");
		window.top.indexFrame.frames['manFrame'].location = 'MainServlet_sys?fid=UII00.04.01.01<%=Integer.parseInt(mypri)==4?"":filter_str%>';
		}
	}
	checknow();
</script>
<%}
}catch(Exception ex){
	ex.printStackTrace();
}
%>


