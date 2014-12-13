package com.zagerbone.util;

import com.zagerbone.dbPool.ConnectionPool;
import com.zagerbone.exception.DBException;
import com.zagerbone.util.data.SysConstant;
import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.util:
//			Query, SysTrace, Tools

public class DBTrans
{

	private static Logger log;
	private String jndiName;
	private String JNDI;
	private String dsType;
	private String dsName;
	private boolean display_jndi;
	private String actionName;
	private String errorMsg;
	private DataSource ds;
	private Context ctx;
	private Connection con;
	private Statement stmt;
	private Vector sqlVector;
	private PreparedStatement pstmt;

	public static void info(String s)
	{
		StringBuffer stringbuffer = new StringBuffer("In Class=DBTrans:");
		stringbuffer.append(s);
		log.info(stringbuffer.toString());
	}

	public Connection getDBTransConnection()
		throws Exception
	{
		return con;
	}

	public CachedRowSet findBySQL_prepared(String s, String s1, String s2, HashMap hashmap)
	{
		String s3 = "";
		CachedRowSet cachedrowset = null;
		ResultSet resultset = null;
		Object obj = null;
		ArrayList arraylist = new ArrayList();
		Set set = hashmap.entrySet();
		String s5;
		for (Iterator iterator = set.iterator(); iterator.hasNext(); arraylist.add(s5))
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			String s4 = (String)entry.getKey();
			s5 = (String)entry.getValue();
			s3 = (new StringBuilder()).append(s3).append("and ").append(s4).append("=? ").toString();
		}

