package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;

/**
 * 
 * @作者 zhuhh
 * @描述   订单详情表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class OrdersDetail extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int type;// 店铺类型       （1：餐厅  2：大使馆   3：商场   4：美甲区    5：其它）
	private String countryCode;// 国家
	private String areaCode;// 地区
	private String cityCode;// 城市
	private int ordersId;// 订单id

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
}
