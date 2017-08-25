// JavaScript Document ����չ

	var checkedKeys = [];

	function onCheckAllRow4grid(checked){ 
    	for (var rowid in this.records) {
			if(checked){	
            	addCheckedCustomer(this.records[rowid][[keyColumn]]);
			} else {
                removeCheckedCustomer(this.records[rowid][[keyColumn]]);
			}
        }
    }
    
    function findChecked( key )  { 
		for(var i =0;i< checkedKeys.length;i++)  {
        	if(checkedKeys[i] ==  key ) return i;
        }
        return -1;
    }
		
    function addChecked( key ) {
    	if(findChecked( key ) == -1){
			checkedKeys.push( key );
		}
    }
		
    function removeChecked( key ) {
        var i = findChecked( key );
        if(i==-1) return;
        checkedKeys.splice(i,1);
    }
		
    function isChecked(rowdata)  {
    	if ( findChecked( rowdata.key ) == -1)
        	return false;
    }
		
    function onCheckRow( checked , data)  {  
    	if (checked){ 
			addChecked( data[keyColumn] );
		} else {
			removeChecked( data[keyColumn] );
		}
    }
		
    function getChecked()  {
       alert(checkedKeys.join(','));
	   return checkedKeys;
    }
	 
	function getCheckedData() {
       var rows = grid.getCheckedRows();
       //alert('>>' + JSON.stringify(rows));
	   return rows;
     }

	function getSelected() { 
		var row = grid.getSelectedRow();
		if (!row) { 
			alert('��ѡ����'); 
			return; 
		}
		alert(JSON.stringify(row));
	}
	
	/*********************************************************
	 * Load Model Part
	 *********************************************************/  
	 
	function WinParam(){
		var dialog = frameElement.dialog; 		
		var dataBox = dialog.get('data');  
		return dataBox;
	}
	 
	/**
	 * Load Model Part
	 * @param url
	 * @param param 请求参数
	 *				方式1 ：null 						= {"key"	  : dialog.get('data') }				value 			类传参		{'key':value}
	 * 				方式2 ："accountId" 				= {"accountId": dialog.get('data') }					string 			参数名称型
	 *				方式3 ："DataArray"				= JSON.stringify( dialog.get('data') );				DataArray		数组	
	 *				方式4 ："{"accountId": "1231" }"	= {"accountId": "1231" }								DataArrayStr    字符串		{param:value}
	 * @param successFunction
	 **/   
	function LoadEntity(url , param ,success1 ){
		//console.log(url);
		
		var dialog = frameElement.dialog; 
		if(!dialog.get('data')){
			alert('参数传递有误！');	
			console.log('not find data ');
			console.close();
			return;
		}
	 
		//key = dialog.get('data')[param];
		var dataBox = dialog.get('data');  
		console.log("dataBox:"+dataBox + " >>> " + JSON.stringify(dataBox)); 
		
		
		
        var requestParam = {} ; 
		if( !param ){											//方式1 null || undefined 		{'key':value}
			requestParam[ "key" ]  = dataBox ; 
			requestParam = JSON.stringify(requestParam);
		}else if( param ==  "DataArray" ){						//方式3 DataArray
			requestParam = JSON.stringify(dataBox) ; 	 
		}else if( param.indexOf(":") !=-1 ){					//方式4 JsonString	
			requestParam = param  ; 	
		}else {													//方式2 value 					{param:value}
			requestParam[ param ]  = dataBox ; 
		 	requestParam = JSON.stringify(requestParam);
		}
		 
		if(!success1){
			success1 = LoadSuccess;
		} 
		console.log("requestParam:"+requestParam);
		$.ajax({
			type : "POST",
			contentType : 'application/json', 
			url:url,
			processData : false, 
			data: requestParam,
			beforeSend:function(){
				//alert(key);
				//$("#"+key).html('加载中 <img src="/images/loadingbox.gif" alt="加载中..." width="35" height="35"/>');
			},
			success: success1 ,
			error: HttpErr
		}); 
	}
	
	
	function LoadEntity4data(url , param , success1 ,sendType,getDataByParam){
		//console.log(url);
		var loadkey,key;
		var dialog = frameElement.dialog;
		//
		if(!dialog.get('data')){
			console.log('not find data ');
			return;
		}
		if(getDataByParam){
			key = dialog.get('data')[param];
		}else{
			key = dialog.get('data');
		}
		if( !key || key == 'undefined'){
			console.log('undefined');
			alert('参数传递有误！');
			console.close();
			return;
		}
		console.log("keys:"+key);
		param = param != null ? param : 'key';
        sendType = sendType != null ? sendType : 'POST';
		if(sendType == 'GET'){
			loadkey = param + "=" + key;
		}else{
			loadkey = '{"'+param+'" : "'+ key +'"}';
		}
		if(!success1){
			success1 = LoadSuccess;
		} 
		console.log("loadkey:"+loadkey);
		$.ajax({
			type : sendType,
			contentType : 'application/json', 
			url:url,
			processData : false, 
			data: loadkey,
			beforeSend:function(){
				//alert(key);
				//$("#"+key).html('加载中 <img src="/images/loadingbox.gif" alt="加载中..." width="35" height="35"/>');
			},
			success: success1 ,
			error: HttpErr
		}); 
	}
	
	function LoadSuccess(data){
		console.log('load');
		initForm( data.message );
	}
	
	
