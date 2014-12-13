package com.zagerbone.mobileserver;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.zagerbone.mobileserver.constant.Config;

public class AjzMobileServerShutDown extends Thread {
	private static String ip = "127.0.0.1";
	private static int port = 8018;
	public void run(){
		try {
			System.out.println("------======AjzMobileServerShutDown被调用======------");
			Socket socket=new Socket(ip,port);
			DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			dos.writeInt(Config.SHUTDOWNSERVER);
			dos.flush();
			dos.writeInt(Config.SHUTDOWNSERVER);
			dos.flush();
			dos.close();
			System.out.println("------======AjzMobileServerShutDown调用结束======------");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
