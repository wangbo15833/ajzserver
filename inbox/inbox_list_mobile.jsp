<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.shenghao.arch.util.*"%>
<%@ page import="sun.jdbc.rowset.*"%>
<%@ page import="com.zagerbone.util.Query"%>
<%@ page import="com.zagerbone.util.Tools"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>

<%@ page contentType = "text/html; charset=UTF-8" %>
<%@ page import="com.shenghao.arch.event.*"%>
<%@ page import="com.zagerbone.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="org.json.*"%>
<%
try{
//System.out.println("hp_user=***="+hp_user.toString());

  EventResponse eventResponse = (EventResponse)request.getAttribute("result");  
  Query queryCon = (Query)eventResponse.getBody(); 
  CachedRowSet crs = null;
  if(queryCon!=null)
  {
    crs = (CachedRowSet)queryCon.getConditionBody();
  }

  JSONObject gginfo = new JSONObject();
  JSONObject temp ;
  JSONArray agginfo = new JSONArray();
	while(crs.next()){
	temp = new JSONObject();
	temp.put("title",crs.getString("title"));
	temp.put("code",crs.getString("ggcode"));
	temp.put("time",crs.getString("gg_time"));
	temp.put("cont",crs.getString("cont"));
	temp.put("sta",(crs.getString("isNew")).equals("是")?"0":"1");
	agginfo.put(temp);
	temp= null;
	}
	gginfo.put("gginfo",agginfo);
	%><%=gginfo.toString()%><%
crs.close();
System.out.println(queryCon.getQueryString());
System.out.println(gginfo.toString());
}
catch (Exception e)
{
e.printStackTrace();
}
%>