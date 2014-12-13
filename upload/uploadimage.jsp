<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.io.*,java.awt.Image,java.awt.image.*,java.sql.*,com.jspsmart.upload.*,java.util.*"%><%

SmartUpload mySmartUpload=new SmartUpload();
long file_max=4000000;

String fileName2="",ext="",testvar="";
String url="upload/";

mySmartUpload.initialize(pageContext);//只允许上载此种文档
try{
	mySmartUpload.setAllowedFilesList("jpg,gif");
	mySmartUpload.upload();
	}
	catch(Exception ex){%>
		<script language="javascript">
			alert("只允许上传.jpg");
			window.location='upload.htm';
		</script>
	<%}
	try{
		com.jspsmart.upload.File myFile=mySmartUpload.getFiles().getFile(0);
		if(myFile.isMissing()){
			%>
			<script>
			 alert("请选择上传的文件");
			window.location='upload.htm';
			</script>
			<%}else{
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
			out.print(saveurl);
			String ret = "parent.HtrmlEdit.focus();";
			ret +="var rage=parent.HtmlEdit.doucument.selection.createRange();";
			ret +="range.pasteHTML('<img src=\""+request.getContextPath()+"/upload/"+filename+"."+ext+"\">');";
			
			ret+="alert('文件上传成功！')";
			
			ret+="window.location='upload.htm';";
			out.print("<script>"+ret+"</script>");
			}
			}
		}catch(Exception e){
			e.toString();
		}
%>