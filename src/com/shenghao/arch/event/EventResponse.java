// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   EventResponse.java

package com.shenghao.arch.event;

import java.io.PrintStream;
import java.io.Serializable;

public class EventResponse
	implements Serializable
{

	private boolean successFlag;
	private String errorCode;
	private String errorMessage;
	private Object body;
	private Object paramBody;
	private Object generalBody;
	private String functionID;
	private String userid;

	public EventResponse()
	{
		successFlag = false;
		errorCode = "";
		errorMessage = "处理成功！";
		body = null;
		paramBody = null;
		generalBody = null;
		functionID = "";
		userid = "";
	}

	public void setSuccessFlag(boolean flag)
	{
		successFlag = flag;
	}

	public boolean isSuccessFlag()
	{
		return successFlag;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public String getErrorCode()
	{
		if (errorCode == null || errorCode.equals(""))
			return "00000000";
		if (functionID == null || functionID.equals(""))
		{
			return (new StringBuilder()).append("00000000").append(errorCode).toString();
		} else
		{
			System.out.println((new StringBuilder()).append("---===   EventResponse getErrorMessage ").append(functionID).append("  ===---").toString());
			return (new StringBuilder()).append(functionID.substring(1, 3)).append(functionID.substring(4, 6)).append(functionID.substring(7, 9)).append(functionID.substring(10, 12)).append(errorCode).toString();
		}
	}

	public Object getBody()
	{
		return body;
	}

	public void setBody(Object obj)
	{
		body = obj;
	}

	public Object getGeneralBody()
	{
		return generalBody;
	}

	public void setGeneralBody(Object obj)
	{
		generalBody = obj;
	}

	public void setErrorCode(String s)
	{
		errorCode = s;
	}

	public void setErrorMessage(String s)
	{
		errorMessage = s;
	}

	public String getFunctionID()
	{
		return functionID;
	}

	public void setFunctionID(String s)
	{
		functionID = s;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String s)
	{
		userid = s;
	}

	public Object getParamBody()
	{
		return paramBody;
	}

	public void setParamBody(Object obj)
	{
		paramBody = obj;
	}
}
