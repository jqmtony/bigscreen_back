package com.gochinatv.accelarator.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gochinatv.accelarator.api.bean.AdInfo;
import com.gochinatv.accelarator.api.bean.ImageAdInfo;
import com.gochinatv.accelarator.api.bean.TextAdInfo;
import com.gochinatv.accelarator.api.service.BusinessService;
import com.gochinatv.accelarator.api.service.DeviceService;
import com.gochinatv.accelarator.api.util.AccelaratorConfig;
import com.gochinatv.accelarator.api.util.DateUtils;
import com.gochinatv.accelarator.api.vo.ResponseAdInfo;
import com.gochinatv.accelarator.api.vo.ResponseDeviceInfo;
import com.gochinatv.accelarator.api.vo.ResponseImageAdInfo;
import com.gochinatv.accelarator.api.vo.ResponseTextAdInfo;
import com.gochinatv.accelarator.api.vo.ResponseWebAdInfo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("ad_v1")
public class AdController {

	private static Logger logger = LoggerFactory.getLogger(AdController.class);
	
	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private DeviceService deviceService;
	
	//@ResponseBody
	/**
	 * 广告体接口  终端开机调用，返回对应的基本信息
	 * @param mac
	 * @return
	 */
	@ApiOperation(value="获取广告体", httpMethod ="GET", notes ="获取广告体,如果没有则使用默认gochinatv设备") 
	@RequestMapping(value = "getDeviceInfo",produces = "application/json;charset=utf-8")
	public ResponseDeviceInfo getDeviceInfo(
			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac) {
		ResponseDeviceInfo responseDeviceInfo = new ResponseDeviceInfo();
		try {
			responseDeviceInfo = deviceService.getDeviceInfo(mac);
		} catch (Exception e) {
			logger.error("===getDeviceInfo===mac:"+mac+"  error:"+e.getMessage());
			responseDeviceInfo.setStatus(AccelaratorConfig.API_STATUS_ERROR);
			responseDeviceInfo.setMessage("getDeviceInfo error");
		}
		return responseDeviceInfo;
	}
	
	
	/**
	 *  视频列表
	 * @param mac
	 * @return
	 */
	@ApiOperation(value="视频列表", httpMethod ="GET", notes ="视频列表,如果没有则使用默认gochinatv设备") 
	@RequestMapping(value = "getAdList",produces = "application/json;charset=utf-8")
	public ResponseAdInfo getAdInfo(
			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac) {
		ResponseAdInfo responseAdInfo = new ResponseAdInfo();
		try {
			/*List<AdInfo> current = new ArrayList<AdInfo>();
			List<Business>  list = businessService.queryList();
			for(Business business : list){
				AdInfo adInfo = new AdInfo();
				adInfo.setAdVideoId(business.getId());
				adInfo.setAdVideoIndex(business.getId());
				adInfo.setAdVideoName(business.getUserName());
				adInfo.setAdVideoUrl("/2016/03/15/A96969F3000048C1.mp4");
				current.add(adInfo);
			}*/
			
			String now = DateUtils.convert(new Date(), DateUtils.DATE_FORMAT);
			List<AdInfo> current = deviceService.getAdInfo(mac,now);
			List<AdInfo> next = new ArrayList<AdInfo>();
			if(current==null || current.size()==0){
				responseAdInfo.setStatus(AccelaratorConfig.API_STATUS_ERROR);
			}else{
				String nextDay = DateUtils.convert(DateUtils.getNextDate(new Date()), DateUtils.DATE_FORMAT);
				 next = deviceService.getAdInfo(mac,nextDay);
			}
			responseAdInfo.setCurrent(current);
			responseAdInfo.setNext(next);
		} catch (Exception e) {
			logger.error("===getDeviceInfo===mac:"+mac+"  error:"+e.getMessage());
			responseAdInfo.setStatus(AccelaratorConfig.API_STATUS_ERROR);
			responseAdInfo.setMessage("getAdInfo error");
		}
		return responseAdInfo;
	}
	
	
	/**
	 * web广告接口
	 * @param mac
	 * @return
	 */
	@ApiOperation(value="web广告接口", httpMethod ="GET", notes ="web广告接口") 
	@RequestMapping(value = "getWebAdInfo",produces = "application/json;charset=utf-8")
	public ResponseWebAdInfo getWebAdInfo(
			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac) {
		ResponseWebAdInfo responseWebAdInfo = new ResponseWebAdInfo();
		try {
			responseWebAdInfo.setAdWebUrl("http://www.baidu.com");
		} catch (Exception e) {
			responseWebAdInfo.setStatus(1);
			responseWebAdInfo.setMessage("添加视频失败！");
		}
		return responseWebAdInfo;
	}
	
	
	/**
	 *图片广告接口
	 * @param mac
	 * @return
	 */
	@ApiOperation(value="图片广告接口", httpMethod ="GET", notes ="图片广告接口") 
	@RequestMapping(value = "getImageAdList",produces = "application/json;charset=utf-8")
	public ResponseImageAdInfo getImageAdInfo(
			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac) {
		ResponseImageAdInfo responseImageAdInfo = new ResponseImageAdInfo();
		try {
			responseImageAdInfo.setAdImgInterval(100);
			List<ImageAdInfo> data = deviceService.queryImageAdInfoList();
			responseImageAdInfo.setData(data);
		} catch (Exception e) {
			responseImageAdInfo.setStatus(1);
			responseImageAdInfo.setMessage(e.getMessage());
		}
		return responseImageAdInfo;
	}
	
	/**
	 *文字广告接口
	 * @param mac
	 * @return
	 */
	@ApiOperation(value="文字广告接口", httpMethod ="GET", notes ="文字广告接口") 
	@RequestMapping(value = "getTextAdList",produces = "application/json;charset=utf-8")
	public ResponseTextAdInfo getTextAdList(
			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac) {
		ResponseTextAdInfo responseImageAdInfo = new ResponseTextAdInfo();
		try {
			responseImageAdInfo.setAdTextInterval(100);
			
			List<TextAdInfo> data = new ArrayList<TextAdInfo>();
			for(int i=0;i<10;i++){
				TextAdInfo imageAdInfo = new TextAdInfo();
				imageAdInfo.setAdTextId(100+i);
				imageAdInfo.setAdTextIndex(i);
				imageAdInfo.setAdTextStr("测试");
				data.add(imageAdInfo);
			}
			responseImageAdInfo.setData(data);
		} catch (Exception e) {
			responseImageAdInfo.setStatus(1);
			responseImageAdInfo.setMessage("添加视频失败！");
		}
		return responseImageAdInfo;
	}
	
}
