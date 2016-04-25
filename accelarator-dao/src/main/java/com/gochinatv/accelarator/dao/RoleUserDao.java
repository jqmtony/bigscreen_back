package com.gochinatv.accelarator.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gochinatv.accelarator.dao.entity.RoleUser;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * @作者 zhuhh
 * @描述     角色用户数据库交互层
 * @创建时间 2016年4月20日 下午2:57:37
 * @修改时间
 */
public interface RoleUserDao  extends BaseDao<RoleUser>{

   
	/**
     * 根据roleId查询 角色资源表
     * @param roleId
     * @return
     */
	public List<RoleUser> getListByRoleId(@Param("roleId") int roleId);
}
