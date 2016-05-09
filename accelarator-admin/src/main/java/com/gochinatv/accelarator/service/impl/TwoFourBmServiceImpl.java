package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.TwoFourBmDao;
import com.gochinatv.accelarator.dao.entity.TwoFourBm;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
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
	
	@Override
	protected BaseDao<TwoFourBm> getDao() {
		return twoFourBmDao;
	}
    
}
