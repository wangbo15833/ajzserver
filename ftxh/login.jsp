<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="java.util.HashMap"%>
<%
String sessionkill = request.getParameter("sessionkill");
if(sessionkill != null && sessionkill.equals("1")){
	System.out.println("sessionkill============================:"+sessionkill);
	session.invalidate();
	System.out.println("session============================:"+session);
	try{
	System.out.println("session============================:"+session.getAttribute("hp_user"));
	}catch(Exception ex){
		ex.printStackTrace();
	}
}
%>

<HTML><HEAD><TITLE>建设工程安全管理服务系统</TITLE>

<link rel="shortcut icon" href="favicon.ico"/>
<script language="JScript" type="text/javascript"  src="js/jquery.min.js"> </script>
<META http-equiv=Content-Type content="text/html; charset=gb2312">

<STYLE type=text/css>
.style1 {
	FONT-SIZE: 12px
}
.textinput {
	BORDER-RIGHT: #dddddd 1px solid; BORDER-TOP: #999999 1px solid; FONT-SIZE: 12px; BORDER-LEFT: #999999 1px solid; BORDER-BOTTOM: #dddddd 1px solid; FONT-FAMILY: "宋体"
}
.selectab {
	BORDER-RIGHT: #dddddd 1px solid; BORDER-TOP: #999999 1px solid; FONT-SIZE: 12px; BORDER-LEFT: #999999 1px solid; BORDER-BOTTOM: #dddddd 1px solid; FONT-FAMILY: "宋体"
}
</STYLE>

<META content="MSHTML 6.00.2900.2180" name=GENERATOR></HEAD>
<BODY style="OVERFLOW: hidden" leftMargin=0 topMargin=0  marginheight="0" marginwidth="0">
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe> 
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TR vAlign=center>
    <TD align=middle>
      <TABLE  style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid" cellSpacing=0 cellPadding=0 width=796 align=center border=0>
        <TR><TD background=ftxh/imags/login/top_bg.gif colSpan=2 height=62 
				><div style="height:30px; margin-bottom:10px; vertical-align:middle;font-size:24px;color:#2A3FFF; font-weight:bold; text-align:center">&nbsp;建设工程安全管理服务系统<a href="#" style="color:gray;font-size:12px;color:#999">测试版</a></div></TD></TR>
       <TR>
          <TD width=389 bgColor=#f7f7ff>
            <TABLE cellSpacing=0 cellPadding=0 width=389 bgColor=#f7f7ff border=0>
              <TBODY>
              <TR>
                <TD height=12></TD></TR>
              <TR>
               
              </TR></TBODY></TABLE>
            <img height=199 alt=""  src="ftxh/imags/login/sys_title.gif" width=389 border=0 name=登录界面_r5_c1></TD>
          <TD width=405 bgColor=#f7f7ff>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TR>
                <TD bgColor=#f7f7ff colSpan=4 height=28>&nbsp;</TD></TR>
              <TR>
                <TD><IMG height=33 alt=""  src="ftxh/imags/login/login_left.jpg" width=36 border=0 name=登录界面_r4_c2></TD>
                <TD background=ftxh/imags/login/login_top.gif>&nbsp;</TD>
                <TD><IMG height=33 alt=""  src="ftxh/imags/login/login_right.jpg" width=34 border=0  name=登录界面_r4_c4></TD>
                <TD width=31 bgColor=#f7f7ff rowSpan=3>&nbsp;</TD></TR>
              <TR>
                <TD background=ftxh/imags/login/login_left_bg.gif>&nbsp;</TD>
                <TD width=304>
                  <TABLE height=143 cellSpacing=0 cellPadding=0 width="100%" bgColor=#ffffff border=0>
                    <TBODY>
					<form name="formHead" action="MainServlet_sys"  method="post">
                    <TR><input type="hidden" name="fid" value="F00.00.00.05">
										<!--input type="hidden" name="companyName" value="tgy"-->
                      <TD align=right width="30%"><SPAN class=style1><FONT color=#8c8e8c>用户名：</FONT></SPAN></TD>
                      <TD><INPUT class="textinput" style="WIDTH: 160px" name="username"value=""> <BR></TD>
					</TR>
                    
					<TR>
                      <TD align=right><SPAN class=style1><FONT color=#8c8e8c>密&nbsp;&nbsp;码：</FONT></SPAN></TD>
                      <TD><INPUT class="textinput" style="WIDTH: 160px"  type="password" name="password" value=""><BR></TD>
					</TR>
					
                    <TR align=middle>
                      <TD colSpan=2>
                        <TABLE width=180>
                          <TBODY>
                          <TR>
                            <TD><A href="javascript:login();"><IMG  src="ftxh/imags/login/denglu.gif" border=0></A>&nbsp;</TD>
                            <TD><IMG src="ftxh/imags/login/zhuce.gif" border=0 onClick="javascript:myreset();">&nbsp;</TD>
						  </TR>
						  </TBODY>
						  </TABLE>
					  </TD>
					</TR>
					</form></TABLE></TD>
                	<TD background=ftxh/imags/login/login_right_bg.gif>&nbsp;</TD>
						  </TR>
						  <TR>
							<TD colSpan=3><IMG height=47 alt="" src="ftxh/imags/login/login_bottom.gif" width=374 border=0></TD>
						 </TR>
						</TBODY>
					   </TABLE>
			         </TD>
				  </TR>
        <TR>
          <TD background=imags/login/bottom_bg.gif colSpan=2  height=14></TD></TR>
        <TR vAlign=top height=86>
          <TD colSpan=2>
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR>
                <TD width=120 height="21">&nbsp;</TD>
                <TD><div class=style1>
				<FONT color=#8c8e8c>版权：秦皇岛微讯信息技术有限公司 Copyright@2013 <br>
				制作：秦皇岛微讯信息技术有限公司<br>
				(提示：本系统需要运行在IE6.0及以上版本；建议分辨率1024×768以上。)</FONT></div></TD>
              </TR></TBODY></TABLE></TD></TR></TBODY></TABLE>
      </TD>
  </TR></TBODY></TABLE>
  
  
  
  
  </BODY></HTML>

<script language="javascript">
function login()
{
	formHead.submit();
}
function myreset(){
	formHead.reset();
	formHead.username.value='';
	formHead.password.value='';
}
</script>
<script>
$("input[name='password']").keydown(function(ev){
	var ev = ev || event;
	var currKey = ev.keyCode || ev.which || ev.charCode;
	if(currKey==13){
		login();
	}
});
$("input[name='username']").keydown(function(ev){
	var ev = ev || event;
	var currKey = ev.keyCode || ev.which || ev.charCode;
	if(currKey==13){
		$("input[name='password']").focus();
	}
});
</script>





