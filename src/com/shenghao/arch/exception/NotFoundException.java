// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   NotFoundException.java

package com.shenghao.arch.exception;


// Referenced classes of package com.shenghao.arch.exception:
//			BaseException

public class NotFoundException extends BaseException
{

	public NotFoundException(String msg)
	{
		super(0x5f8fde0, msg);
	}

	public NotFoundException(String msg, Throwable myException)
	{
		super(0x5f8fde0, msg, myException);
	}
}
