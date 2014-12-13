<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %><%@ page import="java.io.*,com.jspsmart.upload.*,java.util.*"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="com.zagerbone.util.Tools"
%><%
try{
	String filecode = (String)request.getParameter("filecode");
	String filename = new String(request.getParameter("filename").getBytes("ISO-8859-1"),"utf-8"); 
	SmartUpload su = new SmartUpload();
	su.initialize(pageContext);
	su.setContentDisposition(null);
	su.downloadFile("uploadEnclo/"+filecode,"",filename);
}catch(Exception e){
	e.printStackTrace();
}
%>