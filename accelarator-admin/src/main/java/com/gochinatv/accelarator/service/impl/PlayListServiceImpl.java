package com.gochinatv.accelarator.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.PlayListDao;
import com.gochinatv.accelarator.dao.entity.PlayList;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.framework.web.base.utils.DateUtils;
import com.gochinatv.accelarator.service.PlayListService;


/**
 * 
 * @作者 zhuhh
 * @描述     播控业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class PlayListServiceImpl extends BaseServiceImpl<PlayList> implements PlayListService {

	@Autowired
	private PlayListDao playListDao;
	
	@Override
	protected BaseDao<PlayList> getDao() {
		return playListDao;
	}

	public List<PlayList> getOnlineAdForDevice(PlayList pl) throws Exception {
		//昨天
		pl.setYesterdayTime(DateUtils.addDay(-1));
		return playListDao.getOnlineAdForDevice(pl);
	}

}
