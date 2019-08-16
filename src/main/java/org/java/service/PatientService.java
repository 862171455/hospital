package org.java.service;

import java.util.Map;

/**
 * Created by GD on 2019/8/11 0011 10:51
 */
public interface PatientService {
	Map<String,Object> findbyUuAndP(Map<String,Object> map);//病人账号密码登录
	void updatepwd(Map<String,Object> map);//密码修改
	void updateDetails(Map<String,Object> map);//信息修改
	void addPatient(Map<String,Object> map);//患者注册
}
