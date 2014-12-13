package com.zagerbone.xmzg;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface XmzgDAO
{

	public abstract String createXmzg(Xmzg xmzg,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalXmzg(Xmzg xmzg)
		throws BussinessProcessException; */

	public abstract Query findXmzgList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalXmzgList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findXmzgBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameXmzgList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameXmzgList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Xmzg findXmzgById(String s)
		throws BussinessProcessException; */

	public abstract String modifyXmzg(Xmzg xmzg,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteXmzg(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewXmzg(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
