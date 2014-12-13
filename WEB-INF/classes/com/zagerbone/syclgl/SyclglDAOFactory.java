// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyclglDAOFactory.java

package com.zagerbone.syclgl;


// Referenced classes of package com.zagerbone.syclgl:
//			SyclglDAOImpl, SyclglDAO

public class SyclglDAOFactory
{

	public SyclglDAOFactory()
	{
	}

	public static SyclglDAO getDAO()
	{
		return new SyclglDAOImpl();
	}
}
