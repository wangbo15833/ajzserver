// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QxbzglDAOFactory.java

package com.zagerbone.qxbzgl;


// Referenced classes of package com.zagerbone.qxbzgl:
//			QxbzglDAOImpl, QxbzglDAO

public class QxbzglDAOFactory
{

	public QxbzglDAOFactory()
	{
	}

	public static QxbzglDAO getDAO()
	{
		return new QxbzglDAOImpl();
	}
}
