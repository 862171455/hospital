package org.java.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/4 0004 9:33
 */
@Mapper
public interface StaffMapper {
	public List<Map<String,Object>> findAllStaff();//所有的专家
	public Map<String,Object> findAllStaffbyid(int id);//所有的专家
}
