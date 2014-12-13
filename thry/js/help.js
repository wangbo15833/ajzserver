function doGYLXJT(formName, element_name)
{
	//var PONo 			= eval("document."+ formName).elements["PONo"].value;
	var PONo			= null;
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/data_pick.jsp?PONo="+PONo+"&time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:25;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  //alert(theValue);
	if(theValue==null){
	  theValue="";
	}
		
	if(theValue.length==0 || theValue[0]=="undefined"){
		//返回为空值,不操作.
		//eval("document."+ formName).elements[element_name].value = "";
	}else{
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		eval("document."+ formName).elements["BItem"].value 			= theValue[1];
		eval("document."+ formName).elements["Item"].value 				= theValue[0];
	}
}

function help(){
	helpMenu.submit();
}