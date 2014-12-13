<%@ page contentType="text/html; charset=gb2312"%>
<HTML><HEAD><TITLE>天津大学机电科技中心</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">

<STYLE type=text/css>.style1 {
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
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR vAlign=center>
    <TD align=middle>
      <TABLE  style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid" cellSpacing=0 cellPadding=0 width=796 align=center border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=40>&nbsp;</TD></TR>
        <TR>
          <TD background=imags/login/top_bg.jpg colSpan=2 
          height=62>&nbsp;</TD></TR>
        <TR>
          <TD width=389 bgColor=#f7f7ff>
            <TABLE cellSpacing=0 cellPadding=0 width=389 bgColor=#f7f7ff border=0>
              <TBODY>
              <TR>
                <TD height=52></TD></TR>
              <TR>
                <TD><IMG height=199 alt=""  src="imags/login/sys_title.jpg" width=389 border=0 name=登录界面_r5_c1></TD></TR></TBODY></TABLE></TD>
          <TD width=405 bgColor=#f7f7ff>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
                <TD bgColor=#f7f7ff colSpan=4 height=28>&nbsp;</TD></TR>
              <TR>
                <TD><IMG height=33 alt=""  src="imags/login/login_left.jpg" width=36 border=0 name=登录界面_r4_c2></TD>
                <TD background=imags/login/login_top.jpg>&nbsp;</TD>
                <TD><IMG height=33 alt=""  src="imags/login/login_right.jpg" width=34 border=0  name=登录界面_r4_c4></TD>
                <TD width=31 bgColor=#f7f7ff rowSpan=3>&nbsp;</TD></TR>
              <TR>
                <TD background=imags/login/login_left_bg.jpg>&nbsp;</TD>
                <TD width=304>
                  <TABLE height=143 cellSpacing=0 cellPadding=0 width="100%" bgColor=#ffffff border=0>
                    <TBODY>
					<form name="formHead" action="/MainServlet" target="submitframe" onKeyDown="if(event.keyCode==13)login()">
                    <TR><input type="hidden" name="fid" value="F00.00.00.05">
                      <TD align=right width="30%"><SPAN class=style1><FONT color=#8c8e8c>用户名：</FONT></SPAN></TD>
                      <TD><INPUT class="textinput" style="WIDTH: 160px" name="username" onkeydown="if(event.keyCode==13)event.keyCode=9"> <BR></TD>
					</TR>
                    
					<TR>
                      <TD align=right><SPAN class=style1><FONT color=#8c8e8c>密&nbsp;&nbsp;码：</FONT></SPAN></TD>
                      <TD><INPUT class="textinput" style="WIDTH: 160px"  type="password" name="password"><BR></TD>
					</TR>
					
                    <TR align=middle>
                      <TD colSpan=2>
                        <TABLE width=180>
                          <TBODY>
                          <TR>
                            <TD><A href="javascript:login();"><IMG  src="imags/login/denglu.jpg" border=0></A>&nbsp;</TD>
                            <TD><A href="user/regist.jsp"><IMG src="imags/login/zhuce.jpg" border=0></A>&nbsp;</TD>
						  </TR>
						  </TBODY>
						  </TABLE>
					  </TD>
					</TR>
					</form>
					</TBODY></TABLE></TD>
                	<TD background=imags/login/login_right_bg.jpg>&nbsp;</TD>
						  </TR>
						  <TR>
							<TD colSpan=3><IMG height=47 alt="" src="imags/login/login_bottom.jpg" width=374 border=0></TD>
						 </TR>
						</TBODY>
					   </TABLE>
			         </TD>
				  </TR>
        <TR>
          <TD background=imags/login/bottom_bg.jpg colSpan=2  height=14></TD></TR>
        <TR vAlign=top height=86>
          <TD colSpan=2>
            <TABLE cellSpacing=0 cellPadding=4 width="100%" border=0>
              <TBODY>
              <TR>
                <TD width=120>&nbsp;</TD>
                <TD><SPAN class=style1><FONT color=#8c8e8c>提示：本系统需要运行在IE6.0及以上版本中；如果您是首次登录本系统请确认您的浏览器是否<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;IE6.0以上版本；建议分辨率1024×768</FONT></SPAN></TD></TR></TBODY></TABLE></TD></TR></FORM></TBODY></TABLE>
      </TD>
  </TR></TBODY></TABLE>
  </BODY></HTML>
<iframe id="submitframe" name="submitframe" width="100%" height="100" src="" frameborder="NONE" style="display:none"></iframe>
<script language="javascript">
function login()
{
	formHead.submit();
}
</script>