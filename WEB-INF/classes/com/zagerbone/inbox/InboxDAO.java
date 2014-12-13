package com.zagerbone.inbox;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;



public interface InboxDAO
{

	public abstract String createInbox(Inbox inbox,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalInbox(Inbox inbox)
		throws BussinessProcessException; */

	public abstract Query findInboxList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalInboxList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findInboxBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameInboxList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameInboxList(Query query)
		throws BussinessProcessException; */

	public abstract CachedRowSet findInboxById(String s,HttpServletRequest request)
		throws BussinessProcessException; 

	public abstract String modifyInbox(Inbox inbox,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteInbox(String s,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewInbox(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
		
	public abstract HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException;
		
	public abstract void readGginfo(String id,HttpServletRequest request)
		throws BussinessProcessException;
}
