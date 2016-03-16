package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述 字典表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class Dict extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String key;// 名称key
	private String value;// 名称值
	private int status; // 状态 是否可用，1可用，2不可用
	private int sort; // 排序
	private String type;// 类型 [行政区划：XZQH]
	private String descs;// 描述

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescs() {
		return descs;
	}

	public void setDescs(String descs) {
		this.descs = descs;
	}

}
