package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/18 0018 9:17
 */
public interface RoomService {
	List<Map<String,Object>> findAllroom(Map<String,Object> map);//分页查询全部房间
	int findroomcount();
	void delroom(int id);//按主键删除
	void updateroom(Map<String,Object> map);
	void addroom(Map<String,Object> map);
	List<Map<String,Object>> findroom();
}
