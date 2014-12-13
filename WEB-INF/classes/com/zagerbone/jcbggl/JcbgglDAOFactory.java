// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JcbgglDAOFactory.java

package com.zagerbone.jcbggl;


// Referenced classes of package com.zagerbone.jcbggl:
//			JcbgglDAOImpl, JcbgglDAO

public class JcbgglDAOFactory
{

	public JcbgglDAOFactory()
	{
	}

	public static JcbgglDAO getDAO()
	{
		return new JcbgglDAOImpl();
	}
}
