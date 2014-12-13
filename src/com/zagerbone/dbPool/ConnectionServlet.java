// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ConnectionServlet.java

package com.zagerbone.dbPool;

import com.zagerbone.sys_display.dao.Sys_displayDAOFactory;
import com.zagerbone.sys_display.dao.Sys_displayDAOImpl;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.SysTrace;
import com.zagerbone.util.data.SysDbServer;
import com.zagerbone.util3.MySysds;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.dbPool:
//			ConnectionPool

public class ConnectionServlet extends HttpServlet
{

	HashMap hp_inner_jdbcDriver;
	HashMap hp_inner_dbURL;
	HashMap hp_inner_username;
	HashMap hp_inner_password;
	HashMap hp_dsName;
	HashMap hp_dsType;
	HashMap hp_dsPswd;
	ConnectionPool connectionPool;
	SimpleDateFormat formatter;

	public ConnectionServlet()
	{
		hp_inner_jdbcDriver = new HashMap();
		hp_inner_dbURL = new HashMap();
		hp_inner_username = new HashMap();
		hp_inner_password = new HashMap();
		hp_dsName = new HashMap();
		hp_dsType = new HashMap();
		hp_dsPswd = new HashMap();
		connectionPool = null;
		formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss.SSS");
	}

	public void init(ServletConfig servletconfig)
		throws ServletException
	{
		super.init(servletconfig);
		String s = "org.gjt.mm.mysql.Driver";
		String s1 = "jdbc:mysql://localhost/tongwei?useUnicode=true&characterEncoding=gb2312";
		String s2 = "";
		String s3 = "";
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		try
		{
			String s4 = "DB_oracle";
			hp_inner_jdbcDriver.put(s4, "oracle.jdbc.driver.OracleDriver");
			hp_inner_dbURL.put(s4, "jdbc:oracle:thin:@127.0.0.1:1521:EISDATA");
			hp_inner_username.put(s4, "tongwei");
			hp_inner_password.put(s4, "111");
			s4 = "DB_mypubs";
			hp_inner_jdbcDriver.put("DB_mypubs", "com.microsoft.jdbc.sqlserver.SQLServerDriver");
			hp_inner_dbURL.put("DB_mypubs", "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=TDJY");
			hp_inner_username.put(s4, "sa");
			hp_inner_password.put(s4, "111");
			String s5 = "219.243.10.112";
			String s6 = "TGXY";
			s5 = SysDbServer.getAttribute("DSServerIp");
			s6 = SysDbServer.getAttribute("DSName");
			System.out.println((new StringBuilder()).append("ip=").append(s5).toString());
			System.out.println((new StringBuilder()).append("db=").append(s6).toString());
			s4 = "DB-0";
			hp_inner_jdbcDriver.put("DB-0", "com.microsoft.jdbc.sqlserver.SQLServerDriver");
			hp_inner_dbURL.put("DB-0", (new StringBuilder()).append("jdbc:microsoft:sqlserver://").append(s5).append(":1433;DatabaseName=").append(s6).toString());
			hp_inner_username.put("DB-0", "weixun");
			hp_inner_password.put("DB-0", "aJz456$%^");
			s4 = "DB-1";
			hp_inner_jdbcDriver.put("DB-1", "com.mysql.jdbc.Driver");
			hp_inner_dbURL.put("DB-1", (new StringBuilder()).append("jdbc:mysql://").append(s5).append(":3306/").append(s6).toString());
			hp_inner_username.put("DB-1", "root");
			hp_inner_password.put("DB-1", "123");
			s4 = "DB_TJWJC";
			hp_inner_jdbcDriver.put("DB_TJWJC", "com.microsoft.jdbc.sqlserver.SQLServerDriver");
			hp_inner_dbURL.put("DB_TJWJC", "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=TJWJC");
			hp_inner_username.put("DB_TJWJC", "sa");
			hp_inner_password.put("DB_TJWJC", "111");
			s4 = "DB_MRP";
			hp_inner_jdbcDriver.put(s4, "com.microsoft.jdbc.sqlserver.SQLServerDriver");
			hp_inner_dbURL.put(s4, "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=MRP");
			hp_inner_username.put(s4, "sa");
			hp_inner_password.put(s4, "111");
			s4 = "DB_LNC";
			hp_inner_jdbcDriver.put(s4, "com.microsoft.jdbc.sqlserver.SQLServerDriver");
			hp_inner_dbURL.put(s4, "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=LNC");
			hp_inner_username.put(s4, "sa");
			hp_inner_password.put(s4, "111");
			MySysds.getDataSources(hp_dsName, hp_dsType, hp_dsPswd);
			Collection collection = getJNDI(hp_dsType);
			Iterator iterator = collection.iterator();
			System.out.println((new StringBuilder()).append("c_jndiname.size()===========================").append(collection.size()).toString());
			String s7;
			ConnectionPool connectionpool;
			for (; iterator.hasNext(); bindDataSource(s7, connectionpool))
			{
				String s8 = (String)iterator.next();
				String s9 = (String)hp_dsPswd.get(s8);
				s7 = (String)hp_dsName.get(s8);
				String s10 = (String)hp_inner_username.get(s8);
				String s11 = (String)hp_inner_password.get(s8);
				String s12 = (String)hp_inner_jdbcDriver.get(s8);
				String s13 = (String)hp_inner_dbURL.get(s8);
				System.out.println((new StringBuilder()).append("username.size()===========================").append(s10).toString());
				System.out.println((new StringBuilder()).append("password.size()===========================").append(s11).toString());
				System.out.println((new StringBuilder()).append("开始连接到").append(s7).toString());
				connectionpool = null;
				try
				{
					connectionpool = new ConnectionPool(s12, s13, s10, s11);
					connectionpool.setInitialConnections(5);
					connectionpool.setIncrementalConnections(5);
					connectionpool.setMaxConnections(20);
					System.out.println("创建连接池对象");
					connectionpool.createPool();
				}
				catch (Exception exception1)
				{
					exception1.printStackTrace();
				}
				System.out.println((new StringBuilder()).append("已经连接到").append(s7).toString());
			}

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new ServletException("Unable to initialize connection pool", exception);
		}
		javax.servlet.ServletContext servletcontext = getServletContext();
	}

