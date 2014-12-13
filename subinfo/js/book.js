var id_queryframe_clicked = false;
/*
function Show_Sub(id_num,num){ //舌签功能
	for(var i = 0;i <= 9;i++){
		if(GetObj("S_Menu_" +  i)){
			GetObj("S_Menu_" +  i).className = '';
		}
		if(GetObj("S_Cont_" +  i)){
			GetObj("S_Cont_" +  i).style.display = 'none';
		}
	}
	if(GetObj("S_Menu_"  + num)){
		GetObj("S_Menu_"  + num).className = 'selectd';
	}
	if(GetObj("S_Cont_" + num)){
		GetObj("S_Cont_" + num).style.display = 'block';
	}
}*/

function Show_Sub(frameName){ //舌签功能
	//GetObj("S_Menu_" +  "listframe").className = '';
	//GetObj("listframe").style.display = 'none';
	//GetObj("S_Menu_" +  "editframe").className = '';
	//GetObj("editframe").style.display = 'none';
	
	//alert(frameName);
	if(GetObj("S_Menu_" +  "listframe")){
		GetObj("S_Menu_" +  "listframe").className = '';
	}  
	if(GetObj("S_Menu_" +  "editframe")){
		GetObj("S_Menu_" +  "editframe").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_id")){
		GetObj("S_Menu_" +  "editframe_id").className = '';
	}
	if(GetObj("S_Menu_" +  "reportframe")){
		GetObj("S_Menu_" +  "reportframe").className = '';
	}
	
	
	
	if(GetObj("S_Menu_" +  "master_listframe")){
		GetObj("S_Menu_" +  "master_listframe").className = '';
	}  
	if(GetObj("S_Menu_" +  "master_editframe")){
		GetObj("S_Menu_" +  "master_editframe").className = '';
	}
	if(GetObj("S_Menu_" +  "MD_editframe")){
		GetObj("S_Menu_" +  "MD_editframe").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_audit")){
		GetObj("S_Menu_" +  "editframe_audit").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_payoff")){
		GetObj("S_Menu_" +  "editframe_payoff").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_archive")){
		GetObj("S_Menu_" +  "editframe_archive").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_excel")){
		GetObj("S_Menu_" +  "editframe_excel").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_text")){
		GetObj("S_Menu_" +  "editframe_text").className = '';
	}
	
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_basic")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_basic").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_work")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_work").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_4050")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_4050").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_retire")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_retire").className = '';
	}  
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_62retire")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_62retire").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_died")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_died").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_leavework")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_leavework").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_lostlabour")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_lostlabour").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_leavework")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_leavework").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_worker")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_worker").className = '';
	}		
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_workinjure")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_workinjure").className = '';
	}	
	
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_modeChange")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_modeChange").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_alert")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_alert").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_leaderretire")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_leaderretire").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_peopleinfo_leader")){
		GetObj("S_Menu_" +  "editframe_peopleinfo_leader").className = '';
	} 

	if(GetObj("S_Menu_" +  "editframe_basic")){
		GetObj("S_Menu_" +  "editframe_basic").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_plan")){
		GetObj("S_Menu_" +  "editframe_plan").className = '';
	}
		
	if(GetObj("S_Menu_" +  "editframe_process")){
		GetObj("S_Menu_" +  "editframe_process").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_procedure")){
		GetObj("S_Menu_" +  "editframe_procedure").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_dinge")){
		GetObj("S_Menu_" +  "editframe_dinge").className = '';
	}
	
	if(GetObj("S_Menu_" +  "editframe_wuliu")){
		GetObj("S_Menu_" +  "editframe_wuliu").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_buy")){
		GetObj("S_Menu_" +  "editframe_buy").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_quality")){
		GetObj("S_Menu_" +  "editframe_quality").className = '';
	}
	
	
	
	
	
	
	if(GetObj("S_Menu_" +  "editframe_stock")){
		GetObj("S_Menu_" +  "editframe_stock").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_cost")){
		GetObj("S_Menu_" +  "editframe_cost").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_physics")){
		GetObj("S_Menu_" +  "editframe_physics").className = '';
	} 
	
	if(GetObj("S_Menu_" +  "editframe_work")){
		GetObj("S_Menu_" +  "editframe_work").className = '';
	} 
	if(GetObj("S_Menu_" +  "editframe_salary")){
		GetObj("S_Menu_" +  "editframe_salary").className = '';
	}			
	if(GetObj("S_Menu_" +  "editframe_family")){
		GetObj("S_Menu_" +  "editframe_family").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_post")){
		GetObj("S_Menu_" +  "editframe_post").className = '';
	}
	if(GetObj("S_Menu_" +  "editframe_jc")){
		GetObj("S_Menu_" +  "editframe_jc").className = '';
	}
	if(GetObj("S_Menu_" +  "itemframe")){
		GetObj("S_Menu_" +  "itemframe").className = '';
	}
	if(GetObj("S_Menu_" +  "parentlistframe")){
		GetObj("S_Menu_" +  "parentlistframe").className = '';
	}
	if(GetObj("S_Menu_" +  "paperframe")){
		GetObj("S_Menu_" +  "paperframe").className = '';
	}
	if(GetObj("S_Menu_" +  "treeframe")){
		GetObj("S_Menu_" +  "treeframe").className = '';
	}
	if(GetObj("S_Menu_" +  "layerframe")){
		GetObj("S_Menu_" +  "layerframe").className = '';
	}
	
	
		
	if(GetObj("S_Menu_"  + frameName)){
		GetObj("S_Menu_"  + frameName).className = 'selectd';
	}
	
	
	
	
	/*
	if(GetObj(frameName)){
		GetObj(frameName).style.display = 'block';
	}*/
	parent.switchOnIframe( frameName);
	parent.changeIframe(frameName);
}




/*
function GetObj(objName){
	if(document.getElementById){
		return eval('document.getElementById("' + objName + '")');
	}else{
		return eval('document.all.' + objName);
	}
}*/ 

function GetObj(objName){
	if(document.getElementById){
		return eval('document.getElementById("' + objName + '")');
	}else{
		return eval('document.all.' + objName);
	}
}

function GetParentObj(objName){
	if(parent.document.getElementById){
		return eval('parent.document.getElementById("' + objName + '")');
	}else{
		return eval('parent.document.all.' + objName);
	}
	
	
}


function doGeneralQueryCon(queryframe, div_queryframe,locationvalue,special_choice){ 
	if(!id_queryframe_clicked){
		if(document.getElementById('id_queryframe') != undefined){
			document.getElementById('id_queryframe').click();
			document.getElementById('id_queryframe').click();
			id_queryframe_clicked = true; 
		} 
	}
	eval(special_choice)
	if(parent.document.getElementById(queryframe).style.display == 'block'){ 
		parent.document.getElementById(queryframe).style.display 		= 'none'; 
		parent.document.getElementById(div_queryframe).style.display = 'none'; 
	} 
	else if(parent.document.getElementById(queryframe).style.display == 'none' || parent.document.getElementById(queryframe).style.display == ''){ 
		parent.document.getElementById(queryframe).style.display 		= 'block'; 
		//alert(eval('parent.'+ queryframe+ '.location.href)) ;
		if( eval('parent.'+queryframe+'.location.href') == 'about:blank'){ 
			eval('parent.'+queryframe+'.location =locationvalue');	
			//alert('hhh') 
		}		parent.document.getElementById(div_queryframe).style.display = 'block'; 
	} 
}      