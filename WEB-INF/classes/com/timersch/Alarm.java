package com.timersch;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.TimerTask;

import com.zagerbone.mobileserver.constant.Config;
import com.zagerbone.mobileserver.net.ForwardTask;
import com.zagerbone.util.DBTrans;

import sun.jdbc.rowset.CachedRowSet;

public class Alarm extends TimerTask  {
	
	private String rec_name = "";//accounts , separate by ','
	private long exeMills = 0L;//
	//private HashMap<String,String> hp = new HashMap<String,String>();
	private String title = "";
	private String cont = "";
	private String auth = "";
	private String auth_account = "";
	private String dept = "";
	
	
	public Alarm(String receiver,long exeMills,String title,String cont,
			String auth , String auth_account, String dept){
		this.rec_name = receiver;
		this.exeMills = exeMills;
		this.title = title;
		this.cont = cont;
		this.auth = auth;
		this.auth_account = auth_account;
		this.dept = dept;
	}
	
	public long getExeMills(){
		return exeMills;
	}
	@Override
	public void run() {
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		try{
			dbtrans.beginTransaction();
			CachedRowSet crs1 = dbtrans.getResultBySelect("select * from codeset where tabname='txinfo' ");
			crs1.next();
			String prefix = crs1.getString("prefix");
			String manu = crs1.getString("manu");
			String curcode = String.valueOf(Integer.parseInt(manu)+1);
			while(curcode.length()<5){
				curcode="0"+curcode;
			}
			String txcode =prefix + curcode;
			
			//向txinfo 表中增加数据
			
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("insert into txinfo(title, txcode, tx_time, auth, dept, cont,rec_name,auth_account ,issend) values('");
			stringbuffer.append(this.title).append("','");
			stringbuffer.append(txcode).append("',").append("getdate()").append(",'");
			stringbuffer.append(this.auth).append("','");
			stringbuffer.append(this.dept).append("','");
			stringbuffer.append(this.cont).append("','");
			stringbuffer.append(this.rec_name).append("','");
			stringbuffer.append(auth_account).append("','1')");
			
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			//向inbox表中增加数据
			String ai[] = (rec_name).split(";");
			for (int i =0 ; i<ai.length;i++){
				stringbuffer = new StringBuffer();
				stringbuffer.append("insert into inbox(title,auth, rec_name, msgtype, isforceread, isread, msgcode, deli_time, read_time,auth_account, rec_account)" +
						" values('").append(this.title).append("','").append(this.auth).append("','").append(ai[i].split(",")[1]).append("','").append("提醒").append("','").append("0").append("','").append("0").append("','")
						.append(txcode).append("',").append("getdate()").append(",'").append("2000-01-01 00:00:000").append("','").append(auth_account).append("','").append(ai[i].split(",")[2]).append("')");
				//log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
			}
			////////////////////////////////
			String notifyaccount="";
			Socket s = null;
			DataOutputStream dos;
			for (int i =0 ; i<ai.length;i++){
				notifyaccount=ai[i].split(",")[2];
				s = ForwardTask.map.get(notifyaccount);
				if(null!=s){
					System.out.println("-------"+notifyaccount+"---在线----");
					dos=new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
					dos.writeInt(Config.SEN_NOTIFY);
					dos.writeUTF("newtxinfo");
					dos.flush();
				}
			}
			System.out.println("---map---:"+ForwardTask.map.toString());
			s=null;
			dos=null;
			//////////////////////
			stringbuffer = new StringBuffer();
			stringbuffer.append("update codeset set manu='").append(curcode).append("'  where tabname='txinfo' ");
			///log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			dbtrans.commitTransaction();
		}catch(Exception e){
			dbtrans.rollbackTransaction();
			e.printStackTrace();
		}

	}
}
