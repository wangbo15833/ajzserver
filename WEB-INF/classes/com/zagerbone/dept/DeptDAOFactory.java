package com.zagerbone.dept;

public class DeptDAOFactory
{

	public DeptDAOFactory()
	{
	}

	public static DeptDAO getDAO()
	{
		return new DeptDAOImpl();
	}
}
