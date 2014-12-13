package com.zagerbone.emp;

public class EmpDAOFactory
{

	public EmpDAOFactory()
	{
	}

	public static EmpDAO getDAO()
	{
		return new EmpDAOImpl();
	}
}
