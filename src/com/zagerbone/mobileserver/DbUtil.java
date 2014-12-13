package com.zagerbone.mobileserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sun.jdbc.rowset.CachedRowSet;


/**
 * 这是中转服务器对数据库的操作
 * @author 李振南
 * 连接mssql首先导入三个JAR包 msbase.jar mssqlserver.jar msutil.jar 
 */
public class DbUtil {
	private static Logger log;
	Connection con=null;
	Statement sql=null;
	ResultSet rs=null;
	final String driver="com.microsoft.jdbc.sqlserver.SQLServerDriver";
 	final String uri="jdbc:sqlserver://localhost:1433;DatabaseName=ajz";
 	public DbUtil(){
 		try {
			Class.forName(driver);
			con=DriverManager.getConnection(uri, "weixun", "aJz456$%^");
			sql=con.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
 	}
 	private CachedRowSet getResultBySelect(String s)
 		{
 			CachedRowSet cachedrowset=null;
 			try
 			{	
 				cachedrowset=new CachedRowSet();
	 			System.out.println("***===current sql is==="+s);
	 			rs = sql.executeQuery(s);
	 			cachedrowset.populate(rs);
	 			System.out.println("the crs.size() is :"+cachedrowset.size());
 			}
 			catch (SQLException sqle) { 
 				System.out.println((new StringBuilder()).append("getResultBySelect出现错误 : ").append(sqle.getMessage()).toString());
 			}
 			catch (Exception ex) {
 					ex.printStackTrace();
 			}
 				return cachedrowset;

 		}
 	private boolean executeSql(String s)
 		{
 			boolean flag = false;
 			try
 			{
 				System.out.println("***===current sql is==="+s);
 				sql.executeUpdate(s);
 				flag = true;
 			}catch (SQLException dbexception)
 			{
 				//log.error((new StringBuilder()).append("executeSql出现错误：").append(dbexception.getMessage()).toString());
 				System.out.println(new StringBuilder().append("executeSql出现错误：").append( dbexception.getMessage()).toString());
 			}catch (Exception exception) {
 				exception.printStackTrace();
 			}
 			return flag;
 		}
 //////////////////////////////////////////////////////	
 	public Map<String,String> getLoin(String account,String psw) {
 		System.out.println("----获取监督记录列表----");
 		JSONObject userinfo= new JSONObject();
 		String cent="";
 		CachedRowSet crs = null;
 		Map<String,String> thismap = new HashMap<String,String>();
 		try{
 			cent="select * from userinfo where username='"+account+"' ";
 			crs=getResultBySelect(cent);
 			thismap.put("flag", "false");
 			if(crs.size()>0){
 				cent="select * from userinfo where username='"+account+"' and password='"+psw+"' ";
 	 			crs=getResultBySelect(cent);
 	 			if(crs.size()>0){
 	 				crs.next();
 	 				String nor = crs.getString("flag");
 	 				if(nor.equals("正常")){
 	 					userinfo.put("work_unit",crs.getString("work_unit"));
 	 	 				userinfo.put("userid",crs.getString("userid"));
 	 	 				userinfo.put("account",crs.getString("username"));
 	 	 				userinfo.put("realname",crs.getString("realname"));
 	 	 				userinfo.put("usertype",crs.getString("usertype"));
 	 	 	 			thismap.put("flag", "true");
 	 				}else{
 	 					userinfo.put("info","禁用");
 	 				}
 	 			}else{
 	 				userinfo.put("info","用户口令不正确");
 	 			}
 			}else{
 				userinfo.put("info","该用户不存在");
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		System.out.println(userinfo.toString());
 		thismap.put("body", userinfo.toString());
 		return thismap;
 	}
 	public String getJdjlList(String account) {
 		System.out.println("----获取监督记录列表----");
 		JSONObject jsono = null;
 		JSONArray jsona = new JSONArray();
 		String cent="select j.*,s.gcname as gcname from jdjl as j left join subinfo as s on j.jdsubcode = s.sub_code where jdaccount='"+account+"' order by jdtime desc ";
 		CachedRowSet crs = null;
 		try{
	 		crs=getResultBySelect(cent);
	 		while(crs.next()){
	 			jsono=new JSONObject();
	 			jsono.put("jl_code", crs.getString("jl_code"));
	 			jsono.put("gcname", crs.getString("gcname"));
	 			jsono.put("jdlb", crs.getString("jdlb"));
	 			jsono.put("title", crs.getString("title"));
	 			jsono.put("cont", crs.getString("cont"));
	 			jsono.put("jdtime", crs.getString("jdtime"));
	 			jsona.put(jsono);
	 		}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		System.out.println(Tools.omit(jsona.toString(),15));
 		return jsona.toString();
 	}
 	public String getGginfoList(int curi,int numperpage) {
 		System.out.println("----获取公告列表----");
 		JSONObject jsono = null;
 		JSONArray jsona = new JSONArray();
 		
 		
 		String cent="select top "+numperpage+" * from gginfo where id not in (select top "+(curi-1)*numperpage+" id from gginfo where 1=1  order by isNew desc, gg_time desc )  order by isNew desc, gg_time desc  ";
 		CachedRowSet crs = null;
 		try{
 			crs=getResultBySelect(cent);
 			while(crs.next()){
 				jsono=new JSONObject();
 				jsono.put("code", crs.getString("ggcode"));
 				jsono.put("time", crs.getString("gg_time"));
 				jsono.put("title", crs.getString("title"));
 				jsono.put("cont", crs.getString("cont"));
 				jsono.put("sta", crs.getString("isNew").equals("是")?"0":"1");
 				jsona.put(jsono);
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		System.out.println(Tools.omit(jsona.toString(), 150));
 		return jsona.toString();
 	}
 	
 	public String getSigSta(String account) {
 		System.out.println("----查询 "+account+" 的签到状态----");
 		String sta="";
 		String cent="select * from signup where useraccount='"+account+"' and datediff(day,signtime,getdate())=0";
 		CachedRowSet crs = null;
 		try{
 			crs=getResultBySelect(cent);
 			if(crs.size()>0){
 				sta="signuped";
 			}else{
 				sta="nosignup";
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		System.out.println(account+" : "+sta);
 		return sta;
 	}
 	public String getSig(String userid,String account,String username,String dept,String la,String ln) {
 		System.out.println("---- "+account+" 签到----");
 		boolean sta=false;
 		String cent="insert signup(userid,username,userdept,signtime,latitude,longtitude,useraccount) values('"+userid+"','"+username+"','"+dept+"',getDate(),'"+la+"','"+ln+"','"+account+"')";
 		try{
 			sta=executeSql(cent);
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		System.out.println(account+" : "+sta);
 		return String.valueOf(sta);
 	}
 	public String getSubinfo(String dept) {
 		System.out.println("---- "+dept+" --的相关项目信息----");
 		JSONObject jsono = null;
 		JSONArray jsona = new JSONArray();
 		String cent="select  distinct s.sub_code as e1, s.* from subinfo as s left join subinfoasso as sa "
 				+"on s.sub_code = sa.sub_code where (sa.parname='sjdw' or sa.parname='jldw' "
 				+" or sa.parname='jsdw' or sa.parname='kcdw' or  sa.parname='sgdw' "
 				+" or sa.parname='sgfbdw' or sa.parname='sgzbdw') and  parvalue='"+dept+"'";
 		CachedRowSet crs = null;
		try{
			crs=getResultBySelect(cent);
			while(crs.next()){
				jsono=new JSONObject();
				jsono.put("subcode", crs.getString("sub_code"));
				jsono.put("subname", crs.getString("gcname"));
				jsono.put("la", crs.getString("latitude"));
				jsono.put("ln", crs.getString("longtitude"));
				jsona.put(jsono);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(Tools.omit(jsona.toString(), 150));
		return jsona.toString();
 	}
 	public String getMsgList(int curi,int numperpage,String username) {
 		System.out.println("----获取消息列表----");
 		JSONObject jsono = null;
 		JSONArray jsona = new JSONArray();
 		CachedRowSet crs = null;
 		CachedRowSet crs2 = null;
 		String cent=null;
 		String cent2=null;
 		cent="select top "+numperpage+" i.*,tz.cont as cont ,tz.auth_dept from inbox as i "
 				+"left join tongz as tz "
 				+" on i.msgcode=tz.tzcode "
 				+" where i.id not in (select top "+(curi-1)*numperpage+" id from inbox where  rec_name = '"+username+"' and msgtype='通知' order by isread asc, deli_time desc ) "
 				+" and i.rec_name = '"+username+"' and msgtype='通知' order by isread asc, deli_time desc ";
 		
 		cent2="select top "+numperpage+" i.*,tx.cont  as cont,tx.auth_dept from inbox as i "
 				+"left join txinfo as tx  "
 				+" on i.msgcode=tx.txcode "
 				+"where i.id not in (select top "+(curi-1)*numperpage+" id from inbox where  rec_name = '"+username+"' and msgtype='提醒' order by isread asc, deli_time desc ) "
 				+" and i.rec_name = '"+username+"' and msgtype='提醒' order by isread asc ,deli_time desc";
 		
 		try{
 			System.out.println(cent);
 			crs=getResultBySelect(cent);
 			crs2=getResultBySelect(cent2);
 			while(crs.next()){
 				jsono=new JSONObject();
				jsono.put("msgcode", crs.getString("msgcode"));
				jsono.put("msgtime", crs.getString("deli_time"));
				jsono.put("auth", crs.getString("auth"));
				jsono.put("auth_account", crs.getString("auth_account"));
				jsono.put("auth_dept", crs.getString("auth_dept"));
				jsono.put("msgtitle", crs.getString("title"));
				jsono.put("isread", crs.getString("isread"));
				jsono.put("msglb", crs.getString("msgtype"));
				jsono.put("cont", crs.getString("cont"));
 				jsona.put(jsono);
 			}
 			while(crs2.next()){
 				jsono=new JSONObject();
 				jsono.put("msgcode", crs2.getString("msgcode"));
 				jsono.put("msgtime", crs2.getString("deli_time"));
				jsono.put("auth", crs2.getString("auth"));
				jsono.put("auth_account", crs2.getString("auth_account"));
				jsono.put("auth_dept", crs2.getString("auth_dept"));
 				jsono.put("msgtitle", crs2.getString("title"));
 				jsono.put("isread", crs2.getString("isread"));
 				jsono.put("msglb", crs2.getString("msgtype"));
 				jsono.put("cont", crs2.getString("cont"));
 				jsona.put(jsono);
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		System.out.println(Tools.omit(jsona.toString(),150));
 		return jsona.toString();
 	}
 	public String getNewChk(String account, String dept, String username,
 			String title, String cont, String chklb, String gcname,String subcode) {
 		System.out.println("----新增检查----用户为："+account);
 		JSONObject jsono = null;
 		try{
			CachedRowSet cachedrowset = getResultBySelect("select * from codeset where tabname='jdjl' ");
			cachedrowset.next();
			String prefix = cachedrowset.getString("prefix");
			String manu = cachedrowset.getString("manu");
			String curcode = String.valueOf(Integer.parseInt(manu)+1);
			while(curcode.length()<5){
				curcode="0"+curcode;
			}
			String jlcode =prefix + curcode;
			//////
			String cent="insert jdjl(jdtime,jdsubcode,jdaccount,jdname,jdlb,title,cont,jl_code)"
					+" values(getDate(),'"+subcode+"','"
					+account+"','"+username+"','"+chklb+"','"+title+"','"+cont+"','"+jlcode+"')";
			
			executeSql(cent);		
			/////////
			executeSql("update codeset set manu='"+curcode+"' where tabname='jdjl' ");
			///////
			jsono=new JSONObject();
			jsono.put("sta",true);
			jsono.put("jlcode",jlcode);

 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		System.out.println(Tools.omit(jsono.toString(),150));
 		return jsono.toString();
 	}
 	public String savefile(String jlcode,String account,String username,String filename,String comment) {
 		System.out.println("----上传图片的用户为："+account);
 		boolean flag=false;
 		String rltmsg ="";
 		try{
 			String cent="insert jdjlpic(jlcode,account,username,filename,comment,uptime) "
 					+"values('"+jlcode+"','"+account+"','"+username+"','"+filename+"','"+comment+"',getDate())";
 			flag = executeSql(cent);
 			if(flag){
 				rltmsg="上传成功";
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return rltmsg ;
 	}
 	public String getChkPics(String account ,String username,String jlcode) {
 		System.out.println("----要获取检查记录的用户为："+account+" 姓名："+username);
 		CachedRowSet crs = null;
 		JSONObject jsono = null;
 		JSONArray jsona= new JSONArray();
 		try{
 			String cent="select * from jdjlpic where jlcode='"+jlcode+"'";
 			crs = getResultBySelect(cent);
 			while(crs.next()){
 				jsono = new JSONObject();
 				jsono.put("filename", crs.getString("filename"));
 				jsono.put("comment", crs.getString("comment"));
 				jsono.put("uptime", crs.getString("uptime"));
 				jsona.put(jsono);
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 		}

 		System.out.println(Tools.omit(jsona.toString(),150));
 		return jsona.toString() ;
 	}
 	public String getMarkMsg(String account ,String username,String msgtype,String msgCode) {
 		System.out.println("----要获标记消息的用户为："+account+" 姓名："+username);
 		JSONObject jsono = new JSONObject();
 		try{
 			String cent="update inbox set isread='1' where msgcode ='"
 					+msgCode+"' and (rec_name='"
 					+username+"' or rec_account='"+account+"' )";
 			executeSql(cent);
 			jsono.put("msgtype", msgtype);
 			jsono.put("sta", "ok");
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		
 		System.out.println(username+"标记"+msgCode+"已经读"+jsono.optString("sta"));
 		return jsono.toString() ;
 	}
 	public String getTsyj(String account ,String json) {
 		System.out.println("---投诉用户为："+account);
 		JSONObject j = new JSONObject();
 		try{
 			JSONObject jsono = new JSONObject(json);
 			String title  = (jsono.optString("title","")).replaceAll("'", "''");
 			String cont  = (jsono.optString("cont","")).replaceAll("'", "''");
 			String sub  = (jsono.optString("sub","")).replaceAll("'", "''");
 			String cent="insert tsyj(ts_time,ts_sub,ts_title,ts_cont,account) values(getDate()," +
 					"'"+sub+"','"+title+"','"+cont+"','"+account+"')";
 			executeSql(cent);
 			j.put("stat", "success");
 		}catch(Exception e){
 			e.printStackTrace();
 			try {
				j.put("stat", "fault");
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
 		}
 		
 		System.out.println(account+"-提交投诉意见成功");
 		return j.toString() ;
 	}
 	public String getModpwd(String account ,String json) {
 		System.out.println("---修改密码的用户为："+account);
 		JSONObject j = new JSONObject();
 		try{
 			JSONObject jsono = new JSONObject(json);
 			String newpwd  = (jsono.optString("newpwd","")).replaceAll("'", "''");
 			String oldpwd  = (jsono.optString("oldpwd","")).replaceAll("'", "''");
 			if(newpwd.length()>16 || newpwd.length()<6 || null == account || account.equals("")){
 				return "{\"stat\":\"fault\"}";
 			}
 			
 			
 			CachedRowSet crs = getResultBySelect("select * from userinfo where username='"+account+"' " +
 					"and password = '"+oldpwd+"' ");
 			if (crs.size() != 1){
 				return "{\"stat\":\"fault\"}";
 			}
 			
 			String cent="update userinfo set password ='"+newpwd+"' where username='"+account+"'";
 			executeSql(cent);
 			j.put("stat", "success");
 		}catch(Exception e){
 			e.printStackTrace();
 			try {
 				j.put("stat", "fault");
 			} catch (JSONException e1) {
 				e1.printStackTrace();
 			}
 		}
 		
 		System.out.println(account+"-修改密码成功");
 		return j.toString() ;
 	}
 	public String getJcbz(String account ,String json) {
 		System.out.println("---请求查询手册用户为："+account);
 		JSONArray j = new JSONArray();
 		String code = "" ;
 		String parentcode = "" ;
 		String isleaf = "" ;
 		String ischeck = "" ;
 		String depth = "" ;
 		String title = "" ;
 		try{
 			JSONObject jsono = new JSONObject(json);
 			String reqType  = (jsono.optString("type","")).replaceAll("'", "''");
 			String reqCode  = (jsono.optString("code","")).replaceAll("'", "''");
 			if(reqType.equals("all") && reqCode.equals("root")){
 				CachedRowSet crs = getResultBySelect("select * from jcbz where version=0 ");
 				JSONObject jtmp;
 				while(crs.next()){
 					code = crs.getString("code");
 					parentcode = crs.getString("parentcode");
 					isleaf = crs.getString("isleaf");
 					ischeck = crs.getString("ischeck");
 					depth = crs.getString("depth");
 					title = crs.getString("title");
 					
 					jtmp = new JSONObject();
 					
 					jtmp.put("code",code );
 					jtmp.put("parentcode",parentcode );
 					jtmp.put("isleaf",isleaf );
 					jtmp.put("ischeck",ischeck );
 					jtmp.put("depth",depth );
 					jtmp.put("title",title );
 					
 					j.put(jtmp);
 				}
 				return "{\"stat\":\"fault\"}";
 			}
 		}catch(Exception e){
 			e.printStackTrace();
 			j.put("error");
 		}
 		
 		System.out.println(account+"-查询手册成功");
 		return j.toString() ;
 	}
 	
 ////////////////////////////////////////////////////////////////////
 	public void close(){
 		try {
 			if(con!=null)
 				con.close();
 			if(sql!=null)
 				sql.close();
 			if(rs!=null)
 				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
 	}
}
