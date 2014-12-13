var originColor   = 'white';
var  menuBar_int 	= 3;
function mouseOverColor(thisobj){
	originColor = thisobj.style.background;
	thisobj.style.background = '#bdd7d6';
	//thisobj.style.background = 'red';
}

function mouseOutColor(thisobj){
	//alert(originColor);
	thisobj.style.background = originColor;
}
function mouseOutColor_item(thisobj){
	//alert(originColor);
	thisobj.style.background = originColor;
}

function mouseClickColor(thisobj){
	//document.getElementById("menuBarAlink_1").style.background = 'red'
	for(var j =0; j<menuBar_int; j++){
		//alert("222");
		//alert(eval('document.getElementById("menuBarAlink_"+(j+1))').style.background);
		if(eval('document.getElementById("menuBarAlink_"+(j+1))') != undefined){
			eval('document.getElementById("menuBarAlink_"+(j+1))').style.background= '#dedfd7';
		}
	}
	thisobj.style.background = 'white';
	originColor =  'white';
}

function mouseClickColor_item(thisobj){
	document.getElementById("item_menuBarAlink_1").style.background = 'red'
	for(var j =0; j<menuBar_int; j++){
		//alert("222");
		//alert(eval('document.getElementById("item_menuBarAlink_"+(j+1))').style.background);
		eval('document.getElementById("item_menuBarAlink_"+(j+1))').style.background= '#dedfd7';
	}
	thisobj.style.background = 'white';
	originColor =  'white';
	//alert(originColor);
}


function mouseOverColor_menu(thisobj){
	originColor = thisobj.style.background;
	thisobj.style.background = '#bdd7d6';
	//alert("originColor:"+thisobj.style.background);
}

function mouseOutColor_menu(thisobj){
	thisobj.style.background = originColor;
	//alert(thisobj.style.background);
}

function mouseClickColor_menu(thisobj){
	for(var j =0; j<menuBar_int; j++){
	//menuBarAlink_1.style.background= '#dedfd7';
	//	if(originColor  != 'white')
	//		eval("menuBarAlink_"+(j+1)).style.background= originColor;
	}
	//thisobj.style.background = 'white';
	//originColor =  'white';
}





function doDisplayItem(){
	parent.closeIframe("editframe");
	parent.openIframe("queryframe");
}
function doCreateItem(){
	parent.openIframe("editframe");
	parent.closeIframe("queryframe");
	parent.editframe.sub_title_dislay("数据录入","input");
}

function doModifyItem()
{
	parent.openIframe("editframe");
	parent.closeIframe("queryframe");
	parent.editframe.sub_title_dislay("数据修改","update");
}

function doDeleteItem()
{
	parent.openIframe("editframe");
	parent.closeIframe("queryframe");
	parent.editframe.sub_title_dislay("数据删除","delete");
}

function resetQueryCon_title(){
	parent.parent.titleFrame.document.getElementById('namePage').innerHTML = '';
  parent.parent.titleFrame.document.getElementById('totalNum').innerHTML = '';
  parent.parent.titleFrame.document.getElementById('totalPage').innerHTML = '';
  parent.parent.titleFrame.document.getElementById('throughPage').innerHTML ='';
} 	
