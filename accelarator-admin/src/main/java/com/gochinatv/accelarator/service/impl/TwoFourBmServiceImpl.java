package com.gochinatv.accelarator.service.impl;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gochinatv.accelarator.dao.TwoFourBmAreaDao;
import com.gochinatv.accelarator.dao.TwoFourBmDao;
import com.gochinatv.accelarator.dao.entity.TwoFourBm;
import com.gochinatv.accelarator.dao.entity.TwoFourBmArea;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.framework.web.base.utils.DateUtils;
import com.gochinatv.accelarator.service.TwoFourBmService;

/**
 * @作者 administrator
 * @描述   2-4号位管理业务层接口实现
 * @创建时间  2016-5-9
 * @修改时间
 */
@Service
public class TwoFourBmServiceImpl extends BaseServiceImpl<TwoFourBm> implements TwoFourBmService{

	@Autowired
	private TwoFourBmDao twoFourBmDao;
	
	@Autowired
	private TwoFourBmAreaDao twoFourBmAreaDao;
	
	@Override
	protected BaseDao<TwoFourBm> getDao() {
		return twoFourBmDao;
	}

	@Override
	public void delivery(TwoFourBmArea twoFourBmArea) throws Exception {
		//先删除
		List<TwoFourBmArea> originalList = twoFourBmAreaDao.getEntityByTbId(twoFourBmArea.getTwoFourBmId());
		if(originalList != null && originalList.size() > 0){
			for(TwoFourBmArea tbBmArea : originalList){
				twoFourBmAreaDao.deleteByEntity(tbBmArea);
			}
		}
		String cityCodes = twoFourBmArea.getCityCode();
		if(StringUtils.isNotBlank(cityCodes)){
			if(cityCodes.contains(",")){
				String[] cityCodeArr = cityCodes.split(",");
				for (int i = 0; i < cityCodeArr.length; i++) {
					TwoFourBmArea tbArea = new TwoFourBmArea();
					tbArea.setCityCode(cityCodeArr[i]);
					tbArea.setTwoFourBmId(twoFourBmArea.getTwoFourBmId());
					tbArea.setCreateTime(new Date());
					twoFourBmAreaDao.save(tbArea);
				}
			}else{
				twoFourBmArea.setCreateTime(new Date());
				twoFourBmAreaDao.save(twoFourBmArea);
			}
		}
		
	}
	@Override
	public List<TwoFourBmArea> checkCode(TwoFourBmArea twoFourBmArea) throws ParseException{
		String dateString = DateUtils.formatDateStringWithOutHMS(new Date());
		twoFourBmArea.setCurrentTime(dateString);
		List<TwoFourBmArea> tList = twoFourBmAreaDao.getListByCodeAndTime(twoFourBmArea);
		if(tList != null && tList.size() > 0){//已存在
			return tList;
		}
		return null;
	}
	@Override
	public String getCheckNodes(int twoFourBmId){
		List<String> checkNodesList = twoFourBmAreaDao.getCheckNodes(twoFourBmId);
		StringBuffer sb=new StringBuffer();
	    if (checkNodesList != null && checkNodesList.size() > 0) {  
	        for (int i = 0; i < checkNodesList.size(); i++) {  
	            if (i < checkNodesList.size() - 1) {  
	                sb.append(checkNodesList.get(i) + ",");  
	            } else {  
	                sb.append(checkNodesList.get(i));  
	            }  
	        }  
	    }  
		String checkNodes =sb.toString();
		return checkNodes;
	}
}
