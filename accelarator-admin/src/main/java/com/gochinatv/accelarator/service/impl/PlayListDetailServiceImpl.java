package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.PlayListDetailDao;
import com.gochinatv.accelarator.dao.entity.PlayListDetail;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.PlayListDetailService;


/**
 * 
 * @作者 zhuhh
 * @描述     播控详情业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class PlayListDetailServiceImpl extends BaseServiceImpl<PlayListDetail> implements PlayListDetailService {

	@Autowired
	private PlayListDetailDao playListDetailDao;
	
	@Override
	protected BaseDao<PlayListDetail> getDao() {
		return playListDetailDao;
	}
	
	
}
