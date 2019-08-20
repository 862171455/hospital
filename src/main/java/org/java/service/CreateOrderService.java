package org.java.service;

import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/18 15:26
 */
public interface CreateOrderService {
    public void createOrder(Map map);
    public List<Map<String,Object>> showTask(String user);
    //返回的所有，正在运行中的流程实例
}
