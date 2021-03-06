package com.gochinatv.accelarator.service.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
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
	
	
	/**
	 * 根据登录人的id查询所拥有的资源信息 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<Resource> getUserResourceList(int userId)throws Exception{
		return resourceDao.getUserResourceList(userId);
	}
	
	/**
	 * 根据登录人的id查询所拥有的左侧菜单
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public Collection<Resource> getUserMenuResourceList(int userId)throws Exception{
		LinkedHashMap<Integer,Resource> treeMap = new LinkedHashMap<Integer,Resource>();
		LinkedHashMap<Integer,Resource> modelMap = new LinkedHashMap<Integer,Resource>();
		//左侧菜单的权限
		List<Resource> resourceList = resourceDao.getUserMenuResourceList(userId);
		for (Resource resource : resourceList) {
			if(resource.getParentId()==-1){
				treeMap.put(resource.getId(), resource);
			}else{
				if(resource.getIsVirtual()==1){//虚拟模块
					modelMap.put(resource.getId(), resource);					
				}else{
					Resource res = modelMap.get(resource.getParentId());
					if(res==null){
						res= treeMap.get(resource.getParentId());
					}
					List<Resource> children = res.getChildren();
					if(null==children){
						children = new ArrayList<Resource>();
					}
					children.add(resource);
					res.setChildren(children);
				}
			}
		}
		Iterator<Entry<Integer, Resource>> iterator = modelMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, Resource> next = iterator.next();
			Resource value = next.getValue();
			int parentId = value.getParentId();
			Resource resource = treeMap.get(parentId);
			List<Resource> children = resource.getChildren();
			if(null==children){
				children = value.getChildren();
			}else{
				children.addAll(value.getChildren());;
			}
			resource.setChildren(children);
		}
		Collection<Resource> values = treeMap.values();
		return values;
	}
	
}
