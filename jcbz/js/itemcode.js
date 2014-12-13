function doItemcode(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/ba_item.jsp?time="+cur_time;
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
		//FormHead.parentitem.value = theValue[0];
		eval("document."+ formName).elements[element_name].value = theValue[0];
	}
}

function doItemcode_pu_po(formName, element_name)
{
	var PONo 			= eval("document."+ formName).elements["PONo"].value;
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/pu_buyitem.jsp?PONo="+PONo+"&time="+cur_time;
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

function doSl_salesitem_view(formName, element_name, thisObj)
{
	//var CustomerID= eval("parent.menuframe.document.controlPane").elements["CustomerID"].value;
	var CustomerID = "";
	if(parent.menuframe.document.controlPane != undefined  && parent.menuframe.document.controlPane.CustomerID != undefined){
		CustomerID = parent.menuframe.document.controlPane.CustomerID.value;
	}
	else if (parent.MD_editframe.CustomerID != undefined){
		CustomerID = parent.MD_editframe.CustomerID;
		//alert("MD_editframe:"+CustomerID);
	}
	else if (parent.reportframe.CustomerID != undefined){
		CustomerID = parent.reportframe.CustomerID;
		//alert("reportframe:"+CustomerID);
	}
	
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/sl_salesitem_view.jsp?time="+cur_time+"&CustomerID="+CustomerID;
	var style			= "status:no;help:no;dialogWidth:30;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  //alert(theValue);
	if(theValue==null){
	  theValue="";
	}
	
	if(theValue.length==0 || theValue[0]=="undefined"){
		//FormHead.parentitem.value="";
		//返回为空值,不操作.
		//eval("document."+ formName).elements[element_name].value = "";
	}
	else{
		//FormHead.parentitem.value = theValue[0];
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		//thisObj.value 			= theValue[0];
		//alert("element_name:"+element_name);
		var idNum = thisObj.id.replace(element_name, '');
		//alert("idNum:"+idNum);
		document.getElementById("SItem"+idNum).value 	= theValue[0]
		document.getElementById("Item"+idNum).value 	= theValue[1];
	}
}

function doHousepay_span(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/housepay_span.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:30;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  //alert(theValue);
	if(theValue==null){
	  theValue="";
	}
	
	if(theValue.length==0 || theValue[0]=="undefined"){
		//eval("document."+ formName).elements[element_name].value = "";
	}else{
		formHead.housepay_span.value 	= theValue[0]
		formHead.housepay_spanname.value 	= theValue[1];
	}
}
  
function doFi_period_for_Localkeep(formName, element_name)
{
	var Year			=  eval("document."+ formName).elements["Date"].value;
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/fi_period_for_Localkeep.jsp?time="+cur_time+"&Year="+Year;
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
		//FormHead.parentitem.value = theValue[0];
		eval("document."+ formName).elements[element_name].value = theValue[1];
	}
}


function doFr_rinvoice(formName, element_name)
{
	var CustomerID= eval("parent.menuframe.document.controlPane").elements["CustomerID"].value;
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/fr_rinvoice_view.jsp?time="+cur_time+"&CustomerID="+CustomerID;
	var style			= "status:no;help:no;dialogWidth:30;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  //alert(theValue);
	if(theValue==null){
	  theValue="";
	}
	
	if(theValue.length==0 || theValue[0]=="undefined"){
		//eval("document."+ formName).elements[element_name].value = "";
	}else{
		eval("document."+ formName).elements["InvNo"].value 			= theValue[0];
	}
}

function doWh_delivery_master(formName, element_name)
{
	//var CustomerID= eval("parent.menuframe.document.controlPane").elements["CustomerID"].value;
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/wh_delivery_master.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:30;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  //alert(theValue);
	if(theValue==null){
	  theValue="";
	}
	
	if(theValue.length==0 || theValue[0]=="undefined"){
		//eval("document."+ formName).elements[element_name].value = "";
	}else{
		eval("document."+ formName).elements[element_name].value 	= theValue[0];
	}
}

