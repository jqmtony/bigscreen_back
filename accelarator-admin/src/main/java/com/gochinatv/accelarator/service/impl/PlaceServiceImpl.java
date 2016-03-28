package com.gochinatv.accelarator.service.impl;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.PlaceDao;
import com.gochinatv.accelarator.dao.entity.Place;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.PlaceService;

/**
 * 
 * @作者 zhuhh
 * @描述   投放区域业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class PlaceServiceImpl extends BaseServiceImpl<Place> implements PlaceService {

	@Autowired
	private PlaceDao placeDao;
	
	@Override
	protected BaseDao<Place> getDao() {
		return placeDao;
	}

	public int getBusinessIdById(Integer id) {
		return placeDao.getBusinessIdById(id);
	}
	
	
	/**
	 * 查看可用广告位
	 * @param place
	 * @return
	 */
	public List<Place> getAvailableList(Place place){
		String cityCode = place.getCityCode();
		StringBuffer buffer = new StringBuffer("'");
		if(!StringUtils.isEmpty(cityCode)){
		   cityCode = cityCode.replaceAll(",","','");
		   buffer.append(cityCode);
		}
		buffer.append("'");
		place.setCityCode(buffer.toString());
		return placeDao.getAvailableList(place);
	}
	
}
