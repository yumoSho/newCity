var maxWidth=100;

var maxHeight=100;

var fileTypes=["bmp","gif","png","jpg","jpeg"];

var outImage="previewField";

var defaultPic="spacer.gif";



function preview(what){
var source=what.value;		
		var ext=source.substring(source.lastIndexOf(".")+1,source.length).toLowerCase();
		for (var i=0; i<fileTypes.length; i++) if (fileTypes[i]==ext) break;
		globalPic=new Image();
		if (i<fileTypes.length) globalPic.src=source;
		

else {
  globalPic.src=defaultPic;
  alert("�ļ����ʹ��������ͼƬ�ļ���׺��:"+fileTypes.join(", "));
}
setTimeout("applyChanges()",200);
}
var globalPic;
function applyChanges(){
var field=document.getElementById(outImage);
var x=parseInt(globalPic.width);
var y=parseInt(globalPic.height);
if (x>maxWidth) {
  y*=maxWidth/x;
  x=maxWidth;
}
if (y>maxHeight) {
  x*=maxHeight/y;
  y=maxHeight;
}

field.src=globalPic.src;
field.width=x;
field.height=y;
}