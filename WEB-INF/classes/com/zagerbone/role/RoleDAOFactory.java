package com.zagerbone.role;

public class RoleDAOFactory
{

	public RoleDAOFactory()
	{
	}

	public static RoleDAO getDAO()
	{
		return new RoleDAOImpl();
	}
}
