package com.gochinatv.accelarator.service;

import java.text.ParseException;
import java.util.List;

import com.gochinatv.accelarator.dao.entity.TwoFourBm;
import com.gochinatv.accelarator.dao.entity.TwoFourBmArea;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;

/**
 * @作者 administrator
 * @描述   2-4号位管理业务层接口   
 * @创建时间  2016-5-9
 * @修改时间
 */
public interface TwoFourBmService extends BaseService<TwoFourBm>{

	/**
	 * 发放区域
	 * @param twoFourBmArea
	 * @throws Exception
	 */
	void delivery(TwoFourBmArea twoFourBmArea) throws Exception;

	/**
	 * 校验选中地区是否已发放
	 * @param twoFourBmArea
	 * @return
	 * @throws ParseException 
	 */
	List<TwoFourBmArea> checkCode(TwoFourBmArea twoFourBmArea) throws ParseException;

	/**
	 * 得到选中的地区
	 * @param twoFourBmId
	 * @return
	 */
	String getCheckNodes(int twoFourBmId);


}