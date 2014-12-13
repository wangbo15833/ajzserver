package com.zagerbone.emp;

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

public class EmpDAOImpl extends BasicObject
	implements EmpDAO
{

	public EmpDAOImpl()
	{
	}

	public String createEmp(Emp emp,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		boolean bl =false;
		CachedRowSet cachedrowset =null;
		int pri =0;
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");
		try
		{
			String prefix = null;
			String manu =   null;
			String curcode =null;
//查出给人员分配的编号  EMP-XXXX
			cachedrowset = dbtrans.getResultBySelect("select * from codeset where tabname='emp' ");
			cachedrowset.next();
			prefix = cachedrowset.getString("prefix");
			manu = cachedrowset.getString("manu");
			curcode = String.valueOf(Integer.parseInt(manu)+1);
			while(curcode.length()<5){
				curcode="0"+curcode;
			}
			String empid =prefix + curcode;
			emp.setProp("empid",empid);
//获得emp表的权限等级
			pri = getPriLevel(request);
			if (pri<4){
				emp.setProp("emp_dept",(String)(hp_user.get("work_unit")));
				emp.setProp("deptid",(String)(hp_user.get("deptid")));
				emp.setProp("emp_role","免费用户");
			}else if (pri==4){
				//emp.setProp("deptid",getDeptIdByName((String)emp.getProp("emp_dept")));
				log.info("adminstrator insert emp emp_dept is :"+(String)emp.getProp("emp_dept"));
				CachedRowSet crs = dbtrans.getResultBySelect("select * from dept where dept_name = '"+(String)emp.getProp("emp_dept")+"' ");
				if(crs.next()){
					//emp.setProp("deptid",getDeptIdByName(crs.getString("deptid")));
					emp.setProp("deptid",crs.getString("deptid"));
				}
			}
			emp.setProp("isblind","0");
			ArrayList al_col = new ArrayList();
			cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'emp' and isautoinc='0' order by CAST(colorder as numeric)");
			if (cachedrowset==null||cachedrowset.size()==0){
			log.info("*** === there is no relate information in tabmap!  === ***");
			throw new BussinessProcessException("no information in tabmap");
			}
			StringBuffer stringbuffer = new StringBuffer("insert into emp(");
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
				stringbuffer.append(emp.getProp(Tools.nvl(al_col.get(i).toString())));
				stringbuffer.append("'");
				bl=true;
			}
			stringbuffer.append(")");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
//更新编号产生表	
			stringbuffer = new StringBuffer();
			stringbuffer.append("update codeset set manu='").append(curcode).append("' where tabname='emp' ");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
////////////////////////////////////////////////////////////////			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","增加");
			hp_op.put("tabname","emp");
			hp_op.put("cent","insert "+empid);
			syslogdao.logact(hp_op,request,dbtrans);
			
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return String.valueOf(flag);
	}

	public Query findEmpList(Query query,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'emp' order by CAST(colorder as numeric)");
			if (cachedrowset==null||cachedrowset.size()==0){
				log.info("*** === there is no relate information in tabmap!  === ***");
				throw new BussinessProcessException("no information in tabmap");
			}
			while(cachedrowset.next()){
				al_col.add(cachedrowset.getString("colname"));
			}
			cachedrowset.close();
			StringBuffer sbr_condition = new StringBuffer();
			String col_v = null;
			for (int i=0 ; i<al_col.size() ; i++){
				col_v = Tools.nvl((String)hashmap.get(al_col.get(i).toString()));
				if (col_v != null && !col_v.equals("")){
					sbr_condition.append(" and ");
					sbr_condition.append(al_col.get(i).toString());
					sbr_condition.append("='");
					sbr_condition.append(col_v);
					sbr_condition.append("'");					
				}
			
			}
			if (query.getTotalNum() == 0)
			{
				StringBuffer sbr_sql = new StringBuffer("select * from emp where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				log.info("-----====="+sbr_sql.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from emp where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from emp where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
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
	
	/**
	*用户查看自己的资料
	*/
	public Query findMyProfile(Query query,HttpServletRequest request)
		throws BussinessProcessException
		{
		
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");
		DBTrans dbtrans = new DBTrans();
		boolean flag = true;
		int pri =0;
		try{
			CachedRowSet crs = null;
			if(hp_user != null ){
				String empid 	= (String)hp_user.get("empid");
				String sql ="select * from emp where empid='"+empid+"'";
				log.info("========the sql is :"+sql);
				crs = dbtrans.getResultBySelect(sql);
				if (crs.size()!=1){
					throw new BussinessProcessException("See MyProfile Err, there is an error in current emp table！");
				}
			}else{
				throw new BussinessProcessException("session is not exist, please relogin！");
			}
			query.setConditionBody(crs);
		}catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return query;
	}
	
	public Emp findEmpById(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTransUtil dbtransutil = new DBTransUtil();
		Object obj = null;
		Emp emp = new Emp();
		StringBuffer stringbuffer = new StringBuffer("select * from emp where id =");
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
				emp.setid(cachedrowset.getString("id"));
				for (int j = 1; j <= i; j++)
					emp.setProp(resultsetmetadata.getColumnName(j).toLowerCase(), Tools.nvl(cachedrowset.getString(j)));
			}
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return emp;
	}

	public String modifyEmp(Emp emp,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("update emp set ");
		ArrayList al_col = new ArrayList();
		try
		{
			dbtrans.beginTransaction();
		////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"emp");
		////以上根据tabmap开启外键！
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'emp' and isautoinc='0' order by CAST(colorder as numeric)");
			if (cachedrowset==null||cachedrowset.size()==0){
				log.info("*** === there is no relate information in tabmap!  === ***");
				throw new BussinessProcessException("no information in tabmap");
			}
			while(cachedrowset.next()){
			if(cachedrowset.getString("colname").equals("id")){}else{
				al_col.add(cachedrowset.getString("colname"));
				}
			}
			log.info("the al_col is the col of emp in the tabmap! it's :"+ al_col.toString());
			cachedrowset.close();
			String col_v = null;
			String col_k = null;
			boolean firstCon = true;
			for (int i=0 ; i<al_col.size() ; i++){
				col_k = al_col.get(i).toString();
				log.info("current col_k is:"+col_k);
				col_v = Tools.nvl((String)emp.getProp(col_k));
				if (col_v != null && !col_v.equals("")){
					if(firstCon){
						stringbuffer.append(col_k);
						stringbuffer.append("='");
						stringbuffer.append(col_v);
						stringbuffer.append("'");
						firstCon = false;
					}else{
						stringbuffer.append(",");
						stringbuffer.append(col_k);
						stringbuffer.append("='");
						stringbuffer.append(col_v);
						stringbuffer.append("'");
					}
				}
			}
			stringbuffer.append(" where id=");
			stringbuffer.append(emp.getid());
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag = dbtrans.executeSql();
			
		////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"emp",iuik);
		////以上根据tabmap关闭外键！
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","修改");
			hp_op.put("tabname","emp");
			hp_op.put("cent","update emp id ="+emp.getid());
			syslogdao.logact(hp_op,request,dbtrans);
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{	
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return emp.getid();
	}

	public String deleteEmp(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		int i = Integer.parseInt(id);
		StringBuffer stringbuffer = new StringBuffer("delete from emp where id = ");
		stringbuffer.append(i).append("");
		
		try
		{
			dbtrans.beginTransaction();
			////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"emp");
			////以上根据tabmap开启外键！
		
			log.info(stringbuffer.toString());
				dbtrans.addSql(stringbuffer.toString());
				boolean flag = dbtrans.executeSql();
				
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","删除");
			hp_op.put("tabname","emp");
			hp_op.put("cent","delete emp id ="+id);
			syslogdao.logact(hp_op,request,dbtrans);
			
			////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"emp",iuik);
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
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where 1=1 and tabname='emp' order by CAST(colorder as numeric)");
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
				String sql ="select * from pritab where tabname='emp' and role_name='"+role_name+"'";
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
	*获取当前用户对emp表的权限等级
	*
	*/
	public int getPriLevel(HttpServletRequest request){
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");
		boolean flag = true;
		int pri =0;
		try{
			if(hp_user != null ){
				String role_name 	= (String)hp_user.get("usertype");
				DBTrans dbtrans = new DBTrans();
				String sql ="select * from pritab where tabname='emp' and role_name='"+role_name+"'";
				log.info("========the sql is :"+sql);
				CachedRowSet crs = dbtrans.getResultBySelect(sql);
				if (crs.size()==0){
					flag = false;
					throw new Exception("在权限表中没有查到\""+role_name+"\"的相关信息");
				}
				crs.next();
				pri = Integer.parseInt(Tools.nvl((String)crs.getString("prilevel")));
				return pri;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pri;
	}
	
	
	/**
	*根据单位名称取得单位编号
	*
	*/
	public String getDeptIdByName(String deptname){
		String deptid ="";
		try{
		DBTrans dbtrans = new DBTrans();
				String sql ="select deptid from dept where dept_name='"+deptname+"'";
				log.info("========the sql is :"+sql);
				CachedRowSet crs = dbtrans.getResultBySelect(sql);
				if (crs.size()==0){
					throw new Exception("在单位表中没有查到\""+deptname+"\"的相关信息");
				}
				crs.next();
				deptid = Tools.nvl((String)crs.getString("deptid"));
				return deptid;
		}catch(Exception e){
			e.printStackTrace();
		}
		return deptid;
	}
	
	/**
	*修改自已的基本资料
	*/
	public String modifyEmpSel(Emp emp,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");
		ArrayList al_col = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		try
		{
			dbtrans.beginTransaction();
		////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"emp");
		////以上根据tabmap开启外键！
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'emp' and isautoinc='0' order by CAST(colorder as numeric)");
			if (cachedrowset==null||cachedrowset.size()==0){
				log.info("*** === there is no relate information in tabmap!  === ***");
				throw new BussinessProcessException("no information in tabmap");
			}
			while(cachedrowset.next()){
			if(cachedrowset.getString("colname").equals("id")){}else{
				al_col.add(cachedrowset.getString("colname"));
				}
			}
			log.info("the al_col is the col of emp in the tabmap! it's :"+ al_col.toString());
			cachedrowset.close();
			String col_v = null;
			String col_k = null;
			boolean firstCon = true;
			
			//不能修改的字段
			String hideSeg = "isblind;emp_role;account;imsi;emp_lb;empid;deptid;emp_dept";
			for (int i=0 ; i<al_col.size() ; i++){
				col_k = al_col.get(i).toString();
				col_v = Tools.nvl((String)emp.getProp(col_k));
				//排出一些不能修改的字段
				if(hideSeg.indexOf(col_k)>-1){
					continue;
				}
				if (col_v != null && !col_v.equals("")){
					if(firstCon){
						stringbuffer.append(col_k);
						stringbuffer.append("='");
						stringbuffer.append(col_v);
						stringbuffer.append("'");
						firstCon = false;
					}else{
						stringbuffer.append(",");
						stringbuffer.append(col_k);
						stringbuffer.append("='");
						stringbuffer.append(col_v);
						stringbuffer.append("'");
					}
				}
			}
			stringbuffer.append(" where empid='");
			stringbuffer.append((String)hp_user.get("empid"));
			stringbuffer.append("' ");
			log.info(stringbuffer.toString());
			dbtrans.addSql("update emp set "+stringbuffer.toString());
			boolean flag = dbtrans.executeSql();
			
		////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"emp",iuik);
		////以上根据tabmap关闭外键！
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","修改个人资料");
			hp_op.put("tabname","emp");
			hp_op.put("cent","emp_name ="+emp.getProp("emp_name"));
			syslogdao.logact(hp_op,request,dbtrans);
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{	
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return emp.getid();
	}
	
	
	/**
	*修改用户密码
	*/
	public String empChangePsw(Emp emp,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		HttpSession session = request.getSession(); 
		HashMap hp_user = (HashMap)session.getAttribute("hp_user");
		String userid = hp_user.get("userid").toString();
		try
		{
			dbtrans.beginTransaction();
			if(!(hp_user.get("password").toString()).equals(emp.getProp("oldpsw"))){
				throw new BussinessProcessException("原始密码输入不正确！");
			}
			if(!(emp.getProp("newpsw1").equals(emp.getProp("newpsw2")))){
				
				throw new BussinessProcessException("两次输入的新密码不一致！");
			}
			String sql = "update userinfo set password='"+emp.getProp("newpsw1")+"' where userid='"+userid+"'";
			log.info(sql);
			dbtrans.addSql(sql);
			boolean flag = dbtrans.executeSql();
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","修改个人登录密码");
			hp_op.put("tabname","userinfo");
			hp_op.put("cent","realname ="+hp_user.get("realname").toString());
			syslogdao.logact(hp_op,request,dbtrans);
			
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{	
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return emp.getid();
	}
}
