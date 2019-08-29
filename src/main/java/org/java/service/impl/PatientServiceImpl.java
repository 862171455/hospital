package org.java.service.impl;

import org.java.dao.PatientMapper;
import org.java.entity.Patient;
import org.java.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/11 0011 10:52
 */
@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientMapper patientMapper;
	@Override
	public Map<String, Object> findbyUuAndP(Map<String, Object> map) {
		return patientMapper.findbyUuAndP(map);
	}
	
	@Transactional
	@Override
	public void updatepwd(Map<String, Object> map) {
		patientMapper.updatepwd(map);
		
	}
	@Transactional
	@Override
	public void updateDetails(Map<String, Object> map) {
		
		patientMapper.updateDetails(map);
	}
	
	@Transactional
	@Override
	public void addPatient(Map<String, Object> map) {
		patientMapper.addPatient(map);
		
	}
	
	@Override
	public List<Map<String, Object>> findalltel() {
		return patientMapper.findalltel();
	}
	
	@Override
	public List<Map<String, Object>> findalluser() {
		return patientMapper.findalluser();
	}
	
	@Override
	public List<Map<String, Object>> findallbr(Map<String,Object> map) {
		return patientMapper.findallbr(map);
	}
	
	@Override
	public int findbrcount() {
		return patientMapper.findbrcount();
	}
	
	
}
