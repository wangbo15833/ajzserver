// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseException.java

package com.shenghao.arch.exception;


public class BaseException extends Exception
{

	Throwable myException;
	private int errorCode;

	public BaseException()
	{
	}

	public BaseException(String s)
	{
		super(s);
	}

	public BaseException(Exception ex)
	{
		super(ex.toString());
	}

	public BaseException(String s, Throwable ex)
	{
		super(s);
		myException = ex;
	}

	public BaseException(int errorCode, String msg)
	{
		super(msg);
		this.errorCode = errorCode;
	}

	public BaseException(int errorCode, String msg, Throwable e)
	{
		super(msg);
		this.errorCode = errorCode;
		myException = e;
	}

	public int getErrorCode()
	{
		return errorCode;
	}
}
