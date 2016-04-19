package com.gochinatv.accelarator.bmapi.dao;

import java.util.List;

import com.gochinatv.accelarator.bmapi.bean.BusinessAd;

public interface BusinessAdDao {

	public List<BusinessAd> queryList(BusinessAd businessAd);
	
	public void save(BusinessAd businessAd);
	
	public void update(BusinessAd businessAd);
	
	public void delete(int businessAdId);
}
