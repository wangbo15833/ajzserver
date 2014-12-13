package com.zagerbone.jcbz;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface JcbzDAO
{

	public abstract String createJcbz(Jcbz jcbz,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalJcbz(Jcbz jcbz)
		throws BussinessProcessException; */

	public abstract Query findJcbzList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalJcbzList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findJcbzBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameJcbzList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameJcbzList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Jcbz findJcbzById(String s)
		throws BussinessProcessException; */

	public abstract String modifyJcbz(Jcbz jcbz,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteJcbz(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewJcbz(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
