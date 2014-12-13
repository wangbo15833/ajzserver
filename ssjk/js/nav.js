// 导航栏配置文件
var outlookbar=new outlook();
var t;

/**
*   COPYRIGHT NOTICE
*   Copyright (c) 2010, 雷贵亮 (版权声明)
*   All rights reserved.
*
*   @file   nav.js
*   @brief
*		点击技术标准
*		改变left内容
*
*   @version    1.0
*   @author JesseRei(jesse.lgl@gmail.com)
*   @date   2010-7-15
*/
t=outlookbar.addtitle('技术标准','技术标准',1)
outlookbar.additem('添加标准',t,'addTec.html')
outlookbar.additem('标准列表',t,'listTec.html')
outlookbar.additem('查询标准',t,'searchTec.html')


/**
*   COPYRIGHT NOTICE
*   Copyright (c) 2010, 雷贵亮 (版权声明)
*   All rights reserved.
*
*   @file   nav.js
*   @brief
*		点击车辆管理
*		改变left内容
*
*   @version    1.0
*   @author JesseRei(jesse.lgl@gmail.com)
*   @date   2010-7-15
*/
t=outlookbar.addtitle('历史档案','历史档案',1)
outlookbar.additem('历史数据查询',t,'xx.php')
outlookbar.additem('检测报告查询',t,'xx.php')
outlookbar.additem('历史数据曲线',t,'xx.php')
outlookbar.additem('历史轨迹查询',t,'xx.php')

/**
*   COPYRIGHT NOTICE
*   Copyright (c) 2010, 雷贵亮 (版权声明)
*   All rights reserved.
*
*   @file   nav.js
*   @brief
*		点击车辆管理
*		改变left内容
*
*   @version    1.0
*   @author JesseRei(jesse.lgl@gmail.com)
*   @date   2010-7-15
*/
t=outlookbar.addtitle('车辆管理','车辆管理',1)
outlookbar.additem('监控车辆列表',t,			'../MainServlet_sys?fid=FCL00.00.00.02&listMode=list')
outlookbar.additem('监控车辆查询',t,			'../syclgl/searchVehicle.jsp?listMode=list')
outlookbar.additem('增加监控车辆',t,			'../syclgl/addVehicle.html')
outlookbar.additem('修改与删除监控车辆',t,'../syclgl/searchVehicle.jsp')
//outlookbar.additem('监控车辆列表',t,'../syclgl/listVehicle.html')

//outlookbar.additem('删除监控车辆',t,'../syclgl/list.jsp')

outlookbar.additem('工作时间清零',t,'xx.php')
outlookbar.additem('设置回报间隔',t,'xx.php')

/**
*   COPYRIGHT NOTICE
*   Copyright (c) 2010, 雷贵亮 (版权声明)
*   All rights reserved.
*
*   @file   nav.js
*   @brief
*		点击实时监控
*		改变left内容
*
*   @version    1.0
*   @author JesseRei(jesse.lgl@gmail.com)
*   @date   2010-7-15
*/
t=outlookbar.addtitle('监控车辆','实时监控',1)
outlookbar.additem('试验车辆1',t,'../ssjk/manframe.jsp?Cname=1&Tnum=1')
outlookbar.additem('试验车辆2',t,'../ssjk/manframe.jsp?Cname=2&Tnum=2')
outlookbar.additem('试验车辆3',t,'xx.php')
outlookbar.additem('试验车辆4',t,'xx.php')
outlookbar.additem('试验车辆5',t,'xx.php')
outlookbar.additem('试验车辆6',t,'xx.php')
outlookbar.additem('试验车辆7',t,'xx.php')
outlookbar.additem('试验车辆8',t,'xx.php')
outlookbar.additem('试验车辆9',t,'xx.php')

t=outlookbar.addtitle('检测完毕','实时监控',0)
outlookbar.additem('试验车辆1',t,'xx.php')
outlookbar.additem('试验车辆2',t,'xx.php')
outlookbar.additem('试验车辆3',t,'xx.php')
outlookbar.additem('试验车辆4',t,'xx.php')




/**
*   COPYRIGHT NOTICE
*   Copyright (c) 2010, 雷贵亮 (版权声明)
*   All rights reserved.
*
*   @file   nav.js
*   @brief
*		点击网站首页
*		改变left内容
*
*   @version    1.0
*   @author JesseRei(jesse.lgl@gmail.com)
*   @date   2010-7-15
*/
t=outlookbar.addtitle('网站首页','网站首页',1)
outlookbar.additem('网站首页',t,'xx.php')

/**
*   COPYRIGHT NOTICE
*   Copyright (c) 2010, 雷贵亮 (版权声明)
*   All rights reserved.
*
*   @file   nav.js
*   @brief
*		点击网站首页
*		改变left内容
*
*   @version    1.0
*   @author JesseRei(jesse.lgl@gmail.com)
*   @date   2010-7-15
*/
t=outlookbar.addtitle('在线帮助','在线帮助',1)
outlookbar.additem('登入官方网站',t,'http://www.ejixie.cn/')
outlookbar.additem('登入官方网站',t,'http://www.ejixie.cn/')
outlookbar.additem('登入官方网站',t,'http://www.ejixie.cn/')
outlookbar.additem('登入官方网站',t,'http://www.ejixie.cn/')
outlookbar.additem('发送邮件1',t,'mailto:jesse.lgl@gmail.com')
outlookbar.additem('发送邮件2',t,'mailto:jesse.lgl@gmail.com')
outlookbar.additem('发送邮件3',t,'mailto:jesse.lgl@gmail.com')
outlookbar.additem('发送邮件4',t,'mailto:jesse.lgl@gmail.com')
