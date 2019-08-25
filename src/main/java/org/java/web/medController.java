package org.java.web;

import org.java.service.YaocarService;
import org.java.service.medService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/11 16:19
 */
@Controller
public class medController {
    @Autowired
    private medService medService;
    
    @Autowired
	private YaocarService yaocarService;
    @RequestMapping("alldrug")
    @ResponseBody
    public List<Map<String,Object>> Alldrug(){
        List<Map<String,Object>> list = medService.findallDrug();
        return list;
        }
    @RequestMapping("drug")
    @ResponseBody
    public Map drug(Integer page,Integer limit,String dru_name,@RequestParam(name = "dru_drugstore_no",defaultValue = "0") Integer dru_drugstore_no){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findDrug(page,limit,dru_name,dru_drugstore_no);
       int count=medService.drugCount(dru_name,dru_drugstore_no);
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",list);
        return map;


    }
    @RequestMapping("/add")
    @ResponseBody
    public void add(@RequestParam Map map){
        medService.add(map);
    }
    @RequestMapping("/update")
    @ResponseBody
    public void update(@RequestParam Map map){

        medService.update(map);
    }
    @RequestMapping("/del")
    @ResponseBody
    public void del(Integer dru_no){

        medService.del(dru_no);
    }

    @RequestMapping("/sup")
    @ResponseBody
    public Map sup(){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findSupplier();
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return map;


    }
    @RequestMapping("/allSup")
    @ResponseBody
    public List<Map<String,Object>> allSup(){

        List<Map<String,Object>> list = medService.findSupplier();
        return list;


    }
    @RequestMapping("/chinese_medicine")
    @ResponseBody
    public Map chinese_medicine(){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findChinese_medicine();
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return map;


    }
    @RequestMapping("/western_medicine")
    @ResponseBody
    public Map western_medicine(){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findWestern_medicine();
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return map;


    }
    @RequestMapping("/drugstore")
    @ResponseBody
    public Map drugstore (){
        Map map=new HashMap();
        List<Map<String,Object>> list = medService.findDrugstore();
        for(Map<String,Object> m:list){
            int sto_count = (int) m.get("sto_count");
            int dru_drugnum=(int)m.get("dru_drugnum");
            int sto_surplus=sto_count-dru_drugnum;
            m.put("sto_surplus",sto_surplus);
        }
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return map;


    }
    @RequestMapping("/pur_message")
    public String pur_message(HttpSession ses, Model model){
       String name = (String) ses.getAttribute("user");
        Map map1 = medService.findPurId(name);
       model.addAttribute("map",map1);
       return "/medicine/pur_message";

    }
    @RequestMapping("/update_pur_pwd")
    public String updatePwd(HttpSession session, @RequestParam Map map){
        String name= (String) session.getAttribute("user");
        String password= (String) map.get("password1");
        medService.updatePur(name,password);
        session.removeAttribute("user");
        return "/ht_login";
    }
    @RequestMapping("yao/{id}")
    public String yaobyid(@PathVariable("id")  int id, HttpSession ses, Model model){
        Map<String,Object> map=medService.findyaobyid(id);
        System.out.println(map);
        model.addAttribute("map",map);
        return "/user_byyao";
        
    }
    
    @RequestMapping("user/buyya")
    public String yaobyid(@RequestParam Map<String,Object> map, HttpServletRequest request){
		
		 Map<String,Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		System.out.println(user);
		map.put("userid",user.get("patient_id"));
		System.out.println(map);
		if(yaocarService.findcf(map)!=null){
			yaocarService.updatenum(map);
		}else{
			yaocarService.addyaocar(map);
		}
		
		return "redirect:/user/showcar";
    }
	
	
	@RequestMapping("user/showcar")
	public String showcar(@RequestParam Map<String,Object> map, HttpServletRequest request){
		Map<String,Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		map.put("userid",user.get("patient_id"));
		List<Map> showcar = yaocarService.showcar(map);
		request.setAttribute("list",showcar);
		System.out.println(showcar);
		return "user_car";
	}
	@RequestMapping("user/delyao/{id}")
	public String removeyao(@RequestParam Map<String,Object> map, @PathVariable("id")  int id,HttpServletRequest request){
		Map<String,Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		map.put("userid",user.get("patient_id"));
		map.put("yaoid",id);
		System.out.println(map);
		yaocarService.delyao(map);
		return "redirect:/user/showcar";
	}
	@RequestMapping("user/delallyao")
	public String removeallyao(@RequestParam Map<String,Object> map,HttpServletRequest request){
		Map<String,Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		map.put("userid",user.get("patient_id"));
		yaocarService.delallyao(map);
		return "redirect:/user/showcar";
	}

    @RequestMapping("/supplier_add")
     @ResponseBody
        public void supplier_add(@RequestParam Map map){
            medService.supplier_add(map);
    }
}
