// JavaScript Document ±íµ¥À©Õ¹

	function onCheckAllRow4grid(checked){ 
    	for (var rowid in this.records) {
			if(checked){	
            	addCheckedCustomer(this.records[rowid]['CustomerID']);
			} else {
                removeCheckedCustomer(this.records[rowid]['CustomerID']);
			}
        }
    }
  
    var checkedCustomer = [];
	
    function findChecked4grid(CustomerID)  { 
		for(var i =0;i<checkedCustomer.length;i++)  {
        	if(checkedCustomer[i] == CustomerID) return i;
        }
        return -1;
    }
		
    function addCheckedCustomer(CustomerID) {
    	if(findCheckedCustomer(CustomerID) == -1){
			checkedCustomer.push(CustomerID);
		}
    }
		
    function removeCheckedCustomer(CustomerID) {
        var i = findCheckedCustomer(CustomerID);
        if(i==-1) return;
        checkedCustomer.splice(i,1);
    }
		
    function f_isChecked(rowdata)  {
    	if (findCheckedCustomer(rowdata.CustomerID) == -1)
        	return false;
    }
		
    function f_onCheckRow(checked, data)  {
    	if (checked) 
			addCheckedCustomer(data.CustomerID);
        else 
			removeCheckedCustomer(data.CustomerID);
    }
		
    function getChecked()  {
        alert(checkedCustomer.join(','));
    }