	public void destroy()
	{
		Collection collection = getJNDI(hp_dsType);
		Iterator iterator = collection.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			String s = (String)iterator.next();
			String s1 = (String)hp_dsName.get(s);
			ConnectionPool connectionpool = null;
			try
			{
				InitialContext initialcontext = new InitialContext();
				connectionpool = (ConnectionPool)initialcontext.lookup(s1);
			}
			catch (NamingException namingexception)
			{
				connectionpool = null;
			}
			if (connectionpool != null)
				try
				{
					System.out.println((new StringBuilder()).append("开始关闭连接").append(s1).toString());
					connectionpool.closeConnections();
				}
				catch (Exception exception)
				{
					System.out.println((new StringBuilder()).append("关断连接池时产生了异常！").append(s1).toString());
				}
		} while (true);
		super.destroy();
	}

	public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
		throws ServletException, IOException
	{
		System.out.println("doGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGetdoGet");
		String s = null;
		String s1 = "";
		Object obj = null;
		DBTrans dbtrans = new DBTrans(s1);
		try
		{
			dbtrans.beginTransaction();
			String s2 = "select * from ba_company where TakeEffect='1'";
			for (CachedRowSet cachedrowset = Sys_displayDAOFactory.getDAO(dbtrans).getDataBySQL(s2); cachedrowset.next();)
				s = cachedrowset.getString("jndiname");

			s2 = "update ba_company set TakeEffect='1' where wkid='TDJY'";
			Sys_displayDAOFactory.getDAO(dbtrans).setDataBySQL(s2);
			dbtrans.commitTransaction();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			dbtrans.rollbackTransaction();
		}
		String s3 = (String)SysTrace.hashMap.get(s);
		ConnectionPool connectionpool = getDataSource(s3);
		unbindDataSource(s3);
		String s4 = "DB-0";
		String s5 = (String)SysTrace.hashMap.get(s4);
		unbindDataSource(s5);
		bindDataSource(s5, connectionpool);
		httpservletresponse.setContentType("text/html; charset=GBK");
		PrintWriter printwriter = httpservletresponse.getWriter();
		printwriter.println((new StringBuilder()).append("<html><tr><td>成功选择").append(s3).append("，请刷新！</td></tr></html>").toString());
	}

	public void bindDataSource(String s, ConnectionPool connectionpool)
	{
		try
		{
			Object obj = null;
			InitialContext initialcontext = new InitialContext();
			try
			{
				obj = initialcontext.lookup(s);
			}
			catch (NamingException namingexception1)
			{
				obj = null;
			}
			if (obj != null)
			{
				System.out.println((new StringBuilder()).append(s).append("对象已经绑定").toString());
				initialcontext.unbind(s);
			}
			initialcontext.bind(s, connectionpool);
			System.out.println((new StringBuilder()).append(s).append("对象绑定成功!").toString());
		}
		catch (NamingException namingexception)
		{
			namingexception.printStackTrace();
		}
	}

	public void unbindDataSource(String s)
	{
		try
		{
			Object obj = null;
			InitialContext initialcontext = new InitialContext();
			try
			{
				obj = initialcontext.lookup(s);
			}
			catch (NamingException namingexception1)
			{
				obj = null;
			}
			if (obj != null)
			{
				System.out.println((new StringBuilder()).append(s).append("对象已经绑定").toString());
				initialcontext.unbind(s);
				System.out.println((new StringBuilder()).append(s).append("对象松绑成功!").toString());
			}
		}
		catch (NamingException namingexception)
		{
			namingexception.printStackTrace();
		}
	}

	public ConnectionPool getDataSource(String s)
	{
		ConnectionPool connectionpool = null;
		try
		{
			Object obj = null;
			InitialContext initialcontext = new InitialContext();
			connectionpool = (ConnectionPool)initialcontext.lookup(s);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return connectionpool;
	}

	protected synchronized String format(long l)
	{
		String s = formatter.format(new Date(l));
		return s;
	}

	public Collection getJNDI(HashMap hashmap)
	{
		ArrayList arraylist = new ArrayList();
		Set set = hashmap.entrySet();
		Iterator iterator = set.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			String s = (String)entry.getKey();
			String s1 = (String)entry.getValue();
			if (s1.equals("servlet"))
				arraylist.add(s);
		} while (true);
		return arraylist;
	}
}
