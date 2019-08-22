package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/4 0004 9:38
 */
public interface StaffService {
	public List<Map<String,Object>> findAllStaff();//所有的专家
	public Map<String,Object> findAllStaffbyid(int id);//所有的专家
	public List<Map<String,Object>> findAllStaff(Map<String,Object> map);//分页查询全部
	public int findstafffcount();//查询所有员工
	public void delstafff(int id);//id删除员工
	public void updatestafff(Map<String,Object> map);//修改员工
	public void addstafff(Map<String,Object> map);//增加员工
	List<Map<String,Object>> findallys(int id);//科室查专家
	Map<String,Object> findysmz(Map<String,Object> map);//门诊的医生登录
	Map<String,Object> findyszy(Map<String,Object> map);//住院的医生登录
}
