// JavaScript Document
function UploadApi(){
	
	this.isNull = function(str){
		return (str == '' || str == null || str == 'undefined');
	}
	
	this.getParam = function(url,name){
		var params = new Array();var urls = url.split("?");
		var paramArr = urls[1].split("&");
		for(var i=0;i<paramArr.length;i++){
			var temp = paramArr[i].split("=");
			params[temp[0]] = temp[1];
		}
		var paramValue = params[name];
		return paramValue;
	}
	
	this.open = function(){
		
//		var child_value = window.showModalDialog('"+path+"/common/up/"+type+".jsp\',window,'dialogWidth:"+dialogWidth+";status:no;dialogHeight:"+dialogHeight+"'); \n"+
//		"	if(isNull(child_value))return;"+ 
//		"	var photo = getParam(child_value,'photo');var uri = getUri(child_value);"+
//		"	var up_value = uri; \n"+    
//		"	document.getElementById('view_img').src ='"+path+"/'+ up_value; \n"+
//		"	document.getElementById('ch_up').value = up_value; \n"+ 
//		"}
	
	}
	
}