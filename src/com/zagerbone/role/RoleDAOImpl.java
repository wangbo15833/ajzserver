package com.zagerbone.role;

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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.zagerbone.syslog.*;
import com.zagerbone.tabmap.*;

public class RoleDAOImpl extends BasicObject
	implements RoleDAO
{

	public RoleDAOImpl()
	{
	}

	public String createRole(Role role,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		try
		{
			dbtrans.beginTransaction();
			StringBuffer stringbuffer = new StringBuffer("insert into role(");
			stringbuffer.append("role_name,role_view_right,role_all_right, role_bz");
			stringbuffer.append(")values(");
			stringbuffer.append("'").append(role.getProp("role_name")).append("',");
			stringbuffer.append("'").append(role.getProp("role_view_right")).append("',");
			stringbuffer.append("'").append(role.getProp("role_all_right")).append("',");
			stringbuffer.append("'").append(role.getProp("role_bz")).append("')");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			
			
			stringbuffer = new StringBuffer("insert pritab(role_name,tabname,tabnamec,prilevel,filter_str,listorder ) select '").append(role.getProp("role_name").replaceAll(" ","")).append("',tabname,tabnamec,prilevel,filter_str,listorder from pritab where role_name='test113' order by cast(listorder as int)");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag1 = dbtrans.executeSql();
			
			HttpSession session = request.getSession(); 
			HashMap hp_user = (HashMap)session.getAttribute("hp_user");
			String username = null;
			if(hp_user != null ){

				username 	= (String)hp_user.get("realname");
			}
	
			// dbtrans = new DBTrans();
			// stringbuffer = new StringBuffer("insert into syslog(event,e_datetime,e_operator,e_type,e_yuju) values('增加', getdate(),'"+username+"','role','insert role')");
			// log.info(stringbuffer.toString());
			// dbtrans.addSql(stringbuffer.toString());
			// flag1 = dbtrans.executeSql();
			
			// dbtrans = new DBTrans();
			// stringbuffer = new StringBuffer("insert into syslog(event,e_datetime,e_operator,e_type,e_yuju) values('增加', getdate(),'"+username+"','pritab','insert pritab')");
			// log.info(stringbuffer.toString());
			// dbtrans.addSql(stringbuffer.toString());
			// flag1 = dbtrans.executeSql();
			
			
			
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","增加");
			hp_op.put("tabname","role");
			hp_op.put("cent","insert role role_name ="+role.getProp("role_name"));
			syslogdao.logact(hp_op,request,dbtrans);
			
			hp_op = new HashMap();
			hp_op.put("event","增加");
			hp_op.put("tabname","pritab");
			hp_op.put("cent","insert pritab role_name ="+role.getProp("role_name"));
			syslogdao.logact(hp_op,request,dbtrans);
			
			dbtrans.commitTransaction();			
		}
		catch (Exception exception)
		{
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return "create role";
	}

	public Query findRoleList(Query query,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		HashMap hashmap = (HashMap)query.getConditionBody();
		try
		{
			String id 				= Tools.nvl((String)hashmap.get("id"));
			String role_name 		= Tools.nvl((String)hashmap.get("role_name"));
			String role_view_right 	= Tools.nvl((String)hashmap.get("role_view_right"));
			String role_all_right 	= Tools.nvl((String)hashmap.get("role_all_right"));
			String role_bz 			= Tools.nvl((String)hashmap.get("role_bz"));
			log.info((new StringBuilder()).append("条件体").toString());
			log.info((new StringBuilder()).append("id ===").append(id).toString());
			log.info((new StringBuilder()).append("role_name ===").append(role_name).toString());
			log.info((new StringBuilder()).append("role_view_right ===").append(role_view_right).toString());
			log.info((new StringBuilder()).append("role_all_right ===").append(role_all_right).toString());
			log.info((new StringBuilder()).append("role_bz ===").append(role_bz).toString());
			log.info((new StringBuilder()).append("queryCon.getTotalNum() ===").append(query.getTotalNum()).toString());
		
			StringBuffer sbr_condition = new StringBuffer();
			if (id != null && !id.equals(""))
			{
				sbr_condition.append(" and id ='");
				sbr_condition.append(id).append("'");
			}
			if (role_name != null && !role_name.equals(""))
			{
				sbr_condition.append(" and role_name ='");
				sbr_condition.append(role_name).append("'");
			}
			if (role_view_right != null && !role_view_right.equals(""))
			{
				sbr_condition.append(" and role_view_right = '");
				sbr_condition.append(role_view_right).append("'");
			}
			if (role_all_right != null && !role_all_right.equals(""))
			{
				sbr_condition.append(" and role_all_right = '");
				sbr_condition.append(role_all_right).append("'");
			}
			if (role_bz != null && !role_bz.equals(""))
			{
				sbr_condition.append(" and role_bz = '");
				sbr_condition.append(role_bz).append("'");
			}
			if (query.getTotalNum() == 0)
			{
				StringBuffer sbr_sql = new StringBuffer("select * from role where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from role where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from role where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
			stringbuffer1.append(sbr_condition.toString());
			stringbuffer1.append("  order by id desc  ");
			query.setSql(stringbuffer1.toString());
			log.info(stringbuffer1.toString());
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(stringbuffer1.toString());
			query.setConditionBody(cachedrowset);
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return query;
	}

	public String modifyRole(Role role,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("update role set ");
		stringbuffer.append("role_name='").append(role.getProp("role_name")).append("',");
		stringbuffer.append("role_view_right='").append(role.getProp("role_view_right")).append("',");
		stringbuffer.append("role_all_right='").append(role.getProp("role_all_right")).append("',");
		stringbuffer.append("role_bz='").append(role.getProp("role_bz")).append("' where id = '");
		stringbuffer.append(role.getid()).append("'");
		log.info(stringbuffer.toString());
		try
		{
			dbtrans.beginTransaction();
			dbtrans.addSql(stringbuffer.toString());
			boolean flag1 = dbtrans.executeSql();
			
			HttpSession session = request.getSession(); 
			HashMap hp_user = (HashMap)session.getAttribute("hp_user");
			String username = null;
			if(hp_user != null ){
				username 	= (String)hp_user.get("realname");
			}
			
			
			// dbtrans = new DBTrans();
			// stringbuffer = new StringBuffer("insert into syslog(event,e_datetime,e_operator,e_type,e_yuju) values('修改', getdate(),'"+username+"','role','update id="+role.getid()+"')");
			// log.info(stringbuffer.toString());
			// dbtrans.addSql(stringbuffer.toString());
			// flag1 = dbtrans.executeSql();
			
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","更新");
			hp_op.put("tabname","role");
			hp_op.put("cent","update role id ="+role.getid());
			syslogdao.logact(hp_op,request,dbtrans);
			
			dbtrans.commitTransaction();	
			
		}
		catch (Exception exception)
		{		
			
			dbtrans.rollbackTransaction();
			throw new BussinessProcessException(exception.getMessage());
		}
		return role.getid();
	}

	public String deleteRole(String id,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer("delete from role where id = ");
		stringbuffer.append(id).append("");
		try
		{
			dbtrans.beginTransaction();
			if (Integer.parseInt(id)>3)
			{
				dbtrans.addSql(stringbuffer.toString());
				boolean flag1 = dbtrans.executeSql();
			
			
			HttpSession session = request.getSession(); 
			HashMap hp_user = (HashMap)session.getAttribute("hp_user");
			String username = null;
			if(hp_user != null ){

				username 	= (String)hp_user.get("realname");
			}
			
			/////////////也删除pritab中相关的信息
			
			/* 
			StringBuffer sbr=new StringBuffer();
			sbr.append("select * from role where id=").append(id);
			log.info(sbr.toString()+"'");
			CachedRowSet cachedrowset = dbtrans.getResultBySelect(sbr.toString());
			String role_name="";
			log.info("11111111111111111111111:::"+cachedrowset.size());
			if (cachedrowset.size()==0){
				throw new BussinessProcessException("角色表中没有相关的信息 there is no information in the table of role");
			}else{
				cachedrowset.next();
				role_name=cachedrowset.getString("role_name");
			}
			sbr = new StringBuffer("delete from pritab where role_name='").append(role_name).append("'");
			log.info(sbr.toString());
			dbtrans.addSql(sbr.toString());
			flag = dbtrans.executeSql();
			 */
			/////////////也删除pritab中相关的信息
			// dbtrans = new DBTrans();
			// stringbuffer = new StringBuffer("insert into syslog(event,e_datetime,e_operator,e_type,e_yuju) values('删除', getdate(),'"+username+"','role','delete id="+id+"')");
			// log.info(stringbuffer.toString());
			// dbtrans.addSql(stringbuffer.toString());
			// flag = dbtrans.executeSql();
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","删除");
			hp_op.put("tabname","role");
			hp_op.put("cent","delete role id ="+id);
			syslogdao.logact(hp_op,request,dbtrans);
			}
			
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
	
}
