/**
 * 所有以“_”开始的方法和变量都属于内部变量，不对外使用。
 */

$(document).ready(function() {
	addWin = $('#add-window').dialogFrameWindow();
	addForm = $('#addForm').form();
	modifyWin = $('#modify-window').dialogFrameWindow();
	modifyForm = $('#modifyForm').form();
});

var modifyWin;
var modifyForm;	
var addWin;
var addForm;
/**
 * 回调查询
 */
function callback(data){
	if(data.status)
		grid.datagrid('reload',$("#queryForm").serializeObject());
	else
		$.messager.alert('系统提示',data.msg,'warning');	
}

/**
 * 增加
 */
function add(){
	addWin.window('open');
	XFrameUtils.clearForm('addForm'); 
	addDefaultCheck();
}

function addDefaultCheck(){
	
}

function modify(){
	var row = grid.datagrid('getSelected');
	if (row){
		modifyRow(row);
	} else {
		$.messager.show({
			title:'系统提示', 
			msg:'请先选择信息。'
		});
	}
}

//更新
function modifyRow(row){
		modifyWin.window('open');
		XFrameUtils.clearForm('modifyForm'); 
//		$('#modifyForm').form('clear');
		XFrameUtils.fillForm('modifyForm',row);
		modifyDefaultCheck(row);
}

function modifyDefaultCheck(row){
	
}
//模块名称
function removeModule(){
	var row = grid.datagrid('getSelected');
	if (row){
		$.messager.confirm('系统提示', '确定要删除这条记录吗？', function(r){
			if (r){
				$.post('delete?id='+row.id,'',callback,'json');
			}
		});
	} else {
		$.messager.show({
			title:'系统提示', 
			msg:'请先选择信息'
		});
	}
}

//焦点定位
function onFocusMain(){
	$('#mainPanel').focus();
}

function query(){
	grid.datagrid('reload',$("#queryForm").serializeObject());	
}


function addCallback(data){
	if(!data.status){
		$.messager.alert('系统提示',data.msg,'warning');
	}else{
		addWin.window('close');
		grid.datagrid('reload',$("#queryForm").serializeObject());	
	}
}


function addSave(url){
	if(addForm.form('validate')){
		$.post( url,$("#addForm").serializeObject(),addCallback,'json');		
	}
}


function cancel(){
	addWin.window('close');
}

//=================================修改==============

function modifySave(url){
	if(modifyForm.form('validate')){
			$.post(url,$("#modifyForm").serializeObject(),modifyCallback,'json');		
	}
}
	
function modifyCallback(data){
	if(!data.status){
		$.messager.alert('系统提示',data.msg,'warning');
	}else{
		modifyWin.window('close');
		grid.datagrid('reload',$("#queryForm").serializeObject());	
	}
}

function winClose(){
	modifyWin.window('close');
}


function uploadPic(form,id,idFileId,urlTmp){
	var reg = /(jpg|gif|bmp|png)/i;
	if(!reg.test($(idFileId).val())){
		$.messager.alert("提示","请选择图片");
		return ;
	}
	$('#'+form).form('submit',{
		url:XFrame.getContextPath()+ urlTmp,
		success:function(data){
			var object =eval('('+data+')');
		 	if(object.success){
		   		$.messager.alert("提示","上传成功");
		   		$(id).val(object.msg);
		   	}else{
		   		if(object.msg!=undefined&&object.msg!=null&&object.msg!=''){
		   			$.messager.alert("提示",object.msg);
		   		}else{
		   			$.messager.alert("提示","上传失败，请重试");
		   		}
		   	}
		}
	});
}


function show(text){
	if(text==''){
		$.messager.alert("提示","暂无图片");
		return ;
	}
	$("#custom").attr("src",text);
	$("#priewPicWin").window({width:300,height:300,top:100});
	$("#priewPicWin").window('open');
}
function showPic(picId){
	var text = $(picId).val();
	show(text);
}
function pressEnter(event,functionname){
	if(event.keyCode==13){
		functionname();
	}
}

/************************************************************************************************************/
function formatCountry(val, row) {
	var text='暂无';
	for(var i=0;i<_country.length;i++){
		if(_country[i].code==val){
			text = _country[i].value;
			break;
		}
	}
	return text;
}

function formatArea(val, row) {
	var text='暂无';
	if(val!=null && val!=""){
	   var country_code = val.substring(0,4);
	   var _json = eval("_"+country_code);
	   for(var i=0;i<_json.length;i++){
		  if(_json[i].code=val){
			  text = _json[i].value;
			  break;
		  }   
	   }
	}
	return text;
}

function formatCity(val, row) {
	var text='暂无';
	if(val!=null && val!=""){
	   var area_code = val.substring(0,6);
	   var _json = eval("_"+area_code);
	   for(var i=0;i<_json.length;i++){
		  if(_json[i].code=val){
			  text = _json[i].value;
			  break;
		  }   
	   }
	}
	return text;
}

function formatStatus(val, row) {
	if (val == 1) {
		return '已启用';
	} else {
		return '已禁用';
	}
}

function formatSource(val, row) {
	if (val == 1) {
		return '自有';
	} else if(val == 2){
		return '商家广告';
	}
}

/*
function createCountryOptions(combobox_id) {
	var country_json = [ { 'code' : '', 'value' : '--请选择--' } ]; 
	for (i = 0; i < _country.length; i++) {
		var country = {};
		country.code = _country[i].code;
		country.value = _country[i].value;
		country_json.push(country);
	}
	createOption(combobox_id,country_json);
}

function createAreaOptions(combobox_id) {
	var default_option = [ { 'code' : '', 'value' : '--请选择--' } ];
	createOption(combobox_id,default_option);
}

function createCityOptions(combobox_id) {
	var default_option = [ { 'code' : '', 'value' : '--请选择--' } ];
	createOption(combobox_id,default_option);
}

function createOption(combobox_id,json_data){
	$('#'+combobox_id).combobox({
	    valueField:'code',
	    textField:'value',
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


$(function(){
	
	var default_option = "<option value=''>--请选择--</option>";
	var country = $("select[id$='countryCode']");
	country.html("");
	country.append(default_option);
	for (i = 0; i < _country.length; i++) {
		country.append("<option value="+_country[i].code+">"+_country[i].value+"</option>");
	}
	
	$("select[id$='areaCode'],select[id$='cityCode']").html(default_option);
	
	$("select[id$='countryCode'],select[id$='areaCode']").change(function(){
		   var value = $(this).val();
		   var targetId = $(this).attr("target");
		   var data = eval("_"+value);
		   $("#"+targetId).html(default_option);
		   for (i = 0; i < data.length; i++) {
				$("#"+targetId).append("<option value="+data[i].code+">"+data[i].value+"</option>");
			}
	});
	
	/*$('#countryCode,#areaCode').combobox({
		onChange : function(newValue, oldValue) {
			var area_json = [ { 'code' : '', 'value' : '--请选择--' } ];
			var _json = eval("_"+newValue);
			for (i = 0; i < _json.length; i++) {
				var area = {};
				area.code = _json[i].code;
				area.value = _json[i].value;
				area_json.push(area);
			}
			var target = $($(this)).attr("target");
			createOption(target, area_json);
		}
	});*/
	
});

/**************************************************************************************************************/
