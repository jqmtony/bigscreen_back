package com.gochinatv.accelarator.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Business;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.BusinessService;

/**
 * 
 * @作者 zhuhh
 * @描述    商家账号控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/business")
public class BusinessController extends BaseController{
    
	@Autowired
	private BusinessService businessService;
	

	@RequestMapping("/gotoList")
	public String gotoList(){
		return "business/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Business> queryList( int page, int rows,
							   Business business) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Business> list = businessService.getListByEntity(business);
		PageInfo<Business> pageInfo = new PageInfo<Business>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Business business){
		Map<String,Object> result = this.success(null);
		try{
			business.setCreateTime(new Date());
			business.setStatus(1);
			businessService.save(business);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	

	@RequestMapping("/gotoUpdate")
	@ResponseBody
	public Business gotoUpdate(@RequestParam(value = "id") int id) throws Exception{
		Business business = businessService.getEntityById(id);
		return business;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Business business){
		Map<String,Object> result = this.success(null);
		try{
			businessService.update(business);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Business business){
		Map<String,Object> result = this.success(null);
		try{
			businessService.deleteByEntity(business);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
}
