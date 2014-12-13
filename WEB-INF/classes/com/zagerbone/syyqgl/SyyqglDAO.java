// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyyqglDAO.java

package com.zagerbone.syyqgl;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;

// Referenced classes of package com.zagerbone.syyqgl:
//			Syyqgl

public interface SyyqglDAO
{

	public abstract String createSyyqgl(Syyqgl syyqgl)
		throws BussinessProcessException;

	public abstract String publishPersonalSyyqgl(Syyqgl syyqgl)
		throws BussinessProcessException;

	public abstract Query findSyyqglList(Query query)
		throws BussinessProcessException;

	public abstract Query findSyyqglList_noPage(Query query)
		throws BussinessProcessException;

	public abstract Query findPersonalSyyqglList(Query query)
		throws BussinessProcessException;

	public abstract Syyqgl findSyyqglById(String s)
		throws BussinessProcessException;

	public abstract String modifySyyqgl(Syyqgl syyqgl)
		throws BussinessProcessException;

	public abstract String deleteSyyqgl(String s)
		throws BussinessProcessException;

	public abstract boolean hasNewSyyqgl(String s)
		throws BussinessProcessException;
}
