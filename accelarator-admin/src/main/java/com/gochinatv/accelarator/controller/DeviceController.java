package com.gochinatv.accelarator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Device;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.service.DeviceService;

/**
 * 
 * @作者 zhuhh
 * @描述  设备信息控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/device")
public class DeviceController extends BaseController{
    
	@Autowired
	private DeviceService deviceService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "user/user_list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public List<Device> queryList(Model model) throws Exception{
		List<Device> list = deviceService.getList();
		return list;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
}
