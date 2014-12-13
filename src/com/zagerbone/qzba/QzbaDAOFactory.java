package com.zagerbone.qzba;

public class QzbaDAOFactory
{

	public QzbaDAOFactory()
	{
	}

	public static QzbaDAO getDAO()
	{
		return new QzbaDAOImpl();
	}
}
