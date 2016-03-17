var crud={};

/**
 * dgid datagrid的id值，默认为dg_search_0
 * url 请求的url
 * method 请求方式  'get' or 'post' 默认为 'post'
 * params 请求参数  {username:zhangsan,sex:1}  json对象格式
 * func 回调函数
 */
crud.search=function(setting){
	var defaults = {
		dgid : 'dg_search_0',
		url : '',
		method : 'post',
		params : {},
		func : null
	};
	$.extend(defaults,setting); 
	$('#'+defaults.dgid).datagrid({
		url : defaults.url,
		method : defaults.method,
		pageNumber : 1,
		queryParams : defaults.params
	});
}


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


var init_json = [ { 'id' : 0, 'text' : '全部' } ];
function initCountry(combobox_id) {
	country_json = init_json; 
	for (i = 0; i < xzqh.length; i++) {
		var country = {};
		country.id = xzqh[i].id;
		country.text = xzqh[i].text;
		country_json.push(country);
	}
	init_default(combobox_id,country_json);
}

function init_default(combobox_id,json_data){
	area_json = init_json; 
	$('#'+combobox_id).combobox({
	    valueField:'id',
	    textField:'text',
	    data:json_data
	});
}


function combobox_change(combobox_id) {
	$('#' + combobox_id).combobox({
		onChange : function(newValue, oldValue) {
			area_json = init_json;
			for (i = 0; i < xzqh.length; i++) {
				var children = xzqh[i].children;
				if (newValue == xzqh[i].id) {
					for (j = 0; j < children.length; j++) {
						var area = {};
						area.id = children[j].id;
						area.text = children[j].text;
						area_json.push(area);
					}
				}
			}
			init_default(combobox_id, area_json);
		}
	});
}
