package com.timersch;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import com.zagerbone.util.DBTrans;

import sun.jdbc.rowset.CachedRowSet;

public class SchManager {
	private static SchManager sm =null;
	private static List<Alarm> alarms = new ArrayList<Alarm>();
	private static Timer timer = new Timer();
	private static boolean working = false;
	private static DBTrans dbtrans ;
	
	public static synchronized SchManager getInstance(){
		if(null==sm){
			sm = new SchManager();
		}
		return sm;
	}
	
	public static void addAlarm(Alarm alarm){
		alarms.add(alarm);
	}
	public static void startTick(){
		Date date = new Date();
		long nowMills = date.getTime();
		for(Alarm a:alarms){
			if(a.getExeMills() - nowMills>5000){
				timer.schedule(a, a.getExeMills() - nowMills);
			}
		}
		
	}
	public static void stopTick(){
		if(null!=timer){
			timer.cancel();
		}
	}
	
	public static void clearTick(){
		alarms.clear();
		
	}

	public static void restartTick(){
		stopTick();
		dbtrans = new DBTrans();
		try{
		Date date = new Date();
		CachedRowSet cachedrowset = dbtrans.getResultBySelect("select * from alarms where exeMills > "+date.getTime());
		System.out.println("alarm===size==="+cachedrowset.size());
		if(cachedrowset.size()>0){
			cachedrowset.next();
			Alarm a = new Alarm(cachedrowset.getString("receiver"), 
					Long.parseLong(cachedrowset.getString("exeMills")), 
					cachedrowset.getString("title"), 
					cachedrowset.getString("cont"), 
					cachedrowset.getString("auth"),
					cachedrowset.getString("auth_account"), 
					cachedrowset.getString("dept"));
			addAlarm(a);
		}
		
		
		timer = new Timer();
		startTick();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}