/**
 * Created by dev on 2016/5/23.
 */
$.fn.serializeJson = function () {
    var serializeObj = {};
    var array = this.serializeArray();
    $(array).each(function () {
        if (serializeObj[this.name]) {
            if ($.isArray(serializeObj[this.name])) {
                serializeObj[this.name].push(this.value);
            } else {
                serializeObj[this.name] = [serializeObj[this.name], this.value];
            }
        } else {
            serializeObj[this.name] = this.value;
        }
    });
    return serializeObj;
};
/**
$.fn.upload = function (options) {
    options = options || {};
    options.dom = this;
    $.upload(options);
};

$.submitFile = function(options){
    var settings = {
        formId: "#uploadLogoForm", //iframe等元素渲染的位置
        imgIds: ["#logoUrl"],
        inputIds:["#logo"]
    }
    settings = $.extend(settings, options);
    var form = $(settings.formId);
    var formData = new FormData(form[0]);
    $.ajax({
        type : 'POST',
        contentType: false,
        processData: false,
        url : form.attr("action"),
        data: formData,
        dataType:"json",
        success : function(data) {
            var file = data.file;
            if(file.err == ""){
                var img = file.path;
                var resourceId = file.resourceid;
                $(settings.imgIds[0]).attr("src",resources_base_url + img);
                $(settings.inputIds[0]).val(resourceId);
            }
            //if(data.)
        },
        error : function(e) {
            $.ligerDialog.error(e);
        }
    });
}
/**
$.upload = function (options) {

    var settings = {
        location: "body", //iframe等元素渲染的位置
        dom: "",
        action: "",
        fileName: "file",
        params: {},
        accept: ".jpg,.png",
        ieSubmitBtnText: "上传",
        dataType: "text",
        submit: function ($form) {
            return true;
        }
    }
    settings = $.extend(settings, options);
    var ele = settings.dom;
    if (!ele) return;

    var width = ele.outerWidth();
    var height = ele.outerHeight();
    var form = $("<form id='uploadLogoForm'></form>");
    form.attr({
        action: settings.action,
        method: "post",
        "class": "ajax_form",
        enctype: "multipart/form-data"
    }).css({
        width: width,
        height: height,
        position: "absolute",
        top: (ele.position().top),
        left: (ele.position().left)
    });
    var input = $("<input type='file'/>");
    input.attr({
            accept: settings.accept,
            name: settings.fileName
        })
        .css({
            opacity: 0,
            position: "absolute",
            width: width,
            height: height + "px",
            cursor: "pointer"
        });
    form.append(input);
    $(settings.location).append(form);
    input.change(function () {
        if (settings.submit.call(null, form)) {
            var fileName = this.value;
            if (fileName != '') {
                var fileExt = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                var pattern = /^(.jpg|.jpeg|.png|.bmp)$/;
                if (!pattern.test(fileExt)) {
                    $.ligerDialog.error("请上传正确的图片格式（.jpg|.jpeg|.png|.bmp）");
                } else {
                    $.submitFile(options);
                }
            }
        }
    });
    for (var param in settings.params) {
        var div = $("<input type='hidden'/>").attr({name: param, value: settings.params[param]});
        input.after(div)
        div = null;
        delete div;
    }
};

/**
 * 创建upload
 * @param options
 
$.createUploader  =  function (options) {
    var settings = {
        elDom : '.page-content',//渲染的位置
        logoButtonId : '#js-upload-logo'
    }
    settings = $.extend(settings, options);
    var that = $(settings.elDom);
    $(settings.logoButtonId).upload({
        location: that.el,	//渲染的位置
        action: upload_base_url + "pic.upload", //上传地址
        fileName: "file",    			 //文件名称。用于后台接收
        params: {},         			 //参数
        accept: ".jpg,.png",    		 //文件类型
        formId: settings.formId, //iframe等元素渲染的位置
        imgIds: settings.imgIds,
        inputIds:settings.inputIds,
        submit: function ($form) {
            var filename = $form.find(':file').val();
            if (!/\.(png)$/.test(filename) && !/\.(jpg)$/.test(filename)) {
                $.ligerDialog.error('只能是jpg或是png格式的图片文件！');
                return false;
            }
            return true;
        }
    });
};
*/