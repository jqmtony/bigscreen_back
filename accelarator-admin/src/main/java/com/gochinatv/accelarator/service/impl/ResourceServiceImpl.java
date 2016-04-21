package com.gochinatv.accelarator.service.impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.ResourceDao;
import com.gochinatv.accelarator.dao.entity.Resource;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.ResourceService;

/**
 * 
 * @作者 zhuhh
 * @描述   资源业务层实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService{

	@Autowired
	private ResourceDao resourceDao;
	
	@Override
	protected BaseDao<Resource> getDao() {
		return resourceDao;
	}
	
	
	/**
	 * 递归查询
	 * @param parentId
	 * @return
	 * @throws Exception 
	 */
	public List<Resource> getTreeList(int parentId,List<Resource> treeList) throws Exception{
		List<Resource> resourceList = resourceDao.getTreeList(parentId);
		for (Resource resource : resourceList) {
			List<Resource> subList = resourceDao.getTreeList(resource.getId());
			if(subList.size()>=0){
				resource.setChildren(getTreeList(resource.getId(),new ArrayList<Resource>()));
			}
			treeList.add(resource);
		}
		return treeList;
	}
	
}
