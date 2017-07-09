$(function()
{
    var path = "/web-domain";
    if ($(this).val() == '' || $(this).val() == $(this).attr('placeholder'))
    {

        $(this).val($(this).attr('placeholder'));
        $(this).addClass('placeholder');
    }



    // remove placeholders on submit
    $('[placeholder]').closest('form').submit(function()
    {

    });
    // check placeholder browser support
});/**
 * Created by Administrator on 2017/5/14.
 */
function adminLogin(){
    var loginName = $("#username").val();
    var password = $("#password").val();
    var data = {
        loginName:loginName,
        password:password
    }
    $.ajax({

        type : "POST",
        contentType : 'application/json',
        url : path + "/login/login",
        processData : false,
        dataType : 'json',
        data :JSON.stringify(data) ,
        complete : function(request){
        },
        success : function(data) {
            if(data.result != 0){
                alert(data.message);
            }else {
                window.location.href= path + "/admin/index.jsp";
            }

        },
        error : function(e) {
            debugger;
            //alert("err");
        }
    });
}
