package com.zagerbone.jcbz;

public class JcbzDAOFactory
{

	public JcbzDAOFactory()
	{
	}

	public static JcbzDAO getDAO()
	{
		return new JcbzDAOImpl();
	}
}
