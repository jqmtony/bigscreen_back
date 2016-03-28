package com.gochinatv.accelarator.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gochinatv.accelarator.dao.entity.Area;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.AreaService;


@Controller
@RequestMapping("/area")
public class AreaController extends BaseController{
	
	@Autowired
	private AreaService areaService;
	//test git
	
	//zhuhh
	@RequestMapping(value = "/gotoList")
	public String gotoList(){
		return "/area/list";
	}
	
	/**
	* @author 冯志文
	* @Description:异步树  easyui 1.2 treegrid
	* id 为jsp页面中idField:'*'的*, 选择的地域areaCode的值
	* @return List<Map<String,Object>>   
	 */
	//zhuhh
	@RequestMapping(value = "/querySynAreaList")
	@ResponseBody
	public List<Map<String,Object>> querySynAreaList(String id) throws Exception  {
			return areaService.queryChildrenAreaList(id);
	}
	/**
	* @author 冯志文
	* @Description:静态树
	* @return Map<String,Object>    
	* @date 2010-7-23 上午09:59:41
	 */
	@RequestMapping(value = "/areaStaticTree")
	@ResponseBody
	public List<Map<String,Object>> menuStaticTree() throws Exception  {
		return areaService.queryAllArea();
	}
	/**
	* @Description: 跳转到地区页面
	 */
	@RequestMapping(value = "/gotoAreaLookUp")
	public String gotoAreaLookUp(Model model,String callBackMethod,String status) throws Exception{
		model.addAttribute("callBackMethod", callBackMethod);
		model.addAttribute("status", status);
		return "/area/areaLookUp";
	}
	/**
	* @Description: 跳转到地区页面
	 */
	@RequestMapping(value = "/gotoChildAreaLookUp")
	public String gotoChildAreaLookUp(Model model,String callBackMethod,String pid) throws Exception{
		model.addAttribute("callBackMethod", callBackMethod);
		model.addAttribute("pid", pid);
		return "/area/childAreaLookUp";
	}
	@RequestMapping(value = "/queryList")
	public Map<String,Object> queryList(int page,int rows,Area area,HttpServletRequest request)throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Area> areaList = areaService.getListByEntity(area);
		PageInfo<Area> pageInfo = new PageInfo<Area>(areaList);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("total",pageInfo.getTotal());
		modelMap.put("rows", pageInfo.getList());
		return modelMap;
	}
	
	@RequestMapping(value = "/queryListByPid")
	public Map<String,Object> queryListByPid(int pid)throws Exception{
		List<Area> list = areaService.queryListByPid(pid);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		modelMap.put("total",list.size());
		modelMap.put("rows", list);
		//genn
		return modelMap;
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String,Object> save(Area area){
		Map<String,Object> result = this.success(null);
		try{
			areaService.save(area);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public Map<String,Object> update(Area area){
		Map<String,Object> result = this.success(null);
		try{
			areaService.update(area);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/delete/{areaCode}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("areaCode")String areaCode){
		Map<String,Object> result = this.success(null);
		try{
			areaService.delete(areaCode);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
}
