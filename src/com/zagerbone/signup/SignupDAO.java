package com.zagerbone.signup;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;


public interface SignupDAO
{

	public abstract String createSignup(Signup signup)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalSignup(Signup signup)
		throws BussinessProcessException; */

	public abstract Query findSignupList(Query query)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalSignupList(Query query)
		throws BussinessProcessException; */

/* 	public abstract CachedRowSet findSignupBytp_carname(String s)
		throws BussinessProcessException; */

/* 	public abstract Query findtp_carnameSignupList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Query findCnameSignupList(Query query)
		throws BussinessProcessException; */

	public abstract Signup findSignupById(String s)
		throws BussinessProcessException;

	public abstract String modifySignup(Signup signup)
		throws BussinessProcessException;

	public abstract String deleteSignup(String s)
		throws BussinessProcessException;

/* 	public abstract boolean hasNewSignup(String s)
		throws BussinessProcessException; */
	public abstract CachedRowSet getAllCol()
		throws BussinessProcessException;
}
