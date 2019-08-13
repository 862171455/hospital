package org.java.web;

import org.java.service.DivisionService;
import org.java.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/9 0009 8:44
 */
@Controller
public class DivisionController {
	@Autowired
	private DivisionService divisionService;
	@Autowired
	private HttpServletRequest request;
	
	
	@RequestMapping("/ksjs/{id}")
	public String findDivisionById(@PathVariable("id") int id, Model model){
		Map<String, Object> map = divisionService.finddivisionbyid(id);
		model.addAttribute("map",map);
		System.out.println(map);
		return "page/ksjsdetails";
	}
	
	@ResponseBody
	@RequestMapping("loadDivision")
	public String loadDepartmentt(){
		Map<String,Object> m = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int start = (page-1)*limit;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("limit",limit);
		List<Map<String, Object>> list = divisionService.finddivision(map);
		int count=divisionService.finddivisioncount();
		m.put("code",0);
		m.put("msg", "");
		m.put("data",list);
		m.put("count",count);
		String json= JsonUtils.objectToJson(m);
		return json;
	}
	@ResponseBody
	@RequestMapping("delDivision/{id}")
	public void del(@PathVariable("id")  int id){
		divisionService.deldivision(id);
		}
	
	
	@ResponseBody
	@RequestMapping("addDivision")
	public void add(@RequestParam Map<String,Object> map){
		System.out.println(map);
		if(map.get("id").equals("")){/*是否有id*/
			/*增加*/
			divisionService.adddivision(map);
			
		}else{
			/*修改*/
			divisionService.updatedivision(map);
			
		}
		
	}
}
