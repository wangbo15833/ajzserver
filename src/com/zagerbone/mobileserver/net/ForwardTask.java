package com.zagerbone.mobileserver.net;

import com.zagerbone.mobileserver.AjzMobileServer;
import com.zagerbone.mobileserver.CompressPicFun;
import com.zagerbone.mobileserver.DbUtil;
import com.zagerbone.mobileserver.FileUtil;
import com.zagerbone.mobileserver.Friend;
import com.zagerbone.mobileserver.LogUtil;
import com.zagerbone.mobileserver.Message;
import com.zagerbone.mobileserver.User;
import com.zagerbone.mobileserver.constant.Config;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务器端进行消息转发的Task
 * @author 李振南
 *
 */
public class ForwardTask extends Task{
	public static HashMap<String, Socket> map=new HashMap<String, Socket>();
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	DbUtil dbUtil;
	LogUtil log;
	
	public String useraccount ="";
	private boolean onWork=true;
	private String account ="";
	public ForwardTask(Socket socket){
		this.socket=socket;
		log=new LogUtil();
		
		dbUtil=new DbUtil();
		
		try {
			dis=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			dos=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Task[] taskCore() throws Exception {
		return null;
	}

	@Override
	protected boolean useDb() {
		return false;
	}

	@Override
	protected boolean needExecuteImmediate() {
		return false;
	}

	@Override
	public String info() {
		return null;
	}
	
	private void setWorkState(boolean state){
		onWork=state;
	}
	public void run() {	
		while(onWork){
			try{
				receiveMsg();
			}catch(Exception e){   //发生异常————通常是连接断开，则跳出循环，一个Task任务执行完毕
				System.out.println("与客户端用户"+useraccount+"的连接断开，用户下线");
				e.printStackTrace();
				break;
			}
		}
		
		try{
			if(socket!=null){
				socket.close();
				System.out.println("------socket close");
				}
			if(dis!=null){
				dis.close();
				System.out.println("------dis close");
			}
			if(dos!=null){
				dos.close();
			System.out.println("------dos close");
			}
			socket=null;
			dis=null;
			dos=null;
			
			////
			log=null;
			dbUtil.close();
			dbUtil=null;
			///
		}catch (IOException e) {
			LogUtil.record("---用户"+useraccount+"退出时发生异常");
		}
		map.remove(useraccount);
		LogUtil.record("---用户"+useraccount+"已经从系统中注销登录");
		printSysSta();
		System.out.println("-------线程结束-------");
	}
	
	public void receiveMsg() throws IOException {
		int requestType = dis.readInt();
		switch (requestType) {
/////////////////////////////////////////////////////////////////////
		case Config.GET_LOGIN:      //处理“登录”请求
			System.out.println("客户端请求 登录");
			handGetLogin();
			break;
		case Config.GET_JDJL_LIST:      //处理“获取检查监督列表”请求
			System.out.println("客户端请求 获取检查监督列表");
			handGetJdjlList();
			break;
		case Config.GET_GGINFO_LIST:      //处理“获取公告列表”请求
			System.out.println("客户端请求 获取公告列表");
			handGetGginfoList();
			break;
		case Config.GET_MSG_LIST:      //处理“获取消息列表”请求
			System.out.println("客户端请求 获取消息列表");
			handGetMsgList();
			break;
		case Config.GET_QUITE:      //处理“退出系统”请求
			System.out.println("客户端请求 退出系统");
			handExit();
			break;
		case Config.GET_SIG_STA:      //处理“查询签到状态”请求
			System.out.println("客户端请求 查询签到状态");
			handSigSta();
			break;
		case Config.GET_SIG:      //处理“签到”请求
			System.out.println("客户端请求 签到");
			handSig();
			break;
		case Config.GET_SUBINFO:      //处理“获取项目信息”请求
			System.out.println("客户端请求 获取项目信息");
			handSubinfo();
			break;
		case Config.HEART_BEAT:      //处理“心跳”请求
			System.out.println("客户端请求 心跳");
			handSig();
			break;
		case Config.GET_RELOGIN:      //处理“重登录”请求
			System.out.println("客户端请求 重登录");
			handRelogin();
			break;
		case Config.GET_NEWCHK:      //处理“重登录”请求
			System.out.println("客户端请求 重登录");
			handNewChk();
			break;
		case Config.GET_UPIMG:      //处理“上传图片”请求
			System.out.println("客户端请求 上传图片");
			handSenPic();
			break;
		case Config.GET_CHKPICS:      //处理“查看图片”请求
			System.out.println("客户端请求 查看图片");
			handGetPics();
			break;
		case Config.GET_MARKMSG:      //处理“标记已读”请求
			System.out.println("客户端请求 标记已读");
			handMarkMsg();
			break;
		case Config.GET_TSYJ:      //处理“投诉意见 ”请求
			System.out.println("客户端请求提交投诉意见");
			handTsyj();
			break;
		case Config.GET_MODPWD:      //处理“修改密码”请求
			System.out.println("客户端请求提交投诉意见");
			handModpwd();
			break;
		case Config.GET_JCBZ:      //处理“修改密码”请求
			System.out.println("客户端请求查询手册");
			handleGetJcbz();
			break;
/////////////////////////////////////////////////////////////////////
		}
	}
	//////////////////////////////////////////////
    private void handShutDown() {
    	System.out.println("------======进入handShutDown处理======------");
    	try {
    		AjzMobileServer.setWorkFlagFalse();
    		for (String key : map.keySet()){
    			Socket thissocket=map.get(key);
    			thissocket.close();
    			map.remove(key);
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	System.out.println("------======handShutDown处理结束======------");
    }
	private void handGetLogin() {
		try {
			handHeartBeat();//心跳
			String account=dis.readUTF();
			String psw=dis.readUTF();
			System.out.println("请求登录账号： "+account);
			this.account = account;//lee add it 2013-12-28
			Map<String,String> thismap = dbUtil.getLoin(account,psw);
			boolean success = false;
			boolean alreadyin = false;
			String flag = thismap.get("flag");
			if(flag.equals("true")){
				success=true;
			}
			if(map.containsKey(account)){
				alreadyin=true;
			}
			//////
			//情况一 ,首次登录成功
			if((!alreadyin) && success ){
				System.out.println("登录情况一：---------");
				map.put(account, socket);
				useraccount=account;
				LogUtil.record("用户"+account+"登录成功");
				dos.writeInt(Config.SEN_LOGIN);
				dos.writeUTF(thismap.get("body"));
				dos.flush();
			}
			////
			//情况二，登录失败
			if(!success){
				System.out.println("登录情况二：---------");
				LogUtil.record("用户"+account+"登录失败");
				dos.writeInt(Config.SEN_LOGIN);
				dos.writeUTF(thismap.get("body"));
				dos.flush();
			}
			///
			//情况三，已有一个同名用户在在线，踢其下线，此次登录用户上线
			if(alreadyin && success){
				Socket thatSocket = map.get(account);
				System.out.println("登录情况三：---------thatSocket "+String.valueOf(thatSocket));
				try {//-------------
					DataOutputStream ddos=new DataOutputStream(new BufferedOutputStream(thatSocket.getOutputStream()));
					
					ddos.writeInt(Config.SEN_KICK);
					ddos.writeUTF("您的账号已从其它手机客户端登录");
					ddos.flush();
					
						try{
							if(thatSocket!=null){
								thatSocket.close();}
							if(ddos!=null){
								ddos.close();}
							thatSocket=null;
							ddos=null;
						}catch (IOException e) {
							LogUtil.record("在线用户退出时发生异常");
						}
						try {Thread.sleep(500);} catch (InterruptedException e){System.out.println("踢除用户等待时出现了错误");
							e.printStackTrace();}
						map.put(account, socket);
						useraccount=account;
						LogUtil.record("用户"+account+"暴力的踢除其它终端并登录成功");
						dos.writeInt(Config.SEN_LOGIN);
						dos.writeUTF(thismap.get("body"));
						dos.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}//----------------
				//LogUtil.record("用户"+account+"登录成功,并踢下其它登录的设备");
			}
			printSysSta();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
/**
 * 重登录
 */
private void handRelogin() {
	try {
		//handHeartBeat();//心跳
		String account=dis.readUTF();
		String psw=dis.readUTF();
		System.out.println("请求重登录账号： "+account);
		//Map<String,String> thismap = dbUtil.getLoin(account,psw);
		boolean success = false;
		boolean alreadyin = false;
		boolean iskicked = false;
//		String flag = thismap.get("flag");
//		if(flag.equals("true")){
			success=true;
//		}
		if(map.containsKey(account)){
			alreadyin=true;
		}
		//////
		//情况一 ,首次重登录成功
		if((!alreadyin) && success ){
			System.out.println("重登录情况一：---------");
			map.put(account, socket);
			useraccount=account;
			LogUtil.record("用户"+account+"重登录成功");
			dos.writeInt(Config.SEN_RELOGIN);
			dos.writeUTF("true");
			dos.flush();
		}

		//情况二，已有一个同名用户在在线，踢其下线，此次登录用户上线
		if(alreadyin && success){
			Socket thatSocket = map.get(account);
			DataOutputStream ddos=new DataOutputStream(new BufferedOutputStream(thatSocket.getOutputStream()));
			DataInputStream ddis=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			System.out.println("重登录情况二：---------thatSocket "+String.valueOf(thatSocket));
			try{
				if(thatSocket!=null){
					thatSocket.close();}
				if(ddos!=null){
					ddos.close();}
				if(ddis!=null){
					ddis.close();}
				thatSocket=null;
				ddos=null;
				ddis=null;
			}catch (IOException e) {
				LogUtil.record("在线用户退出时发生异常");
			}

			map.put(account, socket);
			useraccount=account;
			LogUtil.record("用户"+account+"重登录成功");
			dos.writeInt(Config.SEN_RELOGIN);
			dos.writeUTF("true");
			dos.flush();
		}
		printSysSta();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}

	/**
	 * 处理退出请求
	 */
    private void handExit() {
		try {
			String userId=dis.readUTF();
			LogUtil.record("---------------------------------------");
			LogUtil.record("在线用户"+userId+"请求退出登录");
			
			//结束线程
			setWorkState(false);
			
			LogUtil.record("用户"+userId+"退出前,共有"+map.size()+"个用户在线");
			//注意这里不是在转发消息，并不需要查询发送退出请求的用户是否在线。只要是同服务器有Socket连接就是在线的
			map.remove(userId);
			LogUtil.record("用户"+userId+"退出后,还有"+map.size()+"个用户在线");
			dbUtil.close();		//关闭到数据库的连接
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
     * 处理查询签到状态
     */
    private void handSigSta() {
    	try {
            String account=dis.readUTF();
            System.out.println("请求查询签到状态账号： "+account);
            dos.writeInt(Config.SEN_SIG_STA);
            dos.writeUTF(dbUtil.getSigSta(account));
            dos.flush();

    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 签到
     */
    private void handSig() {
    	try {
    		String account=dis.readUTF();
    		String username=dis.readUTF();
    		String userid=dis.readUTF();
    		String dept=dis.readUTF();
    		String la=dis.readUTF();
    		String ln=dis.readUTF();
    		System.out.println("请求 签到 账号： "+account);
    		dos.writeInt(Config.SEN_SIG);
    		dos.writeUTF(dbUtil.getSig(userid,account,username,dept,la,ln));
    		dos.flush();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 项目信息
     */
    private void handSubinfo() {
    	try {
    		String account=dis.readUTF();
    		String dept=dis.readUTF();
    		System.out.println("请求 获取项目信息 账号： "+account);
    		dos.writeInt(Config.SEN_SUBINFO);
    		dos.writeUTF(dbUtil.getSubinfo(dept));
    		dos.flush();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
	/**
	 * 获取查检监督记录列表（无图片）
	 */
	private void handGetJdjlList() {
	    try {
            String account=dis.readUTF();
            System.out.println("请求获取检查监督列表账号： "+account);
            dos.writeInt(Config.SEN_JDJL_LIST);
            dos.writeUTF(dbUtil.getJdjlList(account));
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        };
    }
	/**
	 * 获取公告列表
	 */
	private void handGetGginfoList() {
		try {
			String account=dis.readUTF();
			int curi = dis.readInt();
			int numperpage = dis.readInt();
			System.out.println("请求公告列表账号： "+account);
			dos.writeInt(Config.SEN_GGINFO_LIST);
			dos.writeUTF(dbUtil.getGginfoList(curi,numperpage));
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取消息列表
	 */
	public void handGetMsgList() {
		try {
			String account=dis.readUTF();
			String username=dis.readUTF();
			int curi = dis.readInt();
			int numperpage = dis.readInt();
			System.out.println("请求消息账号： "+account);
			dos.writeInt(Config.SEN_MSG_LIST);
			dos.writeUTF(dbUtil.getMsgList(curi,numperpage,username));
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新建查检
	 */
	private void handNewChk() {
		try {
			String account=dis.readUTF();
			String dept=dis.readUTF();
			String username=dis.readUTF();
			String title=dis.readUTF();
			String cont=dis.readUTF();
			String chklb=dis.readUTF();
			String gcname=dis.readUTF();
			String subcode=dis.readUTF();
			System.out.println("请求消息账号： "+account);
			dos.writeInt(Config.SEN_NEWCHK);
			dos.writeUTF(dbUtil.getNewChk(account,dept,username,title,cont,chklb,gcname,subcode));
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理心跳请求
	 */
	private void handHeartBeat() {
		setSubmitTime(new Date());
	}
	
	/**
	 * 处理发送过的图片
	 */
	private void handSenPic(){
		FileOutputStream ou=null;
            try{
	            String account=dis.readUTF();
	            String jlcode=dis.readUTF();
	            String username=dis.readUTF();
	            String comment=dis.readUTF();

                File file=FileUtil.createFile();
                ou =new FileOutputStream(file);
                
                int length=0;
                
                int totalNum=0;
                byte[] buffer=new byte[1024];
                while((length=dis.readInt())>0){
                    dis.readFully(buffer, 0, length);//将原有的  length=dis.read(buffer, 0, length);改为此，为了接收全部字节
                    //System.out.println("--------"+length);
                    totalNum+=length;
                    ou.write(buffer, 0, length);
                    ou.flush();
                    }
                ou.close();
                ou=null;
                
                
                String filename=file.getName();
                
                CompressPicFun mypic = new CompressPicFun();   
                String sta= mypic.compressPic(filename);
                String dbrlt=dbUtil.savefile(jlcode,account,username,filename,comment);
                System.out.println("-----"+account+"---uploadfile--statu is:"+sta);
                //System.out.println("img.totalNum="+totalNum);

                dos.writeInt(Config.SEN_UPIMG);
				dos.writeUTF(dbrlt);
				dos.writeUTF(filename);
				dos.flush();
//				log.record("用户"+receiveId+"不在线，先把消息暂存在服务器端");
            
	        } catch (Exception e) {
	        	if(null!=ou){
	                try {ou.close(); ou=null;} catch (IOException e1) {e1.printStackTrace();}
	        	}
	            e.printStackTrace();
	        } 
	}
	
	/**
	 * 处理获得图片列表
	 */
	private void handGetPics(){
		try{
			String account=dis.readUTF();
			String username=dis.readUTF();
			String jlcode=dis.readUTF();
			
			System.out.println("请求消息账号： "+account);
			dos.writeInt(Config.SEN_CHKPICS);
			dos.writeUTF(dbUtil.getChkPics(account ,username,jlcode));
			dos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 处理标记消息为已读
	 */
	private void handMarkMsg(){
		try{
			String account=dis.readUTF();
			String username=dis.readUTF();
			String msgtype=dis.readUTF();
			String msgCode=dis.readUTF();
			
			System.out.println("请求消息账号： "+account);
			dos.writeInt(Config.SEN_MARKMSG);
			dos.writeUTF(dbUtil.getMarkMsg(account ,username,msgtype,msgCode));
			dos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void handTsyj(){
		try{
			String json = dis.readUTF();
			
			System.out.println("请求消息账号： "+this.account);
			dos.writeInt(Config.SEN_TSYJ);
			dos.writeUTF(dbUtil.getTsyj(account ,json));
			dos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void handModpwd(){
		try{
			String json = dis.readUTF();
			
			System.out.println("请求消息账号： "+this.account);
			dos.writeInt(Config.SEN_TSYJ);
			dos.writeUTF(dbUtil.getModpwd(account ,json));
			dos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 请求查询手册
	 */
	private void handleGetJcbz(){
		try{
			String json = dis.readUTF();
			
			System.out.println("请求消息账号： "+this.account);
			dos.writeInt(Config.SEN_JCBZ);
			dos.writeUTF(dbUtil.getJcbz(account ,json));
			dos.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	
///////////////////////////////////////////////////////////////////	
    /**
     * 获取当前系统的时间
     * @return long
     */
    private long getNowMilliSecond() {
    	return new Date().getTime();
    }
    /**
     * 获取当前系统的时间
     * @return boolean
     */
    private boolean checkHeartBeat() {
    	long gap=getNowMilliSecond()-getSubmitTime().getTime();
    	if(gap>20*1000){
    		setWorkState(false);
    		LogUtil.record("-----用户:"+useraccount+"----已经有5分没有发生连接，系统将关闭这个连接！！");
    		return false;
    	}
		return true;
    }
    /**
     * 打印系统信息
     * @return void
     */
    private void printSysSta() {    	
    	System.out.println("******系统当前登录的用户******");
    	System.out.println("***  数量 "+map.size());
    	for(String key : map.keySet()){
    		System.out.println("***   "+key+" : "+map.get(key).toString());
    	}
    	System.out.println("******************************");
    	
    }
}
