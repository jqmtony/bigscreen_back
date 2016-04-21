package com.gochinatv.accelarator.bmapi.dao;

import java.util.List;

import com.gochinatv.accelarator.bmapi.bean.Place;


/**
 * 
 * @描述   投放区域数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface PlaceDao{

	int getBusinessIdById(Integer id);

	/**
	 * 商铺列表
	 * @param businessId
	 * @return
	 */
	List<Place> getListByBusinessId(int businessId);

}
