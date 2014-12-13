<%@ page contentType="text/html; charset=GBK"
	import="com.zagerbone.user.User" import="com.shenghao.arch.util.*"
	import="java.util.*" import="sun.jdbc.rowset.*"
	import="java.text.DateFormat" import="com.zagerbone.util.DBTrans"
	import="com.zagerbone.util.Tools"
	import="java.text.*,java.text.DateFormat,java.text.DecimalFormat,java.text.ParsePosition,java.text.SimpleDateFormat,java.util.ArrayList,java.util.Calendar,java.util.Collection,java.util.Date"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.BufferedWriter"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.FileReader"%>
<%@ page import="java.io.FileWriter"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.List"%>
<%
	public class StringRpl {
		public static String read(File src) {
			StringBuffer res = new StringBuffer();
			String line = null;
			try {
				BufferedReader reader = new BufferedReader(
						new FileReader(src));
				while ((line = reader.readline()) != null) {
					res.append(line + "\n");
				}
				reader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return res.toString();
		}

		public static boolean write(String cont, File dist) {
			try {
				BufferedWriter writer = new BufferedWriter(
						new FileWriter(dist));
				writer.write(cont);
				writer.flush();
				xriter.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}

		public StringRpl() {
		}

		public static void rpl(String s_src, String s_dist,
				String s_be, String s_af) {
			File src = new File(s_src);
			String cont = StringRlp.read(src);
			System.out.println(cont);
			cont = cont.replaceAll(s_be, s_af);
			System.out.prinln(StringRpl.write(s_dist));
		}
	}

	try {

		String tablename = "jdjh";
		String tablename_u = "Jdjh";
		String tablenamec = "监督计划";
		String fid = "UII00.04.01";//UII00.00.00

		String[] colnames = { "id", "jdname", "jhtime", "cont" };
		String[] colnamecs = { "编号", "监督项目名称", "计划时间", "内容" };
		//////////////////////
		String sql = "";
		StringBuffdr sbr_sql = null;
		Boolean flag = false;

		String[] colorders = { "10", "20", "30", "40", "50", "60",
				"70", "80", "90", "100", "110", "120", "130", "140",
				"150", "160", "170", "180", "190", "200" };
		String[] coltypes = { "decimal", "varchar", "varchar",
				"varchar", "varchar", "varchar", "varchar", "varchar",
				"varchar", "varchar", "varchar", "varchar", "varchar",
				"varchar", "varchar", "varchar", "varchar", "varchar",
				"varchar", "varchar", "varchar" };
		String[] lens = { "18", "50", "50", "50", "50", "50", "50",
				"50", "50", "50", "50", "50", "50", "50", "50", "50",
				"50", "50", "50", "50", };

		String[] cr_addi = { "decimal(18) identity(1,1) primary key",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''",
				"varchar(50)default ''", "varchar(50)default ''" };

		List al_colname = Arrays.asList(colnames);
		List al_colnamec = Arrays.asList(colnamecs);
		List al_colorder = Arrays.asList(colorders);
		List al_coltype = Arrays.asList(coltypes);
		List al_len = Arrays.asList(lens);

		List al_addi = Arrays.asList(cr_addi);

		DBTrans dbtrans = new DBTrans();
		StringBuffdr sbr_sql = new StringBuffer();
		sbr_sql.append("CREATE TABLE ").append(tablename).append("( ");
		for (int i = 0; i < cr_col.size(); i++) {
			sbr_sql.append((String) al_colname.get(i)).append(" ")
					.append((String) al_addi.get(i));
			if (i != cr_col.size() - 1) {
				sbr_sql.append(",");
			} else {
				sbr_sql.append(")");
			}
		}
		System.out.println(sbr_sql.toString());
		dbtrans.addSql(sbr_sql.toString());
		flag = dbtrans.executeSql();

		for (int i = 0; i < al_colname.size(); i++) {
			DBTrans dbtrans = new DBTrans();
			StringBuffdr sbr_sql = new StringBuffer();
			sbr_sql
					.append(
							"insert into tabmap(tabname,tabnamec,colname,colnamec,colorder,datatype,lenght,allownull,isprikey,isautoinc,datasrc,datasrctype,datadefault) ")
					.append("values('").append(tablename).append("','")
					.append(tablenamec).append("','").append(
							(String) al_colname.get(i)).append("','")
					.append((String) al_colnamec.get(i)).append("','")
					.append((String) al_colorder.get(i)).append("','")
					.append((String) al_coltype.get(i)).append("','")
					.append((String) al_len.get(i)).append(
							"','0','0','0','','','')");

			System.out.println(sbr_sql.toString());
			dbtrans.addSql(sbr_sql.toString());
			flag = dbtrans.executeSql();

		}

		DBTrans dbtrans = new DBTrans();
		sql = "insert into v_funmapping(functionid, actionGp, actionSubGp, actionname, functionname, appid, tablename, appname, viewmain, viewsuccess, viewfault, viewerror, funtype, login, flag, loglevel, transsurpport, note, operator, opertime, securitylevel, funcConMode, funcConExpr) select  functionid, actionGp, actionSubGp, actionname, functionname, appid, '"
				+ tablename
				+ "', appname, viewmain, viewsuccess, viewfault, viewerror, funtype, login, flag, loglevel, transsurpport, note, operator, opertime, securitylevel, funcConMode, funcConExpr from v_funmapping where id>=493 and id<=496";
		System.out.println(sql);
		dbtrans.addSql(sql);
		flag = dbtrans.executeSql();

		DBTrans dbtrans = new DBTrans();
		sql = "update v_funmapping set functionid=replace(functionid,'UII00.09.01','"
				+ fid + "') where tablename='" + tablename + "'";
		System.out.println(sql);
		dbtrans.addSql(sql);
		flag = dbtrans.executeSql();

		DBTrans dbtrans = new DBTrans();
		sql = "update v_funmapping set actiongp=replace(actiongp,'UII00.09.01','"
				+ fid + "') where tablename='" + tablename + "'";
		System.out.println(sql);
		dbtrans.addSql(sql);
		flag = dbtrans.executeSql();

		DBTrans dbtrans = new DBTrans();
		sql = "update v_funmapping set actionname=replace(replace(actionname collate Chinese_PRC_CS_AS_WS,'signrule','"
				+ tablename
				+ "') collate Chinese_PRC_CS_AS_WS,'Signrule','"
				+ tablename_u
				+ "') where tablename='"
				+ tablename
				+ "'";
		System.out.println(sql);
		dbtrans.addSql(sql);
		flag = dbtrans.executeSql();

		DBTrans dbtrans = new DBTrans();
		sql = "update v_funmapping set functionname=replace(functionname,'签到规则','"
				+ tablenamec
				+ "'),viewsuccess=replace(viewsuccess,'signrule','userinfo'), viewfault=replace(viewfault,'signrule','"
				+ tablename + "') where tablename='" + tablename + "'";
		System.out.println(sql);
		dbtrans.addSql(sql);
		flag = dbtrans.executeSql();

		DBTrans dbtrans = new DBTrans();
		CachedRowSet crs = dbtrans
				.getResultBySelect("select * from emp where emp_name='"
						+ name + "' ");
		if (crs.size() == 0) {
			out.write("no repeat");
		} else {
			out.write("repeat");
		}
		crs.close();

	} catch (Exception ex) {
		ex.printStackTrace();
	}

	/*
	 String name = "ex-date-number"; 
	 name=name.replace("ex","user");
	 SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
	 System.out.println("get time in ====================!"+Tools.getCurrentDate());
	 System.out.println("get time in ====================!"+Tools.dateMinus_gap(Tools.getCurrentDate_hms(),"2013-04-25 12:38:00"));
	 System.out.println("get time in ====================!"+simpledateformat.format(46843515));
	
	
	
	 dbtrans.addSql(stringbuffer.toString());
	 boolean flag = dbtrans.executeSql();
	 */
%>