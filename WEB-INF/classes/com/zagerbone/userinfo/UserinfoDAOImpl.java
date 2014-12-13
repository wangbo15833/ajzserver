package com.zagerbone.userinfo;

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

public class UserinfoDAOImpl extends BasicObject
	implements UserinfoDAO
{

	public UserinfoDAOImpl()
	{
	}

	public String createUserinfo(Userinfo userinfo,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		boolean bl =false;
		CachedRowSet cachedrowset =null;
		
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");	
		String 	work_unit = (String)hp_user.get("work_unit");
		String 	deptid = (String)hp_user.get("deptid");
		int priLevel = getPriLevel(request);
		try
		{
			dbtrans.beginTransaction();
			String prefix = null;
			String manu =   null;
			String curcode =null;
////////////////////////////////////////////////////////
			cachedrowset = dbtrans.getResultBySelect("select * from codeset where tabname='userinfo'  ");
			cachedrowset.next();
			prefix = cachedrowset.getString("prefix");
			manu = cachedrowset.getString("manu");
			curcode = String.valueOf(Integer.parseInt(manu)+1);
			while(curcode.length()<5){
				curcode="0"+curcode;
			}
			String userid =prefix + curcode;
			userinfo.setProp("userid",userid);

			/////////////////进行一些限定
			if(priLevel<4){
				userinfo.setProp("usertype","免费用户");
				userinfo.setProp("work_unit",work_unit);
				userinfo.setProp("deptid",deptid);
				
			}else{
				cachedrowset = dbtrans.getResultBySelect("select * from dept where dept_name='"+userinfo.getProp("work_unit")+"'  ");
				if(cachedrowset.next()){
					userinfo.setProp( "deptid", cachedrowset.getString("deptid"));
				}else{
					throw new BussinessProcessException("单位列表中没相应的单位");
				}
				
			}
			
				userinfo.setProp("password","123456");
			////////////////
			
			ArrayList al_col = new ArrayList();
			cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'userinfo' and isautoinc='0' order by CAST(colorder as numeric)");
			if (cachedrowset==null||cachedrowset.size()==0){
			log.info("*** === there is no relate information in tabmap!  === ***");
			throw new BussinessProcessException("no information in tabmap");
			}
			StringBuffer stringbuffer = new StringBuffer("insert into userinfo(");
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
				stringbuffer.append(userinfo.getProp(Tools.nvl(al_col.get(i).toString())));
				stringbuffer.append("'");
				bl=true;
			}
			stringbuffer.append(")");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			
			/////////////////在emp中设置绑定标识以及账号名
			stringbuffer = new StringBuffer();
			stringbuffer.append("update emp set isblind='1' , account='"+userinfo.getProp("username")+"' where empid='"+userinfo.getProp("empid")+"' ");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			
			//////////////////
						
			stringbuffer = new StringBuffer();
			stringbuffer.append("update codeset set manu='").append(curcode).append("' where tabname='userinfo' ");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();

/////////////////////////////////////////////

			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","增加");
			hp_op.put("tabname","userinfo");
			hp_op.put("cent","insert userinfo");
			syslogdao.logact(hp_op,request,dbtrans);
			
			
			dbtrans.commitTransaction();			
		}
		catch (Exception exception)
		{		
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return String.valueOf(flag);
	}

	public Query findUserinfoList(Query query,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'userinfo' order by CAST(colorder as numeric)");
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
				StringBuffer sbr_sql = new StringBuffer("select * from userinfo where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				log.info("-----====="+sbr_sql.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from userinfo where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from userinfo where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
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

	public Userinfo findUserinfoById(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Userinfo userinfo = new Userinfo();
		StringBuffer stringbuffer = new StringBuffer("select * from userinfo where id =");
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
				userinfo.setid(cachedrowset.getString("id"));
				for (int j = 1; j <= i; j++)
					userinfo.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return userinfo;
	}

	public String modifyUserinfo(Userinfo userinfo,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("update userinfo set ");
		ArrayList al_col = new ArrayList();
		try
		{
			dbtrans.beginTransaction();
		////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"userinfo");
		////以上根据tabmap开启外键！
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'userinfo' and isautoinc='0' order by CAST(colorder as numeric)");
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
			boolean thefirst = true;
			for (int i=0 ; i<al_col.size() ; i++){
				s_col = Tools.nvl((String)userinfo.getProp(al_col.get(i).toString()));
				if (s_col != null && !s_col.equals("")){
					if(thefirst){
						stringbuffer.append(al_col.get(i).toString());
						stringbuffer.append("='");
						stringbuffer.append(s_col);
						stringbuffer.append("'");
						thefirst = false;
					}else{
						stringbuffer.append(",");
						stringbuffer.append(al_col.get(i).toString());
						stringbuffer.append("='");
						stringbuffer.append(s_col);
						stringbuffer.append("'");
					}
				}
			}
			stringbuffer.append(" where id=");
			stringbuffer.append(userinfo.getid());
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag = dbtrans.executeSql();
			
		////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"userinfo",iuik);
		////以上根据tabmap关闭外键！
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","修改");
			hp_op.put("tabname","userinfo");
			hp_op.put("cent","update userinfo id ="+userinfo.getid());
			syslogdao.logact(hp_op,request,dbtrans);
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{	
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return userinfo.getid();
	}

	public String deleteUserinfo(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		String empid ="";
		boolean flag = false;
		try
		{
			dbtrans.beginTransaction();
			////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"userinfo");
			////以上根据tabmap开启外键！
		     
			 /////////////////以下 查出要删除的empid
			StringBuffer stringbuffer = new StringBuffer("select * from userinfo where id = ");
			stringbuffer.append(id).append("");
			log.info(stringbuffer.toString());
			CachedRowSet crs = dbtrans.getResultBySelect(stringbuffer.toString());
			if(crs.size()>0){
				crs.next();
				empid=crs.getString("empid");
			}else{
				throw new BussinessProcessException("===there is no information in the userinfo where id = "+id);
			}
			
			 ////////////////更新 emp 中的绑定信息
			stringbuffer = new StringBuffer("update emp set isblind='0' , account='---' where empid='"+empid+"' ");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			/////////////以上更新 emp中的绑定信息
			
			stringbuffer = new StringBuffer("delete from userinfo where id = ");
			stringbuffer.append(id).append("");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
				
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","删除");
			hp_op.put("tabname","userinfo");
			hp_op.put("cent","delete userinfo id ="+id);
			syslogdao.logact(hp_op,request,dbtrans);
			
			////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"userinfo",iuik);
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
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where 1=1 and tabname='userinfo' order by CAST(colorder as numeric)");
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
				String sql ="select * from pritab where tabname='userinfo' and role_name='"+role_name+"'";
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
	*
	*
	*/
		public void checkUserNumber(HttpServletRequest request)
		throws BussinessProcessException
	{
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");	
		String usertype = (String)hp_user.get("usertype");
		String 	work_unit = (String)hp_user.get("work_unit");
		DBTrans dbtrans = new DBTrans();
		int flag = 0;
		try{
		//计算这个登录的用户所在的单位共有多少个用户
				String sql ="select count(*) as num from userinfo where work_unit='"+work_unit+"'";
				log.info("========the sql is :"+sql);
				CachedRowSet crs = dbtrans.getResultBySelect(sql);
				if (crs.size()==0){
					flag = 1;
				}
				crs.next();
				int userNumber = Integer.parseInt(crs.getString("num"));
		//查找用户所有单位允许多少个用户
				sql ="select  dept_emp_num from dept where dept_name='"+work_unit+"'";
				log.info("========the sql is :"+sql);
				crs = dbtrans.getResultBySelect(sql);
				if (crs.size()==0){
					flag = 1;
				}
				crs.next();
				int allowUserNumber = Integer.parseInt(crs.getString("dept_emp_num"));
				if(allowUserNumber<=userNumber){
					flag = 2;
				}
				if(usertype.equals("系统管理员")){
					flag=0;
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			switch(flag){
			case 1:
				throw new BussinessProcessException("当前用户的单位不正确!");
			case 2:
				throw new BussinessProcessException("您所在单位的用户数量已经达到上限！");
			default:
				break;
			}
		}
	}
	/**
	*获得 权限值
	*
	*/
		public int getPriLevel(HttpServletRequest request)
		throws BussinessProcessException
	{
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");
		String role_name = null;
		int priLevel = 0;
		boolean flag = true;
		try{
		
				System.out.println(hp_user.toString());
			if(hp_user != null ){
				role_name 	= (String)hp_user.get("usertype");
				DBTrans dbtrans = new DBTrans();
				String sql ="select * from pritab where tabname='userinfo' and role_name='"+role_name+"'";
				log.info("========the sql is :"+sql);
				CachedRowSet crs = dbtrans.getResultBySelect(sql);
				if (crs.size()==0){
					flag = false;
					throw new Exception("");
				}
				crs.next();
				
				priLevel= Integer.parseInt(Tools.nvl(crs.getString("prilevel")));
				return priLevel;
			}
		}catch(Exception e){
			//e.printStackTrace();
		}
		if(!flag){
			throw new BussinessProcessException("权限配置不正确！");
		}
		return priLevel;
	}
}
