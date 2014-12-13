function  displayMinutesSecond( str){
	//var str = str_tobe;
	while(str.indexOf("^")>0){
		str = str.replace("^","\"");
	}
	
	while(str.indexOf("~")>0){
		str = str.replace("~","\'");
	}
	return str;		
}


function setValue(textID,selectID){
	//…Ë÷√œ‘ æ
	document.getElementById(textID).value = document.getElementById(selectID).value;	
}