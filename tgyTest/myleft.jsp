<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 
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
		//alert(MenuObj.itemlist[5][0].sortname+0);
		//alert(document.getElementById('item_50'));
		Id = Id.replace('left_nav_','');
		//alert(document.getElementById(MenuObj.itemlist[Id][0]) );
		//alert(document.getElementById(MenuObj.itemlist[Id][0].sortname+'_'+0 )	);
		//alert(document.getElementById(MenuObj.itemlist[Id][0].sortname+'_'+0 )	);
		if(document.getElementById(MenuObj.itemlist[Id][0]) == undefined){
			return;
		}
		if(document.getElementById(MenuObj.itemlist[Id][0].sortname+'_'+0 )){
			document.getElementById(MenuObj.itemlist[Id][0].sortname+'_'+0 ).click();
		}
		/*
		if(document.getElementById(MenuObj.itemlist[Id][0] != undefined)){
			document.getElementById(MenuObj.itemlist[Id][0].sortname+'_'+0 ).click();
			//alert(document.getElementById('right_main_nav').innerHTML);		
		}	else{
			//alert('控制');
		}*/
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
    this.getdefaultnav = getdefaultnav
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
		//在所有标题列表中查找
    for (i = 0; i < MenuObj.titlelist.length; i++) {
		  	//使用1 则默认在左侧列表显示
        if (MenuObj.titlelist[i].isdefault == 1 && MenuObj.titlelist[i].sortname == sortname) {
						//标题
            output += "<div class=list_tilte  id=sub_sort_"+i + " onclick=\"hideorshow('sub_detail_" + i + "')\">";
            output += "<span>" + MenuObj.titlelist[i].title + "</span>";
            output += "</div>";
						
						//显示子项 浏览key,这是孙子
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
//生成儿子的架构
function getbytitle(sortname) {
  var output = "<ul>";
	var firstTag;
    for (i = 0; i < MenuObj.titlelist.length; i++) {
			//list_sub_detail(0,MenuObj.titlelist[i].title)
			if (MenuObj.titlelist[i].sortname == sortname) 
			{
				output += "<li id=left_nav_" + i + " onclick=\"list_sub_detail(id,'" + MenuObj.titlelist[i].title + "')\" class=left_back>" 
								+ MenuObj.titlelist[i].title + "</li>"
				if(!firstTag)
				{
					firstTag = "left_nav_" + i
				}
				//getObject("left_nav_" + i).className = "left_back_onclick";
				//alert(getObject("left_nav_" + i).className)
			}
    }
    output += "</ul>";
	//输出左侧导航栏
    getObject('left_main_nav').innerHTML = output
	 document.getElementById(firstTag).click()
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
//重新解析孙子项目
function getbyitem(item) {
    var output = "";
    for (i = 0; i < MenuObj.titlelist.length; i++) {
        if (MenuObj.titlelist[i].title == item) {
						//标题
            output = "<div class=list_tilte id=sub_sort_" + i + " onclick=\"hideorshow('sub_detail_" + i + "')\">";
            output += "<span>" + MenuObj.titlelist[i].title + "</span>";
            output += "</div>";
						//子项
            output += "<div class=list_detail id=sub_detail_" + i + " style='display:block;'><ul>";
            for (j = 0; j < MenuObj.itemlist[i].length; j++) {
                output += "<li id=" + MenuObj.itemlist[i][j].sortname + "_" + j + " onclick=\"changeframe('" + MenuObj.itemlist[i][j].title + "','" + MenuObj.titlelist[i].title + "','" + MenuObj.itemlist[i][j].key + "')\"><a href=#>" + MenuObj.itemlist[i][j].title + "</a></li>"
            }
            output += "</ul></div>"
        }
    }
		//显示
    getObject('right_main_nav').innerHTML = output
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
        window.top.indexFrame.frames['mainFrame'].getObject('show_text').innerHTML = sortname + "&nbsp;&nbsp;<img src=images/slide.gif broder=0 />&nbsp;&nbsp;" + item
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
	//alert();
    subsortid = "sub_sort_" + divid.substring(11);
    if (getObject(divid).style.display == "none") {
        getObject(divid).style.display = "block";
        getObject(subsortid).className = "list_tilte"
    } else {
        getObject(divid).style.display = "none";
        getObject(subsortid).className = "list_tilte_onclick"
    }
}
 
/**
*   @function  initinav
*/
function initinav(sortname) {
    MenuObj.getdefaultnav(sortname);
    MenuObj.getbytitle(sortname);
    //window.top.indexFrame.frames['manFrame'].location = "tgyTest/init.html"
		window.top.indexFrame.frames['manFrame'].location = "tgyTest/init.html"
}
 
 
// 导航栏配置文件
var MenuObj=new outlook();
var t;
//------------------------角色管理-------------------------1
t=MenuObj.addtitle('角色类型','角色管理',1)//1表示默认是打开的
MenuObj.additem('角色列表',t,  'MainServlet_sys?fid=UII00.00.00.01&listMode=list')
MenuObj.additem('增加角色',t,  'role/new_role.jsp?titleName=新增角色')
 
t=MenuObj.addtitle('单位类型','角色管理',0)
MenuObj.additem('单位类型列表',t,'MainServlet_sys?fid=UII00.00.01.01&listMode=list')
MenuObj.additem('增加单位类型',t,'MainServlet_sys?fid=UII00.00.01.03&mode=before')
 
//------------------------基本信息-------------------------2
t=MenuObj.addtitle('单位信息','基本信息',1)//1表示默认是打开的
MenuObj.additem('单位列表',t,  'MainServlet_sys?fid=UII00.00.02.01&listMode=list')
MenuObj.additem('增加单位',t,  'MainServlet_sys?fid=UII00.00.02.03&mode=before')
 
t=MenuObj.addtitle('人员信息','基本信息',0)
MenuObj.additem('人员列表',t,  'MainServlet_sys?fid=UII00.00.03.01&listMode=list')
MenuObj.additem('增加人员',t,  'MainServlet_sys?fid=UII00.00.03.03&mode=before')
 
t=MenuObj.addtitle('项目信息','基本信息',0)
MenuObj.additem('项目列表',t,  'MainServlet_sys?fid=UII00.00.04.01&listMode=list')
MenuObj.additem('增加项目',t,  'MainServlet_sys?fid=UII00.00.04.03&mode=before')
 
t=MenuObj.addtitle('设备备案','基本信息',0)
MenuObj.additem('备案起重机列表',t,  'MainServlet_sys?fid=UII00.00.05.01&listMode=list')
MenuObj.additem('增加备案起重机',t,  'MainServlet_sys?fid=UII00.00.05.03&mode=before')
 
t=MenuObj.addtitle('特行人员','基本信息',0)
MenuObj.additem('特行人员列表',t,  'MainServlet_sys?fid=UII00.00.06.01&listMode=list')
MenuObj.additem('增加特行人员',t,  'MainServlet_sys?fid=UII00.00.06.03&mode=before')
 
//------------------------用户管理-------------------------3
t=MenuObj.addtitle('用户信息','用户管理',1)//1表示默认是打开的
MenuObj.additem('用户列表',t,  'MainServlet_sys?fid=UII00.03.01.01&listMode=list')
MenuObj.additem('增加用户',t,  'MainServlet_sys?fid=UII00.03.01.03&mode=before')
 
 
//------------------------监督整改-------------------------4
t=MenuObj.addtitle('监督计划','监督整改',1)//1表示默认是打开的
MenuObj.additem('监督计划表',t,  'MainServlet_sys?fid=UII00.04.01.01&listMode=list')
MenuObj.additem('新增监督计划',t,  'MainServlet_sys?fid=UII00.04.01.03&mode=before')
 
t=MenuObj.addtitle('检查计划','监督整改',0)
MenuObj.additem('检查计划表',t,  'MainServlet_sys?fid=UII00.04.02.01&listMode=list')
MenuObj.additem('新增检查计划',t,  'MainServlet_sys?fid=UII00.04.02.03&mode=before')
 
 
t=MenuObj.addtitle('项目整改','监督整改',0)
MenuObj.additem('项目整改列表',t,  'MainServlet_sys?fid=UII00.04.03.01&listMode=list')
MenuObj.additem('增加整改项目',t,  'MainServlet_sys?fid=UII00.04.03.03&mode=before')
 
 
//------------------------查询管理-------------------------5
t=MenuObj.addtitle('用户信息','查询管理',1)//1表示默认是打开的
MenuObj.additem('查询监督计划',t,  'MainServlet_sys?fid=UII00.03.01.01&listMode=list')
MenuObj.additem('查询检查记录',t,  'MainServlet_sys?fid=UII00.03.01.03&mode=before')
MenuObj.additem('查询项目信息',t,  'MainServlet_sys?fid=UII00.03.01.03&mode=before')
MenuObj.additem('查询单位信息',t,  'MainServlet_sys?fid=UII00.03.01.03&mode=before')
MenuObj.additem('查询设备备案信息',t,  'MainServlet_sys?fid=UII00.03.01.03&mode=before')
MenuObj.additem('查询安全生产数据库',t,  'MainServlet_sys?fid=UII00.03.01.03&mode=before')
 
 
//------------------------安全生产-------------------------6
t=MenuObj.addtitle('法律法规','安全生产',1)//1表示默认是打开的
MenuObj.additem('法律法规标准规范列表',t,  'MainServlet_sys?fid=UII00.06.01.01&listMode=list')
MenuObj.additem('新增法律法规标准规范',t,  'MainServlet_sys?fid=UII00.06.01.03&mode=before')
 
t=MenuObj.addtitle('各种文件','安全生产',0)
MenuObj.additem('文件表格模板列表',t,  'MainServlet_sys?fid=UII00.06.01.01&listMode=list')
MenuObj.additem('增加文件表格模板',t,  'MainServlet_sys?fid=UII00.06.01.03&mode=before')
MenuObj.additem('上传文件表格模板',t,  'MainServlet_sys?fid=UII00.06.01.03&mode=before')
 
//------------------------通知提醒-------------------------7
t=MenuObj.addtitle('下发通知','通知提醒',1)//1表示默认是打开的
MenuObj.additem('通知列表',t,  'MainServlet_sys?fid=UII00.07.01.01&listMode=list')
MenuObj.additem('增加通知',t,  'MainServlet_sys?fid=UII00.07.01.03&mode=before')
 
// t=MenuObj.addtitle('提醒设置','通知提醒',0)
// MenuObj.additem('提醒列表',t,  'MainServlet_sys?fid=UII00.03.01.01&listMode=list')
// MenuObj.additem('新增提醒',t,  'MainServlet_sys?fid=UII00.03.01.03&mode=before')
 
 
//------------------------信息公告-------------------------7
t=MenuObj.addtitle('信息编辑','信息公告',1)//1表示默认是打开的
MenuObj.additem('信息列表',t,  'MainServlet_sys?fid=UII00.08.01.01&listMode=list')
MenuObj.additem('增加信息',t,  'MainServlet_sys?fid=UII00.08.01.03&mode=before')
 
// t=MenuObj.addtitle('信息设置','信息公告',0)
// MenuObj.additem('提醒列表',t,  'MainServlet_sys?fid=UII00.03.01.01&listMode=list')
// MenuObj.additem('新增提醒',t,  'MainServlet_sys?fid=UII00.03.01.03&mode=before')
 
//------------------------签到管理-------------------------9
t=MenuObj.addtitle('签到规则','签到管理',1)//1表示默认是打开的
MenuObj.additem('规则列表',t,  'MainServlet_sys?fid=UII00.09.01.01&listMode=list')
MenuObj.additem('增加规则',t,  'MainServlet_sys?fid=UII00.09.01.03&mode=before')
t=MenuObj.addtitle('签到信息','签到管理',0)
MenuObj.additem('签到信息列表',t,  'MainServlet_sys?fid=UII00.09.02.01&listMode=list')
MenuObj.additem('签到',t,  'MainServlet_sys?fid=UII00.09.02.03&mode=before')
 
//------------------------系统维护-------------------------10
t=MenuObj.addtitle('系统日志','系统维护',1)//1表示默认是打开的
MenuObj.additem('查看日志列表',t,  'MainServlet_sys?fid=UII00.10.01.01&listMode=list')
MenuObj.additem('按条件查询日志',t,'syslog/query_log.jsp')
t=MenuObj.addtitle('数据维护','系统维护',0)
MenuObj.additem('查看数据维护设置',t,  'MainServlet_sys?fid=UII00.10.02.01&listMode=list')
MenuObj.additem('更改数据维护设置',t,  'MainServlet_sys?fid=UII00.10.02.04&id=1&mode=before')
 
 
//------------------------字典数据-------------------------11
t=MenuObj.addtitle('字典数据','字典数据',1)//1表示默认是打开的
MenuObj.additem('字典数据列表',t,  'MainServlet_sys?fid=UII99.99.99.01&listMode=list')
MenuObj.additem('增加字典数据',t,  'MainServlet_sys?fid=UII99.99.99.03&mode=before')
 
 
 
//网站首页
t=MenuObj.addtitle('网站首页','网站首页',1)
MenuObj.additem('网站首页',t,'tgyTest/init.html')
 
 
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
