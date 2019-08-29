package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by GD on 2019/8/11 0011 10:51
 */
public interface PatientService {
	Map<String,Object> findbyUuAndP(Map<String,Object> map);//病人账号密码登录
	void updatepwd(Map<String,Object> map);//密码修改
	void updateDetails(Map<String,Object> map);//信息修改
	void addPatient(Map<String,Object> map);//患者注册
	List<Map<String,Object>> findalltel();//查看已注册的手机
	List<Map<String,Object>> findalluser();//查看已注册的用户
	List<Map<String,Object>> findallbr(Map<String,Object> map);//查看全部病人
	int findbrcount();
}
