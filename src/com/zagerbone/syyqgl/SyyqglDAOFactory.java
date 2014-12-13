// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyyqglDAOFactory.java

package com.zagerbone.syyqgl;


// Referenced classes of package com.zagerbone.syyqgl:
//			SyyqglDAOImpl, SyyqglDAO

public class SyyqglDAOFactory
{

	public SyyqglDAOFactory()
	{
	}

	public static SyyqglDAO getDAO()
	{
		return new SyyqglDAOImpl();
	}
}
