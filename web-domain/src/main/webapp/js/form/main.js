	/*********************************************************
	 * Choose Model Part
	 *********************************************************/ 
	   
	//jquery获取复选框值     
	function checkbox_val(id){  
		var chk_value =[];    
		$('input[name="'+id+'"]:checked').each(function(){    
			chk_value.push(	$(this).val()	);    
		});    
		return chk_value; 
	}

	function checkid(){
		return checkbox_val(keyColumn);
	}
	function editid(){
		//$("#checkid").val(art.dialog.opener.checkid());
	}
	function hasSelected4single(){
	
		var data = getCheckedData();
		var size = data.length;
		
		
		if(size == 0){ 
			 alert(  ' Warning : 请选择,需要编辑的记录！ ' );
			return false;
		}
		
		if(size > 1){ 
			alert( ' Warning : Check One ' );
			return false;
		} 
		
		return true; 
	}
	
	function hasSelected(){  
		var data =  getCheckedData();
		var size = data.length; 
		if(size == 0){ 
			 alert(  ' Warning : 请选择,需要编辑的记录！ ' );
			return false;
		} 
		return true; 
	}
	
	/*********************************************************
	 * QueryHtml Part
	 *********************************************************/
	 
	function QueryParam(){
		var param = new Array(); 
		var queryForm = $("#queryForm").serializeArray(); 
		for( var key in queryForm){
				var obj = queryForm[key];
				if( obj.value != ""){
					param[obj.name] = $.trim(obj.value) ;    
				}
		}  
		return param;
	}
	
	QueryHtml.prototype.pagesize = function(){
		try{
			return pageSize;
		}catch(e){
			return null;
		}
	}
	 
	//Ajax HTML Query Contro DIV id
	QueryHtml.prototype.searchResult = "maingrid";  
	//Ajax HTML Query Success Function 
	QueryHtml.prototype.success = function(data ){
		var rows = {};
		rows.Rows = data;
		grid.set({ data: rows });  
	}
	//Ajax HTML Query
	function QueryHtml(url , param , success1  ){
		
		var key = QueryHtml.prototype.searchResult;
		
		if( param == undefined || param=="undefined" || param==null){
			param = new Array();  
		} 
		
		var pagesize = -1; 
		var pn = $("#"+key).attr("pn"); 
		if( param["pagesize"] ){
			pagesize = param["pagesize"];
			param["pn"] =  pagesize ;
		}else if( pn ){ 
			param["pn"] =  pn;
		}else{ 
			pagesize = QueryHtml.prototype.pagesize(); 
			param["pn"] =  pagesize+"/1";
		}
		 
		if(success1=="undefined" || success1==null){
			 success1 = QueryHtml.prototype.success
		}  
		//var am = param.serialize();
		var data = "";
		var json = {}
		for(var key in param){
			//data += key + "="+ param[key];
			json[key] = param[key]
		}
		var jsonStr = JSON.stringify( json );
		console.log("queryParam:" + jsonStr);  
		$.ajax({
			type : 'POST',  
			contentType : 'application/json', 
			url:url,
			processData : false,
			dataType : 'json', 
			data: jsonStr ,    
			beforeSend:function(){
				$("#"+key).html('加载中 <img src="/images/loadingbox.gif" alt="加载中..." width="35" height="35"/>');  
			},
			success: function(data){
				if(data.result != 0){
					alert(data.message);
				}else{
					// console.log(data.datas.data);
                    success1(data.datas.data);
					// grid.loadServerData = data.datas;
                    QueryHtml.prototype.successTotal(data.datas);
                    // QueryHtml.prototype.queryTotal(url , jsonStr);
                }
			} ,
			error: HttpErr,
			complete:function(){
				$("#grid-body").height($(window).height()-69);	
			} 
		});  
  
	}
	
	//Ajax HTML Query
	function Query(url , param , success1  ){
		
		var key = QueryHtml.prototype.searchResult;
		
		if( param == undefined || param=="undefined" || param==null){
			param = new Array();  
		} 
		
		var pagesize = -1; 
		var pn = $("#"+key).attr("pn"); 
		if( param["pagesize"] ){
			pagesize = param["pagesize"];
			param["pn"] =  pagesize ;
		}else if( pn ){ 
			param["pn"] =  pn;
		}else{ 
			pagesize = QueryHtml.prototype.pagesize(); 
			param["pn"] =  pagesize+"/1";
		}
		 
		if(success1=="undefined" || success1==null){
			 success1 = QueryHtml.prototype.success
		}  
		//var am = param.serialize();
		var data = "";
		var json = {}
		for(var key in param){
			//data += key + "="+ param[key];
			json[key] = param[key]
		}
		var jsonStr = JSON.stringify( json );
		console.log("queryParam:" + jsonStr);  
		$.ajax({
			type : 'POST',  
			contentType : 'application/json', 
			url:url,
			processData : false,
			dataType : 'json', 
			data: jsonStr ,    
			beforeSend:function(){
				$("#"+key).html('加载中 <img src="/images/loadingbox.gif" alt="加载中..." width="35" height="35"/>');  
			},
			success: function(data){
				success1(data,name); 
			} ,
			error: HttpErr,
			complete:function(){
				$("#grid-body").height($(window).height()-69);	
			} 
		});  
 
		 
	}
	
	
 
	QueryHtml.prototype.queryTotal = function(url , jsonStr){
	 
		var total = $("#"+ QueryHtml.prototype.searchResult).attr("total" );
 		if( total && total >0){
			return;
		}
		url = url.replace("/query","/count");
		$.ajax({
			type : 'POST',  
			contentType : 'application/json', 
			url:url,
			processData : false,
			dataType : 'json', 
			data: jsonStr , 
			success: QueryHtml.prototype.successTotal ,
			error: HttpErr 
		});
	} 
	QueryHtml.prototype.successTotal = function( data ){
		try{
			if( !grid  ){
				return;
			}
		}catch(e){
			return;
		}
		
		console.log( "count result :" + JSON.stringify(data) );
		var total = 0;
		var key = QueryHtml.prototype.searchResult;
		if(data.count){
			total = parseInt(data.count);
		}else{
			total = parseInt(data.message);
		}
		console.log( "count result :" + key + " = "+ total);
		$("#"+ key).attr("total" , total );   
		
		
		
		var pagesize = grid.options.pageSize; 
		$("#"+ key).attr("pn" , pagesize+"/1");  
		
		
		grid.options.total = total;
		grid.options.pageCount = parseInt( (total + (pagesize - 1)) / pagesize );
		
		grid.ext_total = total;
		grid.ext_pageCount = grid.options.pageCount;
		
		grid._buildPager();
	}
	 
	function getPnAttr(){
		var maingrid = document.getElementById("maingrid");
		if(maingrid=='undefined' || maingrid==null || maingrid ==""){
			return ;
		}	
		var pnAttr = maingrid.getAttribute("pn"); 
		var pagesize = grid.pageSize; 
		var currentData = grid.currentData;
		var Total = grid.Total;
		if( pnAttr=='undefined' || pnAttr==null || pnAttr ==""){  
			maingrid.setAttribute("pn" ,pagesize +"/1");
		}
		
		return maingrid.getAttribute("pn"); 
	}

	function getPn(){
		var pnAttr = getPnAttr(); 
		var pn = pnAttr.split("/")[1];
		return parseInt(pn);
	}
	function getPagesize(){ 
		var num = getPnAttr().split("/")[0];
		return parseInt(num);
	}
	
	function getTotalAttr(){ 
		var maingrid = document.getElementById("maingrid");
		if(maingrid=='undefined' || maingrid==null || maingrid ==""){
			return -1;
		}	
		var total = maingrid.getAttribute("total");  
		 
		return total; 
	}
	function getLastPage(){
		var row_count = getTotalAttr()  ;  
		console.log( "row_count : " + row_count);
		row_count = parseInt(row_count);
		var page_size = getPagesize();
		var pagecount =  (row_count + (page_size - 1)) / page_size;  
		return parseInt(pagecount);
	}
	
	/*********************************************************
	 * Base Part
	 *********************************************************/
	function HttpErr(xhr , status , msg ){
		
		if(xhr.status && xhr.status ){
			HttpErrProcess(xhr.status);
		}else{
			HttpErrProcess( status);
		}
		 
	}	
	
	function HttpErrProcess( status ){
		switch(status) {        
			case 404:          
				alert('404 File not found');          
				break;        
			case 500:          
				alert('Server error');          
				break;        
			case 0:          
				alert('Request aborted');          
				break;   
			case 'error':
				alert('Connection Error . Server is shutdown ');          
				break;        
			default:          
				alert('GrowFace Unknown error :' + status);     
				try{
					if( xhr ){
						console.log( JSON.stringify(xhr) );
					} 
				} catch(e) {
				}
		} 
	}
	
	function beforeSend(XMLHttpRequest){
		 var token = $.cookie('token');
		 console.log("cookie - token :"+ token);
		 XMLHttpRequest.setRequestHeader("token" ,token );
	}
	Execute.prototype.success = function(data){
		if( data.message > 0){
			$.ligerDialog.success('操作成功');
			query();
		}else{
			$.ligerDialog.warn('操作失败')
		}
	}
	function Execute( url , param ,  success1){
		var jsonStr = JSON.stringify( param );
		
		if(success1=="undefined" || success1==null){
			 success1 = Execute.prototype.success
		} 
		
		console.log( JSON.stringify( jsonStr )); 
				
		$.ajax({
			type : 'POST',  
			contentType : 'application/json', 
			url : url,
			processData : false,
			dataType : 'json', 
			data: jsonStr , 
			success: success1  ,
			error: HttpErr 
		});			
	}
	/*********************************************************
	 * Form Submit Part
	 *********************************************************/
	function closeWin(){
		var dialog = frameElement.dialog;	
		dialog.close();
	}
	
	$.fn.serializeObject = function()  {    
	   var o = {};    
	   var a = this.serializeArray();    
	   $.each(a, function() {    
		   if (o[this.name]) {    
			   if (!o[this.name].push) {    
				   o[this.name] = [o[this.name]];    
			   }    
			   o[this.name].push(this.value || '');    
		   } else {    
			   o[this.name] = this.value || '';    
		   }    
	   });    
	   return o;    
	};
	
	SubForm.prototype.formkey = "xForm";  
	
	SubForm.prototype.success = function(data){
		//alert(JSON.stringify(data));
		if(data.result != 0 ){
			alert(data.err);
			return;
		}
		var key = SubForm.prototype.formkey;
		//alert(key);
		if( !frameElement || !frameElement.dialog ){
			return;
		} 	
		var dialog = frameElement.dialog;
		parent.window.query();//关闭dialog .opener.query();
		dialog.close();//关闭dialog .close(); 
	} 

	function SubForm( formid , param , success1){ 
		var url = "";
		var key = SubForm.prototype.formkey;
		
		if(formid=="undefined" || formid==null){
			url = $('#xForm').attr("action");
		}else{
			key = formid;
			url = $("#"+formid).attr("action");
		}
		console.log("SubURL:"+url);
		 
		var  contentHtml =  getUBBContent();
		if(  contentHtml ){ 
			$("#content").val(contentHtml);
			console.log( "Sub name['content'] \n" + contentHtml);
		}
 
		if(param=="undefined" || param==null){
			//param = $('#'+key).serialize();
			param = $('#'+key).serializeObject();
			//param = JSON.parse(param);
			console.log(JSON.stringify( param ));
			param = JSON.stringify( param );
		}
		
		if(success1=="undefined" || success1==null){
			 success1 = SubForm.prototype.success
		}
 
		/*$.ajax({
			 type:'post',
			 url :url,
			 data:param,
			 dataType:'text',   //接受数据格式
			 success: success1 ,
			 error:HttpErr
		});*/
		console.log("request Param : "+param);
		$.ajax({  
			type : 'POST',  
			contentType : 'application/json',  
			url : url,  
			processData : false,  
			dataType : 'json',  
			data : param,  
			beforeSend: beforeSend , 
			success : success1,  
			error:HttpErr
		});
		
	} 
	
	/* eWebEditor1 --------
	 *function getUBBContent( id ){
		if( ! id){
			id = "eWebEditor1";
		}
		var  eWebEditor1 =  document.getElementById( id );
		if(  eWebEditor1 ){
			var o_Editor = eWebEditor1.contentWindow; 
			var contentHtml = o_Editor.getHTML();	
			//$("#" + id ).val(contentHtml);
			//alert("contentHtml:"+contentHtml);
			return contentHtml;
		}
		return null;
	}*/
	
	function getUBBContent( id ){
		try{
			if( um  ){
				return null;
			}
		}catch(e){
			return null;
		}
		
		var content = um.getContent();
		if( ! content){
			return null;
		}
		
		//alert( content );
		 
		return content;
	}
	
	function getRadioValue( name ){
		return $('input[name="'+name+'"]:checked').val()	
	}

 
