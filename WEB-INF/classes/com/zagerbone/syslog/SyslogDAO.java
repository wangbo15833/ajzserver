package com.zagerbone.syslog;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import com.zagerbone.util.DBTrans;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;


public interface SyslogDAO
{

	public abstract String createSyslog(Syslog syslog)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalSyslog(Syslog syslog)
		throws BussinessProcessException; */

	public abstract Query findSyslogList(Query query)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalSyslogList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findSyslogBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameSyslogList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameSyslogList(Query query)
		throws BussinessProcessException; */

	public abstract Syslog findSyslogById(String s)
		throws BussinessProcessException;

	public abstract String modifySyslog(Syslog syslog)
		throws BussinessProcessException;

	public abstract String deleteSyslog(String s)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewSyslog(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract void logact(HashMap hp_op,HttpServletRequest request,DBTrans dbtrans)
		throws BussinessProcessException;
		
	public abstract  void logact2(HashMap hp_op,DBTrans dbtrans)
		throws BussinessProcessException;
		
}
