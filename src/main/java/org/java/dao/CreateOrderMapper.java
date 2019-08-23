package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/18 12:00
 */
@Mapper
public interface CreateOrderMapper {
    public void createOrder(Map map);
    public Map<String,Object> findProcessInstanceId(@Param("processInstanceId") String  processInstanceId);
    public  Map<String,Object> findOrder(@Param("id") String id);
    public void OrderSumit(Map<String,Object> map);
    public void Stock_In(Map<String,Object> map);
    public List<Map<String,Object>> findStock_In();

}
