<%@ page pageEncoding="UTF-8"%>

	$(document).ready(function(){
		$(".l-panel-btn").mouseover(function(){
			var clazz = $(this).attr("class"); 
			$(this).attr("class",clazz+" l-panel-btn-over" );
		}).mouseout(function(){
			var clazz = $(this).attr("class");  
			$(this).attr("class",clazz.replace(" l-panel-btn-over",""));
		});
	});
	
	
	/*********************************************************
	 * Add Model Part
	 *********************************************************/ 
	function AddWin(URL , width , height){ 
		var w = 440;
		var h = 600;
		if(exist(width)){
			w = width;
		} 
		if(exist(height)){
			h = height;
		} 
		$.ligerDialog.open({ height:h, width:w ,url: URL});
	} 

	/*********************************************************
	 * Edit Model Part
	 *********************************************************/  
	 function EditWin(URL , width , height ,data ){
	 
 		if(!data){
			if(!hasSelected4single()  ){
				return;
			} 	
		}
		
 		var w = 800;
		var h = 600;
		if(exist(width)){
			w = width;
		} 
		if(exist(height)){
			h = height;
		}
		var value = null;
        if(data){
            value = data;
        }else{
			value =  getCheckedData()[0][keyColumn];
		}
		console.log( "EditWinParam:"+value);
		if(!value){
			//value = '123';
		}
		$.ligerDialog.open({ height:h, width:w ,url: URL ,data:value});
	}
	
	/*********************************************************
	 * Del Model Part
	 *********************************************************/  
	function DelExc( URL , ids , _success ){
		if( !ids ){
			if(!hasSelected()){
				return;
			}
		}
		 
		var param = {};
		if( ids ){
			param[ "keys" ] = ids;
		}else { 
			var checkData = getCheckedData();    
			var keys = "";
			for( var i =0 ; i <  checkData.length ; i ++){
				var value = checkData[ i ][keyColumn];
				if( keys != "") {
					keys += ",";
				}
				keys += value;
			} 
			//param.push( "keys" , keys );
			param[ "keys" ] = keys;
		}
		var paramStr = JSON.stringify( param );
		$.ajax({
			 type : 'POST',   
			 contentType : 'application/json' , 
			 url :URL,
			 data: paramStr ,
			 dataType:'json', 
			 processData : false,   
			 beforeSend: function(){
			 	//alert('');
			 },
			 success: function(data){
			 	console.log( JSON.stringify( data ));  
                if( _success ){
                	_success( data );
                } else if ( query){ 
					query(); 
                }
			 },
			 error:HttpErr,
			 complete: function(response){ 
        		console.log("GFReponseCode:"+response.getResponseHeader("GFReponseCode"));
        	}
		});
		
	}
 
	
	/*********************************************************
	 * Win Part
	 ********************************************************  */
	function win(width,height,url , data ){
		$.ligerDialog.open({ width:width , height:height , url:url , data:data}); 
	}
    
	
	function Href( url ){
		location.href = url; 
	}
	
    