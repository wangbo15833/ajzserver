package com.zagerbone.jdjh;

public class JdjhDAOFactory
{

	public JdjhDAOFactory()
	{
	}

	public static JdjhDAO getDAO()
	{
		return new JdjhDAOImpl();
	}
}
