    <%@ page autoFlush="false" contentType="text/html;charset=gb2312" language="java" import="java.io.*,java.awt.Image,java.awt.image.*,com.sun.image.codec.jpeg.*,java.sql.*,com.jspsmart.upload.*,java.util.*,java.io.FileInputStream,java.io.FileOutputStream,java.awt.*"%>  
    <!--%@ page import="com.blog.vo.*" %-->  
    <%  
    SmartUpload mySmartUpload =new SmartUpload();  
    long file_size_max=4000000;  
    String fileName2="",ext="",testvar="";  
    String url="uploadfile/images/"; //应保证在根目录中有此目录的存在  
    //初始化  
    mySmartUpload.initialize(pageContext);  
    //只允许上载此类文件  
    try {  
    mySmartUpload.setAllowedFilesList("jpg,gif,JPG,GIF");  
    //上载文件   
    mySmartUpload.upload();  
    } catch (Exception e){  
    %>  
    <SCRIPT language=javascript>  
    alert("只允许上传.jpg和.gif类型图片文件");  
    window.location='face.jsp';  
    </script>  
    <%  
    }  
    try{   
    com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(0);  
    if (myFile.isMissing()){%>  
    <SCRIPT language=javascript>  
    alert("请先选择要上传的文件");  
    window.location='face.jsp';  
    </script>  
    <%}  
    else{  
    //String myFileName=myFile.getFileName(); //取得上载的文件的文件名  
    ext= myFile.getFileExt(); //取得后缀名  
    int file_size=myFile.getSize(); //取得文件的大小   
    String saveurl="";  
    if(file_size<file_size_max){  
    //更改文件名，取得当前上传时间的毫秒数值  
    Calendar calendar = Calendar.getInstance();  
    String filename = String.valueOf(calendar.getTimeInMillis());   
    saveurl=request.getRealPath("/")+url;  
    saveurl+=filename+"."+ext; //保存路径  
    myFile.saveAs(saveurl,mySmartUpload.SAVE_PHYSICAL);  
    //-----------------------上传完成，开始生成缩略图-------------------------   
    java.io.File file = new java.io.File(saveurl); //读入刚才上传的文件  
    String newurl=request.getRealPath("/")+url+filename+"_min."+ext; //新的缩略图保存地址  
    Image src = javax.imageio.ImageIO.read(file); //构造Image对象  
    float tagsize=110;  
    int old_w=src.getWidth(null); //得到源图宽  
    int old_h=src.getHeight(null);   
    int new_w=0;  
    int new_h=0; //得到源图长  
    int tempsize;  
    float tempdouble;   
    if(old_w>old_h){  
    tempdouble=old_w/tagsize;  
    }else{  
    tempdouble=old_h/tagsize;  
    }  
    new_w=Math.round(old_w/tempdouble);  
    new_h=Math.round(old_h/tempdouble);//计算新图长宽  
    BufferedImage image = new BufferedImage(new_w,new_h,BufferedImage.TYPE_INT_RGB);//创建一个BufferedImage来作为图像*作容器   
    Graphics g = image.getGraphics(); //创建一个绘图环境来进行绘制图象   
    g.drawImage(src,0,0,new_w,new_h,null); //将原图像数据流载入这个BufferedImage   
    g.setFont(new Font("Times New Roman",Font.PLAIN,24)); //设定文本字体   
    String rand = "";   
    g.setColor(Color.red); //设定文本颜色   
    g.drawString(rand,new_w-200,new_h-10); //向BufferedImage写入文本字符   
    g.dispose(); //使更改生效  
      
    FileOutputStream newimage=new FileOutputStream(newurl); //输出到文件流  
    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);   
    encoder.encode(image); //近JPEG编码  
    newimage.close();   
    HashMap hp_user = (HashMap)request.getSession().getAttribute("hp_user");  
    //ui.setFace(url+filename+"_min."+ext);  
    //session.setAttribute("userinfo",ui);  
    //String turl="face.jsp?src="+url+filename+"_min."+ext;  
    //out.print("<SCRIPT language='javascript'>");  
    //out.print("window.location='"+turl+"'");  
    //out.print("</SCRIPT>"); 
    out.print("<SCRIPT language='javascript'>");  
    out.print("alert('缩略图生成成功');");  
    out.print("</SCRIPT>");  
    }  
    else{  
    out.print("<SCRIPT language='javascript'>");  
    out.print("alert('上传文件大小不能超过"+(file_size_max/1000)+"K');");  
    out.print("window.location='face.jsp;'");  
    out.print("</SCRIPT>");  
    }  
    }  
    }catch (Exception e){  
      
    e.toString();  
      
    }  
      
    %>  