package com.gochinatv.accelarator.util;

import java.util.HashMap;
import java.util.Map;

import com.gochinatv.accelarator.dao.entity.Area;

/** 
 * @author 冯志文
 * @Description: area地域转换类
 * @date Sep 16, 2010 2:29:20 PM 
 */
public class AreaConvertUtil {
	
	

	/**
	* @author 冯志文
	* @Description: 把对象转换为map treegrid
	* @return List<Map<String,Object>>    
	* @throws 
	* @date 2010-9-15 下午01:38:31
	 */
	public static Map<String,Object> convertAreaToTreegrid(Area area){
		 Map<String,Object> item = new HashMap<String,Object>();  
	 	item.put("areaCode",area.getAreaCode()); 
	 	item.put("name", area.getName());
	 	item.put("areaNameH", "<a oncontextmenu=\"rightMenu(event);return false;\" >"+area.getName()+"</a>");
	 	item.put("parentCode",area.getParentCode()); 
	 	item.put("timeChange", area.getTimeChange());
        item.put("sort",area.getSort()); 
        item.put("level",area.getLevel()); 
        item.put("status",area.getStatus()); 
        item.put("statusDesc",area.getStatusDesc()); 
        item.put("createTime", area.getCreateTime());
        if (area.isHasChildren()){  //haschildren
            item.put("state", "closed");
        } else{
    	 item.put("state", "open");
        }
        return item;
	}
	
	/**
	* @author 冯志文
	* @Description: 把对象转换为map treegrid
	* @return List<Map<String,Object>>    
	* @throws 
	* @date 2010-9-15 下午01:38:31
	 */
	public static Map<String,Object> convertAreaToTree(Area area){
		    Map<String,Object> item = new HashMap<String,Object>();
		    item.put("id", area.getAreaCode());
	        item.put("areaCode",area.getAreaCode());  
	        item.put("name", area.getName());
		 	item.put("areaNameH", "<a oncontextmenu=\"rightMenu(event);return false;\" >"+area.getName()+"</a>");
		 	item.put("parentCode",area.getParentCode()); 
		 	item.put("timeChange",area.getTimeChange()); 
	        item.put("sort",area.getSort()); 
	        item.put("level",area.getLevel()); 
	        item.put("status",area.getStatus()); 
	        item.put("statusDesc",area.getStatusDesc());
	        item.put("createTime", area.getCreateTime());
	        item.put("state", "open");
	        return item;
	}
}
