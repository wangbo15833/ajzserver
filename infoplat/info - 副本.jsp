<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
//String 	work_unit = (String)hp_user.get("work_unit");
//String  usertype = (String)hp_user.get("usertype");
int i = 0 ;

try{
	String titleName = "我的动态信息";
	DBTrans dbtrans = new DBTrans();
%>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0"  >
<div id="man_zone" style=" height::700px;">
  <form name="formHead" action="./MainServlet_sys" target="sframe" onKeyDown="if(event.keyCode==13)event.keyCode=9" style="padding-top:5px;">
    <input type="hidden" name="fid" value="UII00.00.04.03">
    <TABLE class=table style="BORDER-COLLAPSE: collapse" borderColor=#dedfd7 cellSpacing=0 cellPadding=3 width="100%" align=center bgColor=#ffffff border=1>
      <tr>
        <td bgColor=#f0f0f0 height=25 colspan="4" align="center" class="text2" style=" color:#333"><font size="+2"><%=titleName%></font></td>
      </tr>
      <tr>
        <td style="width:65%;"><table width="99%" border="0" style="border-collapse:collapse;">
            <tr>
              <td><TABLE class="biankuang" border="0" cellSpacing="0" 
                        cellPadding="0" width="99%">
                  <TBODY>
                    <TR>
                      <TD height="33" 
                            colSpan="2" usemap="#Map"><div style="font-size: 13px; background-image:url(../image2/qyfc_04.gif) ;background-repeat:no-repeat; height:30px;"><A class="baibt" href="#" >
                          <div style="padding-top:10px;padding-left:50px;  text-align:left; color:#FFF; font-size:18px; text-decoration:none; font-weight:bold; float:left;">通   知</div>
                          </A> <A href="#" onclick="showman('../MainServlet_sys?fid=UII01.01.02.01&listMode=list&msgtype=通知&rec_name=<%=realname%>')">
                          <div style="padding-top:10px;padding-left:30px;  text-align:right; color:  #666; font-size:13px; text-decoration:none; font-weight:bold; float:right; "> 更多>></div>
                          </A> </div></TD>
                    </TR>
                    <TR>
                      <TD colSpan="2"><TABLE border="0" cellSpacing="0" cellPadding="0" width="100%">
                          <TBODY>
                            <TR>
                              <TD class="ziti" height="168" rowSpan="8" 
                                colSpan="3"><TABLE style="border-width: 0px; width: 98%; border-collapse: collapse;" 
                                id="" 
                                border="0" rules="all" cellSpacing="0" 
                                cellPadding="2">
                                  <TBODY>
                                    <%
									CachedRowSet crs = dbtrans.getResultBySelect("select top 6 i.* , tz.cont from inbox as i left join tongz as tz on i.msgcode= tz.tzcode where i.rec_name='"+realname+"' and i.msgtype='通知'  order by cast(isread as int) asc , i.deli_time desc ");
									i=0;
									while(crs.next()){
									i++;
									%>
									<TR style="font: 14px tahoma, arial, 宋体; ">
									<%if(crs.getString("isread").equals("0")){%>
                                      <TD style="border-color: rgb(153, 204, 255);" align="left">&nbsp; &nbsp; <A  href="javascript:;" style="text-decoration:none;" onclick="showman('../MainServlet_sys?fid=UII01.01.02.04&msgtype=通知&id=<%=crs.getString("id")%>&mode=before');return false;">·&nbsp;&nbsp;<font color="red"><%=Tools.omit(crs.getString("title"),25)%></font><img src="../image2/new02.gif" border="0" complete="complete"/></A></TD>
                                      <TD style="border-color: rgb(153, 204, 255); width: 25%; font-size: 14px;"  align="left"><%=Tools.omitnd(crs.getString("deli_time"),16)%></TD><%}else{%>
									  <TD style="border-color: rgb(153, 204, 255); " align="left">&nbsp; &nbsp; <A  href="javaxcript:;" style="text-decoration:none;" onclick="showman('../MainServlet_sys?fid=UII01.01.02.04&msgtype=通知&id=<%=crs.getString("id")%>&mode=before');return false;">·&nbsp;&nbsp;<%=Tools.omit(crs.getString("title"),25)%></A></TD>
                                      <TD style="border-color: rgb(153, 204, 255); width: 25%; font-size: 14px;"  align="left"><%=Tools.omitnd(crs.getString("deli_time"),16)%></TD>
									  <%}%>
                                    </TR>
									<%}
									for(;i<6;i++){
									%>
									<TR style="font: 12px/1.5 tahoma, arial, 宋体;">
                                      <TD style="border-color: rgb(153, 204, 255); " align="left">&nbsp; &nbsp;</TD>
                                      <TD style="border-color: rgb(153, 204, 255); width: 25%; font-size: 14px;"  align="left"></TD>
                                    </TR><%
									}
									%>
                                  </TBODY>
                                </TABLE>
								
								</TD>
                            </TR>
                          </TBODY>
                        </TABLE></TD>
                    </TR>
                  </TBODY>
                </TABLE></td>
            </tr>
          </table></td>
        <td rowspan="2" style=" vertical-align:top;"><table width="99%" border="0" style="border-collapse:collapse;">
            <tr>
              <td><TABLE class="biankuang" border="0" cellSpacing="0" cellPadding="0" width="99%">
                  <TBODY>
                    <TR>
                      <TD height="33" 
                            colSpan="2" usemap="#Map"><div style="font-size: 13px; background-image:url(../image2/qyfc_05.gif) ;background-repeat:no-repeat; height:30px;"><A class="baibt" href="#" >
                          <div style="padding-top:10px;padding-left:50px;  text-align:left; color:#FFF; font-size:18px; text-decoration:none; font-weight:bold; float:left;">公   告</div>
                          </A> <A href="#" onclick="showman('../MainServlet_sys?fid=UII01.01.02.01&listMode=list&msgtype=公告&rec_name=<%=realname%>')" >
                          <div style="padding-top:10px;padding-left:30px;  text-align:right; color:  #666; font-size:13px; text-decoration:none; font-weight:bold; float:right; "> 更多>></div>
                          </A> </div></TD>
                    </TR>
                    <TR>
                      <TD colSpan="2"><TABLE border="0" cellSpacing="0" cellPadding="0" width="100%">
                            <TR>
                              <TD class="ziti" height="168" rowSpan="8" 
                                colSpan="3"><TABLE style="border-width: 0px; width: 100%; border-collapse: collapse;" 
                                id="ctl00_ContentPlaceHolder2_dg_materialinfo" 
                                border="0" rules="all" cellSpacing="0" 
                                cellPadding="2">
								  <div  style="font: 12px tahoma, arial, 宋体; " >
								   <div id="jqnews">
								<%
									crs = dbtrans.getResultBySelect("select top 6 i.* , gg.cont from inbox as i left join gginfo as gg on i.msgcode= gg.ggcode where i.rec_name='"+realname+"' and i.msgtype='公告'  order by cast(isread as int) asc , i.deli_time desc");
								  while(crs.next()){
								  if (crs.getString("isread").equals("0")){
								  %>
									<div>
									<A style="text-decoration:none;" href="javascript:;" onclick="showman('../MainServlet_sys?fid=UII01.01.02.04&msgtype=公告&id=<%=crs.getString("id")%>&mode=before');return false;"><font style="font-size:10px;color:orange;font: 隶书;">标题:</font>&nbsp;&nbsp;<font style=" color:orange;font:15px;"><%=Tools.omit(crs.getString("title"),15)%><img src="../image2/new02.gif" border="0" complete="complete"/></br></font><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;<%=Tools.omit(crs.getString("cont").replaceAll("%40",""),60)%></br></font></A>
									<span style="text-align:right;width:96%;height:20px;margin 0px;"><%=Tools.omitnd(crs.getString("deli_time"),16)%></span>
									</div>
									<%
									}else{
									%>
									<div>
									<A style="text-decoration:none;" href="javascript:;" onclick="showman('../MainServlet_sys?fid=UII01.01.02.04&msgtype=公告&id=<%=crs.getString("id")%>&mode=before');return false;"><font style="font-size:10px;color:blue;font: 隶书;">标题:</font>&nbsp;&nbsp;<font style=" color:blue;font:15px;"><%=Tools.omit(crs.getString("title"),15)%></font></br>&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue"><%=Tools.omit(crs.getString("cont"),60)%></br></font></A>
									<span style="text-align:right;width:96%;height:20px;margin 0px;"><%=Tools.omitnd(crs.getString("deli_time"),16)%></span>
									</div>
									<%
									}
									}
									%></div>
									</div>
                                </TABLE></TD>
                            </TR>
                        </TABLE></TD>
                    </TR>
                  </TBODY>
                </TABLE></td>
            </tr>
          </table>
           </td>
      </tr>
      <tr>
      <td>
      <table width="99%" border="0" style="border-collapse:collapse;">
            <tr>
              <td><TABLE class="biankuang" border="0" cellSpacing="0" 
                        cellPadding="0" width="99%">
                  <TBODY>
                    <TR>
                      <TD height="33" 
                            colSpan="2" usemap="#Map"><div style="font-size: 13px; background-image:url(../image2/qyfc_06.gif) ;background-repeat:no-repeat; height:30px;"><A class="baibt" href="#">
                          <div style="padding-top:10px;padding-left:50px;  text-align:left; color:#FFF; font-size:18px; text-decoration:none; font-weight:bold; float:left;">提   醒</div>
                          </A> <A href="#" onclick="showman('../MainServlet_sys?fid=UII01.01.02.01&listMode=list&msgtype=提醒&rec_name=<%=realname%>')">
                          <div style="padding-top:10px;padding-left:30px;  text-align:right; color:  #666; font-size:13px; text-decoration:none; font-weight:bold; float:right; "> 更多>></div>
                          </A> </div></TD>
                    </TR>
                    <TR>
                      <TD colSpan="2"><TABLE border="0" cellSpacing="0" cellPadding="0"  width="100%">
                          <TBODY>
                            <TR>
                              <TD class="ziti" height="168" rowSpan="8" 
                                colSpan="3"><TABLE style="border-width: 0px; width: 98%; border-collapse: collapse;"  id="" 
                                border="0" rules="all" cellSpacing="0" 
                                cellPadding="2">
								<TBODY>
                                    <%
									i=0;
									crs = dbtrans.getResultBySelect("select top 6 i.* , t.cont from inbox as i left join txinfo as t on i.msgcode= t.txcode where i.rec_name='"+realname+"' and i.msgtype='提醒' order by cast(isread as int) asc , i.deli_time desc");
									while(crs.next()){
									i++;
									%>
									<TR style="font: 14px tahoma, arial, 宋体; ">
									<%if(crs.getString("isread").equals("0")){%>
                                      <TD style="border-color: rgb(153, 204, 255);" align="left">&nbsp; &nbsp; <A  href="javascript:;" style="text-decoration:none;" onclick="showman('../MainServlet_sys?fid=UII01.01.02.04&msgtype=提醒&id=<%=crs.getString("id")%>&mode=before');return false;">·&nbsp;&nbsp;<font color="red"><%=Tools.omit(crs.getString("title"),25)%></font><img src="../image2/new02.gif" border="0" complete="complete"/></A></TD>
                                      <TD style="border-color: rgb(153, 204, 255); width: 25%; font-size: 14px;"  align="left"><%=Tools.omitnd(crs.getString("deli_time"),16)%></TD><%}else{%>
									  <TD style="border-color: rgb(153, 204, 255); " align="left">&nbsp; &nbsp; <A  href="javascript:;" style="text-decoration:none;" onclick="showman('../MainServlet_sys?fid=UII01.01.02.04&msgtype=提醒&id=<%=crs.getString("id")%>&mode=before');return false;">·&nbsp;&nbsp;<%=Tools.omit(crs.getString("title"),25)%></A></TD>
                                      <TD style="border-color: rgb(153, 204, 255); width: 25%; font-size: 14px;"  align="left"><%=Tools.omitnd(crs.getString("deli_time"),16)%></TD>
									  <%}%>
                                    </TR>
									<%}
									for(;i<6;i++){
									%>
									<TR style="font: 12px/1.5 tahoma, arial, 宋体;">
                                      <TD style="border-color: rgb(153, 204, 255); " align="left">&nbsp; &nbsp;</TD>
                                      <TD style="border-color: rgb(153, 204, 255); width: 25%; font-size: 14px;"  align="left"></TD>
                                    </TR><%
									}
									%>
									
									
                                  </TBODY>
                                </TABLE>
                                </TD>
                            </TR>
                          </TBODY>
                        </TABLE></TD>
                    </TR>
                  </TBODY>
                </TABLE></td>
            </tr>
          </table></td>
      </tr>
    </TABLE>
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
