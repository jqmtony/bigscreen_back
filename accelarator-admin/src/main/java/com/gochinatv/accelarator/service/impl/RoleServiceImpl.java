package com.gochinatv.accelarator.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gochinatv.accelarator.dao.RoleDao;
import com.gochinatv.accelarator.dao.RoleResourceDao;
import com.gochinatv.accelarator.dao.RoleUserDao;
import com.gochinatv.accelarator.dao.UserDao;
import com.gochinatv.accelarator.dao.entity.Role;
import com.gochinatv.accelarator.dao.entity.RoleResource;
import com.gochinatv.accelarator.dao.entity.RoleUser;
import com.gochinatv.accelarator.dao.entity.User;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.RoleService;

/**
 * 
 * @作者 zhuhh
 * @描述   系统广告业务层接口实现
 * @创建时间 2016年3月14日 下午12:55:23
 * @修改时间
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Autowired
	private RoleUserDao roleUserDao;
	
	@Override
	protected BaseDao<Role> getDao() {
		return roleDao;
	}
	
	
	/**
	 * 根据角色id查询角色所拥有的资源
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<RoleResource> getSelectedResourceByRoleId(int roleId)throws Exception{
		List<RoleResource> list = roleResourceDao.getListByRoleId(roleId);
		return list;
	} 
	
	
	/**
	 * 根据角色id查询角色所拥有的用户
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<User> getSelectedUserByRoleId(int roleId)throws Exception{
		List<User> users = userDao.getUsers();
		HashMap<Integer,User> userMap = new HashMap<Integer,User>();
		for (User user : users) {
			user.setRoleId(-1);//设置为-1
			userMap.put(user.getId(), user);
		}
		List<RoleUser> roleUserList = roleUserDao.getListByRoleId(roleId);
		for (RoleUser roleUser : roleUserList) {
			User user = userMap.get(roleUser.getUserId());
			if(null!=user){
				user.setRoleId(roleUser.getRoleId());
			}
		}
		return users;
	}
	
	
	/**
	 * 保存分配的资源
	 * @throws Exception
	 */
	public void saveRoleResource(String resourceIds,int roleId)throws Exception{
		RoleResource rr = new RoleResource();
    	rr.setRoleId(roleId);
    	roleResourceDao.deleteByEntity(rr);	
    	
    	List<RoleResource> roleResourceList = new ArrayList<RoleResource>();
    	String[] split = resourceIds.split(",");
    	for (String resourceId : split) {
    		RoleResource roleResource = new RoleResource();
    		roleResource.setRoleId(roleId);
    		roleResource.setResourceId(Integer.parseInt(resourceId));
    		roleResourceList.add(roleResource);
		}
    	if(roleResourceList.size()>0){
    		roleResourceDao.saveAll(roleResourceList);
    	}
	}
	
	
	/**
	 * 保存分配的用户
	 * @throws Exception
	 */
    public void saveRoleUser(String userIds,int roleId)throws Exception{
    	RoleUser ru = new RoleUser();
    	ru.setRoleId(roleId);
    	roleUserDao.deleteByEntity(ru);	
    	
    	List<RoleUser> roleUserList = new ArrayList<RoleUser>();
    	String[] split = userIds.split(",");
    	for (String userId : split) {
    		RoleUser roleUser = new RoleUser();
    		roleUser.setRoleId(roleId);
    		roleUser.setUserId(Integer.parseInt(userId));
    		roleUserList.add(roleUser);
		}
    	if(roleUserList.size()>0){
    		roleUserDao.saveAll(roleUserList);
    	}
	}
    
}
