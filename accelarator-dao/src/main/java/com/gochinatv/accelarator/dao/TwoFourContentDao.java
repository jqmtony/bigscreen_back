package com.gochinatv.accelarator.dao;

import org.apache.ibatis.annotations.Param;

import com.gochinatv.accelarator.dao.entity.TwoFourContent;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;

/**
 * 
 * @作者 administrator
 * @描述   2-4号位内容数据库接口层
 * @创建时间  2016-5-9
 * @修改时间
 */
public interface TwoFourContentDao  extends BaseDao<TwoFourContent>{

   /**
    * 根据广告位id删除广告内容
    * @param twoFourBmId
    */
   public void deleteByBmId(@Param("twoFourBmId") int twoFourBmId);
   
}
