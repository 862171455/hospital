package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 19:01
 */

public interface DepartmentService {
	public List<Map<String,Object>> findAllDepartment();//所有部门
	public List<Map<String,Object>> findDepartment(Map<String,Object> map);//分页查询部门
	int findDepartmenttcount();//聚合查询所以部门
}
