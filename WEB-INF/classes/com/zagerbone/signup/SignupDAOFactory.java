package com.zagerbone.signup;

public class SignupDAOFactory
{

	public SignupDAOFactory()
	{
	}

	public static SignupDAO getDAO()
	{
		return new SignupDAOImpl();
	}
}
