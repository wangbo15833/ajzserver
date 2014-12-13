// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QxbzglDAO.java

package com.zagerbone.qxbzgl;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;

// Referenced classes of package com.zagerbone.qxbzgl:
//			Qxbzgl

public interface QxbzglDAO
{

	public abstract String createQxbzgl(Qxbzgl qxbzgl)
		throws BussinessProcessException;

	public abstract String publishPersonalQxbzgl(Qxbzgl qxbzgl)
		throws BussinessProcessException;

	public abstract Query findQxbzglList(Query query)
		throws BussinessProcessException;

	public abstract Query findQxbzglList_noPage(Query query)
		throws BussinessProcessException;

	public abstract Query findPersonalQxbzglList(Query query)
		throws BussinessProcessException;

	public abstract Qxbzgl findQxbzglById(String s)
		throws BussinessProcessException;

	public abstract String modifyQxbzgl(Qxbzgl qxbzgl)
		throws BussinessProcessException;

	public abstract String deleteQxbzgl(String s)
		throws BussinessProcessException;

	public abstract boolean hasNewQxbzgl(String s)
		throws BussinessProcessException;
}
