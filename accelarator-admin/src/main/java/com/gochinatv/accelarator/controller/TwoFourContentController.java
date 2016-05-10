package com.gochinatv.accelarator.controller;


import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gochinatv.accelarator.dao.entity.TwoFourContent;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.TwoFourContentService;

/**
 * 
 * @作者 administrator
 * @描述   2-4号位内容控制层
 * @创建时间  2016-5-9
 * @修改时间
 */
@Controller
@RequestMapping("/two_four_content")
public class TwoFourContentController extends BaseController{
    
	@Autowired
	private TwoFourContentService twoFourContentService;
	
	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "two_four_content/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<TwoFourContent> queryList(int page,int rows,TwoFourContent twoFourContent) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<TwoFourContent> list = twoFourContentService.getListByEntity(twoFourContent);
		PageInfo<TwoFourContent> pageInfo = new PageInfo<TwoFourContent>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(TwoFourContent twoFourContent){
		Map<String,Object> result = this.success(null);
		try{
			twoFourContentService.save(twoFourContent);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(TwoFourContent twoFourContent){
		Map<String,Object> result = this.success(null);
		try{
			twoFourContentService.update(twoFourContent);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(TwoFourContent twoFourContent){
		Map<String,Object> result = this.success(null);
		try{
			twoFourContentService.deleteByEntity(twoFourContent);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
}