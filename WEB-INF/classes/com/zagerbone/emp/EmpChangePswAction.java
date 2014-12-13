package com.zagerbone.emp;

import com.shenghao.arch.action.BaseActionSupport;
import com.shenghao.arch.event.Event;
import com.shenghao.arch.event.EventResponse;
import com.shenghao.arch.exception.BussinessProcessException;
import com.zagerbone.util.Tools;
import com.zagerbone.util.Query;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import sun.jdbc.rowset.CachedRowSet;

// Referenced classes of package com.zagerbone.emp:
//			Emp, EmpDAOFactory, EmpDAO

public class EmpChangePswAction extends BaseActionSupport
{

	public EmpChangePswAction()
	{
	}

	public EventResponse perform(Event event)
	{
		EventResponse eventresponse = new EventResponse();
		EmpDAO empdao = EmpDAOFactory.getDAO();
		HttpServletRequest request = event.getRequest();
		String mode = Tools.nvl(request.getParameter("mode"));
		System.out.println("***=== EmpChangePswAction mode is "+ mode+" ===***");
		try
		{
			if(mode.equals("before"))
			{
				eventresponse.setSuccessFlag(true);
			}else{
			
				Emp emp = (Emp)processEvent(event);
				System.out.println("*** === step 2 in EmpChangePswAction === ***");
				request.setAttribute("flag1","true");
				String s = empdao.empChangePsw(emp ,request);
				eventresponse.setSuccessFlag(true);
			}
		}catch(Exception exception)
		{
			eventresponse.setSuccessFlag(false);
			eventresponse.setErrorCode(event.getFunctionID());
			eventresponse.setErrorMessage(exception.getMessage().replace('\n', ' '));
			return eventresponse;
		}
		return eventresponse;
	}

	public Object processEvent(Event event)
		throws BussinessProcessException
	{
		Emp emp = new Emp();
		HashMap hashmap = event.getBody();		
		emp.setid((String)hashmap.get("id"));
		hashmap.remove("id");
		emp.setHashMap(hashmap);
		return emp;
	}
}
