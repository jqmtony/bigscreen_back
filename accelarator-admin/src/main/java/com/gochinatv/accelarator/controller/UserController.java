package com.gochinatv.accelarator.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.gochinatv.accelarator.util.Md5Util;

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
	


	@RequestMapping("/gotoUpgradeIndex")
	public String gotoUpgradeIndex(Model model) throws Exception{
		return "upgrade/index";
	}
	
	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "user/list";
	}
	
	/**
	 * 检验用户名的唯一性
	 * @author limr
	 * @param id
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkUserName")
	@ResponseBody
	public String checkUserName(int id, String userName) throws Exception{
		String data = "false";
		User user = userService.getUserByUserName(id, userName);
		if(user == null){
			data = "true";
		}
		return data;
	}
	/**
	 * 检验输入的旧密码是否正确
	 * @param id
	 * @param oldPwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkOldPwd")
	@ResponseBody
	public boolean checkOldPwd(int id, String oldPwd) throws Exception{
		boolean data = false;
		User user = userService.getEntityById(id);
		String pwd = user.getPassword();
		if(pwd.equals(Md5Util.md5(oldPwd))){
			data = true;
		}
		return data;
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
			User u = userService.getUserByUserName(user.getId(), user.getUserName());
			if(u == null || "".equals(u)){
				user.setCreateTime(new Date());
				user.setPassword(Md5Util.md5(user.getPassword()));
//				user.setStatus(1);
				userService.save(user);
			}else{
				result = this.error("用户名已存在！");
			}
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
			User u = userService.getUserByUserName(user.getId(), user.getUserName());
			if(u == null || "".equals(u)){
				String pwd = user.getPassword();
				if(StringUtils.isNotBlank(pwd)){
					user.setPassword(Md5Util.md5(user.getPassword()));
				}
				userService.update(user);
			}else{
				result = this.error("用户名已存在！");
			}
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
