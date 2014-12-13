package com.zagerbone.dwlx;

public class DwlxDAOFactory
{

	public DwlxDAOFactory()
	{
	}

	public static DwlxDAO getDAO()
	{
		return new DwlxDAOImpl();
	}
}
