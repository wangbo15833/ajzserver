package com.zagerbone.tsyj;

public class TsyjDAOFactory
{

	public TsyjDAOFactory()
	{
	}

	public static TsyjDAO getDAO()
	{
		return new TsyjDAOImpl();
	}
}
