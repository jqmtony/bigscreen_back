package com.gochinatv.accelarator.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Resource;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.service.ResourceService;

/**
 * 
 * @作者 zhuhh
 * @描述   资源控制
 * @创建时间 2016年4月21日 上午11:16:08
 * @修改时间
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController{
    
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "resource/list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public List<Resource> queryList() throws Exception{
		int parentId = -1;
		List<Resource> treeList = new ArrayList<Resource>();
		return resourceService.getTreeList(parentId, treeList);
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Resource resource){
		Map<String,Object> result = this.success(null);
		try{
			resourceService.save(resource);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/gotoUpdate")
	@ResponseBody
	public Resource gotoUpdate(int id) throws Exception{
		return resourceService.getEntityById(id);
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Resource resource){
		Map<String,Object> result = this.success(null);
		try{
			resourceService.update(resource);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Resource resource){
		Map<String,Object> result = this.success(null);
		try{
			resourceService.deleteByEntity(resource);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
}
