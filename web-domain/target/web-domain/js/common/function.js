
	//Object �Ƿ����
	function exist(obj){
		if( obj != 'undefined' && obj != undefined && obj!= null){
			return true;
		}
		return false;
	}

	function getNowFormatDate() {
		var date = new Date();
		var seperator1 = "-";
		var seperator2 = ":";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = year + seperator1 + month + seperator1 + strDate + " 00:00:00";
		return currentdate;
	}
