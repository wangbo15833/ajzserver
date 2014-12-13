package com.zagerbone.flfg;

public class FlfgDAOFactory
{

	public FlfgDAOFactory()
	{
	}

	public static FlfgDAO getDAO()
	{
		return new FlfgDAOImpl();
	}
}
