package com.zagerbone.jcjh;

public class JcjhDAOFactory
{

	public JcjhDAOFactory()
	{
	}

	public static JcjhDAO getDAO()
	{
		return new JcjhDAOImpl();
	}
}
