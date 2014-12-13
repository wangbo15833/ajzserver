<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/tr/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ 
page contentType="text/html; charset=gb2312"
import="com.zagerbone.user.User"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="java.text.*"%>
<%@ include  file='../identification.jsp'  
%>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.zagerbone.util.DBTrans"%>
<%@ page import="com.zagerbone.util.Tools"%>
<head>
<title>动态信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet"  href="../css/common.css" type="text/css" />
<link rel="stylesheet"  href="../js/slidenews/style.css"  	type="text/css">
<script language="JScript" type="text/javascript"  src="../js/slidenews/jq1-4-1.js"> </script>
<script language="JScript" type="text/javascript"  src="../js/slidenews/jqNews.js"> </script>
<style type=text/css>
div{font-size:12px;font-family:arial}.baidu{font-size:14px;line-height:24px;font-family:arial}
a,a:link{color:#0000cc;} 
.baidu span{color:#6f6f6f;font-size:12px}
a.more{color:#008000;}a.blk{color:#000;font-weight:bold;}
#linknav{text-align:center;}
#linknav ul{padding:0px; margin:0px; border:0px; list-style:none;}
#linknav ul li{width:120px;float:left; line-height:22px; color:#454545; font-size:16px;text-align:center;}
</style> 
</head>
<%
	/*
	String flag1 =(String)request.getAttribute("flag1"); 
	if (flag1==null || flag1.equals(""))
	 {  
		flag1 ="false" ;
		User user = new User();
		user = (User)session.getAttribute("curUser");
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CachedRowSet crs_cols = (CachedRowSet)request.getAttribute("crs_cols");
		ArrayList al_colname = new ArrayList();
		ArrayList al_colnamec = new ArrayList();
		ArrayList al_datasrc = new ArrayList();
		ArrayList al_datasrctype = new ArrayList();
		while(crs_cols.next()){
			al_colname.add((String)crs_cols.getString("colname"));
			al_colnamec.add((String)crs_cols.getString("colnamec"));
			al_datasrc.add((String)crs_cols.getString("datasrc"));
			al_datasrctype.add((String)crs_cols.getString("datasrctype"));
		}
		*/
		
		
//HashMap hp_user = (HashMap)session.getAttribute("hp_user");
String 	realname = (String)hp_user.get("realname");
//String 	username = (String)hp_user.get("username");
//String 	work_unit = (String)hp_user.get("work_unit");
//String  usertype = (String)hp_user.get("usertype");
int i = 0 ;

try{
	String titleName = "我的动态信息";
	DBTrans dbtrans = new DBTrans();
	CachedRowSet crs = null;
%>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone" style=" height::700px;">
  <form name="formHead" action="./MainServlet_sys" target="sframe" onKeyDown="if(event.keyCode==13)event.keyCode=9" style="padding-top:5px;">
    <input type="hidden" name="fid" value="UII00.00.04.03">
    <table class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
      <tr>
        <td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2" style=" color:#333"><font size="+2"><%=titleName%></font></td>
      </tr>
      <tr>
        <td style="width:65%;"><table width="99%" border="0" style="border-collapse:collapse;">
            <tr>
              <td>
			  <div style="font-size: 13px; background-image:url(../image2/qyfc_06.gif) ;background-repeat:no-repeat; height:30px;"><A class="baibt" href="#" >
                          <div style="padding-top:5px;padding-left:50px;  text-align:left; color:#FFF; font-size:18px; text-decoration:none; font-weight:bold; float:left;">天  气</div>
                          </A> <A href="#" onclick="showman('../MainServlet_sys?fid=UII01.01.02.01&listMode=list&msgtype=通知&rec_name=<%=realname%>')">
                          </A> </div>
	  <iframe name="weather_inc" src="http://tianqi.xixik.com/cframe/2" width="650" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
			  <table class="biankuang" border="0" cellSpacing="0" 
                        cellPadding="0" width="99%">
                  <TBODY>
                    <tr>
                      <TD height="33" 
                            colSpan="2" usemap="#Map"><div style="font-size: 13px; background-image:url(../image2/qyfc_04.gif) ;background-repeat:no-repeat; height:30px;"><A class="baibt" href="#" >
                          <div style="padding-top:5px;padding-left:50px;  text-align:left; color:#FFF; font-size:18px; text-decoration:none; font-weight:bold; float:left;">新   闻</div>
                          </A> <A href="#" onclick="showman('../MainServlet_sys?fid=UII01.01.02.01&listMode=list&msgtype=通知&rec_name=<%=realname%>')">
                          </A> </div></TD>
                    </tr>
                    <tr>
                      <TD colSpan="2"><table border="0" cellSpacing="0" cellPadding="0" width="100%">
                          <TBODY>
                            <tr>
                              <TD class="ziti" height="168" rowSpan="8" 
                                colSpan="3"><table style="border-width: 0px; width: 98%; border-collapse: collapse;" 
                                id="" 
                                border="0" rules="all" cellSpacing="0" 
                                cellPadding="2">
                                  <TBODY>
								  <frame>
<script language="JavaScript" type="text/JavaScript" src="http://news.baidu.com/n?cmd=1&class=internet&pn=1&tn=newsbrofcu"></script>

<script type="text/javascript">
<!--
/*
 * 隐藏 Baidu Logo。
 */
/*var oImgs = document.getElementsByTagName("img");
var baiduLogo = "http://www.baidu.com/search/img/slogo-news.gif";
for (var i=0; i<oImgs.length; i++)
{
    if (oImgs[i].src == baiduLogo)
    {
        oImgs[i].style.display = "none";
    }
}
*/


var oDivs = document.getElementsByTagName("div");
for (var i=0; i<oDivs.length; i++)
{
if (oDivs[i].style.marginTop == "5px"&&oDivs[i].style.fontSize=="12px")
{
oDivs[i].style.display = "none";
}
}

var oDivss = document.getElementsByTagName("div");
for (var i=0; i<oDivss.length; i++)
{
if (oDivss[i].style.marginBottom == "10px")
{
oDivss[i].style.display = "none";
}
}
//-->
</script>
</frame>
                                  </TBODY>
                                </table>
								
								</TD>
                            </tr>
                          </TBODY>
                        </table></TD>
                    </tr>
                  </TBODY>
                </table></td>
            </tr>
          </table></td>
        <td rowspan="2" style=" vertical-align:top;"><table width="99%" border="0" style="border-collapse:collapse;">
            <tr>
              <td><table class="biankuang" border="0" cellSpacing="0" cellPadding="0" width="99%">
                  <TBODY>
                    <tr>
                      <TD height="33" 
                            colSpan="2" usemap="#Map"><div style="font-size: 13px; background-image:url(../image2/qyfc_05.gif) ;background-repeat:no-repeat; height:30px;"><A class="baibt" href="#" >
                          <div style="padding-top:5px;padding-left:50px;  text-align:left; color:#FFF; font-size:18px; text-decoration:none; font-weight:bold; float:left;">公   告</div>
                          </A> <A href="#" onclick="showman('../MainServlet_sys?fid=UII01.01.02.06&listMode=list&msgtype=公告')" >
                          <div style="padding-top:5px;padding-left:30px;  text-align:right; color:  #666; font-size:13px; text-decoration:none; font-weight:bold; float:right; "> 更多>></div>
                          </A> </div></TD>
                    </tr>
                    <tr>
                      <TD colSpan="2"><table border="0" cellSpacing="0" cellPadding="0" width="100%">
                            <tr>
                              <TD class="ziti" height="168" rowSpan="8" 
                                colSpan="3"><table style="border-width: 0px; width: 100%; border-collapse: collapse;" 
                                id="ctl00_ContentPlaceHolder2_dg_materialinfo" 
                                border="0" rules="all" cellSpacing="0" 
                                cellPadding="2">
								  <div  style="font: 12px tahoma, arial, 宋体; " >
								   <div id="jqnews">
								<%
									crs = dbtrans.getResultBySelect("select top 6 *  from gginfo order by isNew desc,gg_time desc");
								  while(crs.next()){
								  if (crs.getString("isNew").equals("是")){
								  %>
									<div>
									<!--<A style="text-decoration:none;" href="javascript:;" onclick="showman('../MainServlet_sys?fid=UII01.01.02.04&msgtype=公告&id=<%=crs.getString("id")%>&mode=before');return false;"><font style="font-size:10px;color:orange;font: 隶书;">标题:</font>&nbsp;&nbsp;<font style=" color:orange;font:15px;"><%=Tools.omit(crs.getString("title"),15)%><img src="../image2/new02.gif" border="0" complete="complete"/></br></font><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;<%=Tools.omit(crs.getString("cont").replaceAll("%40",""),60)%></br></font></A>
									<span style="text-align:right;width:96%;height:20px;margin 0px;"><%=Tools.omitnd(crs.getString("gg_time"),16)%></span>
									</div>-->
									<A style="text-decoration:none;" href="javascript:;" onclick="showman('../MainServlet_sys?fid=UII01.01.02.08&msgtype=公告&id=<%=crs.getString("id")%>&mode=before');return false;"><font style="font-size:10px;color:orange;font: 隶书;">标题:</font>&nbsp;&nbsp;<font style=" color:orange;font:15px;"><%=Tools.omit(crs.getString("title"),15)%><img src="../image2/new02.gif" border="0" complete="complete"/></br></font><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;<%=Tools.omit(crs.getString("cont").replaceAll("%40",""),60)%></br></font></A>
									<span style="text-align:right;width:96%;height:20px;margin 0px;"><%=Tools.omitnd(crs.getString("gg_time"),16)%></span>
									</div>
									<%
									}else{
									%>
									<div>
									<A style="text-decoration:none;" href="javascript:;" onclick="showman('../MainServlet_sys?fid=UII01.01.02.08&msgtype=公告&id=<%=crs.getString("id")%>&mode=before');return false;"><font style="font-size:10px;color:blue;font: 隶书;">标题:</font>&nbsp;&nbsp;<font style=" color:blue;font:15px;"><%=Tools.omit(crs.getString("title"),15)%></font></br>&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue"><%=Tools.omit(crs.getString("cont"),60)%></br></font></A>
									<span style="text-align:right;width:96%;height:20px;margin 0px;"><%=Tools.omitnd(crs.getString("gg_time"),16)%></span>
									</div>
									<%
									}
									}
									%></div>
									</div>
                                </table></TD>
                            </tr>
                        </table></TD>
                    </tr>
                  </TBODY>
                </table></td>
            </tr>
          </table>
           </td>
      </tr>
      <tr>
      <td>

      <table width="99%" border="0" style="border-collapse:collapse;">
            <tr>
              <td><table class="biankuang" border="0" cellSpacing="0" cellPadding="0" width="100%">
                    <tr style="height:30px;">
                      <TD height="33" ><div style="font-size: 13px; background-image:url(../image2/lianjie.gif) ;background-repeat:no-repeat; height:30px;"><A class="baibt" href="#">
                          <div style="padding-top:5px;padding-left:50px;  text-align:left; color:#FFF; font-size:18px; text-decoration:none; font-weight:bold; float:left;">链  接</div>
                          </A></div></TD>
                    </tr>
                    <tr>
					<td>
						  <div id="linknav">
						  <ul>

		<li><a href="http://www.baidu.com">百度</a><span class="split">|</span><a href="http://tieba.baidu.com">贴吧</a></li>

		<li><a href="http://www.sina.com.cn">新浪</a><span class="split">|</span><a href="http://weibo.com">微博</a></li>

		<li><a href="http://www.sohu.com">搜狐</a><span class="split">|</span><a href="http://tv.sohu.com">视频</a></li>

		<li><a href="http://www.163.com">网易</a><span class="split">|</span><a href="http://email.163.com">邮箱</a></li>

		<li><a href="http://www.ifeng.com">凤凰</a><span class="split">|</span><a href="http://news.ifeng.com/mil">军事</a></li>
	</ul>
	</div>
	</td>
                    </tr>
                </table></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none;"></iframe>
</iframe>
<script>
function showman(s)
{
	window.top.indexFrame.frames['manFrame'].location = s;
}
</script>

<%
}catch(Exception ex){
	ex.printStackTrace();
}
%>
