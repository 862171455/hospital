package org.java.service.impl;

import org.java.dao.RoomMapper;
import org.java.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/18 0018 9:19
 */
@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomMapper roomMapper;
	
	@Override
	public List<Map<String, Object>> findAllroom(Map<String, Object> map) {
		return roomMapper.findAllroom(map);
	}
	
	@Override
	public int findroomcount() {
		return roomMapper.findroomcount();
	}
	
	@Transactional
	@Override
	public void delroom(int id) {
		roomMapper.delroom(id);
	}
	@Transactional
	@Override
	public void updateroom(Map<String, Object> map) {
		roomMapper.updateroom(map);
		
	}
	@Transactional
	@Override
	public void addroom(Map<String, Object> map) {
		roomMapper.addroom(map);
	}
	
	@Override
	public List<Map<String, Object>> findroom() {
		
		return roomMapper.findroom();
	}
}
