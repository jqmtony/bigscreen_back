package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.TwoFourContentDao;
import com.gochinatv.accelarator.dao.entity.TwoFourContent;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.TwoFourContentService;

/**
 * @作者 administrator
 * @描述   2-4号位内容业务层接口实现
 * @创建时间  2016-5-9
 * @修改时间
 */
@Service
public class TwoFourContentServiceImpl extends BaseServiceImpl<TwoFourContent> implements TwoFourContentService{

	@Autowired
	private TwoFourContentDao twoFourContentDao;
	
	@Override
	protected BaseDao<TwoFourContent> getDao() {
		return twoFourContentDao;
	}
    
	
  /**
    * 根据广告位id删除广告内容
    * @param twoFourBmId
    */
    public void deleteByBmId(int twoFourBmId){
    	twoFourContentDao.deleteByBmId(twoFourBmId);
    }
}
