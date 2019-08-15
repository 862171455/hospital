package org.java.service.impl;

import org.java.dao.AdminerMapper;
import org.java.service.AdminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by GD on 2019/8/15 0015 10:24
 */
@Service
public class AdminerServiceImpl implements AdminerService {
	
	@Autowired
	private AdminerMapper adminerMapper;
	@Override
	public int findadminer(Map<String, Object> map) {
		if(adminerMapper.findadminer(map)==null){
			return 0;
		}else{
			 return (int)adminerMapper.findadminer(map).get("stafff_id");
		}
		
	}
}
