<%@ page contentType="text/html; charset=gb2312"%>
<%
String username = basic.core.util.Tools.toGb2312(request.getParameter("username"));
String sessionkill = request.getParameter("sessionkill");
if(sessionkill != null && sessionkill.equals("1")){
	System.out.println("sessionkill============================:"+sessionkill);
	session.invalidate();
}
System.out.println("===============================================");
%>

<HTML><HEAD><TITLE>林南仓矿业公司成本作业控制系统</TITLE>
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
<iframe id="submitframe" name="submitframe" width="100%" height="200" src="" frameborder="yes" style="display:none"></iframe> 
<TABLE height="100%" cellSpacing=0 cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR vAlign=center>
    <TD align=middle>
      <TABLE  style="BORDER-RIGHT: 1px solid; BORDER-TOP: 1px solid; BORDER-LEFT: 1px solid; BORDER-BOTTOM: 1px solid" cellSpacing=0 cellPadding=0 width=796 align=center border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=40>&nbsp;</TD></TR>
        <TR><TD background=imags/login/top_bg.gif colSpan=2 height=62 ><div style=" margin-bottom:10px; vertical-align:middle;font-size:24px;color:#2A3FFF; font-weight:bold; text-align:center">&nbsp;林南仓矿业公司成本作业控制系统(密码修改)</div></TD></TR>
				<!--
				<TR><TD background=imags/login/top_bg.gif colSpan=2 height=62 ><div style=" margin-bottom:10px; vertical-align:middle;font-size:24px;color:#2A3FFF; font-weight:bold; text-align:center">&nbsp;福田宣化工厂生产计划与车间控制管理系统</div></TD></TR>-->	
        
        <TR>
          <TD width=389 bgColor=#f7f7ff>
            <TABLE cellSpacing=0 cellPadding=0 width=389 bgColor=#f7f7ff border=0>
              <TBODY>
              <TR>
                <TD height=12></TD></TR>
              <TR>
               
              </TR></TBODY></TABLE>
            <img height=199 alt=""  src="imags/login/sys_title.gif" width=389 border=0 name=登录界面_r5_c1></TD>
          <TD width=405 bgColor=#f7f7ff>
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
              <TR>
                <TD bgColor=#f7f7ff colSpan=4 height=28>&nbsp;</TD></TR>
              <TR>
                <TD><IMG height=33 alt=""  src="imags/login/login_left.jpg" width=36 border=0 name=登录界面_r4_c2></TD>
                <TD background=imags/login/login_top.gif>&nbsp;</TD>
                <TD><IMG height=33 alt=""  src="imags/login/login_right.jpg" width=34 border=0  name=登录界面_r4_c4></TD>
                <TD width=31 bgColor=#f7f7ff rowSpan=3>&nbsp;</TD></TR>
              <TR>
                <TD background=imags/login/login_left_bg.gif>&nbsp;</TD>
                <TD width=304>
                  <TABLE height=143 cellSpacing=0 cellPadding=0 width="100%" bgColor=#ffffff border=0>
                    <TBODY>
					<form name="formHead" action="../FSY000.000.013.91"  onKeyDown="if(event.keyCode==13)login()"  method="post" target="submitframe" >
                    <TR><input type="hidden" name="fid" value="F00.00.00.05">
                      <TD align=right width="30%"><SPAN class=style1><FONT color=#8c8e8c>用户名：</FONT></SPAN></TD>
                      <TD><INPUT class="textinput" style="WIDTH: 160px" name="username" readOnly value="<%=username%>"> <BR></TD>
					</TR>
                    
							<TR>
								<TD align=right><SPAN class=style1><FONT color=#8c8e8c>旧密码：</FONT></SPAN></TD>
								<TD><INPUT class="textinput" style="WIDTH: 160px"  type="password" name="password_old"><BR></TD>
							</TR>
							<TR>
								<TD align=right><SPAN class=style1><FONT color=#8c8e8c>新密码：</FONT></SPAN></TD>
								<TD><INPUT class="textinput" style="WIDTH: 160px"  type="password" name="password"><BR></TD>
							</TR>
					
                    <TR align=middle>
                      <TD colSpan=2>
                        <TABLE width=180>
                          <TBODY>
                          <TR>
                            
														<!--<TD><A href="user/regist.jsp"><IMG src="imags/login/zhuce.gif" border=0></A>&nbsp;</TD>-->
														<TD><a href="#"><input name="modifyBn" type="button" style="width:76px;" value="确定" onClick="modifyPW();"></a>&nbsp;</TD>
														<TD><a href="#"><input name="loginBn" type="button" style="width:76px;" value="登录" onClick="login();"></a>&nbsp;</TD>
														<!--<TD><A href="javascript:login();"><IMG  src="imags/login/denglu.gif" border=0></A>&nbsp;</TD>
														<TD><A href="#"><IMG src="imags/login/zhuce.gif" border=0></A>&nbsp;</TD>
														-->
														
						  </TR>
						  </TBODY>
						  </TABLE>
					  </TD>
					</TR>
					</form></TBODY></TABLE></TD>
                	<TD background=imags/login/login_right_bg.gif>&nbsp;</TD>
						  </TR>
						  <TR>
							<TD colSpan=3><IMG height=47 alt="" src="imags/login/login_bottom.gif" width=374 border=0></TD>
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
				<!--<FONT color=#8c8e8c>版权：福田宣化工厂Copyright@2009 <br>
				制作：天工制造业信息化应用研究所、北华航天工业学院<br>
				-->
				<FONT color=#8c8e8c>版权：林南仓矿业公司Copyright@2009 <br>
				制作：天工制造业信息化应用研究所<br>
				(提示：本系统需要运行在IE6.0及以上版本；建议分辨率1024×768以上。)</FONT></div></TD>
              </TR></TBODY></TABLE></TD></TR></FORM></TBODY></TABLE>
      </TD>
  </TR></TBODY></TABLE>
  
 
  
  
  </BODY></HTML>

<script language="javascript">
function modifyPW(){
	if(document.getElementById('password').value ==''){
		alert('密码不能为空!');
		document.getElementById('password').focus();
		return;
	}
	//alert();
	formHead.target = "submitframe";
	formHead.action  = "../MainServlet/FSY000.000.013.91";
	formHead.submit();

}


function login()
{
	formHead.target = "_self";
	formHead.action  = "../MainServlet_sys";
	//alert('jjjj');
	formHead.submit();
}
</script>