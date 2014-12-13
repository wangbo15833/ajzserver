package com.zagerbone.tabmap;

public class TabmapDAOFactory
{

	public TabmapDAOFactory()
	{
	}

	public static TabmapDAO getDAO()
	{
		return new TabmapDAOImpl();
	}
}
