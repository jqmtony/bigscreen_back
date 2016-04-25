package com.gochinatv.accelarator.service;

import java.util.List;
import com.gochinatv.accelarator.dao.entity.Role;
import com.gochinatv.accelarator.dao.entity.RoleResource;
import com.gochinatv.accelarator.dao.entity.User;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;


/**
 * @作者 zhuhh
 * @描述   角色业务层接口   
 * @创建时间 2016年4月21日 上午11:10:08
 * @修改时间
 */
public interface RoleService extends BaseService<Role>{

	/**
	 * 根据角色id查询角色所拥有的资源
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<RoleResource> getSelectedResourceByRoleId(int roleId)throws Exception;
	
	
	/**
	 * 根据角色id查询角色所拥有的用户
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<User> getSelectedUserByRoleId(int roleId)throws Exception;
	
	
	/**
	 * 保存分配的资源
	 * @throws Exception
	 */
	public void saveRoleResource(String resourceIds,int roleId)throws Exception;
	
	
	/**
	 * 保存分配的用户
	 * @throws Exception
	 */
    public void saveRoleUser(String userIds,int roleId)throws Exception;

}
