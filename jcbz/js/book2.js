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



		
	if(GetObj("S_Menu_"  + frameName)){
		GetObj("S_Menu_"  + frameName).className = 'selectd';
	}
	
	
	/*
	if(GetObj(frameName)){
		GetObj(frameName).style.display = 'block';
	}*/
	switchOnIframe( frameName);
	changeIframe(frameName);
}




 

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

      