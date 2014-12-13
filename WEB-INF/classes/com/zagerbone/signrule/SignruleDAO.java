package com.zagerbone.signrule;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface SignruleDAO
{

	public abstract String createSignrule(Signrule signrule,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalSignrule(Signrule signrule)
		throws BussinessProcessException; */

	public abstract Query findSignruleList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalSignruleList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findSignruleBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameSignruleList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameSignruleList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Signrule findSignruleById(String s)
		throws BussinessProcessException; */

	public abstract String modifySignrule(Signrule signrule,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteSignrule(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewSignrule(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
