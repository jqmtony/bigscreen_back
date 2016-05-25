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
	@ResponseBody
	public Map<String,Object> uploadImage( @RequestParam("file") MultipartFile file,
			@RequestParam(name="weight",defaultValue="486") int weight,
			@RequestParam(name="height",defaultValue="648") int height)
			throws Exception {
		Map<String,Object> result =new HashMap<String, Object>();
		String imageUrl = "";
		try {
			imageUrl = upload(file,weight,height);
			result.put("success","true");
			result.put("msg",imageUrl);
		} catch (Exception e) {
			result.put("success","false");
			result.put("msg","上传图片失败");
		}
		return result;
	}

	private String upload(MultipartFile file,int weight,int height) {
		String result="";
		FileChangeLocal fcl = new FileChangeLocal();
		File localFile = fcl.uploadFileLocal(file, file.getOriginalFilename());
		ImageTool it = new ImageTool();
		String fileName = localFile.getName();
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
		logger.info("fileName======"+fileName+",suffix==="+suffix);
		File reproduceFile;
		try {
			reproduceFile = it.createThumbnailNew(localFile, suffix, weight, height);
			result = AmazonS3Tools.uploadFileToAmazon(suffix, reproduceFile, weight, height);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println("==this is master========");
	}

}
