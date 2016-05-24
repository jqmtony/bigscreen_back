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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


/**
 * @作者 zhuhh
 * @描述    订单业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class OrdersServiceImpl extends BaseServiceImpl<Orders> implements OrdersService {
    
	protected Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);
	
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
	   * 生成排播组合<br>
	   * 1：保存订单完成之后，首先判断订单的有效日期是否已经过期，如果过期那么不用重新排列 
	   *     SELECT * FROM orders WHERE id=5 <br>
	   * 2：查出orders_detail的涉及影响到哪些city_code和type 
	   *     SELECT type,city_code from orders_detail where orders_id=5 <br>
	   * 3：根据city_code,type循环查询影响到这些的记录行数
	   *     for(){<br>
	   *        SELECT adm.duration,o.advertisement_id,o.start_time,o.end_time FROM orders_detail od <br>
	   *        LEFT JOIN orders o  ON od.orders_id=o.id WHERE od.type=xxx AND city_code='xxxx' AND o.status IN(1,2,4) <br>
	   *        and o.endTime>当前时间，否则认为是过期的<br>
	   *     }<br>
	   * 4： 根据每次查询循环的结果开始执行排播？按时间段进行排播还是每天进行排播？
	   * 
	   * type:  SX：订单审核上线     XX：提前下线
	   */
	public boolean createPlayList(Orders orders,String type) throws Exception{
		  boolean result = true;
	      logger.info("*********************执行创建排播组合Orders（endTime={},订单 id={}）",orders.getEndTime(),orders.getId());
		  try{
			  String endTime = orders.getEndTime();
			  Date end = DateUtils.SDF_YYYY_MM_DD.parse(endTime);
			  Date now = new Date();
			  if(now.before(end)){//表示结束日期大于今天，才可进入排播
				 
				 //SELECT * from orders_detail where orders_id=orders.getId()
				 List<OrdersDetail> ordersDetails = ordersDetailDao.getOrdersDetailByOrdersId(orders.getId());//查询此订单影响到哪些城市和店铺类型
				 
				 //SELECT * FROM advertisement WHERE source =1 order by id desc limit 0,10
				 List<Advertisement> ownAdvertisementList = advertisementDao.getOwnAdvertisement();//从自有广告中取出10个广告
				 
				 //SELECT id,duration FROM advertisement 
				 HashMap<Integer, Integer> durationMap = getDurationList();
				 
				 logger.info("*********************执行本订单影响的城市和店铺类型OrdersDetail个数为：{}，自有广告个数为：{}",ordersDetails.size(),ownAdvertisementList.size());
				 
				 List<Long> playListIds = new ArrayList<Long>();//排播列表id集合，用于批量删除的时候用
				 List<PlayList> playLists = new ArrayList<PlayList>();//排播列表Entity，用于批量保存的时候用
				 List<PlayListDetail> detailList = new ArrayList<PlayListDetail>();//排播详情Entity，用户批量保存的时候使用
				 
				 long playListId = System.currentTimeMillis();
				 
				 for (OrdersDetail detail : ordersDetails) {
					HashMap<String, Object> data = new HashMap<String, Object>();
					data.put("type", detail.getType());
					data.put("cityCode", detail.getCityCode());
	                
					/**
					 * cityCode、type 且结束时间大于当天投放的视频信息
					 * SELECT adm.duration,o.advertisement_id,o.start_time,o.end_time FROM orders_detail od <br>
                     * LEFT JOIN orders o  ON od.orders_id=o.id LEFT JOIN advertisement adm  ON adm.id=o.advertisement_id <br>
                     * WHERE od.type=#{type} AND city_code=#{cityCode} AND o.status=2 <br>
                     * AND o.end_time>now() <br>
                     * 查询结果表示在重叠的时间段内排播
					 */
					List<Orders> ordersPlayList = this.getOrdersPlayList(data);//duration,advertisement_id,start_time,end_time
					
					logger.info("*********************正在执行 type为：{}，cityCode为：{}，排播个数为：{}",detail.getType(),detail.getCityCode(),ordersPlayList.size());

					Map<String, List<Advertisement>> adsDataMap = new HashMap<String, List<Advertisement>>();
					for (int i = 0; i < ordersPlayList.size(); i++) {
						Orders order = (Orders) ordersPlayList.get(i);
						String start_time = "";
						if(type.equals("SX")){//审核上线
							start_time = order.getStartTime();
						}else if(type.equals("XX")){
							start_time= DateUtils.addDay(1);//提前下线，取得明天的日期，下线的开始日期必须从明天开始
						}
						String end_time = order.getEndTime();

						long between = DateUtils.getBetweenDays(start_time, end_time);// 取得两个日期之间的天数
						logger.info("*********************order advertisement_id：{}，时间间隔天数为：{}",order.getAdvertisementId(),between);

						Calendar instance = Calendar.getInstance();
						instance.setTime(DateUtils.SDF_YYYY_MM_DD.parse(start_time));
						for (int j = 0; j <= between; j++) {
							instance.add(Calendar.DAY_OF_YEAR, j == 0 ? j : 1);
							String date = DateUtils.SDF_YYYY_MM_DD.format(instance.getTime());
							if(now.before(instance.getTime())){//表示只能对订单中的大于今天的日期进行排播，如果小于等于当天不能进行其排播
								List<Advertisement> values = adsDataMap.get(date);
								if (values == null) {
									values = new ArrayList<Advertisement>();
								}
								Advertisement adm = new Advertisement();
								adm.setId(order.getAdvertisementId());
								adm.setDuration(order.getDuration());
								
								values.add(adm);
								adsDataMap.put(date, values);
							}
						}
					}
					
					//TODO**********2016-05-24 14:28，去除没在此排播的日期，减少循环次数  重要的一段话*******************************
					Map<String, List<Advertisement>> dataMap = new HashMap<String, List<Advertisement>>();
					String startTime = orders.getStartTime();//本次排播开始时间
					Date orderStartTime = DateUtils.SDF_YYYY_MM_DD.parse(startTime);
					if(orderStartTime.before(now)){//此次排播的开始时间小于现在时间，那么排播的开始时间至为现在的下一天
						startTime= DateUtils.addDay(1);//开始时间在明天
					}
					List<String> distinct = new ArrayList<String>();
					long between = DateUtils.getBetweenDays(startTime, endTime);// 取得两个日期之间的天数
					Calendar instance = Calendar.getInstance();
					instance.setTime(DateUtils.SDF_YYYY_MM_DD.parse(startTime));
					for (int j = 0; j <= between; j++) {
						instance.add(Calendar.DAY_OF_YEAR, j == 0 ? j : 1);
						String date = DateUtils.SDF_YYYY_MM_DD.format(instance.getTime());
						distinct.add(date);
					}
					
					for (String key : distinct) {
						dataMap.put(key, adsDataMap.get(key));
					}
					adsDataMap.clear();//放入完成清楚adsDataMap
					//TODO******************************************************************************************
					
					//dataMap中放入的以  日期为key，  广告为 value
					Set<Entry<String, List<Advertisement>>> entrySet = dataMap.entrySet();
					for (Entry<String, List<Advertisement>> entry : entrySet) {
						List<Advertisement> values = entry.getValue();
						int minus = GlobalUtils.ADS_VIDEO_COUNT-values.size();
						if(minus>0){//广告商的广告数量小于总数量，那么需要从自有广告中取出一定的数量进行补充
							List<Advertisement> randomAdvertisementList = getRandomAdvertisementList(ownAdvertisementList,minus);
							values.addAll(randomAdvertisementList);//添加自有广告至排播组合广告集合中
						}
						if(values.size()<10){
							result = false;
							logger.error("*********************当前排播视频小于10个************************");
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
					
					logger.info("*********************循环playMap开始排播组合*********************************");
					
					//循环playMap 开始排播组合
					Set<Entry<String, List<Integer>>> playSet = playMap.entrySet();
					for (Entry<String, List<Integer>> entry : playSet) {
						logger.info("*********************执行排播type为：{}，cityCode为：{}，time为{}",detail.getType(),detail.getCityCode(),entry.getKey());
						HashMap<String,Object> params = new HashMap<String,Object>();
						params.put("cityCode", detail.getCityCode());
						params.put("type", detail.getType());
						params.put("startTime", entry.getKey());
						params.put("endTime", entry.getKey());
						//playListDetailDao.deleteByPlayList(params);//保存之前先删除保存的排播详情
						//playListDao.deleteByMap(params);//保存之前先删除保存的排播列表
						
						Long id = playListDao.getIdByMap(params);
						if(id!=null){
							playListIds.add(id);
						}
						
						savePlayList(entry);
						
						playListId++;
	                    PlayList playList = new PlayList();
	                    playList.setId(playListId);
	                    playList.setCityCode(detail.getCityCode());
	                    playList.setType(detail.getType());
	                    playList.setStartTime(entry.getKey());
	                    playList.setEndTime(entry.getKey());
	                    //playListDao.save(playList);
	                    playLists.add(playList);
	                    
	                    String playStartTime = entry.getKey()+" 10:00:00";
	                    
						List<Integer> values = entry.getValue();
						for(int i=0;i<values.size();i++){
							PlayListDetail details = new PlayListDetail();
							
							Integer advertisementId = values.get(i);
							Integer duration = durationMap.get(advertisementId);//从map中获取时长
							String playEndTime = DateUtils.addSecond(playStartTime, duration);
							
				            details.setPlayListId(playList.getId());
							details.setAdvertisementId(advertisementId);
							details.setStartTime(playStartTime);
							details.setEndTime(playEndTime);
							details.setDuration(duration);
							details.setSort(i+1);
							detailList.add(details);
							
							playStartTime = playEndTime;
						}
						//playListDetailDao.saveAll(detailList);
					}
				} 
				//TODO**********2016-05-24 16:03 解决单条处理sql的问题，批量处理sql的bug*******************************
				 
				 logger.info("*********************正在批量执行  “排播详情删除操作 ” ，共影响{}条**************",playListIds.size());
				 playListDetailDao.deleteAll(playListIds);
				 
				 logger.info("*********************正在批量执行  “排播列表删除操作 ”，共影响{}条 **************",playListIds.size());
				 playListDao.deleteAll(playListIds);
				 
				 logger.info("*********************正在批量保存  “排播保存操作 ”，共影响{}条 **************",playLists.size());
				 playListDao.saveAll(playLists);
				 
				 int size = detailList.size();
				 logger.info("*********************准备正在批量保存  “排播详情保存操作 ”，本次总共影响{}条 **************",size);
				 if(size>0){
					 int page=(size % 2000==0?size/2000:size/2000+1);
					 int endIndex=0;
					 for(int i=0;i<page;i++){
						endIndex = 2000*(i+1);
						if(i==page-1){
							endIndex = size ;
						}
						List<PlayListDetail> subList = detailList.subList(2000*i, endIndex);
						logger.info("*********************正在第{}批量保存  “排播详情保存操作 ”，共影响{}条 **************",i+1,subList.size());
						playListDetailDao.saveAll(subList); 
					 }
				 }
				//TODO**************************************************************************************
			  }else{
				  //TODO 结束时间必须大于当天，否则不予处理
			  }
		  }catch(Exception e){
			  e.printStackTrace();
			  result = false; 
		  }
		  return result;
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
	 * 获取所有的广告 id、duration时长
	 * @return
	 */
	public HashMap<Integer, Integer> getDurationList(){
		HashMap<Integer, Integer> duration = new HashMap<Integer, Integer>();
		List<Advertisement> durationList = advertisementDao.getDurationList();
		for (Advertisement advertisement : durationList) {
			duration.put(advertisement.getId(), advertisement.getDuration());
		}
		return duration;
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
		int loop = 0;
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
			loop++;
			if(loop>250){
				try {
					repeat.clear();
					//logger.error("*********************排播进行了无限循环，请检查排播的视频的id，现在已经强制退出！************************");
					throw new Exception("排播进行了无限循环，请检查排播的视频的id，现在已经强制退出！");
				} catch (Exception e) {
					e.printStackTrace();
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
	public void updateAuditOrders(Orders orders) throws Exception{
		ordersDao.update(orders);
	}

	
	/**
	 * 提前下线
	 * @param orders
	 * @throws Exception 
	 */
	public void updateOfflineOrders(Orders orders) throws Exception{
		ordersDao.updateOfflineTime(orders);
	}

}
