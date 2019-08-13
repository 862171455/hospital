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
	public List<Map<String,Object>> finddivision(Map<String,Object> map);//分页
	public int finddivisioncount();//聚合
	public void deldivision(int id);//主键删除
	public void updatedivision(Map<String,Object> map);//修改
	public  void  adddivision(Map<String,Object> map);//增加
	
}
