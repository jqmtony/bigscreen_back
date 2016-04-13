package com.gochinatv.accelarator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.DeviceImage;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.DeviceImageService;

@Controller
@RequestMapping("/deviceImage")
public class DeviceImageController extends BaseController{
    
	@Autowired
	private DeviceImageService deviceImageService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "deviceImage/list";
	}

	@RequestMapping("/queryList")
	@ResponseBody
	public Map<String,Object> queryList( int page, int rows,
			   DeviceImage deviceImage) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		PageInterceptor.startPage(page, rows);
		List<DeviceImage> list = deviceImageService.getListByEntity(deviceImage);
		PageInfo<DeviceImage> pageInfo = new PageInfo<DeviceImage>(list);
		data.put("rows", pageInfo.getList());
		data.put("pageSize", pageInfo.getPageSize());
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	@RequestMapping(value = "/delete/{areaCode}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("id")int id){
		Map<String,Object> result = this.success(null);
		try{
			deviceImageService.deleteById(id);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
}
