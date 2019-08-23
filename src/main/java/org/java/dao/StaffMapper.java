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
	List<Map<String,Object>> findallys(int id);//科室查专家
	Map<String,Object> findysmz(Map<String,Object> map);//门诊的医生登录
	Map<String,Object> findyszy(Map<String,Object> map);//住院的医生登录
}
