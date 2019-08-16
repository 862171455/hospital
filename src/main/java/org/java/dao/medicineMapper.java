package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by duankun1997@qq.com on 2019/8/11 16:10
 */
@Mapper
public interface medicineMapper {
    public int drugCount(@Param("dru_name") String dru_name,@Param("dru_drugstore_no") int dru_drugstore_no);
    public List<Map<String, Object>> findDrug(@Param("start") int start, @Param("rows") int rows,@Param("dru_name") String dru_name,@Param("dru_drugstore_no") int dru_drugstore_no);
    public void add(Map map);
    public void update(Map map);
    public void del(Integer id);
    public List<Map<String, Object>> findSupplier();
    public List<Map<String, Object>> findChinese_medicine();
    public List<Map<String, Object>> findWestern_medicine();
    public List<Map<String,Object>> findDrugstore();
    public int purCount(Map map);
    public int managerCount(Map map);
    public Map  findPurId(@Param("username")String username);
    public Map  findManagerId(@Param("username")String username);
    public void updatePur(@Param("name") String name,@Param("password1") String password1);
}
