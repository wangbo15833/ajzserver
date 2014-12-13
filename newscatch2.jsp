<%@ page contentType="text/html; charset=gb2312" language="java" import="java.util.regex.*" errorPage="" 
%><%@ page
import="java.io.BufferedReader"
import="java.io.InputStreamReader"
import="java.net.HttpURLConnection"
import="java.net.URL"
%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.zagerbone.util.DBTrans"%>
<%!
public String getHTML(String pageURL, String encoding) {
	StringBuilder pageHTML = new StringBuilder(); 
	try { 
		URL url = new URL(pageURL); 
		HttpURLConnection connection = (HttpURLConnection) url.openConnection(); 
		//connection.setRequestProperty("User-Agent", "MSIE 7.0"); 
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding)); 
		String line = null; 
		pageHTML.append("<ul>"); 
		pageHTML.append("\r\n"); 
		while ((line = br.readLine()) != null) { 
			///pageHTML.append(line); 
			///pageHTML.append("\r\n"); 
			//
			if(line.indexOf("data-client=\"p_society\"")>-1){
				int i = 0 ; 
				int j = 20 ;
				while(true){
					j--;
					System.out.println(j);
					line = br.readLine();
					if(line.indexOf("<li")>-1){
						pageHTML.append(line); 
						pageHTML.append("\r\n"); 
						i++;
					}
					if(i==3){
						break;
					}
					if(j==0){
						break;
					}
				}
			}
			///
			if(line.indexOf("data-client=\"p_china\"")>-1){
				int i = 0 ; 
				int j = 20 ;
				while(true){
					j--;
					System.out.println(j);
					line = br.readLine();
					if(line.indexOf("<li")>-1){
						pageHTML.append(line); 
						pageHTML.append("\r\n"); 
						i++;
					}
					if(i==3){
						break;
					}
					if(j==0){
						break;
					}
				}
			}
			///
			if(line.indexOf("data-client=\"p_world\"")>-1){
				int i = 0 ; 
				int j = 20 ;
				while(true){
					j--;
					System.out.println(j);
					line = br.readLine();
					if(line.indexOf("<li")>-1){
						pageHTML.append(line); 
						pageHTML.append("\r\n"); 
						i++;
					}
					if(i==2){
						break;
					}
					if(j==0){
						break;
					}
				}
			}
			if(line.indexOf("data-client=\"p_finance\"")>-1){
				int i = 0 ; 
				int j = 20 ;
				while(true){
					j--;
					System.out.println(j);
					line = br.readLine();
					if(line.indexOf("<li")>-1){
						pageHTML.append(line); 
						pageHTML.append("\r\n"); 
						i++;
					}
					if(i==2){
						break;
					}
					if(j==0){
						break;
					}
				}
			}
		}
		pageHTML.append("</ul>"); 
		pageHTML.append("\r\n"); 
		connection.disconnect(); 
	} catch (Exception e) { 
		e.printStackTrace(); 
	} 
	return pageHTML.toString(); 
}
%><%
	String news = null;
	Long lastNewTime = 0L;
    Long nowTime = (new java.util.Date()).getTime();
	DBTrans dbtrans1 = new DBTrans(); 
	CachedRowSet crs_news = null;
	try{
		String sql= "select * from multimedia where 1=1 and m_name='news' ";
		System.out.println("=======getnews sql is ======="+sql);
		crs_news = dbtrans1.getResultBySelect(sql);
		if(crs_news.size()!=0){
			crs_news.next();
			lastNewTime = Long.parseLong(crs_news.getString("m_time"));
		}
		Long dategap = (nowTime - lastNewTime)/(8*60*60*1000);
		if(dategap >0){
			news = getHTML("http://news.sina.com.cn/", "GB2312");
			/*dbtrans1.addSql("declare @nowTime varchar(20) , @news varchar(20)");
			dbtrans1.addSql("set @nowTime='"+nowTime+"' ,set  @news='"+news+"'");
			dbtrans1.addSql("update multimedia set m_time=@nowTime  , m_cont=@news where m_name='news'");
			dbtrans1.executeSql();
			dbtrans1.addSql("declare @news varchar(10)	set @news = 'werwer' select * from multimedia where m_name=@news");
			dbtrans1.executeSql();
			*/
			dbtrans1.addSql("update multimedia set m_time='"+nowTime+"'  , m_cont='"+news+"'");
			dbtrans1.executeSql();
		}else{
			news = crs_news.getString("m_cont");
		}
		dbtrans1=null;
	}catch(Exception e){
		e.printStackTrace();
		news = "error!";
	}
	%><%=news%>