// JavaScript Document
/**
 * Created by dev on 2016/5/11.
 */
$.fn.upload = function (options) {
    options = options || {};
    options.dom = this; 
    $.upload(options);
};
$.upload = function (options) {
	 
    var settings = {
        location: "body", //iframe等元素渲染的位置
        dom: "",
        action: upload_base_url + "pic.upload",
        fileName: "file",
        imgId : "logoUrl",
        inputId : "logo",
        formId : "uploadForm_" + Math.random() * 100,
        form : $("<form></form>"),
        params: {},
        accept: ".jpg,.png",
        ieSubmitBtnText: "上传",
        dataType: "text",
        valid: function () {
            return true;
        },
        bindEvent : function(){
            var o = this;
            $("#"+ o.imgId).load(function(){
                o.resize();
            });
            $("#"+ o.imgId).error(function(){
                o.resize();
            });
        },
        resize : function() {
            var form = this.form;
            var ele = settings.dom;
            form.css({
                top: (ele.position().top),
                left: (ele.position().left)
            });
        },
        submit: function () {
            var o = this;
            var form = this.form;
            var formData = new FormData(form[0]);
			console.log(":"+form.attr("action"));
            $.ajax({
                    type : 'POST',
                    contentType: false,
                    processData: false,
					beforeSend: function(XMLHttpRequest){
						var token = $.cookie('token' );  
						console.log("upload-token-by:" + token);
						XMLHttpRequest.setRequestHeader("token",token);
					},
					xhrFields: {
					  withCredentials: true
				   	},
                    url : form.attr("action"),
                    data: formData,
                    dataType:"json",
                    success : function(data){o.success(data)},
                    error : function(e) {
                        alert(JSON.stringify(e));
                    }
            });
        },
        success : function(data) {
            if( !data ){
                return ;
            }
			if( data.result == 1000 ){
				alert('请重新登录');
				return;
			}
			
			if( data.message ){
				data = data.message;
			}
			
            var file = data.file;
			if( !file ){
				return;
			}
            if(file.err == ""){
                var img = file.path;
                var resourceId = file.resourceid;
				console.log( "upload : " + resourceId + " > " + img);
				console.log( "upload url : " + resources_base_url + img);
				var obj = $('#'+this.imgId).attr("value");
                if(this.imgId){
                    $('#'+this.imgId).attr("src",resources_base_url + img);
                    $('#'+this.imgId).attr("resourceId" , resourceId ); 
					if( obj ){
						$("#"+obj).val( resourceId );
					}
                } 
				//填充ID
                if(this.inputId){ 
					if( !obj ){
						$('#'+this.inputId).val(resourceId);
					} 
                }
            }
        }
    }
    settings = $.extend(settings, options);
    var ele = settings.dom;
    if (!ele) return;
    var width = ele.outerWidth();
    var height = ele.outerHeight();
	//from element
    var form = settings.form;
    form.attr({
        action: settings.action,
        method: "post",
        "class": "upload_back_form",
        enctype: "multipart/form-data"
    })
	form.css({
        width: width,
        height: height,
		border:1,
     //   position: "absolute",
        top: (ele.position().top),
        left: (ele.position().left)
    });
	//file element
    var fileInput = $("<input type='file'/>");
    fileInput.attr({
            accept: settings.accept,
            name: settings.fileName
    });
	fileInput.css({
            width: width + "px",
            height: height + "px"
    });
    form.append(fileInput); 
	//
	var _imgID = settings.imgId;
	var _compressAttr = $('#'+_imgID).attr( "compress" );

	if( _compressAttr ){
		var _compress = $("<input type='text'/>");
		_compress.attr({ 
				name: settings.fileName + "_compress"
				, value: _compressAttr
		});
		form.append(_compress); 	
	}

	
	
    $("body").append(form);
    fileInput.change( function uploadEvent(  ) {
        if (settings.valid.call(null, form)) {
            var fileName = this.value;
            if (fileName != '') {
                var fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                var pattern = /^(.jpg|.jpeg|.png|.bmp)$/;
                if (!pattern.test(fileExt)) {
                    alert("请上传正确的图片格式（.jpg|.jpeg|.png|.bmp）");
                } else {
                    settings.submit();
                }
            }
        }
	}); 
	$("#"+ settings.imgId).click(function(){fileInput.click()}); 
	var eventsrc = $("#"+ settings.imgId).attr("eventsrc");
	if( eventsrc ){
		$("#"+ eventsrc).click(function(){fileInput.click()}); 
	}
	
    for (var param in settings.params) {
        var div = $("<input type='hidden'/>").attr({name: param, value: settings.params[param]});
        input.after(div)
        div = null;
        delete div;
    };
	
    settings.bindEvent();
};

function setResourceId( data , key ){
	var resourceId = $("#" + key ).attr("resourceId");
	if(resourceId){
		eval("data.resource."+key + "= resourceId") ;
	}
}
/**
 * 创建上传组件
 * @param divEl   渲染所在区域
 * @param eventsrc  事件源
 * @param imgId   需要显示图片的IMGID
 * @param inputId  需要赋值的隐藏域ID
 * createUploader($(".page-content"),$('#js-upload-logo'),'logoUrl','logo');
  
function RenderUpload(){ 

	 $(".uploader").each(function(index,element){ 
			console.log(" Render : " + element.id);
			$("#"+ element.id ).upload({  imgId : element.id  });
	 }) 
}*/ 

function RenderUpload(){
	// alert(123);
	 $(".uploader").each(function(index,element){ 
			console.log(" Render : " + element.id);
			$("#"+ element.id ).upload({  imgId : element.id  });
	 }) 
}


