package com.zagerbone.sysdataset;

public class SysdatasetDAOFactory
{

	public SysdatasetDAOFactory()
	{
	}

	public static SysdatasetDAO getDAO()
	{
		return new SysdatasetDAOImpl();
	}
}
