package org.java.web;

import org.java.service.AdminerService;
import org.java.service.StaffService;
import org.java.service.medService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by GD on 2019/8/15 0015 9:25
 */
@Controller
public class AdminController {
	@Autowired
	private AdminerService adminerService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private medService medService;
	
	@RequestMapping("admin/admin_login")
	public String admin_login(@RequestParam Map<String, Object> map, HttpSession ses,Model model) {
		if (map.get("type").equals("admin")) {
			int i = adminerService.findadminer(map);
			if(i!=0){Map<String, Object> adminer = staffService.findAllStaffbyid(i);
				System.out.println(adminer);
				request.getSession().setAttribute("user",adminer);
				return "/show";
			}
			String err="账号密码错误";
			request.setAttribute("err",err);
			return "/ht_login";
			
		}
		if (map.get("type").equals("mz")) {
			System.out.println("mz");
		}
		if (map.get("type").equals("zy")) {
			System.out.println("zy");
		}
		if (map.get("type").equals("medicine")) {


			int count=medService.managerCount(map);
			if (count == 0) {
				int count1 = medService.purOrderCount(map);
				if (count1 == 0) {
					String err="账号密码错误";
					request.setAttribute("err",err);
					return "/ht_login";
				} else {
					ses.setAttribute("user", map.get("username"));
					Map map1 = medService.findOrderId(map.get("username").toString());
					model.addAttribute("map1", map1);
					return "/medicine/purchase_show";
				}
			} else {
				ses.setAttribute("user", map.get("username"));
				Map map1 = medService.findManagerId(map.get("username").toString());
				model.addAttribute("map1", map1);
				return "/medicine/manager_show";
			}





		}
		
		return "redirect:/for/show";
	}
	
	@RequestMapping("/admin/admin_out")
	public String outsystem(){
		request.getSession().removeAttribute("user");
		return "/ht_login";
	}
}
