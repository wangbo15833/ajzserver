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
<title>新增检查标准</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="./css/common.css" type="text/css" />
<link rel="stylesheet" href="js/jquery.treeview/jquery.treeview.css" />
<link rel="stylesheet" href="js/jquery.treeview/red-treeview.css" />
<script language="JScript" type="text/javascript"  src="js/jquery.min.js"> </script>
<script src="js/jquery.treeview/lib/jquery.cookie.js" type="text/javascript"></script>
<script src="js/jquery.treeview/jquery.treeview.js" type="text/javascript"></script>
<script src="js/HashMap.js"></script>
<script>
/**
*	Json Objects;
*	author: lee zhen nan, mail: lzn906@163.com, sign: lee * snowflakes 
*	@ 2013-12-25
*	Merry Xmas!
*/
function JsonObj(){
	var j = new Array();
	var add;
	var getStr;
	var getObj;
	var clear;
	
	this.clear = function(){
		j.splice(0,j.length);  
	}
	this.add = function(name,val){
		j.push("\""+name+"\":"+"\""+val+"\"");
	}
	this.getStr = function(){
		return "{"+j.join(",")+"}";
	}
	this.getObj = function(){
		return eval("("+"{"+j.join(",")+"}"+")");
	}
};
function JsonArray(){
	var a = new Array();
	var add;
	var remove;
	var clear;
	var getStr;
	var getArr;
	this.add = function(val){
		a.push(val);
	};
	this.clear = function(){
		a.splice(0,a.length);
	}
	this.remove = function(i){
		a.splice(i,1);
	}
	this.getStr = function(){
		return "["+a.join(",")+"]";
	}
	this.getArr = function(){
		return eval("(["+a.join(",")+"])")
	}
}
</script>
<style>
#main_zone{width:100%;height:100%;}
textarea{height:200px;width:300px;}
.flaglabel{width:150px;border: 0 none;}
.selframe{width:300px;}
.seltitle{font-size:18px;color:#333;background:#999;border:1px black solid;}
.seltitle:hover{cursor:pointer;color:blue;background:#eee;}
.selcont{display:none;}
.folder{color:#333;}
.folder:hover{color:#33F;cursor:pointer;}
#browser{width:300px;max-height:800px;overflow:scroll;}
</style>
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
		String titleName = "新增检查标准";
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
    <input type="hidden" name="fid" value="UII00.06.02.03">
    <input type="hidden" name="items" id="items" value="">
</form>
	<TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
	<tr>
		<td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2"><font size="+1"><%=titleName%></font></td>
	</tr>
	
	<tr><td>
		<div id='main_zone'>
		<div>
			<span>
				<textarea id='txtsrc'></textarea>			
			</span>
			<span>
				<textarea id='txtdis'></textarea>		
			</span>
			<div>
			<ul id="browser" class="filetree treeview-famfamfam">
				<li><span class="folder">标准一</span>
					<ul id="root_ul">
					</ul>
				</li>
			</ul>
			</div>
		</div>	
		<%@ include  file='selframe.jsp'  %>
		<div>
			<input type="button" id="clearinfo" value="重置"/>
			<input type="button" id="genreg" value="产生正则"/>
			<input type="button" id="ansystext" value="分析文本"/>
			<input type="button" id="gentree" value="生成树"/>
			<input type="text" id="txt4" value=""/>
			<input type="text" id="txt5" value=""/>
		</div>
	</div>
  </td></tr>
	
  <tr>
  	<td align="center" colspan="4">
		<input type="button" name="button" value="确 定" style="width:80;height:23 " onClick="doSave();">&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" name="button" value="重 填" style="width:80;height:23 ">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" name="button" value="返 回" style="width:80;height:23 " onClick="javascript:history.go(-1);">
	</td>
  </tr>
</table>
</div>
</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:block"></iframe>
</iframe> 
<script>
  function doSave()
  {
    formHead.target = "submitframe";
	formHead.submit();
  }
  function doClose()
  {
  	parent.returnValue="";
    this.close();
  }
</script>
<script>
/////////初始化变量
var sta="0000";
var book = new Array();
var hp = new HashMap();
</script>
<script>
function cot(str,i,v){
try{
		var index = parseInt(i,10);
		//var val = parseInt(v,10);
		var tStart;
		var tVal;
		var tEnd;
		var retstr;
	if(typeof(v)=="boolean"){
		if(index<str.length){
			tStart = str.substring(0,index);
			tEnd   =  str.substring(index+1,str.length);
			var  t = (v?"1":"0");
			retstr=tStart+t+tEnd;
		}else{
			retstr="超出范围";
			throw("123");
		}
	}else{
		var val = parseInt(v,10);
		if(index<str.length){
			tStart = str.substring(0,index);
			tVal   = str.substring(index,index+1);
			tEnd   =  str.substring(index+1,str.length);
			if(val==-1){
				var  t = (tVal=="1"?"0":"1");
			}else{
				var  t = val.toString();
			}
			retstr=tStart+t+tEnd;
		}else{
			retstr="超出范围";
			throw("123");
		}
	}
	return retstr;
	}catch(e){
		alert(e);
	}
}
</script>
<script>
function mypage(){
var version;
var code;
var parentcode;
var checkparent;
var isleaf
var ischeck;
var depth;
var chapterno;
var title;
var cont;
}
</script>
<script>
var code = 0;
var perfix = "JCBZ";
function getcode(){
	code++;
	return perfix+code;
}
</script>
<script>
var pattern0=new RegExp(/^\d{1,2}\s+/); //以 单个数字开头并且后跟空格 "1 ";
var pattern1=new RegExp(/^\d{1,2}\.\d{1,2}\s+/); //以 数字.数字 开头并且后跟空格 "1.1 ";
var pattern2=new RegExp(/^\d{1,2}\.\d{1,2}\.\d{1,2}\s+/); //以 数字.数字.数字 开头并且后跟空格 "1.1.1 ";
var pattern3=new RegExp(/^\d{1,2}\.\d{1,2}\.\d{1,2}\.\d{1,2}\s+/); //以 数字.数字.数字.数字 开头并且后跟空格 "1.1.1.1 ";
var pattern4=new RegExp(/^\d{1,2}）/); //以数字和）开头 "1）"
function testcont(s){
	var sta = "00000";
	sta = cot(sta,0,pattern0.test(s));
	sta = cot(sta,1,pattern1.test(s));
	sta = cot(sta,2,pattern2.test(s));
	sta = cot(sta,3,pattern3.test(s));
	sta = cot(sta,4,pattern4.test(s));
	return sta;
}
</script>
<script>
function addinbook(code,parentcode,depth,isleaf,ischeck,version,chapterno,title){
 var page = new mypage();
 page.code=code;
 page.parentcode=parentcode;
 page.depth=depth;
 page.isleaf=isleaf;
 page.ischeck=ischeck;
 page.version=version;
 page.chapterno=chapterno;
 page.title = title;
 
 book.push(page);
}
</script>
<script>
var flagreg;
$(document).ready(function(){
	$("#clearinfo").click(function(){
		code=0;
		hp=new HashMap();
		book = new Array();
	});
	$("#genreg").click(function(){
		flagreg = new Array();
		for(i=0;i<5;i++){flagreg.push("");}
		$("#txtdis").val("");
		for(ij=0;ij<5;ij++){
			$(".flag"+ij).each(function(i){
				if($(this).val()!="-1"){
					flagreg.splice(ij,1,flagreg[ij]+$(this).val());
				}else{
					flagreg.splice(ij,1,flagreg[ij]+".");
				}
			});
			$("#txtdis").val($("#txtdis").val()+flagreg[ij]+"\n");
		}
	});
});

</script>
<script>
$(document).ready(function(){
	$(".seltitle:first").siblings("div").css("display","block");
	$(".seltitle").click(function(){
		$(this).siblings("div").slideToggle("slow");
	});

	$("#ansystext").click(function(){
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
		var curline;
		var nextline;
		var pat = new Array();
		
		for(i=0;i<5;i++){
			pat.push(new RegExp(flagreg[i]));
		}
		
		for (i =0;i<line.length;i++){
			curline=line[i];
			if(i<line.length-1){
				nextline=line[i+1];
			}else{
				nextline="";
			}
			
			
			curline = curline.replace(/(^\s*)|(\s*$)/g, "");//消除前导及结尾空格
			nextline = nextline.replace(/(^\s*)|(\s*$)/g, "");//消除前导及结尾空格
			
			var cot1 = testcont(curline);
			var cot2 = testcont(nextline);
			
			if(pat[0].test(cot1+cot2)){
				$("#txtdis").val($("#txtdis").val()+"<一级标题>"+curline+"--\n");
				var code= getcode();
				var parentcode ="root";
				var checkparent="root";
				var depth = 1;
				var isleaf = 0;
				var ischeck = 0;
				var version ="1.0";
				var chapterno =curline.split(" ")[0];
				var title =curline;
				hp.put("level1code",code);
				addinbook(code,parentcode,depth,isleaf,ischeck,version,chapterno,title);
			}
			if(pat[1].test(cot1+cot2)){
				$("#txtdis").val($("#txtdis").val()+"<二级标题>"+curline+"--\n");
				var code= getcode();
				var parentcode =hp.get("level1code");
				var checkparent=hp.get("level1code");
				var depth = 2;
				var isleaf = 0;
				var ischeck = 0;
				var version ="1.0";
				var chapterno =curline.split(" ")[0];
				var title =curline;
				hp.put("level2code",code);
				addinbook(code,parentcode,depth,isleaf,ischeck,version,chapterno,title);
			}
			if(pat[2].test(cot1+cot2)){
				var pattern = new RegExp(/^[0-9]\s/);
				$("#txtdis").val($("#txtdis").val()+"<三级标题>"+curline+"--\n");
				var code= getcode();
				var parentcode =hp.get("level2code");
				var checkparent=hp.get("level2code");
				var depth = 3;
				var isleaf = 1;
				if(pattern.test(nextline)){
					isleaf = 0;
				}
				var ischeck = 0;
				var version ="1.0";
				var chapterno =curline.split(" ")[0];
				var title =curline;
				hp.put("level3code",code);
				addinbook(code,parentcode,depth,isleaf,ischeck,version,chapterno,title);
			}
			if(pat[3].test(cot1+cot2)){
				$("#txtdis").val($("#txtdis").val()+"<检查项目>"+curline+"--\n");
				var code= getcode();
				var parentcode =hp.get("level3code");
				var checkparent=hp.get("level3code");
				var depth = 3;
				var isleaf = 0;
				var ischeck = 1;
				var version ="1.0";
				var chapterno =curline.split(" ")[0];
				var title =curline;
				hp.put("level4code",code);
				addinbook(code,parentcode,depth,isleaf,ischeck,version,chapterno,title);
			}
			if(pat[4].test(cot1+cot2)){
				$("#txtdis").val($("#txtdis").val()+"<检查点>"+curline+"--\n");
				var code= getcode();
				var parentcode =hp.get("level4code");
				var checkparent=hp.get("level4code");
				var depth = 4;
				var isleaf = 1;
				var ischeck = 2;
				var version ="1.0";
				var chapterno =curline.split(" ")[0];
				var title =curline;
				hp.put("level5code",code);
				addinbook(code,parentcode,depth,isleaf,ischeck,version,chapterno,title);
			}
			$("#txt4").val("解析行数："+i);
		}
		
	});
	$("#gentree").click(function(){
		var ja = new JsonArray();
		for (i=0;i<book.length;i++){
			var code = book[i].code;
			var parentcode = book[i].parentcode;
			var depth =  book[i].depth;
			var isleaf = book[i].isleaf;
			var ischeck = book[i].ischeck;
			var title = book[i].title;
			
			var jo = new JsonObj();
			jo.add("code"		,code		);
			jo.add("parentcode"	,parentcode	);
			jo.add("depth"		,depth		);
			jo.add("isleaf"		,isleaf		);
			jo.add("ischeck"	,ischeck	);
			jo.add("title"		,title		);
			ja.add(jo.getStr());
			$("#items").val(ja.getStr());
			
			var cls;
			
			if($("#"+parentcode+"_ul").length==0){
				 $("<ul id='"+parentcode+"_ul' style='display:none;'></ul>").appendTo("#"+parentcode);
			}
			
			
			if (isleaf == 1 && ischeck==2){ 
				cls = "file";
				$("<li id='"+code+"'class='expandable'><span class='"+cls+"'>"+title+"</span></li>").appendTo("#"+parentcode+"_ul");
			}else if(isleaf == 1 && ischeck==0){ 
				cls = "folder";
				$("<li id='"+code+"'class='expandable'><span class='"+cls+"'>"+title+"</span></li>").appendTo("#"+parentcode+"_ul");
			}else if (isleaf == 0 && ischeck==1){
				cls = "folder";
				$("<li id='"+code+"'class='expandable'><div class='hitarea expandable-hitarea'></div><span class='"+cls+"'>"+title+"</span></li>").appendTo("#"+parentcode+"_ul");
			}else if (isleaf == 0 && ischeck==0){
				cls = "folder";
				$("<li id='"+code+"'class='expandable'><div class='hitarea expandable-hitarea'></div><span class='"+cls+"'>"+title+"</span></li>").appendTo("#"+parentcode+"_ul");
			}
			$("#txt5").val("生成树："+i);
			
			
			
		}
	});
	
});
</script>
<script>
$(document).ready(function(){
	//alert("jq is read;");
	$("#browser").treeview({
		toggle: function() {
			console.log("%s was toggled.", $(this).find(">span").text());
		}
	});
	$(".folder").live("click",function(){
		if($(this).siblings("ul").css("display")=="none"){
			$(this).parent().attr("class","collapsable");
			$(this).prev().attr("class","hitarea collapsable-hitarea");
		}else{
			$(this).parent().attr("class","expandable");
			$(this).prev().attr("class","hitarea expandable-hitarea");
		}
		$(this).siblings("ul").toggle();
	});
});
</script>
<%
}else{%>
<script language="javascript">
	function  checknow()
	{
		if(<%=flag1%>){
		alert("增加成功！");
		window.top.indexFrame.frames['manFrame'].location = 'MainServlet_sys?fid=UII00.06.02.01<%=Integer.parseInt(mypri)==4?"":filter_str%>';
		}
	}
	checknow();
</script>
<%}
}catch(Exception ex){
	ex.printStackTrace();
}
%>


