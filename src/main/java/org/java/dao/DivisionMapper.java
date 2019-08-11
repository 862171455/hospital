package org.java.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 18:40
 */
@Mapper
public interface DivisionMapper {
	public List<Map<String,Object>> findAlldivision();//所有科室类型
	public List<Map<String,Object>> finddivisionbytype();//所有科室
	public Map<String,Object> finddivisionbyid(int id);//按主键查找科室
}
