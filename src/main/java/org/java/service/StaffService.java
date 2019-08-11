package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/4 0004 9:38
 */
public interface StaffService {
	public List<Map<String,Object>> findAllStaff();//所有的专家
	public Map<String,Object> findAllStaffbyid(int id);//所有的专家
}
