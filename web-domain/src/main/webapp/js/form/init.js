 	/*********************************************************
	 * Page Init Part
	 *********************************************************/
	function initTitle( title ){  
		 
		if( title == undefined  || title =='undefined' || title == "" ){
			title = "System" ;
		}
		
		if( !frameElement ){
			return;
		}
		
		
		if( !frameElement.dialog ){
			return;
		}
		
		frameElement.dialog.set( 'title', title ); //���ñ���
		$("#title").html(title);
		 
	}
	
	/*********************************************************
	 * Form Init Part
	 *********************************************************/
	/*
	 * ��������INPUT
	 * ʹ�ã� onKeyUp="inputInt(this)" 
	 */
	
	function inputInt(obj){
		obj.value=obj.value.replace(/[^0123456789]/g,'')
	}
	function inputDouble(obj){
		obj.value=obj.value.replace(/[^0123456789.]/g,'')
	}
	var formInitDebug = false;
	function formInit(elementID,value, oper ){
		if( value==undefined ){
			return;
		}
		var element = this.document.getElementById(elementID);
	 
		if(element==null){
			//alert("Not Element:"+elementID);	
			window.prompt("Not Element:","<input type=\"hidden\" name=\""+elementID+"\" value=\"\" />");
	
			return;
		}

		
		var tagname = element.tagName;
		tagname = tagname.toUpperCase();
		if(tagname=="SELECT"){
			selectInit(elementID,value);
			return;
		}
		
		if(tagname=="IMG"){
			imgInit(elementID,value);
			return
		
		}
		
		if(tagname=="TEXTAREA"){
			textInit(elementID,value);
		}
		
		 
		var tagtype = element.type;
		tagtype = tagtype.toLowerCase();
		//alert(tagtype+"="+elementID);
		if(tagtype=="checkbox"){
			checkBoxGuoupInit(elementID,value);
			return;
		}
		
		if(tagtype=="radio" ){
			radioGuoupInit(elementID,value);
			return;
		}
		
		initInput(elementID,value);
		
		
		//Oper 
		
		if(oper != "undefined" && oper != undefined && oper != null){
			if( oper == "hide"){
				formHide( elementID );	
			}
			if( oper == "readonly"){ 
				formReadonly( elementID );
			}
		}
		
	}
	function initInput(elementId,value){
		var formOBJ =  this.document.getElementById(elementId);
		formOBJ.value = value;
	}
	
	function selectInit(selectElementID,selectedForValue){
		var selectObj = this.document.getElementById(selectElementID)
		var selectLength = selectObj.length;
		for (var i = 0; i<selectLength ; i ++){
			if (selectObj[i].value == selectedForValue){
				selectObj[i].selected  = true ;
			}
		}
	}
	
	function radioGuoupInit(radioElementID,checkedForValue){
		var radioObj = eval("document.all." + radioElementID);
		var radioLength = radioObj.length;
		for (var i = 0; i<radioLength ; i ++){
			if (radioObj[i].value == checkedForValue){
				radioObj[i].checked  = true ;
				
			}
		}
	}
	
	function checkBoxInit(checkBoxElementID,checkedForValue){
		var checkBoxObj = this.document.getElementById(checkBoxElementID)
		if (checkBoxObj.value == checkedForValue){
			checkBoxObj.checked  = true ;
			//checkBoxObj.checked  = "checked" ;
		}
	}
	
	function checkBoxGuoupInit(checkBoxElementID,checkedForValue){
		//alert("checkbox="+checkBoxElementID);
		var radioObj = eval("document.all." + checkBoxElementID);
		var radioLength = radioObj.length;
		//alert("radioLength:"+radioLength);
		if((radioLength+"") =="undefined"){
			//alert('��');
			checkBoxInit(checkBoxElementID,checkedForValue);	
			return;
		}
		
		if(radioLength<=1){
			//alert('��');
			checkBoxInit(checkBoxElementID,checkedForValue);	
			return;
		}
		
		for (var i = 0; i<radioLength ; i ++){
			if(radioObj[i].value == checkedForValue){
				radioObj[i].checked  = true ;
			}
		}
	}
	
	function imgInit(ElementID,value){
		var obj = this.document.getElementById(ElementID);
		if(value==null||value==""||value=="undefined"||value=="null"){
			return;
		}
		obj.src = value;
	}
	
	function textInit(elementId,value){
		console.log(value);
		var formOBJ =  this.document.getElementById(elementId);
		formOBJ.value = value;
	 
		//var fw_ubb = this.document.getElementById('eWebEditor1'); 
		//if(fw_ubb!=null&&fw_ubb!=""&&fw_ubb!="undefined"){  
			//fw_ubb.src = fw_ubb.src;	
		//}	
	 
	}
	
	function displayCtrlInit(elementID,value,condition){
		var e = this.document.getElementById(elementID);
		if (condition == value){
			e.style.display = "";
		}else{
			e.style.display = "none";
		}
	}
	function subRELOAD(){
		var e = document.getElementById("OK_RETURN_URL");
		e.value = "RELOAD";
		document.jzform.submit();
	}
	
	function RELOAD(){
		subRELOAD();
	}
	
	
	function formAction(actionPath){
		jzform.action = actionPath;
	}
	
	function onsub(){
		var exec = arguments[0];
		if(exec=="set"){
			onsub_set(arguments[1]);	
			return true;
		}
		
	}
	
	function onsub_set(paramName){
		var returnURLOBJ = document.getElementById("OK_RETURN_URL");
		var returnURL = returnURLOBJ.value;
		var OBJ1 = document.getElementById(paramName);
		var value = OBJ1.value;
		returnURL = returnURL+"?"+paramName+"="+value;
		document.getElementById("OK_RETURN_URL").value = returnURL;
		//alert(returnURL);
	}
	
	//2014-10-22
	
	/**
	 *  about hide or display
	 *  1 formHide(elementid) Is Hide Tr or Td
	 *  2 formInit(elementid, value , "hide") , Is Init And Hide Tr or Td
	 */
	function formHide(elementId){ 
		var elementId = "tr_"+elementId;
		var e = document.getElementById(elementId);
		if(e){
			//var style = e.getAttribute( "style" ).value;
			//e.setAttribute( "sytle" , "display:none"  ); 
			e.style.display = "none";
		}
		
	}
	
	function formReadonly(  elementId){
		var e = document.getElementById(elementId);
		e.readonly = true;
	}
	
	function formPut( object ){
		$.each( object , function ( attribute , value){
			$('#' + attribute).val(value);  
			formInit( attribute , value );
		}); 
	}