package com.gochinatv.accelarator.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.gochinatv.accelarator.dao.entity.RoleResource;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * 
 * @作者 zhuhh
 * @描述     角色资源数据库交互层
 * @创建时间 2016年4月20日 下午2:57:37
 * @修改时间
 */
public interface RoleResourceDao  extends BaseDao<RoleResource>{

    /**
     * 根据roleId查询 角色资源表
     * @param roleId
     * @return
     */
	public List<RoleResource> getListByRoleId(@Param("roleId") int roleId);
}
