package com.gochinatv.accelarator.framework.web.base.dao;

import java.util.Collection;
import java.util.List;

public interface BaseDao<T> {
  
	/**
	 * 根据 id 查询  实体
	 * @param id
	 * @throws Exception
	 */
	public T get(int id) throws Exception;

    
    /**
     * 查询 list 集合
     * @return
     * @throws Exception
     */
	public List<T> getList() throws Exception;
    
	
	/**
     * 根据传入的entity 查询返回 entity实体集合
     * @return
     * @throws Exception
     */
	public List<T> getListByEntity(T entity) throws Exception;
	
	
	/**
	 * 根据 sql 查询  记录条数 
	 * @param sql
	 * @param params
	 */
	public int getCountBySql(String sql,List<Object> params);
	
	
	/**
	 * 保存 实体
	 * @param entity
	 * @throws Exception
	 */
	public void save(T entity) throws Exception;

	/**
	 * 保存 实体集合 
	 * @param entities
	 * @throws Exception
	 */
	public void saveAll(Collection<T> entities) throws Exception;

	/**
	 * 修改 实体
	 * @param entity
	 * @throws Exception
	 */
	public void update(T entity) throws Exception;

	/**
	 * 修改 实体 集合
	 * @param entities
	 * @throws Exception
	 */
	public void updateAll(Collection<T> entities) throws Exception;
	
	/**
	 * 保存或者修改
	 * @param entity
	 * @throws Exception
	 */
	public void saveOrUpdate(T entity)throws Exception;
  
	/**
	 * 删除实体
	 * @param entity
	 * @throws Exception
	 */
	public void deleteByEntity(T entity) throws Exception;

	/**
	 * 删除 实体
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;
	
}
