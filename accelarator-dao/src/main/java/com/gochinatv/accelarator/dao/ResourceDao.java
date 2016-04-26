package com.gochinatv.accelarator.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.gochinatv.accelarator.dao.entity.Resource;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * 
 * @作者 zhuhh
 * @描述   资源数据库层  
 * @创建时间 2016年4月20日 下午2:58:04
 * @修改时间
 */
public interface ResourceDao  extends BaseDao<Resource>{

	
	/**
	 * 资源管理 json 集合
	 */
	public List<Resource> getTreeList(@Param("parentId") int parentId) throws Exception;
    
	
	/**
	 * 根据角色id查询角色所拥有的资源
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Resource> getSelectedResourceByRoleId(@Param("roleId") int roleId)throws Exception;
	
	
	/**
	 * 根据登录人的id查询所拥有的资源信息 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Resource> getUserResourceList(@Param("userId") int userId)throws Exception;
	
}
