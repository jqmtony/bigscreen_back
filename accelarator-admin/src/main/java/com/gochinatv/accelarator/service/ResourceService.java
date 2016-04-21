package com.gochinatv.accelarator.service;

import java.util.List;
import com.gochinatv.accelarator.dao.entity.Resource;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;


/**
 * @作者 zhuhh
 * @描述   资源业务层接口   
 * @创建时间 2016年4月21日 上午11:10:08
 * @修改时间
 */
public interface ResourceService extends BaseService<Resource>{
	   
   
	/**
	 * 递归查询
	 * @param parentId
	 * @return
	 * @throws Exception 
	 */
	public List<Resource> getTreeList(int parentId,List<Resource> treeList) throws Exception;
}
