package com.shenghao.arch.actionmapping;

import java.util.HashMap;

import sun.jdbc.rowset.CachedRowSet;

import com.shenghao.arch.manage.function.Function;
import com.shenghao.arch.util.SysTrace;
import com.zagerbone.util.DBTrans;

// Referenced classes of package com.shenghao.arch.actionmapping:
//			ActionMapping, ActionMappingDAO

public class ActionMappingDAOImpl
	implements ActionMappingDAO
{

	public ActionMappingDAOImpl()
	{
	}

	public ActionMapping buildActionMapping(String s)
	{
		HashMap hashmap;
		ActionMapping actionmapping;
		CachedRowSet cachedrowset;
		hashmap = new HashMap(10);
		actionmapping = new ActionMapping();
		cachedrowset = null;
		try{
			DBTrans dbtrans = new DBTrans();
			
			StringBuffer stringbuffer = new StringBuffer("select * from v_funmapping where flag = '0'");
			SysTrace.info(stringbuffer.toString());
			SysTrace.info(SysTrace.JNDI);
			Function function;
			for (cachedrowset = dbtrans.getResultBySelect(stringbuffer.toString()); cachedrowset.next(); hashmap.put(cachedrowset.getString("functionid"), function))
			{
				function = new Function();
				function.setFunctionid(cachedrowset.getString("functionid"));
				function.setAction(cachedrowset.getString("actionname"));
				function.setLogin(cachedrowset.getString("login"));
				function.setTranssurpport(cachedrowset.getString("transsurpport"));
				function.setLoglevel(cachedrowset.getString("loglevel"));
				function.setViewsuccess(cachedrowset.getString("viewsuccess"));
				function.setViewfault(cachedrowset.getString("viewfault"));
			}
			
		}
		catch (Exception localException)
	    {
	      SysTrace.error("error at getActionMapping : " + localException.getMessage());
	      localException.printStackTrace();
	    }
		
		finally
		{
			try
			{
				cachedrowset.close();
			}
			catch (Exception exception) { }
		}
		
		actionmapping.setFunctionMappings(hashmap);
		return actionmapping;
	}
}
