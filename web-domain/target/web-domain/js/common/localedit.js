/**
 * 获取省份下拉框
 */
function createProvince() {
    var pros = parent.PovinceCode;
    if(!pros){
        pros = parent.parent.PovinceCode;
    }
    $.each(pros, function (pro) {
        $("#province").append('<option value="' + pros[pro].code + '">' + pros[pro].name + '</option>');
    });
};
/**
 * 获取城市下拉框
 */
function updateCityByPro(proId, cityId) {
    var citys = null;
    if(parent.getCityByPovinceCode) {
        citys = parent.getCityByPovinceCode(proId);
    }else{
        citys = parent.parent.getCityByPovinceCode(proId);
    }
    $.each(citys, function (index) {
        $("#city").append('<option value="' + citys[index].code + '">' + citys[index].name + '</option>');
    });
    if (cityId && cityId > 0) {
        $("#city").val(cityId);
    }
};
/**
 * 获取区/县下拉框
 * @param cityId
 * @param countyId
 */
function updateCountyByCity(cityId, countyId) {
    var county = null;
    if(parent.getCountyByCityCode) {
        county = parent.getCountyByCityCode(cityId);
    }else{
        county = parent.parent.getCountyByCityCode(cityId);
    }
    $("#county").children().remove();
    $("#county").append('<option value="-1">请选择</option>');
    $.each(county, function (index) {
        $("#county").append('<option value="' + county[index].code + '">' + county[index].name + '</option>');
    });
    if(countyId)
        $("#county").val(countyId);
};

/**
 *
 * @param cityName
 */
function setCitySelectByCityName(cityName) {
    var city = null;
    if(parent.getCityByName) {
        city = parent.getCityByName(cityName);
    }else{
        city = parent.parent.getCityByName(cityName);
    }
    if (city) {
        var province = city.provinceCode;
        $("#province").val(province);
        updateCityByPro(province, city.code);
        $('#city').trigger('change', [-1]);
    }
};

function getCityNameBySelect(){
    var citySelect = $('#city');
    if(citySelect.length > 0) {
        return citySelect.children('option:selected').text();
    }
    return null;
}
function getCountyNameBySelect(){
    var returnStr = '';
    var proSelect = $('#province');
    var citySelect = $('#city');
    var countySelect = $('#county');
    if(proSelect.length > 0) {
        returnStr += proSelect.children('option:selected').text();
    }
    if(citySelect.length > 0) {
        returnStr += citySelect.children('option:selected').text();
    }
    if(countySelect.length > 0) {
        returnStr += countySelect.children('option:selected').text();
    }
    return returnStr;
}

$(function(){
    if($('#province') != null){
        $('#province').change(function (event, cityId, countyId,nogeo) {
            var proId = $(this).children('option:selected').val();
            $("#city").children().remove();
            $("#city").append('<option value="-1">请选择</option>');
            $("#county").children().remove();
            $("#county").append('<option value="-1">请选择</option>');
            if (proId != -1) {
                updateCityByPro(proId, cityId,nogeo);
                if (cityId != null) {
                    countyId = countyId == null ? -1 : countyId;
                    $('#city').trigger('change', [countyId,nogeo]);
                }
            }
        });
    }
    if($('#city') != null){
        $('#city').change(function (event, countyId,nogeo) {
            var proName = $('#province').children('option:selected').text();
            var cityId = $(this).children('option:selected').val();
            var cityName = $(this).children('option:selected').text();
            if (cityId != -1) {
                updateCountyByCity(cityId, countyId);
                if (!nogeo && $('#mapMatch').length > 0 && Map.gaodeMap) {
                    //Map.baiduMap.geo(cityName, proName + cityName);
                    Map.gaodeMap.geo(cityName, proName + cityName);
                }
            }
        });
    }
    if($('#county') != null){
        $('#county').change(function (event, nogeo) {
            var proName = $('#province').children('option:selected').text();
            var cityName = $('#city').children('option:selected').text();
            var countyName = $(this).children('option:selected').text();
            var countyId = $(this).children('option:selected').val();
            if (countyId != -1) {
                $('#street').focus();
                if (!nogeo && $('#mapMatch').length > 0 && Map.gaodeMap) {
                    //Map.baiduMap.geo(cityName, proName + cityName + countyName);
                    Map.gaodeMap.geo(cityName, proName + cityName + countyName);
                }
            } else {

            }
            $('#local').val('');
            $('#street').val('');
        });
    }
    if($('#mapMatch') != null){
        $('#mapMatch').click(function (e) {
            var proName = $('#province').children('option:selected').text();
            var cityName = $('#city').children('option:selected').text();
            var countyName = $('#county').children('option:selected').text();
            var street = $('#street').val();
            if (street != '' && Map.gaodeMap) {
                //Map.baiduMap.geo(cityName, proName + cityName + countyName + street);
                Map.gaodeMap.geo(cityName, proName + cityName + countyName + street);
                $('#local').val(proName + cityName + countyName + street);
            }
        });
    }
});

