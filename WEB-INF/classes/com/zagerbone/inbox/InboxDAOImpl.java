package com.zagerbone.inbox;

import com.shenghao.arch.baseobject.BasicObject;
import com.shenghao.arch.exception.BussinessProcessException;
import com.shenghao.arch.util.DBTransUtil;
import com.zagerbone.util.Tools;
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

public class InboxDAOImpl extends BasicObject
	implements InboxDAO
{

	public InboxDAOImpl()
	{
	}

	public String createInbox(Inbox inbox,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		boolean bl =false;
		try
		{
			ArrayList al_col = new ArrayList();
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'inbox' and isautoinc='0' order by CAST(colorder as numeric)");
			if (cachedrowset==null||cachedrowset.size()==0){
			log.info("*** === there is no relate information in tabmap!  === ***");
			throw new BussinessProcessException("no information in tabmap");
			}
			StringBuffer stringbuffer = new StringBuffer("insert into inbox(");
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
				stringbuffer.append(inbox.getProp(Tools.nvl(al_col.get(i).toString())));
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
			hp_op.put("tabname","inbox");
			hp_op.put("cent","insert inbox");
			syslogdao.logact(hp_op,request,dbtrans);
			
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return String.valueOf(flag);
	}

	public Query findInboxList(Query query,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			String msgtype = Tools.GBK(request.getParameter("msgtype"));
			String rec_name = Tools.GBK(request.getParameter("rec_name"));
			String mytab = null;
			String mycode = null;
			if(!msgtype.equals("公告")){
				if (msgtype.equals("通知")){
					mytab="tongz";
					mycode="tzcode";
				}else if (msgtype.equals("提醒")){
					mytab="txinfo";
					mycode="txcode";
				}/* else if (msgtype.equals("公告")){
					mytab="gginfo";
					mycode="ggcode";
				} */
				
				CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'inbox' order by CAST(colorder as numeric)");
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
						sbr_condition.append(" and inbox.");
						sbr_condition.append(al_col.get(i).toString());
						sbr_condition.append("='");
						sbr_condition.append(s_col);
						sbr_condition.append("'");					
					}
				
				}
				if (query.getTotalNum() == 0)
				{
					StringBuffer sbr_sql = new StringBuffer("select * from inbox where 1=1 and  msgtype='").append(msgtype).append("' and rec_account='").append(rec_name).append("' ");
					sbr_sql.append(" ");
					log.info("-----====="+sbr_sql.toString());
					query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
				}
				if (query.getCurrentPageNum() <= 0)
					query.setCurrentPageNum(1);
				int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
				StringBuffer stringbuffer1 = new StringBuffer();
				stringbuffer1.append("select top ").append(query.getRowsPerPage()).append((" inbox.*, mytab.cont from inbox left join mytab on msgcode=mytab.mycode where inbox.id not in ").replaceAll("mytab",mytab).replaceAll("mycode",mycode));
				stringbuffer1.append("(select top ").append(i).append(" inbox.id from inbox where 1=1 ");
				stringbuffer1.append(sbr_condition.toString()).append(" order by cast(isread as int) asc, inbox.id desc ) ");
				stringbuffer1.append(sbr_condition.toString());
				stringbuffer1.append("  order by cast(isread as int) asc, inbox.id desc  ");
				
				/*"select top 3 inbox.*,tongz.cont from inbox left join tongz on msgcode=tongz.tzcode where inbox.id not in (select top 0 id from inbox where 1=1 and  inbox.msgtype='通知' and inbox.rec_name='李四' order by inbox.id desc )  and  inbox.msgtype='通知' and inbox.rec_name='李四' order by inbox.id desc  "*/
				
				query.setSql(stringbuffer1.toString());
				log.info(stringbuffer1.toString());
				CachedRowSet cachedrowset1 = dbtrans.getResultBySelect(stringbuffer1.toString());
				query.setConditionBody(cachedrowset1);
			}else {
				/*if (msgtype.equals("通知")){
					mytab="tongz";
					mycode="tzcode";
				}else if (msgtype.equals("提醒")){
					mytab="txinfo";
					mycode="txcode";
				} else if (msgtype.equals("公告")){
					mytab="gginfo";
					mycode="ggcode";
				} */
				
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
					if((al_col.get(i).toString()).equals("rec_name")){
						continue;
					} 
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
					StringBuffer sbr_sql = new StringBuffer("select * from gginfo where 1=1");
					log.info("-----====="+sbr_sql.toString());
					query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
				}
				if (query.getCurrentPageNum() <= 0)
					query.setCurrentPageNum(1);
				int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
				StringBuffer stringbuffer1 = new StringBuffer();
				stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from gginfo where id not in ");
				stringbuffer1.append("(select top ").append(i).append(" id from gginfo where 1=1 ");
				stringbuffer1.append(sbr_condition.toString()).append(" order by isNew desc, gg_time desc ) ");
				stringbuffer1.append(sbr_condition.toString());
				stringbuffer1.append(" order by isNew desc, gg_time desc  ");
				
				/*"select top 3 inbox.*,tongz.cont from inbox left join tongz on msgcode=tongz.tzcode where inbox.id not in (select top 0 id from inbox where 1=1 and  inbox.msgtype='通知' and inbox.rec_name='李四' order by inbox.id desc )  and  inbox.msgtype='通知' and inbox.rec_name='李四' order by inbox.id desc  "*/
				
				
			StringBuffer sbr = new StringBuffer("select * from tabmap where 1=1 and tabname='gginfo' order by CAST(colorder as numeric)");			
			CachedRowSet crs = dbtrans.getResultBySelect(sbr.toString());
			request.setAttribute("crs_cols",crs);
				
				query.setSql(stringbuffer1.toString());
				log.info(stringbuffer1.toString());
				CachedRowSet cachedrowset1 = dbtrans.getResultBySelect(stringbuffer1.toString());
				query.setConditionBody(cachedrowset1);
			
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return query;
	}

	public CachedRowSet findInboxById(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Inbox inbox = new Inbox();
		String msgtype = Tools.GBK(request.getParameter("msgtype"));
		System.out.println("the query msgtype is :"+msgtype);
		StringBuffer stringbuffer = null;
		CachedRowSet cachedrowset = null;
		if(msgtype.equals("通知")){
			stringbuffer = new StringBuffer("select i.*,t.cont from inbox as i left join tongz as t on i.msgcode=t.tzcode where i.id =");
		}else if(msgtype.equals("提醒")){
			stringbuffer = new StringBuffer("select i.*,t.cont from inbox as i left join txinfo as t on i.msgcode=t.txcode where i.id =");
		}else if(msgtype.equals("公告")){
			//stringbuffer = new StringBuffer("select i.*,t.cont from inbox as i left join gginfo as t on i.msgcode=t.ggcode where i.id =");
			stringbuffer = new StringBuffer("select * from gginfo where id =");
		}
		stringbuffer.append(id);
		try
		{
			cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString());
			if (cachedrowset == null || cachedrowset.size() == 0){
				throw new BussinessProcessException("find***ById is no information in inbox!");
			}
			
			return cachedrowset;
			/*
			ResultSetMetaData resultsetmetadata = cachedrowset.getMetaData();
			
			int i = resultsetmetadata.getColumnCount();
			if (cachedrowset.next())
			{
				inbox.setid(cachedrowset.getString("id"));
				for (int j = 1; j <= i; j++)
					inbox.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
			}
			*/
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
	}

	public String modifyInbox(Inbox inbox,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("update inbox set ");
		ArrayList al_col = new ArrayList();
		try
		{
			stringbuffer.append(" isread = '1',read_time=getdate()");
			stringbuffer.append(" where id=");
			stringbuffer.append(inbox.getid());
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag = dbtrans.executeSql();
			/* dbtrans.beginTransaction();
		////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"inbox");
		////以上根据tabmap开启外键！
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'inbox' and isautoinc='0' and deta_disp='1' order by CAST(colorder as numeric)");
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
				s_col = Tools.nvl((String)inbox.getProp(al_col.get(i).toString()));
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
			stringbuffer.append(inbox.getid());
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag = dbtrans.executeSql();
			
		////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"inbox",iuik);
		////以上根据tabmap关闭外键！
			 */
/* 			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","修改");
			hp_op.put("tabname","inbox");
			hp_op.put("cent","update inbox id ="+inbox.getid());
			syslogdao.logact(hp_op,request,dbtrans);
			dbtrans.commitTransaction(); */
			
		}
		catch (Exception exception)
		{	
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return inbox.getid();
	}

	public String deleteInbox(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		int i = Integer.parseInt(id);
		StringBuffer stringbuffer = new StringBuffer("delete from inbox where id = ");
		stringbuffer.append(i).append("");
		
		try
		{
			dbtrans.beginTransaction();
			////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"inbox");
			////以上根据tabmap开启外键！
		
			log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				boolean flag = dbtrans.executeSql();
				
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","删除");
			hp_op.put("tabname","inbox");
			hp_op.put("cent","delete inbox id ="+id);
			syslogdao.logact(hp_op,request,dbtrans);
			
			////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"inbox",iuik);
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
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where 1=1 and tabname='inbox' order by CAST(colorder as numeric)");
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
				String sql ="select * from pritab where tabname='inbox' and role_name='"+role_name+"'";
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
	/**
	*查出公告信息 根据id查出 并设置在request里
	*设置request.setAttribute("crs_byid",crs_byid);request.setAttribute("crs_cols",crs);
	*/
		public void  readGginfo(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = null;
		stringbuffer = new StringBuffer("select * from gginfo where id =");
		stringbuffer.append(id);
		try
		{
			log.info("this is the readGginfo processor , sql is :"+stringbuffer.toString());
			CachedRowSet crs_byid = dbtrans.getResultBySelect(stringbuffer.toString());
			if (crs_byid == null || crs_byid.size() == 0){
				throw new BussinessProcessException("find***ById is no information in tabmap!");
			}
			stringbuffer = new StringBuffer("select * from tabmap where tabname = 'gginfo' order by CAST(colorder as numeric)");
			log.info("this is the readGginfo processor , sql is :"+stringbuffer.toString());
			CachedRowSet crs = dbtrans.getResultBySelect(stringbuffer.toString());
			
		request.setAttribute("crs_byid",crs_byid);
		request.setAttribute("crs_cols",crs);	
		}catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
	}

}
