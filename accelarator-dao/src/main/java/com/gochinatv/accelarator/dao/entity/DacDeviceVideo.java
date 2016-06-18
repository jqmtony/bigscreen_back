package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;


/**
 * @作者 zhuhh
 * @描述 数据分析后的元数据存储实体
 * @创建时间 2016年6月14日 下午4:06:09
 * @修改时间
 */
public class DacDeviceVideo extends BaseVo implements Serializable {
   
	public static final int KJSJ=101;//开机时间
	public static final int XZSC=102;//文件下载时长
	public static final int BFCS=103;//视频播放次数
	public static final int SCFK=104;//视频删除反馈
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String mac;
	private String code;
	private int videoId;
	private String videoName;
	private int placeId;
	private int businessId;
	private String cityCode;
	private int advertiserId;
	private int type;     //101:开机时间  、102:文件下载时长  、103:视频播放次数 、 104:视频删除反馈
	private String createTime;
	private String businessTime;
	
	
	private int total;//视频播放次数

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getAdvertiserId() {
		return advertiserId;
	}

	public void setAdvertiserId(int advertiserId) {
		this.advertiserId = advertiserId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBusinessTime() {
		return businessTime;
	}

	public void setBusinessTime(String businessTime) {
		this.businessTime = businessTime;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}
