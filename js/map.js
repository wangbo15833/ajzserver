var pln=119.591585;
var pla=39.94528;
function $s(s){
	return document.getElementById(s);
	}
function $$(s){
	return document.getElementByName(s);
	}
    //创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
        addMarker();//向地图中添加marker
    }
    
    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("baidumap");//在百度地图容器中创建一个地图
        var point = new BMap.Point(pln,pla);//定义一个中心点坐标
        map.centerAndZoom(point,14);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
    }
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
    }
    
    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
 //var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
 //map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
 var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
 map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
 map.addControl(ctrl_sca);
    

   	
	}
    
    //标注点数组
	
    //var markerArr = [{title:"微讯软件",content:"电话：0335-1234567<br/>手机：1330001111",point:"119.591585|39.94528",isOpen:1,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}];
   
    var markerArr = [{title:"",content:"",point:"",isOpen:0,icon:{w:23,h:25,l:46,t:21,x:9,lb:12}}];

    //创建marker
    function addMarker(){
        for(var i=0;i<markerArr.length;i++){
				var json = markerArr[i];
				var p0 = json.point.split("|")[0];
				var p1 = json.point.split("|")[1];
				var point = new BMap.Point(p0,p1);
				var iconImg = createIcon(json.icon);
				var marker = new BMap.Marker(point,{icon:iconImg});
				var iw = createInfoWindow(i);
				var label = new BMap.Label(json.title,{"offset":new BMap.Size(json.icon.lb-json.icon.x+10,-20)});
				marker.setLabel(label);
				map.addOverlay(marker);
				label.setStyle({
                        borderColor:"#808080",
                        color:"#333",
                        cursor:"pointer"
            });
   
  (function(){
    var index = i;
    var _iw = createInfoWindow(i);
    var _marker = marker;
    _marker.addEventListener("click",function(){
        this.openInfoWindow(_iw);
       });
       _iw.addEventListener("open",function(){
        _marker.getLabel().hide();
       })
       _iw.addEventListener("close",function(){
        _marker.getLabel().show();
       })
    label.addEventListener("click",function(){
	alert(e.point);
        _marker.openInfoWindow(_iw);
       })
    if(!!json.isOpen){
     label.hide();
     _marker.openInfoWindow(_iw);
    }
   })()
        }
    }
    //创建InfoWindow
    function createInfoWindow(i){
        var json = markerArr[i];
        var iw = new BMap.InfoWindow("<b class='iw_poi_title' title='" + json.title + "'>" + json.title + "</b><div class='iw_poi_content'>"+json.content+"</div>");
        return iw;
    }
    //创建一个Icon
    function createIcon(json){
        var icon = new BMap.Icon("js/baidumapico/us_cursor.gif", new BMap.Size(json.w,json.h),{imageOffset: new BMap.Size(-json.l,-json.t),infoWindowOffset:new BMap.Size(json.lb+5,1),offset:new BMap.Size(json.x,json.h)})
        return icon;
    }
function showmap(){
	try{
		//alert($s("baidumap"));
		if($("#baidumap").css("z-index")!="9" && $("#baidumap").css("z-index")!="-9"){
			var ln=$("#longtitude").val().replace(/\s/gm,"");
			var la=$("#latitude").val().replace(/\s/gm,"");
			if (ln!="" && la!=""){
				pln=parseFloat(ln);
				pla=parseFloat(la);
				
				markerArr[0].point=ln+"|"+la;
			}
			var mw = $('#baidumap')[0].clientWidth;
			var mh = $('#baidumap')[0].clientHeight;
			var dh = document.body.clientHeight;
			var dw = document.body.clientWidth;
			var dl = $('#man_zone')[0].scrollLeft;
			var dt = $('#man_zone')[0].scrollTop;
			//alert("mw:"+mw+";  "+"mh:"+mh+";  "+"dh:"+dh+";  "+"dw:"+dw+";  "+"dl:"+dl+";  "+"dt:"+dt+";  ");
			//$s('baidumap').style.top=(dh-dt-mh)/2;
			//$s('baidumap').style.left=(dw-dl-mw)/2;
			$('#baidumap').css("top",(dh-dt-mh)/2);
			$('#baidumap').css("left",(dw-dl-mw)/2);
			//alert($s('baidumap').top);
			$('#baidumap').css("z-index","9");
			initMap();//创建和初始化地图
			map.addEventListener("click",function(e){   
				newpoint = new BMap.Point(e.point.lng,e.point.lat);

				$("#longtitude")[0].value = e.point.lng; 
				$("#latitude")[0].value = e.point.lat;    //输出数组里的经纬度
				//调用map.removeOverlay或者map.clearOverlays方法时，API会自动将initialize方法返回的DOM元素进行移除
				map.clearOverlays();
				markerArr[0].point=e.point.lng+"|"+e.point.lat;
				addMarker();
				//return false;
			});
			
		}else{
			$("#baidumap").css("z-index",$("#baidumap").css("z-index")=="9"?"-9":"9");
		}
	}catch(e){
	alert(e.description );
	}
}
function hidemap(){
	//$s('baidumap').style.zIndex="-9";
	$('#baidumap').css({"z-index":"-9"});
}
$(document).ready(function(ev){
	$(document).click(function(ev){
		var ev  =  ev  ||  window.event;
		var target =  ev.target  ||  ev.srcElement;
		var thiseleid=target.id;
		//alert(thiseleid);
		if(thiseleid!="latitude" && thiseleid!="longtitude" && $("#baidumap").css("z-index")=="9"){hidemap()}
	});
	$("#baidumap").click(function(){
		return false;
	});
	$("#longtitude,#latitude").dblclick(function(){
		showmap();
	});
});