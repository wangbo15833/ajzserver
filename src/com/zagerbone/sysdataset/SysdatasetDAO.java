package com.zagerbone.sysdataset;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;


public interface SysdatasetDAO
{

	public abstract String createSysdataset(Sysdataset sysdataset)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalSysdataset(Sysdataset sysdataset)
		throws BussinessProcessException; */

	public abstract Query findSysdatasetList(Query query)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalSysdatasetList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findSysdatasetBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameSysdatasetList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameSysdatasetList(Query query)
		throws BussinessProcessException; */

	public abstract Sysdataset findSysdatasetById(String s)
		throws BussinessProcessException;

	public abstract String modifySysdataset(Sysdataset sysdataset)
		throws BussinessProcessException;

	public abstract String deleteSysdataset(String s)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewSysdataset(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
}
