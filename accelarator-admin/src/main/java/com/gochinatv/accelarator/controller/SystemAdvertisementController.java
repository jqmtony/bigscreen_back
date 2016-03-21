package com.gochinatv.accelarator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.SystemAdvertisement;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.service.SystemAdvertisementService;

/**
 * 
 * @作者 zhuhh
 * @描述  系统广告控制  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/advertiser")
public class SystemAdvertisementController extends BaseController{
    
	@Autowired
	private SystemAdvertisementService systemAdvertisementService;
	
	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "user/user_list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public List<SystemAdvertisement> queryList(Model model) throws Exception{
		List<SystemAdvertisement> list = systemAdvertisementService.getList();
		return list;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
}
