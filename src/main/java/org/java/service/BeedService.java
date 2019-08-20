package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/18 0018 10:54
 */
public interface BeedService {
	List<Map<String,Object>> findAllbed(Map<String,Object> map);
	int findbedcount();
	void updatetype(Map<String,Object> map);//修改床位状态
	void delbed(int id);
	void updatebed(Map<String,Object> map);
	void addbed(Map<String,Object> map);
}
