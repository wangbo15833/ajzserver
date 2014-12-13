package com.zagerbone.subinfo;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface SubinfoDAO
{

	public abstract String createSubinfo(Subinfo subinfo,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalSubinfo(Subinfo subinfo)
		throws BussinessProcessException; */

	public abstract Query findSubinfoList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalSubinfoList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findSubinfoBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameSubinfoList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameSubinfoList(Query query)
		throws BussinessProcessException; */

	public abstract String findSubinfoById(String id,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String modifySubinfo(Subinfo subinfo,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteSubinfo(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewSubinfo(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
