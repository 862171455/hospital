package org.java.dao;


import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Patient;

import java.util.List;
import java.util.Map;

@Mapper
public interface PatientMapper {
    int deleteByPrimaryKey(String patientId);

    int insert(Patient record);

    int insertSelective(Patient record);

    Patient selectByPrimaryKey(String patientId);

    int updateByPrimaryKeySelective(Patient record);

    int updateByPrimaryKey(Patient record);
    
    Map<String,Object> findbyUuAndP(Map<String,Object> map);//病人账号密码登录
    void updatepwd(Map<String,Object> map);//密码修改
    void updateDetails(Map<String,Object> map);//信息修改
    void addPatient(Map<String,Object> map);//患者注册
    List<Map<String,Object>> findalltel();//查看已注册的手机
    List<Map<String,Object>> findalluser();//查看已注册的用户
    
}