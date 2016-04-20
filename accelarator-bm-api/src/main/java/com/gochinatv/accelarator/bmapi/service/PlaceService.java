package com.gochinatv.accelarator.bmapi.service;

import java.util.List;


/**
 * 
 * @描述  投放区域业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface PlaceService{

	/**
	 * 商铺列表
	 * @param businessId
	 * @return
	 */
	List<Object> getListByBusinessId(int businessId) throws Exception;

}
