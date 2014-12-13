package com.zagerbone.xmzg;

public class XmzgDAOFactory
{

	public XmzgDAOFactory()
	{
	}

	public static XmzgDAO getDAO()
	{
		return new XmzgDAOImpl();
	}
}
