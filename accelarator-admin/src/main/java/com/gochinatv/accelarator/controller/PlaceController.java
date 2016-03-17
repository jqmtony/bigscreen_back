package com.gochinatv.accelarator.controller;

import java.util.List;
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
 * @作者 zhuhh
 * @描述 投放区域管理控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/place")
public class PlaceController extends BaseController{
    
	@Autowired
	private PlaceService placeService;
	

	@RequestMapping("/gotoList")
	public String gotoList(Model model) throws Exception{
		return "place/place_list";
	}
	
	
	@RequestMapping("/queryList")
	@ResponseBody
	public PageInfo<Place> queryList(@RequestParam(value = "page", defaultValue = ("1")) int pageNum,
			                @RequestParam(value = "rows", defaultValue = ("20")) int pageSize,
			                Place place) throws Exception{
		PageInterceptor.startPage(pageNum, pageSize);
		List<Place> list = placeService.getListByEntity(place);
		PageInfo<Place> pageInfo = new PageInfo<Place>(list);
		return pageInfo;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(Model model){
		return "";
	}
	
}
