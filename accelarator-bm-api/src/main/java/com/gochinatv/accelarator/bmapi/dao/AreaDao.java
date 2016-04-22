package com.gochinatv.accelarator.bmapi.dao;

import org.apache.ibatis.annotations.Param;

public interface AreaDao{
	/**
	 * 根据编码得到名称
	 * @author limr
	 * @param areaCode
	 * @return
	 */
	public String getNameByCode(@Param(value="areaCode") String areaCode);
}
