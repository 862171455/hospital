package org.java.web;

import org.java.service.HisService;
import org.java.service.StaffService;
import org.java.service.medService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by GD on 2019/8/9 0009 9:38
 */
@Controller
public class StaffController {
	@Autowired
	private StaffService staffService;
	@Autowired
	private medService mdService;
	@Autowired
	private HisService hisService;
	
	@RequestMapping("/zjjs/{id}")
	public String findDivisionById(@PathVariable("id") int id, Model model){
		Map<String, Object> map = staffService.findAllStaffbyid(id);
		model.addAttribute("map",map);
		System.out.println(map);
		return "page/zjdetails";
	}
	
	@ResponseBody
	@RequestMapping("load/data/yy/yao")
	public Map loaddata(){
		Map<String,Object> m=new HashMap<String,Object>();//创一个m集合
		/*查询数据库 返回List<Map<String,Object>> list里的map 有两个kv  k1：name
		 * k1：name  v1：电脑类型
		 * k2：value  v2：数量*/
		
		List<Map<String,Object>> list=mdService.loaddrug();
		List<Map<String,Object>> list1=new ArrayList<>();
		for(Map<String,Object> mm:list){
			Map<String,Object> m1=new HashMap<String,Object>();
			m1.put("name",mm.get("dru_name"));
			m1.put("value",mm.get("dru_drugnum"));
			list1.add(m1);
		}
		//创建个一维集合，保存电脑类型
		List<Object> titles = new ArrayList<Object>();
		for(Map<String,Object> map:list){
			Object name = map.get("dru_name");
			titles.add(name);
		}
		//因为扇形是一个一维数组和一个二维数值形成
		m.put("titles",titles);//一维数组放在m里
		m.put("vals", list1);////二维数组放在m里
		return m;
	}
	@ResponseBody
	@RequestMapping("load/data/yy/sy")
	public Map loadsy(){
		Map<String,Object> map = hisService.findsy();//查询数据库   返回的map  k：电脑类型， v：数量
		Set<String> keys=map.keySet();//得到key数组
		TreeSet treeSet = new TreeSet(keys);
		
		List<Object> list=new ArrayList<>();
		
		for (Object str : treeSet) {
			list.add(map.get(str));
		}//得到val数组
		Map<String,Object> m=new HashMap<String,Object>();//创一个m集合
		m.put("names", treeSet);//把key数组存放到m里，这时的K必须是name
		m.put("vals", list);//把val数组存放到m里，这时的K必须是vals 因为要返回给ECharts的k与v
		return m;
	}
}
