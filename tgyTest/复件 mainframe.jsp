<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.zagerbone.util.DBTrans"%>
<%@ page import="com.zagerbone.util.Tools"%>
<%
String usertype = null;
/*
EventResponse 	eventResponse 	= (EventResponse)request.getAttribute("result");  
if(eventResponse.isSuccessFlag()){ 
	HashMap hp_general = (HashMap)eventResponse.getGeneralBody();
	HashMap hp_user = null;
	hp_user = (HashMap)hp_general.get("hp_user");
	System.out.println("hp_user======***======"+hp_user);
	usertype = (String)hp_user.get("usertype");
	//request.getSession();
	session.setAttribute("Myusertype", usertype);
}
*/	
HashMap hp_user = (HashMap)session.getAttribute("hp_user");
String 	username = (String)hp_user.get("realname");
String 	work_unit = (String)hp_user.get("work_unit");
System.out.println("hp_user====="+hp_user);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link rel="stylesheet" href="css/common.css" type="text/css" />
<title>导航栏</title>

<style type="text/css">
#my_sub_info {
	width: 100%;
	float:left;
	background-image: url(../images/manage_r3_c11.jpg);
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 2px;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-right-color: #B4C9C6;
	border-bottom-color: #B4C9C6;
	border-left-color: #f1f1f1;
	height: 27px;
	background-repeat: repeat-x;
	padding: 5px 0 0 0;
	color:#ffffff;
}
</style>
</head>
<script  type="text/javascript">
/**
*   COPYRIGHT NOTICE
*   Copyright (c) 2013, 李振南 (版权声明)
*   All rights reserved.
*
*   @file   mainframe.html
*   @function  list_sub_nav
*   @brief
*		当点击时候改变 属性
*		往左边传递参数
*   @retval 
*
*   @version    1.0
*   @author floatsnowflakes(floatsnowflakes@hotmail.com)
*   @date   2013-3-25
*
*   @NOTE
*/
var preClassName = "man_nav_01";
function list_sub_nav(Id,sortname){
   if(preClassName != ""){
      getObject(preClassName).className="bg_image";
   }
   
   if(getObject(Id).className == "bg_image"){
      getObject(Id).className="bg_image_onclick";
      preClassName = Id;	//保持全局变量
			showInnerText(Id);	//设置提示
			//联动左边栏目  MenuObj
			window.top.indexFrame.frames['leftFrame'].MenuObj.getbytitle(sortname);
			window.top.indexFrame.frames['leftFrame'].MenuObj.getdefaultnav(sortname);
	}
}

/**
*   COPYRIGHT NOTICE
*   Copyright (c) 2013, 李振南 (版权声明)
*   All rights reserved.
*
*   @file   mainframe.html
*   @function  showInnerText
*   @brief
*		建设工程安全管理服务系统示相应的提示*		
*   @retval 
*		传入ID值
*   @version    1.0
*   @author floatsnowflakes(floatsnowflakes@hotmail.com)
*   @date   2013-3-25
*
*   @NOTE	截去前8建设工程安全管理服务系统为数字
*/
function showInnerText(Id){
    var switchId = parseInt(Id.substr(8,2),10);
	var showText = "对不起没有信息！";
	switch(switchId){
	   	case 1:
		   showText =  "建设工程安全管理服务系统相应的提示操作！";
		   break;
	   	case 2:
		   showText =  "建设工程安全管理服务系统建设工程安全管理服务系统修改、删除！";
		   break;
	   	case 3:
		   showText =  "建设工程安全管理服务系统，等基本信息！";
		   break;		   
	   	case 4:
		   showText =  "建设工程安全管理服务系统建设工程安全管理服务系统进行查询。";
		   break;	
	   	case 5:
		   showText =  "建设工程安全管理服务系统，修改标准界限";
		   break;	
		case 6:
		   showText =  "建设工程安全管理服务系统权限设置！";
		   break;		
		case 7:
		   showText =  "建设工程安全管理服务系统的问题！";
		   break;
		case 8:
		   showText =  "";
		case 9:
		   showText =  "建设工程安全管理服务系统使用！";
		   break;
		case 10:
		   showText =  "建设工程安全管理服务系统使用！";
		   break;
		case 11:
		   showText =  "建设工程安全管理服务系统日志、数据维护！";
		   break;
		case 12:
		   showText =  "建设工程安全管理服务系统使用！";
		   break;
		default:
			break;
	}
	//对id=show_text进行设置内容
	getObject('show_text').innerHTML = showText;
}
 
 /**
*   COPYRIGHT NOTICE
*   Copyright (c) 2013, 李振南 (版权声明)
*   All rights reserved.
*
*   @file   mainframe.html
*   @function  getObject
*   @brief
*		获取该ID得对象
*   @retval 
*		传入ID值
*   @version    1.0
*   @author floatsnowflakes(floatsnowflakes@hotmail.com)
*   @date   2013-3-25
*
*   @NOTE	兼容获取
*/
 function getObject(objectId) {
    if(document.getElementById && document.getElementById(objectId)) {
	// W3C DOM
	return document.getElementById(objectId);
    } else if (document.all && document.all(objectId)) {
	// MSIE 4 DOM
	return document.all(objectId);
    } else if (document.layers && document.layers[objectId]) {
	// NN 4 DOM.. note: this won't find nested layers
	return document.layers[objectId];
    } else {
	return false;
    }
}
</script>
<body>
<div id="nav">
    <ul>
      <li id="man_nav_01" onclick="list_sub_nav(id,'网站首页')"  class="bg_image_onclick">网站首页</li>
      <li id="man_nav_02" onclick="list_sub_nav(id,'角色管理')"  class="bg_image">角色管理</li>
      <li id="man_nav_03" onclick="list_sub_nav(id,'基本信息')"  class="bg_image">基本信息</li>
      <li id="man_nav_04" onclick="list_sub_nav(id,'用户管理')"  class="bg_image">用户管理</li>
      <li id="man_nav_05" onclick="list_sub_nav(id,'监督整改')"  class="bg_image">监督整改</li>
	  <li id="man_nav_06" onclick="list_sub_nav(id,'查询管理')"  class="bg_image">查询管理</li>
      <li id="man_nav_07" onclick="list_sub_nav(id,'安全生产')"  class="bg_image">安全生产</li>
      <li id="man_nav_08" onclick="list_sub_nav(id,'通知提醒')"  class="bg_image">通知提醒</li>
      <li id="man_nav_09" onclick="list_sub_nav(id,'信息公告')"  class="bg_image">信息公告</li>
      <li id="man_nav_10" onclick="list_sub_nav(id,'签到管理')"  class="bg_image">签到管理</li>
      <li id="man_nav_11" onclick="list_sub_nav(id,'系统维护')"  class="bg_image">系统维护</li>
      <li id="man_nav_12" onclick="list_sub_nav(id,'字典数据')"  class="bg_image">字典数据</li>
  </ul>
  
  <div style="float:right;" ><font onclick="my_quit();"   style="cursor:pointer;" >退出系统</font>&nbsp;&nbsp;&nbsp;&nbsp;</div>
</div>
<script>
function my_quit(){
	top.location ='../login/login_quit.jsp?sessionkill=1';
}
</script>
<div style="float:left;width:" id="sub_info">&nbsp;登录人:<%=work_unit%>,<%=username%>&nbsp;||&nbsp;&nbsp;&nbsp;&nbsp;<span id="show_text">建设工程安全管理服务系统信息系统!</span></div>
</body>
</html>
