package org.java.service.impl;

import org.java.dao.BeedMapper;
import org.java.service.BeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/18 0018 10:55
 */
@Service
public class BeedServiceImpl implements BeedService{
	
	@Autowired
	private BeedMapper beedMapper;
	@Override
	public List<Map<String, Object>> findAllbed(Map<String, Object> map) {
		return beedMapper.findAllbed(map);
	}
	
	@Override
	public int findbedcount() {
		return beedMapper.findbedcount();
	}
	
	@Transactional
	@Override
	public void updatetype(Map<String, Object> map) {
		beedMapper.updatetype(map);
	}
	
	@Transactional
	@Override
	public void delbed(int id) {
		beedMapper.delbed(id
		);
		
	}
	@Transactional
	@Override
	public void updatebed(Map<String, Object> map) {
		beedMapper.updatebed(map);
	}
	
	@Transactional
	@Override
	public void addbed(Map<String, Object> map) {
	beedMapper.addbed(map);
	}
}
