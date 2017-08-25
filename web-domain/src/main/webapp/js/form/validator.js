
function isInt(num){
	var reg=new RegExp("^[-\\+]?\\d+$");	
	if(!reg.test(num)||num.length==0){
		return false;
	}
	return true;
}
function isDouble(num){
	var reg=new RegExp("^[-\\+]?\\d+(\\.\\d+)?$");
	if(!reg.test(num)||num.length==0){
		return false;
	}
	return true;
}


//匹配国内电话号码(0511-4405222 或 021-87888822) 
function istell(str) 
{ 
var result=str.match(/\d{3}-\d{8}|\d{4}-\d{7}/); 
if(result==null) return false; 
return true; 
} 


// 判断输入是否是有效的长日期格式 - "YYYY-MM-DD HH:MM:SS" || "YYYY/MM/DD HH:MM:SS" 
function isdatetime(str) 
{ 
var result=str.match(/^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/); 
if(result==null) return false; 
var d= new Date(result[1], result[3]-1, result[4], result[5], result[6], result[7]); 
return (d.getFullYear()==result[1]&&(d.getMonth()+1)==result[3]&&d.getDate()==result[4]&&d.getHours()==result[5]&&d.getMinutes()==result[6]&&d.getSeconds()==result[7]); 
} 


// 检查是否为 YYYY-MM-DD || YYYY/MM/DD 的日期格式 
function isdate(str){ 
var result=str.match(/^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
if(result==null) return false; 
var d=new Date(result[1], result[3]-1, result[4]); 
return (d.getFullYear()==result[1] && d.getMonth()+1==result[3] && d.getDate()==result[4]); 
} 


// 判断输入是否是有效的电子邮件 
function isemail(str) 
{ 
var result=str.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/); 
if(result==null) return false; 
return true; 
} 


//匹配中国邮政编码(6位) 
function ispostcode(str) 
{ 
var result=str.match(/[1-9]\d{5}(?!\d)/); 
if(result==null) return false; 
return true; 
} 


function ismobile(str){ 

var result=str.match(/^1[3|4|5|8][0-9]\d{4,9}$/); 
if(result==null) return false; 
return true;
} 


//匹配腾讯QQ号 
function isqq(str) 
{ 
var result=str.match(/[1-9][0-9]{4,}/); 
if(result==null) return false; 
return true; 
} 


//匹配身份证(15位或18位) 
function isidcard(str) 
{ 
var result=str.match(/\d{15}|\d{18}/); 
if(result==null) return false; 
return true; 
}

//非空
function isNull(str){
	if(str==undefined || str == 'undefined' || str == null || str == ''){
		return true;
	}
	return false;
}

String.prototype.Trim = function(){   
	return this.replace(/(^\s*)|(\s*$)/g, "");   
}
String.prototype.LTrim = function(){   
	return this.replace(/(^\s*)/g, "");   
}   
String.prototype.RTrim = function() {   
	return this.replace(/(\s*$)/g, "");   
}  

