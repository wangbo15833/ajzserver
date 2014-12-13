package com.zagerbone.syslog;

import com.shenghao.arch.baseobject.BasicObject;
import com.shenghao.arch.exception.BussinessProcessException;
import com.shenghao.arch.util.DBTransUtil;
import com.shenghao.arch.util.Tools;
import com.zagerbone.util.DBTrans;
import com.zagerbone.util.Query;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sun.jdbc.rowset.CachedRowSet;
import java.lang.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SyslogDAOImpl extends BasicObject
	implements SyslogDAO
{

	public SyslogDAOImpl()
	{
	}

	public String createSyslog(Syslog syslog)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		try
		{
			ArrayList al_col = new ArrayList();
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'syslog' and isautoinc='0' order by CAST(colorder as DECIMAL)");
			if (cachedrowset==null||cachedrowset.size()==0){
			log.info("*** === there is no relate information in tabmap!  === ***");
			throw new BussinessProcessException("no information in tabmap");
			}
			StringBuffer stringbuffer = new StringBuffer("insert into syslog(");
			while(cachedrowset.next()){
				al_col.add(cachedrowset.getString("colname"));
			}
			cachedrowset.close();
			for ( int i = 0 ; i<al_col.size() ; i++){
				if ( i != 0 ){
					stringbuffer.append(",");
				}
				stringbuffer.append(al_col.get(i).toString());
			}
			stringbuffer.append(")values(");
			for ( int i = 0 ; i<al_col.size() ; i++){
				if ( i != 0 ){
					stringbuffer.append(",");
				}
				stringbuffer.append("'");
				stringbuffer.append(syslog.getProp(Tools.nvl(al_col.get(i).toString())));
				stringbuffer.append("'");
			}
			stringbuffer.append(")");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return String.valueOf(flag);
	}

	public Query findSyslogList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'syslog' order by CAST(colorder as DECIMAL)");
			if (cachedrowset==null||cachedrowset.size()==0){
				log.info("*** === there is no relate information in tabmap!  === ***");
				throw new BussinessProcessException("no information in tabmap");
			}
			while(cachedrowset.next()){
				al_col.add(cachedrowset.getString("colname"));
			}
			cachedrowset.close();
			StringBuffer sbr_condition = new StringBuffer();
			String s_col = null;
			for (int i=0 ; i<al_col.size() ; i++){
				s_col = Tools.nvl((String)hashmap.get(al_col.get(i).toString()));
				if (s_col != null && !s_col.equals("")){
					sbr_condition.append(" and ");
					sbr_condition.append(al_col.get(i).toString());
					sbr_condition.append("='");
					sbr_condition.append(s_col);
					sbr_condition.append("'");					
				}
			
			}
			if (query.getTotalNum() == 0)
			{
				StringBuffer sbr_sql = new StringBuffer("select * from syslog where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				log.info("-----====="+sbr_sql.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from syslog where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from syslog where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
			stringbuffer1.append(sbr_condition.toString());
			stringbuffer1.append("  order by id desc  ");
			query.setSql(stringbuffer1.toString());
			log.info(stringbuffer1.toString());
			CachedRowSet cachedrowset1 = dbtrans.getResultBySelect(stringbuffer1.toString());
			query.setConditionBody(cachedrowset1);
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return query;
	}

	public Syslog findSyslogById(String id)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Syslog syslog = new Syslog();
		StringBuffer stringbuffer = new StringBuffer("select * from syslog where id =");
		stringbuffer.append(id);
		try
		{
			CachedRowSet cachedrowset = dbtransutil.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0){
				throw new BussinessProcessException("find***ById is no information in tabmap!");
			}
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				syslog.setid(cachedrowset.getString("id"));
				for (int j = 1; j <= i; j++)
					syslog.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return syslog;
	}

	public String modifySyslog(Syslog syslog)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("update syslog set ");
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'syslog' and isautoinc='0' order by CAST(colorder as DECIMAL)");
			if (cachedrowset==null||cachedrowset.size()==0){
				log.info("*** === there is no relate information in tabmap!  === ***");
				throw new BussinessProcessException("no information in tabmap");
			}
			while(cachedrowset.next()){
				al_col.add(cachedrowset.getString("colname"));
			}
			cachedrowset.close();
			String s_col = null;
			for (int i=0 ; i<al_col.size() ; i++){
				s_col = Tools.nvl((String)syslog.getProp(al_col.get(i).toString()));
				if (s_col != null && !s_col.equals("")){
					if(i!=0){
						stringbuffer.append(",");
					}
					stringbuffer.append(al_col.get(i).toString());
					stringbuffer.append("='");
					stringbuffer.append(s_col);
					stringbuffer.append("'");					
				}
			}
			stringbuffer.append(" where id=");
			stringbuffer.append(syslog.getid());
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return syslog.getid();
	}

	public String deleteSyslog(String id)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		int i = Integer.parseInt(id);
		StringBuffer stringbuffer = new StringBuffer("delete from syslog where id = ");
		stringbuffer.append(i).append("");
		try
		{
			log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				boolean flag = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return id;
	}
	
	public CachedRowSet getAllCol()
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where 1=1 and tabname='syslog' order by CAST(colorder as DECIMAL)");
		try
		{				
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			return cachedrowset;
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
	}
	///////////////////////////////////////////////////////////
	////系统操作日志
	///////////////////////////////////////////////////////////
	public void logact(HashMap hp_op,HttpServletRequest request,DBTrans dbtrans)
		throws BussinessProcessException
	{

			HttpSession session = request.getSession(); 
			HashMap hp_user = (HashMap)session.getAttribute("hp_user");
			String username = null;
			String work_unit = null;
			String realname = null;
			
			String event = null;
			String tabname = null;
			String cent = null;
			
			boolean flag = false;
			try{
				if(hp_user != null ){
					realname 	= (String)hp_user.get("realname");
					work_unit 	= (String)hp_user.get("work_unit");
					username 	= (String)hp_user.get("username");
				}
				
				
				if(hp_op != null ){
					event 	= (String)hp_op.get("event");
					tabname 	= (String)hp_op.get("tabname");
					cent 	= (String)hp_op.get("cent");
				}
				StringBuffer stringbuffer = new StringBuffer("insert into syslog(event,e_datetime,e_operator,e_account,e_work_unit,tabname,cent) values('"+event+"', getdate(),'"+realname+"','"+username+"','"+work_unit+"','"+tabname+"','"+cent+"')");
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
			}catch(Exception e){
				e.printStackTrace();
				throw new BussinessProcessException(e.getMessage());
			}
	
	}
	
	public void logact2(HashMap hp_op,DBTrans dbtrans)
		throws BussinessProcessException
	{
	
			String username = null;
			String work_unit = null;
			String realname = null;
			
			String event = null;
			String tabname = null;
			String cent = null;
			
			boolean flag = false;
			try{
					realname 	= "系统";
					work_unit 	= "--";
					username 	= "--";
				
				
			//System.out.println("=========Initonce is 111111!!");
				if(hp_op != null ){
					event 	= (String)hp_op.get("event");
					tabname 	= (String)hp_op.get("tabname");
					cent 	= (String)hp_op.get("cent");
				}
				
				StringBuffer stringbuffer = new StringBuffer("insert into syslog(event,e_datetime,e_operator,e_account,e_work_unit,tabname,cent) values('"+event+"', getdate(),'"+realname+"','"+username+"','"+work_unit+"','"+tabname+"','"+cent+"')");
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
			}catch(Exception e){
				e.printStackTrace();
				throw new BussinessProcessException(e.getMessage());
			}
	
	}
}
