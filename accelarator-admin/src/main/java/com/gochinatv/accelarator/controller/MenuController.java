package com.gochinatv.accelarator.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.dao.entity.Menu;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.MenuService;

/**
 * 菜单demo
 * @author limr
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{
	
	@Autowired
	private MenuService menuService;
	@RequestMapping("/gotoList")
	public String gotoList(){
		return "menu/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Menu> queryList( int page, int rows,
							   Menu menu) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Menu> list = menuService.getListByEntity(menu);
		PageInfo<Menu> pageInfo = new PageInfo<Menu>(list);
		return pageInfo;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Menu menu){
		Map<String,Object> result = this.success(null);
		try{
			menu.setCreateTime(new Date());
//			menu.setStatus(1);
			menuService.save(menu);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	

	@RequestMapping("/gotoUpdate")
	@ResponseBody
	public Menu gotoUpdate(@RequestParam(value = "id") int id) throws Exception{
		Menu menu = menuService.getEntityById(id);
		return menu;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Menu menu){
		Map<String,Object> result = this.success(null);
		try{
			menuService.update(menu);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Menu menu){
		Map<String,Object> result = this.success(null);
		try{
			menuService.deleteByEntity(menu);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
}
