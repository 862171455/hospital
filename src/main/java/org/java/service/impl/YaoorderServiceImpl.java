package org.java.service.impl;

import org.java.dao.YaoorderMapper;
import org.java.service.YaoorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/27 0027 9:20
 */
@Service
public class YaoorderServiceImpl  implements YaoorderService {
	@Autowired
	private YaoorderMapper yaoorderMapper;
	
	@Override
	public void addorder(Map<String, Object> map) {
	yaoorderMapper.addorder(map);
	}
	
	@Override
	public List<Map<String, Object>> findorder(Map map) {
		return yaoorderMapper.findorder(map);
	}
}
