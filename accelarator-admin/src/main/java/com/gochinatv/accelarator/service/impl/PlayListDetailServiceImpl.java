package com.gochinatv.accelarator.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.PlayListDetailDao;
import com.gochinatv.accelarator.dao.entity.OnlineAd;
import com.gochinatv.accelarator.dao.entity.OnlineAdResponse;
import com.gochinatv.accelarator.dao.entity.PlayListDetail;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.PlayListDetailService;


/**
 * 
 * @作者 zhuhh
 * @描述     播控详情业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class PlayListDetailServiceImpl extends BaseServiceImpl<PlayListDetail> implements PlayListDetailService {

	@Autowired
	private PlayListDetailDao playListDetailDao;
	
	@Override
	protected BaseDao<PlayListDetail> getDao() {
		return playListDetailDao;
	}

	public List<OnlineAdResponse> getOnlineAdListByEntity(
			PlayListDetail playListDetail) {
		List<OnlineAd> list = new ArrayList<OnlineAd>();
		List<PlayListDetail> playDetailList = playListDetailDao.getOnlineAdListByEntity(playListDetail);
		
		Map<String,List<PlayListDetail>>  map  = new HashMap<String,List<PlayListDetail>>();
		for(PlayListDetail playListDetail2 : playDetailList){
			String key = playListDetail2.getPlayListId()+"#"+playListDetail2.getStartTime();
			if(map.get(key) ==null){
				List<PlayListDetail> list2 = new ArrayList<PlayListDetail>();
				list2.add(playListDetail2);
				map.put(playListDetail2.getPlayListId()+"#"+playListDetail2.getStartTime(), list2);
			}else{
				List<PlayListDetail> list2 = map.get(key);
				list2.add(playListDetail2);
				map.put(playListDetail2.getPlayListId()+"#"+playListDetail2.getStartTime(), list2);
			}
		}
		
		
		 for (Map.Entry<String, List<PlayListDetail>> entry : map.entrySet()) {
			 String key = entry.getKey();
			 OnlineAd onlineAd = new OnlineAd();
			 onlineAd.setPlayListId(Long.parseLong(key.split("#")[0]));
			 onlineAd.setStartTime(key.split("#")[1]);
			 onlineAd.setAdList( entry.getValue());
			 list.add(onlineAd);
		}
		 
		 List<OnlineAdResponse> onlineAdResponseList = new ArrayList<OnlineAdResponse>();
		 
		 for(OnlineAd onlineAd : list){
			 OnlineAdResponse onlineAdResponse = new OnlineAdResponse();
			 onlineAdResponse.setPlayListId(onlineAd.getPlayListId());
			 onlineAdResponse.setStartTime(onlineAd.getStartTime());
			 
			 if(onlineAd.getAdList()!=null)
			 for(int i=0;i<onlineAd.getAdList().size();i++){
				 PlayListDetail pplayListDetail  = onlineAd.getAdList().get(i);
				 if(i==0){
					 onlineAdResponse.setTitle1(pplayListDetail.getTitle());
				 }
				 if(i==1){
					 onlineAdResponse.setTitle2(pplayListDetail.getTitle());
				 }
				 if(i==2){
					 onlineAdResponse.setTitle3(pplayListDetail.getTitle());
				 }
				 if(i==3){
					 onlineAdResponse.setTitle4(pplayListDetail.getTitle());
				 }
				 if(i==4){
					 onlineAdResponse.setTitle5(pplayListDetail.getTitle());
				 }
				 if(i==5){
					 onlineAdResponse.setTitle6(pplayListDetail.getTitle());
				 }
				 if(i==6){
					 onlineAdResponse.setTitle7(pplayListDetail.getTitle());
				 }
				 if(i==7){
					 onlineAdResponse.setTitle8(pplayListDetail.getTitle());
				 }
				 if(i==8){
					 onlineAdResponse.setTitle9(pplayListDetail.getTitle());
				 }
				 if(i==9){
					 onlineAdResponse.setTitle10(pplayListDetail.getTitle());
				 }
			 }
			 onlineAdResponseList.add(onlineAdResponse);
		 }
		return onlineAdResponseList;
	}
	
}
