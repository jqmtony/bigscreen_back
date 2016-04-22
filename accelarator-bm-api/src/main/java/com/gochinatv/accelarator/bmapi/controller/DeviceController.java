package com.gochinatv.accelarator.bmapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.bmapi.bean.BusinessAd;
import com.gochinatv.accelarator.bmapi.bean.Device;
import com.gochinatv.accelarator.bmapi.interceptor.CheckLoginInterceptorAnnotation;
import com.gochinatv.accelarator.bmapi.service.DeviceService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 
 * @描述  设备信息控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController{
    
	@Autowired
	private DeviceService deviceService;
	
	/**
	 * 根据设备id得到设备详情
	 * @param deviceId
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据设备id得到设备详情", httpMethod = "GET", notes = "根据设备id得到设备详情")
	@CheckLoginInterceptorAnnotation
	@RequestMapping("/getDeviceDetal")
	@ResponseBody
	public Map<String, Object> getDeviceDetal(int deviceId){
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			Device device = deviceService.getEntityById(deviceId);
			data = this.success(device);
		} catch (Exception e) {
			data = this.error(e.getMessage());
		}
		
		return data;
	}
	
	@ApiOperation(value = "更新商家设备3号位时间", httpMethod = "GET", notes = "更新商家设备3号位时间")
	@RequestMapping(value = "/update", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String,Object> update( @ApiParam(value = "设备ID", required = true)int deviceId,
			 @ApiParam(value = "轮询时间", required = true)int refreshTime){
		Map<String,Object> result = this.success(null);
		try{
			deviceService.update(deviceId,refreshTime);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
}
