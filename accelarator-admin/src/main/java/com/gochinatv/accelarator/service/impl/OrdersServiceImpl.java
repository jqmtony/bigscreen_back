package com.gochinatv.accelarator.service.impl;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.AdvertisementDao;
import com.gochinatv.accelarator.dao.OrdersDao;
import com.gochinatv.accelarator.dao.OrdersDetailDao;
import com.gochinatv.accelarator.dao.PlayListDao;
import com.gochinatv.accelarator.dao.PlayListDetailDao;
import com.gochinatv.accelarator.dao.entity.Advertisement;
import com.gochinatv.accelarator.dao.entity.Orders;
import com.gochinatv.accelarator.dao.entity.OrdersDetail;
import com.gochinatv.accelarator.dao.entity.PlayList;
import com.gochinatv.accelarator.dao.entity.PlayListDetail;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.framework.web.base.utils.DateUtils;
import com.gochinatv.accelarator.service.OrdersService;
import com.gochinatv.accelarator.util.GlobalUtils;
import com.gochinatv.accelarator.util.SessionUtils;

/**
 * 
 * @作者 zhuhh
 * @描述    订单业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private OrdersDetailDao ordersDetailDao;
	
	@Autowired
	private AdvertisementDao advertisementDao;
	
	@Autowired
	private PlayListDao playListDao;
	
	@Autowired
	private PlayListDetailDao playListDetailDao;
	
	
	@Override
	protected BaseDao<Orders> getDao() {
		return ordersDao;
	}
	
	
	/**
	 * 订单列表
	 * @param orders
	 * @return
	 */
	public List<Orders> getOrdersList(Orders orders){
		return ordersDao.getOrdersList(orders);
	}
	
	/**
	 * 在播广告列表
	 * @param orders
	 * @return
	 */
	public List<Orders> queryPlayList(Orders orders){
		return ordersDao.queryPlayList(orders);
	}
	
	/**
	 * 查看可用广告位
	 * @param order
	 * @return
	 */
	public List<Orders> getAvailableList(Orders orders){
		String cityCode = orders.getCityCode();
		StringBuffer buffer = new StringBuffer("'");
		if(!StringUtils.isEmpty(cityCode)){
		   cityCode = cityCode.replaceAll(",","','");
		   buffer.append(cityCode);
		}
		buffer.append("'");
		orders.setCityCode(buffer.toString());
		return ordersDao.getAvailableList(orders);
	}
	
	/**
	 * 订单预览确认
	 * @param orders
	 * @return
	 */
	public List<Orders> getRetryOrdersList(Orders orders){
		return ordersDao.getRetryOrdersList(orders);
	}
	
	/**
	 * 保存订单，保存订单详情
	 * @param place
	 * @throws Exception 
	 */
	public void save(Orders orders) throws Exception{
		orders.setCreater(SessionUtils.getLoginUser().getId());
		orders.setCreateTime(new Date());
		orders.setStatus(1);
		ordersDao.save(orders);
		
		List<Orders> ordersList = this.getRetryOrdersList(orders);
		for (Orders o : ordersList) {
			OrdersDetail detail = new OrdersDetail();
			detail.setType(o.getType());
			detail.setOrdersId(orders.getId());
			detail.setCountryCode(o.getCountryCode());
			detail.setAreaCode(o.getAreaCode());
			detail.setCityCode(o.getCityCode());
			ordersDetailDao.save(detail);
		}
	}
	
	
	  /**
	   * 生成排播组合
	   * 1：保存订单完成之后，首先判断订单的有效日期是否已经过期，如果过期那么不用重新排列 
	   *     SELECT * FROM orders WHERE id=5 
	   * 2：查出orders_detail的涉及影响到哪些city_code和type 
	   *     SELECT type,city_code from orders_detail where orders_id=5 
	   * 3：根据city_code,type循环查询影响到这些的记录行数
	   *     for(){
	   *        SELECT od.type,od.city_code,o.advertisement_id,o.start_time,o.end_time FROM orders_detail od 
	   *        LEFT JOIN orders o  ON od.orders_id=o.id WHERE od.type=xxx AND city_code='xxxx' AND o.status IN(2,3,4) 
	   *        and o.endTime>当前时间，否则认为是过期的
	   *     }
	   * 4： 根据每次查询循环的结果开始执行排播？按时间段进行排播还是每天进行排播？
	   */
	public void createPlayList(Orders orders) throws Exception{
		  String endTime = orders.getEndTime();
		  Date end = DateUtils.SDF_YYYY_MM_DD.parse(endTime);
		  Date now = new Date();
		  if(now.before(end)){//表示结束日期大于今天
			 List<OrdersDetail> ordersDetails = ordersDetailDao.getOrdersDetailByOrdersId(orders.getId());
			 List<Advertisement> ownAdvertisementList = advertisementDao.getOwnAdvertisement();//需要随机从自有广告中取出几个出来
			 
			 long playListId = System.currentTimeMillis();
			 
			for (OrdersDetail detail : ordersDetails) {
				HashMap<String, Object> data = new HashMap<String, Object>();
				data.put("type", detail.getType());
				data.put("cityCode", detail.getCityCode());

				List<Orders> ordersPlayList = this.getOrdersPlayList(data);//duration,advertisement_id,start_time,end_time

				Map<String, List<Advertisement>> dataMap = new HashMap<String, List<Advertisement>>();
				for (int i = 0; i < ordersPlayList.size(); i++) {
					Orders order = (Orders) ordersPlayList.get(i);
					String start_time = order.getStartTime();
					String end_time = order.getEndTime();

					long between = DateUtils.getBetweenDays(start_time, end_time);// 取得两个日期之间的天数

					Calendar instance = Calendar.getInstance();
					instance.setTime(DateUtils.SDF_YYYY_MM_DD.parse(start_time));
					for (int j = 0; j <= between; j++) {
						instance.add(Calendar.DAY_OF_YEAR, j == 0 ? j : 1);
						String date = DateUtils.SDF_YYYY_MM_DD.format(instance.getTime());
						if(now.before(instance.getTime())){//表示只能对订单中的大于今天的日期进行排播，如果小于等于当天不能进行其排播
							List<Advertisement> values = dataMap.get(date);
							if (values == null) {
								values = new ArrayList<Advertisement>();
							}
							Advertisement adm = new Advertisement();
							adm.setId(order.getAdvertisementId());
							adm.setDuration(order.getDuration());
							
							values.add(adm);
							dataMap.put(date, values);
						}
					}
				}
				
				//dataMap中放入的以  日期为key，  广告为 value
				Set<Entry<String, List<Advertisement>>> entrySet = dataMap.entrySet();
				for (Entry<String, List<Advertisement>> entry : entrySet) {
					List<Advertisement> values = entry.getValue();
					int minus = GlobalUtils.ADS_VIDEO_COUNT-values.size();
					if(minus>0){//广告商的广告数量小于总数量，那么需要从自有广告中取出一定的数量进行补充
						List<Advertisement> randomAdvertisementList = getRandomAdvertisementList(ownAdvertisementList,minus);
						values.addAll(randomAdvertisementList);//添加自有广告至排播组合广告集合中
					}
				}
				
				Map<String,List<Integer>> playMap = new HashMap<String,List<Integer>>();
				//广告总数量添加完成，需要对每个广告的循环次数进行计算
				for (Entry<String, List<Advertisement>> entry : entrySet) {
					String key = entry.getKey();
					
					List<Integer> valuesList = new ArrayList<Integer>();
					List<Advertisement> values = entry.getValue();
					for (Advertisement adm : values) {//这里目前只能10个广告
						int duration = adm.getDuration();
						int play_count = GlobalUtils.ADS_EACH_PLAY_TIME/duration;//当前视频播放的次数
						for(int i=0;i<play_count;i++){//需要向集合中添加循环多少次的广告
							valuesList.add(adm.getId());//把广告id添加至集合中
							playMap.put(key, valuesList);
						}
					}
				}
				
				//循环playMap 开始排播组合
				Set<Entry<String, List<Integer>>> playSet = playMap.entrySet();
				for (Entry<String, List<Integer>> entry : playSet) {
					
					savePlayList(entry);
					
					playListId++;
                    PlayList playList = new PlayList();
                    playList.setId(playListId);
                    playList.setCityCode(detail.getCityCode());
                    playList.setType(detail.getType());
                    playList.setStartTime(entry.getKey());
                    playList.setEndTime(entry.getKey());
                    playListDao.save(playList);
                    
					List<PlayListDetail> detailList = new ArrayList<PlayListDetail>();
					List<Integer> values = entry.getValue();
					
					for (Integer advertisementId : values) {
						PlayListDetail details = new PlayListDetail();
			            details.setPlayListId(playList.getId());
						details.setAdvertisementId(advertisementId);
						details.setStartTime(entry.getKey());
						details.setEndTime(entry.getKey());
						//details.setDuration(duration);
						details.setSort((int)System.currentTimeMillis());
						detailList.add(details);
					}
					playListDetailDao.saveAll(detailList);
				}
			} 
		  }else{
			  //结束时间必须大于当天
			  //TODO
		  }
	}
	
	
	/**
	 * 随机从自有广告中取出 fetch个广告
	 * @param advertisementList
	 * @param fetch
	 * @return
	 */
	private List<Advertisement> getRandomAdvertisementList(List<Advertisement> ownAdvertisementList, int fetch) {
		List<Advertisement> randomList = new ArrayList<Advertisement>();
		int size = ownAdvertisementList.size();
		if (size > 0) {
			if (size < fetch) {
				fetch = size;
			}
			randomList = ownAdvertisementList.subList(0, fetch);
		}
		return randomList;
	}
	
	
	/**
	 * 保存排播
	 * @param playMap
	 */
	private void savePlayList(Entry<String, List<Integer>> entry) {
		List<Integer> values = entry.getValue();// 广告id集合
		List<Integer> result = new ArrayList<Integer>();
		List<Integer> repeat = new ArrayList<Integer>();

		int temp = -1;
		while (values.size() > 0) {
			int index = (int) (Math.random() * values.size());
			int value = values.get(index);
			if (temp == value)
				repeat.add(value);
			else
				result.add(value);
			temp = value;
			values.remove(index);
		}

		while (repeat.size() > 0) {
			int repeatIndex = (int) (Math.random() * repeat.size());
			int repeatValue = repeat.get(repeatIndex);

			int resultIndex = (int) (Math.random() * result.size());
			int resultValue = result.get(resultIndex);
			int beforeIndex = resultIndex - 1;

			if (beforeIndex != -1) {
				int beforeValue = result.get(beforeIndex);
				if (beforeValue != repeatValue && repeatValue != resultValue) {
					repeat.remove(repeatIndex);
					result.add(resultIndex, repeatValue);
				}
			}
		}
		entry.setValue(result);
	}
	
	/**
	 * 保存排播组合前查询需要排播组合的列表
	 * @param data {type,cityCode}
	 */
	public List<Orders>  getOrdersPlayList(HashMap<String,Object> data){
		return ordersDao.getOrdersPlayList(data);
	}
    
	/**
	 * 审核订单保存
	 * @param orders
	 * @throws Exception 
	 */
	public void updateCheckOnline(Orders orders) throws Exception{
		orders.setAuditor(SessionUtils.getLoginUser().getId());
		orders.setAuditTime(new Date());
		orders.setStatus(2);
		ordersDao.update(orders);
		
		//创建排播组合
		this.createPlayList(orders);
	}

	/**
	 * 提前下线
	 * @param orders
	 * @throws Exception 
	 */
	public void updateOfflineTime(Orders orders) throws Exception{
		ordersDao.updateOfflineTime(orders);
	}
}
