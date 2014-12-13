package com.zagerbone.pritab;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;


public interface PritabDAO
{

	public abstract String createPritab(Pritab pritab)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalPritab(Pritab pritab)
		throws BussinessProcessException; */

	public abstract Query findPritabList(Query query)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalPritabList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findPritabBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnamePritabList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnamePritabList(Query query)
		throws BussinessProcessException; */

	public abstract Pritab findPritabById(String s)
		throws BussinessProcessException;

	public abstract String modifyPritab(Pritab pritab)
		throws BussinessProcessException;

	public abstract String modifyPritab2(Pritab pritab)
		throws BussinessProcessException;

	public abstract String deletePritab(String s)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewPritab(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract CachedRowSet getAllPri(String s)
		throws BussinessProcessException;
}