		s = (new StringBuilder()).append(s).append(s3).append(s2).toString();
		try
		{
			pstmt = con.prepareStatement(s);
			for (int i = 0; i < arraylist.size(); i++)
				pstmt.setString(i + 1, (String)arraylist.get(i));

			resultset = pstmt.executeQuery();
			cachedrowset = new CachedRowSet();
			cachedrowset.populate(resultset);
			if (resultset != null)
				resultset.close();

			if (pstmt != null)
				pstmt.close();
		}
		catch (Exception exception2)
		{
			exception2.printStackTrace();
		}
		return cachedrowset;
	}

	public CachedRowSet findBySQL_prepared(Query query, String s, String s1, String s2, HashMap hashmap)
		throws DBException
	{
		boolean flag;
		CachedRowSet cachedrowset;
		ResultSet resultset;
		ResultSet resultset1;
		Exception exception4;
		flag = false;
		cachedrowset = null;
		resultset = null;
		resultset1 = null;
		Object obj = null;
		int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
		int j = query.getRowsPerPage() > query.getTotalNum() - i ? query.getTotalNum() - i : query.getRowsPerPage();
		StringBuffer stringbuffer = new StringBuffer("");
		try
		{
			String s3 = (String)SysTrace.hashMap.get(jndiName);
			if (con == null)
			{
				flag = true;
				ctx = new InitialContext();
				if (Tools.nvl(s3).equals(""))
					s3 = JNDI;
				if (s3.equals("connectionPool_oracle"))
				{
					StringBuffer stringbuffer1 = (new StringBuffer("select ")).append(s.substring(s.indexOf("select") + 6)).append("where rowNUM<").append(j);
					ConnectionPool connectionpool = null;
					connectionpool = (ConnectionPool)ctx.lookup(s3);
					con = connectionpool.getConnection();
				} else
				{
					StringBuffer stringbuffer2 = (new StringBuffer("select top ")).append(j).append(s.substring(s.indexOf("select") + 6));
					ds = (DataSource)ctx.lookup(s3);
					con = ds.getConnection();
				}
			}
			StringBuffer stringbuffer3;
			if (s3.equals("connectionPool_oracle"))
			{
				stringbuffer3 = (new StringBuffer("select ")).append(s.substring(s.indexOf("select") + 6)).append("and rowNUM<").append(j + 1);
				log.info((new StringBuilder()).append("tempSql========ORACAL======================:").append(stringbuffer3.toString()).toString());
			} else
			{
				stringbuffer3 = (new StringBuffer("select top ")).append(j).append(s.substring(s.indexOf("select") + 6));
				log.info((new StringBuilder()).append("tempSql=").append(stringbuffer3.toString()).toString());
			}
			cachedrowset = new CachedRowSet();
			pstmt = con.prepareStatement(stringbuffer3.toString());
			resultset1 = pstmt.executeQuery();
			cachedrowset.populate(resultset1);
			try
			{
				if (resultset1 != null)
					resultset1.close();
				if (pstmt != null)
					pstmt.close();
			}
			catch (Exception exception3)
			{
				exception3.printStackTrace();
			}
			if (cachedrowset.size() > 0)
			{
				ArrayList arraylist = new ArrayList();
				s1 = "";
				Set set = hashmap.entrySet();
				String s5;
				for (Iterator iterator = set.iterator(); iterator.hasNext(); log.info((new StringBuilder()).append("strval=").append(s5).toString()))
				{
					java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
					String s4 = (String)entry.getKey();
					s5 = (String)entry.getValue();
					s1 = (new StringBuilder()).append(s1).append("and ").append(s4).append("=? ").toString();
					arraylist.add(s5);
				}

				log.info((new StringBuilder()).append("strset=").append(s1).toString());
				s = (new StringBuilder()).append(s).append(s1).append(s2).toString();
				log.info((new StringBuilder()).append("strSql=").append(s).toString());
				pstmt = con.prepareStatement(s);
				for (int k = 0; k < arraylist.size(); k++)
					pstmt.setString(k + 1, (String)arraylist.get(k));

				resultset = pstmt.executeQuery();
				for (int l = 0; l < i; l++)
					resultset.next();

				log.info((new StringBuilder()).append("minRow=").append(i).toString());
				log.info((new StringBuilder()).append("crs.size()=").append(cachedrowset.size()).toString());
				log.info((new StringBuilder()).append("addRow=").append(j).toString());
				boolean flag1 = false;
				for (int i1 = 0; i1 < query.getRowsPerPage() && i1 < j; i1++)
				{
					log.info((new StringBuilder()).append("j=").append(i1).toString());
					cachedrowset.next();
					ResultSetMetaData resultsetmetadata = resultset.getMetaData();
					int j1 = resultsetmetadata.getColumnCount();
					log.info((new StringBuilder()).append("rs=").append(resultset).toString());
					resultset.next();
					for (int k1 = 1; k1 <= j1; k1++)
						cachedrowset.updateString(resultsetmetadata.getColumnName(k1).toLowerCase(), Tools.nvl(resultset.getString(k1)));

				}

			}
			cachedrowset.beforeFirst();
		}
		catch (SQLException sqlexception)
		{
			sqlexception.printStackTrace();
			log.info((new StringBuilder()).append("getResultBySelect : ").append(sqlexception.getMessage()).toString());
			throw new DBException(sqlexception.getMessage());
		}
		catch (NamingException namingexception)
		{
			log.info((new StringBuilder()).append("naming ex : ").append(namingexception.getMessage()).toString());
			throw new DBException(namingexception.getMessage());
		}
		finally { }
		try
		{
			if (resultset1 != null)
				resultset1.close();
			if (resultset != null)
				resultset.close();
			if (stmt != null)
				stmt.close();
				if (flag)
				{
					con.close();
					con = null;
				}

			if (resultset1 != null)
				resultset1.close();
			if (resultset != null)
				resultset.close();
			if (stmt != null)
				stmt.close();
				if (flag)
				{
					con.close();
					con = null;
				}
		}
		catch (Exception exception6) {
			exception6.printStackTrace();
		}
		return cachedrowset;
	}

	public int dosql_prepared(String s, String s1, String s2, HashMap hashmap)
	{
		ArrayList arraylist = new ArrayList();
		String s3 = "";
		int i = -1;
		Set set = hashmap.entrySet();
		String s5;
		for (Iterator iterator = set.iterator(); iterator.hasNext(); log.info((new StringBuilder()).append("strval=").append(s5).toString()))
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			String s4 = (String)entry.getKey();
			s5 = (String)entry.getValue();
			s3 = (new StringBuilder()).append(s3).append(",").append(s4).append("=?").toString();
			arraylist.add(s5);
		}

		log.info((new StringBuilder()).append("strset=").append(s3).toString());
		s3 = s3.substring(1);
		s = (new StringBuilder()).append(s).append(s3).append(s2).toString();
		log.info((new StringBuilder()).append("strsql=").append(s).toString());
		try
		{
			pstmt = con.prepareStatement(s);
			for (int j = 0; j < arraylist.size(); j++)
				pstmt.setString(j + 1, (String)arraylist.get(j));

			i = pstmt.executeUpdate();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			i = -1;
		}
		return i;
	}

	public int dosql_prepared(HashMap hashmap, HashMap hashmap1, HashMap hashmap2, String s, String s1)
	{
		HashMap hashmap3 = new HashMap();
		hashmap3.put("update", (new StringBuilder()).append("update ").append(s).append("  ").toString());
		hashmap3.put("delete", (new StringBuilder()).append("delete from ").append(s).append(" ").toString());
		hashmap3.put("insert", (new StringBuilder()).append("insert into ").append(s).append(" ").toString());
		String s2 = "";
		String s3 = " set ";
		String s4 = " where 1=1 ";
		s2 = (String)hashmap3.get(s1);
		ArrayList arraylist = new ArrayList();
		int i = -1;
		Set set = null;
		Object obj = null;
		if (hashmap1 != null && hashmap1.size() > 0)
		{
			set = hashmap1.entrySet();
			String s7;
			for (Iterator iterator = set.iterator(); iterator.hasNext(); log.info((new StringBuilder()).append("strval=").append(s7).toString()))
			{
				java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
				String s5 = (String)entry.getKey();
				s7 = (String)entry.getValue();
				s3 = (new StringBuilder()).append(s3).append(s5).append("=?,").toString();
				arraylist.add(s7);
			}

			log.info((new StringBuilder()).append("strset=").append(s3).toString());
			s3 = s3.substring(0, s3.length() - 1);
		} else
		{
			s3 = "";
		}
		set = hashmap2.entrySet();
		String s8;
		for (Iterator iterator1 = set.iterator(); iterator1.hasNext(); log.info((new StringBuilder()).append(actionName).append("strval=").append(s8).toString()))
		{
			java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator1.next();
			String s6 = (String)entry1.getKey();
			s8 = (String)entry1.getValue();
			s4 = (new StringBuilder()).append(s4).append("and ").append(s6).append("=? ").toString();
			arraylist.add(s8);
		}

		s2 = (new StringBuilder()).append(s2).append(s3).append(s4).toString();
		log.info((new StringBuilder()).append("strsql=").append(s2).toString());
		try
		{
			pstmt = con.prepareStatement(s2);
			for (int j = 0; j < arraylist.size(); j++)
				pstmt.setString(j + 1, (String)arraylist.get(j));

			i = pstmt.executeUpdate();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			i = -1;
		}
		return i;
	}

	public int dosql_prepared_insert(HashMap hashmap, HashMap hashmap1, HashMap hashmap2, String s, String s1)
	{
		HashMap hashmap3 = new HashMap();
		hashmap3.put("update", (new StringBuilder()).append("update ").append(s).append("  ").toString());
		hashmap3.put("delete", (new StringBuilder()).append("delete from ").append(s).append(" ").toString());
		hashmap3.put("insert", (new StringBuilder()).append("insert into ").append(s).append(" (").toString());
		String s2 = "";
		String s3 = " set ";
		String s4 = " where 1=1 ";
		s2 = (String)hashmap3.get(s1);
		s2 = (String)hashmap3.get("insert");
		int i = -1;
		Object obj = null;
		Object obj1 = null;
		ArrayList arraylist = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		StringBuffer stringbuffer1 = new StringBuffer();
		StringBuffer stringbuffer2 = new StringBuffer();
		if (hashmap != null && hashmap.size() > 0)
		{
			Set set = hashmap.entrySet();
			String s6;
			for (Iterator iterator = set.iterator(); iterator.hasNext(); arraylist.add(s6))
			{
				java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
				String s5 = (String)entry.getKey();
				s6 = (String)entry.getValue();
				stringbuffer1.append(",").append(s5);
				stringbuffer2.append(",").append("?");
			}

		}
		stringbuffer.append(s2).append(stringbuffer1.toString().substring(1));
		stringbuffer.append(")values(");
		stringbuffer.append(stringbuffer2.toString().substring(1));
		stringbuffer.append(")");
		log.info(stringbuffer.toString());
		log.info(arraylist);
		try
		{
			pstmt = con.prepareStatement(stringbuffer.toString());
			for (int j = 0; j < arraylist.size(); j++)
			{
				log.info((new StringBuilder()).append(j).append(",").append(arraylist.get(j)).toString());
				pstmt.setString(j + 1, (String)arraylist.get(j));
			}

			i = pstmt.executeUpdate();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			i = -1;
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (Exception exception1)
		{
			exception1.printStackTrace();
		}
		return i;
	}

	public int prepared_insert_batch(ArrayList arraylist, ArrayList arraylist1, String s)
	{
		String s1 = (new StringBuilder()).append("insert into ").append(s).append(" (").toString();
		int i = -1;
		Object obj = null;
		Object obj1 = null;
		ArrayList arraylist2 = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		StringBuffer stringbuffer1 = new StringBuffer();
		StringBuffer stringbuffer2 = new StringBuffer();
		for (int j = 0; j < arraylist.size(); j++)
		{
			String s2 = (String)arraylist.get(j);
			stringbuffer1.append(",").append(s2);
			stringbuffer2.append(",").append("?");
		}

		stringbuffer.append(s1).append(stringbuffer1.toString().substring(1));
		stringbuffer.append(")values(");
		stringbuffer.append(stringbuffer2.toString().substring(1));
		stringbuffer.append(")");
		log.info(stringbuffer.toString());
		try
		{
			pstmt = con.prepareStatement(stringbuffer.toString());
			for (int k = 0; k < arraylist1.size(); k++)
			{
				ArrayList arraylist3 = (ArrayList)arraylist1.get(k);
				for (int l = 0; l < arraylist3.size(); l++)
				{
					log.info((new StringBuilder()).append(l).append(",").append(arraylist3.get(l)).toString());
					pstmt.setString(l + 1, (String)arraylist3.get(l));
				}

				i = pstmt.executeUpdate();
			}

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			i = -1;
		}
		try
		{
			if (pstmt != null)
				pstmt.close();
		}
		catch (Exception exception1)
		{
			exception1.printStackTrace();
		}
		return i;
	}

	public void InitDBTrans(String s)
	{
		if (s.equals(""))
			s = "DB-0";
		jndiName = s;
		dsType = (String)SysTrace.hp_type.get(s);
		dsName = (String)SysTrace.hashMap.get(s);
		try
		{
			ctx = new InitialContext();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	public DBTrans()
	{
		jndiName = null;
		JNDI = SysTrace.JNDI;
		dsType = null;
		dsName = null;
		display_jndi = true;
		actionName = "";
		errorMsg = "";
		ds = null;
		ctx = null;
		con = null;
		stmt = null;
		sqlVector = new Vector(5);
		pstmt = null;
		jndiName = "DB-0";
		InitDBTrans(jndiName);
	}

	public DBTrans(String s)
	{
		jndiName = null;
		JNDI = SysTrace.JNDI;
		dsType = null;
		dsName = null;
		display_jndi = true;
		actionName = "";
		errorMsg = "";
		ds = null;
		ctx = null;
		con = null;
		stmt = null;
		sqlVector = new Vector(5);
		pstmt = null;
		InitDBTrans(s);
	}

	public DBTrans(String s, String s1)
	{
		jndiName = null;
		JNDI = SysTrace.JNDI;
		dsType = null;
		dsName = null;
		display_jndi = true;
		actionName = "";
		errorMsg = "";
		ds = null;
		ctx = null;
		con = null;
		stmt = null;
		sqlVector = new Vector(5);
		pstmt = null;
		actionName = s1;
		InitDBTrans(s);
	}

	public DBTrans(String s, boolean flag)
	{
		jndiName = null;
		JNDI = SysTrace.JNDI;
		dsType = null;
		dsName = null;
		display_jndi = true;
		actionName = "";
		errorMsg = "";
		ds = null;
		ctx = null;
		con = null;
		stmt = null;
		sqlVector = new Vector(5);
		pstmt = null;
		display_jndi = flag;
		InitDBTrans(s);
	}

	public String getJndiName()
	{
		return jndiName;
	}

	public void addSql(String s)
		throws DBException
	{
		sqlVector.add(s);
	}

	public void closeConnection(Connection connection)
	{
		String s = "";
		closeConnection(connection, s);
	}

	public void closeConnection(Connection connection, String s)
	{
		try
		{
			if (dsType.equals("servlet"))
			{
				ConnectionPool connectionpool = getConnectionPool();
				if (connection != null)
					connectionpool.returnConnection(connection);
				if (!actionName.equals("--获取数据字典,") && !actionName.equals("--获取应用字典,") && !actionName.equals("--获取显示字典,"))
					log.info((new StringBuilder()).append(actionName).append(s).append(",关闭连接con=").append(connection).append(",(连接忙数=").append(connectionpool.findNumOfBuyConnection()).append(")").toString());
				connection = null;
			} else
			{
				connection.close();
				connection = null;
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	public void closeConnection_method(Connection connection)
	{
		try
		{
			if (dsType.equals("servlet"))
			{
				ConnectionPool connectionpool = getConnectionPool();
				if (connection != null)
					connectionpool.returnConnection(connection);
				log.info((new StringBuilder()).append("方法中关闭连接con=").append(connection).append(",目前忙连接数findNumOfBuyConnection()=").append(connectionpool.findNumOfBuyConnection()).toString());
				connection = null;
			} else
			{
				connection.close();
				connection = null;
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	public Connection getCon(String s, String s1, boolean flag)
		throws Exception
	{
		String s2 = "";
		return getCon(s, s1, flag, s2);
	}

	public Connection getCon(String s, String s1, boolean flag, String s2)
		throws Exception
	{
		Connection connection = null;
		if (!s1.equals("FTable000.000.001.02"))
			if (!flag);
		if (dsType.equals("servlet"))
		{
			ConnectionPool connectionpool = null;
			connectionpool = (ConnectionPool)ctx.lookup(dsName);
			connection = connectionpool.getConnection();
			if (!s1.equals("--获取数据字典,") && !s1.equals("--获取应用字典,") && !s1.equals("--获取显示字典,"))
				log.info((new StringBuilder()).append(s1).append(s2).append(",获取连接con=").append(connection).append(",(连接总数=").append(connectionpool.getInuseConnections()).append(",忙数=").append(connectionpool.findNumOfBuyConnection()).append(")").toString());
		} else
		{
			DataSource datasource = (DataSource)ctx.lookup(dsName);
			connection = datasource.getConnection();
			if (!s1.equals("FTable000.000.001.02") && flag)
				log.info((new StringBuilder()).append("con:").append(connection).toString());
		}
		return connection;
	}

	public Connection getCon(String s)
		throws Exception
	{
		Connection connection = null;
		if (dsType.equals("servlet"))
		{
			ConnectionPool connectionpool = null;
			connectionpool = (ConnectionPool)ctx.lookup(dsName);
			connection = connectionpool.getConnection();
			log.info((new StringBuilder()).append("方法中分配连接con=").append(connection).append(",目前连接数getInuseConnections()=").append(connectionpool.getInuseConnections()).toString());
		} else
		{
			DataSource datasource = (DataSource)ctx.lookup(dsName);
			connection = datasource.getConnection();
		}
		return connection;
	}

	public Connection beginTransaction()
		throws DBException
	{
		String s = "开始事务";
		int i = 0;
		do
		{
			i++;
			try
			{
				con = getCon(jndiName, actionName, display_jndi, s);
				con.setAutoCommit(false);
				break;
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
				log.error((new StringBuilder()).append("beginTransaction出现错误：").append(exception.getMessage()).toString());
				try
				{
					closeConnection(con);
				}
				catch (Exception exception1) { }
				if (i >= 4)
					throw new DBException(exception.getMessage());
			}
		} while (true);
		return con;
	}

	public void commitTransaction()
	{
		String s = "提交事务";
		try
		{
			if (con != null)
			{
				con.commit();
				con.setAutoCommit(true);
			}
			closeConnection(con, s);
		}
		catch (Exception exception)
		{
			log.error((new StringBuilder()).append("commit出现错误：").append(exception.getMessage()).toString());
			try
			{
				closeConnection(con, s);
			}
			catch (Exception exception1) { }
		}
	}

	public void rollbackTransaction()
	{
		String s = "回滚事务";
		try
		{
			con.rollback();
			con.setAutoCommit(true);
			closeConnection(con, s);
		}
		catch (Exception exception)
		{
			log.error((new StringBuilder()).append("rollback出现错误：").append(exception.getMessage()).toString());
			try
			{
				closeConnection(con, s);
			}
			catch (Exception exception1) { }
		}
	}

	public boolean executeSql(String s)
		throws DBException
	{
		s = "";
		boolean flag = false;
		try
		{
			flag = executeSql();
		}
		catch (DBException dbexception)
		{
			log.error((new StringBuilder()).append("executeSql出现错误：").append(dbexception.getMessage()).toString());
			throw new DBException(dbexception.getMessage().replace('\n', ' '));
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		return flag;
	}

	public boolean executeSql()
		throws DBException
	{
		boolean flag = false;
		/* int ai[] = {
			0
		}; */
		boolean ex = false;
		String exs = null;
		try
		{
			if (con == null)
			{
				flag = true;
				con = getCon(jndiName);
			}
			stmt = con.createStatement();
			for (int i = 0; i < sqlVector.size(); i++)
				stmt.addBatch(sqlVector.get(i).toString());

			int ai1[] = stmt.executeBatch();
			
				sqlVector.clear();
		
				stmt.close();
		
			if (flag)
			{
				closeConnection_method(con);
				con = null;
			}
		}catch (DBException dbexception)
		{
			//log.error((new StringBuilder()).append("executeSql出现错误：").append(dbexception.getMessage()).toString());
			ex = true;
			exs = dbexception.getMessage();
		}catch (Exception exception) {
			ex = true;
			exs = exception.getMessage();
			exception.printStackTrace();
		}
		/* catch (SQLException sqle){
			log.error((new StringBuilder()).append("executeSql出现错误：").append(sqle.getMessage()).toString());
		} */
		
		
		
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
			if (flag)
			{
				closeConnection_method(con);
				con = null;
			}
		}
		catch (Exception exception5) { }
		try
		{
			sqlVector.clear();
		}
		catch (Exception exception7) { }
		try
		{
			stmt.close();
		}
		catch (Exception exception8) { }
		try
		{
			if (flag)
			{
				closeConnection_method(con);
				con = null;
			}
		}
		catch (Exception exception9) { 
		
			exception9.printStackTrace();
		}
		
		if (ex){
			throw new DBException(exs.replace('\n', ' '));
		}
		return true;
	}

	public boolean executeSql_noBatch()
		throws DBException
	{
		boolean flag;
		flag = false;
		int ai[] = {
			0
		};
		try
		{
		if (con == null)
		{
			flag = true;
			con = getCon(jndiName);
		}
		stmt = con.createStatement();
		String s = null;
		for (int i = 0; i < sqlVector.size(); i++)
			s = sqlVector.get(0).toString();

		
		stmt.executeUpdate(s);
		
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
			if (flag)
			{
				closeConnection_method(con);
				con = null;
			}
		}
		catch (Exception exception2) {
			exception2.printStackTrace();
			}
		
		
		log.error((new StringBuilder()).append("executeSql出现错误：").append(new SQLException().getMessage()).toString());		
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
			if (flag)
			{
				closeConnection_method(con);
				con = null;
			}
		}
		catch (Exception exception5) { 
			exception5.printStackTrace();
		}
		try
		{
			sqlVector.clear();
		}
		catch (Exception exception7) { }
		try
		{
			stmt.close();
		}
		catch (Exception exception8) { }
		try
		{
			if (flag)
			{
				closeConnection_method(con);
				con = null;
			}
		}
		catch (Exception exception9) { 
			exception9.printStackTrace();
		}
		return true;
	}

	public CachedRowSet getResultBySelect(String s, String s1)
		throws DBException
	{
		s1 = "";
		CachedRowSet cachedrowset = null;
		try
		{
			cachedrowset = getResultBySelect(s);
		}
		catch (DBException dbexception)
		{
			log.info((new StringBuilder()).append("getResultBySelect : ").append(dbexception.getMessage()).toString());
			throw new DBException(dbexception.getMessage());
		}
		return cachedrowset;
	}

	public CachedRowSet getResultBySelect(String s)
		throws DBException
	{
		boolean flag;
		ResultSet resultset;
		CachedRowSet cachedrowset=null;
		flag = false;
		resultset = null;
		try
		{
		cachedrowset=new CachedRowSet();
		if (con == null)
		{
			flag = true;
			con = getCon(jndiName);
		}
		stmt = con.createStatement();		
		log.info((new StringBuilder()).append("current strSql is=============").append(s).toString());
		resultset = stmt.executeQuery(s);
		cachedrowset.populate(resultset);
		log.info("the crs.size() is :"+cachedrowset.size());
			if (resultset != null)
				resultset.close();
			if (stmt != null)
				stmt.close();
			try
			{
				if (flag)
				{
					closeConnection_method(con);
					con = null;
				}
			}
			catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		catch (SQLException sqle) { 
		log.info((new StringBuilder()).append("getResultBySelect : ").append(sqle.getMessage()).toString());
		}
		catch (NamingException ne) { 
		log.info((new StringBuilder()).append("naming ex : ").append(ne.getMessage()).toString());
		}
		catch (Exception ex) {
				ex.printStackTrace();
		}
		
		try
		{
			if (resultset != null)
				resultset.close();
			if (stmt != null)
				stmt.close();
			if (flag)
			{
				closeConnection_method(con);
				con = null;
			}
			if (resultset != null)
				resultset.close();
			if (stmt != null)
				stmt.close();
			try
			{
				if (flag)
				{
					closeConnection_method(con);
					con = null;
				}
			}
			catch (Exception exception5) { }
		}
		catch (Exception exception6) { 
			exception6.printStackTrace();
		}
		if (SysConstant.Print_getResultBySelect_strSql.equals("1")){
			info((new StringBuilder()).append("method=getResultBySelect (String strSql),直接获取crs,获取sql=").append(s).toString());
		}
			return cachedrowset;

	}

	public CachedRowSet getResultBySelect_pk(String s, String s1, Query query)
		throws DBException
	{
		boolean flag = false;
		Statement statement = null;
		CachedRowSet cachedrowset = null;
		ResultSet resultset = null;
		ResultSet resultset1 = null;
		int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
		int j = query.getRowsPerPage() > query.getTotalNum() - i ? query.getTotalNum() - i : query.getRowsPerPage();
		StringBuffer stringbuffer = (new StringBuffer("select top ")).append(j).append(" * from ");
		stringbuffer.append(s.substring(s.indexOf("from") + 4));
		log.info((new StringBuilder()).append("tempSql:").append(stringbuffer.toString()).toString());
		try
		{
			if (con == null)
			{
				flag = true;
				con = getCon(s1);
			}
			cachedrowset = new CachedRowSet();
			stmt = con.createStatement(1005, 1008);
			statement = con.createStatement(1005, 1008);
			resultset1 = stmt.executeQuery(stringbuffer.toString());
			cachedrowset.populate(resultset1);
			resultset = stmt.executeQuery(s);
			resultset.absolute(i + 1);
			for (int k = 0; k < query.getRowsPerPage() && !resultset.isAfterLast(); k++)
			{
				String s2 = s.substring(6, s.indexOf("from"));
				String as[] = s2.split(",");
				String s3 = "";
				for (int l = 0; l < as.length; l++)
					s3 = (new StringBuilder()).append(s3).append(" and ").append(as[l]).append(" ='").append(resultset.getString(l + 1)).append("' ").toString();

				StringBuffer stringbuffer1 = new StringBuffer("select * from ");
				stringbuffer1.append(s.substring(s.indexOf("from") + 4, s.indexOf("where")));
				stringbuffer1.append(" where 1=1 ").append(s3);
				log.info((new StringBuilder()).append("sql_pk:").append(stringbuffer1.toString()).toString());
				ResultSet resultset2 = statement.executeQuery(stringbuffer1.toString());
				resultset2.next();
				cachedrowset.next();
				ResultSetMetaData resultsetmetadata = resultset2.getMetaData();
				int i1 = resultsetmetadata.getColumnCount();
				for (int j1 = 1; j1 <= i1; j1++)
					cachedrowset.updateString(resultsetmetadata.getColumnName(j1).toLowerCase(), Tools.nvl(resultset2.getString(j1)));

				resultset.next();
			}

			cachedrowset.first();
			cachedrowset.previous();
		}
		catch (SQLException sqlexception)
		{
			log.info((new StringBuilder()).append("getResultBySelect : ").append(sqlexception.getMessage()).toString());
			throw new DBException(sqlexception.getMessage());
		}
		catch (NamingException namingexception)
		{
			log.info((new StringBuilder()).append("naming ex : ").append(namingexception.getMessage()).toString());
			throw new DBException(namingexception.getMessage());
		}
		catch (Exception exception) { }
		finally
		{
			try
			{
				if (resultset1 != null)
					resultset1.close();
				if (resultset != null)
					resultset.close();
				if (stmt != null)
					stmt.close();
				if (statement != null)
					statement.close();
				try
				{
					if (flag)
					{
						closeConnection_method(con);
						con = null;
					}
				}
				catch (Exception exception2) { }
			}
			catch (Exception exception3) { }
		}
		return cachedrowset;
	}

	public CachedRowSet getResultBySelect_strSql(String s, String s1, Query query)
		throws DBException
	{
		String s2 = "";
		boolean flag = false;
		Statement statement = null;
		CachedRowSet cachedrowset = null;
		ResultSet resultset = null;
		ResultSet resultset1 = null;
		Object obj = null;
		int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
		int j = query.getRowsPerPage() > query.getTotalNum() - i ? query.getTotalNum() - i : query.getRowsPerPage();
		if (j == 0)
		{
			try
			{
				cachedrowset = new CachedRowSet();
			}
			catch (Exception exception) { }
			return cachedrowset;
		}
		String s3 = s.substring(6, s.indexOf("from"));
		StringBuffer stringbuffer = null;
		if (s3.indexOf("distinct") != -1)
		{
			s3 = s3.replace("distinct", "");
			stringbuffer = (new StringBuffer("select distinct top ")).append(j).append(" ").append(s3).append(" from ");
		} else
		{
			stringbuffer = (new StringBuffer("select top ")).append(j).append(" ").append(s3).append(" from ");
		}
		stringbuffer.append(s.substring(s.indexOf("from") + 4));
		log.info((new StringBuilder()).append("tempSql:").append(stringbuffer.toString()).toString());
		try
		{
			if (con == null)
			{
				flag = true;
				con = getCon(s1);
			}
			cachedrowset = new CachedRowSet();
			stmt = con.createStatement(1005, 1008);
			statement = con.createStatement(1005, 1008);
			resultset1 = stmt.executeQuery(stringbuffer.toString());
			cachedrowset.populate(resultset1);
			resultset = stmt.executeQuery(s);
			resultset.absolute(i + 1);
			for (int k = 0; k < query.getRowsPerPage() && !resultset.isAfterLast(); k++)
			{
				cachedrowset.next();
				ResultSetMetaData resultsetmetadata = resultset.getMetaData();
				int l = resultsetmetadata.getColumnCount();
				for (int i1 = 1; i1 <= l; i1++)
					cachedrowset.updateString(resultsetmetadata.getColumnName(i1).toLowerCase(), Tools.nvl(resultset.getString(i1)));

				resultset.next();
			}

			cachedrowset.first();
			cachedrowset.previous();
		}
		catch (SQLException sqlexception)
		{
			log.info((new StringBuilder()).append("getResultBySelect : ").append(sqlexception.getMessage()).toString());
			throw new DBException(sqlexception.getMessage());
		}
		catch (NamingException namingexception)
		{
			log.info((new StringBuilder()).append("naming ex : ").append(namingexception.getMessage()).toString());
			throw new DBException(namingexception.getMessage());
		}
		catch (Exception exception1) { }
		finally
		{
			try
			{
				if (resultset1 != null)
					resultset1.close();
				if (resultset != null)
					resultset.close();
				if (stmt != null)
					stmt.close();
				if (statement != null)
					statement.close();
				try
				{
					if (flag)
					{
						closeConnection_method(con);
						con = null;
					}
				}
				catch (Exception exception3) { }
			}
			catch (Exception exception4) { }
		}
		return cachedrowset;
	}

	public CachedRowSet getResultBySelect(String s, String s1, Query query)
		throws DBException
	{
		boolean flag = false;
		Statement statement = null;
		CachedRowSet cachedrowset = null;
		ResultSet resultset = null;
		ResultSet resultset1 = null;
		Object obj = null;
		int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
		int j = query.getRowsPerPage() > query.getTotalNum() - i ? query.getTotalNum() - i : query.getRowsPerPage();
		StringBuffer stringbuffer = (new StringBuffer("select top ")).append(j).append(" * from ");
		stringbuffer.append(s.substring(s.indexOf("from") + 4));
		log.info((new StringBuilder()).append("tempSql:").append(stringbuffer.toString()).toString());
		try
		{
			if (con == null)
			{
				flag = true;
				con = getCon(s1);
			}
			cachedrowset = new CachedRowSet();
			stmt = con.createStatement(1005, 1008);
			statement = con.createStatement(1005, 1008);
			resultset1 = stmt.executeQuery(stringbuffer.toString());
			cachedrowset.populate(resultset1);
			resultset = stmt.executeQuery(s);
			resultset.absolute(i + 1);
			for (int k = 0; k < query.getRowsPerPage() && !resultset.isAfterLast(); k++)
			{
				cachedrowset.next();
				ResultSetMetaData resultsetmetadata = resultset.getMetaData();
				int l = resultsetmetadata.getColumnCount();
				for (int i1 = 1; i1 <= l; i1++)
					cachedrowset.updateString(resultsetmetadata.getColumnName(i1).toLowerCase(), Tools.nvl(resultset.getString(i1)));

				resultset.next();
			}

			cachedrowset.first();
			cachedrowset.previous();
		}
		catch (SQLException sqlexception)
		{
			log.info((new StringBuilder()).append("getResultBySelect : ").append(sqlexception.getMessage()).toString());
			throw new DBException(sqlexception.getMessage());
		}
		catch (NamingException namingexception)
		{
			log.info((new StringBuilder()).append("naming ex : ").append(namingexception.getMessage()).toString());
			throw new DBException(namingexception.getMessage());
		}
		catch (Exception exception) { }
		finally
		{
			try
			{
				if (resultset1 != null)
					resultset1.close();
				if (resultset != null)
					resultset.close();
				if (stmt != null)
					stmt.close();
				if (statement != null)
					statement.close();
				try
				{
					if (flag)
					{
						closeConnection_method(con);
						con = null;
					}
				}
				catch (Exception exception2) { }
			}
			catch (Exception exception3) { }
		}
		return cachedrowset;
	}

	public CachedRowSet getResultBySelect(String s, Query query)
		throws DBException
	{
		CachedRowSet cachedrowset;
label0:
		{
			boolean flag = false;
			cachedrowset = null;
			ResultSet resultset = null;
			ResultSet resultset1 = null;
			Object obj = null;
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			int j = query.getRowsPerPage() > query.getTotalNum() - i ? query.getTotalNum() - i : query.getRowsPerPage();
			StringBuffer stringbuffer = new StringBuffer("");
			try
			{
				if (con == null)
				{
					flag = true;
					con = getCon(jndiName);
					StringBuffer stringbuffer1;
					if (dsName.equals("connectionPool_oracle"))
						stringbuffer1 = (new StringBuffer("select ")).append(s.substring(s.indexOf("select") + 6)).append("where rowNUM<").append(j);
					else
						stringbuffer1 = (new StringBuffer("select top ")).append(j).append(s.substring(s.indexOf("select") + 6));
				}
				StringBuffer stringbuffer2;
				if (dsName.equals("connectionPool_oracle"))
				{
					stringbuffer2 = (new StringBuffer("select ")).append(s.substring(s.indexOf("select") + 6)).append("and rowNUM<").append(j + 1);
					log.info((new StringBuilder()).append("tempSql========ORACAL======================:").append(stringbuffer2.toString()).toString());
				} else
				{
					stringbuffer2 = (new StringBuffer("select top ")).append(j).append(s.substring(s.indexOf("select") + 6));
					if (SysConstant.Print_getResultBySelect_strSql_queryCon.equals("1"))
						info((new StringBuilder()).append("method=getResultBySelect(String strSql, Query queryCon):获取crs骨架,骨架Sql=").append(stringbuffer2.toString()).toString());
				}
				cachedrowset = new CachedRowSet();
				stmt = con.createStatement(1005, 1008);
				info(stringbuffer2.toString());
				resultset1 = stmt.executeQuery(stringbuffer2.toString());
				cachedrowset.populate(resultset1);
				if (cachedrowset.size() > 0)
				{
					resultset = stmt.executeQuery(s);
					if (SysConstant.Print_getResultBySelect_strSql_queryCon.equals("1"))
						info((new StringBuilder()).append("method=getResultBySelect(String strSql, Query queryCon):更新crs骨架,形成crs,起始行=").append(i).append(",更新sql=").append(s).toString());
					resultset.absolute(i + 1);
					int k = 0;
					do
					{
						if (k >= query.getRowsPerPage() || resultset.isAfterLast())
							break;
						if (cachedrowset.next())
						{
							ResultSetMetaData resultsetmetadata = resultset.getMetaData();
							int l = resultsetmetadata.getColumnCount();
							for (int i1 = 1; i1 <= l; i1++)
								cachedrowset.updateString(resultsetmetadata.getColumnName(i1).toLowerCase(), Tools.nvl(resultset.getString(i1)));

							resultset.next();
							k++;
						}
					} while (true);
				}
				if (SysConstant.Print_getResultBySelect_strSql_queryCon.equals("1"))
					info("in method getResultBySelect(String strSql, Query queryCon):获取crs结束");
				cachedrowset.beforeFirst();
			}
			catch (SQLException sqlexception)
			{
				sqlexception.printStackTrace();
				log.info((new StringBuilder()).append("getResultBySelect : ").append(sqlexception.getMessage()).toString());
				throw new DBException(sqlexception.getMessage());
			}
			catch (NamingException namingexception)
			{
				log.info((new StringBuilder()).append("naming ex : ").append(namingexception.getMessage()).toString());
				throw new DBException(namingexception.getMessage());
			}
			catch (Exception exception2)
			{
				try
				{
					if (resultset1 != null)
						resultset1.close();
					if (resultset != null)
						resultset.close();
					if (stmt != null)
						stmt.close();
					try
					{
						if (flag)
						{
							closeConnection_method(con);
							con = null;
						}
					}
					catch (Exception exception3) { }
				}
				catch (Exception exception4) { }
				break label0;
			}
			finally
			{
				try
				{
					if (resultset1 != null)
						resultset1.close();
					if (resultset != null)
						resultset.close();
					if (stmt != null)
						stmt.close();
					try
					{
						if (flag)
						{
							closeConnection_method(con);
							con = null;
						}
					}
					catch (Exception exception6) { }
				}
				catch (Exception exception7) { 
		exception7.printStackTrace();}
			}
			try
			{
				if (resultset1 != null)
					resultset1.close();
				if (resultset != null)
					resultset.close();
				if (stmt != null)
					stmt.close();
				try
				{
					if (flag)
					{
						closeConnection_method(con);
						con = null;
					}
				}
				catch (Exception exception) { }
			}
			catch (Exception exception1) { }
			break label0;
		}
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
				s1 = cachedrowset.getString(1) != null ? cachedrowset.getString(1) : "";
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
		finally { }
		try
		{
			cachedrowset.close();
		}
		catch (SQLException sqlexception) { 
		sqlexception.printStackTrace();
		}
		try
		{
			cachedrowset.close();
		}
		catch (SQLException sqlexception1) { 
		sqlexception1.printStackTrace();
		}
		return i;
	}

	public int getRecNumBySelect(String s, String s1, String s2)
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
			stringbuffer.append("select count(distinct ").append(s2).append(") from ");
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
		finally { }
		try
		{
			cachedrowset.close();
		}
		catch (SQLException sqlexception) { 
		sqlexception.printStackTrace();}
		try
		{
			cachedrowset.close();
		}
		catch (SQLException sqlexception1) { 
			sqlexception1.printStackTrace();
		}
		return i;
	}

	public int getRecNumBySelect(String s, String s1)
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
			log.info((new StringBuilder()).append("tempSql.toString():").append(stringbuffer.toString()).toString());
			cachedrowset = getResultBySelect(stringbuffer.toString(), s1);
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
		finally { }
		try
		{
			cachedrowset.close();
		}
		catch (SQLException sqlexception) { 
		sqlexception.printStackTrace();
		}
		try
		{
			cachedrowset.close();
		}
		catch (SQLException sqlexception1) { }
		return i;
	}

	public String getErrorMessage()
	{
		return errorMsg;
	}

	public ConnectionPool getConnectionPool()
	{
		ConnectionPool connectionpool = null;
		try
		{
			connectionpool = (ConnectionPool)ctx.lookup(dsName);
		}
		catch (Exception exception)
		{
			log.info(exception.getMessage());
			exception.printStackTrace();
		}
		return connectionpool;
	}

	private void wait(int i)
	{
		try
		{
			Thread.sleep(i);
		}
		catch (InterruptedException interruptedexception) { }
	}

	static 
	{
		log = Logger.getLogger(SysTrace.DBLOGGER);
	}
}
