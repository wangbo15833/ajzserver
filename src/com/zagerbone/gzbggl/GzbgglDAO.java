// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbgglDAO.java

package com.zagerbone.gzbggl;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.Query;

// Referenced classes of package com.zagerbone.gzbggl:
//			Gzbggl

public interface GzbgglDAO
{

	public abstract String createGzbggl(Gzbggl gzbggl)
		throws BussinessProcessException;

	public abstract String createGzbggl_forCNam(DBTrans dbtrans, Gzbggl gzbggl)
		throws BussinessProcessException;

	public abstract String publishPersonalGzbggl(Gzbggl gzbggl)
		throws BussinessProcessException;

	public abstract Query findGzbgglList(Query query)
		throws BussinessProcessException;

	public abstract Query findGzbgglList_noPage(Query query)
		throws BussinessProcessException;

	public abstract Query findPersonalGzbgglList(Query query)
		throws BussinessProcessException;

	public abstract Gzbggl findGzbgglById(String s)
		throws BussinessProcessException;

	public abstract String modifyGzbggl(Gzbggl gzbggl)
		throws BussinessProcessException;

	public abstract String deleteGzbggl(String s)
		throws BussinessProcessException;

	public abstract boolean hasNewGzbggl(String s)
		throws BussinessProcessException;
}
