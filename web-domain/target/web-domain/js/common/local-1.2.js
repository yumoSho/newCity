//ProvinceCode 数据源必须存在

/*************************************************************************************************************************************************************
 * 渲染UI
 **********************************************************************************************************************************************************/
/**
 * 渲染，省份
 * @param provinceValue 省份Id
 * @param elementId 自定义省份TagID default= province
 * @param changeFunction （废弃） , bind 扩展
 */
function RenderProvince( provinceId , elementId , changeFunction ){  

	var provinceElementId = "province" ; 
	if( elementId ){
		provinceElementId = elementId;
	}
	
	//数据源，引入 localdata-1.2.js
	//或者，用其他方式初始化 ProvinceCode 变量
	provinceData = ProvinceCode;	
	
	console.log(provinceData);
 
	$.each( provinceData , function (pro) { 					 
        $("#" + provinceElementId ).append('<option value="' + provinceData[pro].code + '">' + provinceData[pro].name + '</option>');
    });
	
	if( provinceId && provinceId !=null ){
		$( "#"+provinceElementId ).find("option[value='"+provinceId+"']").attr("selected",true); 
	}
		
	
	if(changeFunction){ 
		$("#"+provinceElementId).change(function(){ 
			changeFunction( $(this).val() );	
		}); 	 
	}
 
}	

/**
 * 2 Bind 渲染,城市
 * @param provinceId 省份Id
 * @param cityId 	 城市value ， 选中值
 * @param cityElementId 自定义城市TagID ，default="city" 
 */ 
function BindCity( provinceTagId , cityTagId ){
	var provinceElementId = "province";
	var cityElementId = "province";
	if( provinceTagId ){
		provinceElementId = provinceTagId
	}
	if( cityTagId ){
		cityElementId = cityTagId
	}
	var provinceValue = $("#"+provinceElementId).val();
	if( provinceValue !=null &&  provinceValue != '' &&  provinceValue !=-1  ){
		RenderCity(  provinceValue , null , cityElementId );
	}
	$("#"+provinceElementId).change(function(){  
		RenderCity( $(this).val() , null , cityElementId );
	});
}
 
/**
 * 2 渲染,城市
 * @param provinceId 省份Id
 * @param cityId 	 城市value ， 选中值
 * @param cityElementId 自定义城市TagID ，default="city" 
 */ 
function RenderCity( provinceId , cityId , cityElementId ){  
	var cityData = getCityByPovinceCode( provinceId );
 
	var elementId = "city";
	if( cityElementId ){
		elementId = cityElementId;
	}
 
	$("#"+elementId).children().remove(); 
    $("#"+elementId).append('<option value="-1">请选择</option>');
	
	$.each( cityData , function (pro) { 					 
        $("#"+elementId).append('<option value="' + cityData[pro].code + '">' + cityData[pro].name + '</option>');
    });  
	if (cityId && cityId > 0) {
        $("#"+elementId).val(cityId);
    }
 }

//3 渲染，区县
function RenderCounty( cityId , countyId) {
    var county = getCountyByCityCode( cityId );
    $("#county").children().remove();
    $("#county").append('<option value="-1">请选择</option>');
    $.each(county, function (index) {
        $("#county").append('<option value="' + county[index].code + '">' + county[index].name + '</option>');
    });
    if(countyId){
		$("#county").val(countyId);
	}
}

//4 渲染，学校
function RenderCollege( cityId , collegeId){
 	var college = getCollegeByCityId( cityId );
	$("#college").children().remove();
	$("#college").append('<option value="-1">请选择</option>');
    $.each( college, function (index) {
        $("#college").append('<option value="' + college[index].code + '">' + college[index].name + '</option>');
    });
	if( collegeId ){
		$("#college").val( collegeId );
	}
}
function RenderSchool( cityId , schoolId , elementId ){
	var param = {};
	param.cityCode = cityId; 
 
	$.ajax({
			type : 'POST',  
			contentType : 'application/json', 
			url:global_base_url+"/code/querySchool",
			processData : false,
			dataType : 'json', 
			data: JSON.stringify(param) ,    
			beforeSend:function(){ 
			},
			success: function(data){
				var str =  JSON.stringify( data );
				console.log(str);
				var schools = data.schools;
				$.each( schools, function (index) {
					$("#"+elementId ).append('<option value="' + schools[index].schoolcode + '">' + schools[index].schoolname + '</option>');
				});
				if ( schoolId && cityId > 0) {
					$("#"+elementId).val(schoolId);
				}
				
			} ,
			error: HttpErr, 
	});   
}


