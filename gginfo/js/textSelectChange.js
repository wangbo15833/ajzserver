function displayTextSelect(condition, divTextArea,divSelectArea,textID,selectID){
	if(condition)
		document.getElementById(divTextArea).style.display = "block";
	else
		document.getElementById(divTextArea).style.display = "none";
		
	if(condition)
		document.getElementById(divSelectArea).style.display = "none";
	else
		document.getElementById(divSelectArea).style.display = "block";
	//清空显示
	document.getElementById(textID).value = "";
	document.getElementById(selectID).value = "";
}


function setValue(textID,selectID){
	//设置显示
	document.getElementById(textID).value = document.getElementById(selectID).value;	
}