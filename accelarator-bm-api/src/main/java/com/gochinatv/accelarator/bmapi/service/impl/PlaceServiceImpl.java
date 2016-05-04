package com.gochinatv.accelarator.bmapi.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.bmapi.bean.Device;
import com.gochinatv.accelarator.bmapi.bean.Place;
import com.gochinatv.accelarator.bmapi.bean.PlaceDevice;
import com.gochinatv.accelarator.bmapi.dao.AreaDao;
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
	
	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<Object> getListByBusinessId(int businessId) throws Exception {
		List<Place> list = placeDao.getListByBusinessId(businessId);
		List<Object> resultList = new ArrayList<Object>();
		for(Place p : list){
			p.setCountryName(areaDao.getNameByCode(p.getCountryCode()));
			p.setAreaName(areaDao.getNameByCode(p.getAreaCode()));
			p.setCityName(areaDao.getNameByCode(p.getCityCode()));
			
			List<Device> dList = deviceDao.getListByPlaceId(p.getId());
			PlaceDevice pd = new PlaceDevice();
			pd.setPlace(p);
			pd.setDeviceList(dList);
			
			resultList.add(pd);
		}
			
		return resultList;
		
	}
	
}
