package com.gochinatv.accelarator.service.impl;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public void save(Area area,HttpServletRequest request) throws Exception{
		Area area2 = areaDao.queryByAreaCode(area.getAreaCode().trim());
		if(area2 !=null ){
			throw new Exception("地域区号已存在");
		}
		if(area.getParentCode().equals("-1") ){//国家
			if(area.getAreaCode().trim().length()==4){
				super.save(area);
			}else{
				throw new Exception("地域区号不合法,国家区号必须4位");
			}
			
		}else{
			if(area.getAreaCode().startsWith(area.getParentCode().trim())
					&& area.getAreaCode().trim().length()==area.getParentCode().trim().length()+2){
					super.save(area);
			}else{
				throw new Exception("地域区号不合法");
			}
		}

        createAreaJson(request);
	}
	
	@Override
	public void update(Area area,HttpServletRequest request) throws Exception{
		super.update(area);
        createAreaJson(request);
	}
	
	public void delete(String areaCode,HttpServletRequest request) throws Exception{
		List<Area> childList = areaDao .queryByParentCode(areaCode);
        if (childList != null &&childList.size()>0){
            for (Area area : childList){
                List<Area> childList2 = areaDao .queryByParentCode(area.getAreaCode());
                 if (childList2 != null && childList2.size()>0){
                      for (Area area2 : childList2){
                           areaDao.delete(area2.getAreaCode());
                     }
                }
                 areaDao.delete(area.getAreaCode());
           }
       }
        areaDao.delete(areaCode);

        createAreaJson(request);

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
        	   menu.setHasChildren(areaDao.isHasChildren(menu)>0);
               menu.setChildrenList(areaDao.queryChildrenAreaList(menu.getAreaCode()));
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
	
	
	/**
	 * 创建静态json文件
	 * @return
	 * @throws Exception
	 */
	public JSONArray createAreaJson(HttpServletRequest request) throws Exception{
		StringBuffer buffer = new StringBuffer();
		
		//查询country列表集合
		List<Area> countryList = queryByLevel(1);
		JSONArray country = new JSONArray();
		
		JSONArray xzqh = new JSONArray();
		
		FileOutputStream fos = null;
		Writer out = null;
		
		for (Area area : countryList) {
			JSONObject object = new JSONObject();
			String areaCode = area.getAreaCode();
			object.put("id", areaCode);
			object.put("text", area.getName());
			JSONArray sub = queryByParentCode(areaCode);
			buffer.append("var _"+areaCode+"=" + sub.toJSONString() +"\n");
			country.add(object);
			
			JSONObject object1 = new JSONObject();
			object1.put("id", areaCode);
			object1.put("text", area.getName());
			JSONArray children = queryByParentCode(new JSONArray(),areaCode);
			if(children.size()>0){
				object1.put("children",children);
			}
			xzqh.add(object1);
		}
		
		//查询city列表集合
		List<Area> cityList = queryByLevel(2);
		for (Area area : cityList) {
			String areaCode = area.getAreaCode();
			JSONArray sub = queryByParentCode(areaCode);
			buffer.append("var _"+areaCode+"=" + sub.toJSONString() +"\n");
		}
		
		buffer.append("var _country=" + country.toJSONString()+"\n");
		
		buffer.append("var _xzqh=" + xzqh.toJSONString()+"\n\n");
		
		String[] shopType = new String[]{"餐厅","大使馆","商场","美甲区","其它"};
		JSONArray shopTypeArray = new JSONArray();
		for(int i=0;i<shopType.length;i++){
			JSONObject object = new JSONObject();
			object.put("id", (i+1)+"");
			object.put("text",shopType[i]);
			shopTypeArray.add(object);
		}
		buffer.append("var _shop_type=" + shopTypeArray.toJSONString()+"\n");
		
		JSONObject shopTypeTree = new JSONObject();
		shopTypeTree.put("id", "-1");
		shopTypeTree.put("text","全部");
		shopTypeTree.put("children", shopTypeArray);
		
		buffer.append("var _shop_type_tree=[" + shopTypeTree.toJSONString()+"]\n");
		
		fos = new FileOutputStream(request.getSession().getServletContext().getRealPath("/js/data.js"));
		out = new OutputStreamWriter(fos, "UTF-8");
		out.write(buffer.toString());
		out.flush();
		out.close();
		fos.close();
		return null;
	}
	
}
