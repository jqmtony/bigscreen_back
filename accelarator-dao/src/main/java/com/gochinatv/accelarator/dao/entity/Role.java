package com.gochinatv.accelarator.dao.entity;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述   角色表  
 * @创建时间 2016年4月20日 下午2:54:30
 * @修改时间
 */
public class Role extends BaseVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String roleName;// 权限名称
	private String remark;// 权限备注

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}