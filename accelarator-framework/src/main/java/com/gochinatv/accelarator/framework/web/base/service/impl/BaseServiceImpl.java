package com.gochinatv.accelarator.framework.web.base.service.impl;


import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gochinatv.accelarator.framework.web.base.BaseServlet;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.BaseService;


public abstract class BaseServiceImpl<T> extends BaseServlet implements BaseService<T> {
	
	/**
	 * 
	 */
	protected abstract BaseDao<T> getDao();
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/**
	 * 根据 id 查询  实体
	 * @param id
	 * @throws Exception
	 */
	public T getEntityById(int id) throws Exception{
		return getDao().getEntityById(id);
	}

    
    /**
     * 查询 list 集合
     * @return
     * @throws Exception
     */
	public List<T> getList() throws Exception{
		return getDao().getList();
	}
	
	/**
     * 根据传入的entity 查询返回 entity实体集合
     * @return
     * @throws Exception
     */
	public List<T> getListByEntity(T entity) throws Exception{
		return getDao().getListByEntity(entity);
	}
	
	/**
	 * 根据 sql 查询  记录条数 
	 * @param sql
	 * @param params
	 */
	public int getCountBySql(String sql,List<Object> params){
		return getDao().getCountBySql(sql, params);
	}
	
	/**
	 * 保存 实体
	 * @param entity
	 * @throws Exception
	 */
	public void save(T entity) throws Exception{
		getDao().save(entity);
	}

	/**
	 * 保存 实体集合 
	 * @param entities
	 * @throws Exception
	 */
	public void saveAll(Collection<T> entities) throws Exception{
		getDao().saveAll(entities);
	}

	/**
	 * 修改 实体
	 * @param entity
	 * @throws Exception
	 */
	public void update(T entity) throws Exception{
		getDao().update(entity);
	}

	/**
	 * 修改 实体 集合
	 * @param entities
	 * @throws Exception
	 */
	public void updateAll(Collection<T> entities) throws Exception{
		getDao().updateAll(entities);
	}
	
	/**
	 * 保存或者修改
	 * @param entity
	 * @throws Exception
	 */
	public void saveOrUpdate(T entity)throws Exception{
		getDao().saveOrUpdate(entity);
	}
    
	
	/**
	 * 删除实体
	 * @param entity
	 * @throws Exception
	 */
	public void deleteByEntity(T entity) throws Exception{
		getDao().deleteByEntity(entity);
	}

	/**
	 * 删除 实体
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception{
		getDao().deleteById(id);
	}

}


