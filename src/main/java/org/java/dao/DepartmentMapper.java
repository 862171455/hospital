package org.java.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 18:59
 */
@Mapper
public interface DepartmentMapper {
public List<Map<String,Object>> findAllDepartmentt();//所有部门
	public List<Map<String,Object>> findDepartment(Map<String,Object> map);//分页查询部门
	int findDepartmenttcount();//聚合查询所以部门
}
