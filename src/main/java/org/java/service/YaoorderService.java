package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/27 0027 9:19
 */
public interface YaoorderService {
	void addorder(Map<String,Object> map);// 生成订单
	List<Map<String,Object>> findorder(Map map);//根据订单查看详情
	
}
