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
import com.gochinatv.accelarator.dao.entity.Place;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInfo;
import com.gochinatv.accelarator.framework.web.base.pagination.PageInterceptor;
import com.gochinatv.accelarator.service.PlaceService;

/**
 * 
 * @作者 limr
 * @描述 投放区域管理控制层  
 * @创建时间 2016年3月22日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/place")
public class PlaceController extends BaseController{
    
	@Autowired
	private PlaceService placeService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "place/list";
	}
	
	@RequestMapping(value = "/gotoPlaceLookUp")
    public String gotoPlaceLookUp(Model model,@RequestParam(value = "parentMethod") String parentMethod){
		model.addAttribute("parentMethod", parentMethod);
		return "place/lookUpForDevice";
    }
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Place> queryList(int page,int rows,Place place) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Place> list = placeService.getListByEntity(place);
		PageInfo<Place> pageInfo = new PageInfo<Place>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/save")
	@ResponseBody
	public Map<String,Object> save(Place place){
		Map<String,Object> result = this.success(null);
		try{
			place.setCreateTime(new Date());
			placeService.save(place);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	

	@RequestMapping("/gotoUpdate")
	@ResponseBody
	public Place gotoUpdate(@RequestParam(value = "id") int id) throws Exception{
		Place place = placeService.getEntityById(id);
		return place;
	}
	
	
	@RequestMapping("/update")
	@ResponseBody
	public Map<String,Object> update(Place place){
		Map<String,Object> result = this.success(null);
		try{
			placeService.update(place);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String,Object> delete(Place place){
		Map<String,Object> result = this.success(null);
		try{
			placeService.deleteByEntity(place);
		}catch(Exception e){
			result = this.error(e.getMessage());
		}
		return result;
	}
	
	
	/******************************************************************************************/
	/**
	 * 到可用广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/gotoAvailableList")
	public String gotoAvailableList(Model model) throws Exception{
		return "place/available_list";
	}
	
	/**
	 * 查询可用广告列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAvailableList")
	@ResponseBody
	public PageInfo<Place> queryAvailableList(int page,int rows,Place place) throws Exception{
		PageInterceptor.startPage(page, rows);
		List<Place> list = placeService.getAvailableList(place);
		PageInfo<Place> pageInfo = new PageInfo<Place>(list);
		return pageInfo;
	}
	/*********************************************************************************************/
	
}
