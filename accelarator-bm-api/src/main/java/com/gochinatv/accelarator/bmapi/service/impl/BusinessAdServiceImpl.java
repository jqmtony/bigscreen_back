package com.gochinatv.accelarator.bmapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.bmapi.bean.BusinessAd;
import com.gochinatv.accelarator.bmapi.dao.BusinessAdDao;
import com.gochinatv.accelarator.bmapi.service.BusinessAdService;

@Service
public class BusinessAdServiceImpl implements BusinessAdService{

	@Autowired
	private BusinessAdDao businessAdDao;
	
	public List<BusinessAd> queryList(BusinessAd businessAd) {
		return businessAdDao.queryList(businessAd);
	}

	public void save(BusinessAd businessAd) {
		businessAdDao.save(businessAd);		
	}

	public void update(BusinessAd businessAd) {
		businessAdDao.update(businessAd);		
	}

	public void delete(int businessAdId) {
		businessAdDao.delete(businessAdId);		
	}

}
