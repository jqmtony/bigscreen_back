package com.gochinatv.accelarator.bmapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.bmapi.bean.Device;
import com.gochinatv.accelarator.bmapi.bean.Place;
import com.gochinatv.accelarator.bmapi.service.DeviceService;
import com.gochinatv.accelarator.bmapi.service.PlaceService;

/**
 * 
 * @作者 limr
 * @描述 投放区域管理控制层  
 * @创建时间 2016年3月22日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/place")
public class PlaceController extends BaseController{
    
	@Autowired
	private PlaceService placeService;
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping("/getByBusinessId")
	@ResponseBody
	public Map<String,Object> getByBusinessId(int businessId){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			List<Object> resultList = placeService.getListByBusinessId(businessId);
			result = this.success(resultList);
		} catch (Exception e) {
			result = this.error(e.getMessage());
		}
		return result;
	}

}
