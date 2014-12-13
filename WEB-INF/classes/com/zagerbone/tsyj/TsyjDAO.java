package com.zagerbone.tsyj;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface TsyjDAO
{

	public abstract String createTsyj(Tsyj tsyj,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalTsyj(Tsyj tsyj)
		throws BussinessProcessException; */

	public abstract Query findTsyjList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalTsyjList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findTsyjBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameTsyjList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameTsyjList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Tsyj findTsyjById(String s)
		throws BussinessProcessException; */

	public abstract String modifyTsyj(Tsyj tsyj,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteTsyj(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewTsyj(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
