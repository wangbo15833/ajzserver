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
	var ok = false 
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