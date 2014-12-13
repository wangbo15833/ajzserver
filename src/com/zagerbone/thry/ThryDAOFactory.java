package com.zagerbone.thry;

public class ThryDAOFactory
{

	public ThryDAOFactory()
	{
	}

	public static ThryDAO getDAO()
	{
		return new ThryDAOImpl();
	}
}
