package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/18 12:00
 */
@Mapper
public interface CreateOrderMapper {
    public void createOrder(Map map);
    public Map<String,Object> findProcessInstanceId(@Param("processInstanceId") String  processInstanceId);
}
