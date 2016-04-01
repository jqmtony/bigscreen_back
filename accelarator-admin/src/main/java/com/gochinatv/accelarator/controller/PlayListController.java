package com.gochinatv.accelarator.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.service.PlayListService;


/**
 * 
 * @作者 zhuhh
 * @描述   排播列表控制层  
 * @创建时间 2016年4月1日 下午5:28:25
 * @修改时间
 */
@Controller
@RequestMapping("/play_list")
public class PlayListController extends BaseController{
    
	@Autowired
	private PlayListService playListService;

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		playListService.getList();
		return "orders/list";
	}
	
	
	
}
