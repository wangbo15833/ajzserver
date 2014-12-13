<%@ page language="java" contentType="text/html;charset=gb2312" %>

<html>
<head>
<title>天津市河北区劳动局信息化系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link rel="stylesheet" href="skin/css/2004.css" type="text/css">
<script language="JavaScript" src="/share/js/utils.js"></script>
</head>
<body bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0" marginheight="600">

<form name="form1" method="post" action="/MainServlet" onkeydown="if(event.keyCode==13)login()" target="submitframe">
<table width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
<input type="hidden" name="fid" value="F00.00.00.05">
  <tr> 
    <td> 
      <h5>&nbsp;</h5>
    </td>
  </tr>
  <tr> 
    <td height="420"> 
      <table border="0" cellpadding="0" cellspacing="0" width="650" align="center">
        <tr> 
          <td><img src="images/spacer.gif" width="246" height="1" border="0"></td>
          <td><img src="images/spacer.gif" width="404" height="1" border="0"></td>
          <td><img src="images/spacer.gif" width="1" height="1" border="0"></td>
        </tr>
        <tr> 
          <td colspan="2"><img name="login1_r1_c1" src="images/login1_r1_c1.jpg" width="650" height="314" border="0"></td>
          <td><img src="images/spacer.gif" width="1" height="314" border="0"></td>
        </tr>
        <tr> 
          <td> 
            <table width="246" border="0" cellspacing="0" cellpadding="0" height="106">
              <tr> 
                <td background="images/login1_r2_c1.jpg"> 
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr> 
                      <td width="50">&nbsp;</td>
                      <td width="65"> 
                        <div align="center">登陆日期：</div>
                      </td>
                      <td> 
                        <input type="text" name="use" class="listdrpdown100nb" onkeydown="if(event.keyCode==13)event.keyCode=9" ReadOnly>
                      </td>
                    </tr>
                    <tr> 
                      <td width="50">&nbsp;</td>
                      <td width="65"> 
                        <div align="center">登陆帐号：</div>
                      </td>
                      <td> 
                        <input type="text" name="username" class="listdrpdown"  onkeydown="if(event.keyCode==13)event.keyCode=9">
                      </td>
                    </tr>
                    <tr> 
                      <td>&nbsp;</td>
                      <td> 
                        <div align="center">登陆密码：</div>
                      </td>
                      <td> 
                        <input type="password" name="password" class="listdrpdown" value="">
                      </td>
                    </tr>
                    <tr> 
                      <td height="30">&nbsp;</td>
                      <td colspan="2"> 
                        <table width="100%" border="0" cellspacing="0" cellpadding="0" height="20">
                          <tr> 
                            <td>&nbsp;</td>
							<a href="javascript:login()">
                            <td width="50" background="images/login.jpg">确定</td>
							</a>
                            <td width="20">&nbsp;</td>
                            <a href="javascript:quit()">
                            <td width="50" background="images/close.jpg">返回</td>
                            </a>
                            <td>&nbsp;</td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
          </td>
          <td><img name="login1_r2_c2" src="images/login1_r2_c2.jpg" width="404" height="106" border="0"></td>
          <td><img src="images/spacer.gif" width="1" height="106" border="0"></td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
</body>
</html>
<iframe name="submitframe" style="display:none"></iframe>
<script language="JavaScript">

function login(){
  form1.submit();
}
function quit(){
  self.close();
}

today = new Date () ;
form1.lastlogin.value=today.getYear()+"."+(today.getMonth()+1)+"."+today.getDate();

</script>
