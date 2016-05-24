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
	
	/**
	 * 检验商铺编码的唯一性
	 * @author limr
	 * @param id
	 * @param cname
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkCname")
	@ResponseBody
	public boolean checkCname(int id, String cname) throws Exception{
		boolean data = false;
		Place place = placeService.getPlaceByCname(id, cname);
		if(place == null){
			data = true;
		}
		return data;
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
			boolean checkCname = checkCname(place.getId(),place.getCname());
			if(!checkCname){
				result = this.error("exsit");
			}else{
				place.setCreateTime(new Date());
				placeService.save(place);
			}
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
			boolean checkCname = checkCname(place.getId(),place.getCname());
			if(!checkCname){
				result = this.error("exsit");
			}else{
				placeService.update(place);
			}
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
	
}
