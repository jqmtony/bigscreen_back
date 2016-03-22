package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import com.gochinatv.accelarator.dao.constants.AreaConstants;
import com.gochinatv.accelarator.dao.constants.AreaConstants.Status;

public class Area implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String areaCode;
	private String name;
	private String parentCode;
	private int level;//树级别
	private int sort;
	private int status;//是否有投屏  有效 无效
	private String createTime;
	
	 private boolean hasChildren;
	 private List<Area> childrenList;
	 
	 
	 public String getStatusDesc() {
		Status estatus=  AreaConstants.Status.getEnum(status);
		if(estatus ==null){
			return "";
		}else{
			return estatus.getDesc();
		}
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public boolean isHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	public List<Area> getChildrenList() {
		return childrenList;
	}
	public void setChildrenList(List<Area> childrenList) {
		this.childrenList = childrenList;
	}
}
