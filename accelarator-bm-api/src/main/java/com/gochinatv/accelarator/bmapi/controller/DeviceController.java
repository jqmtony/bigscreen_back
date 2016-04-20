package com.gochinatv.accelarator.bmapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.bmapi.bean.Device;
import com.gochinatv.accelarator.bmapi.service.DeviceService;
import com.wordnik.swagger.annotations.ApiOperation;

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
}
