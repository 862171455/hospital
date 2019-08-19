package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Beed;

import java.util.List;
import java.util.Map;

@Mapper
public interface BeedMapper {
    int deleteByPrimaryKey(Integer bedId);

    int insert(Beed record);

    int insertSelective(Beed record);

    Beed selectByPrimaryKey(Integer bedId);

    int updateByPrimaryKeySelective(Beed record);

    int updateByPrimaryKey(Beed record);
  
    List<Map<String,Object>> findAllbed(Map<String,Object> map);
    int findbedcount();
    
    void updatetype(Map<String,Object> map);//修改床位状态
    void delbed(int id);
    void updatebed(Map<String,Object> map);
    void addbed(Map<String,Object> map);
    
}