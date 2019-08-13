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
				||target.equals("add_departmentt")||target.equals("update_ks")||target.equals("add_ks")){
			return "/gw/"+target;
		}
		if(target.equals("chinese_medicine")||target.equals("western_medicine")||target.equals("supplier")||target.equals("drug")||target.equals("add")||target.equals("edit")||target.equals("detail")
<<<<<<< HEAD
				||target.equals("chinese_detail")||target.equals("western_detail"))
		
=======
				||target.equals("chinese_detail")||target.equals("western_detail")||target.equals("drugstore")||target.equals("drugstore_detail"))



>>>>>>> 0d4f89f30e3051ab4b87c3c890db74be02d50a74
		{
			return "/medicine/"+target;
		}
		return target;
	}
	
}
