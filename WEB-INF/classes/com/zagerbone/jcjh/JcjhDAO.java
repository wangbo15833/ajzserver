package com.zagerbone.jcjh;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;


public interface JcjhDAO
{

	public abstract String createJcjh(Jcjh jcjh)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalJcjh(Jcjh jcjh)
		throws BussinessProcessException; */

	public abstract Query findJcjhList(Query query)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalJcjhList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findJcjhBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameJcjhList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameJcjhList(Query query)
		throws BussinessProcessException; */

	public abstract Jcjh findJcjhById(String s)
		throws BussinessProcessException;

	public abstract String modifyJcjh(Jcjh jcjh)
		throws BussinessProcessException;

	public abstract String deleteJcjh(String s)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewJcjh(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
}
