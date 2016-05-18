package com.gochinatv.accelarator.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gochinatv.accelarator.api.bean.AdInfo;
import com.gochinatv.accelarator.api.bean.TextAdInfo;
import com.gochinatv.accelarator.api.bean.TwoAdInfo;
import com.gochinatv.accelarator.api.service.DeviceService;
import com.gochinatv.accelarator.api.util.AccelaratorConfig;
import com.gochinatv.accelarator.api.util.DateUtils;
import com.gochinatv.accelarator.api.vo.ResponseAdInfo;
import com.gochinatv.accelarator.api.vo.ResponseDeviceInfo;
import com.gochinatv.accelarator.api.vo.ResponseImageAdInfo;
import com.gochinatv.accelarator.api.vo.ResponseTextAdInfo;
import com.gochinatv.accelarator.api.vo.ResponseTwoAdInfo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("ad_v1")
public class AdController {

	private static Logger logger = LoggerFactory.getLogger(AdController.class);
	
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
		long start = System.currentTimeMillis();
		ResponseDeviceInfo responseDeviceInfo = new ResponseDeviceInfo();
		try {
			responseDeviceInfo = deviceService.getDeviceInfo(mac);
		} catch (Exception e) {
			logger.error("===getDeviceInfo===mac:"+mac+"  error:"+e.getMessage());
			responseDeviceInfo.setStatus(AccelaratorConfig.API_STATUS_ERROR);
			responseDeviceInfo.setMessage("getDeviceInfo error");
		}
		long end = System.currentTimeMillis();
		System.out.println("===getDeviceInfo===mac:"+mac+"==time:"+(end-start) );
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
	           @ApiParam(value = "设备MAC地址", required = true) String mac,
	           @RequestParam(required = false)
	           @ApiParam(value = "时间,格式2016-04-23", required = false) String time) {
		long start = System.currentTimeMillis();
		ResponseAdInfo responseAdInfo = new ResponseAdInfo();
		String now ="";
		try {
		    now = DateUtils.convert(new Date(), DateUtils.DATE_FORMAT);
			if(StringUtils.isNotBlank(time)){
				now = time;
			}
		
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
		long end = System.currentTimeMillis();
		System.out.println("===getAdInfo===mac:"+mac+"===now:"+now+"=time:"+(end-start) );
		return responseAdInfo;
	}
	/**
	 *图片广告接口
	 * @param mac 2号位接口
	 * @return
	 */
	@ApiOperation(value="web广告接口", httpMethod ="GET", notes ="web广告接口") 
	@RequestMapping(value = "getWebAdInfo",produces = "application/json;charset=utf-8")
	public ResponseTwoAdInfo getWebAdInfo(
			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac,
	           @RequestParam(required = false)
	           @ApiParam(value = "时间,格式2016-04-23", required = false) String time) {
		long start = System.currentTimeMillis();
		ResponseTwoAdInfo responseImageAdInfo = new ResponseTwoAdInfo();
		try {
			responseImageAdInfo.setAdTextInterval(5000);
		    String now = DateUtils.convert(new Date(), DateUtils.DATE_FORMAT);
			if(StringUtils.isNotBlank(time)){
				now = time;
			}
			List<TwoAdInfo> data =  deviceService.queryTwoAdInfoList(mac,now);
			responseImageAdInfo.setData(data);
		} catch (Exception e) {
			responseImageAdInfo.setStatus(1);
			responseImageAdInfo.setMessage("error");
			logger.error("===getWebAdInfo===mac:"+mac+"===now:"+time+"error"+e.getMessage());
		}
		long end = System.currentTimeMillis();
		System.out.println("===getWebAdInfo===mac:"+mac+"===now:"+time+"=time:"+(end-start) );
		return responseImageAdInfo;
	}
	
	/**
	 *图片广告接口 3号位
	 * @param mac
	 * @return
	 */
	@ApiOperation(value="图片广告接口", httpMethod ="GET", notes ="图片广告接口") 
	@RequestMapping(value = "getImageAdList",produces = "application/json;charset=utf-8")
	public ResponseImageAdInfo getImageAdInfo(
			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac) {
		long start = System.currentTimeMillis();
		ResponseImageAdInfo responseImageAdInfo = new ResponseImageAdInfo();
		try {
			responseImageAdInfo = deviceService.queryImageAdInfoList(mac);
		} catch (Exception e) {
			responseImageAdInfo.setStatus(1);
			responseImageAdInfo.setMessage(e.getMessage());
			logger.error("===getWebAdInfo===mac:"+mac+"=error:"+e.getMessage());
		}
		long end = System.currentTimeMillis();
		System.out.println("===getImageAdInfo===mac:"+mac+"=time:"+(end-start) );
		return responseImageAdInfo;
	}
	
	/**
	 *文字广告接口
	 * @param mac 4号位接口
	 * @return
	 */
	@ApiOperation(value="文字广告接口", httpMethod ="GET", notes ="文字广告接口") 
	@RequestMapping(value = "getTextAdList",produces = "application/json;charset=utf-8")
	public ResponseTextAdInfo getTextAdList(
			   @RequestParam(required = true, defaultValue = "gochinatv")
	           @ApiParam(value = "设备MAC地址", required = true) String mac,
	           @RequestParam(required = false)
	           @ApiParam(value = "时间,格式2016-04-23", required = false) String time) {
		long start = System.currentTimeMillis();
		ResponseTextAdInfo responseImageAdInfo = new ResponseTextAdInfo();
		try {
			responseImageAdInfo.setAdTextInterval(5000);
		    String now = DateUtils.convert(new Date(), DateUtils.DATE_FORMAT);
			if(StringUtils.isNotBlank(time)){
				now = time;
			}
			List<TextAdInfo> data =  deviceService.queryTextAdInfoList(mac,now);
			responseImageAdInfo.setData(data);
		} catch (Exception e) {
			responseImageAdInfo.setStatus(1);
			responseImageAdInfo.setMessage("error");
			logger.error("===getTextAdList===mac:"+mac+"=error:"+e.getMessage());
		}
		long end = System.currentTimeMillis();
		System.out.println("===getTextAdList===mac:"+mac+"=time:"+(end-start) );
		return responseImageAdInfo;
	}
	
}
