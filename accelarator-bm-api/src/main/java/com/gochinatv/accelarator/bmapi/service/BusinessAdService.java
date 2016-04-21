package com.gochinatv.accelarator.bmapi.service;

import java.util.List;

import com.gochinatv.accelarator.bmapi.bean.BusinessAd;

public interface BusinessAdService {

	public List<BusinessAd> queryList(BusinessAd businessAd);
	
	public void save(BusinessAd businessAd);
	
	public void update(BusinessAd businessAd);
	
	public void delete(int businessAdId);
}
