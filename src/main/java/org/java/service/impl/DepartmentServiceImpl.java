package org.java.service.impl;

import org.java.dao.DepartmentMapper;
import org.java.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 19:02
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Override
	public List<Map<String, Object>> findAllDepartment() {
		return departmentMapper.findAllDepartment();
	}
}
