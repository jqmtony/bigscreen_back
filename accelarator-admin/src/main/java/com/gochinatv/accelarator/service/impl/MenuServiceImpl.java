package com.gochinatv.accelarator.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.MenuDao;
import com.gochinatv.accelarator.dao.entity.Menu;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.MenuService;

/**
 * 菜单demo
 * @author limr
 *
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	protected BaseDao<Menu> getDao() {
		return menuDao;
	}
	
	
}
