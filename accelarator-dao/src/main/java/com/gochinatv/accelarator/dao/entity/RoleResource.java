package com.gochinatv.accelarator.dao.entity;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述  角色资源表   
 * @创建时间 2016年4月20日 下午2:55:33
 * @修改时间
 */
public class RoleResource extends BaseVo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int roleId;//角色id
	private int resourceId;//资源id

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
}
