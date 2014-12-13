// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   QueryCondition.java

package com.shenghao.arch.util;


public class QueryCondition
{

	private int total;
	private int rowsperpage;
	private int page;
	private String pagetype;
	private String queryString;
	private String apanage;
	private String sql;
	private Object conditionBody;

	public QueryCondition()
	{
		total = 0;
		rowsperpage = 20;
		page = 1;
		pagetype = "2";
		queryString = "";
		apanage = "";
		sql = "";
	}

	public int getPage()
	{
		return page;
	}

	public String getQueryString()
	{
		return queryString;
	}

	public int getRowsperpage()
	{
		return rowsperpage;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public void setRowsperpage(int rowsperpage)
	{
		this.rowsperpage = rowsperpage;
	}

	public void setQueryString(String queryString)
	{
		this.queryString = queryString;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public void setConditionBody(Object conditionBody)
	{
		this.conditionBody = conditionBody;
	}

	public int getPrevious()
	{
		return page - 1 > 0 ? page - 1 : 1;
	}

	public int getNext()
	{
		return page + 1 > getMax() ? page : page + 1;
	}

	public int getMax()
	{
		int mPage = total / rowsperpage;
		if (mPage * rowsperpage < total)
			return mPage + 1;
		else
			return mPage;
	}

	public String getXPage(int xpage)
	{
		int xxpage = xpage;
		if (xxpage > getMax())
			xxpage = getMax();
		if (xxpage < 1)
			xxpage = 1;
		return queryString + "&page=" + xpage + "&total=" + total;
	}

	public int getMin()
	{
		return 1;
	}

	public String getMinPage()
	{
		return queryString + "&page=" + getMin() + "&total=" + total;
	}

	public String getPreviousPage()
	{
		return queryString + "&page=" + getPrevious() + "&total=" + total;
	}

	public String getNextPage()
	{
		return queryString + "&page=" + getNext() + "&total=" + total;
	}

	public String getMaxPage()
	{
		return queryString + "&page=" + getMax() + "&total=" + total;
	}

	public Object getConditionBody()
	{
		return conditionBody;
	}

	public String getApanage()
	{
		return apanage;
	}

	public void setApanage(String apanage)
	{
		this.apanage = apanage;
	}

	public String getSql()
	{
		return sql;
	}

	public void setSql(String sql)
	{
		this.sql = sql;
	}

	public String getPagetype()
	{
		return pagetype;
	}

	public void setPagetype(String pagetype)
	{
		this.pagetype = pagetype;
	}
}
