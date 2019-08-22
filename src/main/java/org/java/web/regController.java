package org.java.web;

import org.java.service.DivisionService;
import org.java.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/20 0020 14:06
 */
@Controller
public class regController {
	@Autowired
	private DivisionService divisionService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private StaffService staffService;
	@ResponseBody
	@RequestMapping("findregks")
	public List<Map<String,Object>> findks(){
	return 	divisionService.findAlldivision();
	}
	@ResponseBody
	@RequestMapping("findregys/{id}")
	public List<Map<String,Object>> findys(@PathVariable("id")  int id){
		List<Map<String, Object>> findallys = staffService.findallys(id);
		System.out.println(findallys.toString());
		return findallys;
	}
	@ResponseBody
	@RequestMapping("findregmoney/{id}")
	public Map<String,Object> findmoney(@PathVariable("id")  int id){
		Map<String, Object> findallys = staffService.findAllStaffbyid(id);
		System.out.println(findallys.toString());
		return findallys;
	}
	
	
}
