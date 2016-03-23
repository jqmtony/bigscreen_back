package com.gochinatv.accelarator.service;

import java.util.List;
import java.util.Map;

import com.gochinatv.accelarator.dao.entity.Area;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;

public interface AreaService  extends BaseService<Area>{
	public List<Area> queryListByPid(int parentId);
	
	public Area queryById(long areaId);
	
	public void delete(String areaCode);
	
	public Area queryBycq(long areaId);
	
	public List<Map<String,Object>> queryAllArea()throws Exception ;
	/**
	* @author 冯志文
	* @Description:异步树  查询areaId对应的子地域
	 */
	public List<Map<String,Object>> queryChildrenAreaList(String areaCode) throws Exception;
}
