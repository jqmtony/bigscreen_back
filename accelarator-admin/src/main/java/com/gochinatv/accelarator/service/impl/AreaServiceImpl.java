package com.gochinatv.accelarator.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.AreaDao;
import com.gochinatv.accelarator.dao.entity.Area;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.AreaService;
import com.gochinatv.accelarator.util.AreaConvertUtil;
@Service
public class AreaServiceImpl  extends BaseServiceImpl<Area> implements  AreaService {

	private static  Logger logger = Logger.getLogger(AreaServiceImpl.class);
	  
	@Autowired
	private AreaDao areaDao;
	
	@Override
	protected BaseDao<Area> getDao() {
		return areaDao;
	}
	public void delete(String areaCode) {
		areaDao.delete(areaCode);
	}

	@Override
	public List<Area> queryListByPid(int parentId) {
		return areaDao.queryListByPid(parentId);
	}

	@Override
	public Area queryById(long areaId) {
		return areaDao.queryById(areaId);
	}
	
	@Override
	public List<Map<String,Object>> queryChildrenAreaList(String areaCode) throws Exception {
		List<Area> areaList = new ArrayList<Area>();	
		String areaCODE = "0";//根地域（全国）的parentCode
		if(areaCode != null){
			areaCODE = areaCode;
		}
		areaList = areaDao.queryChildrenAreaList(areaCODE);
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();   
		for(Area area: areaList){   
			area.setHasChildren(areaDao.isHasChildren(area)>0);
			 Map<String,Object> item = AreaConvertUtil.convertAreaToTreegrid(area);
	         items.add(item);   
		}   
		return items;
	}

	@Override
	public Area queryBycq(long areaId) {
		 return areaDao.queryBycq(areaId);
	}
	

}
