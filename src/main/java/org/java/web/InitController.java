package org.java.web;

import org.java.service.DepartmentService;
import org.java.service.DivisionService;
import org.java.service.HisService;
import org.java.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by GD on 2019/8/3 0003 16:30
 */
@Controller
public class InitController {
	@Autowired
	private HisService hisService;
	@Autowired
	private DivisionService divisionService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private StaffService staffService;
	@Autowired
	private HttpServletRequest request;
	
	@RequestMapping("/init")
	public String init(){
		request.getSession().setAttribute("his",hisService.findHis());
		request.getSession().setAttribute("division",divisionService.findAlldivision());
		request.getSession().setAttribute("division1",divisionService.finddivisionbytype());
		request.getSession().setAttribute("department",departmentService.findAllDepartment());
		request.getSession().setAttribute("staff",staffService.findAllStaff());
		
		System.out.println(hisService.findHis().toString());
		System.out.println(divisionService.findAlldivision().toString());
		System.out.println(departmentService.findAllDepartment().toString());
		System.out.println(divisionService.finddivisionbytype().toString());
		System.out.println(staffService.findAllStaff().toString());
		return "page/index";
	}
}
