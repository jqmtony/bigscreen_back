package com.gochinatv.accelarator.dao.entity;

import java.io.Serializable;
import com.gochinatv.accelarator.framework.web.base.vo.BaseVo;


/**
 * @作者 zhuhh
 * @描述 排播详情表
 * @创建时间 2016年3月14日 上午11:26:55
 * @修改时间
 */
public class OnlineAdResponse extends BaseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long playListId;// 排播列表id
	private String startTime;// 开始时间
	private String title1;
	private String title2;
	private String title3;
	private String title4;
	private String title5;
	private String title6;
	private String title7;
	private String title8;
	private String title9;
	private String title10;
	
	
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public String getTitle4() {
		return title4;
	}
	public void setTitle4(String title4) {
		this.title4 = title4;
	}
	public String getTitle5() {
		return title5;
	}
	public void setTitle5(String title5) {
		this.title5 = title5;
	}
	public String getTitle6() {
		return title6;
	}
	public void setTitle6(String title6) {
		this.title6 = title6;
	}
	public String getTitle7() {
		return title7;
	}
	public void setTitle7(String title7) {
		this.title7 = title7;
	}
	public String getTitle8() {
		return title8;
	}
	public void setTitle8(String title8) {
		this.title8 = title8;
	}
	public String getTitle9() {
		return title9;
	}
	public void setTitle9(String title9) {
		this.title9 = title9;
	}
	public String getTitle10() {
		return title10;
	}
	public void setTitle10(String title10) {
		this.title10 = title10;
	}
	public long getPlayListId() {
		return playListId;
	}
	public void setPlayListId(long playListId) {
		this.playListId = playListId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
