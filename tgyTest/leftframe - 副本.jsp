<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=gb2312" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.zagerbone.util.DBTrans"%>
<%@ page import="com.zagerbone.util.Tools"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%
/*
String usertype = (String)session.getAttribute("Myusertype");
System.out.println("usertype==="+usertype);
*/
HashMap hp_user = (HashMap)session.getAttribute("hp_user");
String 	realname = (String)hp_user.get("realname");
String 	work_unit = (String)hp_user.get("work_unit");
String  usertype = (String)hp_user.get("usertype");
String  myaccount = (String)hp_user.get("username");
System.out.println("hp_user====="+hp_user);
%>
<%
try{
	DBTrans dbtrans = new DBTrans();
	CachedRowSet crs = dbtrans.getResultBySelect("select m.* ,p.* from menulist m  join pritab p on m.tabname=p.tabname and cast(m.needpri as int)<=cast(p.prilevel as int) and role_name='"+usertype+"' and m.disp='on' order by cast(m.order1 as int),cast(m.order2 as int),cast(m.order3 as int)");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<title>左侧</title>
</head>
<script  type="text/javascript">
/**
*   @function  list_sub_detail
*   @brief
*		获取左侧列表的子项
*   @retval 
*/
var preClassName = "";

//列左标签，重新解析项目
function list_sub_detail(Id, item) {
	//alert("Id="+Id+",item="+item);
    if (preClassName != "") {
        getObject(preClassName).className = "left_back"
    }
		//被点击则改变样式效果
    if (getObject(Id).className == "left_back") {
        getObject(Id).className = "left_back_onclick";
				//获取item的子项
        MenuObj.getbyitem(item);
        preClassName = Id	//保存ID
    }
		Id = Id.replace('left_nav_','');
		if(document.getElementById(MenuObj.itemlist[Id][0]) == undefined){
			return;
		}
		if(document.getElementById(MenuObj.itemlist[Id][0].sortname+'_'+0 )){
			document.getElementById(MenuObj.itemlist[Id][0].sortname+'_'+0 ).click();
		}
}

//返回ID的对象
function getObject(objectId) {
    if (document.getElementById && document.getElementById(objectId)) {
        return document.getElementById(objectId)
    } else if (document.all && document.all(objectId)) {
        return document.all(objectId)
    } else if (document.layers && document.layers[objectId]) {
        return document.layers[objectId]
    } else {
        return false
    }
}

//定义类outlook，两个属性：titlelist与itemlist；5个方法：
function outlook() {
    this.titlelist 		= new Array();//一个菜单下的竖列表标签
    this.itemlist 		= new Array();//竖列表中的内容
		
    this.addtitle 		= addtitle;
    this.additem 		= additem;
    this.getbytitle 	= getbytitle;
    this.getbyitem 		= getbyitem;
    this.getdefaultnav = getdefaultnav;
    this.backtofirst = backtofirst;
}

//孙子的架构：item
//参数分别为：标题 排序 键值 默认
function theitem(in_title, in_sort, in_key, in_isdefault) {
    this.sortname 	 = in_sort;
    this.key 		 = in_key;
    this.title		 = in_title;
    this.isdefault  = in_isdefault
}

/**
*   @function  addtitle
*   @brief
*		添加标题
*   @retval 
*		1.标题名称
*		2.导航栏名称定为
*		3.默认值 1
*/
//爷爷生了个孩子
function addtitle(intitle, sortname, inisdefault) {
    MenuObj.itemlist [MenuObj.titlelist.length] = new Array();
    MenuObj.titlelist[MenuObj.titlelist.length] = new theitem(intitle, sortname, 0, inisdefault);
    return (MenuObj.titlelist.length - 1)
}

/**
*		为标题添加子项目
*   @retval 
*		1.该子项目名称
*		2.所属
*		3.打开URL
*/
function additem(in_title, parentid, in_key) {
	if (parentid >= 0 && parentid <= MenuObj.titlelist.length) {
		var in_sort = "item_" + parentid;
		MenuObj.itemlist[parentid][MenuObj.itemlist[parentid].length] = new theitem(in_title, in_sort, in_key, 0);
		return (MenuObj.itemlist[parentid].length - 1)
	} 
	else {
		additem = -1;
	}
}

/**
*   @function  getdefaultnav
*   @brief
*		显示在做列表
*   @retval 
*		导航栏名字定位
*   @NOTE	修饰 class=list_tilte id=sub_sort_i
*/
function getdefaultnav(sortname) {
    var output = "";
    for (i = 0; i < MenuObj.titlelist.length; i++) {
        if (MenuObj.titlelist[i].isdefault == 1 && MenuObj.titlelist[i].sortname == sortname) {
            output += "<div class=list_tilte  id=sub_sort_"+i + " onclick=\"hideorshow('sub_detail_" + i + "')\">";
            output += "<span>" + MenuObj.titlelist[i].title + "</span>";
            output += "</div>";
            output += "<div class=list_detail id=sub_detail_"+ i + "><ul>";
            for (j = 0; j < MenuObj.itemlist[i].length; j++) {
                output += "<li id=" + MenuObj.itemlist[i][j].sortname + j 
													+ " onclick=\"changeframe('" + MenuObj.itemlist[i][j].title + "','" + MenuObj.titlelist[i].title + "','" + MenuObj.itemlist[i][j].key + "')\">"
													+"<a href=#>" + MenuObj.itemlist[i][j].title + "</a></li>"
            }
            output += "</ul></div>"
        }
    }
		//alert(output);
		//显示出来
    getObject('right_main_nav').innerHTML = output
}

/**
*   @function  getbytitle
*   @brief
*		左侧列表导航栏
*   @retval 
*		导航栏名字定位
*   @NOTE	修饰 class=left_back id=left_nav_
*/
function getbytitle(sortname) {
  var output = "<ul>";
	var firstTag;
    for (i = 0; i < MenuObj.titlelist.length; i++) {
			if (MenuObj.titlelist[i].sortname == sortname) 
			{
				output += "<li id=left_nav_" + i + " onclick=\"list_sub_detail(id,'" + MenuObj.titlelist[i].title + "')\" class=left_back>" 
								+ MenuObj.titlelist[i].title + "</li>"
				if(!firstTag)
				{
					firstTag = "left_nav_" + i
				}
			}
    }
    output += "</ul>";
    getObject('left_main_nav').innerHTML = output;
	 document.getElementById(firstTag).click();
}


/**
*   @function  getbyitem
*   @brief
*		右侧点击title显示和隐藏子项
*   @retval 
*		导航栏名字定位
*
*   @NOTE	修饰 class=list_detail id=sub_detail_i
*/
function getbyitem(item) {
    var output = "";
    for (i = 0; i < MenuObj.titlelist.length; i++) {
        if (MenuObj.titlelist[i].title == item) {
            output = "<div class=list_tilte id=sub_sort_" + i + " onclick=\"hideorshow('sub_detail_" + i + "')\">";
            output += "<span>" + MenuObj.titlelist[i].title + "</span>";
            output += "</div>";
            output += "<div class=list_detail id=sub_detail_" + i + " style='display:block;'><ul>";
            for (j = 0; j < MenuObj.itemlist[i].length; j++) {
				if (j==0){
					myfirstlink= MenuObj.itemlist[i][j].sortname + "_" + j;
				}
                output += "<li id=" + MenuObj.itemlist[i][j].sortname + "_" + j + " onclick=\"changeframe('" + MenuObj.itemlist[i][j].title + "','" + MenuObj.titlelist[i].title + "','" + MenuObj.itemlist[i][j].key + "')\"><a >" + MenuObj.itemlist[i][j].title + "</a></li>"
            }
            output += "</ul></div>"
        }
    }
    getObject('right_main_nav').innerHTML = output
	document.getElementById(myfirstlink).click();
}

/**
*   @function  changeframe
*   @brief
*		浏览导航
*   @retval 
*		选择的子项和导航栏名称都显示到mainFrame导航栏下面做提示
*/
function changeframe(item, sortname, src) {
    if (item != "" && sortname != "") {
        window.top.indexFrame.frames['mainFrame'].getObject('show_text').innerHTML = sortname + "&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;" + item
    }
    if (src != "") {
        window.top.indexFrame.frames['manFrame'].location = src
		//alert(src);
    }
}

/**
*   @function  hideorshow
*   @brief
*		显示和隐藏子项
*   @retval 
*/
function hideorshow(divid) {
    subsortid = "sub_sort_" + divid.substring(11);
    if (getObject(divid).style.display == "none") {
        getObject(divid).style.display = "block";
        getObject(subsortid).className = "list_tilte"
    } else {
        getObject(divid).style.display = "none";
        getObject(subsortid).className = "list_tilte_onclick"
    }
}




// 导航栏配置文件
var MenuObj=new outlook();
var t;

<%
	int prem1=0;
	int curm1=0;
	int prem2=0;
	int curm2=0;
	int prem3=0;
	int curm3=0;
	
	String menu1=null;
	String menu2=null;
	String menu3=null;
	
	String link=null;
	String needpri=null;
	String prilevel=null;
	String filter_str=null;
	String firstP="";
	String filter=null;
	
	
	boolean flag1=false;
	boolean flag2=false;
	boolean flag3=false;

	while(crs.next()){
		menu1=crs.getString("menu1");
		menu2=crs.getString("menu2");
		menu3=crs.getString("menu3");
		
		curm1=Integer.parseInt(crs.getString("order1").replaceAll(" ",""));
		curm2=Integer.parseInt(crs.getString("order2").replaceAll(" ",""));
		curm3=Integer.parseInt(crs.getString("order3").replaceAll(" ",""));
		
		link=crs.getString("link").replaceAll(" ","");
		needpri=crs.getString("needpri").replaceAll(" ","");
		prilevel=crs.getString("prilevel").replaceAll(" ","");
		filter_str=crs.getString("filter_str").replaceAll(" ","");
		
		filter=filter_str.replaceAll("myname",realname).replaceAll("mydept",work_unit).replaceAll("myaccount",myaccount);
		
		if(Integer.parseInt(prilevel)<=3 && !filter_str.equals("")){
			link+=filter;
			//link+=link.indexOf("?")==-1?"?"+filter:"&"+filter;
			//link=link+"&"+filter_str.replaceAll("myname",realname).replaceAll("mydept",work_unit);
		}
		
		if (firstP.equals("")){
			firstP=link;
		}
		if(curm1!=prem1){
			flag1=true;
			prem1=curm1;
		}
		if(curm2!=prem2){
			flag2=true;
			prem2=curm2;
		}
		if(curm3!=prem3){
			flag3=true;
			prem3=curm3;
		}
		
		if(flag1 ){
		%>
	
	t=MenuObj.addtitle('<%=menu2%>','<%=menu1%>',1)
	MenuObj.additem('<%=menu3%>',t,'<%=link%>')<%
		}

		if(!flag1 && flag2){
		%>
	t=MenuObj.addtitle('<%=menu2%>','<%=menu1%>',0)
	MenuObj.additem('<%=menu3%>',t,'<%=link%>')<%
		}
		
		if(!flag1 && !flag2 && flag3){%>
	MenuObj.additem('<%=menu3%>',t,'<%=link%>')<%
		}
		
		flag1=false;
		flag2=false;
		flag3=false;
	}		
%>
/**
*   @function  initinav
*/
function initinav(sortname) {
    MenuObj.getdefaultnav(sortname);
    MenuObj.getbytitle(sortname);
	window.top.indexFrame.frames['manFrame'].location = "<%=firstP%>"
}

/**
*   @function  initinav
*/
function backtofirst(){
//initinav('网站首页')
window.top.indexFrame.frames['manFrame'].location = "../infoplat/info.jsp"
}
</script>
<body onload="initinav('网站首页')">
<div id="left_content" style="height:500px">
	<div style=" margin-top:6px;"></div>
	<div id="main_nav">
	 <div id="left_main_nav"  ></div>
	 <div id="right_main_nav" style=" overflow-y:hidden; overflow-x:hidden"></div>
	</div>
</div>
</body>
</html>
<%
crs.close();
}catch(Exception ex){ 
	ex.printStackTrace();
}%>