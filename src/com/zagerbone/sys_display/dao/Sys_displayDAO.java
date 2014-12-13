// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Sys_displayDAO.java

package com.zagerbone.sys_display.dao;

import com.zagerbone.exception.BussinessProcessException;
import com.zagerbone.util.*;
import java.util.HashMap;
import sun.jdbc.rowset.CachedRowSet;

public interface Sys_displayDAO
{

	public abstract String create(GeneralObject generalobject)
		throws BussinessProcessException;

	public abstract CachedRowSet find(Query query)
		throws BussinessProcessException;

	public abstract GeneralObject findById(String s)
		throws BussinessProcessException;

	public abstract CachedRowSet findColumn(String s)
		throws BussinessProcessException;

	public abstract String modify(GeneralObject generalobject, HashMap hashmap)
		throws BussinessProcessException;

	public abstract String delete(String s)
		throws BussinessProcessException;

	public abstract String setDataBySQL(String s)
		throws BussinessProcessException;

	public abstract CachedRowSet getDataBySQL(String s)
		throws BussinessProcessException;

	public abstract GeneralDataArray getDataArray()
		throws BussinessProcessException;

	public abstract CachedRowSet getDisplayDataByPK(String s, String s1)
		throws BussinessProcessException;
}
