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
        imgSize : "150x150",
        formId : 'uploadForm_',
        form : $("<form></form>"),
        params: {},
        accept: ".jpg,.png",
        ieSubmitBtnText: "上传",
        dataType: "text",
        resizeOtherForms : [],
        valid: function () {
            return true;
        },
        bindEvent : function(){
            var o = this;
            var form = settings.form;
            $(settings.dom).click(
                function(){
                    form.find('input').click();
                }
            );
        },
        submit: function () {
            var o = this;
            var form = this.form;
            var formData = new FormData(form[0]);
            $.ajax({
                    type : 'POST',
                    contentType: false,
                    processData: false,
                    url : form.attr("action"),
                    beforeSend: function(XMLHttpRequest){
                        var token = $.cookie('token' );
                        XMLHttpRequest.setRequestHeader("token",token);
                    },
                    xhrFields: {
                        withCredentials: true
                    },
                    data: formData,
                    dataType:"json",
                    success : function(data){o.success(data)},
                    error : function(e) {
                        parent.alertMsg(e.statusText);
                    }
            });
        },
        success : function(data) {
            var result = data.result;

            if(result != 0){
                return ;
            }
            var file = data.message.file;
            if(file.err == ""){
                var img = file.path;
                var resourceId = file.resourceid;
                if(this.imgId){
                    $('#'+this.imgId).attr("src",file.domain);
                    $('#'+this.imgId).attr("resourceId" , resourceId );
                }
                if(this.inputId){
                    $('#'+this.inputId).val(resourceId);
                    $('#'+this.inputId).trigger('change');
                }
            }
        }
    }
    settings = $.extend(settings, options);
    var ele = settings.dom;
    if (!ele) return;
    var width = ele.outerWidth();
    var height = ele.outerHeight();
    var form = settings.form;
    form.attr({
        id : settings.formId+ ele.attr('id'),
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
            width: width + "px",
            height: height + "px"
        });
    form.append(input);
    var sizeInput = $('<input type="hidden">');
    sizeInput.attr({
            name: settings.fileName + "_compress",
            value : settings.imgSize
        });
    form.append(sizeInput);
    $(settings.location).append(form);
    input.change(function () {
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
    for (var param in settings.params) {
        var div = $("<input type='hidden'/>").attr({name: param, value: settings.params[param]});
        input.after(div)
        div = null;
        delete div;
    };
    settings.bindEvent();
};
/**
 * 创建上传组件
 * @param divEl   渲染所在区域
 * @param uploadEl  渲染空间位置
 * @param imgId   需要显示图片的IMGID
 * @param inputId  需要赋值的隐藏域ID
 * createUploader($(".page-content"),$('#js-upload-logo'),'logoUrl','logo');
 */

function createUploader(divEl,uploadEl,imgId,inputId,resizeOtherForms,imgSize) {
    uploadEl.upload({
        location: divEl.el,	//渲染的位置
        imgId : imgId,
        inputId : inputId,
        resizeOtherForms : resizeOtherForms,
        imgSize :imgSize
    });
};

function deleteImg(id){
    var val = $('#'+id).val();
    if(val != ''){
        $('#'+id).val('');
        $('#'+id).trigger('change');
        $('#'+id+"Domain").attr('src',global_base_url + '/img/default_logo.png');
    }
}

