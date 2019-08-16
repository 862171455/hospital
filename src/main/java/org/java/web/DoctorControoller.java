package org.java.web;

import org.java.service.StaffService;
import org.java.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/12 0012 10:56
 */
@Controller
public class DoctorControoller {
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private HttpServletRequest request;
	
	@ResponseBody
	@RequestMapping("loadAllDoctor")
	public String loadAllDepartment(){
		
		Map<String,Object> m = new HashMap<String, Object>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		int start = (page-1)*limit;
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("start",start);
		map.put("limit",limit);
		List<Map<String, Object>> list = staffService.findAllStaff(map);
		int count=staffService.findstafffcount();
		m.put("code",0);
		m.put("msg", "");
		m.put("data",list);
		m.put("count",count);
		String json= JsonUtils.objectToJson(m);
		return json;
	}
	
	@ResponseBody
	@RequestMapping("delStafff/{id}")
	public void del(@PathVariable("id")  int id){
		staffService.delstafff(id);
	}
	@ResponseBody
	@RequestMapping("addstafff")
	public void add(@RequestParam("photo") MultipartFile photo,
			@RequestParam Map<String,Object> map
					) throws IOException {
		String path = "D:/his/src/main/resources/static/img";
		//String path=request.getServletContext().getRealPath("/resource/uploads");
		
		String fname = photo.getOriginalFilename();
		fname="up"+fname;
		
		//在指定目录中，产生一个指定名称的新文件（文件是空的，没有内容）
		File newFile = new File(path,fname);
		//判断，保存上传文件的目录是否存在，如果不存在，就产生目录
		if(!newFile.getParentFile().exists()){
			newFile.getParentFile().mkdirs(); //创建目录
		}
		//将上传文件中的数据，写入到新文件中
		photo.transferTo(newFile);
		String dphoto="/images/doc1.jpg";//默认图片
		System.out.println(map);
		if(map.get("id").equals("")){/*是否有id*/
			/*增加*/
			if(fname.equals("up")){
				map.put("photo",dphoto);
			}else{
				map.put("photo","/img/"+fname);
			}
			staffService.addstafff(map);
			
		}else{
			/*修改*/
			if(fname.equals("up")){
//				String x= (String) map.get("p");
//				String [] b = x.split("9999");
//				System.out.println(b[1]);
//				map.put("photo",b[1]);
				map.put("photo",map.get("photo1"));
			}else{
				map.put("photo","/img/"+fname);
			}
			staffService.updatestafff(map);
		}
		
	}
}
