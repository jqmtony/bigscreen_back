package com.gochinatv.accelarator.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gochinatv.accelarator.dao.AreaDao;
import com.gochinatv.accelarator.dao.entity.Area;
import com.gochinatv.accelarator.framework.web.base.dao.BaseDao;
import com.gochinatv.accelarator.framework.web.base.service.impl.BaseServiceImpl;
import com.gochinatv.accelarator.service.AreaService;
import com.gochinatv.accelarator.util.AreaConvertUtil;


@Service
public class AreaServiceImpl  extends BaseServiceImpl<Area> implements  AreaService {

	@Autowired
	private AreaDao areaDao;
	
	@Override
	protected BaseDao<Area> getDao() {
		return areaDao;
	}
	
	@Override
	public void save(Area area) throws Exception{
		if(area.getAreaCode().startsWith(area.getParentCode())
				&& area.getAreaCode().length()==area.getParentCode().length()+2){
				super.save(area);
		}else{
			throw new Exception("地域区号不合法");
		}
	}
	
	public void delete(String areaCode) {
		areaDao.delete(areaCode);
	}

	@Override
	public List<Area> queryListByPid(int parentId) {
		return areaDao.queryListByPid(parentId);
	}

	@Override
	public Area queryById(long areaId) {
		return areaDao.queryById(areaId);
	}
	
	public List<Map<String,Object>> queryAllArea()throws Exception {
		List<Area> areaAList = queryAAreaList();
        return  getAllMenuList(areaAList,"tree");
	}
	/**
	* @author 冯志文
	* @Description:查询所有的一级菜单
	* @date 2010-7-23 上午09:59:41
	 */
	private List<Area> queryAAreaList() throws Exception{
		
		List<Area> areaList = new ArrayList<Area>();	
		String areaCODE = "0";//根地域（全国）的parentCode
		areaList = areaDao.queryChildrenAreaList(areaCODE);
		for(Area area: areaList){   
			area.setHasChildren(areaDao.isHasChildren(area)>0);
			area.setChildrenList(areaDao.queryChildrenAreaList(area.getAreaCode()));
		}   
		return areaList;
	}
	/**
	* @author 冯志文
	* @Description:  通过一级菜单 查询所有的菜单
	* @date 2010-7-23 上午09:59:41
	 */
	private List<Map<String,Object>> getAllMenuList(List<Area> menuList,String treeType){
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();   
        for(Area menu: menuList){   
        	 Map<String,Object> item  = null;
        		item = AreaConvertUtil.convertAreaToTree(menu);
        		 item.put("text",menu.getName());
            if (menu.isHasChildren()){  //如果有子菜单 则递归得到该菜单下的所有菜单
            	List<Map<String,Object>> items2 = new ArrayList<Map<String,Object>>();   
                 items2 = getAllMenuList(menu.getChildrenList(),treeType);
                 item.put("children", items2);
                 item.put("state","open");
            }else{
            }
            items.add(item);   
        }   
         return items;
	}
	/**
	 * 一级一级树
	 */
	@Override
	public List<Map<String,Object>> queryChildrenAreaList(String areaCode) throws Exception {
		List<Area> areaList = new ArrayList<Area>();	
		String areaCODE = "0";//根地域（全国）的parentCode
		if(areaCode != null){
			areaCODE = areaCode;
		}
		areaList = areaDao.queryChildrenAreaList(areaCODE);
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();   
		for(Area area: areaList){   
			area.setHasChildren(areaDao.isHasChildren(area)>0);
			 Map<String,Object> item = AreaConvertUtil.convertAreaToTreegrid(area);
	         items.add(item);   
		}   
		return items;
	}

	@Override
	public Area queryBycq(long areaId) {
		 return areaDao.queryBycq(areaId);
	}
	
    
	/**
	 * 查询所有可用的列表集合
	 * @return
	 */
	public List<Area> queryByLevel(int level){
		return areaDao.queryByLevel(level);
	}
	
	/**
	 * 根据parentCode 查询地区的集合
	 * @param parentCode
	 * @return
	 */
	public JSONArray queryByParentCode(String parentCode){
		JSONArray array = new JSONArray();
		List<Area> areaList = areaDao.queryByParentCode(parentCode);
		if(areaList.size()>0){
			for (Area area : areaList) {
				JSONObject object = new JSONObject();
				object.put("id", area.getAreaCode());
				object.put("text", area.getName());
				array.add(object);
			}
		}
		return array;
	}
	
	
	/**
	 * 根据parentCode 递归查询地区的集合
	 * @param parentCode
	 * @return
	 */
	public JSONArray queryByParentCode(JSONArray array,String parentCode){
		List<Area> areaList = areaDao.queryByParentCode(parentCode);
		if(areaList.size()>0){
			for (Area area : areaList) {
				JSONObject object = new JSONObject();
				object.put("id", area.getAreaCode());
				object.put("text", area.getName());
				JSONArray sub = queryByParentCode(new JSONArray(),area.getAreaCode());
				if(sub.size()>0){
					object.put("children", sub);
				}
				array.add(object);
			}
		}
		return array;
	}
}
