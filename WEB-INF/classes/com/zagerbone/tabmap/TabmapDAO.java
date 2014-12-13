package com.zagerbone.tabmap;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.DBTrans;


public interface TabmapDAO
{

	public abstract String createTabmap(Tabmap tabmap)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalTabmap(Tabmap tabmap)
		throws BussinessProcessException; */

	public abstract Query findTabmapList(Query query)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalTabmapList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findTabmapBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameTabmapList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameTabmapList(Query query)
		throws BussinessProcessException; */

	public abstract Tabmap findTabmapById(String s)
		throws BussinessProcessException;

	public abstract String modifyTabmap(Tabmap tabmap)
		throws BussinessProcessException;

	public abstract String deleteTabmap(String s)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewTabmap(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract String setcascade(DBTrans dbtrans,String fktab)
		throws BussinessProcessException;
		
	public abstract void remcascade(DBTrans dbtrans,String fkta,String iuik)
		throws BussinessProcessException;
}
