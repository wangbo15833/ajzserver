// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ConnectionPool.java

package com.zagerbone.dbPool;

import java.io.PrintStream;
import java.sql.*;
import java.util.*;

// Referenced classes of package com.zagerbone.dbPool:
//			PooledConnection

public class ConnectionPool
{

	private String jdbcDriver;
	private String dbUrl;
	private String dbUsername;
	private String dbPassword;
	private String testTable;
	private int initialConnections;
	private int incrementalConnections;
	private int maxConnections;
	private int inuseConnections;
	private Vector connections;

	public ConnectionPool(String s, String s1, String s2, String s3)
	{
		jdbcDriver = "";
		dbUrl = "";
		dbUsername = "";
		dbPassword = "";
		testTable = "";
		initialConnections = 10;
		incrementalConnections = 5;
		maxConnections = 50;
		inuseConnections = 0;
		connections = null;
		jdbcDriver = s;
		dbUrl = s1;
		dbUsername = s2;
		dbPassword = s3;
	}

	public int getInitialConnections()
	{
		return initialConnections;
	}

	public void setInitialConnections(int i)
	{
		initialConnections = i;
	}

	public int getIncrementalConnections()
	{
		return incrementalConnections;
	}

	public void setIncrementalConnections(int i)
	{
		incrementalConnections = i;
	}

	public int getMaxConnections()
	{
		return maxConnections;
	}

	public void setMaxConnections(int i)
	{
		maxConnections = i;
	}

	public int getInuseConnections()
	{
		if (connections != null)
			inuseConnections = connections.size();
		else
			inuseConnections = 0;
		return inuseConnections;
	}

	public Collection getPooledConnection()
	{
		if (connections != null)
			return connections;
		else
			return null;
	}

	public String getTestTable()
	{
		return testTable;
	}

	public void setTestTable(String s)
	{
		testTable = s;
	}

	public synchronized void createPool()
		throws Exception
	{
		if (connections != null)
		{
			return;
		} else
		{
			Driver driver = (Driver)Class.forName(jdbcDriver).newInstance();
			DriverManager.registerDriver(driver);
			connections = new Vector();
			createConnections(initialConnections);
			return;
		}
	}

	private void createConnections(int i)
		throws SQLException
	{
		for (int j = 0; j < i && (maxConnections <= 0 || connections.size() < maxConnections); j++)
		{
			connections.addElement(new PooledConnection(newConnection()));
			System.out.println("a connection created...");
		}

	}

	private Connection newConnection()
		throws SQLException
	{
		System.out.println("newConnection()开始......");
		Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
		if (connections.size() == 0)
		{
			DatabaseMetaData databasemetadata = connection.getMetaData();
			int i = databasemetadata.getMaxConnections();
			if (i > 0 && maxConnections > i)
				maxConnections = i;
		}
		System.out.println("newConnection()结束......");
		return connection;
	}

	public synchronized Connection getConnection()
		throws SQLException
	{
		if (connections == null)
			return null;
		Connection connection;
		for (connection = getFreeConnection(); connection == null; connection = getFreeConnection())
			wait(250);

		return connection;
	}

	private Connection getFreeConnection()
		throws SQLException
	{
		Connection connection = findFreeConnection();
		if (connection == null)
		{
			createConnections(incrementalConnections);
			connection = findFreeConnection();
			if (connection == null)
				return null;
		}
		return connection;
	}

	private Connection findFreeConnection()
		throws SQLException
	{
		Connection connection = null;
		Object obj = null;
		Vector vector = connections;
		Iterator iterator = vector.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			PooledConnection pooledconnection = (PooledConnection)iterator.next();
			if (pooledconnection.isBusy())
				continue;
			connection = pooledconnection.getConnection();
			pooledconnection.setBusy(true);
			if (!testConnection(connection))
			{
				connection = newConnection();
				pooledconnection.setConnection(connection);
			}
			break;
		} while (true);
		return connection;
	}

	public int findNumOfBuyConnection()
		throws SQLException
	{
		int i = 0;
		Object obj = null;
		Object obj1 = null;
		Vector vector = connections;
		Iterator iterator = vector.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			PooledConnection pooledconnection = (PooledConnection)iterator.next();
			if (pooledconnection.isBusy())
				i++;
		} while (true);
		return i;
	}

	private boolean testConnection(Connection connection)
	{
		try
		{
			if (testTable.equals(""))
				connection.setAutoCommit(true);
		}
		catch (SQLException sqlexception)
		{
			sqlexception.printStackTrace();
			closeConnection(connection);
			return false;
		}
		return true;
	}

	public void returnConnection(Connection connection)
	{
		if (connections == null)
			return;
		Object obj = null;
		Vector vector = connections;
		Iterator iterator = vector.iterator();
		do
		{
			if (!iterator.hasNext())
				break;
			PooledConnection pooledconnection = (PooledConnection)iterator.next();
			if (connection != pooledconnection.getConnection())
				continue;
			pooledconnection.setBusy(false);
			break;
		} while (true);
	}

	public synchronized void refreshConnections()
		throws SQLException
	{
		if (connections == null)
			return;
		Object obj = null;
		PooledConnection pooledconnection;
		for (Enumeration enumeration = connections.elements(); enumeration.hasMoreElements(); pooledconnection.setBusy(false))
		{
			pooledconnection = (PooledConnection)enumeration.nextElement();
			if (!pooledconnection.isBusy())
				wait(10000);
			closeConnection(pooledconnection.getConnection());
			pooledconnection.setConnection(newConnection());
		}

	}

	public synchronized void closeConnections()
		throws SQLException
	{
		if (connections == null)
		{
			System.out.println("直接关闭连接");
			return;
		}
		System.out.println((new StringBuilder()).append("现在的总连接数为====").append(connections.size()).toString());
		System.out.println((new StringBuilder()).append("现在的忙连接数为====").append(findNumOfBuyConnection()).toString());
		Object obj = null;
		Enumeration enumeration = connections.elements();
		int i = 0;
		for (; enumeration.hasMoreElements(); System.out.println((new StringBuilder()).append("关闭连接").append(i).toString()))
		{
			i++;
			PooledConnection pooledconnection = (PooledConnection)enumeration.nextElement();
			if (pooledconnection.isBusy())
				wait(1000);
			closeConnection(pooledconnection.getConnection());
		}

		connections = null;
	}

	public void closeConnection(Connection connection)
	{
		try
		{
			connection.close();
		}
		catch (SQLException sqlexception)
		{
			sqlexception.printStackTrace();
		}
	}

	private void wait(int i)
	{
		try
		{
			Thread.sleep(i);
		}
		catch (InterruptedException interruptedexception) { }
	}
}
