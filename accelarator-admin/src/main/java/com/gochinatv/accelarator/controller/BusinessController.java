package com.gochinatv.accelarator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Business;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
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
	

	@RequestMapping("/to_list")
	public String to_list(Model model) throws Exception{
		return "user/user_list";
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public List<Business> list(Model model) throws Exception{
		List<Business> list = businessService.getList();
		return list;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
}