function doFr_rinvoice_master_for_voucher(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/fr_rinvoice_master_for_voucher.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:30;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  if(theValue==null){
	  theValue="";
	}
  if(theValue.length==0 || theValue[0]=="undefined"){
		//eval("document."+ formName).elements[element_name].value = "";
	}else{
		eval("document."+ formName).elements[element_name].value 	= theValue[0];
	}
}

function doFr_receipt_master_for_voucher(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/fr_receipt_master_for_voucher.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:30;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  if(theValue==null){
	  theValue="";
	}
  if(theValue.length==0 || theValue[0]=="undefined"){
		//eval("document."+ formName).elements[element_name].value = "";
	}else{
		eval("document."+ formName).elements[element_name].value 	= theValue[0];
	}
}

function doPeopleinfo_salary(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/peopleinfo_salary.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:28;dialogHeight:27;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  if(theValue==null){
	  theValue="";
	}
	if(theValue.length==0 || theValue[0]=="undefined"){
		//返回为空值,不操作.
	}else{
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		eval("document."+ formName).elements["people_id"].value 			= theValue[0];
		eval("document."+ formName).elements["name"].value 			= theValue[1];
		eval("document."+ formName).elements["people_mode"].value 			= theValue[2];
		eval("document."+ formName).elements["bank_code"].value 			= theValue[3];
		eval("document."+ formName).elements["people_code"].value 			= theValue[4];
		eval("document."+ formName).elements["workspace_id"].value 			= theValue[5];
	}
}

function doPeoplemode_salary(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/peoplemode.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:25;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  if(theValue==null){
	  theValue="";
	}
	if(theValue.length==0 || theValue[0]=="undefined"){
		//返回为空值,不操作.
	}else{
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		var name_array = new Array();
		var value_array = new Array();
		if(theValue[0] == '公益'){
			name_array[0] = "模式0";
			name_array[1] = "模式1";
			
			value_array[0] = "0";
			value_array[1] = "1";
		}
		else if(theValue[0] == '丧劳'){
			name_array[0] = "模式0";
			name_array[1] = "模式1";
			
			value_array[0] = "0";
			value_array[1] = "1";
		}
		
		
		else{
			name_array[0] = "模式0";
			
			value_array[0] = "0";
		}	
			
		fillSelect(document.all.type_id,  name_array, value_array);
		document.all.people_mode.value = theValue[0];
		document.all.type_people_mode.value = theValue[1];
	}
}




function doPeopleDepart(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/hr_depart.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:28;dialogHeight:27;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  if(theValue==null){
	  theValue="";
	}
	if(theValue.length==0 || theValue[0]=="undefined"){
		//返回为空值,不操作.
	}else{
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		eval("document."+ formName).elements["company_code"].value 	= theValue[0];
		eval("document."+ formName).elements["company_code_textpop"].value 	= theValue[1];
		eval("document."+ formName).elements["depart_code"].value 	= theValue[2];
		eval("document."+ formName).elements["depart_code_textpop"].value 	= theValue[3];
	}
}


/*
function doPu_supplier(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../MainServlet/FSys000.000.001.00?time="+cur_time+"&displayName=pu_supplier";
	var style			= "status:no;help:no;dialogWidth:28;dialogHeight:27;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  if(theValue==null){
	  theValue="";
	}
	if(theValue.length==0 || theValue[0]=="undefined"){
		//返回为空值,不操作.
	}else{
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		if(eval("document."+ formName).elements["SupplierID"] != undefined){
			eval("document."+ formName).elements["SupplierID"].value = theValue[0];
		}
		else if(eval("document."+ formName).elements["SupplierName"] != undefined){
			eval("document."+ formName).elements["SupplierName"].value 			= theValue[1];
		}
		else if(eval("document."+ formName).elements["Tel"] != undefined){
			eval("document."+ formName).elements["Tel"].value 			= theValue[2];
		}
		else if(eval("document."+ formName).elements["Address"] != undefined){
			eval("document."+ formName).elements["Address"].value 			= theValue[3];
		}
	}
}
*/



