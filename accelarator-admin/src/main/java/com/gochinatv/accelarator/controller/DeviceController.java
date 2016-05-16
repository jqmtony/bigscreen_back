package com.gochinatv.accelarator.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.dao.entity.Device;
import com.gochinatv.accelarator.dao.entity.Place;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.framework.web.base.utils.DateUtils;
import com.gochinatv.accelarator.service.AreaService;
import com.gochinatv.accelarator.service.DeviceService;
import com.gochinatv.accelarator.service.PlaceService;
import com.gochinatv.accelarator.util.ExcelUtils;

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
	
	@Autowired
	private AreaService areaService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "device/list";
	}
	
	/**
	 * 
	 * @param parentMethod
	 * @return
	 */
	@RequestMapping(value = "/gotoDeviceLookUp")
    public String gotoDeviceLookUp(Model model,
    							   @RequestParam(value = "parentMethod") String parentMethod,
    							   @RequestParam(value = "placeId", required = false, defaultValue = "0") int placeId){
		model.addAttribute("parentMethod", parentMethod);
		model.addAttribute("placeId", placeId);
		return "device/lookUpForSysAdvertisement";
    }
	
	/**
	 * 检验用编码的唯一性
	 * @author limr
	 * @param id
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkCode")
	@ResponseBody
	public boolean checkCode(int id, String code) throws Exception{
		boolean data = false;
		Device device = deviceService.getDeviceByCode(id, code);
		if(device == null){
			data = true;
		}
		return data;
	}
	/**
	 * 检验mac的唯一性
	 * @author limr
	 * @param id
	 * @param mac
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkMac")
	@ResponseBody
	public boolean checkMac(int id, String mac) throws Exception{
		boolean data = false;
		Device device = deviceService.getDeviceByMac(id, mac);
		if(device == null){
			data = true;
		}
		return data;
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
			boolean checkCode = checkCode(device.getId(), device.getCode());
			boolean checkMac = checkMac(device.getId(), device.getMac());
			if(!checkCode){
				result = this.error("code");
			}else if(!checkMac){
				result = this.error("mac");
			}else{
				device.setCreateTime(new Date());
//				device.setStatus(1);
				Integer placeId = device.getPlaceId();
				Integer businessId = placeService.getBusinessIdById(placeId);
				device.setBusinessId(businessId);
				
				Place place = placeService.getEntityById(placeId);
				device.setCityCode(place.getCityCode());
				deviceService.save(device);
			}
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
			boolean checkCode = checkCode(device.getId(), device.getCode());
			boolean checkMac = checkMac(device.getId(), device.getMac());
			if(!checkCode){
				result = this.error("code");
			}else if(!checkMac){
				result = this.error("mac");
			}else{
				Integer placeId = device.getPlaceId();
				Integer businessId = placeService.getBusinessIdById(placeId);
				device.setBusinessId(businessId);
				deviceService.update(device);
			}
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
	
	
	@RequestMapping(value = "/excel/export")    
    public void exportExcel(HttpServletRequest request, HttpServletResponse response)     
    throws Exception {
		List<Device> list = deviceService.getList();
		List<Object[]> data = new ArrayList<Object[]>();
		for (Device device : list) {
			String bootTime = "";
			if(device.getBootTime() != null){
				bootTime = DateUtils.formatDateString(device.getBootTime());
			}
			int status = device.getStatus();
			String statusString = "";
			if(status == 1){
				statusString = "已启用";
			}else{
				statusString = "已禁用";
			}
			Object[] objects = new Object[16];
			objects[0]=device.getCode();
			objects[1]=device.getBrand();
			objects[2]=device.getModel();
			objects[3]=device.getMac();
			objects[4]=device.getVersionNum();
			objects[5]=device.getVersionName();
			objects[6]=bootTime;
			objects[7]=device.getUserName();
			objects[8]=device.getCname();
			objects[9]=areaService.getNameByCode(device.getCountryCode());
			objects[10]=areaService.getNameByCode(device.getAreaCode());
			objects[11]=areaService.getNameByCode(device.getCityCode());
			objects[12]=device.getScreenNum();
			objects[13]=device.getScreenShotInterval();
			objects[14]=statusString;
			objects[15]=DateUtils.formatDateString(device.getCreateTime());
			data.add(objects);
		}
		String[] columns = {"编号","设备编码","设备品牌","设备型号","设备mac","版本号","版本名","开机时间","商家负责人","商铺名称"
				,"国家","地区","城市","分屏数量","截屏时间","状态","创建日期"};
		ExcelUtils.getExcel(data, columns, response,"设备信息导出");
   }    
}
