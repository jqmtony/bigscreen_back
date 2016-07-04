package com.gochinatv.accelarator.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Device;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.service.DeviceService;


/**
 * 
 * @作者 zhuhh
 * @描述    无需登录即可处理的业务员类
 * @创建时间 2016年7月1日 上午10:19:41
 * @修改时间
 */
@Controller
@RequestMapping("/nologin")
public class NoLoginHandlerController extends BaseController{
	
	@Autowired
	private DeviceService deviceService;
	
	
	@RequestMapping("/deviceInfo")
	public String deviceInfo(Model model) throws Exception{
		return "device/deviceInfo";
	}
	
	@RequestMapping("/doDeviceInfo")
	@ResponseBody
	public Map<String, Object> doDeviceInfo(@RequestParam(value = "code") String code) throws Exception{
		Device device = deviceService.getDeviceByCode(0, code);
		Map<String, Object> data = this.success(device);
		if(null==device){
			data.put("code", "400");
		}
		return data;
	}
}
