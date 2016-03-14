package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.RegionDao;
import com.gochinatv.accelarator.dao.entity.Region;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.RegionService;

/**
 * 
 * @作者 zhuhh
 * @描述    行政区划业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class RegionServiceImpl extends BaseServiceImpl<Region> implements RegionService {

	@Autowired
	private RegionDao regionDao;
	
	@Override
	protected BaseDao<Region> getDao() {
		return regionDao;
	}
	
	
}
