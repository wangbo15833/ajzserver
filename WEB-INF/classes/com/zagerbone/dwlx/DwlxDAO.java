package com.zagerbone.dwlx;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;


public interface DwlxDAO
{

	public abstract String createDwlx(Dwlx dwlx)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalDwlx(Dwlx dwlx)
		throws BussinessProcessException; */

	public abstract Query findDwlxList(Query query)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalDwlxList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findDwlxBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameDwlxList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameDwlxList(Query query)
		throws BussinessProcessException; */

	public abstract Dwlx findDwlxById(String s)
		throws BussinessProcessException;

	public abstract String modifyDwlx(Dwlx dwlx)
		throws BussinessProcessException;

	public abstract String deleteDwlx(String s)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewDwlx(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
}
