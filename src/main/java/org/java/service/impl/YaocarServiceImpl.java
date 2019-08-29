package org.java.service.impl;

import org.java.dao.YaocarMapper;
import org.java.service.YaocarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/23 0023 15:24
 */
@Service
public class YaocarServiceImpl implements YaocarService {
	
	@Autowired
	private YaocarMapper yaocarMapper;
	
	@Transactional
	@Override
	public void addyaocar(Map map) {
		yaocarMapper.addyaocar(map);
	}
	
	@Override
	public List<Map> showcar(Map map) {
		return yaocarMapper.showcar(map);
	}
	
	@Override
	public Map findcf(Map map) {
		return yaocarMapper.findcf(map);
	}
	
	@Transactional
	@Override
	public void updatenum(Map map) {
		yaocarMapper.updatenum(map);
		
	}
	@Transactional
	@Override
	public void delyao(Map map) {
		yaocarMapper.delyao(map);
	}
	@Transactional
	@Override
	public void delallyao(Map map) {
		yaocarMapper.delallyao(map);
	}
	@Transactional
	@Override
	public void updatetype(Map map) {
		yaocarMapper.updatetype(map);
		
	}
}
