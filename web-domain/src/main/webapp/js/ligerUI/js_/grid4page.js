function openpage(key){  
	var index = 1;
	var pn = getPn();
	var last_page = getLastPage();
	if( key == 'first'){ //first
		index = 1 ;
	} 
	
	if( key == 'prev' ){ //prev
		index = (pn>1)?pn-1:1;
	}
	if( key == 'next'){ //next
		index = (pn<last_page)?pn+1:last_page;
	}
	if( key == 'last'){ //last
		//alert(last_page);
		index = last_page;
	}  
	queryPage( index );
}

function queryPage(pageIndex){   
	//alert("index:"+pageIndex);  
	var pagesize = getPagesize(); 
	var maingrid = document.getElementById("maingrid");
	if(maingrid=='undefined' || maingrid==null || maingrid ==""){
		return ;
	}	
	maingrid.setAttribute("pn", pagesize +"/"+pageIndex);   
	query();
} 


