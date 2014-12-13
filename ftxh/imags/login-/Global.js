function CreateConfirm()
{
	return confirm('是否确定创建？')
}
function DeleteConfirm()
{
	return confirm('是否确定删除？')
}

function LogoutConfirm()
{
	if (confirm('是否确定退出系统？'))
	{	
		self.location.href='/LoginMgr/Logoutur/default.asp';		
	}
	return false;
}

function CancelConfirm()
{
	return confirm('是否确定作废？')
}

function systurnpage(p)
{
	if (document.forms("systurnpageform"))
	{
		document.forms("systurnpageform").whichpage.value = p;
		document.forms("systurnpageform").submit();
	}
	

}

//跳出新窗口
function popwin(windowname,h,w,url)
{
	var intTop = 0;
	var intLeft = 0;

	if (parseInt(h) == 8888 )
	{
	//如果高度为8888,那么全屏显示
		w=window.screen.availWidth-10;
		h=window.screen.availHeight-20;
	}
	else
	{//分辨率为800×600，从中间呈现
		intTop = (window.screen.availHeight-parseInt(h))/2;
		intLeft = (window.screen.availWidth-parseInt(w))/2;

		if (intTop < 30)
			intTop = 0;

		if (intLeft < 30)
			intLeft = 0;
	}
    var windowconfig = "titlebar=0,status=1,scrollbars=yes,top="+ intTop +",left="+ intLeft  +",resizable=1,width=" + w + ",height=" + h;


    subwin=window.open(url, windowname, windowconfig);
	if (subwin)
	{
		subwin.focus();
		return subwin;
		
	}
	   
}

function popdialog(objwin,h,w,url)
{
	//弹出对话框
	var dialogconfig = 'dialogHeight:' + h + 'px;dialogWidth:' + w + 'px;help:no;status:no;center:yes;edge:sunken';	
    window.showModelessDialog(url,objwin,dialogconfig);
}

function checkNumeric(fieldtmp)
{
	if(isNaN(fieldtmp.value)) return false
	else return true
}

function checkDate(fieldtmp)
{
	var tmpdate=trim(fieldtmp.value)
	if (tmpdate!="")
	{
		tmpdate=getDateValue(tmpdate)
		if(tmpdate=="error")
		{
			fieldtmp.focus()
			fieldtmp.select()
			return false
		} 
		else 
		{
			fieldtmp.value=tmpdate
			return true
		}
	}
	else
	{
		fieldtmp.value=tmpdate
		return false
	}
}

function ltrim(str)
{
	for(var i = 0 ; i<str.length && str.charAt(i)==" " ; i++ ) ;
	return str.substring(i,str.length); 
}
function rtrim(str)
{
	for(var i = str.length ; i>0 && str.charAt(i)==" " ; i-- ) ;
	return str.substring(0,i); 
}
function trim(str)
{
	return ltrim(rtrim(str));
}

function getDateValue(date)
{
	var year=-1;
	var month=-1;
	var day=-1;
	var pos=-1;
	var smonth="";
	var sday="";

	year=isYearValid(cint(date));
	pos=getFirstNotNumber(date,0);
	if (pos!=-1&&year!=-1)
	{
		month=cint(date.substring(pos+1));
		if (month>12 || month<1) month=-1;
	}
	pos=getFirstNotNumber(date,pos+1)
	if (pos!=-1&&month!=-1)
		day=isDayValid(year,month,cint(date.substring(pos+1)));
	if (month<10)
		smonth='0'+month
	else
		smonth=month
	if (day!=-1)
	{

		if (day<10)
			sday='0'+day
		else
			sday=day

		return year+'-'+smonth+'-'+sday
	}
	else 
	{
		return "error";
	}
}
function isYearValid(year)
{
	var retyear=year;
	/*if (year>20 && year<100)retyear=year+1900;
	else
	{
		if (year>=0 && year<=20)	retyear=year+2000;
		else 
		{*/
			if (year<1950 || year>2050) retyear=-1;
		/*}
	}*/
	return retyear;
}
function isDayValid(year,month,day)
{
	var retday=day;
	if (day<1||day>31||month<1||month>12)retday=-1;
	else
	{
		if (day==31&&(month==2||month==4||month==6||month==9||month==11)) retday=-1;
		else
		{
			if(month==2&&day>28&&!(day==29&&year%4==0&&(year%100!==0||year%400==0)))
				retday=-1
		}
	}
	return retday;
}

