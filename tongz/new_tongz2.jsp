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
<title>新增通知信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="../css/index.css" type="text/css">
<link href="../css/login/style.css" type=text/css rel=stylesheet>

<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<link rel="stylesheet"  href="css/001.css"  										type="text/css" >
<link rel="stylesheet"  href="css/style1.css" 									type=text/css>
<script type="text/javascript" src="./js/map.js"></script>

<script language="JavaScript" src="../share/js/utils.js"></script>
<script language="JScript" type="text/javascript"  src="./js/calendar.js"> </script>
    <script type="text/javascript" charset="utf-8" src="editor_config.js"></script>
    <script type="text/javascript" charset="utf-8" src="editor_all.js"></script>
</head>
<%
try{
	String flag1 =(String)request.getAttribute("flag1"); 
	HashMap hp_pri = (HashMap)(request.getAttribute("hp_pri"));
	System.out.println(hp_pri.toString());
	String filter_str = Tools.nvl((String)(hp_pri.get("filter_str")));
	String mypri = Tools.nvl((String)(hp_pri.get("mypri")));
	CachedRowSet crs_user = (CachedRowSet)hp_pri.get("crs_user");
  if (flag1==null || flag1.equals(""))
  {   
		flag1 ="false" ;
		User user = new User();
		user = (User)session.getAttribute("curUser");
		String titleName = "新增通知信息";
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
<form name="formHead" action="./MainServlet_sys" target="sframe" method="post" onKeyDown="if(event.keyCode==13)event.keyCode=9" >
    <input type="hidden" name="fid" value="UII00.07.01.03">
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	<%
	int ik = 0;
	int jk = 0 ;
	while(ik<al_colname.size()){
			if(jk%2==0){
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
		<%}else if(((String)al_datasrctype.get(ik)).equals("getmap")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" ondblclick="showmap()" size="25" value=""/>双击 打开/隐藏 地图</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("calendar")){
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="41%"><input type="text" onfocus="calendar()" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25" value=""/>&nbsp;&nbsp;</td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("textarea")){
		if(jk%2==1){
		%><td  width="9%"></td><td  width="41%"></td></tr><%
		}
		%><td height="20" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td width="91%" colspan="3" style="font:10px;"><!--textarea cols="100%" rows="10" id="<%=al_colname.get(ik).toString()%>" id="<%=al_colname.get(ik).toString()%>" name="<%=al_colname.get(ik).toString()%>" size="25" value=""></textarea>&nbsp;&nbsp;--><div>
		<script  id="editor" type="text/plain"></script>
		</div><input type="hidden" id="cont" name="cont" value=""/></td> 
		<%}else if(((String)al_datasrctype.get(ik)).equals("moreline")){
		if(jk%2==1){
		%><td  width="9%"></td><td  width="41%"></td></tr><%
		}		
		%><td height="60" width="9%" align="right" class="text2"><%=(String)al_colnamec.get(ik)%>：</td><td colspan="3" width="91%" >单击添加收件人:<div id="rec_named" style="padding: 3 3 3 3 ;background-color:#ddddff;height:54px;width:96%" onclick="showbk()"></div></td><input type="hidden" id="rec_name" name="rec_name" value=""/>
		<%jk=1;
		}
		jk++;
		ik++;
		if(jk%2==0){
		%>
	</tr><%}
		}
		if(jk%2==0){
		%><td height="20" width="9%" align="right" class="text2">&nbsp;&nbsp;</td>
		<td width="41%">&nbsp;&nbsp;</td>
	</tr>		
		<%
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
  </div>
<div id="rec_namebook" style="position:absolute;background-color:#eeeeff;height:200px;width:600px;z-index: 10; border:2px solid #ccc ;display:none;overflow:scroll;s">
<%	String preu = "";
	String curu = "";
	boolean newd = false;
	while(crs_user.next()){
		curu=crs_user.getString("work_unit");
		String realname =crs_user.getString("realname");
		String theid =crs_user.getString("id");
		if(!curu.equals(preu)){
		
		newd = true;
		}
		if(newd && !preu.equals("")){
		%></div><%}
		if (newd){
%>
<div style="clear:left;">
		<div style="color:#000; font:bold;"><%=curu%>:</div>
		<%
		}
%>		<div style="height:auto;  width:100%;">
		<div style="padding:5 3 5 3px;float:left;  border-bottom:2px dotted #00C;width:56px; height:20px;">
        <div style="font-size:14px; width:50px; color:#666; background-color:#ddddff;" id ="r<%=theid%>" onclick="selrec(this.id);"><%=realname%></div>
		</div><%
  newd = false;
  preu =curu;
%>
</div>
<%}
 %>
</div>
</div>
</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:block"></iframe>
</iframe> 
<script language=javascript>
  function doSave()
  {
  
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
  
function g(s){
	return document.getElementById(s);	
	}
function selrec(s){
	isfind = false;
	selname=g(s).innerHTML;
	var a = new Array();
	var b = new Array();
	if (g('rec_named').innerHTML==''){
		g('rec_named').innerHTML=selname;
		g('rec_name').value=selname;
		g(s).style.backgroundColor="#6699cc";
	return
		}
	a= g('rec_named').innerHTML.split(";");
	for(var nam in a){
			if(selname==a[nam]){
			isfind=true;
			break;
			}
		}
	if(isfind){
		g(s).style.backgroundColor="#ddddff";
		for(var nam in a){
			if (selname!=a[nam]){
				b.push(a[nam]);
			}
			
		}
	}else{
		g(s).style.backgroundColor="#6699cc";
		for(var nam in a){
			if(a.length==0){}else{
			b.push(a[nam]);
			}
		}
		b.push(selname);
	}
	if(b.length!=0){
		g('rec_named').innerHTML=b.join(";");
		g('rec_name').value=b.join(";");
	}else{
		g('rec_named').innerHTML ="";
		g('rec_name').value="";
	}
	//alert(g('rec_name').value);
}
function addfun(){
	if(window.addEventListener){ // Mozilla, Netscape, Firefox 
		// document.addEventListener('click', alert('11'), false); 
		// document.addEventListener('click', alert('12'), false); 
	} else { // IE 
		 //document.attachEvent('onclick', function(){alert('21');}); 
		// document.attachEvent('onclick', function a1(){g('rec_namebook').style.display ='none';}); 
	} 
}
function showbk(){
	if(g('rec_named').innerHTML=="单击此处添加收件人"){
	g('rec_named').innerHTML="";}
	var e = window.event.srcElement;
	var t = e.offsetTop,  h = e.clientHeight, l = e.offsetLeft, p = e.type;
	while (e = e.offsetParent){t += e.offsetTop; l += e.offsetLeft;}
	if(g('rec_namebook').style.display =='none'){
		g('rec_namebook').style.display ='block';
		g('rec_namebook').style.top =t+60;
		g('rec_namebook').style.left =l+0;
		//alert(g('rec_namebook').style.top+":"+g('rec_namebook').style.left)
		
	}else{
		g('rec_namebook').style.display ='none';
	}
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
			document.getElementById('<%=key%>').readOnly=true;
			document.getElementById('<%=key%>').style.color="#aaa";
		}<%
		}
		}
		%>
  
  }
  checkfilter();
   addfun();
</script>
<script type="text/javascript">
    //实例化编辑器
    var ue = UE.getEditor('editor');

    ue.addListener('ready',function(){
        this.focus()
    });

    function insertHtml(){
        var value = prompt('插入html代码','');
        ue.execCommand('insertHtml',value)
    }
    function createEditor(){
        enableBtn();
        UE.getEditor('editor')
    }
    function getAllHtml() {
        alert( UE.getEditor('editor').getAllHtml() )
    }
    function getContent() {
        var arr = [];
        arr.push( "使用editor.getContent()方法可以获得编辑器的内容" );
        arr.push( "内容为：" );
        arr.push(  UE.getEditor('editor').getContent() );
        alert( arr.join( "\n" ) );
    }
    function getPlainTxt() {
        var arr = [];
        arr.push( "使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容" );
        arr.push( "内容为：" );
        arr.push(  UE.getEditor('editor').getPlainTxt() );
        alert( arr.join( '\n' ) )
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push( "使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容" );
        UE.getEditor('editor').setContent( '欢迎使用ueditor',isAppendTo );
        alert( arr.join( "\n" ) );
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled( 'fullscreen' );
        disableBtn( "enable" );
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range =  UE.getEditor('editor').selection.getRange();
        range.select();
        var txt =  UE.getEditor('editor').selection.getText();
        alert( txt )
    }

    function getContentTxt() {
        var arr = [];
        arr.push( "使用editor.getContentTxt()方法可以获得编辑器的纯文本内容" );
        arr.push( "编辑器的纯文本内容为：" );
        arr.push(  UE.getEditor('editor').getContentTxt() );
        alert( arr.join( "\n" ) );
    }
    function hasContent() {
        var arr = [];
        arr.push( "使用editor.hasContents()方法判断编辑器里是否有内容" );
        arr.push( "判断结果为：" );
        arr.push(  UE.getEditor('editor').hasContents() );
        alert( arr.join( "\n" ) );
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn( str ) {
        var div = document.getElementById( 'btns' );
        var btns = domUtils.getElementsByTagName( div, "button" );
        for ( var i = 0, btn; btn = btns[i++]; ) {
            if ( btn.id == str ) {
                domUtils.removeAttributes( btn, ["disabled"] );
            } else {
                btn.setAttribute( "disabled", "true" );
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById( 'btns' );
        var btns = domUtils.getElementsByTagName( div, "button" );
        for ( var i = 0, btn; btn = btns[i++]; ) {
            domUtils.removeAttributes( btn, ["disabled"] );
        }
    }
</script>
<%
crs_cols.close();
}else{%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("增加成功！");
		window.top.indexFrame.frames['manFrame'].location = 'MainServlet_sys?fid=UII00.07.01.01<%=Integer.parseInt(mypri)==4?"":filter_str%>';
		}
	}
	checknow();
</script>
<%}
}catch(Exception ex){
	ex.printStackTrace();
}
%>


