package org.java.web;

import org.java.service.DepartmentService;
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
 * Created by GD on 2019/8/12 0012 20:01
 */
@Controller
public class DepartmentController {
	@Autowired
    private DepartmentService departmentService;
	
	@Autowired
	private HttpServletRequest request;
	
	@ResponseBody
	@RequestMapping("loadDepartmentt")
	public String loadDepartmentt(){
		Map<String,Object> m = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int start = (page-1)*limit;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("limit",limit);
		List<Map<String, Object>> list = departmentService.findDepartment(map);
		int count=departmentService.findDepartmenttcount();
		m.put("code",0);
		m.put("msg", "");
		m.put("data",list);
		m.put("count",count);
		String json= JsonUtils.objectToJson(m);
		return json;
	}
	@ResponseBody
	@RequestMapping("delDepartmentt/{id}")
	public void del(@PathVariable("id")  int id){
		System.out.println(id);
		departmentService.delDepartmentt(id);
	}
	
	
	@ResponseBody
	@RequestMapping("addDepartmentt")
	public void add(@RequestParam Map<String,Object> map){
		
		if(map.get("id").equals("")){/*是否有id*/
			/*增加*/
			departmentService.addDepartmentt(map);
		}else{
			/*修改*/
			departmentService.updateDepartmentt(map);
		}
		
	}
}
