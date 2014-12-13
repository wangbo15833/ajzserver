package com.zagerbone.signrule;

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
import com.zagerbone.syslog.*;
import com.zagerbone.tabmap.*;

public class SignruleDAOImpl extends BasicObject
	implements SignruleDAO
{

	public SignruleDAOImpl()
	{
	}

	public String createSignrule(Signrule signrule,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		boolean bl =false;
		try
		{
			ArrayList al_col = new ArrayList();
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'signrule' and isautoinc='0' order by CAST(colorder as numeric)");
			if (cachedrowset==null||cachedrowset.size()==0){
			log.info("*** === there is no relate information in tabmap!  === ***");
			throw new BussinessProcessException("no information in tabmap");
			}
			StringBuffer stringbuffer = new StringBuffer("insert into signrule(");
			while(cachedrowset.next()){
			if(cachedrowset.getString("colname").equals("id")){}else{
				al_col.add(cachedrowset.getString("colname"));
				}
			}
			cachedrowset.close();
			for ( int i = 0 ; i<al_col.size() ; i++){
				if ( bl){
					stringbuffer.append(",");
				}
				stringbuffer.append(al_col.get(i).toString());
				bl = true;
			}
			stringbuffer.append(")values(");
			bl=false;
			for ( int i = 0 ; i<al_col.size() ; i++){
				if ( bl){
					stringbuffer.append(",");
				}
				stringbuffer.append("'");
				stringbuffer.append(signrule.getProp(Tools.nvl(al_col.get(i).toString())));
				stringbuffer.append("'");
				bl=true;
			}
			stringbuffer.append(")");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			
			
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","增加");
			hp_op.put("tabname","signrule");
			hp_op.put("cent","insert signrule");
			syslogdao.logact(hp_op,request,dbtrans);
			
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return String.valueOf(flag);
	}

	public Query findSignruleList(Query query,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'signrule' order by CAST(colorder as numeric)");
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
				StringBuffer sbr_sql = new StringBuffer("select * from signrule where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				log.info("-----====="+sbr_sql.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from signrule where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from signrule where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
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

	public Signrule findSignruleById(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Signrule signrule = new Signrule();
		StringBuffer stringbuffer = new StringBuffer("select * from signrule where id =");
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
				signrule.setid(cachedrowset.getString("id"));
				for (int j = 1; j <= i; j++)
					signrule.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return signrule;
	}

	public String modifySignrule(Signrule signrule,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("update signrule set ");
		ArrayList al_col = new ArrayList();
		try
		{
			dbtrans.beginTransaction();
		////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"signrule");
		////以上根据tabmap开启外键！
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'signrule' and isautoinc='0' order by CAST(colorder as numeric)");
			if (cachedrowset==null||cachedrowset.size()==0){
				log.info("*** === there is no relate information in tabmap!  === ***");
				throw new BussinessProcessException("no information in tabmap");
			}
			while(cachedrowset.next()){
			if(cachedrowset.getString("colname").equals("id")){}else{
				al_col.add(cachedrowset.getString("colname"));
				}
			}
			cachedrowset.close();
			String s_col = null;
			for (int i=0 ; i<al_col.size() ; i++){
				s_col = Tools.nvl((String)signrule.getProp(al_col.get(i).toString()));
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
			stringbuffer.append(signrule.getid());
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag = dbtrans.executeSql();
			
		////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"signrule",iuik);
		////以上根据tabmap关闭外键！
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","修改");
			hp_op.put("tabname","signrule");
			hp_op.put("cent","update signrule id ="+signrule.getid());
			syslogdao.logact(hp_op,request,dbtrans);
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{	
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return signrule.getid();
	}

	public String deleteSignrule(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		int i = Integer.parseInt(id);
		StringBuffer stringbuffer = new StringBuffer("delete from signrule where id = ");
		stringbuffer.append(i).append("");
		
		try
		{
			dbtrans.beginTransaction();
			////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"signrule");
			////以上根据tabmap开启外键！
		
			log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				boolean flag = dbtrans.executeSql();
				
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","删除");
			hp_op.put("tabname","signrule");
			hp_op.put("cent","delete signrule id ="+id);
			syslogdao.logact(hp_op,request,dbtrans);
			
			////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"signrule",iuik);
			////以上根据tabmap关闭外键！
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return id;
	}
	
	public CachedRowSet getAllCol()
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where 1=1 and tabname='signrule' order by CAST(colorder as numeric)");
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
	
	public HashMap getPri(HttpServletRequest request)
		throws BussinessProcessException
	{
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");
		String role_name = null;
		HashMap hp_pri = new HashMap();
		
		boolean flag = true;
		try{
		
				System.out.println(hp_user.toString());
			if(hp_user != null ){
				role_name 	= (String)hp_user.get("usertype");
				DBTrans dbtrans = new DBTrans();
				String sql ="select * from pritab where tabname='signrule' and role_name='"+role_name+"'";
				log.info("========the sql is :"+sql);
				CachedRowSet crs = dbtrans.getResultBySelect(sql);
				if (crs.size()==0){
					flag = false;
					throw new Exception("");
				}
				crs.next();
				
				String filter_str=Tools.nvl((String)crs.getString("filter_str")).replaceAll(" ","");
				
				String 	realname = (String)hp_user.get("realname");
				String 	work_unit = (String)hp_user.get("work_unit");
				filter_str = filter_str.replaceAll("myname",realname).replaceAll("mydept",work_unit);
				
				hp_pri.put("filter_str",filter_str);
				hp_pri.put("mypri",Tools.nvl((String)crs.getString("prilevel")));
				System.out.println(hp_pri.toString());
				return hp_pri;
			}
		}catch(Exception e){
			//e.printStackTrace();
		}
		if(!flag){
			throw new BussinessProcessException("权限配置不正确！");
			
		}
		hp_pri.put("filter_str","");
		hp_pri.put("mypri","1");
		return hp_pri;
	}
}
