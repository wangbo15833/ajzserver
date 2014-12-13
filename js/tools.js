function checkboxClick(elementName, checkedValue, uncheckedValue)
{
	if(eval("document.all."+elementName).checked == true){
		eval("document.all."+elementName).value = checkedValue;
	}else{
		eval("document.all."+elementName).value = uncheckedValue;
	}
	//alert(eval("document.all."+elementName+".value"));
}

function confirmButton(operationMode, executedMethod){
	var ok = false; 
	if(operationMode== 'input' ){ 
		ok= window.confirm('您将要输入数据,\n'+'您确定吗?')	
	}	
	else if(operationMode== 'update' ){ 
		ok= window.confirm('您将要修改数据,\n'+'您确定吗?')	
	}	
	else if(operationMode== 'delete' ){ 
		ok= window.confirm('您将要删除数据,\n'+'您确定吗?')	
	}	
	if(ok==true)
	{
		eval(executedMethod);
	}
}


function confirmButton(operationMode, executedMethod, info){ 
	if(info == undefined){
		info = "";
	} 
	var ok = false 
	if(operationMode== 'input' ){ 
		ok= window.confirm('您将要输入'+info+'数据,\n'+'您确定吗?')	
	}	
	else if(operationMode== 'update' ){ 
		ok= window.confirm('您将要修改'+info+'数据,\n'+'您确定吗?')	
	}	
	else if(operationMode== 'delete' ){ 
		ok= window.confirm('您将要删除'+info+'数据,\n'+'您确定吗?')	
	}	
	if(ok==true)
	{
		eval(executedMethod);
	}
}

/*
//清除编辑文本框中的内容,修改时间：2009-04-29
//输入参数
a, 表单名字
array_device，清除单元个数组
jk,单元格数组行号

*/
function doDelCellValue_line(a,array_device, jk){
	for(i=0; i<array_device.length; i++){
		//alert(a+"."+array_device[i]+jk);
		if(array_device[i] == 'Line' || array_device[i] =='id'){
			continue;
		}
		eval(a+"."+array_device[i]+jk).value ='';
	}
}


function judgeDigtal(val){
	var v_len = val.length;
	var ary_digtal = new Array();
	ary_digtal[0] = ".";
	for(var k=0;k<10; k++){
		ary_digtal[k+1] = k+"";
	}
	
	var b_digtal = false;
	//alert(v_len);
	for(var i=0; i<v_len; i++){
		v_temp = val.substring(i,i+1);
		//判断一个字符是数字
		b_digtal = false;
		for(var j=0; j<ary_digtal.length; j++){
			//alert("ary_digtal["+j+"]="+ary_digtal[j]+",v_temp="+v_temp);
			if(ary_digtal[j] == v_temp){
				b_digtal = true;
				break;
			}
		}
		if(!b_digtal){
			;//不是数字了；
			break;
		}
	}
	return b_digtal;
}

//将空字符串转换为0;
function nvl0(val){
	//alert(val);
	val = nvl(val);
	if(val == ''){
		return '0';
	}else{
		return val;
	}
}

//去掉空字符串
function nvl(val){
	if(val == undefined || val ==null || val =='null'  ){
		return '';
	}else {
		var v_temp = null; 
		//alert('val='+val);
		v_temp = val.toLowerCase();
		//alert('v_temp='+v_temp);
		if(v_temp == 'nan'){
			return '0';
		}else{
			return val;
		}
	}
}


