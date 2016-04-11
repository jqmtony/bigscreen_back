/**
 * 所有以“_”开始的方法和变量都属于内部变量，不对外使用。
 */

$(document).ready(function() {
	addWin = $('#add-window').dialogFrameWindow();
	addForm = $('#addForm').form();
	modifyWin = $('#modify-window').dialogFrameWindow();
	modifyForm = $('#modifyForm').form();
	lookUpWin = $("#lookUpSelect-window").dialogFrameWindow();
	$("#add-window, #modify-window").window({ 
		top:60,   
        left:300,
        width:600,
        height:360,
		resizable: false
	});
});

var modifyWin;
var modifyForm;	
var addWin;
var addForm;
var lookUpWin;
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
	$("#add_cityCode").combotree('setValue','');
	$("#add_status,#add_source,#add_type").combobox('setValue','1');
	$("#add_screenNum").combobox('setValue','1');
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
		//XFrameUtils.clearForm('modifyForm'); 
		$('#modifyForm').form('clear');
		//XFrameUtils.fillForm('modifyForm',row);
		$('#modifyForm').form('load',row);
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
//校验用户名的唯一性
//修改时
function checkUpdateUserName(id, userName, url){
	var userId = $("#id").val();
	checkUserName(id, userId, userName, url);
}
//添加时
function checkUserName(id, userId, userName, url){
	$.ajax({  
		    url:url,   
		    data:{  
		    	id : userId,
		    	userName : userName
		    },  
		    type:'post',  
		    dataType:'json',  
		    success:function(data) { 
		        if(data == false ){
		        	alert("用户名已存在，请重新输入！");
		            $("#"+id).val(''); 
		            $("#"+id).focus();
		        } 
		     },  
		     error : function() {  
		          alert("异常！");  
		     }  
		});
}
//校验确认密码
function checkRetPassword(){
	var psw = $("#password").val();
	var r_psw = $("#ret_password").val();
	if((psw == null || psw == "") && (r_psw != null && r_psw != "")){
		alert("请先输入密码！");
		$("#password").focus();
		$("#ret_password").val('');
	} else if((psw != null && psw != "") && (r_psw != null && r_psw != "") && psw != r_psw){
		alert("两次输入的密码不一样!");
		$("#ret_password").focus();
		$("#ret_password").val('');
	}
}

//焦点定位
function onFocusMain(){
	$('#mainPanel').focus();
}
//有地区查询条件的查询的查询
function queryWithCode(){
	$("#queryCountryCode").val("");
	$("#queryAreaCode").val("");
	$("#queryCityCode").val("");
	var code = $("#query_code").combotree("getValue");
	if(code != null && code != ""){
		if(code.length == 4){
			$("#queryCountryCode").val(code);
		} else if(code.length == 6){
			$("#queryCountryCode").val(code.substring(0,4));
			$("#queryAreaCode").val(code);
		}else if(code.length == 8){
			$("#queryCountryCode").val(code.substring(0,4));
			$("#queryAreaCode").val(code.substring(0,6));
			$("#queryCityCode").val(code);
		}
	}
	query();
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
		if(_country[i].id==val){
			text = _country[i].text;
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
		  if(_json[i].id==val){
			  text = _json[i].text;
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
		  if(_json[i].id==val){
			  text = _json[i].text;
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

function formatType(val, row) {
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

$(function(){
	
	var default_option = "<option value=''>--请选择--</option>";
	var country = $("select[id$='countryCode']");
	country.html("");
	country.append(default_option);
	for (i = 0; i < _country.length; i++) {
		country.append("<option value="+_country[i].id+">"+_country[i].text+"</option>");
	}
	
	$("select[id$='areaCode'],select[id$='cityCode']").html(default_option);
	
	$("select[id$='countryCode'],select[id$='areaCode']").change(function(){
		   var value = $(this).val();
		   var targetId = $(this).attr("target");
		   var data = eval("_"+value);
		   $("#"+targetId).html(default_option);
		   for (i = 0; i < data.length; i++) {
				$("#"+targetId).append("<option value="+data[i].id+">"+data[i].text+"</option>");
			}
	});
	
	
	$("table","#addForm,#modifyForm").attr("class","table-bordered");
	var table = "table[class='table-bordered']";
	$("tr:eq(0) > td",table).attr("style","border-top:0px");
	$("tr > td:even",table).attr("bgcolor","#E0ECFF");
	$("tr > td:nth-child(1)",table).attr("style","border-left:0px");
	$("tr > td:eq(0)",table).attr("style","border-left:0px;border-top:0px;");
	
});

/**************************************************************************************************************/
//lookup
function openLookUp(url) {
	lookUpWin.dialogFrameHtml(url);
}

function openLookUpCallBack(row){
	$('#add_lookUpId,#edit_lookUpId').val(row.id);
	$('#add_lookUpName,#edit_lookUpName').val(row.userName);
	$('#add_lookUpCname,#edit_lookUpCname').val(row.cname);
	
	$('#add_lookUpName,#edit_lookUpName').validatebox({novalidate: true});
	lookUpWin.window('close');
}
