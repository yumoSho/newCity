<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!-- Ô¤ÀÀ -->
<style type="text/css">
.preview_back {
    width: 100%;
    height: 100%;
    opacity: 0.6;
    background-color: #000;
    position: fixed;
    top: 0;
    bottom: 0;
    z-index: 1001; 
	display:none  
}
.preview_box { 
    width: 320px;
    height: 583px;
    border: solid 2px #c5c5c5;
    border-radius: 33px;
    position: absolute;
    z-index: 1002; 
    background-color: #ffffff;
    left: 50%;
    margin-left: -160px;
	 
}
.preview_box .preview_lis {
    width: 56px;
    height: 5px;
    margin: 33px auto 17px;
    border: solid 1px #c5c5c5;
    border-radius: 3px;
}
#preview_out {
    position: absolute;
    right: -58px;
    top: 1px;
    width: 38px;
    height: 38px;
    cursor: pointer;
}
#look {
    border: 1px solid #c5c5c5;
    background-color: #fff;
    height: 460px;
    width: 293px;
    margin: 0 auto;
    background-size: 100% 100%;
    font-size: 1em;
}
.content-box {
    height: 460px !important;
    transform: scale(1);
    word-wrap: break-word;
    overflow-x: hidden;
    overflow-y: auto;
    word-wap: break-word;
    padding: 1px 5px;
    max-width: 293px;
}
.preview_QR {
    position: absolute;
    right: -243px;
    width: 200px;
} 
</style>
<style type='text/css'>.temp_conent img {width:100%;}</style> 
<div class="preview_back" id="preview-background" onclick="phone_preview_close()"></div>
<div class="preview_box" id="preview-box" style="display:none">
	<div class="preview_lis">
            <div id="preview_out" onclick="phone_preview_close()"><img src="${path}/images/out_btn.png"></div>
    </div>
 
	<div id="look" class="preview-look">
		<div class="content-box temp_conent" id="contentBox"></div>
	</div>
	
	<div class="preview_btn"></div>
</div>
<script language="javascript">
function phone_preview(){ 
	$( "#preview-background" ).show();
	$( "#preview-box" ).show(); 
	
	$( "#contentBox").html( um.getContent() );
}
function phone_preview_close(){
	$( "#preview-background" ).hide();
	$( "#preview-box" ).hide(); 
}
</script>