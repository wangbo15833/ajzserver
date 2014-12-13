package com.zagerbone.tabmap;

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

public class TabmapDAOImpl extends BasicObject
	implements TabmapDAO
{

	public TabmapDAOImpl()
	{
	}

	public String createTabmap(Tabmap tabmap)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		try
		{
			ArrayList al_col = new ArrayList();
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'tabmap' and isautoinc='0' order by colorder");
			if (cachedrowset==null||cachedrowset.size()==0){
			log.info("*** === there is no relate information in tabmap!  === ***");
			throw new BussinessProcessException("no information in tabmap");
			}
			StringBuffer stringbuffer = new StringBuffer("insert into tabmap(");
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
				stringbuffer.append(tabmap.getProp(Tools.nvl(al_col.get(i).toString())));
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

	public Query findTabmapList(Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'tabmap' order by colorder");
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
			
			String equalstr= "and mycol = 'myvalue'";
			if (hashmap.containsKey("isblur")){
				if(((String)hashmap.get("isblur")).equals("1")){
					equalstr="and mycol like '%myvalue%'";
				}
			}
			
			
			for (int i=0 ; i<al_col.size() ; i++){
				s_col = Tools.nvl((String)hashmap.get(al_col.get(i).toString()));
				if (s_col != null && !s_col.equals("")){
					sbr_condition.append(equalstr.replaceAll("mycol",al_col.get(i).toString()).replaceAll("myvalue",s_col));
/* 					sbr_condition.append(" and ");
					sbr_condition.append(al_col.get(i).toString());
					sbr_condition.append("='");
					sbr_condition.append(s_col);
					sbr_condition.append("'");		 */			
				}
			
			}
			if (query.getTotalNum() == 0)
			{
				StringBuffer sbr_sql = new StringBuffer("select * from tabmap where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				log.info("-----====="+sbr_sql.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from tabmap where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from tabmap where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
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

	public Query findTabmapListBlur (Query query)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'tabmap' order by colorder");
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
					sbr_condition.append("like'");
					sbr_condition.append(s_col);
					sbr_condition.append("'");					
				}
			
			}
			if (query.getTotalNum() == 0)
			{
				StringBuffer sbr_sql = new StringBuffer("select * from tabmap where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				log.info("-----====="+sbr_sql.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from tabmap where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from tabmap where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
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

	
	public Tabmap findTabmapById(String id)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Tabmap tabmap = new Tabmap();
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where id =");
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
				tabmap.setid(cachedrowset.getString("id"));
				for (int j = 1; j <= i; j++)
					tabmap.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return tabmap;
	}

	public String modifyTabmap(Tabmap tabmap)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer=null;
		ArrayList al_col = new ArrayList();
		boolean flag =false;
		try
		{
			dbtrans.beginTransaction();
			String mode = Tools.nvl((String)tabmap.getProp("mode"));
			String x=null;
			if(mode.equals("altercol")||mode.equals("modcol")){//修改列
				stringbuffer = new StringBuffer("update tabmap set ");
				CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'tabmap' and isautoinc='0' order by colorder");
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
					s_col = Tools.nvl((String)tabmap.getProp(al_col.get(i).toString()));
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
				stringbuffer.append(tabmap.getid());
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
				if(mode.equals("altercol")){
					stringbuffer = new StringBuffer();
					stringbuffer.append("alter table dbo.").append((String)tabmap.getProp("tabname"));
					stringbuffer.append(" alter column ");
					stringbuffer.append(((String)tabmap.getProp("colname"))+" ");
					stringbuffer.append(" ").append((String)tabmap.getProp("datatype"));
					stringbuffer.append("(").append((String)tabmap.getProp("lenght")).append(") ");
					stringbuffer.append(((String)tabmap.getProp("allownull")).equals("1")?" ":" not null ");
					stringbuffer.append(((String)tabmap.getProp("datadefault")).equals("--")?" ":"default '"+((String)tabmap.getProp("datadefault"))+"' ").append(";");
					log.info(stringbuffer.toString());
					dbtrans.addSql(stringbuffer.toString());
					flag = dbtrans.executeSql();
				}
				
			}else if (mode.equals("newcol")){//新列
			
				stringbuffer = new StringBuffer();
				stringbuffer.append("alter table ").append((String)tabmap.getProp("tabname"));
				stringbuffer.append(" add ");
				stringbuffer.append(((String)tabmap.getProp("colname"))+" ");
				stringbuffer.append((String)tabmap.getProp("datatype"));
				stringbuffer.append("(").append((String)tabmap.getProp("lenght")).append(") ");
				stringbuffer.append(((String)tabmap.getProp("allownull")).equals("1")?" null ":" not null ");
				stringbuffer.append(((String)tabmap.getProp("datadefault")).equals("--")?"default ''":("default '"+(String)tabmap.getProp("datadefault")+"';"));
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
				
				x=createTabmap2(tabmap,dbtrans);
			
			}else if (mode.equals("newtab")){//新表
			
				stringbuffer = new StringBuffer();
				stringbuffer.append("create table dbo.").append((String)tabmap.getProp("tabname"));
				stringbuffer.append("(");
				stringbuffer.append("id  decimal IDENTITY(1,1) primary key)");
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
				
				
				stringbuffer = new StringBuffer();
				stringbuffer.append("insert into tabmap(tabname,tabnamec,colname,colnamec,datatype,allownull,isautoinc,isprikey,list_disp,deta_disp,colorder) values(");
				stringbuffer.append("'"+(String)tabmap.getProp("tabname")+"',");
				stringbuffer.append("'"+(String)tabmap.getProp("tabnamec")+"',");
				stringbuffer.append("'id',");
				stringbuffer.append("'编号',");
				stringbuffer.append("'numeric',");
				stringbuffer.append("'0',");
				stringbuffer.append("'1',");
				stringbuffer.append("'1',");
				stringbuffer.append("'1',");
				stringbuffer.append("'1',");
				stringbuffer.append("'10')");
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
				
				
			
			}else if (mode.equals("delcol")){//删除列
			
				stringbuffer = new StringBuffer();
				stringbuffer.append("alter table ").append((String)tabmap.getProp("tabname"));
				stringbuffer.append(" drop column ");
				stringbuffer.append(((String)tabmap.getProp("colname"))+" ");
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
				
				x=deleteTabmap2(tabmap.getid(),dbtrans);
			
			}else if (mode.equals("droptab")){//删除表
			
				stringbuffer = new StringBuffer();
				stringbuffer.append("drop table ").append((String)tabmap.getProp("tabname"));
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
				
				stringbuffer = new StringBuffer();
				stringbuffer.append("delete from tabmap where tabname='").append((String)tabmap.getProp("tabname")).append("'");
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
			
			}
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
			
		}
		return tabmap.getid();
	}

	public String deleteTabmap(String id)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		int i = Integer.parseInt(id);
		StringBuffer stringbuffer = new StringBuffer("delete from tabmap where id = ");
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
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where 1=1 and tabname='tabmap' order by colorder ");
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

	/////////////////////////////////////////////////////////////////////////////////////////////////
	//以下两个方法为modify内部调用
	/////////////////////////////////////////////////////////////////////////////////////////////////
		private String createTabmap2(Tabmap tabmap,DBTrans dbtrans)
		throws BussinessProcessException
	{
		boolean flag = false;
		try
		{
			ArrayList al_col = new ArrayList();
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'tabmap' and isautoinc='0' order by colorder");
			if (cachedrowset==null||cachedrowset.size()==0){
			log.info("*** === there is no relate information in tabmap!  === ***");
			throw new BussinessProcessException("no information in tabmap");
			}
			StringBuffer stringbuffer = new StringBuffer("insert into tabmap(");
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
				stringbuffer.append(tabmap.getProp(Tools.nvl(al_col.get(i).toString())));
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
	
		private String deleteTabmap2(String id,DBTrans dbtrans)
		throws BussinessProcessException
	{
		int i = Integer.parseInt(id);
		StringBuffer stringbuffer = new StringBuffer("delete from tabmap where id = ");
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
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	//设置unique 约束 设置外键级联------------删除处键级联 删除unique约束
	/////////////////////////////////////////////////////////////////////////////////////////////////
	public String setcascade(DBTrans dbtrans,String fktab)
		throws BussinessProcessException
	{
		CachedRowSet crs=null;
		StringBuffer sbr = new StringBuffer();
		boolean flag = false;
		
		String colname = null;
		String tabname = null;
		String fkcol = null;
		int iu = 0;
		int ik = 0;
		String sql= null;
		
		try
		{
		
			//设置unique
			sql = "select distinct fkcol,id from tabmap where fktab = '"+fktab+"' and unfkca='1' order by id ";
			log.info("设置unique约束"+sql);
			crs = dbtrans.getResultBySelect(sql);
			while(crs.next())
			{
				fkcol = Tools.nvl(crs.getString("fkcol"));
				
				if(!fkcol.equals("")){
					iu++;
					sql = "alter table "+fktab+" add  constraint unique_"+String.valueOf(iu)+" unique ("+fkcol+")";
					log.info(sql);
					dbtrans.addSql(sql);
					flag = dbtrans.executeSql();
					
				}
			}
			//设置外键级联
			crs = new CachedRowSet();
			sql = "select colname,tabname,fkcol from tabmap where fktab = '"+fktab+"' and unfkca='1' order by id ";
			log.info("设置级联"+sql);
			crs = dbtrans.getResultBySelect(sql);
			while(crs.next()){
			
				colname = Tools.nvl(crs.getString("colname"));
				tabname = Tools.nvl(crs.getString("tabname"));
				fkcol = Tools.nvl(crs.getString("fkcol"));
				
				if(!fkcol.equals("") && !tabname.equals("") && !colname.equals("")){
					ik++;
					sql = "alter table "+tabname+" add constraint  fk_"+String.valueOf(ik)+" foreign key ("+colname+")  references "+fktab+"("+fkcol+") on update cascade on delete cascade";
					log.info(sql);
					dbtrans.addSql(sql);
					flag = dbtrans.executeSql();
				}			
			}
			return String.valueOf(iu)+":"+String.valueOf(ik);
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		
	}
	
	public void remcascade(DBTrans dbtrans,String fktab,String iuik)
		throws BussinessProcessException
	{
		CachedRowSet crs=null;
		StringBuffer sbr = new StringBuffer();
		boolean flag = false;
		String sql = null;
		
		String colname = null;
		String tabname = null;
		String fkcol = null;
		int iu = Integer.parseInt(iuik.split(":")[0]);
		int ik = Integer.parseInt(iuik.split(":")[1]);
		
		try
		{
			//删除外键级联
			sql = "select colname,tabname,fkcol from tabmap where fktab = '"+fktab+"' and unfkca='1' order by id desc";
			log.info("删除外键级联===sql："+sql);
			crs = dbtrans.getResultBySelect(sql);
			while(crs.next()){
			
				colname = Tools.nvl(crs.getString("colname"));
				tabname = Tools.nvl(crs.getString("tabname"));
				fkcol = Tools.nvl(crs.getString("fkcol"));
				
				if(!fkcol.equals("") && !tabname.equals("") && !colname.equals("")){
					sql="alter table "+tabname+" drop constraint fk_"+String.valueOf(ik)+" ";
					log.info(sql);
					dbtrans.addSql(sql);
					flag = dbtrans.executeSql();
					ik--;
				}			
			}
			
			//删除unique约束
			crs = new CachedRowSet();
			sql = "select distinct fkcol,id from tabmap where fktab = '"+fktab+"' and unfkca='1' order by id desc";
			log.info("删除unique约束===sql： "+sql);
			crs = dbtrans.getResultBySelect(sql);
			while(crs.next())
			{
				fkcol = Tools.nvl(crs.getString("fkcol"));
				
				if(!fkcol.equals("")){
					sql = "alter table "+fktab+" drop constraint unique_"+String.valueOf(iu)+" ";
					log.info(sql);
					dbtrans.addSql(sql);
					flag = dbtrans.executeSql();
					iu--;
				}
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
	}

/* --增加unique约束
alter table student 
add  constraint only_one unique (sname)
--添加SQL SERVER外键
alter table s2 
add constraint Fk_s foreign key (sname)  references student(sname) 
on update cascade on delete cascade
--删除SQL SERVER外键
alter table 
s2 drop constraint FK_s
--删除名为only_one的约束
alter table student
drop constraint only_one */
	
	
}
