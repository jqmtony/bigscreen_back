package com.gochinatv.accelarator.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Advertisement;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.AdvertisementService;


/**
 * 
 * @作者 zhuhh
 * @描述  广告控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/advertisement")
public class AdvertisementController extends BaseController{
    
	@Autowired
	private AdvertisementService advertisementService;
	
	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "advertisement/list";
	}
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Advertisement> queryList(int page,int rows,Advertisement advertisement) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Advertisement> list = advertisementService.getListByEntity(advertisement);
		PageInfo<Advertisement> pageInfo = new PageInfo<Advertisement>(list);
		return pageInfo;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Advertisement advertisement){
		Map<String,Object> result = this.success(null);
		try{
//			advertisement.setStatus(1);
			advertisementService.save(advertisement);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Advertisement advertisement){
		Map<String,Object> result = this.success(null);
		try{
			advertisementService.update(advertisement);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Advertisement advertisement){
		Map<String,Object> result = this.success(null);
		try{
			advertisementService.deleteByEntity(advertisement);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}

}
