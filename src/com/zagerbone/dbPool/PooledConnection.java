// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PooledConnection.java

package com.zagerbone.dbPool;

import java.sql.Connection;

public class PooledConnection
{

	Connection connection;
	boolean busy;

	public PooledConnection(Connection connection1)
	{
		connection = null;
		busy = false;
		connection = connection1;
	}

	public Connection getConnection()
	{
		return connection;
	}

	public void setConnection(Connection connection1)
	{
		connection = connection1;
	}

	public boolean isBusy()
	{
		return busy;
	}

	public void setBusy(boolean flag)
	{
		busy = flag;
	}
}
