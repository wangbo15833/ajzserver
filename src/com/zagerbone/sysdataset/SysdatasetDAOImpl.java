package com.zagerbone.sysdataset;

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

public class SysdatasetDAOImpl extends BasicObject
	implements SysdatasetDAO
{

	public SysdatasetDAOImpl()
	{
	}

	public String createSysdataset(Sysdataset sysdataset)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		try
		{
			ArrayList al_col = new ArrayList();
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'sysdataset' and isautoinc='0' order by CAST(colorder as DECIMAL)");
			if (cachedrowset==null||cachedrowset.size()==0){
			log.info("*** === there is no relate information in tabmap!  === ***");
			throw new BussinessProcessException("no information in tabmap");
			}
			StringBuffer stringbuffer = new StringBuffer("insert into sysdataset(");
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
				stringbuffer.append(sysdataset.getProp(Tools.nvl(al_col.get(i).toString())));
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

	public Query findSysdatasetList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'sysdataset' order by CAST(colorder as DECIMAL)");
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
				StringBuffer sbr_sql = new StringBuffer("select * from sysdataset where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				log.info("-----====="+sbr_sql.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from sysdataset where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from sysdataset where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
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

	public Sysdataset findSysdatasetById(String id)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Sysdataset sysdataset = new Sysdataset();
		StringBuffer stringbuffer = new StringBuffer("select * from sysdataset where id =");
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
				sysdataset.setid(cachedrowset.getString("id"));
				for (int j = 1; j <= i; j++)
					sysdataset.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return sysdataset;
	}

	public String modifySysdataset(Sysdataset sysdataset)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("update sysdataset set ");
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'sysdataset' and isautoinc='0' order by CAST(colorder as DECIMAL)");
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
				s_col = Tools.nvl((String)sysdataset.getProp(al_col.get(i).toString()));
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
			stringbuffer.append(sysdataset.getid());
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag = dbtrans.executeSql();
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return sysdataset.getid();
	}

	public String deleteSysdataset(String id)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		int i = Integer.parseInt(id);
		StringBuffer stringbuffer = new StringBuffer("delete from sysdataset where id = ");
		stringbuffer.append(i).append("");
		try
		{
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
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where 1=1 and tabname='sysdataset' order by CAST(colorder as DECIMAL)");
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
}
