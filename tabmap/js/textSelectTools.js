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

function fill(SelectCtrl, itemArray){  //填充select
  	var i;
	for (i = SelectCtrl.options.length; i>=0; i--){
		SelectCtrl.options[i] = null;
	}
	if(itemArray != null ){
		for(i = 0; i<itemArray.length; i++){
			SelectCtrl.options[i] = new Option(itemArray[i]);
			if(itemArray[i] != null){
				SelectCtrl.options[i].value = itemArray[i];
			}
		}
	}
}

function fillSelect(SelectCtrl, itemDisplayArray, itemValueArray){
  	var i;
	for (i = SelectCtrl.options.length; i>=0; i--){
		SelectCtrl.options[i] = null;
	}
	if(itemDisplayArray != null && itemValueArray != null ){
		for(i = 0; i<itemDisplayArray.length; i++){
			SelectCtrl.options[i] = new Option(itemDisplayArray[i]);
			if(itemValueArray[i] != null){
				SelectCtrl.options[i].value = itemValueArray[i];
			}
		}
	}
}

function selectChosen(SelectCtrl, chosenItem){//选中select
  	var i ;
	i = SelectCtrl.options.length;
	var j =0;
	
	var proof = "www";
	while( j<i && proof == "www" ){
		if(SelectCtrl.options[j].value == chosenItem){
			SelectCtrl.options[j].selected = true;
			proof = "";
		}
		j++;
	}
}

/*
*设置 Select 的值,若为"--==选择==--",则选择空值
*/
function setSelectValue(selectName){
 	if(selectName.value == "--==选择==--") {
		selectName.value = "";
	} 
	//alert(selectName.value);		
}

/*
*设置 Select 为空值
*/ 
function clearSelectValue(myForm){
	var allElements = myForm.elements;
	for(var i=0; i<allElements.length; i++){
		if(allElements[i].type == "select-one"){
			allElements[i].value = "";
		}
	} 		
}