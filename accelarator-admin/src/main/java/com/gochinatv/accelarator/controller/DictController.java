package com.gochinatv.accelarator.controller;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gochinatv.accelarator.dao.entity.Area;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.service.AreaService;

/**
 * 
 * @作者 zhuhh
 * @描述  基础数据字典控制层  
 * @创建时间 2016年3月14日 下午1:24:13
 * @修改时间
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController{
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/create_area")
	@ResponseBody
	public JSONArray create_area() throws Exception{
		StringBuffer buffer = new StringBuffer();
		HttpServletRequest request = this.getRequest();
		
		//查询country列表集合
		List<Area> countryList = areaService.queryByLevel(1);
		JSONArray country = new JSONArray();
		
		JSONArray xzqh = new JSONArray();
		
		FileOutputStream fos = null;
		Writer out = null;
		
		for (Area area : countryList) {
			JSONObject object = new JSONObject();
			String areaCode = area.getAreaCode();
			object.put("id", areaCode);
			object.put("text", area.getName());
			JSONArray sub = areaService.queryByParentCode(areaCode);
			buffer.append("var _"+areaCode+"=" + sub.toJSONString() +"\n");
			country.add(object);
			
			JSONObject object1 = new JSONObject();
			object1.put("id", areaCode);
			object1.put("text", area.getName());
			JSONArray children = areaService.queryByParentCode(new JSONArray(),areaCode);
			if(children.size()>0){
				object1.put("children",children);
			}
			xzqh.add(object1);
		}
		
		//查询city列表集合
		List<Area> cityList = areaService.queryByLevel(2);
		for (Area area : cityList) {
			String areaCode = area.getAreaCode();
			JSONArray sub = areaService.queryByParentCode(areaCode);
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
