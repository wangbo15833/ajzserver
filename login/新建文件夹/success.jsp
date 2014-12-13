<%@ page contentType="text/html; charset=GBK" %>

<%@ page import="java.util.*"%>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="com.zagerbone.notice.*"%>
<%@ page import="com.zagerbone.user.*"%>

<%
 
  EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
  if(eventResponse.isSuccessFlag()){ 
 
    if(eventResponse.getFunctionID().equals("F00.00.00.01")){  //用户注册  
	  String username = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("注册成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.location='/wait.jsp';");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F00.00.00.05")){  //用户登陆
	  User user = (User)eventResponse.getBody();
	  //设置当前用户的session;
	  session.setAttribute("curUser",user);
	  out.println("<script language=JavaScript>");
	  out.println("parent.location = 'oasys/index.jsp';");
      out.println("</script>");
      return;
    }
	  /**
	if(eventResponse.getFunctionID().equals("F05.02.01.05")){  //修改登记表
      String devid = (String)eventResponse.getBody();
	  out.println(SysTrace.showMsg("修改成功！"));
      out.println("<script language=JavaScript>");
      out.println("parent.location = 'ldjc/djb_cx.jsp';");
      out.println("</script>");
      return;
    }
    
	if(eventResponse.getFunctionID().equals("F05.02.04.01")){  //立案审批
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("立案审批成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.returnValue="+ywxh+";");
      out.println("parent.close();");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F05.02.04.03")){  //取消立案
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("取消成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.location = 'MainServlet?fid=F05.02.01.02';");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F05.02.01.04")){  //处理办结
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("处理办结成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.returnValue="+ywxh+";");
      out.println("parent.close();");
      out.println("</script>");
      return;
    }
	
	if(eventResponse.getFunctionID().equals("F05.02.02.04")){  //填写监察效果
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("监察效果填写成功！"));
      out.println("<script language=JavaScript>");
      out.println("parent.location = 'ldjc/aj_cx.jsp';");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F05.02.02.07")){  //修改监察效果
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("操作成功！"));
      out.println("<script language=JavaScript>");
      out.println("parent.location = 'ldjc/aj_cx.jsp';");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F05.02.02.08")){  //修改监察效果
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("操作成功！"));
      out.println("<script language=JavaScript>");
      out.println("parent.returnValue="+ywxh+";");
      out.println("parent.close();");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F05.02.05.02")){  //删除案件
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("案件删除成功！"));
      out.println("<script language=JavaScript>");
      out.println("parent.location = 'MainServlet?fid=F05.02.02.01';");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F05.02.04.04")){  //取消结案
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("取消成功！"));
      out.println("<script language=JavaScript>");
	  out.println("parent.location = 'MainServlet?fid=F05.02.02.01';");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F05.02.02.03")){  //填写结案报告
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("填写成功！"));
      out.println("<script language=JavaScript>");
      out.println("parent.returnValue="+ywxh+";");
      out.println("parent.close();");
      out.println("</script>");
      return;
    }
	if(eventResponse.getFunctionID().equals("F05.02.04.02")){  //结案审批
      String ywxh = (String)eventResponse.getBody();
      out.println(SysTrace.showMsg("审批成功！"));
      out.println("<script language=JavaScript>");
      out.println("parent.returnValue="+ywxh+";");
      out.println("parent.close();");
      out.println("</script>");
      return;
    }
	*/
  }
  else{
    String errMsg = eventResponse.getErrorMessage();
    String errCode = eventResponse.getErrorCode();
    out.println(SysTrace.showMsg(errMsg));
    return;
  }
%>