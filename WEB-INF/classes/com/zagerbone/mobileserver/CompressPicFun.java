package com.zagerbone.mobileserver;  
  
import java.awt.Image;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import javax.imageio.ImageIO;  
import com.sun.image.codec.jpeg.JPEGCodec;  
import com.sun.image.codec.jpeg.JPEGImageEncoder;  
 public class CompressPicFun {   
     private File file = null; // 文件对象   
     private String inputDir; // 输入图路径  
     private String outputDir; // 输出图路径  
     private String inputFileName; // 输入图文件名  
     private String outputFileName; // 输出图文件名  
     private int outputWidth = 110; // 默认输出图片宽  
     private int outputHeight = 110; // 默认输出图片高  
     private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)  
     public CompressPicFun() { // 初始化变量  
         inputDir = "";   
         outputDir = "";   
         inputFileName = "";   
         outputFileName = "";   
         outputWidth = 100;   
         outputHeight = 100;   
     }   
     public void setInputDir(String inputDir) {   
         this.inputDir = inputDir;   
     }   
     public void setOutputDir(String outputDir) {   
         this.outputDir = outputDir;   
     }   
     public void setInputFileName(String inputFileName) {   
         this.inputFileName = inputFileName;  
     }   
     public void setOutputFileName(String outputFileName) {   
         this.outputFileName = outputFileName;   
     }   
     public void setOutputWidth(int outputWidth) {  
         this.outputWidth = outputWidth;   
     }   
     public void setOutputHeight(int outputHeight) {   
         this.outputHeight = outputHeight;   
     }   
     public void setWidthAndHeight(int width, int height) {   
         this.outputWidth = width;  
         this.outputHeight = height;   
     }   
        
     public long getPicSize(String path) {   
         file = new File(path);   
         return file.length();   
     }  
       
     public String compressPic() {   
         try {   
             file = new File(inputDir + inputFileName);   
             if (!file.exists()) {   
                 return "没有找到源文件";   
             }   
             Image img = ImageIO.read(file);   
             if (img.getWidth(null) == -1) {  
                 System.out.println(" can't read,retry!" + "<BR>");   
                 return "no";   
             } else {   
                 int newWidth; int newHeight;   
                 if (this.proportion == true) {   
                     double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;   
                     double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;   
                     double rate = rate1 > rate2 ? rate1 : rate2;   
                     newWidth = (int) (((double) img.getWidth(null)) / rate);   
                     newHeight = (int) (((double) img.getHeight(null)) / rate);   
                 } else {   
                     newWidth = outputWidth; // 输出的图片宽度   
                     newHeight = outputHeight; // 输出的图片高度   
                 }   
                BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);   
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);  
                FileOutputStream out = new FileOutputStream(outputDir + outputFileName);  
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);   
                encoder.encode(tag);   
                System.out.println("已经完成了处理");
                out.close();   
             }   
         } catch (IOException ex) {   
             ex.printStackTrace();   
         }   
         return "ok";   
    }   
    public String compressPic (String inputDir, String outputDir, String inputFileName, String outputFileName) {   
        // 输入图路径   
        this.inputDir = inputDir;   
        // 输出图路径   
        this.outputDir = outputDir;   
        // 输入图文件名   
        this.inputFileName = inputFileName;   
        // 输出图文件名  
        this.outputFileName = outputFileName;   
        return compressPic();   
    }   
    public String compressPic (String inputFileName) {   //@auth 李振南
    	this.inputDir = "E:\\T5.0\\ajz\\uploadfile\\images\\";   
    	this.outputDir = "E:\\T5.0\\ajz\\uploadfile\\images\\img\\";   
    	this.inputFileName = inputFileName;   
    	System.out.println("文件名是"+inputFileName);
    	String s[]=inputFileName.split("\\.");
    	for(int i = 0 ; i<s.length; i++){
    		System.out.println("--------s"+i+"  :"+s[i]);
    	}
    	this.outputFileName = s[0]+"_min."+s[1]; 
    	System.out.println("缩放的图片名为"+this.outputFileName);
    	return compressPic();   
    }   
    public String compressPic(String inputDir, String outputDir, String inputFileName, String outputFileName, int width, int height, boolean gp) {   
        // 输入图路径   
        this.inputDir = inputDir;   
        // 输出图路径   
        this.outputDir = outputDir;   
        // 输入图文件名   
        this.inputFileName = inputFileName;   
        // 输出图文件名   
        this.outputFileName = outputFileName;   
        // 设置图片长宽  
        setWidthAndHeight(width, height);   
        // 是否是等比缩放 标记   
        this.proportion = gp;   
        return compressPic();   
    }   
 } 