package com.gochinatv.accelarator.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.AdvertiserMessage;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.service.AdvertiserMessageService;

/**
 * 
 * @作者 zhuhh
 * @描述   广告内容控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/advertiser_message")
public class AdvertiserMessageController extends BaseController{
    
	@Autowired
	private AdvertiserMessageService advertiserMessageService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "user/user_list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public List<AdvertiserMessage> queryList(Model model) throws Exception{
		List<AdvertiserMessage> list = advertiserMessageService.getList();
		return list;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
}
