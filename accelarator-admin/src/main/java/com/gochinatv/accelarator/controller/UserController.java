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
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "user/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public Map<String,Object> queryList(int page,int rows,User user) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		PageInterceptor.startPage(page, rows);
		List<User> list = userService.getListByEntity(user);
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		data.put("rows", pageInfo.getList());
		data.put("pageSize", pageInfo.getPageSize());
		data.put("total", pageInfo.getTotal());
		return data;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(User user){
		Map<String,Object> result = this.success(null);
		try{
			user.setCreateTime(new Date());
			user.setStatus(1);
			userService.save(user);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(User user){
		Map<String,Object> result = this.success(null);
		try{
			userService.update(user);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(User user){
		Map<String,Object> result = this.success(null);
		try{
			userService.deleteByEntity(user);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
}
