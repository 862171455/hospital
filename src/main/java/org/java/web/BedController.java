package org.java.web;

import org.java.service.BeedService;
import org.java.service.RoomService;
import org.java.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/18 0018 9:11
 */
@Controller
public class BedController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private BeedService beedService;
	
	@Autowired
	private RoomService roomService;
	
	@ResponseBody
	@RequestMapping("loadAllbeed")
	public String loadDepartmentt(){
		Map<String,Object> m = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int start = (page-1)*limit;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("limit",limit);
		List<Map<String, Object>> list = beedService.findAllbed(map);
		int count=beedService.findbedcount();
		m.put("code",0);
		m.put("msg", "");
		m.put("data",list);
		m.put("count",count);
		String json= JsonUtils.objectToJson(m);
		return json;
	}
	
	
	@ResponseBody
	@RequestMapping("upbedtype/{id}/{type}")
	public void updatetype(@PathVariable("id")  int id,@PathVariable("type")  int type){
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("id",id);
		m.put("type",type);
		beedService.updatetype(m);
	}
	@ResponseBody
	@RequestMapping("delbed/{id}")
	public void del(@PathVariable("id")  int id){
		
		beedService.delbed(id);
	}
	
	@ResponseBody
	@RequestMapping("findroom_bed")
	public List<Map<String,Object>> findroom(){
	return	roomService.findroom();
	
	}
	
	
	@ResponseBody
	@RequestMapping("addbed")
	public void add(@RequestParam Map<String,Object> map){
		System.out.println(map);
		if(map.get("bed_id").equals("")){/*是否有id*/
			/*增加*/
			beedService.addbed(map);
		}else{
			/*修改*/
			beedService.updatebed(map);
		}
		
	}
	
}
