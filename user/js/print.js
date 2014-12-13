function printsetup(){ 
	document.all.wb.ExecWB(8,1); 
} 

function printpreview(){
	
	parent.parent.pageFrame.focus(); 
	if(document.all) {
		if(document.getElementById("notprint1") != 'undefined'){
			document.getElementById("notprint1").style.display ="none";
		}
		if(document.getElementById("notprint2") != 'undefined'){
			document.getElementById("notprint2").style.display ="none";
		}
		if(parent.document.getElementById("menuframe") != 'undefined'){
			parent.document.getElementById("menuframe").style.display ="none";
		}
		if(parent.parent.document.getElementById("idxFrame") != 'undefined'){
			parent.parent.document.getElementById("idxFrame").style.display ="none";
		}
		if(parent.parent.document.getElementById("titleFrame") != 'undefined'){
			parent.parent.document.getElementById("titleFrame").style.display ="none";
		}
		if(parent.parent.document.getElementById("topLogo") != 'undefined'){
			parent.parent.document.getElementById("topLogo").style.display ="none";
		}
		if(document.getElementById("queryConTable_id") != 'undefined'){
			//document.getElementById("queryConTable_id").style.display ="none";
		}
		
		
		//parent.closeIframe("masterframe");
		//parent.closeIframe("menuframe_report");
		//parent.closeIframe("bookframe");
		//parent.closeIframe("bookBlankframe");
		//parent.parent.toolframe.turn();
	}
	var agent = navigator.userAgent.toLowerCase();
	this.major = navigator.appVersion;
	var v1 = parseInt(navigator.appVersion);  
	var i = this.major.indexOf("MSIE");
	var v2=parseInt(this.major.substring(i+5,i+6));
	if(v1>4||v2>5){
		document.all.wb.ExecWB(7,1);
	}else  {
  		window.alert("此项功能适用于IE 5.0以上版本！");
  	}
   	if(document.getElementById("notprint1") != 'undefined'){
			document.getElementById("notprint1").style.display ="none";
		}
		if(document.getElementById("notprint2") != 'undefined'){
			document.getElementById("notprint2").style.display ="none";
		}
   
	//parent.openIframe("masterframe");
	//parent.openIframe("menuframe_report");
	//parent.openIframe("bookframe");
	if(parent.document.getElementById("menuframe") != 'undefined'){
		parent.document.getElementById("menuframe").style.display ="block";
	}
	if(parent.parent.document.getElementById("idxFrame") != 'undefined'){
		parent.parent.document.getElementById("idxFrame").style.display ="block";
	}
	if(parent.parent.document.getElementById("titleFrame") != 'undefined'){
		parent.parent.document.getElementById("titleFrame").style.display ="block";
	}
	if(parent.parent.document.getElementById("topLogo") != 'undefined'){
		parent.parent.document.getElementById("topLogo").style.display ="block";
	}
	
	if(document.getElementById("queryConTable_id") != 'undefined'){
		//document.getElementById("queryConTable_id").style.display ="block";
	}
	
}
 
function printit(){ 
	if(document.all) {
		notprint2.style.display ="none";
		notprint1.style.display ="none";
	}
	if (confirm('确定打印吗？')) { 
		document.all.wb.ExecWB(6,1);
	}
	notprint2.style.display ="block";
	notprint1.style.display ="block"; 
}