function cint(str)
{
	var retint=0;
	for(var i=0 ;i<str.length; i++)
	{
		if (str.charAt(i)>"9" || str.charAt(i)<"0")	break;
		retint=retint*10+(str.charAt(i)-"0")
	}
	return retint;
}
function getFirstNotNumber(str,start)
{
	for (var i=start;i<str.length && str.charAt(i)>="0" && str.charAt(i)<="9";i++);
	if (i>=str.length)return -1;
	else return i;

}





var lastObjTR
function SysChangeRowColor(objTR)
{
    //选中DBGrid中的某行（即标识不同颜色），同时将原来选中的抹去
	
	if (lastObjTR)
	{
		lastObjTR.style.backgroundColor = '';
		/*
		for (td_idx = 0; td_idx < lastObjTR.cells.length; td_idx++)
		{
			objTD = lastObjTR.cells[td_idx]; //循环获得该行所有单元格对象
			//objTD.style.backgroundColor = '';
			
			for (obj_idx = 0; obj_idx < objTD.childNodes.length; obj_idx++)
			{
				obj = objTD.childNodes[obj_idx];
				if (obj.style)
				{
					obj.style.backgroundColor = '';
				}
				
			}

			
		}
		*/
	}
	if (objTR)
	{
		objTR.style.backgroundColor = '#EDEDED';
		//如果该行包括其他表单，那么将这些表单元素的背景颜色一并改为#FFF7E5
		/*
		for (td_idx = 0; td_idx < objTR.cells.length; td_idx++)
		{
			objTD = objTR.cells[td_idx]; //循环获得该行所有单元格对象
			//objTD.style.backgroundColor = '#EDEDED';
			
			for (obj_idx = 0; obj_idx < objTD.childNodes.length; obj_idx++)
			{
				obj = objTD.childNodes[obj_idx];
				if (obj.style)
				{
					obj.style.backgroundColor = '#EDEDED';
				}
				
			}
			
		}
		*/
		lastObjTR = objTR;
	}
}

function showMyLocation1(stitle){
	if (self.parent.banner.mylocation){	
		self.parent.banner.mylocation.innerText = stitle
	}
	
}


function goPage(objTurnPage)
{

	if (document.forms('sysrequery')){
		if (isNaN(objTurnPage)){
			if (objTurnPage == 's'){
				document.forms('sysrequery').formpagenumber.value = 1;
			} else if (objTurnPage == 'e'){
				document.forms('sysrequery').formpagenumber.value = document.all('systotalpage').innerText;
			} else {
				document.forms('sysrequery').formpagenumber.value = document.all(objTurnPage).value;
			}
		}
		else
			document.forms('sysrequery').formpagenumber.value = parseInt(document.forms('sysrequery').formpagenumber.value) + 
															    parseInt(objTurnPage);
			
		document.forms('sysrequery').submit();
	}
}

	function WriteCookie (cookieName, cookieValue, expiry) 
   {
      var expDate = new Date();

      if(expiry){ //如果设置了cookie失效时间;
         expDate.setTime (expDate.getTime() + expiry);
         document.cookie = cookieName + "=" + escape (cookieValue) + "; expires=" + expDate.toGMTString();
      }
      else{ //没有设置cookie失效时间;
         document.cookie = cookieName + "=" + escape (cookieValue); }
   }

   function ReadCookie (CookieName) {
      var CookieString = document.cookie;
      var CookieSet = CookieString.split (';');
      var SetSize = CookieSet.length;
      var CookiePieces
      var ReturnValue = "";
      var x = 0; 
      for (x = 0; ((x < SetSize) && (ReturnValue == "")); x++) {

         CookiePieces = CookieSet[x].split ('=');

         if (CookiePieces[0].substring (0,1) == ' ') {
            CookiePieces[0] = CookiePieces[0].substring (1, CookiePieces[0].length);}

         if (CookiePieces[0] == CookieName) {
            ReturnValue = CookiePieces[1];}

      }
      return ReturnValue;

   }
function barfocus(obj){
	obj.className = 'barfocus';
}

function barlostfocus(obj){
	obj.className = 'barlostfocus';
}

function barlostfocus1(obj){
	obj.className = 'barlostfocus1';
}

function bardofocus(obj){
	obj.className = 'bardofocus';
}

