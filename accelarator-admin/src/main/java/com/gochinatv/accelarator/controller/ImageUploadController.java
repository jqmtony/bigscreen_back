package com.gochinatv.accelarator.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gochinatv.accelarator.util.upload.FileChangeLocal;
import com.gochinatv.accelarator.util.upload.HttpClientTools;
import com.gochinatv.accelarator.util.upload.PropertiesUtil;


@Controller
@RequestMapping("/imagecallback")
public class ImageUploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(ImageUploadController.class);
	
	
	@RequestMapping("/upload")
    public void upload(HttpServletRequest request,
			HttpServletResponse response,@RequestParam("file") MultipartFile file) throws Exception {
		String wq ="";
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
		    wq = HttpClientTools.Upload(url, localFile, heads,params);
			logger.info(wq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.setContentType("text/html; charset=utf-8");
		
		response.getWriter().write(wq);
		response.getWriter().close();
		response.getWriter().flush();
	}
}
