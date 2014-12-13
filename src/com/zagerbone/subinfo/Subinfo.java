package com.zagerbone.subinfo;

import java.util.HashMap;

public class Subinfo
{

	private String id;
	private HashMap extProperties;

	public Subinfo()
	{
		extProperties = new HashMap(1);
	}

	public void setid(String s)
	{
		id = s;
	}

	public String getid()
	{
		return id;
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
	public void setHashMap(HashMap hp)
	{
		this.extProperties = hp;
	}
	public HashMap getHashMap()
	{
		return this.extProperties;
	}
}
