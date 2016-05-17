package com.gochinatv.accelarator.dao;

import java.util.List;

import com.gochinatv.accelarator.dao.entity.TwoFourBmArea;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;

/**
 * 
 * @作者 administrator
 * @描述   2-4号位发放区域管理数据库接口层
 * @创建时间  2016-5-9
 * @修改时间
 */
public interface TwoFourBmAreaDao  extends BaseDao<TwoFourBmArea>{

	/**
	 * 根据时间和城市代码得到发放地区
	 * @param twoFourBmArea
	 * @return
	 */
	List<TwoFourBmArea> getListByCodeAndTime(TwoFourBmArea twoFourBmArea);

	/**
	 * 根据twoFourBmId得到TwoFourBmAreaList
	 * @param twoFourBmId
	 * @return
	 */
	List<TwoFourBmArea> getEntityByTbId(int twoFourBmId);

	/**
	 * 得到选中地区
	 * @param twoFourBmId
	 * @return
	 */
	List<String> getCheckNodes(int twoFourBmId);


}
