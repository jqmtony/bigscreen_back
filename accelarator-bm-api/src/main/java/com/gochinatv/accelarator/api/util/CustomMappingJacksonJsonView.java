package com.gochinatv.accelarator.api.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * BUG:MappingJacksonJsonView返回 {model类名:{内容}}
 */
public class CustomMappingJacksonJsonView extends MappingJacksonJsonView {

	protected Object filterModel(Map<String, Object> model) {
		System.out.println("=========json============");
		Map<?, ?> result = (Map<?, ?>) super.filterModel(model);
		if (result.size() == 3) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("total", result.get("total"));
			map.put("rows", result.get("rows"));
			return map;
		}else if (result.size() == 2 && result.get("total")==null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resultPo", result.get("resultPo"));
			return map.values().iterator().next();
		}else if (result.size() == 1) {
			return result.values().iterator().next();
		} else {
			return result;
		}
	}
}
