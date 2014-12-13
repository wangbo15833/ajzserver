// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbgglDAOFactory.java

package com.zagerbone.gzbggl;


// Referenced classes of package com.zagerbone.gzbggl:
//			GzbgglDAOImpl, GzbgglDAO

public class GzbgglDAOFactory
{

	public GzbgglDAOFactory()
	{
	}

	public static GzbgglDAO getDAO()
	{
		return new GzbgglDAOImpl();
	}
}
