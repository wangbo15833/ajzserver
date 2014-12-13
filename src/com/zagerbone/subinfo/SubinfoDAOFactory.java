package com.zagerbone.subinfo;

public class SubinfoDAOFactory
{

	public SubinfoDAOFactory()
	{
	}

	public static SubinfoDAO getDAO()
	{
		return new SubinfoDAOImpl();
	}
}
