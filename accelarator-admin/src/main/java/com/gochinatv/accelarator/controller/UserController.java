package com.gochinatv.accelarator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.User;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.UserService;

/**
 * 
 * @作者 zhuhh
 * @描述  用户控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    
	@Autowired
	private UserService userService;
	

	@RequestMapping("/to_list")
	public String to_list(Model model) throws Exception{
		return "user/user_list";
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String,Object> list(@RequestParam(value = "page", defaultValue = ("1")) int pageNum,
								   @RequestParam(value = "rows", defaultValue = ("20")) int pageSize,
								   Model model) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		PageInterceptor.startPage(pageNum, pageSize);
		List<User> list = userService.getList();
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		data.put("rows", pageInfo.getList());
		data.put("pageSize", pageInfo.getPageSize());
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
}
