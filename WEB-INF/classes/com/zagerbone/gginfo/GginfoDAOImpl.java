package com.zagerbone.gginfo;

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

public class GginfoDAOImpl extends BasicObject
	implements GginfoDAO
{

	public GginfoDAOImpl()
	{
	}

	public String createGginfo(Gginfo gginfo,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		boolean bl =false;
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select * from codeset where tabname='gginfo' ");
			cachedrowset.next();
			String prefix = cachedrowset.getString("prefix");
			String manu = cachedrowset.getString("manu");
			String curcode = String.valueOf(Integer.parseInt(manu)+1);
			while(curcode.length()<5){
				curcode="0"+curcode;
			}
			String ggcode =prefix + curcode;
			
			
			gginfo.setProp("auth",(String)hp_user.get("realname"));
			gginfo.setProp("dept",(String)hp_user.get("work_unit"));
			
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("insert into gginfo(title, ggcode, gg_time, eff_time, auth, dept, cont,auth_account ,rec_name, issend,isNew) values('");
			stringbuffer.append(gginfo.getProp("title").toString()).append("','");
			stringbuffer.append(ggcode).append("',").append("getdate()").append(",'");
			stringbuffer.append(Tools.nvl(gginfo.getProp("eff_time"))).append("','");
			stringbuffer.append(Tools.nvl(gginfo.getProp("auth"))).append("','");
			stringbuffer.append(Tools.nvl(gginfo.getProp("dept"))).append("','");
			stringbuffer.append(Tools.nvl(gginfo.getProp("cont"))).append("','");
			stringbuffer.append((String)hp_user.get("username")).append("','");
			stringbuffer.append("所有人").append("','1','");
			stringbuffer.append("是").append("')");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			
		
	/* 	String ai[] = (gginfo.getProp("rec_name").toString()).split(";");
			for (int i =0 ; i<ai.length;i++){
				stringbuffer = new StringBuffer();
				stringbuffer.append("insert into inbox(title,auth, rec_name, msgtype, isforceread, isread, msgcode, deli_time, read_time) values('").append(gginfo.getProp("title").toString()).append("','").append(gginfo.getProp("auth").toString()).append("','").append(ai[i]).append("','").append("通知").append("','").append("0").append("','").append("0").append("','").append(tzcode).append("',").append("getdate()").append(",'").append("2000-01-01 00:00:000").append("')");
				log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				flag = dbtrans.executeSql();
			} */
			
			stringbuffer = new StringBuffer();
			stringbuffer.append("update codeset set manu='").append(curcode).append("'  where tabname='gginfo'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			
			
			stringbuffer = new StringBuffer();
			stringbuffer.append("select * from userinfo where 1=1 and flag='正常'");
			log.info(stringbuffer.toString());
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset.size()>0){
				while(cachedrowset.next()){
					String rec_name = cachedrowset.getString("realname");
					stringbuffer = new StringBuffer();
					stringbuffer.append("insert into inbox(title,auth, rec_name, msgtype, isforceread, isread, msgcode, deli_time, read_time) values('").append(gginfo.getProp("title").toString()).append("','").append(gginfo.getProp("auth").toString()).append("','").append(rec_name).append("','").append("公告").append("','").append("0").append("','").append("0").append("','").append(ggcode).append("',").append("getdate()").append(",'").append("2000-01-01 00:00:000").append("')");
					log.info(stringbuffer.toString());
					dbtrans.addSql(stringbuffer.toString());
					flag = dbtrans.executeSql();
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","增加");
			hp_op.put("tabname","gginfo");
			hp_op.put("cent","insert gginfo");
			syslogdao.logact(hp_op,request,dbtrans);
			
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return String.valueOf(flag);
	}

	public Query findGginfoList(Query query,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'gginfo' order by CAST(colorder as numeric)");
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
				StringBuffer sbr_sql = new StringBuffer("select * from gginfo where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				log.info("-----====="+sbr_sql.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from gginfo where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from gginfo where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
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

	public Gginfo findGginfoById(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Gginfo gginfo = new Gginfo();
		StringBuffer stringbuffer = new StringBuffer("select * from gginfo where id =");
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
				gginfo.setid(cachedrowset.getString("id"));
				for (int j = 1; j <= i; j++)
					gginfo.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return gginfo;
	}

	public String modifyGginfo(Gginfo gginfo,HttpServletRequest request)
		throws BussinessProcessException
	{
	
		String flag =null;
		flag = deleteGginfo(gginfo.getid(),request);
		flag = createGginfo(gginfo,request);
/* 		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("update gginfo set ");
		ArrayList al_col = new ArrayList();
		try
		{
			dbtrans.beginTransaction();
		////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"gginfo");
		////以上根据tabmap开启外键！
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'gginfo' and isautoinc='0' order by CAST(colorder as numeric)");
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
				s_col = Tools.nvl((String)gginfo.getProp(al_col.get(i).toString()));
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
			stringbuffer.append(gginfo.getid());
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag = dbtrans.executeSql();
			
		////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"gginfo",iuik);
		////以上根据tabmap关闭外键！
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","修改");
			hp_op.put("tabname","gginfo");
			hp_op.put("cent","update gginfo id ="+gginfo.getid());
			syslogdao.logact(hp_op,request,dbtrans);
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{	
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		} */
		return gginfo.getid();
	}

	public String deleteGginfo(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = null;
		CachedRowSet cachedrowset = null;
		try
		{	
			stringbuffer = new StringBuffer("select * from gginfo where id = ");
			stringbuffer.append(id);
			log.info("in delete msg processor, sql is ="+stringbuffer.toString());
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset.size()!=0){
				cachedrowset.next();
				String msgcode=cachedrowset.getString("ggcode");
				stringbuffer = new StringBuffer("delete from inbox where msgcode = '");
				stringbuffer.append(msgcode).append("'");
				
				log.info("------msg in inbox has been delete, sql is :"+stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				boolean flag = dbtrans.executeSql();
				
			}
			
			int i = Integer.parseInt(id);
			stringbuffer = new StringBuffer("delete from gginfo where id = ");
			stringbuffer.append(i).append("");
			dbtrans.beginTransaction();
			////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"gginfo");
			////以上根据tabmap开启外键！
		
			log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				boolean flag = dbtrans.executeSql();
				
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","删除");
			hp_op.put("tabname","gginfo");
			hp_op.put("cent","delete gginfo id ="+id);
			syslogdao.logact(hp_op,request,dbtrans);
			
			////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"gginfo",iuik);
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
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where 1=1 and tabname='gginfo' order by CAST(colorder as numeric)");
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
				String sql ="select * from pritab where tabname='gginfo' and role_name='"+role_name+"'";
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
				
				if (role_name.equals("系统管理员")){
				sql ="select * from userinfo where 1=1";
				}else{
				sql ="select * from userinfo where 1=1 "+ " and work_unit='"+work_unit+"'";
				}
				log.info("========the sql is :"+sql);
				CachedRowSet crs_user = dbtrans.getResultBySelect(sql);
				
				hp_pri.put("filter_str",filter_str);
				hp_pri.put("mypri",Tools.nvl((String)crs.getString("prilevel")));
				hp_pri.put("crs_user",crs_user);
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
