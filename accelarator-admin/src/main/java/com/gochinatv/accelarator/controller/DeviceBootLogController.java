package com.gochinatv.accelarator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.DeviceBootLog;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.framework.web.base.utils.DateUtils;
import com.gochinatv.accelarator.service.AreaService;
import com.gochinatv.accelarator.service.DeviceBootLogService;
import com.gochinatv.accelarator.util.ExcelUtils;

/**
 * 
 * @作者 limr
 * @描述  设备活跃度监控  
 */
@Controller
@RequestMapping("/deviceBootLog")
public class DeviceBootLogController extends BaseController{
    
	@Autowired
	private DeviceBootLogService deviceBootLogService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/gotoList")
	public String gotoControlList(Model model) throws Exception{
		return "deviceBootLog/list";
	}
	@RequestMapping("/queryList")
	@ResponseBody
	public Map<String,Object> queryList( int page, int rows, DeviceBootLog deviceBootLog) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		PageInterceptor.startPage(page, rows);
		List<DeviceBootLog> list = deviceBootLogService.getListByEntity(deviceBootLog);
		PageInfo<DeviceBootLog> pageInfo = new PageInfo<DeviceBootLog>(list);
		data.put("rows", pageInfo.getList());
		data.put("pageSize", pageInfo.getPageSize());
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	
	@RequestMapping(value = "/excel/export")    
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, DeviceBootLog deviceBootLog)     
    throws Exception {
		List<DeviceBootLog> list = deviceBootLogService.getListByEntity(deviceBootLog);
		List<Object[]> data = new ArrayList<Object[]>();
		for (DeviceBootLog innerDeviceBootLog : list) {
			String bootTime = "";
			if(innerDeviceBootLog.getBootTime() != null){
				bootTime = DateUtils.formatDateString(innerDeviceBootLog.getBootTime());
			}
			Object[] objects = new Object[10];
			objects[0]=innerDeviceBootLog.getCode();
			objects[1]=innerDeviceBootLog.getMac();
			objects[2]=innerDeviceBootLog.getVersionNum();
			objects[3]=innerDeviceBootLog.getVersionName();
			objects[4]=bootTime;
			objects[5]=innerDeviceBootLog.getCname();
			objects[6]=areaService.getNameByCode(innerDeviceBootLog.getCountryCode());
			objects[7]=areaService.getNameByCode(innerDeviceBootLog.getAreaCode());
			objects[8]=areaService.getNameByCode(innerDeviceBootLog.getCityCode());
			objects[9]=DateUtils.formatDateString(innerDeviceBootLog.getCreateTime());
			data.add(objects);
		}
		String[] columns = {"编号","设备编码","设备mac","版本号","版本名","开机时间","商铺编码"
				,"国家","地区","城市","创建日期"};
		ExcelUtils.getExcel(data, columns, response,"设备开机监控");
   }  
}