function RenderLocal( provinceId ,cityId , countyId ){
	
	if( cityId ){
		RenderCity( provinceId , cityId );
	}
	
	if( countyId ){
		RenderCounty( cityId , countyId);
	} 
}

/*************************************************************************************************************************************************************
 * 根据ID获取省份
 * @param code
 * @returns {{code, name}|*}
 */
function getPovinceByCode(code){
    for(var i = 0; i< PovinceCode.length; i++){
        var o = PovinceCode[i];
        if(o.code == code){
            return o;
        }
    }
}
/**
 * 根据名称获取省份
 * @param name
 * @returns {{code, name}|*}
 */
function getPovinceByName(name){
    for(var i = 0; i< PovinceCode.length; i++){
        var o = PovinceCode[i];
        if(o.name.indexOf(name) != -1){
            return o;
        }
    }
}
/**
 * 根据省份ID获取城市列表
 * @param code
 * @returns {Array}
 */
function getCityByPovinceCode(code){
    var citys = [];
    for(var i = 0; i< CityCode.length; i++){
        var o = CityCode[i];
        if(o.provinceCode == code){
            citys.push(o);
        }
    }
    return citys;
}

/**
 *根据城市ID获取区/县
 * @param code
 * @returns {Array}
 */
function getCountyByCityCode(code){
    var countys = [];
    for(var i = 0; i< CountyCode.length; i++){
        var o = CountyCode[i];
        if(o.cityCode == code){
            countys.push(o);
        }
    }
    return countys;
}
/**
 * 根据城市名称获取当前城市
 * @param name
 * @returns {{code, name, provinceCode}|*}
 */
function getCityByName(name){
    for(var i = 0; i< CityCode.length; i++){
        var o = CityCode[i];
        var o_name = o.name.replace("市辖区","");
        if(o_name.indexOf(name) != -1 || name.indexOf(o_name) != -1 || name.indexOf(o_name) != -1){
            return o;
        }
    }
}
/**
 * 根据城市ID获取当前城市
 * @param name
 * @returns {{code, name, provinceCode}|*}
 */
function getCityById(code){
    for(var i = 0; i< CityCode.length; i++){
        var o = CityCode[i];
        if(o.code == code){
            return o;
        }
    }
}

/**
 * 根据区/县名称获取区/县
 * @param name
 * @returns {{code, name, cityCode}|*}
 */
function getCountyByName(name){
    var city = getCityByName(name);
    if(city){
        for(var i = 0; i< CountyCode.length; i++){
            var o = CountyCode[i];
            if((o.name.indexOf(name) != -1 || name.indexOf(o.name) != -1) && o.cityCode == city.code){
                return o;
            }
        }
    }
}
/**
 * 根据区/县ID获取当前区/县
 * @param name
 * @returns {{code, name, cityCode}|*}
 */
function getCountyById(code){
    for(var i = 0; i< CountyCode.length; i++){
        var o = CountyCode[i];
        if(o.code == code){
            return o;
        }
    }
}

/**
 * 根据城市ID获取学校列表
 * @param name
 * @returns {{code, name, cityCode}|*}
 */
function getCollegeByCityId(code){
    var colleges = [];
    for(var i = 0; i< CollegeCode.length; i++){
        var o = CollegeCode[i];
        if(o.cityCode == code){
            colleges.push(o);
        }
    }
    return colleges;
}

/**
 * 根据学校名称模糊匹配
 * @param name
 * @returns {{code, name, cityCode}|*}
 */
function getCollegeByName(name){
    var colleges = [];
    for(var i = 0; i< CollegeCode.length; i++){
        var o = CollegeCode[i];
        if(o.name.indexOf(name) != -1){
            colleges.push(o);
        }
    }
    return colleges;
}