/*
function doItemcode_sl_salesitem(formName, element_name)
{
	var Item 				= eval("document."+ formName).elements["Item"].value;
	if( Item =="" ){
		alert("必须输入货号!");
	}else{
		eval("document."+ formName).elements["SItem"].value = Item;
	}
}


function doItemcode_pu_buyitem(formName, element_name)
{
	var Item 				= eval("document."+ formName).elements["Item"].value;
	if(Item =="" ){
		alert("必须输入货号!");
	}else{
		eval("document."+ formName).elements["BItem"].value = Item;
	}
}
*/
/*
function doPu_supplier(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/pu_supplier.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:25;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  //alert(theValue);
	if(theValue==null){
	  theValue="";
	}
	
	if(theValue.length==0 || theValue[0]=="undefined"){
		//FormHead.parentitem.value="";
		//返回为空值,不操作.
		//eval("document."+ formName).elements[element_name].value = "";
	}else{
		//FormHead.parentitem.value = theValue[0];
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		//eval("document."+ formName).elements["SupplierID"].value 			= theValue[1];
		eval("document."+ formName).elements["SupplierID"].value 				= theValue[0];
	}
}
*/


/*
function doSl_customer(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/sl_customer.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:25;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  //alert(theValue);
	if(theValue==null){
	  theValue="";
	}
	
	if(theValue.length==0 || theValue[0]=="undefined"){
		//FormHead.parentitem.value="";
		//返回为空值,不操作.
		//eval("document."+ formName).elements[element_name].value = "";
	}else{
		//FormHead.parentitem.value = theValue[0];
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		//eval("document."+ formName).elements["SupplierID"].value 			= theValue[1];
		eval("document."+ formName).elements["CustomerID"].value 				= theValue[0];
	}
}
*/

/*
function doSl_shipping_view(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/sl_shipping_view.jsp?time="+cur_time;
	var style			= "status:no;help:no;dialogWidth:25;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  if(theValue==null){
	  theValue="";
	}
	if(theValue.length==0 || theValue[0]=="undefined"){
		//返回为空值,不操作.
	}else{
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		eval("document."+ formName).elements["CustomerID"].value 			= theValue[0];
		eval("document."+ formName).elements["ShippingID"].value 			= theValue[1];
	}
}
*/

/*
function doSl_salesitem_view(formName, element_name)
{
	//var CustomerID= eval("parent.menuframe.document.controlPane").elements["CustomerID"].value;
	if(parent.menuframe.document.controlPane.CustomerID != undefined){
		CustomerID = parent.menuframe.document.controlPane.CustomerID.value;
	}
	
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/sl_salesitem_view.jsp?time="+cur_time+"&CustomerID="+CustomerID;
	var style			= "status:no;help:no;dialogWidth:30;dialogHeight:23;dialogTop:100;dialogLeft:250"
  var theValue 	= window.showModalDialog(theUrl,null,style);
  
  //alert(theValue);
	if(theValue==null){
	  theValue="";
	}
	
	if(theValue.length==0 || theValue[0]=="undefined"){
		//FormHead.parentitem.value="";
		//返回为空值,不操作.
		//eval("document."+ formName).elements[element_name].value = "";
	}else{
		//FormHead.parentitem.value = theValue[0];
		//eval("document."+ formName).elements[element_name].value 	= theValue[0];
		eval("document."+ formName).elements["SItem"].value 			= theValue[0];
		eval("document."+ formName).elements["Item"].value 				= theValue[1];
		eval("document."+ formName).elements["Currency"].value 		= theValue[2];
		eval("document."+ formName).elements["Price"].value 			= theValue[3];
		
	}
}
*/

/*
function doItemcode_for_Salesman(formName, element_name)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/ba_item_for_Salesman.jsp?time="+cur_time;
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
		//FormHead.parentitem.value = theValue[0];
		eval("document."+ formName).elements[element_name].value = theValue[0];
	}
}
*/

/*
function doItemcode_para(formName, element_name, parastr)
{
	var today 		= new Date();
	var cur_time	= today.getTime();
	var theUrl 		= "../poppage/ba_item.jsp?time="+cur_time+"&parastr="+parastr;
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
		//FormHead.parentitem.value = theValue[0];
		eval("document."+ formName).elements[element_name].value = theValue[0];
	}
}
*/

