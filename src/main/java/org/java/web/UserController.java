package org.java.web;

/**
 * Created by GD on 2019/8/11 0011 9:25
 */

import org.java.service.PatientService;
import org.java.util.MD5Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**病人登录
 **/
@Controller
public class UserController {
	@Autowired
	private PatientService patientService;
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("user/login")
	public String login(@RequestParam Map<String,Object> map, Model model){
		System.out.println(map.toString());
		String pwd= MD5Demo.tomd5(map.get("pwd").toString());
		map.put("pwd",pwd);//md5加密后
		Map<String, Object> user = patientService.findbyUuAndP(map);//查用户
		if(user==null){
			String err="账号或者密码错误";
			model.addAttribute("err",err);
			return "/qt_login";
		}
		request.getSession().setAttribute("user",user);
		return "/qt_main";
	}
	
	@RequestMapping("user/updatepwd")
	public String updatepwd(@RequestParam Map<String,Object> map, Model model){
		System.out.println(map.toString());
		String password= MD5Demo.tomd5(map.get("password").toString());
		map.put("password",password);//md5加密后
		patientService.updatepwd(map);//密码
		
		return "redirect:/outsystem";
	}
	@RequestMapping("/user/updatedetails")
	public String updatedetails(@RequestParam Map<String,Object> map){
		System.out.println(map.toString());
		Map<String,Object> mapuser = (Map)request.getSession().getAttribute("user");//取到当前用户
	    map.put("patient_id",mapuser.get("patient_id"));//取到当前用户的id
		patientService.updateDetails(map);
		return "redirect:/outsystem";
	}
	
	
	@RequestMapping("outsystem")
	public String outsystem(){
		request.getSession().removeAttribute("user");
		return "/page/index";
	}
}
