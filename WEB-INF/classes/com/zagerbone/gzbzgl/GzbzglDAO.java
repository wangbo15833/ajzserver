// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GzbzglDAO.java

package com.zagerbone.gzbzgl;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;

// Referenced classes of package com.zagerbone.gzbzgl:
//			Gzbzgl

public interface GzbzglDAO
{

	public abstract String createGzbzgl(Gzbzgl gzbzgl)
		throws BussinessProcessException;

	public abstract String publishPersonalGzbzgl(Gzbzgl gzbzgl)
		throws BussinessProcessException;

	public abstract Query findGzbzglList(Query query)
		throws BussinessProcessException;

	public abstract Query findGzbzglList_noPage(Query query)
		throws BussinessProcessException;

	public abstract Query findPersonalGzbzglList(Query query)
		throws BussinessProcessException;

	public abstract Gzbzgl findGzbzglById(String s)
		throws BussinessProcessException;

	public abstract String modifyGzbzgl(Gzbzgl gzbzgl)
		throws BussinessProcessException;

	public abstract String deleteGzbzgl(String s)
		throws BussinessProcessException;

	public abstract boolean hasNewGzbzgl(String s)
		throws BussinessProcessException;
}
