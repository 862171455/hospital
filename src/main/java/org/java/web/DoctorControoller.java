package org.java.web;

import org.java.service.StaffService;
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
 * Created by GD on 2019/8/12 0012 10:56
 */
@Controller
public class DoctorControoller {
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private HttpServletRequest request;
	
	@ResponseBody
	@RequestMapping("loadAllDoctor")
	public String loadAllDepartment(){
		
		Map<String,Object> m = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int start = (page-1)*limit;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("limit",limit);
		List<Map<String, Object>> list = staffService.findAllStaff(map);
		int count=staffService.findstafffcount();
		m.put("code",0);
		m.put("msg", "");
		m.put("data",list);
		m.put("count",count);
		String json= JsonUtils.objectToJson(m);
		return json;
	}
	
	@ResponseBody
	@RequestMapping("delStafff/{id}")
	public void del(@PathVariable("id")  int id){
		System.out.println(id);
		staffService.delstafff(id);
	}
	@ResponseBody
	@RequestMapping("addstafff")
	public void add(@RequestParam Map<String,Object> map){
		
		if(map.get("id").equals("")){/*是否有id*/
			/*增加*/
			staffService.addstafff(map);
			
		}else{
			/*修改*/
			staffService.updatestafff(map);
		}
		
	}
}
