package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;

import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述 APP广告设置
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class AppAdSettings extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int adIndex;// 广告位置
	private int packageId;// 广告套餐id
	private int type;// 类型
	private int categoryId;// 分类id

	public int getAdIndex() {
		return adIndex;
	}

	public void setAdIndex(int adIndex) {
		this.adIndex = adIndex;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
