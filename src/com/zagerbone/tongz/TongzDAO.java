package com.zagerbone.tongz;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface TongzDAO
{

	public abstract String createTongz(Tongz tongz,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalTongz(Tongz tongz)
		throws BussinessProcessException; */

	public abstract Query findTongzList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalTongzList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findTongzBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameTongzList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameTongzList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Tongz findTongzById(String s)
		throws BussinessProcessException; */

	public abstract String modifyTongz(Tongz tongz,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteTongz(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewTongz(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
