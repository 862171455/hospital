package org.java.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/11 16:15
 */
public interface medService {
    public int drugCount(String dru_name,Integer dru_drugstore_no);
    public List<Map<String,Object>> findDrug(int page,int rows,String dru_name,int dru_drugstore_no);
    public void add(Map map);
    public void update(Map map);
    public void del(Integer dru_no);
    public List<Map<String, Object>> findSupplier();
    public List<Map<String, Object>> findChinese_medicine();
    public List<Map<String, Object>> findWestern_medicine();
}
