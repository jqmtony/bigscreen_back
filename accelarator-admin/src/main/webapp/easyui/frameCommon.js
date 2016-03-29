/**
 * 所有以“_”开始的方法和变量都属于内部变量，不对外使用。
 */

$(document).ready(function() {
	addWin = $('#add-window').dialogFrameWindow();
	addForm = $('#addForm').form();
	modifyWin = $('#modify-window').dialogFrameWindow();
	modifyForm = $('#modifyForm').form();
	lookUpWin = $("#lookUpSelect-window").dialogFrameWindow();
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
	//var url ='${base}/business/gotoBusinessLookUp?parentMethod=openLookUpCallBack';
	lookUpWin.dialogFrameHtml(url);
}

function openLookUpCallBack(row){
	$('#add_lookUpId,#edit_lookUpId').val(row.id);
	$('#add_lookUpName,#edit_lookUpName').val(row.userName);
	$('#add_lookUpCname,#edit_lookUpCname').val(row.cname);
	lookUpWin.window('close');

}