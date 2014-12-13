<%@ page contentType = "text/html; charset=GBK" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="java.util.HashMap"%>
<%
EventResponse 	eventResponse 	= (EventResponse)request.getAttribute("result");  
if(eventResponse.isSuccessFlag()){ 
	if(eventResponse.getFunctionID().equals("FCL00.00.00.01")){ 
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
	else if(eventResponse.getFunctionID().equals("FCL00.00.00.04")){
						HashMap hp = new HashMap();
						hp = (HashMap)eventResponse.getBody();
						String  ModifyMode = (String)hp.get("ModifyMode"	);
            String CMonitorStatus = (String)hp.get("CMonitorStatus" );
           String  id =  (String)hp.get("id");      	
		String listMode= Tools.nvl(request.getParameter("listMode")); 		
			%>
			<script language=JavaScript>
				alert('修改成功!');
				parent.location = 'MainServlet_sys?fid=FCL00.00.00.02&listMode=<%=listMode%>';
			</script>
			<%		
	}
	else if(eventResponse.getFunctionID().equals("FCL00.00.00.041")){
		HashMap hp = new HashMap();
		hp = (HashMap)eventResponse.getBody();
		String  ModifyMode    = (String)hp.get("ModifyMode"	);
		String CMonitorStatus = (String)hp.get("CMonitorStatus" );
		String jkcl_name      = (String)hp.get("tp_carname" );
		String  id            = (String)hp.get("id");      	
		String listMode       = Tools.nvl(request.getParameter("listMode")); 
		if(ModifyMode.equals("cancal")){ 
			if(CMonitorStatus.equals("0")){
			%>
			<script language=JavaScript>
				//parent.location = 'MainServlet_sys?fid=FCL00.00.00.02&listMode=<%=listMode%>';
				parent.location = 'MainServlet_sys?fid=FCL00.00.00.12&tp_carname=<%=jkcl_name%>';
				alert('开始生成车辆的故障报告');
				//alert('开始生成车辆的故障报告<%=request.getParameter("id")%>');
				parent.submitframe.location ='MainServlet_sys?fid=FCL00.00.06.11&id=<%=request.getParameter("id")%>';
				//alert(parent.parent.leftFrame.location );
				//parent.parent.leftFrame.location = parent.parent.leftFrame.location ;
				window.top.indexFrame.frames['leftFrame'].location ="MainServlet_sys?fid=FCL00.00.03.02&pageDisp=0";
				//window.top.indexFrame.frames['leftFrame'].location ="MainServlet_sys?fid=FCL00.00.00.12&tp_carname=<%=jkcl_name%>";
			</script>
			<%
			}else{
			%>
				<script language=JavaScript>
				    parent.location = 'MainServlet_sys?fid=FCL00.00.01.02&tp_carname=<%=jkcl_name%>';
				    alert('开始监控!!');
					
					//alert(window.top.indexFrame.frames['leftFrame'].location );
					//window.topframe.leftFrame.location = parent.parent.leftFrame.location ;
					window.top.indexFrame.frames['leftFrame'].location = parent.parent.leftFrame.location ;
					//alert('开始监控!!');
					//parent.location = 'MainServlet_sys?fid=FCL00.00.00.02&listMode=<%=listMode%>';
					window.top.indexFrame.frames['leftFrame'].location ="MainServlet_sys?fid=FCL00.00.03.02&pageDisp=0";
					
				</script>
			<%
			}
		}	
	}
	
	else if(eventResponse.getFunctionID().equals("FCL00.00.00.042")){
			HashMap hp = new HashMap();
			hp = (HashMap)eventResponse.getBody();
			String  ModifyMode = (String)hp.get("ModifyMode"	);
            String CMonitorStatus = (String)hp.get("CMonitorStatus" );
            String  id =  (String)hp.get("id");      	
			String tp_testnum= Tools.nvl(request.getParameter("tp_testnum"));
		    String tp_cargdzt= Tools.nvl(request.getParameter("tp_cargdzt")); 		
			%>
			<script language=JavaScript>
				alert('归档成功!');
				parent.location = 'MainServlet_sys?fid=FCL00.00.00.02&tp_cargdzt=<%=tp_cargdzt%>&tp_testnum=<%=tp_testnum%>';
			</script>
			<%		
	}
		
	else if(eventResponse.getFunctionID().equals("FCL00.00.00.05")){ 
		//out.println(SysTrace.showMsg("删除成功！"));
		//out.println("<script language=JavaScript>");
		//out.println("parent.location = parent.location;");
		//out.println("</script>");
		
		%>
			<script language=JavaScript>
				alert('删除成功！');
				parent.location = 'MainServlet_sys?fid=FCL00.00.00.02';
			</script>
			<%		
		
	}
}else{
	%>
	<script>
	alert("<%=eventResponse.getErrorMessage()%>");
	//document.getElementById("password").focus();
	</script>
	<%
}
%>



