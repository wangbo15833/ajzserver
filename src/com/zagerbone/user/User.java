package com.zagerbone.user;

import java.util.HashMap;
import java.util.Hashtable;

public class User
{

	private String userid;
	private HashMap privlist;
	private Hashtable rolelist;
	private HashMap extProperties;

	public User()
	{
		userid = "";
		privlist = new HashMap(2);
		rolelist = new Hashtable(5);
		extProperties = new HashMap(1);
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String s)
	{
		userid = s;
	}

	public void setProp(String s, String s1)
	{
		extProperties.put(s, s1);
	}

	public String getProp(String s)
	{
		if (extProperties.containsKey(s))
			return (String)extProperties.get(s);
		else
			return null;
	}

	public HashMap getPrivlist()
	{
		return privlist;
	}

	public void setPrivlist(HashMap hashmap)
	{
		privlist = hashmap;
	}

	public boolean checkPrivilege(String s)
	{
		if (privlist == null)
			return false;
		else
			return privlist.containsKey(s);
	}

	public static boolean checkInput(String s)
	{
		String s1 = "abcdefghijklmnopqrstuvwxyz0123456789_.";
		String s2 = "";
		for (int i = 0; i < s.length(); i++)
		{
			String s3 = s.substring(i, i + 1);
			if (s1.indexOf(s3) == -1)
				return false;
		}

		return true;
	}

	public Hashtable getRolelist()
	{
		return rolelist;
	}

	public void setRolelist(Hashtable hashtable)
	{
		rolelist = hashtable;
	}
}
