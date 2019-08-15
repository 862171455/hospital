package org.java.web;

import org.java.service.HisService;
import org.java.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/12 0012 17:01
 */
@Controller
public class HisController {
	@Autowired
	private HisService hisService;
	@Autowired
	private HttpServletRequest request;
	@ResponseBody
	@RequestMapping("updatehis")
	public void updateHis(@RequestParam Map<String,Object> map){
		System.out.println(map);
		hisService.updateHis(map);
		
		
	}
	
	@RequestMapping("/people/fk")
	public String fk(@RequestParam Map<String,Object> map){
		System.out.println(map);
		hisService.addyj(map);
		return "/page/index";
	}
	
	@ResponseBody
	@RequestMapping("loadyjfk")
	public String loadDepartmentt(){
		Map<String,Object> m = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int start = (page-1)*limit;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("limit",limit);
		List<Map<String, Object>> list = hisService.findyjfk(map);
		int count=hisService.findyjfkcount();
		m.put("code",0);
		m.put("msg", "");
		m.put("data",list);
		m.put("count",count);
		String json= JsonUtils.objectToJson(m);
		return json;
	}
	@ResponseBody
	@RequestMapping("delyjfk/{id}")
	public void del(@PathVariable("id")  int id){
	hisService.delyjfk(id);
	}
}
