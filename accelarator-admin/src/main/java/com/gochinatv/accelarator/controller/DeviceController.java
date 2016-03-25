package com.gochinatv.accelarator.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.dao.entity.Device;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.DeviceService;
import com.gochinatv.accelarator.service.PlaceService;

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
	
	@Autowired
	private PlaceService placeService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "device/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public Map<String,Object> queryList( int page, int rows,
			   Device device) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		PageInterceptor.startPage(page, rows);
		List<Device> list = deviceService.getListByEntity(device);
		PageInfo<Device> pageInfo = new PageInfo<Device>(list);
		data.put("rows", pageInfo.getList());
		data.put("pageSize", pageInfo.getPageSize());
		data.put("total", pageInfo.getTotal());
		return data;
	}
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Device device){
		Map<String,Object> result = this.success(null);
		try{
			device.setCreateTime(new Date());
//			device.setStatus(1);
			Integer placeId = device.getPlaceId();
			Integer businessId = placeService.getBusinessIdById(placeId);
			device.setBusinessId(businessId);
			deviceService.save(device);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	

	@RequestMapping("/gotoUpdate")
	@ResponseBody
	public Device gotoUpdate(@RequestParam(value = "id") int id) throws Exception{
		Device device = deviceService.getEntityById(id);
		return device;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Device device){
		Map<String,Object> result = this.success(null);
		try{
			deviceService.update(device);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Device device){
		Map<String,Object> result = this.success(null);
		try{
			deviceService.deleteByEntity(device);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
}
