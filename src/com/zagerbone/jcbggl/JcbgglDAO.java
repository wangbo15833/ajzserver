// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   JcbgglDAO.java

package com.zagerbone.jcbggl;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.Query;

// Referenced classes of package com.zagerbone.jcbggl:
//			Jcbggl

public interface JcbgglDAO
{

	public abstract String createJcbggl(Jcbggl jcbggl)
		throws BussinessProcessException;

	public abstract void createJcbggl_byData(DBTrans dbtrans, Jcbggl jcbggl)
		throws BussinessProcessException;

	public abstract String publishPersonalJcbggl(Jcbggl jcbggl)
		throws BussinessProcessException;

	public abstract Query findJcbgglList(Query query)
		throws BussinessProcessException;

	public abstract Query findJcbgglList_noPage(Query query)
		throws BussinessProcessException;

	public abstract Query findPersonalJcbgglList(Query query)
		throws BussinessProcessException;

	public abstract Jcbggl findJcbgglById(String s)
		throws BussinessProcessException;

	public abstract String modifyJcbggl(Jcbggl jcbggl)
		throws BussinessProcessException;

	public abstract String deleteJcbggl(String s)
		throws BussinessProcessException;

	public abstract boolean hasNewJcbggl(String s)
		throws BussinessProcessException;
}
