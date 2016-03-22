package com.gochinatv.accelarator.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.dao.entity.Advertiser;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.AdvertiserService;

/**
 * 
 * @作者 limr
 * @描述  广告商账号控制层  
 * @创建时间 2016年3月21日 下午17:56:13
 * @修改时间
 */
@Controller
@RequestMapping("/advertiser")
public class AdvertiserController extends BaseController{
    
	@Autowired
	private AdvertiserService advertiserService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "advertiser/list";
	}
	
	@RequestMapping("/queryList")
	@ResponseBody
	public Map<String,Object> queryList(int page,int rows,Advertiser advertiser) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		PageInterceptor.startPage(page, rows);
		List<Advertiser> list = advertiserService.getListByEntity(advertiser);
		PageInfo<Advertiser> pageInfo = new PageInfo<Advertiser>(list);
		data.put("rows", pageInfo.getList());
		data.put("pageSize", pageInfo.getPageSize());
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Advertiser advertiser){
		Map<String,Object> result = this.success(null);
		try{
			advertiser.setCreateTime(new Date());
			advertiser.setStatus(1);
			advertiserService.save(advertiser);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Advertiser advertiser){
		Map<String,Object> result = this.success(null);
		try{
			advertiserService.update(advertiser);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Advertiser advertiser){
		Map<String,Object> result = this.success(null);
		try{
			advertiserService.deleteByEntity(advertiser);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
}
