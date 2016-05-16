package com.gochinatv.accelarator.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.util.upload.FileChangeLocal;
import com.gochinatv.accelarator.util.upload.HttpClientTools;
import com.gochinatv.accelarator.util.upload.PropertiesUtil;


@Controller
@RequestMapping("/imagecallback")
public class ImageUploadController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
	
	
	@RequestMapping("/upload")
	@ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) throws Exception {
		Map<String,Object> result = null;
		try {
			 FileChangeLocal fcl = new FileChangeLocal();
			 File localFile = fcl.uploadFileLocal(file, file.getOriginalFilename());
		   String url = PropertiesUtil.getInstance().getProperty("gochinatv.syncimage.process.url");	
			Map<String, String> heads = new HashMap<String, String>();
			Map<String, String> params = new HashMap<String, String>();
			params.put("fileType", "VRS");
			params.put("markId", "");
			params.put("realId", "");
			params.put("source", "");
			heads.put("Content-Type", "	application/x-www-form-urlencoded; charset=UTF-8");
		    String backUrl = HttpClientTools.Upload(url, localFile, heads,params);
		    JSONObject back = JSONObject.parseObject(backUrl);
		    result = this.success(back.get("msg"));
			logger.info(backUrl);
		} catch (Exception e) {
			e.printStackTrace();
			result = this.error(e.getMessage());
		}
		return result;
	}
}
