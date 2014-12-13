<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%

try{

EventResponse 		eventResponse 		= (EventResponse)request.getAttribute("result");  
System.out.println("eventResponse.getFunctionID()============================================="+eventResponse.getFunctionID());
if(eventResponse.isSuccessFlag()){ 

	if(eventResponse.getFunctionID().equals("FCL00.00.08.01")){ 
		out.println(SysTrace.showMsg("增加成功！"));
		
		//out.println("parent.parent.master_listframe.location = '../MainServlet/FBA000.001.009.02';");
		//out.println("parent.parent.master_editframe.formHead.reset();");
		%>
        <script language=JavaScript>
			parent.location = "MainServlet_sys?fid=FCL00.00.08.02&listMode=list";
            
        </script>
        <%
		
		return;
	}
	
	else if(eventResponse.getFunctionID().equals("FCL00.00.08.04")){
		String listMode= Tools.nvl(request.getParameter("listMode")); 
		out.println(SysTrace.showMsg("修改成功！"));
		out.println("<script language=JavaScript>");
			%>
            parent.location = 'MainServlet_sys?fid=FCL00.00.08.02&listMode=<%=listMode%>';
			//parent.submitframe.location ='MainServlet_sys?fid=FCL00.00.06.11&id=<%=request.getParameter("id")%>';
			<%
		out.println("</script>");
	}
	
	else if(eventResponse.getFunctionID().equals("FCL00.00.08.05")){ 
		//out.println(SysTrace.showMsg("删除成功！"));
		//out.println("<script language=JavaScript>");
		//out.println("parent.location = parent.location;");
		//out.println("</script>");
		%>
			<script language=JavaScript>
				alert('删除成功！');
				parent.location = 'MainServlet_sys?fid=FCL00.00.08.02';
			</script>
			<%		
	}
	
}

}catch(Exception ex){

	ex.printStackTrace();
}
%>



