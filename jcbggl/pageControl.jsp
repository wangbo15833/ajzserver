<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%
EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
if(eventResponse.isSuccessFlag()){ 
	if(eventResponse.getFunctionID().equals("FCL00.00.04.01")){ 
		if(Tools.nvl(request.getParameter("fileName")).equals("ba_dbom_master_id.jsp")){
			String ba_dbom_id = (String)eventResponse.getBody();
			//开始提交子表
			out.println("<script language=JavaScript>");
			%>
			/*
			var totalRow = (parent.parent.editframe_id.totalRow -1);
			for(var jk=1; jk<= totalRow; jk++){
				parent.parent.editframe_id.form_tableBody["CONo"+jk].value = '<%=ba_dbom_id%>';
			}
			*/
			<%
			//out.println("parent.parent.editframe_id.form_tableBody.submit();");
			out.println("</script>");
			out.println(SysTrace.showMsg("增加成功！"));
		}else{
		  String user_id = (String)eventResponse.getBody();
			out.println(SysTrace.showMsg("增加成功！"));
			out.println("<script language=JavaScript>");
			//out.println("parent.parent.master_listframe.location = '../MainServlet/FBA000.001.009.02';");
			//out.println("parent.parent.master_editframe.formHead.reset();");
			out.println("</script>");
		}
		return;
	}
	else if(eventResponse.getFunctionID().equals("FCL00.00.04.04")){
		String listMode= Tools.nvl(request.getParameter("listMode")); 
		out.println(SysTrace.showMsg("修改成功！"));
		out.println("<script language=JavaScript>");
			%>
			parent.location = 'MainServlet_sys?fid=FCL00.00.04.02&listMode=<%=listMode%>';
			<%
		out.println("</script>");
	}
	else if(eventResponse.getFunctionID().equals("FCL00.00.04.05")){ 
		out.println(SysTrace.showMsg("删除成功！"));
		out.println("<script language=JavaScript>");
		out.println("parent.location = 'MainServlet_sys?fid=FCL00.00.04.02';");
		out.println("</script>");
	}
}
%>



