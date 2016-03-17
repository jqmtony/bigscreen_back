package com.gochinatv.accelarator.service.impl;


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
	
	
}
