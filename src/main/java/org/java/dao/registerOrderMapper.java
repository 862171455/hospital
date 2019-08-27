package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/26 8:20
 */
@Mapper
public interface registerOrderMapper {
    public Map<String,Object> findProcessInstanceId(@Param("processInstanceId") String  processInstanceId);
    public  Map<String,Object> findOrder(@Param("regId") String regId);
    public void OrderSumit(Map<String,Object> map);
    public void doctor_med(Map map);
}
