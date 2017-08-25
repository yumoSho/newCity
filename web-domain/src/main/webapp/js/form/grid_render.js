// JavaScript Document UTF-8
	/*********************************************************
	 * Date Part
	 *********************************************************/
 	//扩展Date的format方法   
    Date.prototype.format = function (format) {  
        var o = {  
            "M+": this.getMonth() + 1,  
            "d+": this.getDate(),  
            "h+": this.getHours(),  
            "m+": this.getMinutes(),  
            "s+": this.getSeconds(),  
            "q+": Math.floor((this.getMonth() + 3) / 3),  
            "S": this.getMilliseconds()  
        }  
        if (/(y+)/.test(format)) {  
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
        }  
        for (var k in o) {  
            if (new RegExp("(" + k + ")").test(format)) {  
                format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
            }  
        }  
        return format;  
    }
	   
    /**   
     * 转换日期对象为日期字符串   
     * @param l long值   
     * @param pattern 格式字符串,例如：yyyy-MM-dd hh:mm:ss   
     * @return 符合要求的日期字符串   
     **/    
    function fmtDate ( date, pattern) {  
        date = new Date(date);
        if (pattern == undefined) {  
            pattern = "yyyy-MM-dd hh:mm:ss";  
        }  
        return date.format(pattern);  
    }  
	
	function fmtDay( value ){
		if( !value ){
			return "";
		}
		return fmtDate(value , 'yyyy-MM-dd' );
	} 
	
    function RenderDate( rowdata, rowindex, value ){
    	if( !value ){
        	return "";
        } 
    	return "<span class='date'>"+ fmtDate(value) +"</span>";
    }
	
	
    function RenderDay( rowdata, rowindex, value ){
    	if( !value ){
        	return "";
        } 
    	return "<span class='date'>"+ fmtDay(value)  +"</span>";
    }
	
	/*********************************************************
	 * Grid View Part
	 *********************************************************/
	function RenderState( row , index , value ){
		return renderState(value);
	}
	
	
	
	function renderState(state){
		if(state == 1){
		 return "<span class='green'>启用</span>";
		}
		if(state == 0){
		 return "<span class='red'>停用</span>";
		}
		if(state == 2){
			return "<span class='red'>冻结</span>";
		}
		return "";
	}
	
	
	function RenderOk( row , index , value ){
		return renderOk( value );
	}
	function renderOk(state){
		if(state == 1){
		 	return "<span class='green'>√</span>";
		} else if( state ==0) {
		 	return "<span class='red'>×</span>";
		}  
		return state;
	}

	function renderDel(state){
		if(state == 1){
		 return "<span class='green'>正常</span>";
		}
		if(state == 0){
		 return "<span class='gray'>回收</span>";
		}
	}
	 
	function RenderOPState(  row , index , value ){
		var key = eval( "row."+keyColumn ); 
		 if(value == 1){
			 return "<a href=\"javascript:del( '"+key+"' );\">删除</a>";	
		 }
		 if(value == 0){
			 return "<a href=\"javascript:setState( '"+key+"' , 1 );\">启用</span>";
		 } 	
		 return "";
	}
	
	function RenderOPBase(  row , index , value ){
		var key = eval( "row."+keyColumn ); 
		 if(value == 1){
			 return "<a href=\"javascript:del( '"+key+"' );\">删除</a>";	
		 }
		 if(value == 0){
			 return "<a href=\"javascript:setState( '"+key+"' , 1 );\">启用</span>";
		 } 	
		 return "";
	}
	
	function RenderEmoji(rowdata, rowindex, value ){
         return "<span class='emoji'>"+value+"</span>";        
	}
	
	function RenderAccount(rowdata, rowindex, value ){
		return "<span class='account'>"+value+"</span>";
	} 
    
    function RenderMoney( rowdata, rowindex, value ){
    	if( !value ){
        	return "";
        }
    	var money = 0 ;
        try{
        	money = parseFloat(value).toFixed(3);
        }catch(e){
        	money = value;
        } 
    	return "<span class='money'>"+ money +"</span>";
    }
    