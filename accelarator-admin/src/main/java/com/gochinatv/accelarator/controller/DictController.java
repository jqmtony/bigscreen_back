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
import com.gochinatv.accelarator.dao.entity.Dict;
import com.gochinatv.accelarator.framework.web.base.controller.BaseController;
import com.gochinatv.accelarator.service.DictService;

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
	private DictService dictService;

	@RequestMapping("/create")
	@ResponseBody
	public List<Dict> create() throws Exception{
		List<Dict> list = dictService.getList();
		return list;
	}
	
	@RequestMapping("/create_region")
	@ResponseBody
	public JSONArray create_region(Dict dict) throws Exception{
		HttpServletRequest request = this.getRequest();
		JSONArray array = new JSONArray();
		FileOutputStream fos = null;
		Writer out = null;
		List<Dict> dictList = dictService.getListByEntity(dict);
		for (Dict d : dictList) {
			JSONObject region = new JSONObject();
			String key = d.getKey();
			if(key.length()==3){
				region.put("id", d.getId());
				region.put("key", key);
				region.put("text", d.getValue());
				region.put("children",new JSONArray());
				array.add(region);
			}else{
				for (Object object : array) {
					JSONObject obj = (JSONObject) object;
					String jsonKey = obj.getString("key");
					JSONArray children = obj.getJSONArray("children");
					if(key.startsWith(jsonKey)){
						region.put("id", d.getId());
						region.put("key", key);
						region.put("text", d.getValue());
						children.add(region);
					}
				}
			}
		}
		//array.addAll(dictList);
		
		fos = new FileOutputStream(request.getSession().getServletContext().getRealPath("/js/region.js"));
		out = new OutputStreamWriter(fos, "UTF-8");
		out.write("var xzqh=" + array.toJSONString());
		out.flush();
		out.close();
		fos.close();
		return array;
	}
	
}
