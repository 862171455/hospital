package org.java.service.impl;

import org.java.dao.StaffMapper;
import org.java.dao.StafffMapper;
import org.java.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/4 0004 9:39
 */
@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StafffMapper stafffMapper;
	@Autowired
	private StaffMapper staffMapper;
	
	//@Cacheable("findAllStaff")
	@Override
	public List<Map<String, Object>> findAllStaff() {
		return staffMapper.findAllStaff();
	}
	
	//@Cacheable("findAllStaffbyid")
	@Override
	public Map<String, Object> findAllStaffbyid(int id) {
		return staffMapper.findAllStaffbyid(id);
	}
	
	@Override
	public List<Map<String, Object>> findAllStaff(Map<String, Object> map) {
		return stafffMapper.findAllStaff(map);
	}
	
	@Override
	public int findstafffcount() {
		return stafffMapper.findstafffcount();
	}
	
	@Transactional
	@Override
	public void delstafff(int id) {
		stafffMapper.delstafff(id);
		}
	
	@Override
	public void updatestafff(Map<String, Object> map) {
		stafffMapper.updatestafff(map);
		
	}
	
	@Override
	public void addstafff(Map<String, Object> map) {
		stafffMapper.addstafff(map);
	}
}
