package com.zagerbone.jdjh;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface JdjhDAO
{

	public abstract String createJdjh(Jdjh jdjh,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalJdjh(Jdjh jdjh)
		throws BussinessProcessException; */

	public abstract Query findJdjhList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalJdjhList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findJdjhBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameJdjhList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameJdjhList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Jdjh findJdjhById(String s)
		throws BussinessProcessException; */

	public abstract String modifyJdjh(Jdjh jdjh,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteJdjh(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewJdjh(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
