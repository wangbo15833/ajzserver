//编辑列表的点击维护操作
function passValue_click_1(checkbox_prefix, num){
	
}



function openIframe(frameName){
	document.getElementById(frameName).style.display = "block";
}

function closeIframe(frameName){
	document.getElementById(frameName).style.display = "none";
}


function editTitleHeadFunction( displayContent,  operationMode, displayMode ){
	//设置显示按钮的值
	document.getElementById('editSubmit').value = displayMode;
	//修改并记录操作模式
	operation_mode = operationMode;
	if(operationMode == 'input'){
		//控制按钮表是否显示
		document.getElementById('editSubmit_td').style.display = 'block';
		//改变editHeadTile的颜色
		document.getElementById('sub_title_dislay').innerHTML ='<font color=green>'+displayContent+'</font>';
	}
	else if(operationMode == 'update'){
		document.getElementById('editSubmit_td').style.display = 'block';
		document.getElementById('sub_title_dislay').innerHTML ='<font color=red>'+displayContent+'</font>';
	}
	else if(operationMode == 'delete'){
		document.getElementById('editSubmit_td').style.display = 'block';
		document.getElementById('sub_title_dislay').innerHTML ='<font color=yellow>'+displayContent+'</font>';
	}
	else if(operationMode == 'detail' || 'edit'){
		document.getElementById('sub_title_dislay').innerHTML ='<font color=green>'+displayContent+'</font>';
		//查询框页面
		document.getElementById('editSubmit_td').style.display = 'none';
	}
	else{
		document.getElementById('editSubmit_td').style.display = 'block';
		document.getElementById('sub_title_dislay').innerHTML ='<font color=green>'+displayContent+'</font>';
	}
}


function editTitleHeadFunction_notitle( displayContent,  operationMode, displayMode ){
	//设置显示按钮的值
	document.getElementById('editSubmit').value = displayMode;
	//修改并记录操作模式
	operation_mode = operationMode;
	document.getElementById('editSubmit_td').style.display = 'none';
	if(operationMode == 'input'){
		//控制按钮表是否显示
		document.getElementById('editSubmit_td').style.display = 'none';
		//改变editHeadTile的颜色
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=green>'+displayContent+'</font>';
	}else if(operationMode == 'update'){
		document.getElementById('editSubmit_td').style.display = 'none';
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=red>'+displayContent+'</font>';
	}else if(operationMode == 'delete'){
		document.getElementById('editSubmit_td').style.display = 'none';
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=yellow>'+displayContent+'</font>';
	}else if(operationMode == 'detail'){
		//查询框页面
		document.getElementById('editSubmit_td').style.display = 'none';
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=green>'+displayContent+'</font>';
	}else{
		document.getElementById('editSubmit_td').style.display = 'none';
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=green>'+displayContent+'</font>';
	}
}

/*
function editTitleHeadFunction_notitle( displayContent,  operationMode, displayMode ){
	//设置显示按钮的值
	document.getElementById('editSubmit').value = displayMode;
	//修改并记录操作模式
	operation_mode = operationMode;
	if(operationMode == 'input'){
		//控制按钮表是否显示
		document.getElementById('editSubmit_td').style.display = 'block';
		//改变editHeadTile的颜色
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=green>'+displayContent+'</font>';
	}else if(operationMode == 'update'){
		document.getElementById('editSubmit_td').style.display = 'block';
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=red>'+displayContent+'</font>';
	}else if(operationMode == 'delete'){
		document.getElementById('editSubmit_td').style.display = 'block';
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=yellow>'+displayContent+'</font>';
	}else if(operationMode == 'detail'){
		//查询框页面
		document.getElementById('editSubmit_td').style.display = 'none';
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=green>'+displayContent+'</font>';
	}else{
		document.getElementById('editSubmit_td').style.display = 'block';
		//document.getElementById('sub_title_dislay').innerHTML ='<font color=green>'+displayContent+'</font>';
	}
}*/

function queryTitleHeadFunction( displayContent){
	document.getElementById('sub_title_dislay').innerHTML ='<font color=black>'+displayContent+'</font>';
}

function queryTitleHeadFunction_notitle( displayContent){
	//document.getElementById('sub_title_dislay').innerHTML ='<font color=black>'+displayContent+'</font>';
}
