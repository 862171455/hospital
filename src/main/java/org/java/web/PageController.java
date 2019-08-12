package org.java.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by GD on 2019/8/2 0002 14:32
 */
@Controller
public class PageController {
	@GetMapping("page/{target}")
	public String tool(@PathVariable("target") String target){
		return "page/"+target;
	}
	@GetMapping("for/{target}")
	public String forword(@PathVariable("target") String target){
		if(target.equals("updategw")||target.equals("add_stafff")||target.equals("update_his")||target.equals("update_bm")
				){
			return "/gw/"+target;
		}
		return target;
	}
	
}
