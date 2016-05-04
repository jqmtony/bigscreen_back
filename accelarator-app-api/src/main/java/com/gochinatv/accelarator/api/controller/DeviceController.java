package com.gochinatv.accelarator.api.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gochinatv.accelarator.api.bean.Device;
import com.gochinatv.accelarator.api.bean.UploadLog;
import com.gochinatv.accelarator.api.service.DeviceService;
import com.gochinatv.accelarator.api.util.FileChangeLocal;
import com.gochinatv.accelarator.api.util.HttpClientTools;
import com.gochinatv.accelarator.api.util.PropertiesUtil;
import com.gochinatv.accelarator.api.vo.BaseVo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("device_v1")
public class DeviceController {

	private static Logger logger = LoggerFactory.getLogger(DeviceController.class);
	
	@Autowired
	private DeviceService deviceService;

	@ApiOperation(value = "上传图片", httpMethod = "GET", notes = "上传图片")
	@RequestMapping(value = "uploadImage", produces = "application/json;charset=utf-8")
	public BaseVo uploadImage(

			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac,
	           @RequestParam(required = true, defaultValue = "name")
	           @ApiParam(value = "name", required = true) String name/*,
			@ApiParam(value = "图片文件", required = true) @RequestParam("file") MultipartFile file*/)
			throws Exception {
		BaseVo baseVo = new BaseVo();

		String imageUrl = "";
		try {
			/*imageUrl = uploadImage(file);*/
			Device device = new Device();
			device.setName(name);
			device.setMac(mac);
			device.setImageUrl(imageUrl);
			logger.info("====mac:" + mac + "==url:" + imageUrl);
			deviceService.saveDeviceImage(device);
		} catch (Exception e) {
			logger.info("=====mac:" + mac + "===upload.error:"
					+ e.getMessage());
			baseVo.setStatus(1);
			baseVo.setMessage("上传图片失败");
		}
		return baseVo;
	}

	private String uploadImage(MultipartFile file) {
		String result;
		FileChangeLocal fcl = new FileChangeLocal();
		File localFile = fcl.uploadFileLocal(file, file.getOriginalFilename());
		String url = PropertiesUtil.getInstance().getProperty(
				"gochinatv.syncimage.process.url");
		Map<String, String> heads = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("fileType", "VRS");
		params.put("markId", "");
		params.put("realId", "");
		params.put("source", "");
		heads.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
		result = HttpClientTools.Upload(url, localFile, heads, params);
		JSONObject jsonObject = JSONObject.fromObject(result);
		String statuString = jsonObject.getString("msg");
		return statuString;
	}
	
	
	@ApiOperation(value = "上传日志", httpMethod = "GET", notes = "上传日志")
	@RequestMapping(value = "uploadLog", produces = "application/json;charset=utf-8")
	public BaseVo uploadLog(

			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac,
	           @RequestParam(required = true, defaultValue = "msg")
	           @ApiParam(value = "msg", required = true) String msg)
			throws Exception {
		BaseVo baseVo = new BaseVo();
		try {
			UploadLog uploadLog = new UploadLog();
			uploadLog.setMsg(msg);
			uploadLog.setMac(mac);
			logger.error("====mac:" + mac + "==msg:" + msg);
			deviceService.uploadLog(uploadLog);
		} catch (Exception e) {
			logger.info("=====mac:" + mac + "===upload.error:"
					+ e.getMessage());
			baseVo.setStatus(1);
			baseVo.setMessage("上传日志失败");
		}
		return baseVo;
	}
}
