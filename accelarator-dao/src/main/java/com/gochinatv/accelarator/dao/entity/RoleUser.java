package com.gochinatv.accelarator.dao.entity;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述    角色用户表
 * @创建时间 2016年4月20日 下午2:56:20
 * @修改时间
 */
public class RoleUser extends BaseVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int roleId;//角色id
	private int userId;//用户id

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
