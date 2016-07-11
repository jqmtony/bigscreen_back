package com.gochinatv.accelarator.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.gochinatv.accelarator.dao.entity.DacDeviceVideo;
import com.gochinatv.accelarator.dao.entity.DeviceBootLog;
import com.gochinatv.accelarator.dao.entity.DeviceImage;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.framework.web.base.utils.DateUtils;
import com.gochinatv.accelarator.service.DacDeviceVideoService;
import com.gochinatv.accelarator.service.DeviceBootLogService;
import com.gochinatv.accelarator.service.DeviceImageService;
import com.gochinatv.accelarator.util.ExcelUtils;
import com.gochinatv.accelarator.util.GlobalUtils;
import com.gochinatv.accelarator.util.PO2ArrayUtils;


/**
 * @作者 limr
 * @描述  设备活跃度监控  
 */
@Controller
@RequestMapping("/deviceBootLog")
public class DeviceBootLogController extends BaseController{
    
	@Autowired
	private DeviceBootLogService deviceBootLogService;
	
	@Autowired
	private DeviceImageService deviceImageService;
	
	@Autowired
	private DacDeviceVideoService dacDeviceVideoService;
	
	
	
	/*****************************************************开机时间统计********************************************************/
	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
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
			/*objects[6]=areaService.getNameByCode(innerDeviceBootLog.getCountryCode());
			objects[7]=areaService.getNameByCode(innerDeviceBootLog.getAreaCode());
			objects[8]=areaService.getNameByCode(innerDeviceBootLog.getCityCode());*/
			objects[6]=GlobalUtils.AREA_CODE_NAME.get(innerDeviceBootLog.getCountryCode());
			objects[7]=GlobalUtils.AREA_CODE_NAME.get(innerDeviceBootLog.getAreaCode());
			objects[8]=GlobalUtils.AREA_CODE_NAME.get(innerDeviceBootLog.getCityCode());
			
			objects[9]=DateUtils.formatDateString(innerDeviceBootLog.getCreateTime());
			data.add(objects);
		}
		String[] columns = {"编号","设备编码","设备mac","版本号","版本名","开机时间","商铺编码","国家","地区","城市","创建日期"};
		ExcelUtils.getExcel(data, columns, response,"设备开机监控");
   } 
	
	
	/*****************************************************开机时长统计********************************************************/
	//统计
	@RequestMapping("/gotoStatList")
	public String gotoStatList(Model model) throws Exception {
		return "deviceBootLog/statList";
	}

	@RequestMapping("/queryStatList")
	@ResponseBody
	public Map<String, Object> queryStatList(int page, int rows, DeviceImage deviceImage) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		PageInterceptor.startPage(page, rows);
		List<DeviceImage> list = deviceImageService.getListByStatEntity(deviceImage);
		PageInfo<DeviceImage> pageInfo = new PageInfo<DeviceImage>(list);
		data.put("rows", pageInfo.getList());
		data.put("pageSize", pageInfo.getPageSize());
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	/**
	 * 开机时长统计图片
	 * @param deviceImage
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryStatPic")
	@ResponseBody
	public Map<String, Object> queryStatPic(DeviceImage deviceImage) throws Exception {
		/*SELECT d.code AS deviceName,COUNT(*)*15 AS duration,DATE_FORMAT(di.create_time,'%Y-%m-%d') FROM device_image di,
		device d, place p WHERE 1=1 AND di.mac=d.mac AND d.place_id=p.id 
		 AND d.mac IN (
		    SELECT mac FROM (SELECT mac FROM device_image GROUP BY mac ORDER BY COUNT(1) DESC LIMIT 0,10) AS t
		 ) 
		GROUP BY d.code,DATE_FORMAT(di.create_time,'%Y-%m-%d')*/
		return null;
	}
	
	
	@RequestMapping(value = "/exportStat")
	public void exportStat(DeviceImage deviceImage,HttpServletResponse response) throws Exception {
		List<DeviceImage> list = deviceImageService.getListByStatEntity(deviceImage);
		
		for (DeviceImage di : list) {
			di.setCountryCode(GlobalUtils.AREA_CODE_NAME.get(di.getCountryCode()));
			di.setCityCode(GlobalUtils.AREA_CODE_NAME.get(di.getCityCode()));
			di.setAreaCode(GlobalUtils.AREA_CODE_NAME.get(di.getAreaCode()));
		}
		
		List<Object[]> data = 
				PO2ArrayUtils.po2Array(list, new String[]{"deviceName","mac","duration","placeName","countryCode","areaCode","cityCode"});
		
		String[] columns = { "编号", "设备编码", "设备mac", "开机时长（分）", "商铺编号", "国家", "地区", "城市"};
		ExcelUtils.getExcel(data, columns, response,"开机时长统计");
	}
	
	/********************************************************视频播放次数统计*****************************************************/
	// 统计
	@RequestMapping("/gotoPlayCountList")
	public String gotoPlayCountList(Model model) throws Exception {
		model.addAttribute("startTime", DateUtils.addDay(-6));
		model.addAttribute("endTime", DateUtils.formatDateStringWithOutHMS(new Date()));
		return "deviceBootLog/playCount";
	}

	
	@RequestMapping("/queryPlayCountList")
	@ResponseBody
	public PageInfo<DacDeviceVideo> queryPlayCountList(int page, int rows, DacDeviceVideo dacDeviceVideo) throws Exception {
		dacDeviceVideo.setType(DacDeviceVideo.BFCS);//设置播放次数
		PageInterceptor.startPage(page, rows);
		List<DacDeviceVideo> list = dacDeviceVideoService.getListByEntity(dacDeviceVideo);
		PageInfo<DacDeviceVideo> pageInfo = new PageInfo<DacDeviceVideo>(list);
		return pageInfo;
	}
	
	/**
	 * 播放次数统计导出excel
	 * @param dacDeviceVideo
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportPlayCount")
	public void exportPlayCount(DacDeviceVideo dacDeviceVideo,HttpServletResponse response) throws Exception {
		dacDeviceVideo.setType(DacDeviceVideo.BFCS);//设置播放次数
		List<DacDeviceVideo> list = dacDeviceVideoService.getListByEntity(dacDeviceVideo);
		
		List<Object[]> data = 
				PO2ArrayUtils.po2Array(list, new String[]{"videoId","videoName","userName","total"});
		
		String[] columns = { "编号", "视频ID", "视频名称", "广告主", "播放次数"};
		ExcelUtils.getExcel(data, columns, response,"播放次数统计");
	}
	
	
	@RequestMapping("/queryPlayCountDetail")
	@ResponseBody
	public Map<String,List<DacDeviceVideo>> queryPlayCountDetail(DacDeviceVideo dacDeviceVideo) throws Exception {
		Map<String, List<DacDeviceVideo>> dataMap = new HashMap<String, List<DacDeviceVideo>>();
		dacDeviceVideo.setType(DacDeviceVideo.BFCS);//设置播放次数
		dataMap.put("rows", dacDeviceVideoService.getPlayCountDetail(dacDeviceVideo));
		return dataMap;
	}
	
}
