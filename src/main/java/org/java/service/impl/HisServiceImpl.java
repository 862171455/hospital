package org.java.service.impl;

import org.java.dao.HisMapper;
import org.java.service.HisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by GD on 2019/8/3 0003 16:40
 */
@Service
public class HisServiceImpl implements HisService {
	@Autowired
	private HisMapper hisMapper;
	
	//@Cacheable("findHis")
	@Override
	public Map<String, Object> findHis() {
		return hisMapper.findHis();
	}
	
	@Transactional
	@Override
	public void updateHis(Map<String, Object> map) {
		hisMapper.updateHis(map);
		
	}
}
