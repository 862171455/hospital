package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Yaoorder;

import java.util.List;
import java.util.Map;

@Mapper
public interface YaoorderMapper {
    int deleteByPrimaryKey(Integer yaoorderId);

    int insert(Yaoorder record);

    int insertSelective(Yaoorder record);

    Yaoorder selectByPrimaryKey(Integer yaoorderId);

    int updateByPrimaryKeySelective(Yaoorder record);

    int updateByPrimaryKey(Yaoorder record);
    
    void addorder(Map<String,Object> map);// 生成订单
    List<Map<String,Object>> findorder(Map map);//根据订单查看详情
}