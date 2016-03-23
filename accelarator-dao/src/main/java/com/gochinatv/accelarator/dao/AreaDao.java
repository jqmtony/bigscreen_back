package com.gochinatv.accelarator.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.gochinatv.accelarator.dao.entity.Area;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;

public interface AreaDao extends BaseDao<Area>{
	
	public List<Area> queryListByPid(@Param(value="parentId")int parentId);
	
	public void delete(@Param(value="areaCode")String areaCode);
	
	public Area queryById(@Param(value="areaId")long areaId);
	
	public Area queryBycq(@Param(value="areaId")long areaId);
	
	
	/**
	* @author 冯志文
	* @Description: 查询area是否含有子area
	* @throws 
	 */
	public int isHasChildren(Area area);
	
	/**
	* @author 冯志文
	* @Description:异步树  查询areaId对应的子地域
	 */
	public List<Area> queryChildrenAreaList(@Param(value="areaCode")String areaCode);
	
	
	
	/*************************************************************************************************************/
	
	/**
	 * 根据level查询所有可用列表集合
	 * @return
	 */
	public List<Area> queryByLevel(@Param(value="level") int level);
	
	/**
	 * 根据parentCode 查询地区的集合
	 * @param parentCode
	 * @return
	 */
	public List<Area> queryByParentCode(@Param(value="parentCode") String parentCode);
}
