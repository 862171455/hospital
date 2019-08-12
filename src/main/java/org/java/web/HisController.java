package org.java.web;

import org.java.service.HisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by GD on 2019/8/12 0012 17:01
 */
@Controller
public class HisController {
	@Autowired
	private HisService hisService;
	@ResponseBody
	@RequestMapping("updatehis")
	public void updateHis(@RequestParam Map<String,Object> map){
		System.out.println(map);
		hisService.updateHis(map);
		
		
	}
}
