// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Tools3.java

package com.zagerbone.util3;

import com.zagerbone.exception.BussinessProcessException;
import com.zagerbone.util.GeneralObject;
import com.zagerbone.util.Tools;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import sun.jdbc.rowset.CachedRowSet;

public class Tools3
{

	public Tools3()
	{
	}

	public static GeneralObject CrsToGObj(CachedRowSet cachedrowset)
		throws BussinessProcessException
	{
		GeneralObject generalobject = new GeneralObject();
		try
		{
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				for (int j = 1; j <= i; j++)
					if (!resultsetmetadata.getColumnName(j).equals("id"))
						generalobject.setProp(resultsetmetadata.getColumnName(j), Tools.nvl(cachedrowset.getString(j)));

			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return generalobject;
	}

	public static GeneralObject CrsToGObj_nonext(CachedRowSet cachedrowset)
		throws BussinessProcessException
	{
		GeneralObject generalobject = new GeneralObject();
		try
		{
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			for (int j = 1; j <= i; j++)
				if (!resultsetmetadata.getColumnName(j).equals("id"))
					generalobject.setProp(resultsetmetadata.getColumnName(j), Tools.nvl(cachedrowset.getString(j)));

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return generalobject;
	}

	public static HashMap CrsToHashMap(CachedRowSet cachedrowset)
		throws BussinessProcessException
	{
		HashMap hashmap = new HashMap();
		try
		{
			cachedrowset.beforeFirst();
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				for (int j = 1; j <= i; j++)
					if (!resultsetmetadata.getColumnName(j).equals("id"))
						hashmap.put(resultsetmetadata.getColumnName(j), Tools.nvl(cachedrowset.getString(j)));

			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return hashmap;
	}
}
