package com.zagerbone.mobileserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.zagerbone.mobileserver.net.ForwardTask;
import com.zagerbone.mobileserver.net.ThreadPool;

public class AjzMobileServer extends Thread {
	private static ServerSocket serverSocket;
	private static boolean workFlag=true;
	public void run() {
		try {
			serverSocket=new ServerSocket(8018);
			ThreadPool pool=ThreadPool.getInstance();
				System.out.println("---===服务器启动===---");
			while(workFlag){
				System.out.println("正在监听端口......");
				Socket socket=serverSocket.accept();
				System.out.println("服务器接收到一个客户端连接");
				ForwardTask task=new ForwardTask(socket);
				pool.addTask(task);
			}
			System.out.println("        --------------------------------------------------");
			System.out.println("        --------------------------------------------------");
			System.out.println("        ------------------手机服务器关闭-----------------");
			System.out.println("        --------------------------------------------------");
			System.out.println("        --------------------------------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void setWorkFlagFalse(){
		System.out.println("--------------设置手机服务器运行标志------------------------");
		workFlag=false;}
	public static void closeSocketServer(){
		System.out.println("--------------设置手机服务器运行标志------------------------");
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}}
}
