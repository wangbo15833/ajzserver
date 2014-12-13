package com.zagerbone.userinfo;

public class UserinfoDAOFactory
{

	public UserinfoDAOFactory()
	{
	}

	public static UserinfoDAO getDAO()
	{
		return new UserinfoDAOImpl();
	}
}
