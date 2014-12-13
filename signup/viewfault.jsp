<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="java.util.*"%>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="com.shenghao.arch.manage.user.*"%>
<%@ page import="com.zagerbone.user.*"%>
<html>
<body>
<h1>this is the fault page!</h1>
</body>
</html>
<%
 
  EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
  if(eventResponse.isSuccessFlag()){ 
  if(eventResponse.getFunctionID().equals("F00.00.00.03")){  //审批用户
      String userid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("审批成功！"));
      out.println("<script language=JavaScript>");
	  	//out.println("parent.location = 'MainServlet?fid=F00.00.00.02';");
			out.println("parent.location = parent.location;");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F00.00.00.04")){  //禁用用户
      String userid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("操作成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.location = 'MainServlet?fid=F00.00.00.02';");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F00.00.00.06")){  //删除用户
      String userid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("删除成功！"));
      out.println("<script language=JavaScript>");
	  //out.println("parent.location = 'MainServlet?fid=F00.00.00.02';");
      out.println("parent.location = parent.location;");
	  out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F00.00.00.13")){  //修改密码	  
	  String fsr = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("修改成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.returnValue='"+fsr+"';");
      out.println("parent.close();");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F00.00.00.11")){  //审批个人信息
      String userid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("审批成功！"));
      out.println("<script language=JavaScript>");
	  	//out.println("parent.location = 'MainServlet?fid=F00.00.00.08';");
			%>
			//alert(parent.location);
			<%
	  	out.println("parent.location = parent.location;");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F00.00.00.07")){  //新增个人信息
      String userid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("增加成功！"));
      out.println("<script language=JavaScript>");
	  //out.println("parent.location = 'MainServlet_sys?fid=F00.00.00.14';");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F00.00.00.10")){  //修改个人信息
      String userid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("修改成功！"));
      out.println("<script language=JavaScript>");
	  //out.println("parent.location = 'MainServlet?fid=F00.00.00.08';");
      out.println("</script>");
      return;
    }
    if(eventResponse.getFunctionID().equals("F00.00.01.01")){  //新增角色  
	  String rolename = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("增加成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.returnValue='"+rolename+"';");
      out.println("parent.close();");
      out.println("</script>");
      return;
    }
    if(eventResponse.getFunctionID().equals("F00.00.01.03")){  //禁用角色
      String roleid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("禁用成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.location = 'MainServlet?fid=F00.00.01.02';");
      out.println("</script>");
      return;
    }
    if(eventResponse.getFunctionID().equals("F00.00.01.04")){  //恢复角色
      String roleid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("恢复成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.location = 'MainServlet?fid=F00.00.01.02';");
      out.println("</script>");
      return;
    }
    if(eventResponse.getFunctionID().equals("F00.00.01.05")){  //删除角色
      String roleid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("删除成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.location = 'MainServlet?fid=F00.00.01.02';");
      out.println("</script>");
      return;
    }
    if(eventResponse.getFunctionID().equals("F00.00.01.06")){  //赋予角色权限  
	  String roleid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("操作成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.returnValue="+roleid+";");
      out.println("parent.close();");
      out.println("</script>");
      return;
    }
    if(eventResponse.getFunctionID().equals("F00.00.01.08")){  //赋予用户角色  
	  String userid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("操作成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.returnValue="+userid+";");
      out.println("parent.close();");
      out.println("</script>");
      return;
    }
    if(eventResponse.getFunctionID().equals("F00.00.00.17")){  //将成员变为校友
      String roleid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("操作成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.location = 'MainServlet?fid=F00.00.04.02';");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F00.00.00.18")){  //成员导出
      String roleid = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("导出成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.location = 'user/user_basic_export.jsp';");
      out.println("</script>");
      return;
    }
  }
  else{
    String errMsg = eventResponse.getErrorMessage();
    String errCode = eventResponse.getErrorCode();
    out.println(SysTrace.showMsg(errMsg));
    return;
  }
%>