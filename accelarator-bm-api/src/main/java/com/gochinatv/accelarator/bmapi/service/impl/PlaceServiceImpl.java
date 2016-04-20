package com.gochinatv.accelarator.bmapi.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.bmapi.bean.Device;
import com.gochinatv.accelarator.bmapi.bean.Place;
import com.gochinatv.accelarator.bmapi.dao.DeviceDao;
import com.gochinatv.accelarator.bmapi.dao.PlaceDao;
import com.gochinatv.accelarator.bmapi.service.PlaceService;

/**
 * 
 * @描述   投放区域业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private DeviceDao deviceDao;
	
	@Override
	public List<Object> getListByBusinessId(int businessId) throws Exception {
		List<Place> list = placeDao.getListByBusinessId(businessId);
		List<Object> resultList = new ArrayList<Object>();
		for(Place p : list){
			Map<String,Object> placeResult = new HashMap<String,Object>();
			List<Device> dList = deviceDao.getListByPlaceId(p.getId());
			placeResult.put("deviceList", dList);
			placeResult.put("id", p.getId());
			placeResult.put("message", p.getMessage());
			placeResult.put("currentTime", p.getCurrentTime());
			placeResult.put("cname", p.getCname());
			placeResult.put("ename", p.getEname());
			placeResult.put("type", p.getType());
			placeResult.put("scale", p.getScale());
			placeResult.put("averageDailyFlow", p.getAverageDailyFlow());
			placeResult.put("countryCode", p.getCountryCode());
			placeResult.put("areaCode", p.getAreaCode());
			placeResult.put("cityCode", p.getCityCode());
			placeResult.put("businessId", p.getBusinessId());
			placeResult.put("createTime", p.getCreateTime());
			
			resultList.add(placeResult);
		}
			
		return resultList;
		
	}
	
}
