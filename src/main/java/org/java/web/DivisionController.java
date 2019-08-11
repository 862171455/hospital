package org.java.web;

import org.java.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by GD on 2019/8/9 0009 8:44
 */
@Controller
public class DivisionController {
	@Autowired
	private DivisionService divisionService;
	
	
	@RequestMapping("/ksjs/{id}")
	public String findDivisionById(@PathVariable("id") int id, Model model){
		Map<String, Object> map = divisionService.finddivisionbyid(id);
		model.addAttribute("map",map);
		System.out.println(map);
		return "page/ksjsdetails";
	}
}
