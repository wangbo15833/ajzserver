package com.zagerbone.inbox;

public class InboxDAOFactory
{

	public InboxDAOFactory()
	{
	}

	public static InboxDAO getDAO()
	{
		return new InboxDAOImpl();
	}
}
