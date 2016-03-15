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