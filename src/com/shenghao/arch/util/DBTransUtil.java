package com.shenghao.arch.util;

import com.shenghao.arch.exception.DBException;
import java.sql.*;
import java.util.Vector;
import javax.naming.*;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;


public class DBTransUtil
{

	private String JNDI;
	private DataSource ds;
	private Context ctx;
	private Connection con;
	private Statement stmt;
	private String errorMessage;
	private Vector sqlVector;
	private static Logger log;

	public DBTransUtil()
	{
		JNDI = SysTrace.JNDI;
		ds = null;
		ctx = null;
		con = null;
		stmt = null;
		errorMessage = "";
		sqlVector = new Vector(5);
	}

	public DBTransUtil(String s)
	{
		JNDI = SysTrace.JNDI;
		ds = null;
		ctx = null;
		con = null;
		stmt = null;
		errorMessage = "";
		sqlVector = new Vector(5);
		JNDI = s;
	}

	public void addSql(String s) throws DBException
	{
		sqlVector.add(s);
	}

	public boolean executeSql()
		throws DBException
	{
		Object obj = null;
		int ai[] = {
			0
		};
		try
		{
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(JNDI);
			con = ds.getConnection();
			stmt = con.createStatement();
			for (int i = 0; i < sqlVector.size(); i++)
				stmt.addBatch(sqlVector.get(i).toString());

			int ai1[] = stmt.executeBatch();
		}
		catch (SQLException sqlexception)
		{
			log.error((new StringBuilder()).append("executeSql出现错误：").append(sqlexception.getMessage()).toString());
			throw new DBException(sqlexception.getMessage().replace('\n', ' '));
		}
		catch (NamingException namingexception)
		{
			log.error((new StringBuilder()).append("executeSql出现错误1：").append(namingexception.getMessage()).toString());
			throw new DBException(namingexception.getMessage());
		}
		finally
		{
			
		}
		try
		{
			sqlVector.clear();
		}
		catch (Exception exception) { }
		try
		{
			stmt.close();
		}
		catch (Exception exception1) { }
		try
		{
			con.close();
		}
		catch (Exception exception2) { }
		try
		{
			sqlVector.clear();
		}
		catch (Exception exception3) { }
		try
		{
			stmt.close();
		}
		catch (Exception exception4) { }
		try
		{
			con.close();
		}
		catch (Exception exception5) { }
		return true;
	}

	public CachedRowSet getResultBySelect(String s)
		throws DBException
	{
		CachedRowSet cachedrowset = null;
		ResultSet resultset = null;
		try
		{
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(JNDI);
			cachedrowset = new CachedRowSet();
			con = ds.getConnection();
			stmt = con.createStatement();
			resultset = stmt.executeQuery(s);
			cachedrowset.populate(resultset);
		}
		catch (SQLException sqlexception)
		{
			log.info((new StringBuilder()).append("getResultBySelect : ").append(sqlexception.getMessage()).toString());
			throw new DBException(sqlexception.getMessage());
		}
		catch (NamingException namingexception)
		{
			namingexception.printStackTrace();
			log.info((new StringBuilder()).append("naming ex : ").append(namingexception.getMessage()).toString());
			throw new DBException(namingexception.getMessage());
		}
		finally
		{
			
		}
		try
		{
			if (resultset != null)
				resultset.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		}
		catch (Exception exception) { }
		return cachedrowset;
	}

