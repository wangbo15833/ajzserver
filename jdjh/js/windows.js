function winOpen(url, width, height, top, left){
	var attr = "width="+width+",height="+height+", location=1, menubar=1, scrollbars=1, toolbar=1, status=1, resizable=1, top="+top+",left="+left;
	var myWin = window.open(url, "testWindow", attr);
	myWin.focus();
}