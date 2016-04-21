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

	private String roleId;
	private String userId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
