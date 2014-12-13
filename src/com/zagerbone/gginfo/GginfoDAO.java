package com.zagerbone.gginfo;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface GginfoDAO
{

	public abstract String createGginfo(Gginfo gginfo,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalGginfo(Gginfo gginfo)
		throws BussinessProcessException; */

	public abstract Query findGginfoList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalGginfoList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findGginfoBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameGginfoList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameGginfoList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Gginfo findGginfoById(String s)
		throws BussinessProcessException; */

	public abstract String modifyGginfo(Gginfo gginfo,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteGginfo(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewGginfo(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
