<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,com.jspsmart.upload.*,java.util.*"
import="com.shenghao.arch.util.*"
import="java.util.*"
import="sun.jdbc.rowset.*"
import="java.text.DateFormat"
import="com.zagerbone.util.DBTrans"
import="com.zagerbone.util.Tools"
import="org.json.*"

%><%
DBTrans dbtrans = new DBTrans();
/*
dbtrans.addSql(stringbuffer.toString());
flag = dbtrans.executeSql();
*/
SmartUpload mySmartUpload=new SmartUpload();
long file_max=4000000;

String fileName2="",ext="",testvar="";
String url="uploadEnclo/";
//System.out.println("==in upload");
mySmartUpload.initialize(pageContext);//只允许上载此种文档
try{
	mySmartUpload.setAllowedFilesList("jpg,gif,bmp,doc,docx,xls,xlsx,ppt,pptx,txt,png,rar,JPG,GIF,BMP,DOC,DOCX,XLS,XLSX,PPT,PPTX,TXT,PNG,RAR");
	mySmartUpload.upload();
	}
	catch(Exception ex){
		%>error<%
		String ret = "window.parent.upfinish('00000','err','formaterr');";
		out.print("<script>"+ret+"</script>");
	}
	try{
		com.jspsmart.upload.File myFile=mySmartUpload.getFiles().getFile(0);
		if(myFile.isMissing()){
			%>missing<%
			//System.out.println("==missing");
			}else{
		String myFileName=myFile.getFileName();//取得上载的文件名
		ext=myFile.getFileExt();//取得上载文件后缀名
		
		int file_size=myFile.getSize();//取得文件大小
		String saveurl="";
		if(file_size<file_max){
			//更改文件名取得，取得当前上传时间的毫秒数值
			Calendar calendar=Calendar.getInstance();
			String filename=String.valueOf(calendar.getTimeInMillis());
			saveurl=application.getRealPath("/")+url;
			saveurl+=filename+"."+ext;//保存路径
			myFile.saveAs(saveurl,SmartUpload.SAVE_PHYSICAL);
			String sql = "insert into enclosure(fileName,fileCode) values('"+myFileName+"','"+filename+"."+ext+"')";
			dbtrans.addSql(sql);
			dbtrans.executeSql();
			dbtrans = null ;
			out.print(saveurl);
			String ret = "window.parent.upfinish('"+filename+"."+ext+"','"+myFileName+"','ok');";
			out.print("<script>"+ret+"</script>");
			}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
%>