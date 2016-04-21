package com.gochinatv.accelarator.dao.entity;


import java.io.Serializable;
import java.util.List;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;


/**
 * 
 * @作者 zhuhh
 * @描述   资源表   
 * @创建时间 2016年4月20日 下午2:54:41
 * @修改时间
 */
public class Resource extends BaseVo implements Serializable {

	private static final long serialVersionUID = 1L;
	// Fields

	private String resourceName;// 资源名称
	private String url;// 资源url
	private String remark;// 备注
	private String parentId;// 父级ID
	private int isMenu;// 是否菜单显示
	private String menuCls;// 菜单样式（图片）
	private int sort;// 排序值 
	
	
	
	private List<Resource> children;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(int isMenu) {
		this.isMenu = isMenu;
	}

	public String getMenuCls() {
		return menuCls;
	}

	public void setMenuCls(String menuCls) {
		this.menuCls = menuCls;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}
	
}