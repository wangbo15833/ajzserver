// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UserDAO.java

package com.zagerbone.user;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import java.util.ArrayList;
import java.util.HashMap;

// Referenced classes of package com.zagerbone.user:
//			User

public interface UserDAO
{

	public abstract String registUser(User user)
		throws BussinessProcessException;

	public abstract Query findUserList(Query query)
		throws BussinessProcessException;

	public abstract String confirmUser(User user)
		throws BussinessProcessException;

	public abstract String forbidUser(User user)
		throws BussinessProcessException;

	public abstract String loginUser(User user)
		throws BussinessProcessException;

	public abstract User findUserById(String s)
		throws BussinessProcessException;

	public abstract String deleteUser(String s)
		throws BussinessProcessException;

	public abstract String createUserBasic(User user)
		throws BussinessProcessException;

	public abstract Query findUserBasicManageList(Query query)
		throws BussinessProcessException;

	public abstract Query findUserBasicList(Query query)
		throws BussinessProcessException;

	public abstract User findUserBasicById(String s)
		throws BussinessProcessException;

	public abstract String modifyUserBasic(User user)
		throws BussinessProcessException;

	public abstract String confirmUserBasic(User user)
		throws BussinessProcessException;

	public abstract String addPrivilege(User user)
		throws BussinessProcessException;

	public abstract String findPrivilege(String s)
		throws BussinessProcessException;

	public abstract String modifyPassword(User user)
		throws BussinessProcessException;

	public abstract String modifyUserToFriend(String s, String s1)
		throws BussinessProcessException;

	public abstract String createUserBasicExcel()
		throws BussinessProcessException;

	public abstract ArrayList find_functionids(HashMap hashmap)
		throws BussinessProcessException;

	public abstract User findUserById(String s, HashMap hashmap)
		throws BussinessProcessException;
}
