package com.gochinatv.accelarator.service;

import java.util.List;
import com.gochinatv.accelarator.dao.entity.Advertisement;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;

/**
 * 
 * @作者 zhuhh
 * @描述     广告内容业务层接口
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface AdvertisementService  extends BaseService<Advertisement>{
   
	
	/**
	 * 自有广告查询，最新的10个广告
	 * @return
	 */
	public List<Advertisement> getOwnAdvertisement();

}
