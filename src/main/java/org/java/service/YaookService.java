package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/27 0027 10:18
 */
public interface YaookService {
	void addyaook(Map map);//订单
	List<Map<String,Object>> findyaook();//确认收费列表
	List<Map<String,Object>> findyaookbyid(Map map);//单个用户
	void  updatetype(Map map);//修改状态
}

