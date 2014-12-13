package com.zagerbone.jdjl;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface JdjlDAO
{

	public abstract String createJdjl(Jdjl jdjl,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalJdjl(Jdjl jdjl)
		throws BussinessProcessException; */

	public abstract Query findJdjlList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalJdjlList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findJdjlBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameJdjlList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameJdjlList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Jdjl findJdjlById(String s)
		throws BussinessProcessException; */

	public abstract String modifyJdjl(Jdjl jdjl,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteJdjl(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewJdjl(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
