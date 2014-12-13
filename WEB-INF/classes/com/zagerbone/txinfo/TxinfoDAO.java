package com.zagerbone.txinfo;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface TxinfoDAO
{

	public abstract String createTxinfo(Txinfo txinfo,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalTxinfo(Txinfo txinfo)
		throws BussinessProcessException; */

	public abstract Query findTxinfoList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalTxinfoList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findTxinfoBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameTxinfoList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameTxinfoList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract Txinfo findTxinfoById(String s)
		throws BussinessProcessException; */

	public abstract String modifyTxinfo(Txinfo txinfo,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteTxinfo(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewTxinfo(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
}
