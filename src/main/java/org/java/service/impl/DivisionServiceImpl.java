package org.java.service.impl;

import org.java.dao.DivisionMapper;
import org.java.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 18:45
 */
@Service
public class DivisionServiceImpl implements DivisionService {
    @Autowired
	private DivisionMapper divisionMapper;
 
	//@Cacheable("findAlldivision")
	@Override
	public List<Map<String, Object>> findAlldivision() {
		return divisionMapper.findAlldivision();
	}
	
	//@Cacheable("finddivisionbytype")
	@Override
	public List<Map<String, Object>> finddivisionbytype() {
		return divisionMapper.finddivisionbytype();
	}
	
	//@Cacheable("finddivisionbyid")
	@Override
	public Map<String, Object> finddivisionbyid(int id) {
		return divisionMapper.finddivisionbyid(id);
	}
	
	@Override
	public List<Map<String, Object>> finddivision(Map<String, Object> map) {
		return divisionMapper.finddivision(map);
	}
	
	@Override
	public int finddivisioncount() {
		return divisionMapper.finddivisioncount();
	}
	
	@Transactional
	@Override
	public void deldivision(int id) {
		divisionMapper.deldivision(id);
		
	}
	@Transactional
	@Override
	public void updatedivision(Map<String, Object> map) {
		divisionMapper.updatedivision(map);
	}
	@Transactional
	@Override
	public void adddivision(Map<String, Object> map) {
		divisionMapper.adddivision(map);
	}
}