	public CachedRowSet getResultBySelect(String s, QueryCondition querycondition)
		throws DBException
	{
		CachedRowSet cachedrowset = null;
		ResultSet resultset = null;
		if (querycondition.getTotal() == 0)
		{
			int i = getRecNumBySelect(s);
			querycondition.setTotal(i);
		}
		if (querycondition.getPage() <= 0)
			querycondition.setPage(1);
		querycondition.setSql(s);
		int j = querycondition.getRowsperpage() * querycondition.getPage();
		int k = querycondition.getRowsperpage() * (querycondition.getPage() - 1);
		if (j > querycondition.getTotal())
			j = querycondition.getTotal();
		if (k < 0)
			k = 0;
		StringBuffer stringbuffer = new StringBuffer("");
		if (querycondition.getPagetype().equals("1"))
		{
			stringbuffer.append("select * from (select row_number() over(order by ");
			if (s.indexOf("order") > 0)
				stringbuffer.append(s.substring(s.indexOf("order") + 8));
			else
				stringbuffer.append("rowid");
			stringbuffer.append(") countnum,t.* from (");
			if (s.indexOf("order") > 0)
				stringbuffer.append(s.substring(0, s.indexOf("order")));
			else
				stringbuffer.append(s);
			stringbuffer.append(") t) where countnum>").append(k);
			stringbuffer.append(" and countnum <=").append(j);
		} else
		if (querycondition.getPagetype().equals("2"))
		{
			stringbuffer.append("select * from ( select rownum countnum,t.* from (");
			stringbuffer.append(s).append(") t where rownum<='").append(j);
			stringbuffer.append("') where countnum>='").append(k).append("'");
		} else
		if (querycondition.getPagetype().equals("3"))
		{
			stringbuffer.append("select rownum,a.* from (");
			stringbuffer.append(s);
			stringbuffer.append(") a,dual b where rownum <= ");
			stringbuffer.append(j);
			stringbuffer.append(" minus select rownum,a.* from (");
			stringbuffer.append(s);
			stringbuffer.append(") a,dual b where rownum <= ");
			stringbuffer.append(k);
		}
		try
		{
			cachedrowset = getResultBySelect(stringbuffer.toString());
		}
		catch (Exception exception)
		{
			log.error((new StringBuilder()).append("getResultBySelect 产生错误，SQL：").append(s).toString());
			log.error(exception.getMessage());
			throw new DBException(exception.getMessage());
		}
		finally
		{
			
		}
		try
		{
			if (resultset != null)
				resultset.close();
		}
		catch (Exception exception1) { }
		try
		{
			if (stmt != null)
				stmt.close();
		}
		catch (Exception exception2) { }
		try
		{
			if (con != null)
				con.close();
		}
		catch (Exception exception3) { }
		return cachedrowset;
	}

	public String getSequence(String s)
		throws DBException
	{
		Object obj = null;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append((new StringBuilder()).append("Select ").append(s).append(".nextval from dual").toString());
		String s1 = getColumnValue(stringbuffer.toString());
		return s1;
	}

	public String getColumnValue(String s)
		throws DBException
	{
		String s1 = "";
		CachedRowSet cachedrowset = getResultBySelect(s);
		try
		{
			if (cachedrowset.next())
				s1 = cachedrowset.getString(1) == null ? "" : cachedrowset.getString(1);
		}
		catch (SQLException sqlexception)
		{
			throw new DBException(sqlexception.getMessage().replace('\n', ' '));
		}
		return s1;
	}

	public int getRecNumBySelect(String s)
		throws DBException
	{
		int i;
		CachedRowSet cachedrowset;
		Exception exception1;
		i = 0;
		cachedrowset = null;
		StringBuffer stringbuffer = new StringBuffer("");
		try
		{
			stringbuffer.append("select count(1) from ");
			stringbuffer.append(s.substring(s.indexOf("from") + 4));
			cachedrowset = getResultBySelect(stringbuffer.toString());
			if (cachedrowset.next())
				i = cachedrowset.getInt(1);
			else
				i = 0;
		}
		catch (Exception exception)
		{
			log.error(exception.getMessage());
			throw new DBException(exception.getMessage().replace('\n', ' '));
		}
		finally {
			try
			{
				cachedrowset.close();
			}
			catch (SQLException sqlexception) { }
			
		}
		
		return i;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public static Connection getConnection()
	{
		Connection connection = null;
		try
		{
			InitialContext initialcontext = new InitialContext();
			DataSource datasource = (DataSource)initialcontext.lookup(SysTrace.JNDI);
			connection = datasource.getConnection();
		}
		catch (Exception exception)
		{
			log.error((new StringBuilder()).append("getConnection出现错误：").append(exception.getMessage()).toString());
			try
			{
				connection.close();
			}
			catch (Exception exception1) { }
		}
		return connection;
	}

	public Connection getConnection(String s)
	{
		try
		{
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup(s);
			con = ds.getConnection();
		}
		catch (Exception exception)
		{
			log.error((new StringBuilder()).append("getConnection出现错误：").append(exception.getMessage()).toString());
			try
			{
				con.close();
			}
			catch (Exception exception1) { }
		}
		return con;
	}

	static 
	{
		log = Logger.getLogger(SysTrace.DBLOGGER);
	}
}
