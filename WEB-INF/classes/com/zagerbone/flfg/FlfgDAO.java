package com.zagerbone.flfg;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface FlfgDAO
{

	public abstract String createFlfg(Flfg flfg,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalFlfg(Flfg flfg)
		throws BussinessProcessException; */

	public abstract Query findFlfgList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalFlfgList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findFlfgBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameFlfgList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameFlfgList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Flfg findFlfgById(String s)
		throws BussinessProcessException; */

	public abstract String modifyFlfg(Flfg flfg,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteFlfg(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewFlfg(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
