function checkboxClick(elementName, checkedValue, uncheckedValue)
{
	if(eval("document.all."+elementName).checked == true){
		eval("document.all."+elementName).value = checkedValue;
	}else{
		eval("document.all."+elementName).value = uncheckedValue;
	}
	//alert(eval("document.all."+elementName+".value"));
}

function closeGeneralQueryframe(queryframe, div_queryframe){
	parent.document.getElementById(queryframe).style.display = 'none';
	parent.document.getElementById(div_queryframe).style.display = 'none';
	//parent.parent.titleFrame.menuframe.closeQueryImg();
}

function closeGeneralQueryframe(queryframe){
	parent.document.getElementById(queryframe).style.display = 'none';
	//parent.document.getElementById(div_queryframe).style.display = 'none';
	//parent.parent.titleFrame.menuframe.closeQueryImg();
}
