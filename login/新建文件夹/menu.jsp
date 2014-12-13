<%@ page contentType="text/html; charset=gb2312"%>
<%@ page import="com.zagerbone.user.*"%>
<%@ page import="com.zagerbone.notice.*"%>
<%@ page import="java.util.*"%>

<HTML><HEAD>
<meta http-equiv="refresh" content="60">
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<STYLE>BODY {
	FONT-SIZE: 14px; MARGIN: 0px; COLOR: #000000
}
TABLE {
	FONT-SIZE: 14px; COLOR: #000000
}
.opermenu {
	BORDER-TOP: #81aad3 1px solid; FONT-SIZE: 14px; BORDER-LEFT: #81aad3 1px solid; WIDTH: 100px; CURSOR: hand; COLOR: #ffffff; BORDER-BOTTOM: #000000 1px solid; HEIGHT: 18px; BACKGROUND-COLOR: #668cb3; TEXT-ALIGN: center
}
TD.bu1 {
	BACKGROUND-POSITION: center 50%; BACKGROUND-IMAGE: url(oasys/images/bu1.gif); BACKGROUND-REPEAT: no-repeat
}
A {
	COLOR: #000000; TEXT-DECORATION: none
}
A:hover {
	COLOR: #ff3300; TEXT-DECORATION: none
}
</STYLE>

<SCRIPT language=JavaScript>
<!-- 
function goto(url)
{
	var form = document.mainForm;
	form.action = url;
	form.target = "contenFrame";
	form.submit;
}
function doClose()
{
	var form = document.mainForm;
	form.action = "/logoff.jsp";
	form.target = "_parent";
	form.submit;
}
// -->
</SCRIPT>


<body text="#000000" vLink="#000000" aLink="#ff0000" link="#000000" bgColor="#668cb3" leftMargin="0" topMargin="0">
<%
	User user = (User)session.getAttribute("curUser");
	HashMap privlist = user.getPrivlist();
%>
<div id="Layer1" style="position: absolute; left: 0px; top: 0px; width: 992px; height: 638px; z-index: 1">
<form name="mainForm" action="">
<TABLE height=600 cellSpacing=0 cellPadding=0 width="100%" border=0> 
<tr>
  <TBODY>
  <TR>
    <TD vAlign=top width=160 bgColor=#336699>
      <DIV align=right>
      <TABLE style="BORDER-COLLAPSE: collapse" borderColor=#111111 height=567 
      cellSpacing=0 cellPadding=0 width=166 background="imags/leftimg.gif" border=0>
        <TBODY>
        <TR>
          <TD vAlign=top align=right>
            <DIV><BR><BR><BR><BR>
            <TABLE style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width="75%" border=0>
              <TBODY>
              <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
				  <input type="submit" name="Submit1" value="通知通告" style="width:100;height:23 " onClick="goto('../notice/notice.jsp');"> 
				 </P></TD></TR>
              <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
                    <input type="submit" name="Submit2" value="个人空间" style="width:100;height:23 " onClick="goto('notice/personal_notice.jsp');">
                  </P></TD></TR>
              <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
                    <input type="submit" name="Submit3" value="成员信息" style="width:100;height:23 " onClick="goto('user/user_basic.jsp');">
                  </P></TD></TR>
              <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
				   <input type="submit" name="Submit3" value="书籍信息" style="width:100;height:23 " onClick="goto('book/book.jsp');"></P></TD></TR>
              <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px"> 
				  <input type="submit" name="Submit3" value="论文信息" style="width:100;height:23 " onClick="goto('paper/paper.jsp');"></P></TD></TR>
			  <%if(privlist.containsKey("F00.00.13.00")){%>
			  <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
				  <input type="submit" name="Submit1" value="毕业论文" style="width:100;height:23 " onClick="goto('report/report.jsp');"> 
				 </P></TD></TR>
			  <%}%>
			  <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
				   <input type="submit" name="Submit3" value="科研项目" style="width:100;height:23 " onClick="goto('project/project.jsp');"></P></TD></TR>
              <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
				   <input type="submit" name="Submit3" value="校友信息" style="width:100;height:23 " onClick="goto('schoolmate/schoolmate.jsp');"></P></TD></TR>
              <%if(privlist.containsKey("F00.00.07.00")){%>
			  <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px"> 
				  <input type="submit" name="Submit3" value="采购管理" style="width:100;height:23 " onClick="goto('stock/stock.jsp');"></P></TD></TR>
              <%}%>
			  <%if(privlist.containsKey("F00.00.08.00")){%>
			  <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
				   <input type="submit" name="Submit3" value="财务管理" style="width:100;height:23 " onClick="goto('/expense/expense.jsp');"></P></TD></TR>
             <%}%>
			 <%if(privlist.containsKey("F00.00.10.00")){%>
              <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
				   <input type="submit" name="Submit3" value="资产管理" style="width:100;height:23 " onClick="goto('/plant/plant.jsp');"></P></TD></TR>
			 <%}%>
			 <%if(privlist.containsKey("F00.00.00.00")){%>
			 <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
				   <input type="submit" name="Submit3" value="系统管理" style="width:100;height:23 " onClick="goto('/user/user.jsp');"></P></TD></TR>
	    	<%}%>
			  <TR>
                <TD class=bu1 align=middle height=28>
                  <P align="center" style="MARGIN-TOP: 5px">
				   <input type="submit" name="Submit3" value="退出系统" style="width:100;height:23 "  onClick="doClose();"></P></TD></TR></TBODY></TABLE></DIV></TD></TR></TBODY></TABLE></DIV></TD>
    <TD>&nbsp;      </TD>
  </TR></TBODY></TABLE>
  </form>
  </div>
  <form name="tipForm" action="" target="contenFrame">
  </form>
<%
	NoticeDAO dao = NoticeDAOFactory.getDAO();
	if(dao.hasNewNotice(user.getProp("realname"))){
		out.print("<script>");
		out.print("if (window.confirm('你有新邮件，需要立即查看吗？')) {");
		out.print("tipForm.action='notice/personal_notice.jsp';");
		out.print("tipForm.submit();");
		out.print("}");
		//out.print("}else {");
		//out.print("tipForm.action='';");
		//out.print("tipForm.submit();}");
		out.print("</script>");
	}
	
 %>
</BODY>
</HTML>
