package com.gochinatv.accelarator.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.DacDeviceVideoDao;
import com.gochinatv.accelarator.dao.entity.DacDeviceVideo;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.DacDeviceVideoService;



@Service
public class DacDeviceVideoServiceImpl extends BaseServiceImpl<DacDeviceVideo> implements DacDeviceVideoService {

	@Autowired
	private DacDeviceVideoDao dacDeviceVideoDao;
	
	@Override
	protected BaseDao<DacDeviceVideo> getDao() {
		return dacDeviceVideoDao;
	}

	
	@Override
	public List<DacDeviceVideo> getPlayCountDetail(int videoId) {
		return dacDeviceVideoDao.getPlayCountDetail(videoId);
	}

}
