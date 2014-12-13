package com.zagerbone.thry;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface ThryDAO
{

	public abstract String createThry(Thry thry,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalThry(Thry thry)
		throws BussinessProcessException; */

	public abstract Query findThryList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalThryList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findThryBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameThryList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameThryList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Thry findThryById(String s)
		throws BussinessProcessException; */

	public abstract String modifyThry(Thry thry,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteThry(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewThry(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
