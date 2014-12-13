package com.zagerbone.qzba;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface QzbaDAO
{

	public abstract String createQzba(Qzba qzba,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalQzba(Qzba qzba)
		throws BussinessProcessException; */

	public abstract Query findQzbaList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalQzbaList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findQzbaBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameQzbaList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameQzbaList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Qzba findQzbaById(String s)
		throws BussinessProcessException; */

	public abstract String modifyQzba(Qzba qzba,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteQzba(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewQzba(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
