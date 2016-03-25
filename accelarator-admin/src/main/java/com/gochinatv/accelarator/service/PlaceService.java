package com.gochinatv.accelarator.service;

import com.gochinatv.accelarator.dao.entity.Place;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;


/**
 * 
 * @作者 zhuhh
 * @描述  投放区域业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface PlaceService extends BaseService<Place>{

	int getBusinessIdById(Integer placeId);
	   

}
