package org.java.dao;

import org.apache.ibatis.annotations.Mapper;
import org.java.entity.Stafff;

import java.util.List;
import java.util.Map;

@Mapper
public interface StafffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Stafff record);

    int insertSelective(Stafff record);

    Stafff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stafff record);

    int updateByPrimaryKey(Stafff record);
    public List<Map<String,Object>> findAllStaff(Map<String,Object> map);//分页查询全部
    public int findstafffcount();//查询所有员工
    public void delstafff(int id);//id删除员工
    public void updatestafff(Map<String,Object> map);//修改员工
    public void addstafff(Map<String,Object> map);//增加员工
}