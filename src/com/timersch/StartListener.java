package com.timersch;
import java.util.Timer;
import com.timersch.SchManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartListener implements ServletContextListener {
  
  private SchManager schManager = null;

  public void contextInitialized(ServletContextEvent event) {
    //timer = new Timer(true);
    //设置任务计划，启动和间隔时间
    //timer.schedule(new MyTask(), 0, 86400000);
	try{
		Thread.sleep(2000);
		System.out.println("----====开始时钟管理====----");
	}catch(Exception e){
		e.printStackTrace();
	}
	schManager = SchManager.getInstance();
	schManager.startTick();
  }

  public void contextDestroyed(ServletContextEvent event) {
    if(null!=schManager){
		schManager.clearTick();
		System.out.println("----====结束时钟管理====----");
	}
  }
  
}