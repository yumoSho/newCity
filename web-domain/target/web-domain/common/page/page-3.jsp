<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%! 
    //取得开始位
    public int getForMinNum(int pn){ 
       if(pn>9){
          return pn-4;
       } 
       return 1;
    }
    
    //取得结束位
    public int getForMaxNum(int pn,int page_count){ 
       if((pn+6)<page_count){
          if(pn>9){
            return pn+6;
          }else{
            return 10;
          }
       } 
       return page_count;
    }
    
    //取得当前
    public String getPageStyle(int i , int pn){
    	if(i==pn){
    		return "class='acitve'";
    	}
    	return "onclick='openPage("+i+");'";
    } 
%><%  
    String _PN = request.getAttribute("CURRENT_PAGE_NUM")+""; 
	String _PAGE_COUNT 	= request.getAttribute("PAGE_COUNT")+"";
	String _ROW_COUNT 	= request.getAttribute("ROW_COUNT")+""; 
	String _PAGE_SIZE 	= request.getAttribute("PAGE_SIZE")+""; 
	String _FIRST_PAGE 	= request.getAttribute("FIRST_PAGE")+""; 
	String _LAST_PAGE 	= request.getAttribute("LAST_PAGE")+""; 
	String _PREV_PAGE 	= request.getAttribute("PREV_PAGE")+""; 
	String _NEXT_PAGE 	= request.getAttribute("NEXT_PAGE")+"";
	
	int PN 			=  1 ;  
	 
	int PAGE_COUNT 	=  1 ;
	int ROW_COUNT 	=  1 ; 
	int PAGE_SIZE 	=  1 ; 
	int FIRST_PAGE 	=  1 ; 
	int LAST_PAGE 	=  1 ; 
	int PREV_PAGE 	=  1 ; 
	int NEXT_PAGE 	=  1 ;
	
%>  
<div class="l-panel-bar" >            
	<div class="l-panel-bbar-inner" >                
		<div class="l-bar-group " style="float:left"><span class="l-bar-text">每页显示：<%=PAGE_SIZE%> </span></div>     
		<div style="float:right;  " >       
			<div class="l-bar-group l-bar-selectpagesize" >
			</div>                
			<div class="l-bar-separator"></div>                
			<div class="l-bar-group" >                    
				<div class="l-bar-button l-bar-btnfirst" ><span class="l-disabled" onclick="openPage(1);"  title="first"></span></div>                    
				<div class="l-bar-button l-bar-btnprev"><span class="l-disabled" onclick="openPage(<%=PREV_PAGE%>);" title="prev" ></span></div>                
			</div>                              
			<div class="l-bar-group">
				<span class="pcontrol"> <input type="text" size="4" value="<%=PN%>" style="width:20px" maxlength="3"> </span> / <span><%=PAGE_COUNT%></span>
			</div>        
			<div class="l-bar-separator"></div>                
			<div class="l-bar-group">                     
				<div class="l-bar-button l-bar-btnnext"><span class="l-disabled" onclick="openPage(<%=NEXT_PAGE%>);" title="next"></span></div>                    
				<div class="l-bar-button l-bar-btnlast"><span class="l-disabled" onclick="openPage(<%=LAST_PAGE%>);" title="last"></span></div>                
			</div>                
			<div class="l-bar-separator"></div>                
			<div class="l-bar-group">                     
				<div class="l-bar-button l-bar-btnload"><span class="" onclick="query();"></span></div>                
			</div>                
			<div class="l-bar-separator"></div>                
			
			<div class="l-clear"></div> 
		</div>           
	</div>         
</div>
<script language="javascript">  
function openPage(pageIndex){   //alert(pageIndex); 
	var searchResultContent = document.getElementById("searchResult");
	if(searchResultContent=='undefined' || searchResultContent==null || searchResultContent ==""){
		return ;
	}		  
	var pnAttr = searchResultContent.getAttribute("pn");
	//alert(pageIndex);
	if(pnAttr=='undefined' || pnAttr==null || pnAttr ==""){ 
		//return ;
		//searchResultContent.setAttribute("pn" , "20");
	}  
	
	//var arr = pnAttr.split(":");
	//var page_size = arr[1];  
	searchResultContent.setAttribute ("pn", pageIndex+":<%=PAGE_SIZE%>");  
	query();
} 
</script>