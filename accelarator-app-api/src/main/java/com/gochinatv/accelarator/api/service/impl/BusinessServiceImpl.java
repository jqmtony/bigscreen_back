package com.gochinatv.accelarator.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.api.bean.Business;
import com.gochinatv.accelarator.api.dao.BusinessDao;
import com.gochinatv.accelarator.api.service.BusinessService;

@Service
public class BusinessServiceImpl implements BusinessService{

	@Autowired
	private BusinessDao businessDao;
	
	@Override
	public List<Business> queryList() {
		return businessDao.queryList();
	}

}
