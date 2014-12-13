package com.zagerbone.emp;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface EmpDAO
{

	public abstract String createEmp(Emp emp,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalEmp(Emp emp)
		throws BussinessProcessException; */

	public abstract Query findEmpList(Query query,HttpServletRequest request)
		throws BussinessProcessException;
	public abstract Query findMyProfile(Query query,HttpServletRequest request)
		throws BussinessProcessException;

	public String modifyEmpSel(Emp emp,HttpServletRequest request)
		throws BussinessProcessException;
		
	public String empChangePsw(Emp emp,HttpServletRequest request)
		throws BussinessProcessException;
/* 	public abstract Query findPersonalEmpList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findEmpBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameEmpList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameEmpList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Emp findEmpById(String s)
		throws BussinessProcessException; */

	public abstract String modifyEmp(Emp emp,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteEmp(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewEmp(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
