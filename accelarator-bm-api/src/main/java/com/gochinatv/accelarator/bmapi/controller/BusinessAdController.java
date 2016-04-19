package com.gochinatv.accelarator.bmapi.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gochinatv.accelarator.bmapi.bean.BaseVo;
import com.gochinatv.accelarator.bmapi.bean.BusinessAd;
import com.gochinatv.accelarator.bmapi.service.BusinessAdService;
import com.gochinatv.accelarator.bmapi.util.FileChangeLocal;
import com.gochinatv.accelarator.bmapi.util.HttpClientTools;
import com.gochinatv.accelarator.bmapi.util.PropertiesUtil;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 商铺广告
 * @author vego
 *
 */
@Controller
@RequestMapping("bm_v1")
public class BusinessAdController  extends BaseController{


	@Autowired
	private BusinessAdService businessAdService;
	
	private static Logger logger = LoggerFactory.getLogger(BusinessAdController.class);
	
	@ApiOperation(value = "上传图片", httpMethod = "GET", notes = "上传图片")
	@RequestMapping(value = "uploadImage", produces = "application/json;charset=utf-8")
	public BaseVo uploadImage(
			@ApiParam(value = "图片文件", required = true) @RequestParam("file") MultipartFile file)
			throws Exception {
		BaseVo baseVo = new BaseVo();

		String imageUrl = "";
		try {
			imageUrl = upload(file);
		} catch (Exception e) {
			logger.info("===upload.error:"
					+ e.getMessage());
			baseVo.setStatus(1);
			baseVo.setMessage("上传图片失败");
		}
		return baseVo;
	}

	private String upload(MultipartFile file) {
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
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public List<BusinessAd> queryList(
			BusinessAd businessAd) throws Exception{
		List<BusinessAd> list = businessAdService.queryList(businessAd);
		return list;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(BusinessAd businessAd){
		Map<String,Object> result = this.success(null);
		try{
			businessAdService.save(businessAd);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(BusinessAd businessAd){
		Map<String,Object> result = this.success(null);
		try{
			businessAdService.update(businessAd);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(int businessAdId){
		Map<String,Object> result = this.success(null);
		try{
			businessAdService.delete(businessAdId);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
}
