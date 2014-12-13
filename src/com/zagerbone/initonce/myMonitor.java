package com.zagerbone.initonce;

import com.shenghao.arch.baseobject.BasicObject;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.Tools;
import sun.jdbc.rowset.CachedRowSet;

public class myMonitor implements Runnable
{
	
	public void run(){
	boolean flag1 = false;
	StringBuffer sbr = new StringBuffer();
	CachedRowSet crs = null;
	DBTrans dbtrans = null;
	Integer tick = 0;
	try{
		while(true){
			dbtrans = new DBTrans();
			
			crs = dbtrans.getResultBySelect("select convert(varchar(50),logclstart,20) as e, logautoclT as n, convert(varchar(50),dbbkstart,20) as e1, dbbkT as n1, convert(varchar(50),dbclstart,20) as e2, dbclT as n2 from sysdataset ");
			crs.next();
			String logclstart = crs.getString("e");
			String logautoclT = crs.getString("n");
			String dbbkstart = crs.getString("e1");
			String dbbkT = crs.getString("n1");
			String dbclstart = crs.getString("e2");
			String dbclT = crs.getString("n2");
			System.out.println("=========in thread =========");
			if (Math.abs(Tools.dateMinus_gapms(Tools.getCurrentDate_hms(),logclstart)-Integer.parseInt(logautoclT)*86400000)<60000){
			
				
				dbtrans.addSql("update sysdataset set logclstart='"+Tools.getCurrentDate_hms()+"' where id=1 ");
				flag1 = dbtrans.executeSql();
				
				
				dbtrans.addSql("delete from syslog");
				flag1 = dbtrans.executeSql();
				
				System.out.println("auto clear log!");
			}
			if (Math.abs(Tools.dateMinus_gapms(Tools.getCurrentDate_hms(),dbbkstart)-Integer.parseInt(dbbkT)*86400000)<60000){
			
				
				dbtrans.addSql("backup database ajz to disk='d:\\anjianzhan"+Tools.getCurrentDate()+".bak'");
				dbtrans.executeSql();
				System.out.println("auto backup db!"+Tools.dateMinus_gap(Tools.getCurrentDate_hms(),dbbkstart));
			}
			
			if (Math.abs(Tools.dateMinus_gapms(Tools.getCurrentDate_hms(),dbclstart)-Integer.parseInt(dbclT)*86400000)<60000){
				
				System.out.println("auto clear db!");
			}
			
			
			System.out.println("²âÊÔ°æ£¡");
			System.out.println("²âÊÔ°æ£¡");
			System.out.println("²âÊÔ°æ£¡");
			System.out.println("²âÊÔ°æ£¡");
			System.out.println("²âÊÔ°æ£¡");
			
			/* id, tname, tnamec, sta, pushtime, poptime, funid, fun, para, descr */
			try{
				sbr = new StringBuffer();
				sbr.append("select * from timersch where 1=1 and sta='false' and datediff(mi,poptime,getdate())<10 order by id asc ");
				crs = dbtrans.getResultBySelect(sbr.toString());
				if (crs.size()!=0){
					while(crs.next()){
						String name = crs.getString("name");
						String namec = crs.getString("namec");
						String funid = crs.getString("funid");
						String fun = crs.getString("fun");
						String para = crs.getString("para");
						
						int ifunid = Integer.parseInt(funid);
						switch (ifunid){
							case 0 :
								fun0(para,dbtrans);
								break;
							case 1 :
								fun1(para,dbtrans);
								break;
							case 2 :
								fun2(para,dbtrans);
								break;
							case 3 :
								fun3(para,dbtrans);
								break;
							case 4 :
								fun4(para,dbtrans);
								break;
							case 5 :
								fun5(para,dbtrans);
								break;
							case 6 :
								fun6(para,dbtrans);
								break;
							default:
								break;						
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			tick ++ ;
			if(tick>240){
				tick=0;
				sbr = new StringBuffer();
				sbr.append("delete from timersch where sta='true' ");
				dbtrans.addSql(sbr.toString());
				flag1 = dbtrans.executeSql();
			}
			dbtrans = null;
			Thread.sleep(45000);
		}//while end
	}catch(Exception e){
		e.printStackTrace();
	}
  }
  private void fun0(String para , DBTrans dbtrans){
	try{
		
	}catch(Exception e){
		e.printStackTrace();
	}
  }
  private void fun1(String para , DBTrans dbtrans){
  	try{
			
	}catch(Exception e){
		e.printStackTrace();
	}
  }
  private void fun2(String para , DBTrans dbtrans){
  	try{
			
	}catch(Exception e){
		e.printStackTrace();
	}
  }
  private void fun3(String para , DBTrans dbtrans){
  	try{
			
	}catch(Exception e){
		e.printStackTrace();
	}
  }
  private void fun4(String para , DBTrans dbtrans){
  	try{
			
	}catch(Exception e){
		e.printStackTrace();
	}
  }
  private void fun5(String para , DBTrans dbtrans){
  	try{
			
	}catch(Exception e){
		e.printStackTrace();
	}
  }
  private void fun6(String para , DBTrans dbtrans){
  	try{
			
	}catch(Exception e){
		e.printStackTrace();
	}
  }
}