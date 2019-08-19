package org.java.web;

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
 * Created by GD on 2019/8/18 0018 9:10
 */
@Controller
public class RoomController {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private RoomService roomService;
	
	@ResponseBody
	@RequestMapping("loadAllRoom")
	public String loadDepartmentt(){
		Map<String,Object> m = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int start = (page-1)*limit;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("limit",limit);
		List<Map<String, Object>> list = roomService.findAllroom(map);
		int count=roomService.findroomcount();
		m.put("code",0);
		m.put("msg", "");
		m.put("data",list);
		m.put("count",count);
		String json= JsonUtils.objectToJson(m);
		return json;
	}
	@ResponseBody
	@RequestMapping("delRoom/{id}")
	public void del(@PathVariable("id")  int id){
		System.out.println(id);
		roomService.delroom(id);
	}
	
	
	@ResponseBody
	@RequestMapping("addroom")
	public void add(@RequestParam Map<String,Object> map){
	
		if(map.get("room_id").equals("")){/*是否有id*/
			/*增加*/
			roomService.addroom(map);
		}else{
			/*修改*/
			roomService.updateroom(map);
			}
		
	}
}
