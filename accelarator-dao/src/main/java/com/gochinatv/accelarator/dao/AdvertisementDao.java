package com.gochinatv.accelarator.dao;

import java.util.List;

import com.gochinatv.accelarator.dao.entity.Advertisement;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * 
 * @作者 zhuhh
 * @描述     广告内容数据库接口层
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
public interface AdvertisementDao  extends BaseDao<Advertisement>{
   
	
	/**
	 * 自有广告查询，最新的10个广告
	 * @return
	 */
	public List<Advertisement> getOwnAdvertisement();
	
	
	/**
	 * 获取所有的广告 id、duration时长
	 * @return
	 */
	public List<Advertisement> getDurationList();

}
