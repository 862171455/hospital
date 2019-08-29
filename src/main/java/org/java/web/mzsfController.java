package org.java.web;

import org.java.service.YaookService;
import org.java.service.YaoorderService;
import org.java.service.medService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/27 0027 10:23
 */

@Controller
public class mzsfController {
	@Autowired
	private YaookService yaookService;
	@Autowired
	private YaoorderService yaoorderService;
	@Autowired
	private medService mdSercive;
	
	
	@RequestMapping("alldd")
	public String findalldd(HttpServletRequest request){
		List<Map<String,Object>> findyaook =yaookService.findyaook();
		request.setAttribute("list",findyaook);
		
		return "mzsf";
	}
	@RequestMapping("allddbyid")
	public String findallddbyid(HttpServletRequest request){
		Map<String,Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		String patient_id = (String) user.get("patient_id");
		Map<String,Object> map=new HashMap<>();
		map.put("id",patient_id);
		List<Map<String,Object>> findyaook =yaookService.findyaookbyid(map);
		request.setAttribute("list",findyaook);
		
		return "user_order";
	}
	@RequestMapping("user/yaoorder/{id}")
	public String findbyorderid(@PathVariable("id") String id, HttpServletRequest request){
		Map<String,Object> map=new HashMap<>();
		map.put("id",id);
		List<Map<String, Object>> findorder = yaoorderService.findorder(map);
		System.out.println(findorder);
		request.setAttribute("list",findorder);
		return "ddxq";
	}
	@RequestMapping("user/yaook/{id}")
	public String updateordertype(@PathVariable("id") String id){
		Map<String,Object> map=new HashMap<>();
		map.put("id",id);
		List<Map<String, Object>> findorder = yaoorderService.findorder(map);
		for(Map<String,Object> m:findorder){//减少医院库里药
			Map<String,Object> yao=new HashMap<>();
			yao.put("id",m.get("yaoorder_yao"));
			yao.put("number",m.get("yaoorder_num"));
			mdSercive.updateyaonum(yao);
		}
		yaookService.updatetype(map);
		//commit
		return "redirect:/alldd";
	}
	
}
