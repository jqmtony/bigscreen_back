
/************************************************************************************************************/
/**
 * 国家格式化
 */
function formatCountry(val, row) {
	var text='暂无';
	for(var i=0;i<_country.length;i++){
		if(_country[i].id==val){
			text = _country[i].text;
			break;
		}
	}
	return text;
}

/**
 * 地区格式化
 * @param val
 * @param row
 * @returns {String}
 */
function formatArea(val, row) {
	var text='暂无';
	if(val!=null && val!=""){
	   var country_code = val.substring(0,4);
	   var _json = eval("_"+country_code);
	   for(var i=0;i<_json.length;i++){
		  if(_json[i].id==val){
			  text = _json[i].text;
			  break;
		  }   
	   }
	}
	return text;
}

/**
 * 地市格式化
 * @param val
 * @param row
 * @returns {String}
 */
function formatCity(val, row) {
	var text='暂无';
	if(val!=null && val!=""){
	   var area_code = val.substring(0,6);
	   var _json = eval("_"+area_code);
	   for(var i=0;i<_json.length;i++){
		  if(_json[i].id==val){
			  text = _json[i].text;
			  break;
		  }   
	   }
	}
	return text;
}

/**
 * 启用状态
 * @param val
 * @param row
 * @returns {String}
 */
function formatStatus(val, row) {
	if (val == 1) {
		return '已启用';
	} else {
		return '已禁用';
	}
}

/**
 * 广告来源
 * @param val
 * @param row
 * @returns {String}
 */
function formatSource(val, row) {
	if (val == 1) {
		return '自有';
	} else if(val == 2){
		return '商家广告';
	}
}

/*
function createCountryOptions(combobox_id) {
	var country_json = [ { 'id' : '', 'text' : '--请选择--' } ]; 
	for (i = 0; i < _country.length; i++) {
		var country = {};
		country.id = _country[i].id;
		country.text = _country[i].text;
		country_json.push(country);
	}
	createOption(combobox_id,country_json);
}

function createAreaOptions(combobox_id) {
	var default_option = [ { 'id' : '', 'text' : '--请选择--' } ];
	createOption(combobox_id,default_option);
}

function createCityOptions(combobox_id) {
	var default_option = [ { 'id' : '', 'text' : '--请选择--' } ];
	createOption(combobox_id,default_option);
}

function createOption(combobox_id,json_data){
	$('#'+combobox_id).combobox({
	    valueField:'id',
	    textField:'text',
	    data:json_data
	});
}*/

function dateFormat(now) {
	var result=[];
	var year = now.getFullYear();
	var month = now.getMonth() + 1;
	var date = now.getDate();
	var hour = now.getHours();
	var minute = now.getMinutes();
	var second = now.getSeconds();
	result.push(year);
	result.push(month);
	result.push(date);
	result.push(hour);
	result.push(minute);
	result.push(second);
	return result;
} 

/**
 * 格式化时间YYYY-MM-DD
 * @param val
 * @param row
 * @returns {String}
 */
function formatYYYYMMDD(val, row){
	var result = dateFormat(new Date(val));
	return result[0]+ "-" + result[1] + "-" + result[2];
}

/**
 * 格式化时间YYYY-MM-DD HH:MM:SS
 * @param val
 * @param row
 * @returns {String}
 */
function formatYYYYMMDDHHMMSS(val, row){
	var result = dateFormat(new Date(val));
	return result[0]+ "-" + result[1] + "-" + result[2]+ " " + result[3] + ":" + result[4] + ":" + result[5];
}

/**
 * 店铺类型
 * @param val
 * @param row
 * @returns {String}
 */
function formatPlaceType(val, row) {
	if (val == 1) {
		return '餐厅';
	} else if(val == 2){
		return '大使馆';
	}else if(val == 3){
		return '商场';
	}else if(val == 4){
		return '美甲区';
	}else if(val == 5){
		return '其他';
	}
}

/**
 * 广告类型
 * @param val
 * @param row
 * @returns {String}
 */
function formatAdvertisementType(val, row) {
	if (val == 1) {
		return '视频类型';
	} else if(val == 2){
		return '图片类型';
	}else if(val == 3){
		return '文字类型';
	}
}


function formatOnlineStatus(val, row) {
	if (val == 1) {
		return '上线';
	} else {
		return '下线';
	}
}