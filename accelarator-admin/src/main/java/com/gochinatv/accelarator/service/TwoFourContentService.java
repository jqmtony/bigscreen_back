package com.gochinatv.accelarator.service;

import com.gochinatv.accelarator.dao.entity.TwoFourContent;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;

/**
 * @作者 administrator
 * @描述   2-4号位内容业务层接口   
 * @创建时间  2016-5-9
 * @修改时间
 */
public interface TwoFourContentService extends BaseService<TwoFourContent>{

	/**
     * 根据广告位id删除广告内容
     * @param twoFourBmId
     */
	public void deleteByBmId(int twoFourBmId);
}