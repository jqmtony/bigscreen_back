package com.gochinatv.accelarator.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.gochinatv.accelarator.dao.entity.Area;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;

public interface AreaService  extends BaseService<Area>{
	public List<Area> queryListByPid(int parentId);
	
	public Area queryById(long areaId);
	
	public void delete(String areaCode,HttpServletRequest request) throws Exception;
	
	public Area queryBycq(long areaId);
	
	public List<Map<String,Object>> queryAllArea()throws Exception ;
	/**
	* @author 冯志文
	* @Description:异步树  查询areaId对应的子地域
	 */
	public List<Map<String,Object>> queryChildrenAreaList(String areaCode) throws Exception;
	
	
	/**
	 * 查询所有可用的列表集合
	 * @return
	 */
	public List<Area> queryByLevel(int level);
	
	/**
	 * 根据parentCode 查询地区的集合
	 * @param parentCode
	 * @return
	 */
	public JSONArray queryByParentCode(String parentCode);
	
	
	/**
	 * 根据parentCode 递归查询地区的集合
	 * @param parentCode
	 * @return
	 */
	public JSONArray queryByParentCode(JSONArray array,String parentCode);
	
	
	/**
	 * 创建静态json文件
	 * @return
	 * @throws Exception
	 */
	public JSONArray createAreaJson(HttpServletRequest request) throws Exception;
	
	
	public void save(Area area,HttpServletRequest request) throws Exception;
	
	public void update(Area area,HttpServletRequest request) throws Exception;
	
}
