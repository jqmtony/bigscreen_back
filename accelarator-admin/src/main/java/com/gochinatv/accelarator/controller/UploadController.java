package com.gochinatv.accelarator.controller;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.util.FileChangeLocal;
import com.gochinatv.accelarator.util.imageUpload.AmazonS3Tools;
import com.gochinatv.accelarator.util.imageUpload.ImageTool;

/**
 * 商铺广告
 * @author vego
 *
 */
@Controller
@RequestMapping("upload")
public class UploadController  extends BaseController{


	private static Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@RequestMapping(value = "uploadImage", produces = "application/json;charset=utf-8")
	public Map<String,Object>  uploadImage( @RequestParam("file") MultipartFile file)
			throws Exception {
		Map<String,Object> result = this.success(null);
		String imageUrl = "";
		try {
			imageUrl = upload(file);
			result = this.success(imageUrl);
		} catch (Exception e) {
			result = this.error(e.getMessage());
		}
		return result;
	}

	private String upload(MultipartFile file) {
		String result="";
		FileChangeLocal fcl = new FileChangeLocal();
		File localFile = fcl.uploadFileLocal(file, file.getOriginalFilename());
		ImageTool it = new ImageTool();
		String fileName = localFile.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
		logger.info("fileName======"+fileName+",suffix==="+suffix);
		File reproduceFile;
		try {
			reproduceFile = it.createThumbnailNew(localFile, suffix, 324, 243);
			result = AmazonS3Tools.uploadFileToAmazon(suffix, reproduceFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
