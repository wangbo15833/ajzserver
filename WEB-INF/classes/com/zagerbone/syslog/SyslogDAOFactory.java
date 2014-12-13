package com.zagerbone.syslog;

public class SyslogDAOFactory
{

	public SyslogDAOFactory()
	{
	}

	public static SyslogDAO getDAO()
	{
		return new SyslogDAOImpl();
	}
}
