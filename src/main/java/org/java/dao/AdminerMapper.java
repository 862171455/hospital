package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Adminer;

import java.util.Map;

@Mapper
public interface AdminerMapper {
    int deleteByPrimaryKey(Integer adminerId);

    int insert(Adminer record);

    int insertSelective(Adminer record);

    Adminer selectByPrimaryKey(Integer adminerId);

    int updateByPrimaryKeySelective(Adminer record);

    int updateByPrimaryKey(Adminer record);
    
    Map<String,Object> findadminer(Map<String,Object> map);//根据账号密码查医生id
    void addadminer(Map<String,Object> map);
}