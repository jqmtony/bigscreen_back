package com.gochinatv.accelarator.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.AdvertisementDao;
import com.gochinatv.accelarator.dao.entity.Advertisement;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.AdvertisementService;


/**
 * 
 * @作者 zhuhh
 * @描述     广告内容业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class AdvertisementServiceImpl extends BaseServiceImpl<Advertisement> implements AdvertisementService {

	@Autowired
	private AdvertisementDao advertisementDao;
	
	@Override
	protected BaseDao<Advertisement> getDao() {
		return advertisementDao;
	}
	
	
	/**
	 * 自有广告查询，最新的10个广告
	 * @return
	 */
	public List<Advertisement> getOwnAdvertisement(){
		return advertisementDao.getOwnAdvertisement();
	}
	
}
