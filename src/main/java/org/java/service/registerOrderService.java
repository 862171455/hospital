package org.java.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/26 8:45
 */
public interface registerOrderService {
    public List<Map<String,Object>> showTask(String user);
    public List<Map<String,Object>> showProcessInstance();
    public void submitOrder( String taskId) ;
    public List<Map<String,Object>> showclaimTask(String userId);
    public void claimTask(String userId,String taskId);
    public List<Map<String,Object>> showback(String userId);
    public  Map<String,Object> findOrder(String regId);
    public void OrderSumit(Map<String,Object> map);
    public void storage(String taskId);
    public void registerOrder(Map map);
    public void detailsCheck(Map map);
    public void checkOver(Map map);
    public void cure(Map map);
    public void operation(Map map);
    public void operationOver(Map map);
    public Map<String,Object> dispose(String regId);
    public Map<String,Object> doctorMed(String regId);
    public void finance(Map map);
    public Map<String,Object> check_body( String  regId);
    public Map<String,Object> checkover( String  regId);
    public Map<String,Object> Cure(String  regId);
    public Map<String,Object> Finance(String  regId);
    public Map<String,Object> Operationover( String  regId);
    public Map<String,Object> Operation(String  regId);
}
