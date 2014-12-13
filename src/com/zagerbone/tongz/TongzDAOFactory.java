package com.zagerbone.tongz;

public class TongzDAOFactory
{

	public TongzDAOFactory()
	{
	}

	public static TongzDAO getDAO()
	{
		return new TongzDAOImpl();
	}
}
