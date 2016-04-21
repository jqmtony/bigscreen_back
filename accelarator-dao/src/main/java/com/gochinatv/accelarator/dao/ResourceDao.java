package com.gochinatv.accelarator.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.gochinatv.accelarator.dao.entity.Resource;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;


/**
 * 
 * @作者 zhuhh
 * @描述   资源数据库层  
 * @创建时间 2016年4月20日 下午2:58:04
 * @修改时间
 */
public interface ResourceDao  extends BaseDao<Resource>{

	
	/**
	 * 资源管理 json 集合
	 */
	public List<Resource> getTreeList(@Param("parentId") int parentId) throws Exception;

}