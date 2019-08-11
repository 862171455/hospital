package org.java.service.impl;

import org.java.dao.StaffMapper;
import org.java.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/4 0004 9:39
 */
@Service
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffMapper staffMapper;
	
	@Cacheable("findAllStaff")
	@Override
	public List<Map<String, Object>> findAllStaff() {
		return staffMapper.findAllStaff();
	}
	
	@Cacheable("findAllStaffbyid")
	@Override
	public Map<String, Object> findAllStaffbyid(int id) {
		return staffMapper.findAllStaffbyid(id);
	}
}
