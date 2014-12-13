// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SyclglDAO.java

package com.zagerbone.syclgl;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;

// Referenced classes of package com.zagerbone.syclgl:
//			Syclgl

public interface SyclglDAO
{

	public abstract String createSyclgl(Syclgl syclgl)
		throws BussinessProcessException;

	public abstract String publishPersonalSyclgl(Syclgl syclgl)
		throws BussinessProcessException;

	public abstract Query findSyclglList(Query query)
		throws BussinessProcessException;

	public abstract Query findSyclglList_noPage(Query query)
		throws BussinessProcessException;

	public abstract Query findPersonalSyclglList(Query query)
		throws BussinessProcessException;

	public abstract Syclgl findSyclglById(String s)
		throws BussinessProcessException;

	public abstract String modifySyclgl(Syclgl syclgl)
		throws BussinessProcessException;

	public abstract String modifySyclgl_dogd(Syclgl syclgl)
		throws BussinessProcessException;

	public abstract String modifySyclgl_cancal(Syclgl syclgl)
		throws BussinessProcessException;

	public abstract String deleteSyclgl(String s)
		throws BussinessProcessException;

	public abstract boolean hasNewSyclgl(String s)
		throws BussinessProcessException;
}
