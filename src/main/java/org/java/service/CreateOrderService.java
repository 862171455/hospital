package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/18 15:26
 */
public interface CreateOrderService {
    public void createOrder(Map map);
    public List<Map<String,Object>> showTask(String user);
    public List<Map<String,Object>> showProcessInstance();
    //返回的所有，正在运行中的流程实例
    public void submitOrder( String taskId) ;
    public List<Map<String,Object>> showclaimTask(String userId);
    public void claimTask(String userId,String taskId);
    public List<Map<String,Object>> showback(String userId);
    public  Map<String,Object> findOrder(String OrderId);
    public void OrderSumit(Map<String,Object> map);
    public void Stock_In(Map<String,Object> map);
    public List<Map<String,Object>> findStock_In();

}
