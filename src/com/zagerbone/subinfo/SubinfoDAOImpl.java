package com.zagerbone.subinfo;

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

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.*;

public class SubinfoDAOImpl extends BasicObject
	implements SubinfoDAO
{

	public SubinfoDAOImpl()
	{
	}

	public String createSubinfo(Subinfo subinfo,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag = false;
		StringBuffer stringbuffer = new StringBuffer();
		try
		{	
			dbtrans.beginTransaction();
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select * from codeset where tabname='subinfo' ");
			cachedrowset.next();
			String prefix = cachedrowset.getString("prefix");
			String manu = cachedrowset.getString("manu");
			String curcode = String.valueOf(Integer.parseInt(manu)+1);
			while(curcode.length()<5){
				curcode="0"+curcode;
			}
			String sub_code =prefix + curcode;
			subinfo.setProp("sub_code",sub_code);
		
			ArrayList al_col = new ArrayList();
			Map<String,String> mp_en = new HashMap<String , String >(subinfo.getHashMap());
			StringBuffer sbr_sql = new StringBuffer();
			StringBuffer sbr_sql2 = new StringBuffer();
			StringBuffer sbr_sql2_key = new StringBuffer();
			StringBuffer sbr_sql2_values = new StringBuffer();
			
			sbr_sql2.append("insert subinfo(");
			String entabcol ="sub_code;bacode;gcname;gcdz;jzmj;jglx;jgcs;gczj;jzlb;gcgs;zbsafelic;jclx;kgsj;fbsafelic;jgsj;latitude;longtitude";
			String assocol="sj_js_ph=&jsdw=&fb_aq_zc_4=&fb_aq_zc_3=&fb_aq_zc_2=&fb_aq_zc_1=&sgzb_xm_ph=&js_fr_ph=&sgzb_js_name=&sgfb_js_name=&js_xm_zs=&jl_xm_ph=&sj_fr_zs=&kc_fr_name=&jl_js_name=&js_js_ph=&sj_fr_name=&sgfb_fr_ph=&sgfb_xm_zs=&sgfb_xm_name=dfg&sgzb_xm_name=&sj_js_zs=&sgzb_fr_ph=&sgzb_xm_zs=&js_fr_name=&js_fr_zs=&jl_fr_ph=&jl_xm_zs=&zb_aq_ph_4=&zb_aq_ph_3=&zb_aq_ph_2=dfg&zb_aq_ph_1=&sgzb_fr_zsbh=&sgfb_fr_zsbh=&jl_fr_zsbh=&sgfb_js_ph=&zb_aq_name_4=&zb_aq_name_3=&jl_xm_name=&zb_aq_name_2=&zb_aq_name_1=&zb_aq_gen_4=&kc_js_name=&zb_aq_gen_3=&zb_aq_gen_2=&zb_aq_gen_1=dfg&sgzb_js_ph=&fb_aq_name_4=&fb_aq_name_3=dfg&zj_gcs_ph=&fb_aq_name_2=&js_js_zs=&fb_aq_name_1=dfg&fb_aq_ph_4=&jl_js_ph=&fb_aq_ph_3=&fb_aq_ph_2=&fb_aq_ph_1=&sgfb_fr_zs=&barq=&sgzb_fr_zs=&sg_xmjl_id=&sgfb_js_zsbh=&sj_js_name=&sgzb_js_zsbh=&kc_xm_name=&kcdw=&jl_fr_zs=&sgfbdw=&slr=&sgfb_js_zs=&js_js_name=&zj_gcs_zz=&kc_fr_zsbh=&sgzb_js_zs=&jl_js_zsbh=&sj_fr_zsbh=&jl_js_zs=&sgzb_xm_zsbh=&kc_xm_ph=&sgfb_xm_zsbh=&sj_xm_name=&js_fr_zsbh=&js_xm_name=&sjdw=&jl_xm_zsbh=&kc_js_zsbh=&jldw=&kc_fr_ph=&kc_xm_zs=&sj_js_zsbh=&fb_aq_gen_4=&fb_aq_gen_3=&kc_xm_zsbh=&fb_aq_gen_2=&fb_aq_gen_1=&zb_aq_zy_4=&kc_js_ph=&zb_aq_zy_3=&sgzbdw=&zb_aq_zy_2=&zb_aq_zy_1=&js_js_zsbh=&sj_xm_ph=&sg_xmjl_ph=&zb_aq_khzh_4=&zb_aq_khzh_3=&zb_aq_khzh_2=&zb_aq_khzh_1=&sj_xm_zsbh=&kc_fr_zs=&fb_aq_zy_4=&fb_aq_zy_3=&fb_aq_zy_2=&fb_aq_zy_1=&fb_aq_khzh_4=&fb_aq_khzh_3=&fb_aq_khzh_2=&fb_aq_khzh_1=&zj_gcs_name=&js_xm_zsbh=&zj_gcs_id=&js_xm_ph=&kc_js_zs=&sgfb_fr_name=&sgzb_fr_name=&jl_fr_name=dfg&sj_fr_ph=&sg_xmjl_zz=&sj_xm_zs=&zb_aq_zc_4=&zb_aq_zc_3=&zb_aq_zc_2=&zb_aq_zc_1=dfg&sg_xmjl_name=&sgfb_xm_ph=";
			boolean isfirst = true;
			for (String key : mp_en.keySet()){
				if(entabcol.indexOf(key)>-1){
					if(isfirst){
						sbr_sql2_key.append(key);
						sbr_sql2_values.append("'").append(mp_en.get(key)).append("'");
						isfirst=false;
					}else{
						sbr_sql2_key.append(",").append(key);
						sbr_sql2_values.append(",'").append(mp_en.get(key)).append("'");
					}
				}else if(assocol.indexOf(key)>-1){
					sbr_sql.append("insert subinfoasso(sub_code,parname,parvalue) values('").append(sub_code);
					sbr_sql.append("','").append(key);
					System.out.println("========key is :"+key);
					sbr_sql.append("','").append(mp_en.get(key)).append("')");
					
					log.info(sbr_sql.toString());
					dbtrans.addSql(sbr_sql.toString());
					flag = dbtrans.executeSql();
					sbr_sql = new StringBuffer();
				}
			}
			
			
			sbr_sql2.append(sbr_sql2_key.toString()).append(") values(").append(sbr_sql2_values.toString()).append(")");
			log.info(sbr_sql2.toString());
			dbtrans.addSql(sbr_sql2.toString());
			flag = dbtrans.executeSql();
			
			sbr_sql = new StringBuffer();
			stringbuffer.append("update codeset set manu='").append(curcode).append("' where tabname='subinfo' ");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			
			/* 
			cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'subinfo' and isautoinc='0' order by CAST(colorder as numeric)");
			if (cachedrowset==null||cachedrowset.size()==0){
			log.info("*** === there is no relate information in tabmap!  === ***");
			throw new BussinessProcessException("no information in tabmap");
			}
			StringBuffer stringbuffer = new StringBuffer("insert into subinfo(");
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
				stringbuffer.append(subinfo.getProp(Tools.nvl(al_col.get(i).toString())));
				stringbuffer.append("'");
				bl=true;
			}
			stringbuffer.append(")");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql(); */
			
			
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","增加");
			hp_op.put("tabname","subinfo");
			hp_op.put("cent","insert subinfo");
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

	public Query findSubinfoList(Query query,HttpServletRequest request)
		throws BussinessProcessException
	{
	
		DBTrans dbtrans = new DBTrans();
		Object obj = null;
		HashMap hashmap = (HashMap)query.getConditionBody();
		ArrayList al_col = new ArrayList();
		try
		{
			CachedRowSet cachedrowset = dbtrans.getResultBySelect("select colname from tabmap where tabname = 'subinfo' order by CAST(colorder as numeric)");
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
			
			
/* 			for (int i=0 ; i<al_col.size() ; i++){
				s_col = Tools.nvl((String)hashmap.get(al_col.get(i).toString()));
				if (s_col != null && !s_col.equals("")){
					sbr_condition.append(" and ");
					sbr_condition.append(al_col.get(i).toString());
					sbr_condition.append("='");
					sbr_condition.append(s_col);
					sbr_condition.append("'");					
				}
			
			} */
			if (query.getTotalNum() == 0)
			{
				StringBuffer sbr_sql = new StringBuffer("select * from subinfo where 1=1 ");
				sbr_sql.append(sbr_condition.toString());
				log.info("-----====="+sbr_sql.toString());
				query.setTotalNum(dbtrans.getRecNumBySelect(sbr_sql.toString()));
			}
			if (query.getCurrentPageNum() <= 0)
				query.setCurrentPageNum(1);
			int i = query.getRowsPerPage() * (query.getCurrentPageNum() - 1);
			StringBuffer stringbuffer1 = new StringBuffer();
			stringbuffer1.append("select top ").append(query.getRowsPerPage()).append(" * from subinfo where id not in ");
			stringbuffer1.append("(select top ").append(i).append(" id from subinfo where 1=1 ").append(sbr_condition.toString()).append(" order by id desc ) ");
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

	public String findSubinfoById(String sub_code,HttpServletRequest request)
		throws BussinessProcessException
	{
		try
		{
			DBTrans dbtrans = new DBTrans();
			CachedRowSet crs = dbtrans.getResultBySelect("select * from subinfo where sub_code = '"+sub_code+"'");
			CachedRowSet crs2 = dbtrans.getResultBySelect("select * from subinfoasso where sub_code = '"+sub_code+"'");
			CachedRowSet crs_xmzg = dbtrans.getResultBySelect("select * from xmzg where xmcode = '"+sub_code+"'");
			request.setAttribute("crs",crs);
			request.setAttribute("crs2",crs2);
			request.setAttribute("crs_xmzg",crs_xmzg);
		}
		catch (Exception exception)
		{
			throw new BussinessProcessException(exception.getMessage());
		}
		return "1";
	}

	public String modifySubinfo(Subinfo subinfo,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		boolean flag =false;
		try
		{
			dbtrans.beginTransaction();
		////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"subinfo");
		////以上根据tabmap开启外键！
		
		/////////////////////
			dbtrans.beginTransaction();

			//删除原来项目信息
			dbtrans.addSql("delete from subinfo where sub_code ='"+(String)subinfo.getProp("sub_code")+"'");
			flag = dbtrans.executeSql();
			dbtrans.addSql("delete from subinfoasso where sub_code ='"+(String)subinfo.getProp("sub_code")+"'");
			flag = dbtrans.executeSql();
			//删除原来项目信息
			
			Map<String,String> mp_en = new HashMap<String , String >(subinfo.getHashMap());
			StringBuffer sbr_sql = new StringBuffer();
			StringBuffer sbr_sql2 = new StringBuffer();
			StringBuffer sbr_sql2_key = new StringBuffer();
			StringBuffer sbr_sql2_values = new StringBuffer();
			
			
			
			sbr_sql2.append("insert subinfo(");
			String entabcol ="sub_code;bacode;gcname;gcdz;jzmj;jglx;jgcs;gczj;jzlb;gcgs;zbsafelic;jclx;kgsj;fbsafelic;jgsj;latitude;longtitude";
			String assocol="sj_js_ph=&jsdw=&fb_aq_zc_4=&fb_aq_zc_3=&fb_aq_zc_2=&fb_aq_zc_1=&sgzb_xm_ph=&js_fr_ph=&sgzb_js_name=&sgfb_js_name=&js_xm_zs=&jl_xm_ph=&sj_fr_zs=&kc_fr_name=&jl_js_name=&js_js_ph=&sj_fr_name=&sgfb_fr_ph=&sgfb_xm_zs=&sgfb_xm_name=dfg&sgzb_xm_name=&sj_js_zs=&sgzb_fr_ph=&sgzb_xm_zs=&js_fr_name=&js_fr_zs=&jl_fr_ph=&jl_xm_zs=&zb_aq_ph_4=&zb_aq_ph_3=&zb_aq_ph_2=dfg&zb_aq_ph_1=&sgzb_fr_zsbh=&sgfb_fr_zsbh=&jl_fr_zsbh=&sgfb_js_ph=&zb_aq_name_4=&zb_aq_name_3=&jl_xm_name=&zb_aq_name_2=&zb_aq_name_1=&zb_aq_gen_4=&kc_js_name=&zb_aq_gen_3=&zb_aq_gen_2=&zb_aq_gen_1=dfg&sgzb_js_ph=&fb_aq_name_4=&fb_aq_name_3=dfg&zj_gcs_ph=&fb_aq_name_2=&js_js_zs=&fb_aq_name_1=dfg&fb_aq_ph_4=&jl_js_ph=&fb_aq_ph_3=&fb_aq_ph_2=&fb_aq_ph_1=&sgfb_fr_zs=&barq=&sgzb_fr_zs=&sg_xmjl_id=&sgfb_js_zsbh=&sj_js_name=&sgzb_js_zsbh=&kc_xm_name=&kcdw=&jl_fr_zs=&sgfbdw=&slr=&sgfb_js_zs=&js_js_name=&zj_gcs_zz=&kc_fr_zsbh=&sgzb_js_zs=&jl_js_zsbh=&sj_fr_zsbh=&jl_js_zs=&sgzb_xm_zsbh=&kc_xm_ph=&sgfb_xm_zsbh=&sj_xm_name=&js_fr_zsbh=&js_xm_name=&sjdw=&jl_xm_zsbh=&kc_js_zsbh=&jldw=&kc_fr_ph=&kc_xm_zs=&sj_js_zsbh=&fb_aq_gen_4=&fb_aq_gen_3=&kc_xm_zsbh=&fb_aq_gen_2=&fb_aq_gen_1=&zb_aq_zy_4=&kc_js_ph=&zb_aq_zy_3=&sgzbdw=&zb_aq_zy_2=&zb_aq_zy_1=&js_js_zsbh=&sj_xm_ph=&sg_xmjl_ph=&zb_aq_khzh_4=&zb_aq_khzh_3=&zb_aq_khzh_2=&zb_aq_khzh_1=&sj_xm_zsbh=&kc_fr_zs=&fb_aq_zy_4=&fb_aq_zy_3=&fb_aq_zy_2=&fb_aq_zy_1=&fb_aq_khzh_4=&fb_aq_khzh_3=&fb_aq_khzh_2=&fb_aq_khzh_1=&zj_gcs_name=&js_xm_zsbh=&zj_gcs_id=&js_xm_ph=&kc_js_zs=&sgfb_fr_name=&sgzb_fr_name=&jl_fr_name=dfg&sj_fr_ph=&sg_xmjl_zz=&sj_xm_zs=&zb_aq_zc_4=&zb_aq_zc_3=&zb_aq_zc_2=&zb_aq_zc_1=dfg&sg_xmjl_name=&sgfb_xm_ph=";
			boolean isfirst = true;
			for (String key : mp_en.keySet()){
				if(entabcol.indexOf(key)>-1){
					if(isfirst){
						sbr_sql2_key.append(key);
						sbr_sql2_values.append("'").append(mp_en.get(key)).append("'");
						isfirst=false;
					}else{
						sbr_sql2_key.append(",").append(key);
						sbr_sql2_values.append(",'").append(mp_en.get(key)).append("'");
					}
				}else if(assocol.indexOf(key)>-1){
					sbr_sql.append("insert subinfoasso(sub_code,parname,parvalue) values('").append((String)subinfo.getProp("sub_code"));
					sbr_sql.append("','").append(key);
					System.out.println("========key is :"+key);
					sbr_sql.append("','").append(mp_en.get(key)).append("')");
					
					log.info(sbr_sql.toString());
					dbtrans.addSql(sbr_sql.toString());
					flag = dbtrans.executeSql();
					sbr_sql = new StringBuffer();
				}
			}
			
			
			sbr_sql2.append(sbr_sql2_key.toString()).append(") values(").append(sbr_sql2_values.toString()).append(")");
			log.info(sbr_sql2.toString());
			dbtrans.addSql(sbr_sql2.toString());
			flag = dbtrans.executeSql();

		//////////////////////

		////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"subinfo",iuik);
		////以上根据tabmap关闭外键！
			
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","修改");
			hp_op.put("tabname","subinfo");
			hp_op.put("cent","update subinfo id ="+subinfo.getid());
			syslogdao.logact(hp_op,request,dbtrans);
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{	
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return subinfo.getid();
	}

	public String deleteSubinfo(String sub_code,HttpServletRequest request)
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("delete from subinfo where sub_code = '");
		stringbuffer.append(sub_code).append("'");
		boolean flag = false;
		
		try
		{
			dbtrans.beginTransaction();
			////以下根据tabmap开启外键！
			TabmapDAO tabmapdao = TabmapDAOFactory.getDAO();
			String iuik = tabmapdao.setcascade(dbtrans,"subinfo");
			////以上根据tabmap开启外键！
		
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
			
			
			stringbuffer = new StringBuffer("delete from subinfoasso where sub_code = '");
			stringbuffer.append(sub_code).append("'");
			log.info(stringbuffer.toString());
			dbtrans.addSql(stringbuffer.toString());
			flag = dbtrans.executeSql();
				
			SyslogDAO syslogdao = SyslogDAOFactory.getDAO();
			HashMap hp_op = new HashMap();
			hp_op.put("event","删除");
			hp_op.put("tabname","subinfo");
			hp_op.put("cent","delete subinfo sub_code ="+sub_code);
			syslogdao.logact(hp_op,request,dbtrans);
			
			////以下根据tabmap关闭外键！
			tabmapdao.remcascade(dbtrans,"subinfo",iuik);
			////以上根据tabmap关闭外键！
			dbtrans.commitTransaction();
			
		}
		catch (Exception exception)
		{
			dbtrans.rollbackTransaction();
			exception.printStackTrace();
			throw new BussinessProcessException(exception.getMessage());
		}
		return sub_code;
	}
	
	public CachedRowSet getAllCol()
		throws BussinessProcessException
	{
		DBTrans dbtrans = new DBTrans();
		StringBuffer stringbuffer = new StringBuffer("select * from tabmap where 1=1 and tabname='subinfo' order by CAST(colorder as numeric)");
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
				String sql ="select * from pritab where tabname='subinfo' and role_name='"+role_name+"'";
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
