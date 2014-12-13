var trClickNum 	= 0;
var trChecked 	= 0;
function changeColor_click( tr_prefix, checkbox_prefix, num)
{
	/*********相关参数说明*********//*
	form_name:		表示选择行的表单名字
	tr_prefix:	表示选择行id的前缀；
	checkbox_prefix:表示选者框名字前缀
	checkboxItem:	表示选者框;
	num：				表示选择行号；
	idValue：		表示选择行的id值；
	*/
	//parent.menuframe.controlPane.id_value.value 		= idValue;		  //设置idvalue的值
	//document.controlPane.id_value.value 		= idValue;		  //设置idvalue的值
	//var trObj 		= document.getElementById("trname"+num);
	//var number 		= document.forms[form_name].thisItem.value;//记录上次的值！
	//var number 		= document.getElementById("thisItem").value;//记录上次的值！
	trClickNum = num;
	var number  = trChecked;
	
	
	//将上次选项的值复位
	if(  number>0 && number<13){ 
		//document.forms[form_name].elements[checkbox_prefix + number].checked = false;
		document.getElementById(checkbox_prefix+number).checked = false;
		(document.getElementById(tr_prefix+number)).style.backgroundColor = "white";
	}
		
	//设置本次的值
	if(document.getElementById(checkbox_prefix+num).checked){/*  本次checkbox框被选中   */
		document.getElementById(checkbox_prefix+num).checked	= true;
		//document.forms[form_name].thisItem.value 	= num;//设置记录的本次的值
		//document.getElementById("thisItem").value 	= num;//设置记录的本次的值
		
		trChecked = num;//设置记录的本次的值
		
		(document.getElementById(tr_prefix+num)).style.backgroundColor = "skyblue";
	}else{/*  本次checkbox框被复位   */
		document.getElementById(checkbox_prefix+num).checked	= false;
		//document.forms[form_name].thisItem.value 		= "";//checkbox框被复位,
		//document.getElementById("thisItem").value 	= "";//checkbox框被复位
		
		trChecked = 0;//设置记录的本次的值
		
		//parent.menuframe.controlPane.id_value.value 	= "";////checkbox框被复位, 需要将idValue也复位
		(document.getElementById(tr_prefix+num)).style.backgroundColor = "white";
	}
}

function changeColor_dblclick( tr_prefix, checkbox_prefix, num)
{
	/*********相关参数说明*********//*
	form_name:		表示选择行的表单名字
	tr_prefix:	表示选择行id的前缀；
	checkbox_prefix:表示选者框名字前缀
	checkboxItem:	表示选者框;
	num：				表示选择行号；
	idValue：		表示选择行的id值；
	*/
	//parent.menuframe.controlPane.id_value.value 		= idValue;		  //设置idvalue的值
	//document.controlPane.id_value.value 		= idValue;		  //设置idvalue的值
	//var trObj 		= document.getElementById("trname"+num);
	//var number 		= document.forms[form_name].thisItem.value;//记录上次的值！
	//var number 		= document.getElementById("thisItem").value;//记录上次的值！
	
	var number  = trChecked;
	
	
	//将上次选项的值复位
	if(  number>0 && number<13){ 
		//document.forms[form_name].elements[checkbox_prefix + number].checked = false;
		document.getElementById(checkbox_prefix+number).checked = false;
		(document.getElementById(tr_prefix+number)).style.backgroundColor = "white";
	}
		
	//设置本次的值
	//if(document.getElementById(checkbox_prefix+num).checked){/*  本次checkbox框被选中   */
	if(true){/*  本次checkbox框被选中   */
		document.getElementById(checkbox_prefix+num).checked	= true;
		//document.forms[form_name].thisItem.value 	= num;//设置记录的本次的值
		//document.getElementById("thisItem").value 	= num;//设置记录的本次的值
		
		trChecked = num;//设置记录的本次的值
		
		(document.getElementById(tr_prefix+num)).style.backgroundColor = "skyblue";
	}else{/*  本次checkbox框被复位   */
		document.getElementById(checkbox_prefix+num).checked	= false;
		//document.forms[form_name].thisItem.value 		= "";//checkbox框被复位,
		//document.getElementById("thisItem").value 	= "";//checkbox框被复位
		
		trChecked = 0;//设置记录的本次的值
		
		//parent.menuframe.controlPane.id_value.value 	= "";////checkbox框被复位, 需要将idValue也复位
		(document.getElementById(tr_prefix+num)).style.backgroundColor = "white";
	}
}

function changeColor_OverOut(tr_value, checkboxItem, mouseStatus)
{
	/*********相关参数说明*********/
	/*
	tr_value：	表示选择行的id值；
	checkboxItem:	表示选择行的checkbox的值
	mouseStatus:	表示鼠标over 与 out 标志；
	*/
	var trObj = document.getElementById(tr_value);
	if(!checkboxItem.checked){
		if(mouseStatus=='over'){
			trObj.style.backgroundColor = "lightBlue";
		}
		else if(mouseStatus=='out' ){
			trObj.style.backgroundColor = "white";
		}
	}
}


function changeColor2(itemName,itemValue,num,idValue)
{
	var trObj 		= document.getElementById("trname"+num);
	document.controlPane.id_value.value 	= idValue;
	var number 	= document.controlPane.thisItem.value;//记录上次的值！
	
	//比较上次的值，将上次的选项复位！！！
	if(number == 1){
		document.frm1.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 2){
		document.frm2.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 3){
		document.frm3.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 4){
		document.frm4.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 5){
		document.frm5.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 6){
		document.frm6.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 7){
		document.frm7.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 8){
		document.frm8.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 9){
		document.frm9.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 10){
		document.frm10.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	if(number == 11){
		document.frm11.itemLine.checked	= false;
		(document.getElementById("trname"+number)).style.backgroundColor = "white";
	}
	
	//修正本次的值
	if(itemName.checked){
		itemName.checked					= true;
		document.controlPane.thisItem.value = num;//设置纪录的值
		(document.getElementById("trname"+num)).style.backgroundColor = "red";
	}else{
		itemName.checked					= false;
		document.controlPane.thisItem.value = "";//
		(document.getElementById("trname"+num)).style.backgroundColor = "white";
	}
	
}

function changeColor(itemValue,flag,num)
{
	var trObj = document.getElementById("trname"+num);
	if(flag==1 && !itemValue){
		trObj.style.backgroundColor = "lightBlue";
	}
	else if(flag==0 && !itemValue){
		trObj.style.backgroundColor = "white";
	}
}

function mutiSelCheck(i){}