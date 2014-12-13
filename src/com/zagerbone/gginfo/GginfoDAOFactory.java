package com.zagerbone.gginfo;

public class GginfoDAOFactory
{

	public GginfoDAOFactory()
	{
	}

	public static GginfoDAO getDAO()
	{
		return new GginfoDAOImpl();
	}
}
