// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BussinessProcessException.java

package com.shenghao.arch.exception;


// Referenced classes of package com.shenghao.arch.exception:
//			BaseException

public class BussinessProcessException extends BaseException
{

	private String ErrorCode;

	public BussinessProcessException()
	{
		ErrorCode = "";
	}

	public BussinessProcessException(String msg)
	{
		super(msg);
		ErrorCode = "";
	}

	public String getErrCode()
	{
		return ErrorCode;
	}

	public void setErrorCode(String ErrorCode)
	{
		this.ErrorCode = ErrorCode;
	}
}
