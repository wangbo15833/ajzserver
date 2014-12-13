package com.zagerbone.role;

import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Query;
import sun.jdbc.rowset.CachedRowSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// Referenced classes of package com.zagerbone.role:
//			Role

public interface RoleDAO
{

	public abstract String createRole(Role role,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract String publishPersonalRole(Role role)
		throws BussinessProcessException; */

	public abstract Query findRoleList(Query query,HttpServletRequest request)
		throws BussinessProcessException;

/* 	public abstract Query findPersonalRoleList(Query query)
		throws BussinessProcessException; */
/* 
	public abstract CachedRowSet findRoleBytp_carname(String s)
		throws BussinessProcessException;

	public abstract Query findtp_carnameRoleList(Query query)
		throws BussinessProcessException;

	public abstract Query findCnameRoleList(Query query)
		throws BussinessProcessException; */

/* 	public abstract Role findRoleById(String s,HttpServletRequest request)
		throws BussinessProcessException;
 */
	public abstract String modifyRole(Role role,HttpServletRequest request)
		throws BussinessProcessException;

	public abstract String deleteRole(String s,HttpServletRequest request)
		throws BussinessProcessException;
/* 
	public abstract boolean hasNewRole(String s)
		throws BussinessProcessException; */
}
