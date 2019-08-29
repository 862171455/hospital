package org.java.service.impl;

import org.java.dao.YaookMapper;
import org.java.entity.Yaook;
import org.java.service.YaookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/27 0027 10:19
 */
@Service
public class YaookServiceImpl implements YaookService {
	@Autowired
	private YaookMapper yaookMapper;
	
	@Transactional
	@Override
	public void addyaook(Map map) {
		yaookMapper.addyaook(map);
	}
	
	@Override
	public List<Map<String,Object>> findyaook() {
		return yaookMapper.findyaook();
	}
	
	@Override
	public void updatetype(Map map) {
		yaookMapper.updatetype(map);
	}
}
