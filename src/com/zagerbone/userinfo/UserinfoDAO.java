package com.zagerbone.userinfo;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface UserinfoDAO
{

	public abstract String createUserinfo(Userinfo userinfo,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalUserinfo(Userinfo userinfo)
		throws BussinessProcessException; */

	public abstract Query findUserinfoList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalUserinfoList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findUserinfoBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameUserinfoList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameUserinfoList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Userinfo findUserinfoById(String s)
		throws BussinessProcessException; */

	public abstract String modifyUserinfo(Userinfo userinfo,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteUserinfo(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewUserinfo(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;

	public abstract void checkUserNumber(HttpServletRequest request)
		throws BussinessProcessException;
}
