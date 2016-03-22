package com.gochinatv.accelarator.controller;

import java.util.List;
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
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
}
