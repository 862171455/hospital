package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Yaook;

import java.util.List;
import java.util.Map;

@Mapper
public interface YaookMapper {
    int deleteByPrimaryKey(String yaookId);

    int insert(Yaook record);

    int insertSelective(Yaook record);

    Yaook selectByPrimaryKey(String yaookId);

    int updateByPrimaryKeySelective(Yaook record);

    int updateByPrimaryKey(Yaook record);
    
    void addyaook(Map map);//订单
    
    List<Map<String,Object>> findyaook();//确认收费列表
    void  updatetype(Map map);//修改状态
}