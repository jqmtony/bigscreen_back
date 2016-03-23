package com.gochinatv.accelarator.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.dao.entity.SystemAdvertisement;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.SystemAdvertisementService;

/**
 * 
 * @作者 zhuhh
 * @描述  系统广告控制  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/system_advertisement")
public class SystemAdvertisementController extends BaseController{
    
	@Autowired
	private SystemAdvertisementService systemAdvertisementService;
	
	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "system_advertisement/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<SystemAdvertisement> queryList(int page,int rows,SystemAdvertisement sa) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<SystemAdvertisement> list = systemAdvertisementService.getListByEntity(sa);
		PageInfo<SystemAdvertisement> pageInfo = new PageInfo<SystemAdvertisement>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(SystemAdvertisement systemAdvertisement){
		Map<String,Object> result = this.success(null);
		try{
			systemAdvertisement.setCreateTime(new Date());
//			systemAdvertisement.setStatus(1);
			systemAdvertisementService.save(systemAdvertisement);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
//
//	@RequestMapping("/gotoUpdate")
//	@ResponseBody
//	public SystemAdvertisement gotoUpdate(@RequestParam(value = "id") int id) throws Exception{
//		SystemAdvertisement systemAdvertisement = systemAdvertisementService.getEntityById(id);
//		return systemAdvertisement;
//	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(SystemAdvertisement systemAdvertisement){
		Map<String,Object> result = this.success(null);
		try{
			systemAdvertisementService.update(systemAdvertisement);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(SystemAdvertisement systemAdvertisement){
		Map<String,Object> result = this.success(null);
		try{
			systemAdvertisementService.deleteByEntity(systemAdvertisement);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
}
