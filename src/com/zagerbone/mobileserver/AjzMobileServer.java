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
				System.out.println("---===����������===---");
			while(workFlag){
				System.out.println("���ڼ����˿�......");
				Socket socket=serverSocket.accept();
				System.out.println("���������յ�һ���ͻ�������");
				ForwardTask task=new ForwardTask(socket);
				pool.addTask(task);
			}
			System.out.println("        --------------------------------------------------");
			System.out.println("        --------------------------------------------------");
			System.out.println("        ------------------�ֻ��������ر�-----------------");
			System.out.println("        --------------------------------------------------");
			System.out.println("        --------------------------------------------------");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void setWorkFlagFalse(){
		System.out.println("--------------�����ֻ����������б�־------------------------");
		workFlag=false;}
	public static void closeSocketServer(){
		System.out.println("--------------�����ֻ����������б�־------------------------");
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}}
}
