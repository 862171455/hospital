package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 18:44
 */
public interface DivisionService {
	public List<Map<String,Object>> findAlldivision();//科室类型
	public List<Map<String,Object>> finddivisionbytype();//所有科室
	public Map<String,Object> finddivisionbyid(int id);//按主键查找科室
}
