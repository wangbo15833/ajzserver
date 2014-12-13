package com.zagerbone.txinfo;

public class TxinfoDAOFactory
{

	public TxinfoDAOFactory()
	{
	}

	public static TxinfoDAO getDAO()
	{
		return new TxinfoDAOImpl();
	}
}
