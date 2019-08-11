package org.java.service.impl;

import org.java.dao.DivisionMapper;
import org.java.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 18:45
 */
@Service
public class DivisionServiceImpl implements DivisionService {
    @Autowired
	private DivisionMapper divisionMapper;
	@Override
	public List<Map<String, Object>> findAlldivision() {
		return divisionMapper.findAlldivision();
	}
	
	@Override
	public List<Map<String, Object>> finddivisionbytype() {
		return divisionMapper.finddivisionbytype();
	}
	
	@Override
	public Map<String, Object> finddivisionbyid(int id) {
		return divisionMapper.finddivisionbyid(id);
	}
}
