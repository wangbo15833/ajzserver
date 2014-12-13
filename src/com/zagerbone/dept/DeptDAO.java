package com.zagerbone.dept;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface DeptDAO
{

	public abstract String createDept(Dept dept,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalDept(Dept dept)
		throws BussinessProcessException; */

	public abstract Query findDeptList(Query query,HttpServletRequest request)
		throws BussinessProcessException;
		
	public abstract String findDeptByThisId(String deptid,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalDeptList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findDeptBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameDeptList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameDeptList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Dept findDeptById(String s)
		throws BussinessProcessException; */

	public abstract String modifyDept(Dept dept,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteDept(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewDept(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
