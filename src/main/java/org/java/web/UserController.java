package org.java.web;

/**
 * Created by GD on 2019/8/11 0011 9:25
 */

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.java.service.PatientService;
import org.java.util.JsonUtils;
import org.java.util.MD5Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**病人登录
 **/
@Controller
public class UserController {
	@Autowired
	private PatientService patientService;
	@Autowired
	private HttpServletRequest request;
	
	@ResponseBody
	@RequestMapping("loadbr")
	public String loadDepartmentt(){
		Map<String,Object> m = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int start = (page-1)*limit;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("limit",limit);
		List<Map<String, Object>> list = patientService.findallbr(map);
		int count=patientService.findbrcount();
		m.put("code",0);
		m.put("msg", "");
		m.put("data",list);
		m.put("count",count);
		String json= JsonUtils.objectToJson(m);
		return json;
	}
	
	@RequestMapping("user/login")
	public String login(@RequestParam Map<String,Object> map, Model model){
		System.out.println(map.toString());
		String pwd= MD5Demo.tomd5(map.get("pwd").toString());
		map.put("pwd",pwd);//md5加密后
		System.out.println(map);
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
	
	
	
	
	
	
	
	//短信
	
	@RequestMapping("/sendSms")
	@ResponseBody
	public Object sendSms(HttpServletRequest req, String tel) {
		try {
			JSONObject json = null;
			//生成6位验证码
			String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
			//发送短信
			DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIbR7wIUdegYJd", "4qvYjRGJRGmOPPpCF1uy3mZNVyr9zy");
			IAcsClient client = new DefaultAcsClient(profile);
			CommonRequest request = new CommonRequest();
			request.setMethod(MethodType.POST);
			request.setDomain("dysmsapi.aliyuncs.com");
			request.setVersion("2017-05-25");
			request.setAction("SendSms");
			request.putQueryParameter("RegionId", "cn-hangzhou");
			request.putQueryParameter("PhoneNumbers",tel);
			request.putQueryParameter("SignName", "d102医疗项目");
			request.putQueryParameter("TemplateCode", "SMS_172005240");
			request.putQueryParameter("TemplateParam", "{\"code\":\"" + verifyCode + "\"}");
			CommonResponse response = client.getCommonResponse(request);
			String result = response.getData();
			System.out.println(result);
			json = JSONObject.parseObject(result);
			if(json.getIntValue("code") != 0)//发送短信失败
				return "fail";
			//    将验证码存到session中,同时存入创建时间
			//以json存放，这里使用的是阿里的fastjson
			HttpSession session = req.getSession();
			json = new JSONObject();
			json.put("verifyCode", verifyCode);
			System.out.println(verifyCode);
			json.put("createTime", System.currentTimeMillis());
			// 将认证码存入SESSION
			req.getSession().setAttribute("verifyCode", json);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 注册
	 */
	@RequestMapping("/register")
	@ResponseBody
	public String register(
			HttpServletRequest request, @RequestParam Map<String,Object> map) {
		System.out.println(map.get("verifyCode"));
		JSONObject json = (JSONObject)request.getSession().getAttribute("verifyCode");
		System.out.println(json.getString("verifyCode"));
		if(!json.getString("verifyCode").equals(map.get("verifyCode"))){
			System.out.println("验证码错误");
			return "fail";
		}
		if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
			
			System.out.println("验证码过期");
			return "fail";
		}
		//将用户信息存入数据库
		//这里省略
		String pwd= MD5Demo.tomd5(map.get("pwd").toString());
		map.put("pwd",pwd);//md5加密后
		patientService.addPatient(map);
		System.out.println("验证成功");
		return "success";
	}
	
	@RequestMapping("/checkPhone")
	@ResponseBody
	public String checkPhone(
			HttpServletRequest request, @RequestParam Map<String,Object> map) {
		System.out.println(map);
		//String phone="17371430366";
		List<Map<String, Object>> findalltel = patientService.findalltel();
		String number = (String) map.get("tel");
		System.out.println(findalltel);
		if(number.equals("")){
			return "faill";
		}
		for(Map<String,Object> m:findalltel){
			if(m.get("patient_tel").equals(number)){
				return "fail";
			}
		}
		return "yes";
	}
	@RequestMapping("/checkName")
	@ResponseBody
	public String checkName(
			HttpServletRequest request, @RequestParam Map<String,Object> map) {
		System.out.println(map);
		
		List<Map<String, Object>> findalluser = patientService.findalluser();
		String number = (String) map.get("username");
		System.out.println(findalluser);
		if(number.equals("")){
			return "faill";
		}
		for(Map<String,Object> m:findalluser){
			if(m.get("patient_username").equals(number)){
				return "fail";
			}
		}
		return "yes";
	}
	
	
	
	
	
}
