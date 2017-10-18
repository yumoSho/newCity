/**
 * Created by dev on 2017/6/13.
 */
window.onload = (function(){
    var date = new Date();
    var seperator1 = "-";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate;
    document.getElementById("inscribeDate").value = currentdate;
})
var path = "http://192.168.0.101:8093/resource-domain";
function $(id) {
    return document.getElementById(id);
}


//发送Base64图片码  上传图片
function saveImg(){
    var img = $('img');
    var img_src = img.src;
    if(img_src == ''){
        alert("图片为空");
        return;
    }

    var dates = {
        "datas":img_src,
        "contentType":"image/png",
        "fileName":"test.png"
    };
    var xhr = new XMLHttpRequest();

    xhr.open("POST",  path +"/upload/img2Base64", true);
    xhr.setRequestHeader("Content-type","application/json");
    xhr.onreadystatechange = function(){
        var XMLHttpReq = xhr;
        if (XMLHttpReq.readyState == 4) {
            if (XMLHttpReq.status == 200) {
                var text = XMLHttpReq.responseText;
                console.log(text);
                document.getElementById("dataA").value = JSON.parse(text).datas.url;
            }
        }
    };
    xhr.send(JSON.stringify(dates));

   /* $.ajax({
        type : "POST",
        contentType : 'application/json',
        url : path +"/upload/img2Base64",
        processData : false,
        dataType : 'json',
        data : {
            datas:img_src,
            contentType:"image/png",
            fileName:"test.png"
        },
        success : function(data) {
            console.log(data);
        },
        error : function(e) {
            alert("err");
        }
    });*/
}



//生成图片信息
function textToImg() {

    var i = 0;
    var fontSize = 15;//字体的大小

    var fontWeight = 'normal';

    var canvas = $('canvas');
    var imgP = document.getElementById("imgP");

    //抬头数据
    var rise_text = $("rise").value;
    var rise_x = 112;
    var rise_y = 115;

    //正文数据
    var txt_x = 162;
    var txt_y = 160;
    var txt = $('txt').value;
    var txt_width = 233;
    var txt_height = 116;

    //落款分两种  时间与名称  名称在时间的基础上  x轴减少20px  y轴增加20px
    var inscribe_x = 273;
    var inscribe_y = 300;
    var insctibeDate = $('inscribeDate').value;
    var insctibeName = $('inscribeName').value;

    if (txt == '' || rise_text == '' || insctibeDate == '' || insctibeName == '') {
        alert('请输入文字！');
        $("txt").focus();
    }
    canvas.width = 750;
    canvas.height = 510;

    var len = txt_width / fontSize -1.;//每行显示的个数

    var context = canvas.getContext('2d');
    context.clearRect(0, 0, canvas.width, canvas.height);
    context.fillStyle = "#000000";
    context.font = fontWeight + ' ' + fontSize + 'px sans-serif';
    context.textBaseline = 'top';
    canvas.style.display = 'none';
    context.drawImage(imgP, 0, 0,canvas.width,canvas.height);
    //抬头
    context.fillText(rise_text, rise_x, rise_y, 300);

    //正文
    var k = 0;
    function fillTxt(text,j) {

        while (text.length > len) {
            var x = j == 0 && k == 0 ? txt_x + 30 : txt_x ;//第一行空两格
            var lenW = j == 0 && k == 0 ? len - 2 : len;
            var txtLine = text.substring(0, lenW);

            context.fillText(txtLine, x, txt_y + (fontSize * k), txt_width);
            text = text.substring(lenW);
            k++;
        }
        context.fillText(text, x, txt_y + (fontSize * k), txt_width);
        k++;
    }
    var txtArray = txt.split('\n');
    for ( var j = 0; j < txtArray.length; j++) {
        fillTxt(txtArray[j],j);
        //context.fillText('\n', 0, 0, canvas.width);
    }

    //落款
    context.fillText(insctibeDate, inscribe_x, inscribe_y, 300);
    context.fillText(insctibeName, inscribe_x - 20, inscribe_y + 20, 300);
    var imageData = context.getImageData(0, 0, canvas.width, canvas.height);
    var img = $("img");
    img.src = canvas.toDataURL("image/png");
}


