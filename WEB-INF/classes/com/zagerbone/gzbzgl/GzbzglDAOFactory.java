// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbzglDAOFactory.java

package com.zagerbone.gzbzgl;


// Referenced classes of package com.zagerbone.gzbzgl:
//			GzbzglDAOImpl, GzbzglDAO

public class GzbzglDAOFactory
{

	public GzbzglDAOFactory()
	{
	}

	public static GzbzglDAO getDAO()
	{
		return new GzbzglDAOImpl();
	}
}
