package com.zagerbone.pritab;

public class PritabDAOFactory
{

	public PritabDAOFactory()
	{
	}

	public static PritabDAO getDAO()
	{
		return new PritabDAOImpl();
	}
}
