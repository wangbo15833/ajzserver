package com.zagerbone.jdjl;

public class JdjlDAOFactory
{

	public JdjlDAOFactory()
	{
	}

	public static JdjlDAO getDAO()
	{
		return new JdjlDAOImpl();
	}
}
