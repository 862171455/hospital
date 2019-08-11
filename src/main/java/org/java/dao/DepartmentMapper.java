package org.java.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 18:59
 */
@Mapper
public interface DepartmentMapper {
public List<Map<String,Object>> findAllDepartment();//所有部门
}
