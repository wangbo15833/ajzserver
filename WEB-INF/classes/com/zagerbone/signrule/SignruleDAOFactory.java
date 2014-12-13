package com.zagerbone.signrule;

public class SignruleDAOFactory
{

	public SignruleDAOFactory()
	{
	}

	public static SignruleDAO getDAO()
	{
		return new SignruleDAOImpl();
	}
}
