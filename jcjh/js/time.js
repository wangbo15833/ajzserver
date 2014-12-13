function getCurrentDate(){
	var today = new Date();
	var year 	= today.getYear();
	var month 	= today.getMonth();
	var date 	= today.getDate();
	
	var str_month 	= '';
	var str_date 	= '';
	if(month<9){
		month++;
		str_month = '0'+month;
	}
	else{
		str_month = month;
	}
	
	if(date<9){
		date++;
		str_date = '0'+date;
	}
	else{
		str_date = date;
	}
	//alert(''+year+'-'+str_month+'-'+str_date);
	return ''+year+'-'+str_month+'-'+str_date;
}

function help(){
	helpMenu.submit();
}

function doTime(formName, element_name)
{
	var today 				= new Date();
	var current_time	= today.getTime();
	var theUrl = "../toolsPage/popMenu/time.jsp?"+"time="+current_time;
    var style="status:no;help:no;dialogWidth:25;dialogHeight:20;dialogTop:100;dialogLeft:250"
    theValue=window.showModalDialog(theUrl,null,style);
	if(theValue==null){
	  theValue="";
	}
	if(theValue.length==0||theValue=="undefined"){
	}
	else{
	  //location = location;
	  //document.formHead.elements[element_name].value = theValue;
	  eval("document."+ formName).elements[element_name].value = theValue;
	}
}

function doDate(formName, element_name, thisObj)
{
	var today 				= new Date();
	var current_time	= today.getTime();
	var theUrl = "../toolsPage/popMenu/date.jsp?"+"time="+current_time;
    var style="status:no;help:no;dialogWidth:25;dialogHeight:20;dialogTop:100;dialogLeft:250"
    theValue=window.showModalDialog(theUrl,null,style);
	if(theValue==null){
	  theValue="";
	}
	if(theValue.length==0||theValue=="undefined"){
	}
	else{
	  //location = location;
	  //document.formHead.elements[element_name].value = theValue;
	  //eval("document."+ formName).elements[element_name].value = theValue;
	  /*
		if(eval("document."+ formName) != undefined  && eval("document."+ formName).elements[element_name] != undefined){
			eval("document."+ formName).elements[element_name].value = theValue;
		}else{
			thisObj.value = theValue;
		}*/
		thisObj.value = theValue;
	}
}

function doDate_a(formName, element_name)
{
	var today 				= new Date();
	var current_time	= today.getTime();
	var theUrl = "../toolsPage/popMenu/date.jsp?"+"time="+current_time;
    var style="status:no;help:no;dialogWidth:25;dialogHeight:20;dialogTop:100;dialogLeft:250"
    theValue=window.showModalDialog(theUrl,null,style);
	if(theValue==null){
	  theValue="";
	}
	if(theValue.length==0||theValue==undefined){
	}
	else{
	  //location = location;
	  //document.formHead.elements[element_name].value = theValue;
	  //eval("document."+ formName).elements[element_name].value = theValue;
	  if(eval("document."+ formName) != undefined  && eval("document."+ formName).elements[element_name] != undefined){
			eval("document."+ formName).elements[element_name].value = theValue;
		}
		//thisObj.value = theValue;
	}
}

function doDate_year_month(formName, element_name, thisObj)
{
	var today 				= new Date();
	var current_time	= today.getTime();
	var theUrl = "../poppage/date_year_month.jsp?"+"time="+current_time;
	var style="status:no;help:no;dialogWidth:25;dialogHeight:20;dialogTop:100;dialogLeft:250"
  var theValue=window.showModalDialog(theUrl,null,style);
  
  if(theValue==null){
	  theValue="";
	}
	
	if(theValue.length==0 || theValue[0]=="undefined"){
		//alert("111theValue:"+theValue[0]);
		;
	}
	else{
		if(eval("document."+ formName) != undefined  ){
			//eval("document."+ formName).elements[element_name].value = theValue;
			eval("document."+ formName).elements["year_payoff"].value = theValue[0];
			eval("document."+ formName).elements["month_payoff"].value = theValue[1];
		}else{
			thisObj.value = theValue;
		}
	}
}


var date_now_js=new Date();
var year_js=date_now_js.getYear();
var month_js=date_now_js.getMonth()+1;
var Date_js=date_now_js.getDate();
var date_now=year_js+'-'+month_js+'-'+Date_js;
